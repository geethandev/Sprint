import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Product to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. Update Product Quantity");
            System.out.println("5. Clear Cart");
            System.out.println("6. Calculate Total Price");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProductToCart();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    updateProductQuantity();
                    break;
                case 5:
                    clearCart();
                    break;
                case 6:
                    calculateTotalPrice();
                    break;
                case 7:
                    checkout();
                    break;
                case 8:
                    System.out.println("Exiting the shopping cart.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProductToCart() {
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();

        Product product = new Product(name, price, quantity);
        products.add(product);

        System.out.println("Product added to cart.");
    }

    private static void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            double total = 0.0;
            for (int i = 0; i < products.size(); i++) {
                System.out.println("Index: " + i + " - " + products.get(i));
                total += products.get(i).getPrice() * products.get(i).getQuantity();
            }
            System.out.println("Total Price: $" + total);
        }
    }

    private static void removeProductFromCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to remove.");
            return;
        }

        System.out.print("Enter the index of the product to remove: ");
        int index = scanner.nextInt();

        if (index < 0 || index >= products.size()) {
            System.out.println("Invalid index. Please enter a valid index.");
            return;
        }

        Product removedProduct = products.remove(index);
        System.out.println("Removed from cart: " + removedProduct.getName());
    }

    private static void updateProductQuantity() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to update.");
            return;
        }

        System.out.print("Enter the index of the product to update quantity: ");
        int index = scanner.nextInt();

        if (index < 0 || index >= products.size()) {
            System.out.println("Invalid index. Please enter a valid index.");
            return;
        }

        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();
        products.get(index).setQuantity(newQuantity);

        System.out.println("Quantity updated successfully.");
    }

    private static void clearCart() {
        products.clear();
        System.out.println("Cart cleared successfully.");
    }

    private static void calculateTotalPrice() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        System.out.println("Total Price of Cart: $" + total);
    }

    private static void checkout() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
        } else {
            System.out.println("Checkout successful.");
            products.clear();
        }
    }
}
