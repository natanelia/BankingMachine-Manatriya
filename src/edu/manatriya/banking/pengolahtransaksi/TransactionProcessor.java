package edu.manatriya.banking.pengolahtransaksi;

import plugintransaksi.Transaction;

import java.lang.reflect.*;
import java.util.List;
import java.util.Queue;
import java.lang.*;

/**
 * Created by Alberttriadrian on 4/12/2015.
 */

public class TransactionProcessor {
    private Queue<Transaction> pendingTrans;
    private List<Transaction> runningTrans;
    private Account acc;

    //Constructor
    TransactionProcessor(){}

    //Gettter and Setter
    public Account getAcc(){
        return acc;
    }

    public Transaction getRunningTrans(int runningTransID ){
        return runningTrans.get(runningTransID);
    }

    public Transaction getPendingTrans(int pendingTransID ){
        return pendingTrans.element();
    }

    public void setAccount(Account acc){
        this.acc = acc;
    }


    public void generateForm(String TransactionType){
        try {
            //Mendapatkan class plugin yang ingin dipakai misalnya : deposit, Transfer, PayElectricity, dan lain- lain
            Class pluginTransaction = Class.forName("plugintransaksi." + TransactionType);

            try {
                //Mendapatkan parameter-parameter dari class plugin yang didaptakan oleh "pluginTransaction"
                Constructor constructorList[] = pluginTransaction.getDeclaredConstructors();
                Constructor pluginConstructor = constructorList[0];

                System.out.println("name = " + pluginConstructor.getName());
                System.out.println("decl class = " + pluginConstructor.getDeclaringClass());

                Class parameterConstructor[] = pluginConstructor.getParameterTypes();
                for (int j = 0; j < parameterConstructor.length; j++)
                    System.out.println("param #" + j + " " + parameterConstructor[j]);

                try {
                    //Memanggil Konstruktor plugin dengan refelction
                    Object plugin = pluginConstructor.newInstance(parameterConstructor);

                    try {
                        //Mendapatkan method run yang ada pada plugin
                        Method pluginMethodRun = pluginTransaction.getDeclaredMethod("run", new Class[]{});
                        try{
                            //Menjalankan method run pada plugin yang didapat
                            pluginMethodRun.invoke(plugin,new Object[]{});
                        }
                        catch(InvocationTargetException e){
                            System.out.println("Invocation is failed");
                        }
                    }
                    catch (NoSuchMethodException e) {
                        System.out.println("Method tidak ditemukan.");
                    }

                } catch (IllegalAccessException e) {
                    System.out.println("Error 1 saat menghidupkan plugin");
                } catch (InstantiationException e) {
                    System.out.println("Error 2 saat menghidupkan plugin");
                }
            }
            catch(Throwable e){
                System.out.println("Gagal mendapatkan parameter Constructor");
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("Plugin tidak ditemukan.");
        }
    }

    public void startAll(){
        for (int i = 0; i < pendingTrans.size() -1; i++ ){
            pendingTrans.remove().run();
        }
    }

    public void start(int pendingTransID){
        pendingTrans.remove().run();
    }

    public void stop(int runningTransID){
        runningTrans.get(runningTransID);
    }

    public void addTranstoAccount(){
        acc.addSuccesfulTransaction();
    }

}
