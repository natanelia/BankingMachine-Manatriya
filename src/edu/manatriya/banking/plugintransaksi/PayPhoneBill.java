package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PayPhoneBill extends Transaction {
    private String phoneNumber;
    private String provider;

    /**
     * Konstruktor transaksi PayPhoneBill, untuk mengisi pulsa prabayar SIM card
     * @param account Akun untuk membayar pulsa
     * @param phone_number Nomor handphone dengan SIM card yang ingin diisi pulsanya
     * @param provider Provider telekomunikasi
     * @param amount Banyak uang untuk mengisi pulsa
     */
    public PayPhoneBill(Account account, String phone_number, String provider, double amount) {
        super(account, amount);
        this.phoneNumber = phone_number;
        this.provider = provider;
        this.transactionName = this.getClass().getSimpleName();
    }

    /**
     * Getter untuk nomor handphone
     * @return Nomor handphone
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter untuk provider telekomunikasi
     * @return Provider telekomunikasi
     */
    public String getProvider() {
        return provider;
    }


    @Override
    /**
     * Melakukan pembayaran pulsa dengan nominal yang telah didefinisikan di konstruktor
     */
    public synchronized void run() {
        acc.updateSaldo(-amount);
        addToAccount();

        JOptionPane.showMessageDialog(null, "Your phone bill transaction has been processed.");
    }
}
