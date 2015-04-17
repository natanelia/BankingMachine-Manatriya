package edu.manatriya.banking.akunbanking;

/**
 * Created by KEVIN on 14/04/2015.
 */
public class CreditAccount extends Account {
        public CreditAccount(String accID, String accName, long accSaldo){
            super(accID,accName,accSaldo);
    }
        public CreditAccount(String filename,String filedir) {super(filename,filedir);};
}

