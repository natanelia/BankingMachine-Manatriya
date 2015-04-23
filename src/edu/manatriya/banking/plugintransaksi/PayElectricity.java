package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayElectricity extends Transaction {
    private String electricityID;

    public PayElectricity(Account account, String electricity_id, double amount) {
        super( account, amount);
        electricityID = electricity_id;
        transactionName = this.getClass().getSimpleName();
    }

    public String getElectricityID() {
        return electricityID;
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
        addToAccount();
    }
}
