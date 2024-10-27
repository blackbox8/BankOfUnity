import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Savings class represents a savings account in a banking system.
 * It extends the Account class and provides methods to manage the 
 * savings account, including depositing funds, withdrawing funds, displaying
 * the current balance, and logging transactions.
 * 
 * This class includes the ability to track the balance of the savings account
 * and log transactions to a file.
 */
class Saving extends Account {

    /**
     * Default constructor for the Savings class.
     */
    public Saving() {}

    /**
     * Constructs a Savings account with the specified account number
     * and initial balance.
     *
     * @param accountNumber the account number for the savings account
     * @param accountBalance the initial balance of the savings account
     */
    public Saving(int accountNumber, double accountBalance) {
        super(accountNumber, accountBalance);
    }

    /**
     * Displays the current balance of the savings account.
     */
    public void displayBalance() {
        System.out.println("Current Balance: $" + getBalance());
    }

    /**
     * Logs a balance inquiry transaction to a log file.
     *
     * @param customer the {@code Person} object representing the customer
     *                 making the inquiry
     */
    public void toLogtxt(Person customer) {
        String transactionDetail = customer.getFirstName() + " " + customer.getLastName() +
                " made a balance inquiry on " + getAccountNumber() + ". " +
                customer.getFirstName() + " " + customer.getLastName() +
                "’s Balance for " + this.getAccountNumber() + ": $" + this.getBalance() + ".\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(transactionDetail);
        } catch (IOException e) {
            System.out.println("An error occurred while logging the transaction: " + e.getMessage());
        }
    }

    /**
     * Deposits the specified amount into the savings account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        System.out.println("Total amount deposited: " + amount);
        setBalance(getBalance() + amount);
        System.out.println("New balance: " + getBalance());
    }

    /**
     * Withdraws the specified amount from the savings account.
     *
     * @param amount the amount to withdraw
     * @return {@code true} if the withdrawal was successful, 
     *         {@code false} otherwise
     */
    public boolean withdraw(double amount) {
        if(amount > getBalance()){
            System.out.println("Insufficient funds.");
            return false;
        }else{
            setBalance(getBalance() - amount);
            return true;
        }
    }

    /**
     * Returns the limit for the savings account. 
     * For checking accounts, the limit is not applicable and always return 0.0.
     * 
     * @return 0.0 as the limit for the checking account
     */
    public double getLimit() {
        return 0.0;
    }
}
