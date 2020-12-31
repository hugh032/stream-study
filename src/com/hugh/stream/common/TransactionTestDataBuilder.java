package com.hugh.stream.common;

import com.hugh.stream.model.Trader;
import com.hugh.stream.model.Transaction;

import java.util.Arrays;
import java.util.List;

public class TransactionTestDataBuilder {
    public static List<Transaction> buildData() {
        Trader raoul = new Trader("Raoul", "Cambridge",1);
        Trader mario = new Trader("Mario", "Milan",1);
        Trader alan = new Trader("Alan", "Cambridge",1);
        Trader brian = new Trader("Brian", "Cambridge",1);
        Trader anna = new Trader("Anna", "London",0);
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950),
                new Transaction(anna, 2020, 1200)
        );
        return transactions;
    }
}
