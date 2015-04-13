package PengolahNonTransaksi;

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
        if (acc == Null){
            System.out.println("masukkan AccountID anda:");

        }
    }

    @Override
    public void Execute(){
    }

}
