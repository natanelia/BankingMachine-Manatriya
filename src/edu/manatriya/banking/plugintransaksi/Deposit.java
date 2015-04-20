package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.plugintransaksi.Transaction;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Deposit extends Transaction {

    public Deposit(Account _acc, long _amount) {
        super(_acc, _amount);
        transactionName = this.getClass().getSimpleName();
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(getAmount());
        addToAccount();
    }
}
