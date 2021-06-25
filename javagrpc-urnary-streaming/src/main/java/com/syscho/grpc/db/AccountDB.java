
package com.syscho.grpc.db;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountDB {


    public static final Map<Integer, Integer> accountDB =
            IntStream.rangeClosed(1, 10).boxed()
                    .collect(Collectors.toMap(Function.identity(), (v) -> v * 10));


    public static int getBalanceById(int id) {
        return accountDB.getOrDefault(id, 0);
    }


    public static int depositAmount(int id, int amount) {
        if (!isAccountExist(id)) {
            return 0;
        }
        accountDB.computeIfPresent(id, (key, value) -> value + amount);

        return getBalanceById(id);
    }

    public static int deductBalance(int id, int amount) {
        if (!isAccountExist(id)) {
            return 0;
        }
        accountDB.computeIfPresent(id, (key, value) -> value - amount);
        return getBalanceById(id);
    }


    public static void transferAmount(int fromAccountNumber, int toAccountNumber, int amount) {
        accountDB.computeIfPresent(fromAccountNumber, (key, value) -> value - amount);
        accountDB.computeIfPresent(toAccountNumber, (key, value) -> value + amount);
    }

    private static boolean isAccountExist(int accountNumber) {
        return accountDB.containsKey(accountNumber);
    }


    public static void printAccountDetails() {
        System.out.println(accountDB);
    }
}
