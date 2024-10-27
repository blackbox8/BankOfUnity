import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * The RunBank class simulates a simple bank system where users can 
 * view their account balances, perform transactions, and update the system's CSV file.
 */
class RunBank{
    static Scanner scan = new Scanner(System.in); //Static Scanner (Can be used in any function)
    static String filePath = "/Users/manny517/Desktop/BANK SYSTEM/CS 3331 - Bank Users(1).csv"; //Static filepath (Can be used anywhere)(Change filepath to yours)
    static Person customer;
    static Account checkings,savings,credit;

    /**Customer 2 will be used for transfering money
     * between customers
     */
    static Person customer2;
    static Account checkings2,savings2,credit2;

    /**
     * Main method that starts the bank application.
     * It requests the user's name and displays the main menu.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        requestName();//Requesting their name
        menu();//Display menu if optio
    }
    
    /**
     * Displays the main menu allowing the user to view account balance or exit.
     */
    public static void menu(){
        int input;
        do{
            System.out.println("-----------MENU----------");
            System.out.println("1. View account Balance");
            System.out.println("2. Exit account");
            System.out.println("Enter an option: ");
            input = scan.nextInt();
            if(input == 1){
                accountBalanceMenu();
            }
            if(input != 1 && input != 2){
                System.out.println("Invalid option, try again");
            }
        }while(input != 2);
        System.exit(0);
    }

    /**
     * Displays a menu for selecting which account's balance to view.
     * Depending on the input, the balance will be displayed and
     * prompted to another function for further interaction with the specific account.
     */
    public static void accountBalanceMenu(){
        int input;
        do{
            System.out.println("Which account balance would you like to view?");
            System.out.println("1. Checkings");
            System.out.println("2. Savings");
            System.out.println("3. Credit");
            System.out.println("4. Return");
            input = scan.nextInt();
            if(input == 1){
                checkings.displayBalance();
                checkings.toLogtxt(customer);
                transactionMenu(checkings);
            }else if(input == 2){
                savings.displayBalance();
                savings.toLogtxt(customer);
                transactionMenu(savings);
            }else if(input == 3){
                credit.displayBalance();
                credit.toLogtxt(customer);
                transactionMenu(credit);
            }
        }while(input != 4);
    }

    /**
     * Displays a menu for selecting a transaction (deposit, withdraw, transfer, etc.)
     * from the account in which is currently being viewed
     * Depending on the input the user chooses, the current account being viewed 
     * will be modified.
     * @param currentAccount the account on which the transaction is performed
     */
    public static void transactionMenu(Account currentAccount){
        int input;
        do{
            System.out.println("Which transaction would you like to complete?");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Pay credit card");
            System.out.println("5. Return");
            input = scan.nextInt();
            if(input != 5){
                System.out.println("Enter your amount: ");
                double amount = scan.nextDouble();
                if(input == 1){
                    currentAccount.deposit(amount);
                    updateCSV(customer,checkings,savings,credit);
                }else if(input == 2){
                    currentAccount.withdraw(amount);
                    updateCSV(customer,checkings,savings,credit);
                }else if(input == 3){
                    transferMenu(currentAccount, amount);
                }else if(input == 4){
                    if (currentAccount instanceof Credit) {
                    Credit creditAccount = (Credit) currentAccount;  // ClassCastException occurs here if currentAccount is not Credit
                    creditAccount.pay(currentAccount, amount);
                    }else{
                        System.out.println("Error: you are not currently viewing your credit card account.");
                    }
                }
            }
        }while(input != 5);
    }

    
    /**
     * Displays the menu for transferring money between accounts or to another person.
     * Depending on user input, money will be transfered from their current account being viewed, 
     * to an account of their choice. If transfering money to another person, money will be transfered
     * to their checkings account by default.
     * @param currentAccount the account from which the transfer is initiated
     * @param amount the amount to be transferred
     */
    public static void transferMenu(Account currentAccount, double amount){
        int input;
        do{
            System.out.println("Which account would you like to transfer to?");
            System.out.println("1. Checkings");
            System.out.println("2. Savings");
            System.out.println("3. Transfer to someone");
            System.out.println("4. Return");
            input = scan.nextInt();
            if(input == 1){
                currentAccount.withdraw(amount);
                checkings.deposit(amount);
                updateCSV(customer,checkings,savings,credit);
            }else if(input == 2){
                currentAccount.withdraw(amount);
                savings.deposit(amount);
                updateCSV(customer,checkings,savings,credit);
            }else if(input == 3){
                transferProcess(currentAccount, amount);
                updateCSV(customer,checkings,savings,credit);
                updateCSV(customer2,checkings2,savings2,credit2);
            }
        }while(input != 4);
    }
    
    /**
     * Performs the process of transferring money to another person's account. Utilizing methods
     * and object creations of transfer class for documentation purposes onto a txt file.
     * @param currentAccount the account from which the money is being transferred
     * @param amount the amount to be transferred
     */
    public static void transferProcess(Account currentAccount, double amount){
        boolean response;
        do{
            System.out.println("Who would you like to send to: ");
            scan.nextLine();  //Clearing buffer
            String input = scan.nextLine();
            response = personVerification(input);
            if(!response){
                System.out.println("Person not found. Try again.");
            }else{
                Transfer transfer = new Transfer(amount, customer, customer2,currentAccount, checkings2);
                transfer.performTransfer();
                transfer.toLogtxt();
            }
        }while(!response);
    }

