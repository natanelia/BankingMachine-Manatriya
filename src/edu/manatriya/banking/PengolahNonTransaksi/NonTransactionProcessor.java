package edu.manatriya.banking.PengolahNonTransaksi;

import edu.manatriya.banking.akunbanking.Account;

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


<<<<<<< HEAD
    public NonTransactionProcessor(String  _cmd, Account _acc){
        acc = _acc;
        if (_cmd.equals("AccountSignIn")) {
            cmd = new AccountSignIn(acc);
            acc = ((AccountSignIn)cmd).getAccount();
        } else if (_cmd.equals("AccountSignOut")){
            cmd = new AccountSignOut(acc);
            acc = ((AccountSignOut)cmd).getAccount();
        } else if (_cmd.equals("CheckBalance")){
            cmd = new CheckBalance(acc);
        } else if (_cmd.equals("ShowTransactionHistory")){
            cmd = new ShowTransactionHistory(acc);
=======
    public NonTransactionProcessor(String  _command, Account _account){
        account = _account;
        if (_command.equals("AccountSignIn")) {
            command = new AccountSignIn(account);
        } else if (_command.equals("AccountSignOut")){
            command = new AccountSignIn(account);
        } else if (_command.equals("CheckBalance")){
            command = new CheckBalance(account);
        } else if (_command.equals("ShowTransactionHistory")){
            command = new ShowTransactionHistory(account);
>>>>>>> origin/master
        } else {
            throw InvalidParameterException();
        }
    }

    private Exception InvalidParameterException() {
    }

    public NonTransactionProcessor( NonTransactionProcessor N2){
        command = N2.getCommand();
        account = N2.getAccount();
    }



    /**
     * Getter and Setter
     */
    public Command getCommand(){
<<<<<<< HEAD
        return cmd;
    }

    public Account getAccount(){
        return acc;
    }

    public void setCommand(Command cmd2){
        cmd= cmd2;
    }

    public  void setAccount (Account acc2){
        acc = acc2;
=======
        return command;
    }

    public Account getAccount(){
        return account;
    }

    public void setCommand(Command command2){
        command= command2;
    }

    public  void setAccount (Account account2){
        account = account2;
>>>>>>> origin/master
    }




    /**
     * Method-method
     */
    /**
     * Memroses command dengan menghidupkan dan mengeksekusi kelas yang sesuai
     */
    public void process(){
        //menghidupkan command-command yang ada untuk
        command.Execute();
    }
}



