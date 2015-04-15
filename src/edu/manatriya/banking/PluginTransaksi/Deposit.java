package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Deposit extends Transaction {

    public Deposit(Account _acc, int _amount) {
        super(_acc, _amount);
        transactionName = this.getName();
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(amount);
    }
}
