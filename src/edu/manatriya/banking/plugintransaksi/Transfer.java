package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountFactory;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Transfer extends Transaction {
    private String destinationAccountID;
    private int transferDelay;

    /**
     * Transfer constructor using
     * @param account
     * @param to_account_id
     * @param transfer_delay
     * @param amount
     */
    public Transfer(Account account, String to_account_id, int transfer_delay, double amount) {
        super(account, amount);
        destinationAccountID = to_account_id;
        transferDelay = transfer_delay;
        transactionName = this.getClass().getSimpleName();
        setDescription("TO ID: " + getDestinationAccountID());
    }

    public String getDestinationAccountID() {
        return destinationAccountID;
    }

    public void setDestinationAccountID(String destinationAccountID) {
        this.destinationAccountID = destinationAccountID;
    }

    public int getTransferDelay() {
        return transferDelay;
    }

    public void setTransferDelay(int transferDelay) {
        this.transferDelay = transferDelay;
    }

    @Override
    public synchronized void run() {
        try {
            AccountFactory accountFactory = new AccountFactory();
            Account destAccount = accountFactory.getAccount("out/Accounts/" + destinationAccountID + ".acc");
            String destCurrency = destAccount.getCurrency();
            destAccount.changeCurrency(acc.getCurrency());
            ReceiveTransfer receiveTransfer = new ReceiveTransfer(destAccount,acc.getAccountID(),amount);

            JOptionPane.showMessageDialog(null, "Your transfer will be processed as soon as possible.");
            sleep(getTransferDelay() * 1000);

            acc.updateSaldo(-amount);
            receiveTransfer.start();
            receiveTransfer.join();
            destAccount.changeCurrency(destCurrency);
            destAccount.saveAccount();
            addToAccount();

            System.out.printf("Transfer from %s to %s has been processed.\n", acc.getAccountID(), destAccount.getAccountID());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Destination account is not found.", "", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {

        }

    }


}
