package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;
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
     * Mengecek apakah sudah melakukan login account
     * jika acc bernilai Null, artinya belum dilakukan login, dan harus melakukan login
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
            setAccount(new Account(filename));
        }
        if (!f.exists()) {
            setAccount(null);
        }

        //String accountDir = "/Accounts";
        //accountDir = (new Account().getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + accountDir).replace("%20", " ").substring(1);
        /*File available = new File(accountDir);
        String availableAccount[] = available.list();
        int i=0;
        boolean found = false;
        do{
            if (availableAccount[i].contains(inputanUser)) {
                found =true;
                if (inputanUser.contains("cr"))
                    account = new CreditAccount(availableAccount[i], accountDir);
                else
                    account = new DebitAccount(availableAccount[i], accountDir);
            } else i++;
        } while ((i < availableAccount.length) && (!found));*/
    }

    /*private void checkLoginStatus(){
        while (account == null) {
            // Acount sudah berisis (login telah berhasil)
        }
    }*/

    @Override
    public void execute () {
        checkAccountValidityAndAssign(accountID);
    }
}
