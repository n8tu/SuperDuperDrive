package com.faisal.superduperdrive.mappers;

import com.faisal.superduperdrive.models.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS (username,password,salt,firstname,lastname) VALUES(#{username},#{password},#{salt},#{firstName},#{lastName});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "userId")
    void insertUser(User user);

    @Select("SELECT * FROM USERS WHERE userid = #{id}")
    User getUserById(int id);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUserByUsername(String username);



}
