package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Withdraw extends Transaction {
    private int amount;

    public Withdraw(Account _acc, int _amount) {
        super(_acc);
        amount = _amount;
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
