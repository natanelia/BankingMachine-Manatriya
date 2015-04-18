package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class ReceiveTransfer extends Transaction {
    private String bankName;
    private String accountID;

    public ReceiveTransfer(Account _acc, String _bankName, String _accountID, int _amount) {
        super(_acc, _amount);
        bankName = _bankName;
        accountID = _accountID;
        transactionName = this.getName();
        setDescription("FROM: " + getBankName() + "|" + "ID: " + getAccountID());
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountID() {
        return accountID;
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(amount);
    }


}
