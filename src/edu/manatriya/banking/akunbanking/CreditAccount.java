/**
 * Kelas yang merepresentasikan sebuah Credit Account
 * Memungkinkan saldo bernilai negatif
 */
package edu.manatriya.banking.akunbanking;

import java.io.FileNotFoundException;

/**
 * Created by KEVIN on 14/04/2015.
 */

public class CreditAccount extends Account {
    /**
     * Konstruktor CreditAccount
     * @param accountFileName , nama file yang menyimpan informasi mengenai suatu account
     * @throws FileNotFoundException , file tidak ditemukan
     */
    public CreditAccount(String accountFileName) throws FileNotFoundException {
        super(accountFileName);
    }

    /**
     * Mengcopy sebuah Credit account
     * @param c , sebuah CreditAccount
     */
    public CreditAccount(CreditAccount c) {
        super(c);
    }

    /**
     * Konstruktor CreditAccount
     * @param accountID , accountID dari sebuah account
     * @param password , password
     */
    public CreditAccount(String accountID, String password) {
        super(accountID, password);
    }

    /**
     * I.S : Saldo terdefinisi
     * F.S : Saldo ditambah sebanyak amount
     * @param amount , variable yang mengubah saldo dari sebuah account
     */
    public void updateSaldo(double amount){
        double tempSaldo = getSaldo() + amount;
        setSaldo(tempSaldo);
    }
}