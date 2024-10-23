class Checkings extends Account{

    public Checkings(){}
    public Checkings(int accountNumber, double accountBalance){
        super(accountNumber, accountBalance);

    }

    public void displayBalance(){
        System.out.println(getBalance());
    }
    public void deposit(double amount){
       setBalance(getAccountNumber() + amount);
    }
    public boolean withdraw(double amount){
        return true;
    }
}