import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a transfer of funds between two accounts.
 * This class handles the documentation of the transfer between two customers.
 */
public class Transfer {
    private double amount; // The amount to be transferred
    private Person sender; // The person sending the funds
    private Person receiver; // The person receiving the funds
    private Account sendersAccount; // The sender's account
    private Account receiversAccount; // The receiver's account

    /**
     * Constructs a Transfer object with the specified details.
     *
     * @param amount            The amount to be transferred.
     * @param sender            The person sending the funds.
     * @param receiver          The person receiving the funds.
     * @param senderAccount     The sender's account.
     * @param receiverAccount   The receiver's account.
     */
    public Transfer(double amount, Person sender, Person receiver, Account senderAccount, Account receiverAccount) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.sendersAccount = senderAccount;
        this.receiversAccount = receiverAccount;
    }

    /**
     * Performs the transfer of funds from the sender's account to the receiver's account.
     * It deducts the specified amount from the sender's account and deposits it into the receiver's account.
     */
    public void performTransfer() {
        this.sendersAccount.withdraw(this.amount);
        this.receiversAccount.deposit(this.amount);
        //toLogtxt();
    }

    /**
     * Logs the details of the transfer to a file named "log.txt".
     * The log includes information about the sender, receiver, amount transferred, 
     * and the new balance of the receiver's account.
     */
    public void toLogtxt() {
        System.out.println("Transaction completed");
        String transactionDetail = this.sender.getFirstName() + " " + this.sender.getLastName() + " paid " +
                this.receiver.getFirstName() + " " + this.receiver.getLastName() + " $" + this.amount +
                " from account " + this.sendersAccount.getAccountNumber() + ".\n" +
                this.receiver.getFirstName() + " " + this.receiver.getLastName() + " received $" +
                this.amount + ". New balance for account " + this.receiversAccount.getAccountNumber() + ": $" +
                this.receiversAccount.getBalance() + ".\n";
        
        // Write to a file using FileWriter and BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(transactionDetail);
            //writer.write("\n");
        } catch (IOException e) {
            System.out.println("An error occurred while logging the transfer: " + e.getMessage());
        }
    }
}
