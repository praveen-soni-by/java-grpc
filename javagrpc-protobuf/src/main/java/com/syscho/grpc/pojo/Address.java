package com.syscho.grpc.pojo;

public class Address {
    int empId;
    String address1;
    String address2;
    String city;
    String state;
    int pinCode;

    public Address(int empId, String address1, String address2, String city, String state, int pinCode) {
        this.empId = empId;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public Address() {
    }
}
