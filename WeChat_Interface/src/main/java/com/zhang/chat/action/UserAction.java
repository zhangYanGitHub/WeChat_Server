package com.zhang.chat.action;

import com.opensymphony.xwork2.ModelDriven;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.service.interfaces.UserDataService;
import com.zhang.chat.service.interfaces.UserService;
import com.zhang.chat.utils.Constant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
public class UserAction implements ModelDriven<User> {
    User requestUser = new User();
    BaseFeed<User> baseFeed;
    BaseFeed<MainData> mainDataBaseFeed;
    @Resource
    UserService userService;
    @Resource
    UserDataService userDataService;

    @Override
    public User getModel() {
        return requestUser;
    }

    public void setMainDataBaseFeed(BaseFeed<MainData> mainDataBaseFeed) {
        this.mainDataBaseFeed = mainDataBaseFeed;
    }

    public BaseFeed<MainData> getMainDataBaseFeed() {
        return mainDataBaseFeed;
    }

    public void setBaseFeed(BaseFeed<User> baseFeed) {
        this.baseFeed = baseFeed;
    }

    public BaseFeed<User> getBaseFeed() {
        return baseFeed;
    }

    public String login() throws Exception {
        mainDataBaseFeed = userService.login(requestUser);
        if (mainDataBaseFeed.getCode() == Constant.RESPONSE_CODE_200) {
            mainDataBaseFeed = userDataService.getUserData(mainDataBaseFeed.getData().getUser());
        }
        return "success";
    }

    public String update() throws Exception {
        baseFeed = userService.update(requestUser);
        return "success";
    }

    public String register() throws Exception {
        baseFeed = userService.register(requestUser);
        return "success";
    }

}
