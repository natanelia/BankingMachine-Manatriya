package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import java.io.*;

/**
 * Created by Marco Orlando on 4/23/2015.
 */
public class OpenSavingAccount extends  Transaction{
    private String newAccountID;
    private String password;
    private String currency;
    private String name;

    public OpenSavingAccount(Account account, String new_account_id, String password, String currency, double amount, String name){
        super(account, amount);
        this.newAccountID = new_account_id;
        this.password = password;
        this.currency = currency;
        this.name = name;

    }

    public String getNewAccountID() {
        return newAccountID;
    }

    public void setNewAccountID(String newAccountID) {
        this.newAccountID = newAccountID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public synchronized void run() {
        String filename = "out/Accounts/DE" + newAccountID + ".acc";
        File f = new File(filename);
        if (!f.exists()) {
            //file created
            try {
                if (f.createNewFile()) {
                    PrintWriter pw = new PrintWriter(f);

                    //mengisi data akun
                    pw.println(name);
                    pw.println(currency);
                    pw.println(amount);
                    pw.println("DATE||TYPE||DESCRIPTION||AMOUNT");

                    pw.flush();
                    pw.close();
                }
            } catch (FileNotFoundException e) {
                System.err.println("");
            } catch (IOException e) {
                System.err.println("Account can't be saved.");
            }

        } else {
            System.out.println("Account is already registered.");
        }
    }
}
