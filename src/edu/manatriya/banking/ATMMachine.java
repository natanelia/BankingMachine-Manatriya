package edu.manatriya.banking;

import edu.manatriya.banking.akunbanking.Account;

import edu.manatriya.banking.PengolahNonTransaksi.AccountSignIn;
import edu.manatriya.banking.PengolahNonTransaksi.AccountSignOut;
import edu.manatriya.banking.PengolahNonTransaksi.NonTransactionProcessor;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;
import edu.manatriya.banking.pengolahtransaksi.TransactionProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by KEVIN on 14/04/2015.
 */
public class ATMMachine {
            Account acc = null;

    public void run(){
        Scanner inputscanner = new Scanner(System.in);
        String inputanuser = inputscanner.nextLine();
        while(acc==null) {
            doCommand("AccountSignIn", inputanuser);
        }
        while (acc != null) {
            doCommand(inputanuser);
        }
    }

    private void doCommand(String cmd) {
        TransactionProcessor transactionprocessor = new TransactionProcessor();
        try{
            (new NonTransactionProcessor(cmd, acc)).process();
        }

        catch(InvalidParameterException parameterException){
                transactionprocessor.generateForm(cmd);
        }
        catch(Exception e){
            System.err.println("command not found");
        }

    }

    private void doCommand(String cmd, String accID) {
        AccountSignIn NTP = new AccountSignIn(accID);
        acc = NTP.getAccount();
    }
}
