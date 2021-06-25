
package com.syscho.grpc.interceptor;

import io.grpc.*;

import java.util.Objects;

public class AuthInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT>
    interceptCall(ServerCall<ReqT, RespT> serverCall,
                  Metadata metadata, ServerCallHandler<ReqT, RespT>
                          serverCallHandler) {
        String token = metadata.get(ServerConstants.TOKEN);
        System.out.println("token :"+token);
        if (validate(token)) {
            return serverCallHandler.startCall(serverCall, metadata);
        } else {
            Status status = Status.UNAUTHENTICATED.withDescription("Invalid token ");
            serverCall.close(status, metadata);
        }
        return new ServerCall.Listener<ReqT>() {
        };
    }

    public boolean validate(String token) {

        return Objects.nonNull(token) && token.equals("secret");
    }


}