package com.shg.keyebang.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {
    public User(){

    }

    public User(String username, String password){
        setUsername(username);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getNickname() {
        return nickname;
    }


    public User setPhonenumber(String phonenumber){
        this.phonenumber=phonenumber;
        return this;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public User setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getSemester() {
        return semester;
    }

    public User setSemester(String semester) {
        this.semester = semester;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    private String name;
    private String phonenumber;
    private String nickname;
    private String studentId;
    private String semester;
    private String sex;



}
