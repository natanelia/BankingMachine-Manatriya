package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

<<<<<<< HEAD
import java.lang.reflect.Method;
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> origin/master

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
<<<<<<< HEAD
        return acc;
    }

    public void setAccount(Account A2){
        acc = A2;
=======
        return account;
    }

    public void setAccount(Account A2){
        account = A2;
>>>>>>> origin/master
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
