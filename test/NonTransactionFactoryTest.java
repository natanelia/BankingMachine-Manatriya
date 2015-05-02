import edu.manatriya.banking.PengolahNonTransaksi.Command;
import edu.manatriya.banking.PengolahNonTransaksi.NonTransactionFactory;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import junit.framework.TestCase;
import org.junit.Test;

public class NonTransactionFactoryTest extends TestCase {

    @Test
    public void testGetCommandAccSignIn() throws Exception {
        Account acc = new DebitAccount("DE901366","012345");
        NonTransactionFactory ntf = new NonTransactionFactory();
        Command a = ntf.getCommand("AccountSignIn",acc);
        assert(acc.getAccountID() != null && acc.getCurrency() != null && acc.getName() != null);
    }
}