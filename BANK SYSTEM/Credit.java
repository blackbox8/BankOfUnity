import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Credit class represents a credit account in a banking system.
 * It extends the Account class and provides methods to manage the 
 * credit account, including depositing funds, withdrawing funds, displaying
 * the current balance, and logging transactions.
 * 
 * This class includes the ability to track the maximum balance limit and 
 * the amount owed on the credit account.
 */
class Credit extends Account {
    /** The maximum amount the credit class may be charged*/
    private double maxBalance;

    /**
     * Default constructor for the Credit class.
     */
    public Credit() {}

    /**
     * Constructs a Credit account with the specified account number,
     * maximum balance limit, and starting balance.
     *
     * @param accountNumber the account number for the credit account
     * @param maxBalance the maximum balance limit for the credit account
     * @param startBalance the initial balance of the credit account
     */
    public Credit(int accountNumber, int maxBalance, double startBalance) {
        super(accountNumber, startBalance);
        this.maxBalance = maxBalance;
    }

    /**
     * Returns the maximum balance limit for the credit account.
     *
     * @return the maximum balance limit for the credit account
     */
    public double getLimit() {
        return this.maxBalance;
    }

    /**
     * Displays the current balance owed on the credit account.
     */
    public void displayBalance() {
        System.out.println("Current owed balance: $" + getBalance());
    }

    /**
     * Logs a balance inquiry transaction to a log file.
     *
     * @param customer the Person object representing the customer
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
     * Deposits the specified amount into the credit account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    /**
     * Withdraws the specified amount from the credit account.
     *  Checks if the credit limit will not be exceeded.
     * @param amount the amount to withdraw
     * @return true if the withdrawal was successful, 
     *         false otherwise
     */
    public boolean withdraw(double amount) {
        if(amount > Math.abs(this.maxBalance - amount)){
            System.out.println("Excedding maximum balance.");
            return false;
        }else{
            setBalance(getBalance() - amount);
            return true;
        }
    }

    /**
     * Pays the specified amount towards the owed balance of the credit account
     * from the specified current account.
     *
     * @param currentAccount the account from which the payment is made
     * @param amount the amount to pay towards the credit account balance
     */
    public void pay(Account currentAccount, double amount) {
        System.out.println("Your amount to pay will be: " + amount);
        currentAccount.setBalance(currentAccount.getBalance() - amount);
        setBalance(getBalance() + amount);
        System.out.println("Your new owed balance is: " + getBalance());
    }
}
