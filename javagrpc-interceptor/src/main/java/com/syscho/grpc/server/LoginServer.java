package com.syscho.grpc.server;

import com.syscho.grpc.interceptor.AuthInterceptor;
import com.syscho.grpc.interceptor.JwtAuthInterceptor;
import com.syscho.grpc.service.LoginService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class LoginServer {
    public static final int PORT = 6060;

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(PORT)
                .addService(new LoginService())
                .directExecutor()
                .intercept(new JwtAuthInterceptor())
                .build();

        server.start();

        System.out.println("Server started on " + server.getPort());

        server.awaitTermination();
    }
}
