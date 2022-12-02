package com.faisal.superduperdrive.mappers;

import com.faisal.superduperdrive.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename,contentType,filesize,userId,fileData) " +
            "VALUES(#{filename},#{contentType},#{filesize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "fileId")
    int insertFile(File file);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getAllFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{id} AND userId = #{userId}")
    File getFileById(Integer id, Integer userId);

    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userId = #{userId}")
    File getFileByName(String filename, Integer userId);


    @Delete("DELETE FROM FILES WHERE fileId = #{id} AND userId = #{userId}")
    void deleteFile(Integer id, Integer userId);
}
