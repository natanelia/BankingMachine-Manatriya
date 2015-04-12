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

    public getElecID() { return elecID; }
    public getAmount() { return amount; }
    public setAmount(int _amount) { amount = _amount; }

    public void run() {
        acc.updateSaldo(-amount);
    }
}
