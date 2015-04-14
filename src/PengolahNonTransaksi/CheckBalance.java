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
    public CheckBalance(Account _acc){
        acc= _acc;
    }


    /**
     * Method-method
     */
    public  void ShowBalance(){
        System.out.print("Saldo anda adalah sebesar Rp ");
        System.out.print(acc.GetSaldo());
        System.out.println(",00");
    }


    @Override
    public void Execute(){
        ShowBalance();
    }



}
