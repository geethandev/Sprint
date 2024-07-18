import java.util.Random;

public class Customer {
    private String name;
    private String email;
    private String password;
    private String address;
    private String contactNumber;
    private int customerID;
    private boolean active; // Added for status

    public Customer(String name, String email, String password, String address, String contactNumber, int customerID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
        this.customerID = customerID;
        this.active = true; // Initially active
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerID +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nAddress: " + address +
                "\nContact Number: " + contactNumber;
    }

    // Utility method to generate random customer ID
    public static int generateCustomerID() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }
}
