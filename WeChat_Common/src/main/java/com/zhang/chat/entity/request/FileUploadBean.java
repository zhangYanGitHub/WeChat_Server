package com.zhang.chat.entity.request;


import java.io.File;

/**
 * @Author: ZhangYan
 * @Description: 上传文件的请求bean
 * @Date Create In: 2017/9/23 17:23
 * @Modified By:
 */
public class FileUploadBean {

    /**
     * 文件上传类型
     * 1  用户头像
     */
    private int type;
    private int user_id;
    private File file; //上传的文件
    private String fileName; //文件名称
    private String imageContentType; //文件类型

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File image) {
        this.file = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
