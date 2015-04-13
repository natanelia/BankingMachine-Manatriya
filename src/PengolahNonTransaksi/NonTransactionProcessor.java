package PengolahNonTransaksi;

/**
 * Created by Marco Orlando on 4/11/2015.
 */
public class NonTransactionProcessor {
    /**adalah kelas yang menjadi processor untuk aktivitas-aktivitas nontransaksi (tidak mempengaruhi saldo Account),
     * yakni AccountSignIn,CheckBalance, ShowTransactionHistory, AccountSignOut
     */

    /**
     * Artribut
     */
    private Command cmd;
    private Account acc;


    /**
     * 4 sekawan
     */


    public NonTransactionProcessor(String  _cmd, Account _acc){
        acc = _acc;
        if (_cmd.equals("AccountSignIn")) {
            cmd = new AccountSignIn(acc);
        } else if (_cmd.equals("AccountSignOut")){
            cmd = new AccountSignIn(acc);
        } else if (_cmd.equals("CheckBalance")){
            cmd = new CheckBalance(acc);
        } else if (_cmd.equals("ShowTransactionHistory")){
            cmd = new ShowTransactionHistory(acc);
        } else {
            throw InvalidParameterException();
        }
    }

    public NonTransactionProcessor( NonTransactionProcessor N2){
        cmd = N2.cmd;
        acc = N2.acc;
    }

    public ~NonTransactionProcessor(){
        ;
    }


    /**
     * Getter and Setter
     */
    public Command Getcmd(){
        return cmd;
    }

    public Account Getacc(){
        return acc;
    }




    /**
     * Method-method
     */
    /**
     * Memroses command dengan menghidupkan dan mengeksekusi kelas yang sesuai
     */
    public void process(){
        //menghidupkan command-command yang ada untuk
        cmd.Execute();
    }
}



