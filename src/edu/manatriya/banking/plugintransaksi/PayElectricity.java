package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayElectricity extends Transaction {
    private String electricityID;

    /**
     * Konstruktor transaksi PayElectricity
     * @param account Akun untuk membayar listrik
     * @param electricity_id Nomor rekening listrik
     * @param amount Banyak uang yang dibayarkan
     */
    public PayElectricity(Account account, String electricity_id, double amount) {
        super( account, amount);
        electricityID = electricity_id;
        transactionName = this.getClass().getSimpleName();
        description = "ELECID: " + electricity_id;
    }

    /**
     * Getter nomor rekening listrik
     * @return Nomor rekening listrik
     */
    public String getElectricityID() {
        return electricityID;
    }


    @Override
    /**
     * Melakukan pembayaran listrik dengan nominal yang telah didefinisikan di konstruktor
     */
    public synchronized void run() {
        acc.updateSaldo(-amount);
        addToAccount();
        JOptionPane.showMessageDialog(null, "Your electricity bill has been paid.");
    }
}
