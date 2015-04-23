package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import java.util.Date;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public abstract class Transaction extends Thread {
    protected Account acc;
    protected String transactionName;
    protected long amount;
    protected String description;

    public Transaction(Account _acc, long _amount) {
        acc = _acc;
        amount = _amount;
        transactionName = this.getClass().getSimpleName();
        description = "";
    }

    public Account getAccount() {
        return acc;
    }

    public void setAccount(Account _account) {
        acc = _account;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long _amount) {
        amount = _amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    @Override
    public abstract void run();

    @Override
    public String toString() {
        return (new Date()).toString() + "||" + transactionName + "||" + getDescription() + "||" + getAmount();
    }

    protected void addToAccount(){
        //acc.addSuccessfulTransaction(this.toString());
    }
}
