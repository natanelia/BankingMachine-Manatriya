package edu.manatriya.banking.akunbanking;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KEVIN on 28/04/2015.
 */
public class CreditAccountTest{

    @Test
    public void testUpdateSaldo() throws Exception {
        CreditAccount dummy = new CreditAccount("out\\Accounts\\CR790112.acc");
        double tempsaldo=dummy.getSaldo();
        dummy.updateSaldo(-10000);
        assertEquals(tempsaldo-10000,dummy.getSaldo(),0);
    }
}