package com.syscho.grpc;

import com.syscho.grpc.interceptor.ClientConstants;
import com.syscho.grpc.stub.LoginRequest;
import com.syscho.grpc.stub.LoginResponse;
import com.syscho.grpc.stub.LoginServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginClientTest {


    public static final String LOCALHOST = "localhost";
    public static final int PORT = 6060;
    LoginServiceGrpc.LoginServiceBlockingStub loginServiceBlockingStub;


    @Test
    public void testLoginWithToken_Interceptor() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(LOCALHOST, PORT)
                .intercept(MetadataUtils.newAttachHeadersInterceptor(ClientConstants.getClientToken()))
                .usePlaintext().build();
        //stub from proto file
        loginServiceBlockingStub = LoginServiceGrpc.newBlockingStub(managedChannel);

        LoginResponse login = loginServiceBlockingStub.login(LoginRequest.newBuilder().setPassword("password").setUserName("username").build());
        System.out.println(login);
    }

    @Test
    public void testLogin_WithOutInterceptor() {
        try {
            ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(LOCALHOST, PORT)
                    .usePlaintext().build();
            //stub from proto file
            loginServiceBlockingStub = LoginServiceGrpc.newBlockingStub(managedChannel);

            LoginResponse login = loginServiceBlockingStub.login(LoginRequest.newBuilder().setPassword("password").setUserName("username").build());
            System.out.println(login);
        } catch (StatusRuntimeException exception) {
            System.err.println(exception.getMessage());
        }
    }
}

