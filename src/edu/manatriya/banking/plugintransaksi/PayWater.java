package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;

/**
 * Created by KEVIN on 29/04/2015.
 */
public class PayWater extends Transaction{
    private String waterCostumerID;
    private String waterProvider;
    /**
     * Konstruktor transaksi PaySPP, untuk melakukan pembayaran uang sekolah
     * @param account Akun untuk membayar uang sekolah
     * @param _waterCostumerID Nomor identitas rekening sekolah
     * @param _waterProvider Nomor identitas siswa
     * @param amount Banyak uang untuk pembayaran uang sekolah
     */
    public PayWater(Account account, String _waterCostumerID, String _waterProvider, double amount) {
        super(account, amount);
        waterCostumerID = _waterCostumerID;
        waterProvider = _waterProvider;
        transactionName = this.getClass().getSimpleName();
    }

    /**
     * Getter nomor identitas rekening sekolah
     * @return Nomor identitas rekening sekolah
     */
    public String getwaterCostumerID() {
        return waterCostumerID;
    }

    /**
     * Getter nomor identitas siswa
     * @return Nomor identitas siswa
     */
    public String getwaterProvider() {
        return waterProvider;
    }

    @Override
    /**
     * Melakukan pembayaran uang sekolah dengan nominal yang sudah didefinisikan di konstruktor
     */
    public synchronized void run() {
        acc.updateSaldo(-amount);
        addToAccount();
        JOptionPane.showMessageDialog(null, "Your school tuition fee has been paid.");
    }
}
