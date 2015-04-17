package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class CheckBalance implements  Command {
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
    public CheckBalance(Account _account){
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
    public  void showBalance(){
        System.out.print("Saldo anda adalah sebesar Rp ");
        System.out.print(account.getSaldo());
        System.out.println(",00");
    }


    @Override
    public void Execute(){
        showBalance();
    }



}
