package edu.manatriya.banking.akunbanking;

/**
 * Created by Natan Elia on 4/27/2015.
 */
public class AccountFactory {
    public Account getAccount(String accountFileName) throws Exception {
        String filename;
        String dirs[];
        if (accountFileName.contains("\\")) dirs = accountFileName.split("\\\\");
        else dirs = accountFileName.split("/");

        filename = dirs[dirs.length-1];
        if (filename.startsWith("DE")) {
            return new DebitAccount(accountFileName);
        } else if (filename.startsWith("CR")) {
            return new CreditAccount(accountFileName);
        } else {
            throw new Exception("Account not found.");
        }
    }
}
