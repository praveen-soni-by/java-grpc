package com.syscho.grpc.service;

import com.syscho.grpc.interceptor.Role;
import com.syscho.grpc.interceptor.ServerConstants;
import com.syscho.grpc.stub.LoginRequest;
import com.syscho.grpc.stub.LoginResponse;
import com.syscho.grpc.stub.LoginServiceGrpc;
import com.syscho.grpc.stub.Status;
import io.grpc.stub.StreamObserver;

public class LoginService extends LoginServiceGrpc.LoginServiceImplBase {
    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {

        Role role = ServerConstants.CTX_USER_ROLE.get();
        System.out.println(role);

        responseObserver.onNext(LoginResponse.newBuilder()
                .setUserName(Role.USER.name() + " " + request.getUserName())
                .setStatus(Status.SUCCESS).build());
        responseObserver.onCompleted();
    }
}
