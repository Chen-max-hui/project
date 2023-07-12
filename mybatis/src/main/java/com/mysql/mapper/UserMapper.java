package com.mysql.mapper;

import com.mysql.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectUser(@Param("userName") String userName, @Param("password") String password);
}
