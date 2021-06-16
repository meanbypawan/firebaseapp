package com.example.testfirebaseapp;

import java.io.Serializable;

public class User implements Serializable {
  private String id;
  private String name;
  private String mobile;
  private int age;

    public User(String id, String name, String mobile, int age) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.age = age;
    }
    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
