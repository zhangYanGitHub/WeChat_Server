package com.zhang.chat.action;

import com.opensymphony.xwork2.ModelDriven;
import com.zhang.chat.base.BaseAction;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.request.RequestUser;
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
public class UserAction extends BaseAction implements ModelDriven<RequestUser> {
    RequestUser requestUser = new RequestUser();
    BaseFeed<User> baseFeed;
    BaseFeed<MainData> mainDataBaseFeed;
    @Resource
    UserService userService;
    @Resource
    UserDataService userDataService;

    @Override
    public RequestUser getModel() {
        return requestUser;
    }

    public void setBaseFeed(BaseFeed<User> baseFeed) {
        this.baseFeed = baseFeed;
    }

    public BaseFeed<User> getBaseFeed() {
        return baseFeed;
    }

    public String login() throws Exception {
        mainDataBaseFeed = userService.login(requestUser);
        if (baseFeed.getCode() == Constant.RESPONSE_CODE_200) {
            mainDataBaseFeed = userDataService.getUserData(requestUser);
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
