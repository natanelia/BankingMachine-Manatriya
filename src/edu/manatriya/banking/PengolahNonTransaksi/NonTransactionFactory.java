package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/23/2015.
 */
public class NonTransactionFactory {
    public Command getCommand(String cmd, Account acc) {
        if (cmd.equals("AccountSignIn")) {
            return new AccountSignIn(acc);
        } else if (cmd.equals("AccountSignOut")){
            return new AccountSignOut(acc);
        } else if (cmd.equals("CheckBalance")){
            return new CheckBalance(acc);
        } else if (cmd.equals("ShowTransactionHistory")){
            return new ShowTransactionHistory(acc);
        }
        return null;
    }
}
