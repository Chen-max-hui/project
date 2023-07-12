package com.mysql.pojo;

public class SchoolClass {
    Integer id;

    public SchoolClass(Integer id, String className, String classDesc) {
        this.id = id;
        this.className = className;
        this.classDesc = classDesc;
    }

    String className;
    String classDesc;

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", classDesc='" + classDesc + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public SchoolClass() {
    }
}
