package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;

/**
 * Created by Natan on 4/23/2015.
 */
public class ConvertAccountCurrency extends  Transaction {
    private String newCurrency;

    /**
     * Konstruktor transaksi ConvertAccountCurrency
     *
     * @param account Akun yang ingin diubah mata uangnya
     * @param new_currency Mata uang pengganti dalam tiga huruf e.g IDR, EUR, USD
     */
    public ConvertAccountCurrency(Account account, String new_currency) {
        super(account, 0);
        newCurrency = new_currency;
        description = "CONVERTED TO: " + newCurrency;
        transactionName = this.getClass().getSimpleName();
    }

    /**
     * Getter mata uang pengganti
     *
     * @return Mata uang pengganti
     */
    public String getNewCurrency() {
        return newCurrency;
    }

    /**
     * Mengubah variabel mata uang pengganti
     * @param newCurrency mata uang dalam tiga huruf sebagai pengganti
     */
    public void setNewCurrency(String newCurrency) {
        this.newCurrency = newCurrency;
    }

    @Override
    /**
     * Menjalankan proses pengubahan mata uang lama ke mata uang pengganti
     */
    public synchronized void run() {
        try {
            acc.changeCurrency(newCurrency);
            addToAccount();
            JOptionPane.showMessageDialog(null, "Your account currency has been changed.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
