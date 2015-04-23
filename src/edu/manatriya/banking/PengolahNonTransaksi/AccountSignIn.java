package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;
import java.io.File;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class AccountSignIn implements Command {
    private String accountID;
    private Account account;

    public AccountSignIn(String _accountID) {
        accountID = _accountID;
        account = null;
    }

    /**
     * Getter and setter
     */
    public Account getAccount(){
        return account;
    }

    public void setAccount(Account A2){
        account = A2;
    }

    /**
     * Method-method
     */

    /**
     * Mengecek apakah accountID yang ingin melakukan sign in sudah ada dalam database (file eksternal)
     *
     * @param accountID
     */
    private void checkAccountValidityAndAssign(String accountID) {
        String filename = "out/Accounts/CR" + accountID + ".acc";
        File f = new File(filename);
        if (!f.exists()) {
            filename = "out/Accounts/DE" + accountID + ".acc";
            f = new File(filename);
            if (f.exists())
                setAccount(new DebitAccount(filename));
        } else {
            setAccount(new CreditAccount(filename));
        }
        if (!f.exists()) {
            setAccount(null);
        }
    }


        /**
         * eksekusi command
         */
    @Override
    public void execute(){
        checkAccountValidityAndAssign(accountID);
    }
}
