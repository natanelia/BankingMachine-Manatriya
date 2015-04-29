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
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        AccountAutoSaver accAutoSaver = new AccountAutoSaver(account, 5);
        accAutoSaver.start();
        account.setSaldo(1000000);
        File fileTransaction = new File("out\\Accounts\\DE901366.acc");
        Scanner accountScanner = new Scanner(fileTransaction);
        String name, password, currency;
        double saldo;
        name = accountScanner.nextLine();
        password = accountScanner.nextLine();
        currency = accountScanner.nextLine();
        saldo = Double.parseDouble(accountScanner.nextLine());
        //assertEquals("012345", password);
        assertEquals("IDR", currency);
        assertEquals(1000000, saldo, 0);

    }
}