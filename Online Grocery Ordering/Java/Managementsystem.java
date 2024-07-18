import java.util.*;

class Customer {
    private String name;
    private String email;
    private String password;
    private String address;
    private String contactNumber;
    private int customerID;

    public Customer(String name, String email, String password, String address, String contactNumber, int customerID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
        this.customerID = customerID;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerID + ", Name=" + name + ", Email=" + email + ", Address=" + address + ", Contact Number=" + contactNumber + "]";
    }
}

class Product {
    private String productId;
    private String name;
    private String description;
    private int quantity;
    private double price;

    public Product(String productId, String name, String description, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + name + ", Description=" + description + ", Quantity=" + quantity + ", Price=" + price + "]";
    }
}

public class Managementsystem {

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Add Product");
            System.out.println("4. Update Product");
            System.out.println("5. View Customer Details");
            System.out.println("6. Find Costliest Product");
            System.out.println("7. View Products Sorted by Quantity");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    addProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    viewCustomerDetails();
                    break;
                case 6:
                    findCostliestProduct();
                    break;
                case 7:
                    viewProductsSortedByQuantity();
                    break;
                case 8:
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

        int customerID = generateCustomerID();

        Customer customer = new Customer(name, email, password, address, contactNumber, customerID);
        customers.add(customer);
        System.out.println("Customer Registration is successful for " + customerID);
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

        System.out.println("Your Details updated successfully.");
    }

    private static void addProduct() {
        String productId = generateProductId();

        System.out.print("Enter Product Name (Max 50 characters): ");
        String name = scanner.nextLine();
        while (name.length() > 50) {
            System.out.print("Name is too long. Enter Product Name (Max 50 characters): ");
            name = scanner.nextLine();
        }

        System.out.print("Enter Product Description (Max 100 characters): ");
        String description = scanner.nextLine();
        while (description.length() > 100) {
            System.out.print("Description is too long. Enter Product Description (Max 100 characters): ");
            description = scanner.nextLine();
        }

        System.out.print("Enter Available Quantities: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Price (up to 2 decimal points): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Product product = new Product(productId, name, description, quantity, price);
        products.add(product);
        System.out.println("Product added successfully.");
    }

    private static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        String productId = scanner.nextLine();
        Product product = findProductById(productId);

        if (product == null) {
            System.out.println("Product with the given ID does not exist.");
            return;
        }

        System.out.print("Enter new Product Name (Max 50 characters): ");
        String name = scanner.nextLine();
        while (name.length() > 50) {
            System.out.print("Name is too long. Enter new Product Name (Max 50 characters): ");
            name = scanner.nextLine();
        }
        product.setName(name);

        System.out.print("Enter new Product Description (Max 100 characters): ");
        String description = scanner.nextLine();
        while (description.length() > 100) {
            System.out.print("Description is too long. Enter new Product Description (Max 100 characters): ");
            description = scanner.nextLine();
        }
        product.setDescription(description);

        System.out.print("Enter new Available Quantities: ");
        int quantity = scanner.nextInt();
        product.setQuantity(quantity);

        System.out.print("Enter new Price (up to 2 decimal points): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        product.setPrice(price);

        System.out.println("Product details updated successfully.");
    }

    private static void viewCustomerDetails() {
        System.out.print("Enter Email of the customer: ");
        String email = scanner.nextLine();
        Customer customer = findCustomerByEmail(email);

        if (customer == null) {
            System.out.println("No Such Customer Exist with the Given Email");
        } else {
            System.out.println(customer);
        }
    }

    private static void findCostliestProduct() {
        if (products.isEmpty()) {
            System.out.println("Product List is Empty");
            return;
        }

        Product costliestProduct = products.get(0);
        for (Product product : products) {
            if (product.getPrice() > costliestProduct.getPrice()) {
                costliestProduct = product;
            }
        }

        System.out.println("The costliest product is: " + costliestProduct);
    }

    private static void viewProductsSortedByQuantity() {
        if (products.isEmpty()) {
            System.out.println("Product List is Empty");
            return;
        }

        ArrayList<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparingInt(Product::getQuantity));

        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    private static boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private static boolean isEmailUsed(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidContactNumber(String contactNumber) {
        return contactNumber.matches("\\d{10}");
    }

    private static int generateCustomerID() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    private static String generateProductId() {
        Random random = new Random();
        return (random.nextInt(9) + 1) + "-" +
                (random.nextInt(9000) + 1000) + "-" +
                (random.nextInt(9000) + 1000) + "-" +
                (random.nextInt(9) + 1);
    }

    private static Customer findCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    private static Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
}

