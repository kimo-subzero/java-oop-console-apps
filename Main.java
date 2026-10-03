import java.util.ArrayList;
import java.util.List;

// Main Class
public class Main {

    // Internal Product Class
    static class StoreProduct {
        private int id;
        private String name;
        private double price;
        private int stock;

        public StoreProduct(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }

        public void setPrice(double price) {
            if (price < 0) {
                System.out.println("Error: Price cannot be negative!");
            } else {
                this.price = price;
            }
        }

        public void reduceStock(int quantity) {
            if (quantity <= stock) {
                this.stock -= quantity;
            }
        }
    }

    // Internal Customer Class
    static class StoreCustomer {
        private int id;
        private String name;
        private String email;

        public StoreCustomer(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }

        public void placeOrder(String item, int quantity, StoreProduct product) {
            product.reduceStock(quantity);
            System.out.println(name + " placed an order for " + quantity + "x " + item);
            System.out.println("Remaining Stock: " + product.getStock());
        }

        public void placeOrder(int quantity, int productId) {
            System.out.println(name + " placed an order for " + quantity + " item of Product ID: " + productId);
        }
    }

    // Internal Admin Class
    static class StoreAdmin {
        private int id;
        private String name;

        public StoreAdmin(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getUserDetails() {
            return "[Admin Account] ID: " + id + " | Name: " + name + " (System Administrator)";
        }

        public void updateProduct(StoreProduct product, String newName, double newPrice, int newStock) {
            System.out.println("Admin " + name + " updated product: " + newName);
            product.setPrice(newPrice);
        }
    }

    // Interface & Payment Processors
    interface ProcessablePayment {
        void processPayment(double amount);
    }

    static class CardPayment implements ProcessablePayment {
        private String cardEnding;

        public CardPayment(String cardEnding) {
            this.cardEnding = cardEnding;
        }

        @Override
        public void processPayment(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card ending in " + cardEnding);
        }
    }

    static class OnlinePayment implements ProcessablePayment {
        private String email;

        public OnlinePayment(String email) {
            this.email = email;
        }

        @Override
        public void processPayment(double amount) {
            System.out.println("Paid $" + amount + " using PayPal account: " + email);
        }
    }

    public static void main(String[] args) {
        // 1. Initialize Users with name "Karim"
        StoreCustomer customer = new StoreCustomer(1, "Karim", "karim@example.com");
        StoreAdmin admin = new StoreAdmin(2, "Ahmed_Admin");

        // Display Profiles
        System.out.println("[Customer Profile] ID: " + customer.getId() + " | Name: " + customer.getName() + " | Email: " + customer.getEmail());
        System.out.println(admin.getUserDetails());
        System.out.println("----------------------------------");

        // 2. Product Initialization
        StoreProduct laptop = new StoreProduct(101, "Gaming Laptop", 1000.0, 10);

        // 3. Orders
        customer.placeOrder("Laptop", 2, laptop);
        customer.placeOrder(1, 101);
        customer.placeOrder(3, 101);

        System.out.println("----------------------------------");

        // 4. Admin Operations & Validation
        admin.updateProduct(laptop, "Gaming Laptop", 1100.0, 8);
        System.out.println("Updated Price: $" + laptop.getPrice());
        laptop.setPrice(-50.0);

        System.out.println("----------------------------------");

        // 5. Payment
        ProcessablePayment creditCard = new CardPayment("5678");
        ProcessablePayment paypal = new OnlinePayment("karim@example.com");

        creditCard.processPayment(1100.0);
        paypal.processPayment(250.0);
    }
}