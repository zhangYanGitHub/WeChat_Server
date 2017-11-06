package com.zhang.chat.service;

import com.zhang.chat.base.BaseService;
import com.zhang.chat.dao.interfaces.HeaderDao;
import com.zhang.chat.entity.sql.Header;
import com.zhang.chat.service.interfaces.HeaderService;
import com.zhang.chat.utils.LogUtils;
import com.zhang.chat.utils.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//注入服务
@Service("headerService")
public class HeaderServiceImpl extends BaseService<Header> implements HeaderService {

    public static Map<Long, Header> token = new HashMap<Long, Header>();
    //自动注入userDao，也可以使用@Autowired
    @Resource
    private HeaderDao headerDao;

    @Override
    public Header selectByM_id(Header header) {
        if (token.containsKey(header.getM_id())) {
            Header header1 = token.get(header.getM_id());
            if (header1 != null) {
                return header1;
            }
        }
        Header header1 = headerDao.selectByM_id(header);
        if(header1 != null){
            token.put(header1.getM_id(), header1);
        }
        return header1;
    }

    @Override
    public void add(Header header) {
        if (header == null || StrUtil.isBlank(header.getToken())) {
            LogUtils.error(HeaderServiceImpl.class, "header == NULL || StrUtil.isBlank(header.getToken()) == TRUE");
            return;
        }
        headerDao.addHeader(header);

    }

    @Override
    public void update(Header header) {
        if (header == null || StrUtil.isBlank(header.getToken())) {
            LogUtils.error(HeaderServiceImpl.class, "header == NULL || StrUtil.isBlank(header.getToken()) == TRUE");
            return;
        }
        headerDao.update(header);
    }
}
