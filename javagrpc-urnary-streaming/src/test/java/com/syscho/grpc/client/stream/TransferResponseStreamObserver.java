package com.syscho.grpc.client.stream;

import com.syscho.grpc.stub.AccountResponse;
import com.syscho.grpc.stub.AccountResponses;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class TransferResponseStreamObserver implements StreamObserver<AccountResponses> {
    CountDownLatch countDownLatch;

    public TransferResponseStreamObserver(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void onNext(AccountResponses accountResponse) {

        System.out.println("Status : "+ accountResponse.getStatus());
        accountResponse.getAccountResponsesList()
                .stream()
                .map(data -> data.getAccountNumber() +": "+data.getAmount())
                .forEach(System.out::println);
        System.out.println("-------------------------------------");
    }

    @Override
    public void onError(Throwable throwable) {
        countDownLatch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println(" All Transfer Done");
        countDownLatch.countDown();
    }
}
