package task1;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final List<Product> products;
    private double totalPrice;
    private final LocalDateTime orderDate;

    public Order(List<Product> products) {
        this.products = products;
        this.totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
        this.orderDate = LocalDateTime.now();
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void addProduct(Product product) {
        products.add(product);
        totalPrice += product.getPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        totalPrice -= product.getPrice();
    }

    public int getProductCount() {
        return products.size();
    }

    public Product getProductAtIndex(int index) {
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder("Order details:\n");
        for (Product product : products) {
            details.append(product.toString()).append("\n");
        }
        details.append("Total Price: ").append(totalPrice);
        return details.toString();
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
