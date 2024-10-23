class Credit extends Account{
    private double maxBalance;

    public Credit(){
        
    }
    public Credit(int accountNumber, int maxBalance, double startBalance){
        
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