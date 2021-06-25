package com.syscho.grpc.interceptor;

import io.grpc.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MetaClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        Deadline deadline = callOptions.getDeadline();
        if (Objects.isNull(deadline)) {
            callOptions.withDeadline(Deadline.after(4, TimeUnit.SECONDS));
        }

        return channel.newCall(methodDescriptor, callOptions);
    }
}
