package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/23/2015.
 */
public class NonTransactionFactory {

    /**
     * adalah fungsi yang melakukan pengecekan terhadap string masukkan untuk kemudian menghidupkan command yang sesuai dengan masukkan
     * getCommand akan memanggil fungsi untuk diterapkan pada acc masukkan
     * @param cmd cmd adalah masukkan berberntuk string. diperlukan untuk menghidupkan command yang sesuai
     * @param acc acc adalah account yang akan diolah oleh command
     * @return
     */
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
