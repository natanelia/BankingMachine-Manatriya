package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayPhoneBill extends Transaction {
    private String phoneNumber;
    private String provider;

    public PayPhoneBill(Account account, String phone_number, String provider, int amount) {
        super(account, amount);
        this.phoneNumber = phone_number;
        this.provider = provider;
        this.transactionName = this.getClass().getSimpleName();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProvider() {
        return provider;
    }


    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
        addToAccount();
    }
}
