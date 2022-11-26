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

    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    File getFile(Integer id);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    void deleteFile(Integer id);
}
