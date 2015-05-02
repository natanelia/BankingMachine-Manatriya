import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.CreditAccount;
import edu.manatriya.banking.akunbanking.DebitAccount;
import edu.manatriya.banking.pengolahtransaksi.TransactionProcessor;
import edu.manatriya.banking.plugintransaksi.PayElectricity;
import edu.manatriya.banking.plugintransaksi.PayPhoneBill;
import edu.manatriya.banking.plugintransaksi.PaySPP;
import edu.manatriya.banking.plugintransaksi.Transaction;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class TransactionProcessorTest extends TestCase {

    public void testStartAll() throws Exception {

        Account acc = new DebitAccount("DE901366","012345");
        acc.setSaldo(120000);
        acc.setCurrency("IDR");

        TransactionProcessor transactionProcessor = new TransactionProcessor(acc);
        PayPhoneBill PPB = new PayPhoneBill(transactionProcessor.getAccount(), "081901596260", "Xl Axiata", 50000);
        transactionProcessor.addTranstoQueue(PPB);
        transactionProcessor.startAll();
        Thread.sleep(1000);
        
        assertEquals(70000.0, transactionProcessor.getAccount().getSaldo());


    }

    public void testStop() throws Exception {
        TransactionProcessor transactionProcessor = new TransactionProcessor(new CreditAccount("13513098", "kevinYauris"));

        transactionProcessor.addTranstoList(new PayElectricity(transactionProcessor.getAccount(), "123456", 10000));
        transactionProcessor.addTranstoList(new PayPhoneBill(transactionProcessor.getAccount(), "081901596260", "Xl Axiata", 50000));

        transactionProcessor.stop(0);

        assertEquals(50000.0,transactionProcessor.getRunningTrans(0).getAmount() );
    }

    public void testAddTranstoQueue() throws Exception {
        TransactionProcessor transactionProcessor = new TransactionProcessor(new CreditAccount("13513098", "kevinYauris"));

        transactionProcessor.addTranstoQueue(new PayPhoneBill(new CreditAccount("13513098", "kevinYauris"), "081901596260", "Xl Axiata", 50000));
        assertEquals(transactionProcessor.getPendingTrans().getAmount(), 50000.0 );
    }
}