package edu.manatriya.banking;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.PengolahNonTransaksi.AccountSignIn;
import edu.manatriya.banking.PengolahNonTransaksi.NonTransactionProcessor;
import edu.manatriya.banking.akunbanking.DebitAccount;
import edu.manatriya.banking.pengolahtransaksi.TransactionProcessor;
import edu.manatriya.banking.plugintransaksi.MainForm;
import edu.manatriya.banking.plugintransaksi.mainMenuForm;

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
    }

    public void run() throws InterruptedException {
        Scanner inputScanner = new Scanner(System.in);
        MainForm mainform = new MainForm();

        String accID;
        String password;
        do {
            //String accountID = inputScanner.nextLine();
            synchronized (mainform) {
                try {
                    mainform.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainform.setSubmitted(false);
                String accountID = mainform.getAccountID();
                String accountPassword = new String(mainform.getPassword());
                acc = new DebitAccount(accountID,accountPassword);
                doCommand("AccountSignIn");
                if (acc == null) {
                    mainform.setAccepted(false);
                }
            }
        } while (acc==null);
        mainform.setAccepted(true);
        mainform.dispose();

        mainMenuForm mainmenuform = new mainMenuForm();
        transactionProcessor = new TransactionProcessor(acc);
        while (acc != null) {
            synchronized (mainmenuform){
                mainmenuform.wait();
                String command = mainmenuform.getCommand().replace(" ","");
                System.out.println(command.replace(" ",""));
                doCommand(command);
            }
            //doCommand(inputScanner.nextLine());
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
}
