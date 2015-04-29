package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class ReceiveTransfer extends Transaction {
    private String sourceAccountID;

    /**
     * Konstruktor transaksi ReceiveTransfer, untuk menerima transfer.
     * @param account Akun yang menerima transfer
     * @param source_account_id Identitas akun
     * @param amount Banyak uang yang diterima
     */
    public ReceiveTransfer(Account account, String source_account_id, double amount) {
        super(account, amount);
        this.sourceAccountID = source_account_id;
        transactionName = this.getClass().getSimpleName();
        setDescription("FROM ID: " + sourceAccountID);
    }

    /**
     * Getter untuk identitas akun
     * @return Identitas akun
     */
    public String getSourceAccountID() {
        return sourceAccountID;
    }

    @Override
    /**
     * Melakukan penerimaan uang transfer
     */
    public synchronized void run() {
        acc.updateSaldo(amount);
        addToAccount();
    }


}
