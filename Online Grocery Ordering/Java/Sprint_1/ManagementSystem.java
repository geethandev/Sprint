import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ManagementSystem {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Add/Update Product");
            System.out.println("4. View Customer Details by Email");
            System.out.println("5. Find Costliest Product");
            System.out.println("6. View Products Sorted by Quantity");
            System.out.println("7. Inactivate Customer Profile");
            System.out.println("8. Activate Customer Profile");
            System.out.println("9. View Customer List by Email Domain");
            System.out.println("10. Calculate Total Price of Selected Products");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    addOrUpdateProduct();
                    break;
                case 4:
                    viewCustomerByEmail();
                    break;
                case 5:
                    findCostliestProduct();
                    break;
                case 6:
                    viewProductsSortedByQuantity();
                    break;
                case 7:
                    inactivateCustomerProfile();
                    break;
                case 8:
                    activateCustomerProfile();
                    break;
                case 9:
                    viewCustomerListByEmailDomain();
                    break;
                case 10:
                    calculateTotalPrice();
                    break;
                case 11:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerCustomer() {
        System.out.print("Enter Customer Name (Max 50 characters): ");
        String name = scanner.nextLine();
        while (name.length() > 50) {
            System.out.print("Name is too long. Enter Customer Name (Max 50 characters): ");
            name = scanner.nextLine();
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        while (!isValidEmail(email) || isEmailUsed(email)) {
            System.out.print("Invalid or already used email. Enter Email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter Password (6-12 characters): ");
        String password = scanner.nextLine();
        while (password.length() < 6 || password.length() > 12) {
            System.out.print("Password length must be between 6 and 12 characters. Enter Password: ");
            password = scanner.nextLine();
        }

        System.out.print("Enter Address (Max 100 characters): ");
        String address = scanner.nextLine();
        while (address.length() > 100) {
            System.out.print("Address is too long. Enter Address (Max 100 characters): ");
            address = scanner.nextLine();
        }

        System.out.print("Enter Contact Number (10 digits): ");
        String contactNumber = scanner.nextLine();
        while (!isValidContactNumber(contactNumber)) {
            System.out.print("Invalid contact number. Enter Contact Number (10 digits): ");
            contactNumber = scanner.nextLine();
        }

        int customerID = Customer.generateCustomerID();

        Customer customer = new Customer(name, email, password, address, contactNumber, customerID);
        customers.add(customer);
        System.out.println("Customer Registration is successful for Customer ID: " + customerID);
    }

    private static void updateCustomer() {
        System.out.print("Enter Email of the customer to update: ");
        String email = scanner.nextLine();
        Customer customer = findCustomerByEmail(email);

        if (customer == null) {
            System.out.println("Customer with the given email does not exist.");
            return;
        }

        System.out.print("Enter new Customer Name (Max 50 characters): ");
        String name = scanner.nextLine();
        while (name.length() > 50) {
            System.out.print("Name is too long. Enter new Customer Name (Max 50 characters): ");
            name = scanner.nextLine();
        }
        customer.setName(name);

        System.out.print("Enter new Password (6-12 characters): ");
        String password = scanner.nextLine();
        while (password.length() < 6 || password.length() > 12) {
            System.out.print("Password length must be between 6 and 12 characters. Enter new Password: ");
            password = scanner.nextLine();
        }
        customer.setPassword(password);

        System.out.print("Enter new Address (Max 100 characters): ");
        String address = scanner.nextLine();
        while (address.length() > 100) {
            System.out.print("Address is too long. Enter new Address (Max 100 characters): ");
            address = scanner.nextLine();
        }
        customer.setAddress(address);

        System.out.print("Enter new Contact Number (10 digits): ");
        String contactNumber = scanner.nextLine();
        while (!isValidContactNumber(contactNumber)) {
            System.out.print("Invalid contact number. Enter new Contact Number (10 digits): ");
            contactNumber = scanner.nextLine();
        }
        customer.setContactNumber(contactNumber);

        System.out.println("Customer details updated successfully.");
    }

    private static void addOrUpdateProduct() {
        // Placeholder for adding or updating product details
        System.out.println("Add/Update Product functionality can be implemented here.");
    }

    private static void viewCustomerByEmail() {
        System.out.print("Enter Email of the customer to view details: ");
        String email = scanner.nextLine();
        Customer customer = findCustomerByEmail(email);

        if (customer == null) {
            System.out.println("Customer with the given email does not exist.");
        } else {
            System.out.println("Customer Details:");
            System.out.println(customer);
        }
    }

    private static void findCostliestProduct() {
        // Placeholder for finding the costliest product
        System.out.println("Find Costliest Product functionality can be implemented here.");
    }

    private static void viewProductsSortedByQuantity() {
        // Placeholder for viewing products sorted by quantity
        System.out.println("View Products Sorted by Quantity functionality can be implemented here.");
    }

    private static void inactivateCustomerProfile() {
        System.out.print("Enter Email of the customer to inactivate profile: ");
        String email = scanner.nextLine();
        Customer customer = findCustomerByEmail(email);

        if (customer == null) {
            System.out.println("Customer with the given email does not exist.");
            return;
        }

        customer.setActive(false);
        System.out.println("Customer profile inactivated successfully.");
    }

    private static void activateCustomerProfile() {
        System.out.print("Enter Email of the customer to activate profile: ");
        String email = scanner.nextLine();
        Customer customer = findCustomerByEmail(email);

        if (customer == null) {
            System.out.println("Customer with the given email does not exist.");
            return;
        }

        customer.setActive(true);
        System.out.println("Customer profile activated successfully.");
    }

    private static void viewCustomerListByEmailDomain() {
        System.out.print("Enter Email Domain (e.g., gmail.com, yahoo.com): ");
        String emailDomain = scanner.nextLine();
        ArrayList<Customer> matchingCustomers = findCustomersByEmailDomain(emailDomain);

        if (matchingCustomers.isEmpty()) {
            System.out.println("No customers registered with the domain: " + emailDomain);
        } else {
            matchingCustomers.sort(Comparator.comparingInt("Customer:"+getCustomerID()));
            System.out.println("Customer List for Domain " + emailDomain + ":");
            for (Customer customer : matchingCustomers) {
                System.out.println(customer);
                System.out.println("--------------");
            }
        }
    }

    private static void calculateTotalPrice() {
        // Placeholder for calculating total price of selected products
        System.out.println("Calculate Total Price functionality can be implemented here.");
    }

    private static Customer findCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        return null;
    }

    private static boolean isValidEmail(String email) {
        // Basic email validation
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private static boolean isEmailUsed(String email) {
        // Check if email is already used by another customer
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidContactNumber(String contactNumber) {
        // Basic contact number validation
        return contactNumber.matches("\\d{10}");
    }

    private static ArrayList<Customer> findCustomersByEmailDomain(String emailDomain) {
        ArrayList<Customer> matchingCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            String[] parts = customer.getEmail().split("@");
            if (parts.length == 2 && parts[1].equalsIgnoreCase(emailDomain)) {
                matchingCustomers.add(customer);
            }
        }
        return matchingCustomers;
    }
}
