public abstract class Account{
    private int accountNumber;
    private double balance;
    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract void displayBalance();
    public Account(){}
    public Account(int accountNumber, double accountBalance){
        this.accountNumber = accountNumber;
        this.balance = accountBalance;
    }
    public double getBalance() {
        return this.balance;
    }
    public int getAccountNumber() {
        return this.accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

}