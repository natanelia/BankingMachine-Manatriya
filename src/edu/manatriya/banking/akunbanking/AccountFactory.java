
package edu.manatriya.banking.akunbanking;

/**
 * Kelas yang bertanggung jawab menciptkan sebuah account
 */
public class AccountFactory {
    /**
     *
     * @param accountFileName , nama file yang menyimpan informasi mengenai sebuah file
     * @return sebuah DebitAccount atau CreditAccount, sesuai dengan jenis account yang didapatkan dari accountFileName
     * @throws Exception , mengthrow exception apabila accountFileName tidak ditemukan dalam folder yang menyimpan semua .acc tiap account
     */
    public Account getAccount(String accountFileName) throws Exception {
        String filename;
        String dirs[];
        if (accountFileName.contains("\\"))
            dirs = accountFileName.split("\\\\");
        else
            dirs = accountFileName.split("/");

        filename = dirs[dirs.length-1];
        if (filename.startsWith("DE")) {
            return new DebitAccount(accountFileName);
        } else if (filename.startsWith("CR")) {
            return new CreditAccount(accountFileName);
        } else {
            throw new Exception("Account not found.");
        }
    }
}
