package com.syscho.grpc.interceptor;

import io.grpc.Context;
import io.grpc.Metadata;

import java.net.ConnectException;

public class ServerConstants {

    public static Metadata.Key<String> TOKEN = Metadata.Key.of("client-token",
            Metadata.ASCII_STRING_MARSHALLER);
    public static Metadata.Key<String> USER_TOKEN = Metadata.Key.of("jwt-token",
            Metadata.ASCII_STRING_MARSHALLER);


    public static Context.Key<Role> CTX_USER_ROLE = Context.key("user-role");

}
