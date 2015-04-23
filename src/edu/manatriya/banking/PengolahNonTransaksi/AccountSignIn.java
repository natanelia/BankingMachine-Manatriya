package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;
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
     */
    private Account getValidAccount(String accountID, String password) {
        String filename = "out/Accounts/CR" + accountID + ".acc";
        File f = new File(filename);
        if (!f.exists()) {
            filename = "out/Accounts/DE" + accountID + ".acc";
            f = new File(filename);
        }
        try {
            Scanner s = new Scanner(f);
            if (s.nextLine().equals(password))
                return (f.getName().startsWith("DE")) ? new DebitAccount(filename) : new CreditAccount(filename);
            else
                return null;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public Account getAccount() { return account; }

    @Override
    public void execute(){
        account = getValidAccount(account.getAccountID(), account.getPassword());
        //account.setAccount(getValidAccount(account.getAccountID(), account.getPassword()));
    }
}
