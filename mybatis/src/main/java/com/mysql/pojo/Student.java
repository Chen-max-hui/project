package com.mysql.pojo;

public class Student {
    public Student(Integer id, String name, String sn, String sex, String dept, Integer classId, String address) {
        this.id = id;
        this.name = name;
        this.sn = sn;
        this.sex = sex;
        this.dept = dept;
        this.classId = classId;
        this.address = address;
    }

    SchoolClass schoolClass;

    @Override
    public String toString() {
        return "Student{" +
                "schoolClass=" + schoolClass +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", sex='" + sex + '\'' +
                ", dept='" + dept + '\'' +
                ", classId=" + classId +
                ", address='" + address + '\'' +
                '}';
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student(SchoolClass schoolClass, Integer id, String name, String sn, String sex, String dept, Integer classId, String address) {
        this.schoolClass = schoolClass;
        this.id = id;
        this.name = name;
        this.sn = sn;
        this.sex = sex;
        this.dept = dept;
        this.classId = classId;
        this.address = address;
    }

    Integer id;
    String name;
    String sn;
    String sex;
    String dept;
    Integer classId;
    String address;

    public Student() {
    }
}
