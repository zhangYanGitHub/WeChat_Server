package com.zhang.chat.intercepter;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhang.chat.entity.sql.Header;
import com.zhang.chat.service.interfaces.HeaderService;
import com.zhang.chat.utils.LogUtils;
import com.zhang.chat.utils.StrUtil;
import org.apache.struts2.StrutsStatics;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局方法拦截器
 */
public class LoginInterceptor extends MethodFilterInterceptor {
//
    @Resource
    private HeaderService headerService;

    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {


        ActionContext actionContext = actionInvocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        String userId = request.getParameter("m_id");
        if (StrUtil.isBlank(userId)) {
            return "noUser_id";

        }
        long _id = Long.parseLong(userId);

        Header user = new Header();
        user.setM_id(_id);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            String taken_value = cookies[0].getValue();
            Header token = headerService.selectByM_id(user);
            if (token == null) {
                LogUtils.error(this.getClass(), "m_id = " + userId + "  未存入cookies");
            } else {
                if ((StrUtil.isNotBlank(token.getToken()) || StrUtil.isNotBlank(taken_value))
                        && taken_value.equals(token.getToken())) {
                    actionInvocation.invoke();
                    return Action.SUCCESS;
                }
            }
        }
        return "noToken";
    }



}
