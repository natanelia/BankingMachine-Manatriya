package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class ReceiveTransfer extends Transaction {
    private String sourceAccountID;

    public ReceiveTransfer(Account account, String source_account_id, double amount) {
        super(account, amount);
        this.sourceAccountID = source_account_id;
        transactionName = this.getClass().getSimpleName();
        setDescription("FROM ID: " + sourceAccountID);
    }

    public String getSourceAccountID() {
        return sourceAccountID;
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(amount);
        addToAccount();
    }


}
