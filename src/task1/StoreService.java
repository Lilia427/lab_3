package task1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreService {
    private final List<Product> products;
    private final List<User> users;

    public StoreService(List<Product> products, List<User> users) {
        this.products = products;
        this.users = users;
    }

    public Product findMostPopularProduct() {
        Map<Product, Long> productCounts = users.stream()
                .flatMap(user -> user.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));

        return productCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public double findMaxRevenueForDay() {
        Map<LocalDateTime, Double> revenueByDay = users.stream()
                .flatMap(user -> user.getOrders().stream())
                .collect(Collectors.groupingBy(Order::getOrderDate,
                        Collectors.summingDouble(order -> order.getTotalPrice())));

        return revenueByDay.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .orElse(0.0);
    }
}
