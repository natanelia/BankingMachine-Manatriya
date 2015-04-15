package edu.manatriya.banking.PengolahNonTransaksi;

import java.util.Scanner;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class AcountSignIn implements Command {
    /**
     * adalah salah satu kelas NonTransaksi
     *
     */


    /**
     * atribut-atribut
     */
    private Account acc;


    public AccountSignIn() {
        acc = new Account();
    }

    public AccountSignIn(Account _acc) {
        acc = _acc;
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
     * Mengecek apakah sudah melakukan login account
     * jika acc bernilai Null, artinya belum dilakukan login, dan harus melakukan login
     */
    private void checkLoginStatus    {
        while (acc == null) {
            if (acc == null) {
                System.out.println("masukkan AccountID anda:");
                Scanner s = new Scanner(System.in);
                String AccID = s.nextLine();
                acc = new Account(AccID);
            }
            // Acount sudah berisis (login telah berhasil)
        }
    }

        @Override
        public void Execute () {
            checkLoginStatus();
    }


}
