package com.mysql.mapper;

import com.mysql.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int insertStu(Student student);
    List<Student> selectById1(Integer classId);

    int deleteById(Integer id);
    //根据班级查询学生是否存在
    boolean isExistById(Integer ClassId);

    int updateById(@Param("name") String name, @Param("sn")String sn, @Param("sex")String sex, @Param("dept")String dept, @Param("classId")Integer classId, @Param("address")String address, @Param("id")Integer id);
}
