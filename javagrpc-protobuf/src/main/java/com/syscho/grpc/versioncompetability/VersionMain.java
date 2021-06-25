package com.syscho.grpc.versioncompetability;

import com.syscho.grpc.stub.Laptop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionMain {

    public static final String BRAND = "HP";
    public static final String MANUFACTURE = "HP";
    public static final String MODEL_NUMBER = "HP-001";
    public static final int YEAR = 2020;

    public static void main(String[] args) throws IOException {


        Laptop laptop = Laptop.newBuilder()
                .setBrand(BRAND)
              //  .setManufacture(MANUFACTURE)
                .setModelNumber(MODEL_NUMBER)
                //.setYear(YEAR)
                .build();

        //Serialization using v1
        Path pathV1 = Paths.get("src/main/file/laptop.v1");
        Path path = Files.write(pathV1, laptop.toByteArray());

        Laptop parseLaptopV1 = Laptop.parseFrom(Files.readAllBytes(pathV1));
        System.out.println(parseLaptopV1);

    }
}
