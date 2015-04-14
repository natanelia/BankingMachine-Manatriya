package PengolahNonTransaksi;

import java.util.Scanner;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class AccountSignIn implements Command {
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
    public AccountSignIn(){
        acc = new Account();
    }

    public AccountSignIn(Account _acc){
        acc= _acc;
    }


    /**
     * Method-method
     */


    /**
     * Mengecek apakah sudah melakukan login account
     * jika acc bernilai Null, artinya belum dilakukan login, dan harus melakukan login
     */
    private void CheckLoginStatus{
        while (acc == null){
            System.out.println("masukkan AccountID anda:");
            Scanner s = new Scanner(System.in);
            String AccID = s.nextLine();
            acc= new Account(AccID);
        }
        // Acount sudah berisis (login telah berhasil)
    }

    @Override
    public void Execute(){
        CheckLoginStatus();
    }

}
