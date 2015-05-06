package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Adalah kelas yang menangani proses sign out pada account. Semua aktivitas transaksi account akan disimpan sebelum sign out dilakukan.
 */
public class AccountSignOut implements Command {
    private Account account;


    /**
     * constructor berparameter kelas AccountSignOut
     * @param _account adalah account masukkan yang akan digunakan dalam operasi
     */
    public AccountSignOut(Account _account){
        account= _account;
    }


    /**
     * fungsi getter untuk mengembalikan atribut account
     * @return mengembalikan atribut account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Mengeset account menjadi akun dummy
     */
    @Override
    public synchronized void execute() {
        account.saveAccount();
        account.setAccount(null);
    }


}
