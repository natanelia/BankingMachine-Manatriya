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

    public AccountSignIn(Account _account) {
        account = _account;
    }

    /**
     * Mengecek apakah accountID yang ingin melakukan sign in sudah ada dalam database (file eksternal)
     *
     * @param accountID
     * @param password
     */
    private Account getValidAccount(String accountID, String password) throws Exception {
        String filename = "out\\Accounts\\" + accountID + ".acc";
        File f = new File(filename);
        if (f.exists()) {
            AccountFactory accountFactory = new AccountFactory();
            return accountFactory.getAccount(filename);
        } else
            return null;
    }

    public Account getAccount() { return account; }

    @Override
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
