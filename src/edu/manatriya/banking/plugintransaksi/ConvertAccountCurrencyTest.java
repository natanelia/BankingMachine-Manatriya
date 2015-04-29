package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;

public class ConvertAccountCurrencyTest {

    @Test
    public void testGetNewCurrency() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testSetNewCurrency() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testRun() throws Exception {
        Account acc = new DebitAccount("DE901366","012345");
        acc.setSaldo(10000);
        acc.setCurrency("IDR");

        ConvertAccountCurrency ConAcc = new ConvertAccountCurrency(acc, "USD");
        ConAcc.run();
        assert (acc.getCurrency().equals("USD") && acc.getSaldo()!=10000); //converted to another currency
    }
}