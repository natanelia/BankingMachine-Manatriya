package edu.manatriya.banking.akunbanking;

/**
 * Created by KEVIN on 14/04/2015.
 */
public class DebitAccount extends Account {

    public DebitAccount(String accID, String accName, long accSaldo){
        super(accID,accName,accSaldo);
    }

    @Override
    public void updateSaldo(int amount) {
        long   tempSaldo = getSaldo() + amount;
        if (tempSaldo < 0) {
            super.setSaldo(amount);
        }
    }
}
