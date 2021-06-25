package com.syscho.grpc.interceptor;

import io.grpc.Metadata;

public class ClientConstants {

    public static Metadata META_DATA = new Metadata();

    public static Metadata.Key<String> USER_TOKEN = Metadata.Key.of("jwt-token",
            Metadata.ASCII_STRING_MARSHALLER);
    static {
        META_DATA.put(Metadata.Key.of("client-token", Metadata.ASCII_STRING_MARSHALLER), "secret");
    }

    public static Metadata getClientToken() {
        return META_DATA;
    }
}
