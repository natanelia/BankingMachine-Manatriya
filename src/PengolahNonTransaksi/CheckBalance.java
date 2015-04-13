package PengolahNonTransaksi;

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
    public CheckBalance(){
        acc = new Account();
    }

    public CheckBalance(Account _acc){
        acc= _acc;
    }


    /**
     * Method-method
     */

    @Override
    public void Execute(){
    }



}
