package com.syscho.grpc.client;

import com.syscho.grpc.client.stream.TransferResponseStreamObserver;
import com.syscho.grpc.client.stream.AccountStreamObserver;
import com.syscho.grpc.stub.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceClientTest {

    AccountServiceGrpc.AccountServiceBlockingStub accountServiceBlockingStub;
    AccountServiceGrpc.AccountServiceStub accountServiceStub;

    @BeforeAll
    public void setup() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6060).usePlaintext().build();
        //stub from proto file
        accountServiceBlockingStub = AccountServiceGrpc.newBlockingStub(channel);
        accountServiceStub = AccountServiceGrpc.newStub(channel);
    }


    //Unary
    @Test
    @DisplayName("Unary :Test will get the balance details for account number 1")
    public void testShouldGetBalanceDetails() {
        AccountResponse balance = accountServiceBlockingStub.getBalance(BalanceRequest.newBuilder().setAccountNumber(1).build());
        System.out.println(balance);
    }

    @Test
    @DisplayName("Test will get the balance details for account number 1231 ")
    public void testShouldGetBalanceDetailsAsZero() {
        AccountResponse balance = accountServiceBlockingStub.getBalance(
                BalanceRequest.newBuilder().setAccountNumber(1231).build());
        System.out.println(balance);
    }

    //Unary  to Stream (Server stream)
    @Test
    @DisplayName(" Unary  to Stream  : Test should withdraw amount")
    public void testShouldWithDrawAmount() {
        Iterator<AccountResponse> accountResponseIterator = accountServiceBlockingStub.withDrawAmount(WithDrawRequest.newBuilder().setAmount(13).setAccountNumber(8).build());
        accountResponseIterator.forEachRemaining(accountResponse -> {
            System.out.println(String.format("AccountNumber : %d  withdrawn Amount : %d ", accountResponse.getAccountNumber(), accountResponse.getAmount()));
        });
    }

    @Test
    @DisplayName("Async -Test should withdraw amount")
    public void testShouldWithDrawAmountAsync() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        WithDrawRequest withDrawRequest = WithDrawRequest.newBuilder().setAmount(30)
                .setAccountNumber(10).build();
        accountServiceStub.withDrawAmount(
                withDrawRequest, new AccountStreamObserver(countDownLatch));
        countDownLatch.await();

    }


    //Stream to unary (client stream)
    @Test
    @DisplayName("Stream to unary - Test should Deposit amount")
    public void testShouldDepositAmountStreaming() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StreamObserver<DepositRequest> depositRequestStreamObserver =
                accountServiceStub.deposit(new AccountStreamObserver(countDownLatch));
        for (int i = 0; i < 5; i++) {
            DepositRequest depositRequest = DepositRequest.newBuilder().setAmount(10)
                    .setAccountNumber(4).build();

            depositRequestStreamObserver.onNext(depositRequest);
        }

        depositRequestStreamObserver.onCompleted();
        countDownLatch.await();
    }

    //Stream to unary (client stream)
    @Test
    @DisplayName("Stream to Stream - Test should Transfer amount bi directional")
    public void testShouldTransferAmount() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StreamObserver<TransferRequest> transferRequestStreamObserver = accountServiceStub.transferAmount(new TransferResponseStreamObserver(countDownLatch));
        for (int i = 0; i <= 5; i++) {
            TransferRequest transferRequest =
                    TransferRequest.newBuilder().
                            setAmount(ThreadLocalRandom.current().nextInt(10, 110))
                            .setFromAccountNumber(ThreadLocalRandom.current().nextInt(1, 11))
                            .setToAccountNumber(ThreadLocalRandom.current().nextInt(1, 11))
                            .build();
            transferRequestStreamObserver.onNext(transferRequest);
        }
        transferRequestStreamObserver.onCompleted();
        countDownLatch.await();
    }

}

