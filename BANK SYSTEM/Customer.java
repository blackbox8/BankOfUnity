/**
 * Represents a customer, extending the Person class.
 * This class contains customerID.
 */
class Customer extends Person {

    private int customerID; // The unique identification number of the customer

    /**
     * Constructs a Customer with the specified details.
     *
     * @param id           The unique identification number of the customer.
     * @param firstName    The first name of the customer.
     * @param lastName     The last name of the customer.
     * @param dob          The date of birth of the customer.
     * @param address      The address of the customer.
     * @param phoneNumber   The phone number of the customer.
     */
    public Customer(int Id, String firstName, String lastName, String dob, String address, String phoneNumber) {
        super(firstName, lastName, dob, address, phoneNumber);
        this.customerID = Id;
    }

    /**
     * Gets the unique identification number of the customer.
     *
     * @return the customer ID
     */
    public int getCustomerID() {
        return this.customerID;
    }

    /**
     * Sets the unique identification number of the customer.
     *
     * @param id the new customer ID
     */
    public void setCustomerID(int id) {
        this.customerID = id;
    }

    /**
     * Retrieves customer information based on the provided full name.
     * 
     * @param fullName the full name of the customer
     * @return the current Customer instance
     */
    public Customer retrieveCustomerInformation(String fullName) {
        return this;
    }
}
