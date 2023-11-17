package task1;

public class Product {
    private final String name;
    private final String type;
    private double price;
    private int quantity;

    public Product(String name, String type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        checkAndAddPackage();
        generateComment();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
        checkAndAddPackage();
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    private void checkAndAddPackage() {
        if (type.equalsIgnoreCase("Vegetable") || type.equalsIgnoreCase("Fruit")) {
            System.out.println("Adding a package for " + name);

        }
    }

    private void generateComment() {
        if (type.equalsIgnoreCase("Meat") || type.equalsIgnoreCase("Fish")) {
            System.out.println("Don't forget to store the items {" + name + "} in the refrigerator.");

        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
