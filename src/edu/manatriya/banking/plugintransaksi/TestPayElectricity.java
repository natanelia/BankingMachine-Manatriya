package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;

public class TestPayElectricity {

    @Test
    public void testGetElectricityID() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testRun() throws Exception {
        Account acc = new DebitAccount("DE901366","012345");
        acc.setSaldo(5000);
        acc.setCurrency("IDR");

        PayElectricity PEl = new PayElectricity(acc,"12345", 3000);
        PEl.run();
        assert (acc.getSaldo() == 2000);
    }
}