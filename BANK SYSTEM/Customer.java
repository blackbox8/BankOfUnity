class Customer extends Person{

    private int customerID;

    public Customer(int Id, String firstName, String lastName, String dob, String address, String phoneNumber){
        super(firstName, lastName, dob, address, phoneNumber);
        this.customerID = Id;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int id){
        this.customerID = id;
    }

    public Customer retrieveCustomerInformation(String fullName){
        return this;
        
    }

}