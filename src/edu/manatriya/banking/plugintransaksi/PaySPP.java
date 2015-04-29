package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

import javax.swing.*;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PaySPP extends Transaction {
    private String schoolID;
    private String studentID;

    /**
     * Konstruktor transaksi PaySPP, untuk melakukan pembayaran uang sekolah
     * @param account Akun untuk membayar uang sekolah
     * @param school_id Nomor identitas rekening sekolah
     * @param student_id Nomor identitas siswa
     * @param amount Banyak uang untuk pembayaran uang sekolah
     */
    public PaySPP(Account account, String school_id, String student_id, double amount) {
        super(account, amount);
        schoolID = school_id;
        studentID = student_id;
        transactionName = this.getClass().getSimpleName();
        this.description = "SCHID: " + schoolID + "|" + "STUID: " + studentID;
    }

    /**
     * Getter nomor identitas rekening sekolah
     * @return Nomor identitas rekening sekolah
     */
    public String getSchoolID() {
        return schoolID;
    }

    /**
     * Getter nomor identitas siswa
     * @return Nomor identitas siswa
     */
    public String getStudentID() {
        return studentID;
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
