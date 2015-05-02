import edu.manatriya.banking.PengolahNonTransaksi.AccountSignIn;
import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;

public class AccountSignInTest {


    @Test
    public void testExecute() throws Exception {
        Account acc = new DebitAccount("DE901366","012345");
        AccountSignIn accSIn= new AccountSignIn(acc);
        accSIn.execute();
        assert(accSIn.getAccount() != null);
    }

    @Test
    public void testTestExecute() throws Exception {
        Account acc = new DebitAccount("DE909090","012345"); //un-exist account
        AccountSignIn accSIn= new AccountSignIn(acc);
        accSIn.execute();
        assert(accSIn.getAccount() == null);
    }
}