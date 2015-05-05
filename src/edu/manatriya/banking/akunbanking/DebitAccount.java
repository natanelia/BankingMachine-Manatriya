
package edu.manatriya.banking.akunbanking;

import org.w3c.dom.ranges.RangeException;

import java.io.FileNotFoundException;

/**
 * Kelas yang merepresentasikan sebuah Debit Account
 * tidak memperbolehkan account memiliki saldo yang kurang dari 0
 */
public class DebitAccount extends Account {
    /**
     *  Konstruktor dari DebitAccount
     * @param accountFileName , name file yang menampung informasi mengenai sebuah account
     * @throws FileNotFoundException , file tidak ditemukan
     */
    public DebitAccount(String accountFileName) throws FileNotFoundException {
        super(accountFileName);
    }

    /**
     * Menclone c
     * @param c sebuah DebitAccount yang akan dicopy informasinya
     */
    public DebitAccount(DebitAccount c) {
        super(c);
    }

    /**
     * Konstruktor dari Debit account
     * @param accountID , accountID sebuah account
     * @param password , password sebuah account
     */
    public DebitAccount(String accountID, String password) {
        super(accountID, password);
    }

    /**
     * I.S : Saldo terdefinisi
     * F.S : Saldo ditambah sebanyak amount
     * @param amount , variable yang mengubah saldo dari sebuah account
     */
    public void updateSaldo(double amount) {
        double tempSaldo = getSaldo() + amount;
        if (tempSaldo < 0) {
            throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR,
                    "Your balance is not sufficient for the transaction!");
        }
        setSaldo(tempSaldo);
    }
}
