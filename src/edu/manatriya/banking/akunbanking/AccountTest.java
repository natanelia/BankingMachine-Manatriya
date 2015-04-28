package edu.manatriya.banking.akunbanking;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by KEVIN on 27/04/2015.
 */
public class AccountTest {

    @Test
    public void testGetAccountID() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        assertEquals("DE901366", account.getAccountID());
    }

    @Test
    public void testGetSaldo() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        assertEquals(account.getSaldo(), account.getSaldo(),0);
    }

    @Test
    public void testSetSaldo() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        account.setSaldo(0);
        assertEquals(0, account.getSaldo(), 0);
    }

    @Test
    public void testGetAccountFileName() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        assertEquals("out\\Accounts\\DE901366.acc", account.getAccountFileName());
    }

    @Test
    public void testGetName() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        assertEquals("Kevin Yauris", account.getName());
    }

    @Test
    public void testGetPassword() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        assertEquals("012345", account.getPassword());
    }

    @Test
    public void testSetPassword() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        account.setPassword("000");
        assertEquals("000", account.getPassword());
    }

    @Test
    public void testGetCurrency() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        assertEquals("IDR",account.getCurrency());
    }

    @Test
    public void testGetTransactionHistory() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        ArrayList<String[]> test = account.getTransactionHistory();
        String temp="";
        for(int i = 0; i< test.get(1).length;i++) {
             temp = temp+test.get(1)[i];
        }
        assertEquals("14/04/2015TransferTO: Bank Central Asia (BCA)|ID: 790111-3000000|", temp );
    }

    @Test
    public void testGetTransactionHistoryAt() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        String temp = "";
        for(int i = 0; i< account.getTransactionHistoryAt(1).length;i++) {
            temp = temp + account.getTransactionHistoryAt(1)[i];
        }
        assertEquals("14/04/2015TransferTO: Bank Central Asia (BCA)|ID: 790111-3000000|", temp);
    }

    @Test
    public void testSetAccount() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        account.setAccount(null);
        assertEquals(null, account.getAccountID());
        assertEquals(null, account.getName());
    }

    @Test
    public void testChangeCurrency() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        account.changeCurrency("dollar");
        assertEquals("dollar",account.getCurrency());
    }

    @Test
    public void testAddTransactionToHistory() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        account.addTransactionToHistory("hello");
        assertEquals("hello", account.getTransactionHistoryAt(account.getTransactionHistory().size()-1)[0]);
    }

    @Test
    public void testSaveAccount() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        account.setSaldo(1000000);
        account.saveAccount();
        File fileTransaction = new File("out\\Accounts\\DE901366.acc");

            Scanner accountScanner = new Scanner(fileTransaction);
            String name, password, currency;
            double saldo;
            name = accountScanner.nextLine();
            password = accountScanner.nextLine();
            currency = accountScanner.nextLine();
            saldo = Double.parseDouble(accountScanner.nextLine());
            //assertEquals("012345", password);
            assertEquals("IDR",currency);
            assertEquals(1000000,saldo,0);
    }

    @Test
    public void testEquals() throws Exception {
        Account account = new DebitAccount("out\\Accounts\\DE901366.acc");
        Account account1 = new DebitAccount("out\\Accounts\\DE901366.acc");
        //assertEquals(true, account.equals(account1));
    }
}