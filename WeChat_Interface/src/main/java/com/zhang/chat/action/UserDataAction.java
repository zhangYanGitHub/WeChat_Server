package com.zhang.chat.action;

import com.opensymphony.xwork2.ModelDriven;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.request.RequestUser;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.service.interfaces.UserDataService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
public class UserDataAction implements ModelDriven<RequestUser> {
    RequestUser requestUser = new RequestUser();

    @Resource
    UserDataService userDataService;
    private BaseFeed<MainData> baseFeed;

    @Override
    public RequestUser getModel() {
        return requestUser;
    }

    public String getUserData() throws Exception {
        baseFeed = userDataService.getUserData(requestUser);
        return "success";
    }
}
