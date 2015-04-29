package edu.manatriya.banking;

import edu.manatriya.banking.PengolahNonTransaksi.AccountSignIn;
import edu.manatriya.banking.PengolahNonTransaksi.Command;
import edu.manatriya.banking.PengolahNonTransaksi.NonTransactionFactory;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountAutoSaver;
import edu.manatriya.banking.akunbanking.DebitAccount;
import edu.manatriya.banking.pengolahtransaksi.TransactionProcessor;
import edu.manatriya.banking.pengolahtransaksi.TransactionsAutoStarter;
import edu.manatriya.banking.plugintransaksi.MainForm;
import edu.manatriya.banking.plugintransaksi.mainMenuForm;

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

    public void run() {
        while (true) {
            MainForm mainform = new MainForm();

            do {
                //String accountID = inputScanner.nextLine();
                synchronized (mainform) {
                    try {
                        mainform.wait();
                    } catch (InterruptedException e) {
                    /* do nothing */
                    }
                    mainform.setSubmitted(false);
                    String accountID = mainform.getAccountID();
                    String accountPassword = new String(mainform.getPassword());
                    acc = new DebitAccount(accountID, accountPassword);
                    doCommand("AccountSignIn");
                    if (acc == null) {
                        mainform.setAccepted(false);
                    }
                }
            } while (acc == null);
            AccountAutoSaver accAutoSaver = new AccountAutoSaver(acc, 5);
            accAutoSaver.start();
            mainform.setAccepted(true);
            mainform.dispose();

            mainMenuForm mainmenuform = new mainMenuForm();
            transactionProcessor = new TransactionProcessor(acc);
            TransactionsAutoStarter transactionsAutoStarter = new TransactionsAutoStarter(transactionProcessor, 1);
            transactionsAutoStarter.start();
            while (acc.getAccountID() != null) {
                synchronized (mainmenuform) {
                    try {
                        mainmenuform.wait();
                    } catch (InterruptedException e) {
                    /* do nothing */
                    }
                    String command = mainmenuform.getCommand().replace(" ", "");
                    doCommand(command);
                }
            }
            try {
                transactionProcessor.startAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mainmenuform.dispose();
            transactionsAutoStarter.interrupt();
            accAutoSaver.interrupt();
        }
    }

    private void doCommand(String cmd) {
        try {
            NonTransactionFactory nonTransactionFactory = new NonTransactionFactory();
            Command command = nonTransactionFactory.getCommand(cmd, acc);
            command.execute();
            if (cmd.equalsIgnoreCase("AccountSignIn"))
                acc = ((AccountSignIn)command).getAccount();
        } catch (Exception e) {
            try {
                transactionProcessor.generateForm(cmd);
                transactionProcessor.startAll();
            } catch (Exception ex) {
                System.err.println("Command not found!");
            }
        }
    }
}
