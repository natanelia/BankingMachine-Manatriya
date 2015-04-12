package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Transfer extends Transaction {
    private String bankName;
    private String accountID;
    private int duration;
    private int amount;

    public Transfer(Account _acc, String _bankName, String _accountID, int _duration, int _amount) {
        super(_acc);
        bankName = _bankName;
        accountID = _accountID;
        duration = _duration;
        amount = _amount;
    }

    public String getBankName() {return bankName; }
    public String getAccountID() {return accountID; }

    public int getDuration() {return duration; }
    public void setDuration(int _duration) {duration = _duration;}

    public int getAmount() {return amount; }
    public void setAmount(int _amount) {amount = _amount; }

    public void run() {
        acc.updateSaldo(-amount);
    }
}
