
package com.syscho.grpc.service;

import com.syscho.grpc.db.AccountDB;
import com.syscho.grpc.service.stream.DepositStreamingRequest;
import com.syscho.grpc.service.stream.TransferStreamingRequest;
import com.syscho.grpc.stub.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class AccountService extends AccountServiceGrpc.AccountServiceImplBase {

    @Override
    public void getBalance(BalanceRequest request, StreamObserver<AccountResponse> responseObserver) {
        int accountNumber = request.getAccountNumber();
        int amount = AccountDB.getBalanceById(accountNumber);
        AccountResponse accountResponse = AccountResponse.newBuilder().setAccountNumber(accountNumber).setAmount(amount).build();
        responseObserver.onNext(accountResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void withDrawAmount(WithDrawRequest request, StreamObserver<AccountResponse> responseObserver) {
        int accountNumber = request.getAccountNumber();
        int amount = request.getAmount();
        Integer balance = AccountDB.getBalanceById(accountNumber);
        System.out.println(balance);
        if (null == balance || balance < amount) {
            Status status = Status.FAILED_PRECONDITION.withDescription("No Enough balance ");
            responseObserver.onError(status.asRuntimeException());
            return;
        }
        //10 -20 - 03 -40
        for (int i = 0; i < (balance / 10); i++) {
            AccountResponse accountResponse = AccountResponse.newBuilder()
                    .setAccountNumber(accountNumber).setAmount(10).build();
            responseObserver.onNext(accountResponse);
            AccountDB.deductBalance(accountNumber, 10);
            try {
                Thread.sleep(1000);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> deposit(StreamObserver<AccountResponse> responseObserver) {
        return new DepositStreamingRequest(responseObserver);
    }

    @Override
    public StreamObserver<TransferRequest> transferAmount(StreamObserver<AccountResponses> responseObserver) {
        return new TransferStreamingRequest(responseObserver);
    }
}


