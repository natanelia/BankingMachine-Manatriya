package edu.manatriya.banking.akunbanking;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by KEVIN on 28/04/2015.
 */
public class AccountAutoSaverTest {

    @Test
    public void testRun() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        AccountAutoSaver accAutoSaver = new AccountAutoSaver(account, 5);
        accAutoSaver.start();
        account.setSaldo(1000000);
        File fileTransaction = new File("out/Accounts/DEdummy.acc");
        Scanner accountScanner = new Scanner(fileTransaction);
        String name, password, currency;
        double saldo;
        password = accountScanner.nextLine();
        name = accountScanner.nextLine();
        currency = accountScanner.nextLine();
        saldo = Double.parseDouble(accountScanner.nextLine());
        assertEquals("012345", password);
        assertEquals("EUR", currency);
        assertEquals(1000000.2165938611, saldo, 0);

    }
}