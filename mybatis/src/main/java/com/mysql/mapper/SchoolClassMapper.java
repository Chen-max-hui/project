package com.mysql.mapper;

import com.mysql.pojo.SchoolClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolClassMapper {
    List<SchoolClass> selectAll();
    int deleteById(Integer id);
    int updateInfo(@Param("id") Integer id, @Param("className") String className, @Param("classDesc") String classDesc);

    SchoolClass selectById2(Integer id);
    int insertDemo(SchoolClass schoolClass);
}
