import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
class RunBank{
    static Scanner scan = new Scanner(System.in); //Static Scanner (Can be used in any function)
    static String filePath = "/Users/karlamonroy/Downloads/BANK SYSTEM/CS 3331 - Bank Users(1).csv"; //Static filepath (Can be used anywhere)(Change filepath to yours)
    static Person customer;
    static Account checkings,savings,credit;

    public static void main(String[] args) {
        
        //Requesting their name
       requestName();
       //Display menu if option 
       menu(customer,  checkings, savings, credit);
    }
    


    //menu to display options
    public static void menu(Person customer,  Account checkings, Account savings, Account credit){
        int input;
        do{
            System.out.println("-----------MENU----------");
            System.out.println("1. View account Balance");
            System.out.println("2. Exit");
            System.out.println("Enter an option: ");
            input = scan.nextInt();
            if(input == 1){
                accountBalanceMenu( customer,   checkings,  savings, credit);
            }
            if(input != 1 && input != 2){
                System.out.println("Invalid option, try again");
            }
        }while(input != 2);
        System.exit(0);
    }

    //Menu for displaying account balance
    public static void accountBalanceMenu(Person customer,  Account checkings, Account savings, Account credit){
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
            }else if(input == 2){
                savings.displayBalance();
            }else if(input == 3){
                credit.displayBalance();
            }
        }while(input != 4);
    }


    //Function for requesting their name
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

    





    //Function for finding username
    public static boolean extractingUsernameInformation(String userInput){
        String[] fullName = userInput.trim().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        
        try (Scanner scanner = new Scanner(new File(filePath))){

            scanner.useDelimiter("\n");
            while(scanner.hasNext()){
                String line = scanner.next();
                String[] values = line.split(","); //Extracting values   ??????????ERRORRRRRR??????
                System.out.println(values[4] +" "+ values[5] + values[6]);
                if(values[1].equals(firstName) && values[2].equals(lastName)){    //Comparing names
                    System.out.println("Welcome " + firstName);
                    /* Set Up a new curstomer and their account */
                    System.out.println(values[0] + values[4] + " " + values[5]);
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
}