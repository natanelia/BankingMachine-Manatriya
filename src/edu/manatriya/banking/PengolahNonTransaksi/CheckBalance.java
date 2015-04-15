package edu.manatriya.banking.PengolahNonTransaksi;

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
    private Account acc;


    /**
     * 4 sekawan
     */
    public CheckBalance(Account _acc){
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
    public  void showBalance(){
        System.out.print("Saldo anda adalah sebesar Rp ");
        System.out.print(acc.getSaldo());
        System.out.println(",00");
    }


    @Override
    public void Execute(){
        showBalance();
    }



}
