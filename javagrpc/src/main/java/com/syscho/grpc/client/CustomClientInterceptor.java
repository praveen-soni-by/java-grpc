package com.syscho.grpc.client;

import io.grpc.*;

import java.util.concurrent.TimeUnit;

public class CustomClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT>
                                                                       methodDescriptor,
                                                               CallOptions callOptions,
                                                               Channel channel) {
        return channel.newCall(methodDescriptor, callOptions.withDeadline(Deadline.after(4, TimeUnit.SECONDS)));
    }
}
