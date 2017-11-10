package com.zhang.chat.action;

import com.opensymphony.xwork2.ModelDriven;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.request.FileUploadBean;
import com.zhang.chat.service.interfaces.FileService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/23 17:14
 * @Modified By:
 */
@Controller
@Scope("prototype")
public class FileAction implements ModelDriven<FileUploadBean> {
    @Resource
    private FileService fileService;
    //上传参数
    private FileUploadBean fileUploadBean = new FileUploadBean();
    //返回数据
    private BaseFeed<String> baseFeed;

    public void setBaseFeed(BaseFeed<String> baseFeed) {
        this.baseFeed = baseFeed;
    }

    public BaseFeed<String> getBaseFeed() {
        return baseFeed;
    }

    public String uploadFile() throws IOException {

        baseFeed = fileService.uploadImgFace(fileUploadBean);
        return SUCCESS;
    }

    public FileUploadBean getModel() {
        return fileUploadBean;
    }

}
