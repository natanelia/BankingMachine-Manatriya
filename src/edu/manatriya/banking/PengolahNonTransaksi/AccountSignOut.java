package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class AccountSignOut implements Command {
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

    public AccountSignOut(Account _account){
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
     * Mengeset nilai acc menjadi Null, artinya account telah dilogout dan tidak dapat diolah lagi
      */
    public void signOut(){
        account= null;
    }

    @Override
    public void Execute() {
        signOut();
    }


}
