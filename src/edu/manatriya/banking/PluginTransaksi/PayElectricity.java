package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayElectricity extends Transaction {
    private String elecID;

    public PayElectricity(Account _acc, String _elecID, int _amount) {
        super(_acc, _amount);
        elecID = _elecID;
        transactionName = this.getName();
    }

    public String getElecID() {
        return elecID;
    }


    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
    }
}
