package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;

public class PaySPPTest {

    @Test
    public void testGetSchoolID() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testGetStudentID() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testRun() throws Exception {
        Account acc = new DebitAccount("DE901366","012345");
        acc.setSaldo(5000);
        acc.setCurrency("IDR");

        PaySPP PSPP = new PaySPP(acc,"ITB", "13513038", 3000);
        PSPP.run();
        assert (acc.getSaldo() == 2000);

    }
}