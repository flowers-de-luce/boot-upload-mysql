package com.jiabao.uploadMySQL.dao;

import com.jiabao.uploadMySQL.model.UploadFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UploadFileMapper {
    @Insert({"insert into boot_upload (name,url,physicspath) values (#{name},#{url},#{physicspath})"})
    public int insertUrl(@Param("name") String name,
                         @Param("url") String url,
                         @Param("physicspath") String physicspath);


    //查询
    @Select("select * from boot_upload")
    public List<UploadFile> selectboot_upload();
}
