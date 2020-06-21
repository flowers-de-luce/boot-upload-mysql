package com.jiabao.uploadMySQL.service;

import com.jiabao.uploadMySQL.dao.UploadFileMapper;
import com.jiabao.uploadMySQL.model.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadFileService {
    @Autowired
    UploadFileMapper uploadFileMapper;

    // 插入图片
    public int insertUrl(String name, String url, String physicspath){
        System.out.print("开始插入=name=="+name+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        System.out.print("开始插入=physicspath=="+physicspath+"\n");
        int insertResult = this.uploadFileMapper.insertUrl(name, url, physicspath);
        System.out.println("插入结果：" + insertResult);
        return insertResult;
    }

    // 查询
    public List<UploadFile> selectUpload(){
        List<UploadFile> uploadFiles = this.uploadFileMapper.selectboot_upload();
        return uploadFiles;
    }
}
