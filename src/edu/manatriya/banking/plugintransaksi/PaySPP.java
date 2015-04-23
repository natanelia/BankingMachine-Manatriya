package edu.manatriya.banking.plugintransaksi;

import edu.manatriya.banking.akunbanking.Account;

/**
 * Created by Natan Elia on 4/12/2015.
 */
public class PaySPP extends Transaction {
    private String schoolID;
    private String studentID;

    public PaySPP(Account account, String school_id, String student_id, double amount) {
        super(account, amount);
        schoolID = school_id;
        studentID = student_id;
        transactionName = this.getClass().getSimpleName();
    }

    public String getSchoolID() {
        return schoolID;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public synchronized void run() {
        acc.updateSaldo(-amount);
        addToAccount();
    }
}
