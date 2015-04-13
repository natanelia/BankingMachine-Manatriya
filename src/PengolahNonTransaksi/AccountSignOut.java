package PengolahNonTransaksi;

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
    private Account acc;


    /**
     * 4 sekawan
     */
    public AccountSignOut(){
        acc = new Account();
    }

    public AccountSignOut(Account _acc){
        acc= _acc;
    }


    /**
     * Method-method
     */

    @Override
    public void Execute(){
    }


}
