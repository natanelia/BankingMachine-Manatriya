import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.akunbanking.DebitAccount;
import org.junit.Test;
import org.w3c.dom.ranges.RangeException;

import static org.junit.Assert.*;

/**
 * Created by KEVIN on 28/04/2015.
 */
public class DebitAccountTest {

    @Test
    public void testUpdateSaldo() throws Exception {
        Account account = new DebitAccount("out/Accounts/DEdummy.acc");
        double tempsaldo = account.getSaldo();
        account.updateSaldo(10000);
        assertEquals(tempsaldo+10000,account.getSaldo(),0);
    }

    @Test
    public void testUpdateSaldo1() throws Exception {
        try {
            Account account = new DebitAccount("out/Accounts/DEdummy.acc");
            double tempsaldo = account.getSaldo();
            account.updateSaldo((tempsaldo + 10000) * -1);
        }
        catch (RangeException e){

        }
    }

}