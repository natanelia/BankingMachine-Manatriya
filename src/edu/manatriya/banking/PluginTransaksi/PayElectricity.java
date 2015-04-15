package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayElectricity extends Transaction {
    private String elecID;
    private int amount;

    public PayElectricity(Account _acc, String _elecID, int _amount) {
        super(_acc);
        elecID = _elecID;
        amount = _amount;
    }

    public String getElecID() {
        return elecID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int _amount) {
        amount = _amount;
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
    }
}
