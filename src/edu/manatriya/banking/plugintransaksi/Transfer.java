package edu.manatriya.banking.plugintransaksi;

import com.sun.webkit.dom.RectImpl;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountFactory;
import edu.manatriya.banking.akunbanking.DebitAccount;

import javax.swing.*;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class Transfer extends Transaction {
    private String destinationAccountID;
    private int transferDelay;

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
            sleep(getTransferDelay() * 1000);
            acc.updateSaldo(-amount);
            addToAccount();

            AccountFactory accountFactory = new AccountFactory();
            try {
                Account destAccount = accountFactory.getAccount("out\\Accounts\\" + destinationAccountID + ".acc");
                ReceiveTransfer receiveTransfer = new ReceiveTransfer(destAccount,acc.getAccountID(),amount);
                receiveTransfer.start();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Destination account is not found.", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


}
