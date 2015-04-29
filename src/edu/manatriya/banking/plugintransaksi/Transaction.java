package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public abstract class Transaction extends Thread {
    protected Account acc;
    protected String transactionName;
    protected double amount;
    protected String description;

    /**
     * Konstruktor Transaction, ancestor dari semua transaksi
     * @param _acc Akun yang dikenai transaksi
     * @param _amount Banyak uang yang digunakan dalam transaksi
     */
    public Transaction(Account _acc, double _amount) {
        acc = _acc;
        amount = _amount;
        transactionName = this.getClass().getSimpleName();
        description = "";
    }

    /**
     * Getter untuk Account, yaitu akun yang dikenai transaksi
     * @return Akun yang dikenai transaksi
     */
    public Account getAccount() {
        return acc;
    }

    /**
     * Setter untuk Account, yaitu akun yang dikenai transaksi
     * @param _account Objek Account pengganti
     */
    public void setAccount(Account _account) {
        acc = _account;
    }

    /**
     * Getter untuk banyak uang yang digunakan transaksi
     * @return Banyak uang yang digunakan transaksi
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter untuk banyak uang yang digunakan untuk transaksi
     * @param _amount Banyak uang yang menggantikan
     */
    public void setAmount(double _amount) {
        amount = _amount;
    }

    /**
     * Getter untuk deskripsi dari transaksi
     * @return Deskripsi dari transaksi
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter untuk deskripsi dari transaksi
     * @param newDescription Deskripsi pengganti
     */
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    @Override
    public abstract void run();

    @Override
    /**
     * Melakukan formatting secara khusus untuk pengubahan Transaction ke String
     */
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return df.format(new Date()) + "||" + transactionName + "||" + getDescription() + "||" + acc.getCurrency() + " " + String.format("%.2f", getAmount());
    }

    /**
     * Melakukan penambahan transaksi ke sejarah transaksi akun
     */
    protected void addToAccount(){
        acc.addTransactionToHistory(this.toString());
    }
}
