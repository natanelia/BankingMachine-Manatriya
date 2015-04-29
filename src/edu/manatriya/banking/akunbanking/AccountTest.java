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
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        assertEquals("DEdummy", account.getAccountID());
    }

    @Test
    public void testGetSaldo() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        assertEquals(account.getSaldo(), account.getSaldo(),0);
    }

    @Test
    public void testSetSaldo() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        account.setSaldo(0);
        assertEquals(0, account.getSaldo(), 0);
    }

    @Test
    public void testGetAccountFileName() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        assertEquals("out/Accounts/DEdummy.acc", account.getAccountFileName());
    }

    @Test
    public void testGetName() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        assertEquals("Kevin Yauris", account.getName());
    }

    @Test
    public void testGetPassword() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        assertEquals("012345", account.getPassword());
    }

    @Test
    public void testSetPassword() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        account.setPassword("000");
        assertEquals("000", account.getPassword());
    }

    @Test
    public void testGetCurrency() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        assertEquals("EUR",account.getCurrency());
    }

    @Test
    public void testGetTransactionHistory() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        ArrayList<String[]> test = account.getTransactionHistory();
        String temp="";
        for(int i = 0; i< test.get(1).length;i++) {
            temp = temp+test.get(1)[i];
        }
        assertEquals("29-04-2015 14:12ConvertAccountCurrencyCONVERTED TO: EUREUR 0.0", temp );
    }

    @Test
    public void testGetTransactionHistoryAt() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        String temp = "";
        for(int i = 0; i< account.getTransactionHistoryAt(1).length;i++) {
            temp = temp + account.getTransactionHistoryAt(1)[i];
        }
        assertEquals("29-04-2015 14:12ConvertAccountCurrencyCONVERTED TO: EUREUR 0.0", temp);
    }

    @Test
    public void testSetAccount() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        account.setAccount(null);
        assertEquals(null, account.getAccountID());
        assertEquals(null, account.getName());
    }

    @Test
    public void testChangeCurrency() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        account.changeCurrency("IDR");
        assertEquals("IDR",account.getCurrency());
    }

    @Test
    public void testAddTransactionToHistory() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        account.addTransactionToHistory("hello");
        assertEquals("hello", account.getTransactionHistoryAt(account.getTransactionHistory().size()-1)[0]);
    }

    @Test
    public void testSaveAccount() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        account.setSaldo(1000000);
        account.saveAccount();
        File fileTransaction = new File("out/Accounts/DEdummy.acc");

        Scanner accountScanner = new Scanner(fileTransaction);
        String name, password, currency;
        double saldo;
        name = accountScanner.nextLine();
        password = accountScanner.nextLine();
        currency = accountScanner.nextLine();
        saldo = Double.parseDouble(accountScanner.nextLine());
        assertEquals("EUR",currency);
        assertEquals(1000000,saldo,0);
    }

    @Test
    public void testEquals() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        Account account1 = new DebitAccount("out/Accounts/DEdummy.acc");
        assertEquals(true, account.equals(account1));
    }
}