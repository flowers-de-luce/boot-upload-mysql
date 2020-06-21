package com.jiabao.uploadMySQL.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

import static org.springframework.util.unit.DataUnit.MEGABYTES;

/*
* 文件上传配置类
* */
@Configuration
public class FileUploadUtils implements WebMvcConfigurer {

    /* 文件上传路径*/
    @Value("file:/D:/uploadMySQL/")
    private String mImagesPath;

    @Bean
    /* 文件上传大小配置 */
    public MultipartConfigElement multipartConfigElement() {
        long maxFileSize = 1024l;
        long maxRequestSize = 2048l;
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // MEGABYTES  :   兆字节
        factory.setMaxFileSize(DataSize.of(maxFileSize, MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(maxRequestSize, MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")){
            String imagesPath = FileUploadUtils.class.getClassLoader().getResource("").getPath();
            System.out.print("1.上传配置类imagesPath=="+imagesPath+"\n");
            if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/images/";
            mImagesPath = imagesPath;
        }
        System.out.print("imagesPath============="+mImagesPath+"\n");
        //LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath============="+mImagesPath+"\n");
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
        // TODO Auto-generated method stub
        System.out.print("2.上传配置类mImagesPath=="+mImagesPath+"\n");
        WebMvcConfigurer.super.addResourceHandlers(registry);

    }

}
