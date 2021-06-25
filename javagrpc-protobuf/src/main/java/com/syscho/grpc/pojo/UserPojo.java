package com.syscho.grpc.pojo;

import java.util.List;

public class UserPojo {
    private int userId;
    private String userName;
    private String emailId;
    private List<Address> address;


    public UserPojo() {
    }

    public UserPojo(int userId, String userName, String emailId) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

}
