
package com.syscho.grpc.interceptor;

import io.grpc.*;

import java.util.Objects;

import static com.syscho.grpc.interceptor.ServerConstants.CTX_USER_ROLE;

public class JwtAuthInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT>
    interceptCall(ServerCall<ReqT, RespT> serverCall,
                  Metadata metadata, ServerCallHandler<ReqT, RespT>
                          serverCallHandler) {
        String token = metadata.get(ServerConstants.USER_TOKEN);
        System.out.println("token :" + token);
        if (validate(token)) {
            Role role = this.extractRole(token);
            Context context = Context.current().withValue(CTX_USER_ROLE, role);
            return Contexts.interceptCall(context, serverCall, metadata, serverCallHandler);
        } else {
            Status status = Status.UNAUTHENTICATED.withDescription("Invalid token ");
            serverCall.close(status, metadata);
        }
        return new ServerCall.Listener<ReqT>() {
        };
    }

    public boolean validate(String token) {

        return Objects.nonNull(token) && (
                token.equals("jwt-token-3-admin") ||
                        token.equals("jwt-token-1-user"))
                ;
    }

    public Role extractRole(String token) {


        return token.endsWith("admin") ? Role.ADMIN : Role.USER;
    }

}