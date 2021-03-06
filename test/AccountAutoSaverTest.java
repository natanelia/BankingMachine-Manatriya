import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.AccountAutoSaver;
import edu.manatriya.banking.akunbanking.DebitAccount;
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
        AccountAutoSaver accAutoSaver = new AccountAutoSaver(account, 1);
        accAutoSaver.start();
        account.setSaldo(10000);
        File fileTransaction = new File("out/Accounts/DEdummy.acc");
        Scanner accountScanner = new Scanner(fileTransaction);
        String name, password, currency;
        double saldo;

        Thread.sleep(3000);
        password = accountScanner.nextLine();
        name = accountScanner.nextLine();
        currency = accountScanner.nextLine();
        saldo = Double.parseDouble(accountScanner.nextLine());
        assertEquals("012345", password);
        assertEquals("EUR", currency);
        assertEquals(10000.0, saldo, 0);

    }
}