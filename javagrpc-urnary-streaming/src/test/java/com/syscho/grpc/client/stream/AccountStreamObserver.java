package com.syscho.grpc.client.stream;

import com.syscho.grpc.stub.AccountResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class AccountStreamObserver implements StreamObserver<AccountResponse> {
    CountDownLatch countDownLatch;
    int finalAmount = 0;

    public AccountStreamObserver(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void onNext(AccountResponse accountResponse) {
        finalAmount = finalAmount + accountResponse.getAmount();
        System.out.println(String.format("AccountNumber : %d   Amount : %d ",
                accountResponse.getAccountNumber(), accountResponse.getAmount()));

    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage());
        countDownLatch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("finalAmount :" + finalAmount);
        countDownLatch.countDown();
    }
}
