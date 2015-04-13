package edu.manatriya.banking.PluginTransaksi;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayPhoneBill extends Transaction {
    private String phoneNumber;
    private String provider;
    private int amount;

    public PayPhoneBill(Account _acc, String _phoneNumber, String _provider, int _amount) {
        super(_acc);
        phoneNumber = _phoneNumber;
        provider = _provider;
        amount = _amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProvider() {
        return provider;
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
