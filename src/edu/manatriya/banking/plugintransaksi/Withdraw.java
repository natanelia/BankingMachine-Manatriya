package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Withdraw extends Transaction {

    public Withdraw(Account _acc, int _amount) {
        super(_acc, _amount);
        transactionName = this.getName();
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
    }
}
