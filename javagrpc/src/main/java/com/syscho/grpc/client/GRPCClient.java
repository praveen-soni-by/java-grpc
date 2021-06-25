package com.syscho.grpc.client;

import com.syscho.grpc.stub.ProductOuterClass;
import com.syscho.grpc.stub.productGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class GRPCClient {
    public static final int PORT = 9091;

    public static void main(String[] args) {

        ManagedChannel channel =
                ManagedChannelBuilder.forAddress("localhost", PORT)
                        .intercept(new CustomClientInterceptor())
                        .usePlaintext().build();

        //stub from proto file

        productGrpc.productBlockingStub stub = productGrpc.newBlockingStub(channel);

        System.out.println("GRPC CLIENT STARTED");
        System.out.println("--------------------------------------------");
        printProducts(stub);
        saveProduct(stub);
        printProducts(stub);

        try {
            printProductById(stub, 2501);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

        try {
            printProductById(stub, 01201);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }


        try {
            deleteProductById(stub, 2501);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

        try {
            deleteProductById(stub, 00225);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
        printProducts(stub);


    }

    private static void saveProduct(productGrpc.productBlockingStub stub) {
        stub.saveProduct(buildProduct("Earphone",10001,ProductOuterClass.ProductType.ELECTRONIC));
        stub.saveProduct(buildProduct("Olive Oil",10002,ProductOuterClass.ProductType.HOUSE_HOLD));
        stub.saveProduct(buildProduct("Shirt",10003,ProductOuterClass.ProductType.CLOTHING));
        printLing("Product Added Successfully");


    }

    private static ProductOuterClass.Product buildProduct(String productName, int productId, ProductOuterClass.ProductType productType) {
        return ProductOuterClass.Product.newBuilder().setProductName(productName)
                .setProductType(productType).setProductId(productId).build();
    }

    private static void deleteProductById(productGrpc.productBlockingStub stub, int productId) {
        ProductOuterClass.StringMsg stringMsg = stub.deleteProductById(ProductOuterClass.ProductId.newBuilder().setProductId(productId).build());
        printLing("Deleting product by id  :" + stringMsg);
    }

    private static void printProductById(productGrpc.productBlockingStub stub, int productId) {
        ProductOuterClass.Product productById = stub.getProductById(ProductOuterClass.ProductId.newBuilder().setProductId(productId).build());
        printLing("Printing ProductById :" + productById);
    }

    private static void printProducts(productGrpc.productBlockingStub stub) {
        ProductOuterClass.Products products = stub.getProducts(ProductOuterClass.Empty.newBuilder().build());

        printLing("Printing Product List");
        products.getProductsList().forEach(System.out::println);
    }

    private static void printLing(String msg) {
        System.out.println("------------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------------");
    }

}