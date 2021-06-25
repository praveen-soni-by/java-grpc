package com.syscho.grpc.interceptor;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;

import java.util.concurrent.Executor;

public class UserSessionToken extends CallCredentials {

    private String jwt;

    public UserSessionToken(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
        executor.execute(() -> {
            try {
                Metadata metadata = new Metadata();
                metadata.put(ClientConstants.USER_TOKEN, this.jwt);
                metadataApplier.apply(metadata);
            } catch (Exception ex) {
                metadataApplier.fail(Status.UNAUTHENTICATED.withDescription("No Token Found"));
            }
        });

    }

    @Override
    public void thisUsesUnstableApi() {

    }
}
