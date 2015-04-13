package edu.manatriya.banking.PengolahNonTransaksi;

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
    public ShowTransactionHistory(){
        acc = new Account();
    }

    public ShowTransactionHistory(Account _acc){
        acc= _acc;
    }


    /**
     * Method-method
     */

    @Override
    public void execute(){
    }


}
