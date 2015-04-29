package edu.manatriya.banking.akunbanking;

import org.w3c.dom.ranges.RangeException;

import java.io.FileNotFoundException;

/**
 * Created by KEVIN on 14/04/2015.
 */
public class DebitAccount extends Account {

    public DebitAccount(String accountFileName) throws FileNotFoundException {
        super(accountFileName);
    }

    public DebitAccount(DebitAccount c) {
        super(c);
    }

    public DebitAccount(String accountID, String password) {
        super(accountID, password);
    }

    public void updateSaldo(double amount) {
        double tempSaldo = getSaldo() + amount;
        if (tempSaldo < 0) {
            throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR,
                    "Your balance is not sufficient for the transaction!");
        }
        setSaldo(tempSaldo);
    }
}
