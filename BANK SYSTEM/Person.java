abstract class Person{
    private String firstName;
    private String lastName;
    private String DOB;
    private String address;
    private String phoneNumber;

    public Person(String firstName, String lastName, String dob, String address, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //Function for displaying persons information
    public void displayinfo(){
        System.out.println("First name: " + this.firstName +
                            "\nLast name: " + this.lastName + 
                            "\nDate of Birth: " + this.DOB + 
                            "\nAddress: " + this.address + 
                            "\nPhone Number: " + this.phoneNumber);
    }





}