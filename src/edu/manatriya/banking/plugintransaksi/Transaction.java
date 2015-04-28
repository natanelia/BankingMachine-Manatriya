package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public abstract class Transaction extends Thread {
    protected Account acc;
    protected String transactionName;
    protected double amount;
    protected String description;

    public Transaction(Account _acc, double _amount) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double _amount) {
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
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return df.format(new Date()) + "||" + transactionName + "||" + getDescription() + "||" + acc.getCurrency() + " " + getAmount();
    }

    protected void addToAccount(){
        acc.addTransactionToHistory(this.toString());
    }
}
