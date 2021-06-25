package com.syscho.grpc.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syscho.grpc.pojo.UserPojo;
import com.syscho.grpc.stub.UserOuterClass;

public class PerformanceTest {

    public static void main(String[] args) {
        final String emailId = "test@email.com";
        final String name = "admin";
        final int userId = 1000;

        UserPojo userPojo = new UserPojo(userId, emailId, name);

        UserOuterClass.User user = UserOuterClass.User.newBuilder().setEmailId(emailId).setUserId(userId).setUserName(name).build();
        ObjectMapper objectMapper = new ObjectMapper();

        Runnable userPojoRunnable = () -> {
            try {
                byte[] bytes = objectMapper.writeValueAsBytes(userPojo);
                UserPojo userPojo1 = objectMapper.readValue(bytes, UserPojo.class);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };


        Runnable userProtobfRunnable = () -> {
            try {
                byte[] bytes = user.toByteArray();
                UserOuterClass.User user1 = UserOuterClass.User.parseFrom(bytes);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        performance(userPojoRunnable, "JSON");
        performance(userProtobfRunnable, "Protobuf");

    }

    static void performance(Runnable runnable, String methodName) {

        long start = System.currentTimeMillis();

        for (long i = 0; i <= 100_00_00; i++) {
            runnable.run();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time Taken by : " + methodName + " " + (end - start) + " ms");
    }
}

