package com.zhang.chat.action;

import com.opensymphony.xwork2.ModelDriven;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.service.interfaces.UserDataService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
public class UserDataAction implements ModelDriven<User> {
    User requestUser = new User();

    public void setRequestUser(User requestUser) {
        this.requestUser = requestUser;
    }

    public User getRequestUser() {
        return requestUser;
    }

    @Resource
    UserDataService userDataService;
    private BaseFeed<MainData> baseFeed;

    public void setBaseFeed(BaseFeed<MainData> baseFeed) {
        this.baseFeed = baseFeed;
    }

    public BaseFeed<MainData> getBaseFeed() {
        return baseFeed;
    }

    @Override
    public User getModel() {
        return requestUser;
    }

    public String getUserData() throws Exception {
        baseFeed = userDataService.getUserData(requestUser);
        return "success";
    }
}
