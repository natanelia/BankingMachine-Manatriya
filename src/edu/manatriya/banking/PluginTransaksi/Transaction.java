package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public abstract class Transaction extends Thread {
    protected Account acc;

    public Transaction(Account _acc) {
        acc = _acc;
    }

    @Override
    public abstract void run();
}
