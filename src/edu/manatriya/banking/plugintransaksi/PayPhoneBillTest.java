package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;

public class PayPhoneBillTest {

    @Test
    public void testGetPhoneNumber() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testGetProvider() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testRun() throws Exception {
        Account acc = new DebitAccount("DE901366","012345");
        acc.setSaldo(5000);
        acc.setCurrency("IDR");

        PayPhoneBill PPBill = new PayPhoneBill(acc,"081705974747", "XL", 3000);
        PPBill.run();
        assert (acc.getSaldo() == 2000);
    }
}