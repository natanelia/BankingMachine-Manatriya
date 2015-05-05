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
     * Konstruktor transaksi Transfer
     * @param account Akun untuk melakukan transfer
     * @param to_account_id ID akun yang menerima transfer
     * @param transfer_delay Delay transfer untuk timed transfer, dalam detik
     * @param amount Banyak uang yang ditransfer
     */
    public Transfer(Account account, String to_account_id, int transfer_delay, double amount) {
        super(account, amount);
        destinationAccountID = to_account_id;
        transferDelay = transfer_delay;
        transactionName = this.getClass().getSimpleName();
        setDescription("TO ID: " + getDestinationAccountID());
    }

    /**
     * Getter untuk ID akun tujuan
     * @return ID akun tujuan
     */
    public String getDestinationAccountID() {
        return destinationAccountID;
    }

    /**
     * Setter untuk ID akun tujuan
     * @param destinationAccountID ID akun pengganti
     */
    public void setDestinationAccountID(String destinationAccountID) {
        this.destinationAccountID = destinationAccountID;
    }

    /**
     * Getter untuk delay transfer
     * @return Delay transfer dalam detik
     */
    public int getTransferDelay() {
        return transferDelay;
    }

    /**
     * Setter untuk delay transfer
     * @param transferDelay Delay transfer pengganti dalam detik
     */
    public void setTransferDelay(int transferDelay) {
        this.transferDelay = transferDelay;
    }

    @Override
    /**
     * Melakukan penciptaan akun tujuan transfer dan melakukan proses transfer sejumlah uang yang telah didefinisikan pada konstruktor
     */
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
            System.err.println(e.getMessage());
        }

    }


}
