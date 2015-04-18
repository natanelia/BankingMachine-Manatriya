package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Transfer extends Transaction {
    private String bankName;
    private String accountID;
    private int duration;

    public Transfer(Account _acc, String _bankName, String _accountID, int _duration, int _amount) {
        super(_acc, _amount);
        bankName = _bankName;
        accountID = _accountID;
        duration = _duration;
        transactionName = this.getName();
        setDescription("TO: " + getBankName() + "|" + "ID: " + getAccountID());
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountID() {
        return accountID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int _duration) {
        duration = _duration;
    }

    @Override
    public synchronized void run() {
        try {
            sleep(getDuration());
            acc.updateSaldo(-amount);

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


}
