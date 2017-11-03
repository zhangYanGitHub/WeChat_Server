package com.zhang.chat.service;

import com.zhang.chat.base.BaseService;
import com.zhang.chat.dao.interfaces.UserDao;
import com.zhang.chat.dao.interfaces.VerificationDao;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.Verification;
import com.zhang.chat.service.interfaces.VerificationService;
import com.zhang.chat.utils.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/17 19:43
 * @Modified By:
 */
//注入服务
@Service("verificationService")
public class VerificationServiceImpl extends BaseService implements VerificationService {

    @Resource
    private VerificationDao verificationDao;
    @Resource
    private UserDao userDao;

    @Override
    public BaseFeed<ResList<Verification>> getVerificationList(long user_id) {
        ResList<Verification> list = verificationDao.getList(user_id);

        BaseFeed<ResList<Verification>> resListBaseFeed = new BaseFeed<ResList<Verification>>();
        resListBaseFeed.setData(list);
        resListBaseFeed.setInfo("OK");
        resListBaseFeed.setCode(Constant.RESPONSE_CODE_200);
        return resListBaseFeed;
    }


    public BaseFeed<ResList<User>> search(long user_id, String searchKey) {
        BaseFeed<ResList<User>> search = userDao.search(user_id,searchKey);
        return search;
    }
}
