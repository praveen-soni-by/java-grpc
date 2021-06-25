package com.syscho.grpc.versioncompetability;

import com.syscho.grpc.stub.Laptop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionMainV2_V1 {

    public static final String BRAND = "HP";
    public static final String MANUFACTURE = "HP";
    public static final String MODEL_NUMBER = "HP-001";
    public static final int YEAR = 2020;

    public static void main(String[] args) throws IOException {

       //V2 Object
        Laptop laptop = Laptop.newBuilder()
                .setBrand(BRAND)
                .setModelNumber(MODEL_NUMBER)
             //   .setYear(YEAR)
                .build();

        //Serialization using v1  - V1 Serialized Object
      //  Path pathV1 = Paths.get("src/main/file/laptop.v2");
     //   Path path = Files.write(pathV1, laptop.toByteArray());

        Path pathV1 = Paths.get("src/main/file/laptop.v1");
        Laptop parseLaptopV2 = Laptop.parseFrom(Files.readAllBytes(pathV1));
        //Serialization using v1 to DeSerialization to v2
        System.out.println(parseLaptopV2);


    }
}
