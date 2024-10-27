/**
 * The Account class is an abstract representation of a bank account.
 * It provides the basic structure for accounts with account numbers and balances,
 * and it requires subclasses to implement specific behaviors such as depositing,
 * withdrawing, displaying balances, logging transactions, and getting credit limits.
 * 
 * This class cannot be instantiated directly; it must be subclassed to provide
 * concrete implementations for its abstract methods.
 */
public abstract class Account{
    /** The account number associated with the bank account */
    private int accountNumber;
    /** The current balance of the bank account */
    private double balance;

    /**
     * Default constructor for Account class.
     * Initializes a default account with no parameters.
     */
    public Account(){}

    /**
     * Constructs an Account object with the specified account number and initial balance.
     * 
     * @param accountNumber The unique number identifying the account.
     * @param accountBalance The initial balance of the account.
     */
    public Account(int accountNumber, double accountBalance){
        this.accountNumber = accountNumber;
        this.balance = accountBalance;
    }

    /**
     * Gets the current balance of the account.
     * 
     * @return The current balance of the account.
     */
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * Gets the account number of the account.
     * 
     * @return The account number.
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Sets the account number of the account.
     * 
     * @param accountNumber The new account number.
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Sets the balance of the account.
     * 
     * @param balance The new balance of the account.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Abstract method for depositing an amount into the account.
     * 
     * @param amount The amount to be deposited.
     */
    public abstract void deposit(double amount);

    /**
     * Abstract method for withdrawing an amount from the account.
     * This method returns a boolean value indicating whether the
     * withdrawal was successful.
     * 
     * @param amount The amount to be withdrawn.
     * @return true if the withdrawal is successful, false otherwise.
     */
    public abstract boolean withdraw(double amount);

    /**
     * Abstract method for displaying the current balance of the account.
     */
    public abstract void displayBalance();

    /**
     * Abstract method for logging the account details to a text file.
     * 
     * @param customer The customer associated with this account.
     */
    public abstract void toLogtxt(Person customer);

    /**
     * Abstract method to get the credit limit of the account.
     * This will be relevant for credit accounts.
     * 
     * @return The credit limit of the account.
     */
    public abstract double getLimit();
    

}