package com.syscho.grpc.server;

import com.syscho.grpc.exception.ExceptionHandler;
import com.syscho.grpc.service.ProductAdaptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GRPCServer {

    public static final int PORT = 9091;

    public static void main(String args[]) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(PORT)
                .addService(new ProductAdaptor())
                .intercept(new ExceptionHandler())
                .build();

        server.start();

        System.out.println("Server started on " + server.getPort());

        server.awaitTermination();

    }
}