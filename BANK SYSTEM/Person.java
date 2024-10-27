/**
 * Abstract class representing a person.
 * This class contains common attributes and methods associated with a person, 
 * such as their name, date of birth, address, and phone number.
 */
abstract class Person {
    
    private String firstName; // The first name of the person
    private String lastName;  // The last name of the person
    private String DOB;       // The date of birth of the person
    private String address;   // The address of the person
    private String phoneNumber; // The phone number of the person

    /**
     * Constructs a Person with specified attributes.
     *
     * @param firstName   The first name of the person.
     * @param lastName    The last name of the person.
     * @param dob         The date of birth of the person.
     * @param address     The address of the person.
     * @param phoneNumber  The phone number of the person.
     */
    public Person(String firstName, String lastName, String dob, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Abstract method to get the customer ID.
     * This method should be implemented by subclasses to return the customer's ID.
     *
     * @return the customer ID
     */
    abstract int getCustomerID();

    /**
     * Gets the first name of the person.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the date of birth of the person.
     *
     * @return the date of birth
     */
    public String getDOB() {
        return this.DOB;
    }

    /**
     * Gets the address of the person.
     *
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Gets the phone number of the person.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Displays the person's information.
     */
    public void displayinfo() {
        System.out.println("First name: " + this.firstName +
                           "\nLast name: " + this.lastName + 
                           "\nDate of Birth: " + this.DOB + 
                           "\nAddress: " + this.address + 
                           "\nPhone Number: " + this.phoneNumber);
    }
}
