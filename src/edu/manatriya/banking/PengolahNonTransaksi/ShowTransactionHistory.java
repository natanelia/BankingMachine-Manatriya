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
    private Account account;


    /**
     * 4 sekawan
     */
    public ShowTransactionHistory(Account _account){
        account= _account;
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
     * Print out semua history transaksi
     */
    public void printAllTransactionHistory() {
        for (ArrayList<String> transaksi account.getTransactionHistory()){
            for (String unit transaksi){
                System.out.print(unit);
                System.out.print("||");
            }
            System.out.println();
        }
    }


    @Override
    public void Execute(){
        printAllTransactionHistory();
    }



}
