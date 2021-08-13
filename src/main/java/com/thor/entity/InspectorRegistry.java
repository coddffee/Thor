package com.thor.entity;

import com.thor.type.Gender;
import com.thor.type.VerifyStatus;
import java.sql.Blob;
import java.sql.Date;

/**
 * inspectors registry
 */
public class InspectorRegistry {
    private Integer id;
    private String name;
    private String phone;
    private String password;
    private Gender gender;
    private Integer age;
    private Blob icon;
    private Integer postId;
    private Integer managerId;
    private Date date = new Date(0);
    private VerifyStatus status = VerifyStatus.VERIFYING;

    public InspectorRegistry() {
        super();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Blob getIcon() {
        return icon;
    }

    public void setIcon(Blob icon) {
        this.icon = icon;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public VerifyStatus getStatus() {
        return status;
    }

    public void setStatus(VerifyStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InspectorRegistry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", icon=" + icon +
                ", postId=" + postId +
                ", managerId=" + managerId +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
