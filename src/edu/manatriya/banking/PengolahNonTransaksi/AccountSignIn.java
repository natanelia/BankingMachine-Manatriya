package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountFactory;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class AccountSignIn implements Command {
    private Account account;


    /**
     * constructor berparameter kelas AccountSignIn
     * @param _account adalah account masukkan yang akan digunakan dalam operasi
     */
    public AccountSignIn(Account _account) {
        account = _account;
    }

    /**
     * Mengecek apakah accountID yang ingin melakukan sign in sudah ada dalam database (file eksternal)
     *
     * @param accountID accountID adalah string yang akan digunakan untuk pengecekan adanya akun tersebut dalam database
     * @param password password adalah string yang digunakan untuk authentikasi user (password login)
     */
    private Account getValidAccount(String accountID, String password) throws Exception {
        String filename = "out/Accounts/" + accountID + ".acc";
        File f = new File(filename);
        if (f.exists()) {
            Scanner s = new Scanner(f);
            String realPassword = s.nextLine();
            if (realPassword.equals(password)) {
                AccountFactory accountFactory = new AccountFactory();
                return accountFactory.getAccount(filename);
            }
        }
        return null;
    }


    /**
     * Mengembalikan atribut account
     * @return kembalian: account bertipe Account
     */
    public Account getAccount() { return account; }






    @Override
    /**
     * akan memanggil function getValidAccount untuk mengecek kevalidan account yang dicek
     * Jika account tersebut valid (ada dalam databse dan password benar), atribut account akan diaasing dengan account masukkan tersebut
     */
    public void execute() {
        try {
            Account hasil = getValidAccount(account.getAccountID(), account.getPassword());
            account.setAccount(hasil);
            account = hasil;
        } catch (Exception e) {
            account = null;
        }
    }
}
