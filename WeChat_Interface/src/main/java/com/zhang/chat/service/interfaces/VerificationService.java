package com.zhang.chat.service.interfaces;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.Verification;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/17 19:43
 * @Modified By:
 */
public interface VerificationService {

    BaseFeed<ResList<Verification>> getVerificationList(long user_id);

    BaseFeed<ResList<User>> search(long user_id, String searchKey);
}
