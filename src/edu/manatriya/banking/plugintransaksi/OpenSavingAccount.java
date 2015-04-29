package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountFactory;
import org.w3c.dom.ranges.RangeException;

import javax.swing.*;
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
        this.newAccountID = "DE" + new_account_id;
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
            } catch (FileNotFoundException e) {
                /* do nothing */
            } catch (IOException e) {
                final String errMsg = "Account can't be saved.";
                System.err.println(errMsg);
                JOptionPane.showMessageDialog(null,errMsg,"",JOptionPane.ERROR_MESSAGE);
            } catch (RangeException e) {
                System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(null,e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Your balance is not sufficient for the transaction!");
            } finally {
                acc.changeCurrency(oldCurrency);
            }
        } else {
            final String errMsg = "Account is already registered.";
            System.err.println(errMsg);
            JOptionPane.showMessageDialog(null,errMsg,"",JOptionPane.ERROR_MESSAGE);
        }
    }
}
