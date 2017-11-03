package com.zhang.chat.service.interfaces;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.request.FileUploadBean;

import java.io.IOException;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/23 17:15
 * @Modified By:
 */
public interface FileService {

    /**
     * 上传头像
     *
     * @return
     * @param fileUploadBean
     */
    BaseFeed<String> uploadImgFace(FileUploadBean fileUploadBean) throws IOException;
}
