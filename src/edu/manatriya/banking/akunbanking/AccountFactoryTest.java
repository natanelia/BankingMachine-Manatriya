package edu.manatriya.banking.akunbanking;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KEVIN on 28/04/2015.
 */
public class AccountFactoryTest {

    @Test
    public void testGetAccount() throws Exception {
        AccountFactory accountfactorytest = new AccountFactory();
        Account dummy;
        dummy = accountfactorytest.getAccount("out/Accounts/DEdummy.acc");
        assertEquals("DEdummy", dummy.getAccountID());
    }
}