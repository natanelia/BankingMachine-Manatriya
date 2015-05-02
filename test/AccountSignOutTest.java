import edu.manatriya.banking.PengolahNonTransaksi.AccountSignOut;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;

public class AccountSignOutTest {

    @Test
    public void testExecute() throws Exception {
        Account acc = new DebitAccount("DE901366", "012345");
        AccountSignOut AccSOut = new AccountSignOut(acc);
        AccSOut.execute();
        assert (AccSOut.getAccount().getAccountID() == null && AccSOut.getAccount().getTransactionHistory()==null);
        //account is set to null;
    }
}