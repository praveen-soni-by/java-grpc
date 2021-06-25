package com.syscho.grpc.versioncompetability;

import com.syscho.grpc.stub.Laptop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionMainV3 {

    public static final String BRAND = "HP";
    public static final String MANUFACTURE = "HP";
    public static final String MODEL_NUMBER = "HP-001";
    public static final int YEAR = 2020;

    public static void main(String[] args) throws IOException {

        //V2 Object
        //writeInFile();

        Path pathV1 = Paths.get("src/main/file/laptop.v2");
        Laptop parseLaptopV3 = Laptop.parseFrom(Files.readAllBytes(pathV1));
        //Serialization using v1 to DeSerialization to v3
        System.out.println(parseLaptopV3);


        Path pathV2 = Paths.get("src/main/file/laptop.v2");
        parseLaptopV3 = Laptop.parseFrom(Files.readAllBytes(pathV2));
        //Serialization using v2 to DeSerialization to v3
        System.out.println(parseLaptopV3);

    }

    private static void writeInFile() throws IOException {
        Laptop laptop = Laptop.newBuilder()
                .setBrand(BRAND)
                .setModelNumber(MODEL_NUMBER)
                .setYear(YEAR)
                .build();

        Path pathV1 = Paths.get("src/main/file/laptop.v3");
        Path path = Files.write(pathV1, laptop.toByteArray());
    }
}
