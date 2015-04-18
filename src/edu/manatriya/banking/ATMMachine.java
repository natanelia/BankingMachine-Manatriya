package edu.manatriya.banking;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.PengolahNonTransaksi.AccountSignIn;
import edu.manatriya.banking.PengolahNonTransaksi.NonTransactionProcessor;
import edu.manatriya.banking.pengolahtransaksi.TransactionProcessor;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Created by KEVIN on 14/04/2015.
 */
public class ATMMachine {
    Account acc;
    TransactionProcessor transactionProcessor;

    public ATMMachine() {
        acc = null;
        transactionProcessor = new TransactionProcessor();
    }

    public void run(){
        Scanner inputScanner = new Scanner(System.in);
        while(acc==null) {
            String accountID = inputScanner.nextLine();
            doCommand("AccountSignIn", accountID);
        }
        while (acc != null) {
            doCommand(inputScanner.nextLine());
        }
    }

    private void doCommand(String cmd) {
        try {
            NonTransactionProcessor cmdProcessor = new NonTransactionProcessor(cmd,acc);
            cmdProcessor.process();
            acc = cmdProcessor.getAccount();
        }
        catch (Exception e) {
            try {
                transactionProcessor.generateForm(cmd);
                transactionProcessor.startAll();
            } catch (Exception ex) {
                System.err.println("Command not found!");
            }
        }
    }

    private void doCommand(String cmd, String accID) {
        AccountSignIn NTP = new AccountSignIn(accID);
        NTP.execute();
        acc = NTP.getAccount();
    }
}
