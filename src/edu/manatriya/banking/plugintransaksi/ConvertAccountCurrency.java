package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Marco Orlando on 4/23/2015.
 */
public class ConvertAccountCurrency extends  Transaction {
    private String newCurrency;

    public ConvertAccountCurrency(Account account, String new_currency) {
        super(account, 0);
        newCurrency = new_currency;
        description = "CONVERTED TO: " + newCurrency;
        transactionName = this.getClass().getSimpleName();
    }

    public String getNewCurrency() {
        return newCurrency;
    }

    public void setNewCurrency(String newCurrency) {
        this.newCurrency = newCurrency;
    }

    @Override
    public synchronized void run() {
        acc.changeCurrency(newCurrency);
        addToAccount();
    }
}