    /**
     * Requests the user's name and verifies their existence in the system.
     *
     * @return true if the user is found, false otherwise
     */
    public static boolean requestName(){
        boolean response;
        do{
            System.out.println("Enter your first and last name: ");
            String userInput = scan.nextLine();
            response = extractingUsernameInformation(userInput);
            if(response == false){
                System.out.println("Customer not found. Please try again.");
            }
        }while(response == false);
        return response;
    }

    /**
     * Extracts the user's information from the CSV file and sets up their account.
     * This method will primarily be used to extract information for the primary user
     * @param userInput the full name of the user
     * @return true if the user is found, false otherwise
     */
    public static boolean extractingUsernameInformation(String userInput){
        String[] fullName = userInput.trim().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        
        try (Scanner scanner = new Scanner(new File(filePath))){

            scanner.useDelimiter("\n");
            while(scanner.hasNext()){
                String line = scanner.next();
                String[] values = line.split(","); 
                if(values[1].equals(firstName) && values[2].equals(lastName)){    //Comparing names
                    System.out.println("Welcome " + firstName);
                    /* Set Up a new curstomer and their account */
                    customer = new Customer(Integer.parseInt(values[0]),values[1],values[2],values[3],values[4] + " "+ values[5] + values[6],values[7]);
                    checkings = new Checkings(Integer.parseInt(values[8]),Double.parseDouble(values[9]));
                    savings = new Saving(Integer.parseInt(values[10]),Double.parseDouble(values[11]));
                    credit = new Credit(Integer.parseInt(values[12]),Integer.parseInt(values[13]),Double.parseDouble(values[14]));
                    
                    return true;
                }
            }
            return false;


        }catch(FileNotFoundException e){
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return false;
    }

    /**
     * Verifies if a person exists in the CSV file and sets up their account information.
     * This method will be used to set up customer #2 for purpose of transfering money onto another person
     * @param userInput the full name of the person to verify
     * @return true if the person is found, false otherwise
     */
    public static boolean personVerification(String userInput){
        String[] fullName = userInput.trim().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        
        try (Scanner scanner = new Scanner(new File(filePath))){

            scanner.useDelimiter("\n");
            while(scanner.hasNext()){
                String line = scanner.next();
                String[] values = line.split(","); 
                if(values[1].equals(firstName) && values[2].equals(lastName)){    //Comparing names
                    /* Set Up a new curstomer and their account */
                    customer2 = new Customer(Integer.parseInt(values[0]),values[1],values[2],values[3],values[4] + " "+ values[5] + values[6],values[7]);
                    checkings2 = new Checkings(Integer.parseInt(values[8]),Double.parseDouble(values[9]));
                    savings2 = new Saving(Integer.parseInt(values[10]),Double.parseDouble(values[11]));
                    credit2 = new Credit(Integer.parseInt(values[12]),Integer.parseInt(values[13]),Double.parseDouble(values[14]));
                    
                    return true;
                }
            }
            return false;


        }catch(FileNotFoundException e){
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return false;
    }

    /**
     * Updates the CSV file with the latest account information for a specific customer.
     * 
     * The method reads the CSV file, finds the customer by their ID, updates the customer's
     * information (checking, savings, and credit account balances), and writes the updated content
     * back to the CSV file.
     * 
     * @param customer The customer whose account information is being updated.
     * @param checking The checking account of the customer.
     * @param saving The savings account of the customer.
     * @param credit The credit account of the customer.
     */
    public static void updateCSV(Person customer, Account checking, Account saving, Account credit) {
        StringBuilder fileContent = new StringBuilder();
        boolean updated = false;
        String fileName = filePath;

        //Reading and modifitying content
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean headerWritten = false;

            while ((line = reader.readLine()) != null) {
                // Check and writing the header once
                if (!headerWritten) {
                    fileContent.append("Identification Number,First Name,Last Name,Date of Birth,Address,Phone Number," +
                                    "Checking Account Number,Checking Starting Balance,Savings Account Number," +
                                    "Savings Starting Balance,Credit Account Number,Credit Max,Credit Starting Balance\n");
                    headerWritten = true; // Mark header as written
                } else {
                    String[] values = line.split(",");
                    int currentID = Integer.parseInt(values[0]);

                    // Update the customer if ID matches
                    if (currentID == customer.getCustomerID()) {
                        line = customer.getCustomerID() + "," +
                            customer.getFirstName() + "," +
                            customer.getLastName() + "," +
                            customer.getDOB() + "," +
                            customer.getAddress() + "," +  
                            customer.getPhoneNumber() + "," +
                            checking.getAccountNumber() + "," +
                            checking.getBalance() + "," +
                            saving.getAccountNumber() + "," +
                            saving.getBalance() + "," +
                            credit.getAccountNumber() + "," +
                            credit.getLimit() + "," +
                            credit.getBalance(); // Updating line line
                        updated = true; // Mark as updated
                    }

                    fileContent.append(line).append("\n"); // Appending line
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        //Transfering updated content to csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(fileContent.toString()); // Writing all content at once
            System.out.println(updated ? "CSV file updated successfully." : "No updates made for ID: " + customer.getCustomerID());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }



}