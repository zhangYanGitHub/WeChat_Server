package com.zhang.chat.service;

import com.opensymphony.xwork2.ActionContext;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.base.BaseService;
import com.zhang.chat.entity.request.FileUploadBean;
import com.zhang.chat.service.interfaces.FileService;
import com.zhang.chat.utils.Constant;
import com.zhang.chat.utils.TimeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/23 17:15
 * @Modified By:
 */

//注入服务
@Service("fileService")
public class FileServiceImpl extends BaseService implements FileService {


    public BaseFeed<String> uploadImgFace(FileUploadBean fileUploadBean) throws IOException {
        BaseFeed<String> baseFeed = new BaseFeed<String>();
        String fileName = String.valueOf(Constant.USER_IMG_FACE_FILE_NAME + fileUploadBean.getUser_id() + "_" + TimeUtils.now().getTimeInMillis() + ".png");

        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String path = sc.getRealPath(Constant.USER_IMG_FACE_DIR);
        if (fileUploadBean.getFile() != null) {
            File saveFile = new File(new File(path), fileName);
            if (!saveFile.getParentFile().exists())
                saveFile.getParentFile().mkdirs();
            FileUtils.copyFile(fileUploadBean.getFile(), saveFile);
            baseFeed.setCode(Constant.RESPONSE_CODE_200);
            baseFeed.setInfo("上传成功");
            baseFeed.setData( Constant.USER_IMG_FACE_DIR + fileName);
        } else {
            baseFeed.setCode(Constant.RESPONSE_ERROR_CODE_100);
            baseFeed.setInfo("参数不齐");
            baseFeed.setData("");
        }
        return baseFeed;
    }
}
