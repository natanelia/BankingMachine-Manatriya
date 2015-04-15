package edu.manatriya.banking.PengolahNonTransaksi;

import java.lang.reflect.Method;
import java.util.List;

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
    public ShowTransactionHistory(Account _acc){
        acc= _acc;
    }


    /**
     * Getter and setter
     */
    public Account getAcc(){
        return acc;
    }

    public void setAcc(Account A2){
        acc = A2;
    }



    /**
     * Method-method
     */

    /**
     * Print out semua history transaksi
     */
    public void printAllTransactionHistory(){
        int nbTransaction = acc.getList.size();

        for (int i = 0; i<=nbTransaction;i++ ){
            Class myObjectClass = acc.getList[i].class;
            Method method = myObjectClass.getMethod("printTransaksi", null);
            Object returnValue = method.invoke(acc.getList[i]);
        }
    }






    @Override
    public void execute(){
        printAllTransactionHistory();
    }


}
