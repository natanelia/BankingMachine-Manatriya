import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import edu.manatriya.banking.plugintransaksi.Transfer;
import org.junit.Test;

public class TransferTest {

    @Test
    public void testGetDestinationAccountID() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testSetDestinationAccountID() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testGetTransferDelay() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testSetTransferDelay() throws Exception {
        //nothing to be tested
    }

    @Test
    public void testRun() throws Exception {
        Account acc = new DebitAccount("DE901332","012345");
        acc.setSaldo(5000);
        acc.setCurrency("IDR");


        Transfer Trf = new Transfer(acc,"DE901366", 0, 3000);
        Trf.run();
        assert (acc.getSaldo() == 2000);
    }
}