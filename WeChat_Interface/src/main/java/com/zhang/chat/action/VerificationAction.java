package com.zhang.chat.action;

import com.opensymphony.xwork2.ModelDriven;
import com.zhang.chat.entity.request.VerificationRequest;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.Verification;
import com.zhang.chat.service.interfaces.VerificationService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import static com.opensymphony.xwork2.Action.SUCCESS;

@Controller
@Scope("prototype")
public class VerificationAction implements ModelDriven<VerificationRequest> {
    @Resource
    private VerificationService verificationService;
    private VerificationRequest verifivationRequest = new VerificationRequest();

    private BaseFeed<ResList<Verification>> baseFeed;
    private BaseFeed<ResList<User>> search;

    public BaseFeed<ResList<User>> getSearch() {
        return search;
    }

    public BaseFeed<ResList<Verification>> getBaseFeed() {
        return baseFeed;
    }

    public void setVerifivationRequest(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    public VerificationRequest getVerifivationRequest() {
        return verifivationRequest;
    }

    public String getVerificationList() throws Exception {
        baseFeed = verificationService.getVerificationList(verifivationRequest.getUser_id());
        return SUCCESS;
    }
    public String search() throws Exception {
        search = verificationService.search(verifivationRequest.getUser_id(),verifivationRequest.getSearch_key());
        return SUCCESS;
    }

    @Override
    public VerificationRequest getModel() {
        return verifivationRequest;
    }
}
