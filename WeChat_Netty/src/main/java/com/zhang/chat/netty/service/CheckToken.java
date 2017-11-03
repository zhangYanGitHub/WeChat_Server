package com.zhang.chat.netty.service;

import com.zhang.chat.dao.interfaces.HeaderDao;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.sql.Header;
import com.zhang.chat.netty.listener.FutureListener;
import com.zhang.chat.netty.protocol.MessageHolder;
import com.zhang.chat.netty.protocol.ProtocolHeader;
import com.zhang.chat.netty.serializer.Serializer;
import com.zhang.chat.utils.Constant;
import com.zhang.chat.utils.LogUtils;
import io.netty.util.concurrent.Future;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/18 22:26
 * @Modified By:
 */
public class CheckToken extends BaseNettyService {
    private static volatile CheckToken instance;
    private final HeaderDao headerDao;

    public static CheckToken newInstance() {
        if (instance == null) {
            synchronized (PersonMessage.class) {
                if (instance == null) {
                    instance = new CheckToken();
                    return instance;
                }
            }
        }
        return instance;
    }

    private CheckToken() {
        headerDao = (HeaderDao) getBean("headerDao");
    }


    public void checkToken(MessageHolder deserialize) {
        String body = deserialize.getBody();
        Header header = Serializer.deserialize(body, Header.class);


        Header token = headerDao.selectByM_id(header);

        String token1 = header.getToken();
        if (token1.contains("=")) {
            String[] split = token1.split("=");
            token1 = split[split.length - 1];
        }
        if (token == null) {
            LogUtils.error(CheckToken.class, "token 未查询到");
        } else if (token.getToken().equals(token1)) {
            LogUtils.info(CheckToken.class, token.getToken() + "token 校验成功");
        } else {
            BaseFeed<String> baseFeed = new BaseFeed<String>("您的账号Token过期！", Constant.RESPONSE_ERROR_CODE_1000, "");
            Future future = sendResponse(ProtocolHeader.RESPONSE, Serializer.serialize(baseFeed), ProtocolHeader.Token, deserialize.getChannel());

            future.addListener(new FutureListener() {
                @Override
                public void success() {
                    LogUtils.info(HeaderData.class, "token发送成功");
                }

                @Override
                public void error() {
                    LogUtils.info(HeaderData.class, "token发送失败");
                }
            });
        }


    }

    @Override
    public void deal(MessageHolder messageHolder) {

    }
}
