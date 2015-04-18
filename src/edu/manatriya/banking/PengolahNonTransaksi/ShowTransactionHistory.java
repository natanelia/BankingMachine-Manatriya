package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

import java.util.ArrayList;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class ShowTransactionHistory implements Command {
    /**
     * adalah salah satu kelas NonTransaksi
     *
     */


    /**
     *artribut-artribut
     */
    private Account acc;


    /**
     * 4 sekawan
     */
    public ShowTransactionHistory(Account _account){
        acc= _account;
    }


    /**
     * Getter and setter
     */
    public Account getAccount(){
        return acc;
    }

    public void setAccount(Account A2){
        acc = A2;
    }

    /**
     * Method-method
     */

    /**
     * Print out semua history transaksi
     */
    private void printAllTransactionHistory() {
        for (String[] transaksi : acc.getTransactionHistory()){
            for (String unit : transaksi){
                System.out.print(unit);
                System.out.print("||");
            }
            System.out.println();
        }
    }


    @Override
    public void execute(){
        printAllTransactionHistory();
    }



}
