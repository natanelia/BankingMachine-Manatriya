package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountFactory;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Transaksi untuk melakukan pembukaan Account baru.
 * @author Natan
 */
public class OpenSavingAccount extends  Transaction{
    private String newAccountID;
    private String password;
    private String currency;
    private String name;

    /**
     * Konstruktor transaksi OpenSavingAccount, membuka tabungan debit baru
     * @param account Akun yang membuka tabungan debit baru
     * @param new_account_id ID akun yang baru
     * @param password Password akun baru
     * @param currency Mata uang akun baru
     * @param amount Banyak uang awal dari akun baru, ditransfer dari param account
     * @param name Nama pemilik akun baru
     */
    public OpenSavingAccount(Account account, String new_account_id, String password, String currency, double amount, String name){
        super(account, amount);
        this.newAccountID = "DE" + new_account_id;
        this.password = password;
        this.currency = currency;
        this.name = name;
        this.description = "NEWLY OPENED: " + this.newAccountID;

    }

    /**
     * Getter untuk ID akun baru
     * @return ID akun baru
     */
    public String getNewAccountID() {
        return newAccountID;
    }

    /**
     * Setter untuk ID akun baru
     * @param newAccountID ID akun pengganti
     */
    public void setNewAccountID(String newAccountID) {
        this.newAccountID = newAccountID;
    }

    /**
     * Getter untuk password akun baru
     * @return Password akun baru
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter untuk password akun baru
     * @param password Password pengganti
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter untuk mata uang akun baru
     * @return Mata uang akun baru
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Setter untuk mata uang akun baru
     * @param currency Mata uang pengganti
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Membuat akun baru di database akun berdasarkan informasi yang sudah diset sebelumnya
     */
    @Override
    public synchronized void run() {
        String filename = "out/Accounts/" + newAccountID + ".acc";
        File f = new File(filename);

        String oldCurrency = acc.getCurrency();
        if (!f.exists()) {
            //file created
            try {
                acc.changeCurrency(this.getCurrency());
                acc.updateSaldo(-amount);
                if (f.createNewFile()) {
                    PrintWriter pw = new PrintWriter(f);

                    //mengisi data akun
                    pw.println(password);
                    pw.println(name);
                    pw.println(currency);
                    pw.println(0);
                    pw.println("DATE||TYPE||DESCRIPTION||AMOUNT");

                    pw.flush();
                    pw.close();
                    try {
                        AccountFactory accountFactory = new AccountFactory();
                        Account destAccount = accountFactory.getAccount("out/Accounts/" + getNewAccountID() + ".acc");
                        ReceiveTransfer receiveTransfer = new ReceiveTransfer(destAccount,acc.getAccountID(),amount);
                        receiveTransfer.start();
                        receiveTransfer.join();
                        destAccount.saveAccount();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    addToAccount();
                }
                JOptionPane.showMessageDialog(null,"Your new account has been created.");
            } catch (FileNotFoundException e) {
                /* do nothing */
            } catch (IOException e) {
                final String errMsg = "Account can't be saved.";
                System.err.println(errMsg);
                JOptionPane.showMessageDialog(null,errMsg,"",JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    acc.changeCurrency(oldCurrency);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            final String errMsg = "Account is already registered.";
            System.err.println(errMsg);
            JOptionPane.showMessageDialog(null,errMsg,"",JOptionPane.ERROR_MESSAGE);
        }
    }
}
