package com.faisal.superduperdrive.mappers;

import com.faisal.superduperdrive.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) " +
            "VALUES(#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id" , keyColumn = "credentialId")
    void insertCredential(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getAllCredentials(Integer userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{id} AND userId = #{userId}")
    void deleteCredential(Integer id, Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{id}")
    Credential getCredentialById(Integer id);

    @Update("UPDATE CREDENTIALS SET url = #{credential.url}, username = #{credential.username}, password = #{credential.password} WHERE credentialId = #{credential.id} AND userId = #{userId}")
    void updateCredential(Credential credential, Integer userId);
}
