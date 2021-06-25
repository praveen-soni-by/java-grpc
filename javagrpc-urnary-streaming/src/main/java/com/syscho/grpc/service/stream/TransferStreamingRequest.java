package com.syscho.grpc.service.stream;

import com.syscho.grpc.db.AccountDB;
import com.syscho.grpc.stub.AccountResponse;
import com.syscho.grpc.stub.AccountResponses;
import com.syscho.grpc.stub.Status;
import com.syscho.grpc.stub.TransferRequest;
import io.grpc.stub.StreamObserver;

public class TransferStreamingRequest implements StreamObserver<TransferRequest> {
    private StreamObserver<AccountResponses> accountResponsesStreamObserver;

    public TransferStreamingRequest(StreamObserver<AccountResponses> accountResponsesStreamObserver) {
        this.accountResponsesStreamObserver = accountResponsesStreamObserver;
    }

    @Override
    public void onNext(TransferRequest transferRequest) {

        int amount = transferRequest.getAmount();
        int fromAccountNumber = transferRequest.getFromAccountNumber();
        int toAccountNumber = transferRequest.getToAccountNumber();
        int balance = AccountDB.getBalanceById(fromAccountNumber);
        Status status = Status.FAILED;
        if (balance > amount && fromAccountNumber != toAccountNumber) {
            AccountDB.transferAmount(fromAccountNumber, toAccountNumber, amount);
            status = Status.SUCCESS;
        }
        AccountResponses accountResponses = AccountResponses.newBuilder()
                .setStatus(status)
                .addAccountResponses(buildAccountResponse(fromAccountNumber))
                .addAccountResponses(buildAccountResponse(toAccountNumber)).build();

        this.accountResponsesStreamObserver.onNext(accountResponses);
    }

    private AccountResponse buildAccountResponse(int fromAccountNumber) {
        return AccountResponse.newBuilder().setAccountNumber(fromAccountNumber)
                .setAmount(AccountDB.getBalanceById(fromAccountNumber)).build();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        AccountDB.printAccountDetails();
        this.accountResponsesStreamObserver.onCompleted();
    }
}
