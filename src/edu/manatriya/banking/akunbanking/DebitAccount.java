package edu.manatriya.banking.akunbanking;

import org.w3c.dom.ranges.RangeException;

/**
 * Created by KEVIN on 14/04/2015.
 */
public class DebitAccount extends Account {

    /*public DebitAccount(String accID, String accName, long accSaldo){
        super(accID,accName,accSaldo);
    }
    public DebitAccount(String filename,String filedir) {super(filename,filedir);};
    */

    public DebitAccount(String _accountID) {
        super(_accountID);
    }

    @Override
    public void updateSaldo(long amount) {
        long tempSaldo = getSaldo() + amount;
        if (tempSaldo < 0) {
            throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR,
                    "Your balance is not sufficient for the transaction!");
        }
        setSaldo(tempSaldo);
    }
}
