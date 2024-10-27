import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * The Checkings class represents a checking account that extends the {@link Account} class. 
 * It provides implementations for deposit, withdrawal, displaying balance, and logging transactions.
 */
class Checkings extends Account {

    /**
     * Default constructor for Checkings class.
     */
    public Checkings(){}

    /**
     * Constructor for Checkings class that initializes the account with a specified 
     * account number and balance.
     * 
     * @param accountNumber the account number for the checking account
     * @param accountBalance the initial balance for the checking account
     */
    public Checkings(int accountNumber, double accountBalance) {
        super(accountNumber, accountBalance);
    }

    /**
     * Displays the current balance of the checking account.
     * Prints the balance to the console.
     */
    @Override
    public void displayBalance() {
        System.out.println("Current balance: $" + getBalance());
    }

    /**
     * Logs a balance inquiry made by a customer to a log file.
     * Writes the transaction details, including the customer's name, account number, and balance, 
     * to the "log.txt" file.
     * 
     * @param customer the Person who made the balance inquiry
     */
    @Override
    public void toLogtxt(Person customer) {
        String transactionDetail = customer.getFirstName() + " " + customer.getLastName() +
                                   " made a balance inquiry on account " + getAccountNumber() + ". " +
                                   customer.getFirstName() + " " + customer.getLastName() + 
                                   "’s Balance for account " + this.getAccountNumber() + ": $" + this.getBalance() + ".\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(transactionDetail);
        } catch (IOException e) {
            System.out.println("An error occurred while logging the balance inquiry: " + e.getMessage());
        }
    }

    /**
     * Deposits a specified amount into the checking account and updates the balance.
     * Prints the deposited amount and the updated balance to the console.
     * 
     * @param amount the amount to deposit into the checking account
     */
    @Override
    public void deposit(double amount) {
        System.out.println("Total amount deposited: $" + amount);
        setBalance(getBalance() + amount);
        System.out.println("New balance: $" + getBalance());
    }

    /**
     * Withdraws a specified amount from the checking account.
     * In this implementation, the method always returns {@code true} but can be extended
     * to check for sufficient balance before performing the withdrawal.
     * 
     * @param amount the amount to withdraw from the checking account
     * @return true indicating that the withdrawal was successful
     */
    @Override
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
     * Returns the limit for the checking account. 
     * For checking accounts, the limit is not applicable and always return 0.0.
     * 
     * @return 0.0 as the limit for the checking account
     */
    @Override
    public double getLimit() {
        return 0.0;
    }
}