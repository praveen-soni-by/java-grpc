package com.syscho.grpc.service.stream;

import com.syscho.grpc.db.AccountDB;
import com.syscho.grpc.stub.AccountResponse;
import com.syscho.grpc.stub.DepositRequest;
import io.grpc.stub.StreamObserver;

public class DepositStreamingRequest implements StreamObserver<DepositRequest> {
    int balance;
    int accountNumber;
    StreamObserver<AccountResponse> accountResponseStreamObserver;

    public DepositStreamingRequest(StreamObserver<AccountResponse> accountResponseStreamObserver) {
        this.accountResponseStreamObserver = accountResponseStreamObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
        accountNumber = depositRequest.getAccountNumber();
        int amount = depositRequest.getAmount();
        this.balance = AccountDB.depositAmount(accountNumber, amount);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {

        AccountResponse accountResponse = AccountResponse.newBuilder()
                .setAccountNumber(accountNumber).setAmount(balance).build();
        this.accountResponseStreamObserver.onNext(accountResponse);
        this.accountResponseStreamObserver.onCompleted();
    }
}
