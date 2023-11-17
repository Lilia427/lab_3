package task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private final List<Product> products;
    private final List<User> users;

    public Store(List<Product> products) {
        this.products = products;
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUser(String name) {
        return this.users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Order createOrder(String userName, List<Product> products) {
        User user = getUser(userName);
        if (user != null) {
            Order order = new Order(products);
            user.addOrder(order);
            return order;
        }
        return null;
    }

    public List<Product> filterAndSortProducts(String warehouse) {
        return this.products.stream()
                .filter(product -> product.getType().equals(warehouse))
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public double calculateAveragePrice() {
        double totalPrices = products.stream().mapToDouble(Product::getPrice).sum();
        return products.isEmpty() ? 0.0 : totalPrices / products.size();
    }

    public double calculateTotalCost(String userName, int days) {
        User user = getUser(userName);
        if (user != null) {
            return user.getOrders().stream().mapToDouble(Order::getTotalPrice).sum() * days;
        }
        return 0.0;
    }
}
