package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class AccountSignOut implements Command {
    /**
     * adalah salah satu kelas NonTransaksi
     *
     */

    private Account account;

    public AccountSignOut(Account _account){
        account= _account;
    }

    /**
     * Mengeset account menjadi akun dummy
     */
    @Override
    public void execute() {
        account.setAccount(new DebitAccount());
    }


}
