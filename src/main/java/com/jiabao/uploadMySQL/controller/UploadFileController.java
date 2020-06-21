package com.jiabao.uploadMySQL.controller;

import com.jiabao.uploadMySQL.model.UploadFile;
import com.jiabao.uploadMySQL.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/file")
public class UploadFileController {
    @Autowired
    private UploadFileService uploadFileService;

    private String url;

    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file) {
        System.out.println("上传文件");
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名
        String filename = file.getOriginalFilename();
        filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + filename;
        System.out.println("（加个时间戳，尽量避免文件名称重复）保存的文件名为：" + filename);

        String path = "D:/uploadMySQL/" + filename;
        // 文件绝对路径
        System.out.println("保存文件绝对路径" + path + "\n");

        // 创建文件绝对路径
        File dest = new File(path);

        // 判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }

        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            // 上传文件
            file.transferTo(dest);
            System.out.println("保存文件路径" + path);
            url = "http://localhost:8080/images/" + filename;
            int insertResult = uploadFileService.insertUrl(filename, url, path);
            System.out.println("插入结果：" + insertResult);
            System.out.println("保存的完整URL" + url);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功， 文件url:" + url;
    }

    // 查询
    @RequestMapping("/chaxun")
    public String selectAll(Model model){
        List<UploadFile> uploadFiles = uploadFileService.selectUpload();
        model.addAttribute("uploadFile", uploadFiles);

        return "/filelist";
    }
}
