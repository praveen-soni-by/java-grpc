package com.syscho.grpc;

import com.syscho.grpc.interceptor.ClientConstants;
import com.syscho.grpc.interceptor.UserSessionToken;
import com.syscho.grpc.stub.LoginRequest;
import com.syscho.grpc.stub.LoginResponse;
import com.syscho.grpc.stub.LoginServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ThreadLocalRandom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginUserClientTest {


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

        for (int i = 1; i <= 20 ;i++) {
            try {
                int number = ThreadLocalRandom.current().nextInt(1, 4);
                LoginResponse login = loginServiceBlockingStub
                        .withCallCredentials(new UserSessionToken("jwt-token-" + number))
                        .login(LoginRequest.newBuilder().setPassword("password").setUserName("username").build());
                System.out.println(login);
            } catch (StatusRuntimeException exception) {
                System.err.println(exception.getMessage());
            }

        }

    }


    @Test
    public void testLoginWithToken_Interceptor_context() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(LOCALHOST, PORT)
                .intercept(MetadataUtils.newAttachHeadersInterceptor(ClientConstants.getClientToken()))
                .usePlaintext().build();
        //stub from proto file
        loginServiceBlockingStub = LoginServiceGrpc.newBlockingStub(managedChannel);

        for (int i = 1; i <= 20 ;i++) {
            try {
                int number = ThreadLocalRandom.current().nextInt(1, 4);
                LoginResponse login = loginServiceBlockingStub
                        .withCallCredentials(new UserSessionToken("jwt-token-" + number +"-admin"))
                        .login(LoginRequest.newBuilder().setPassword("password").setUserName("username").build());
                System.out.println(login);
            } catch (StatusRuntimeException exception) {
                System.err.println(exception.getMessage());
            }

        }

    }

}

