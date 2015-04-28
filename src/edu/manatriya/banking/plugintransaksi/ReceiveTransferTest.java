package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;

public class ReceiveTransferTest {

    @Test
    public void testGetSourceAccountID() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testRun() throws Exception {
        Account acc = new DebitAccount("DE901366","012345");
        acc.setSaldo(5000);
        acc.setCurrency("IDR");

        ReceiveTransfer RecTrf = new ReceiveTransfer(acc, "DE901366", 3000);
        RecTrf.run();
        assert (acc.getSaldo()==8000);

    }
}