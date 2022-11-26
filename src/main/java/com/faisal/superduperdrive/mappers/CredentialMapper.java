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

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAllCredentials();

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{id}")
    void deleteCredential(Integer id);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{id}")
    Credential getCredentialById(Integer id);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE credentialid = #{id}")
    void updateCredential(Credential credential);
}
