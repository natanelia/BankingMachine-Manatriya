package edu.manatriya.banking.akunbanking;

/**
 * Created by KEVIN on 14/04/2015.
 */

public class CreditAccount extends Account {

    public CreditAccount(String accountFileName) {
        super(accountFileName);
    }

    public CreditAccount(CreditAccount c) {
        super(c);
    }

    public CreditAccount(String accountID, String password) {
        super(accountID, password);
    }

    public void updateSaldo(double amount){
        double tempSaldo = getSaldo() + amount;
        setSaldo(tempSaldo);
    }
}