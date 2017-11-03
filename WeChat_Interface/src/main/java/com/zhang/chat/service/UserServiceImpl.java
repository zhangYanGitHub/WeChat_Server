package com.zhang.chat.service;

import com.opensymphony.xwork2.ActionContext;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.base.BaseService;
import com.zhang.chat.dao.interfaces.UserDao;
import com.zhang.chat.entity.request.RequestUser;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.entity.sql.Header;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.service.interfaces.HeaderService;
import com.zhang.chat.service.interfaces.UserService;
import com.zhang.chat.utils.Constant;
import com.zhang.chat.utils.StrUtil;
import com.zhang.chat.utils.TokenUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

//注入服务
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {


    @Resource
    UserDao userDao;

    @Resource
    HeaderService headerService;

    @Override
    public BaseFeed<MainData> login(User requestUser) {
        BaseFeed<MainData> feed = new BaseFeed<MainData>();
        if (requestUser.getUu_id() == 0 || StrUtil.isBlank(requestUser.getUser_password())) {
            //参数不齐
            feed.setInfo("参数不齐");
            feed.setData(null);
            feed.setCode(Constant.RESPONSE_ERROR_CODE_100);
            return feed;

        }
        User user = userDao.selectByUu_idAndPassword(requestUser);
        if (user == null) {
            //无此用户
            feed.setInfo("无此用户");
            feed.setData(null);
            feed.setCode(Constant.RESPONSE_ERROR_CODE_101);
            return feed;
        }
        boolean equals = requestUser.getUser_password().equals(user.getUser_password());
        if (equals) {
            //产生token
            createToken(user);

            //登录成功
            feed.setInfo("登录成功");
            feed.setData(null);
            feed.setCode(Constant.RESPONSE_CODE_200);
        } else {
            //密码错误
            feed.setInfo("密码错误");
            feed.setData(null);
            feed.setCode(Constant.RESPONSE_ERROR_CODE_102);
        }

        return feed;

    }


    /**
     * 创建token 并添加到响应头中
     *
     * @param user
     */
    private void createToken(User user) {
        HttpServletResponse response = ServletActionContext.getResponse();
        Header header = new Header();
        header.setM_id(user.getM_Id());
        String token = TokenUtil.genRandomToken();
        header.setToken(token);
        headerService.add(header);
        Cookie token1 = new Cookie("token", token);
        response.addCookie(token1);

    }


    @Override
    public BaseFeed<User> register(RequestUser requestUser) {
        if (requestUser == null
                || StrUtil.isBlank(requestUser.getUser_phone())
                || StrUtil.isBlank(requestUser.getUser_password())
                || StrUtil.isBlank(requestUser.getUser_real_name())
                || StrUtil.isBlank(requestUser.getUser_name())
                || requestUser.getUser_sex() != -1) {
            //参数不齐
            return Parameter_irregularity();
        }
        User user = requestUser;
        user.setUser_register_date(new Date().getTime());
        user = userDao.register(user);
        long uu_Id = user.getM_Id() + 10000;
        user.setUu_id(uu_Id);
        userDao.update(user);
        feed.setCode(Constant.RESPONSE_ERROR_CODE_100);
        feed.setData(user);
        feed.setInfo("注册成功");
        return feed;
    }

    @Override
    public BaseFeed<User> update(RequestUser requestUser) {
        if (requestUser == null || requestUser == null) {
            //参数不齐
            return Parameter_irregularity();
        }
        User user = userDao.get(requestUser);
        User user1 = requestUser;
        switch (requestUser.getType()) {
            case RequestUser.UPDATE_NAME:
                user.setUser_name(user1.getUser_name());
                break;
            case RequestUser.UPDATE_ACCOUNT:
                user.setUser_account(user1.getUser_account());
                break;
            case RequestUser.UPDATE_SEX:
                user.setUser_sex(user1.getUser_sex());
                break;
            case RequestUser.UPDATE_IMGFACEPATH:
                //删除原来的头像
                ActionContext ac = ActionContext.getContext();
                ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
                String path = sc.getRealPath(Constant.USER_IMG_FACE_DIR);

                if (StrUtil.isBlank(user.getUser_img_face_path())) { //第一次上传头像
                    user.setUser_img_face_path(user.getUser_img_face_path());
                } else {
                    String[] split = user1.getUser_img_face_path().split("/");
                    if (split != null && split.length > 0) {
                        String fileName = split[split.length - 1];
                        File file = new File(new File(path), fileName);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    user.setUser_img_face_path(user1.getUser_img_face_path());
                }
                break;
            case RequestUser.UPDATE_PHONE:
                user.setUser_phone(user1.getUser_phone());
                break;
            case RequestUser.UPDATE_PASSWORD:
                user.setUser_phone(user1.getUser_phone());
                break;
            case RequestUser.UPDATE_DESC:
                user.setUser_desc(user1.getUser_desc());
                break;
            case RequestUser.UPDATE_ADDRESS:
                user.setU_NationID(user1.getU_NationID());
                user.setU_Province(user1.getU_Province());
                user.setU_City(user1.getU_City());
                break;
        }
        userDao.update(user);
        feed.setInfo("修改信息成功");
        feed.setCode(Constant.RESPONSE_CODE_200);
        feed.setData(new User());
        return feed;
    }
}
