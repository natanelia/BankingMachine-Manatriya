package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayElectricity extends Transaction {
    private String elecID;

    public PayElectricity(Account _acc, String _elecID, int _amount) {
        super( _acc, _amount);
        elecID = _elecID;
        transactionName = this.getClass().getSimpleName();
    }

    public String getElecID() {
        return elecID;
    }


    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
        addToAccount();
    }
}
