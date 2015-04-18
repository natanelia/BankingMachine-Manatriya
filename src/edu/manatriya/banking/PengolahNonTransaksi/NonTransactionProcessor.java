package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

import java.security.InvalidParameterException;

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
    private Command command;
    private Account account;


    /**
     * 4 sekawan
     */

    public NonTransactionProcessor(String  _command, Account _account) throws Exception {
        account = _account;

        if (_command.equals("AccountSignOut")){
            command = new AccountSignOut(account);
        } else if (_command.equals("CheckBalance")){
            command = new CheckBalance(account);
        } else if (_command.equals("ShowTransactionHistory")){
            command = new ShowTransactionHistory(account);
        } else {
            throw new InvalidParameterException();
        }
    }

    public NonTransactionProcessor( NonTransactionProcessor N2){
        command = N2.getCommand();
        account = N2.getAccount();
    }



    /**
     * Getter and Setter
     */
    public Command getCommand(){

        return command;
    }

    public void setCommand(Command cmd2){
        command = cmd2;
    }

    public  void setAccount (Account acc2){
        account = acc2;
    }

    public Account getAccount(){
        return account;
    }


    /**
     * Method-method
     */
    /**
     * Memroses command dengan menghidupkan dan mengeksekusi kelas yang sesuai
     */
    public void process(){
        //menghidupkan command-command yang ada untuk
        command.execute();
    }
}



