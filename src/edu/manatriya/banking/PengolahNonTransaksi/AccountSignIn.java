package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;
import java.io.File;

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
            if (f.exists())
                return (new DebitAccount(filename));
        } else {
            return (new CreditAccount(filename));
        }

        return null;
    }


    @Override
    public void execute(){
        account.setAccount(getValidAccount(account.getAccountID(), account.getPassword()));
    }
}
