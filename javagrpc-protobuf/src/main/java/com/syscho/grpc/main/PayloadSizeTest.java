package com.syscho.grpc.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syscho.grpc.pojo.Address;
import com.syscho.grpc.pojo.UserPojo;
import com.syscho.grpc.stub.AddressOuterClass;
import com.syscho.grpc.stub.UserOuterClass;

import java.util.ArrayList;
import java.util.List;

public class PayloadSizeTest {

    public static final String ADDRESS_1 = "address1";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final int PIN_CODE = 101;
    public static final String ADDRESS_2 = "0";
    public static final String emailId = "test@email.com";
    public static final String name = "admin";
    public static final int userId = 1000;
    static long protobufLenght = 0;
    static long jsonLength = 0;

    public static void main(String[] args) throws Exception {


        jsonMethod(emailId, name, userId);
        protobufMethod(emailId, name, userId);
        System.out.println("JSON bytes are  " + (jsonLength - protobufLenght) + "  bytes  greater then protobuf ");
    }

    private static void protobufMethod(String emailId, String name, int userId) throws Exception {

        UserOuterClass.User.Builder userBuilder =
                UserOuterClass.User.newBuilder().setUserId(userId).setEmailId(emailId).setUserName(name);


        for (int i = 0; i < 100_00; i++) {
            AddressOuterClass.Address address = AddressOuterClass.Address.newBuilder()
                    .setEmpId(1)
                    .setState(STATE).setCity(CITY).setPinCode(PIN_CODE).setAddress2(ADDRESS_2)
                    .setAddress1(ADDRESS_1).build();

            userBuilder.addAddress(address);

        }

        UserOuterClass.User userProtobuf = userBuilder.build();
        byte[] bytes = userProtobuf.toByteArray();
        protobufLenght = bytes.length;
        System.out.println("Protobuf bytes Size " + protobufLenght);
    }


    private static void jsonMethod(String emailId, String name, int userId) throws Exception {
        UserPojo userPojo = new UserPojo(userId, emailId, name);
        List<Address> addressList = new ArrayList<>();
        for (int i = 0; i < 100_00; i++) {

            addressList.add(new Address(1, ADDRESS_1,
                    ADDRESS_2, CITY, STATE, PIN_CODE));
        }
        userPojo.setAddress(addressList);
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(userPojo);
        jsonLength = bytes.length;
        System.out.println("JSON bytes Size " + jsonLength);

    }
}
