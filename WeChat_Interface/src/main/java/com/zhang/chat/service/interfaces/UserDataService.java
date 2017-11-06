package com.zhang.chat.service.interfaces;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.request.RequestUser;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.entity.sql.User;

public interface UserDataService {
    BaseFeed<MainData> getUserData(User requestUser);
}
