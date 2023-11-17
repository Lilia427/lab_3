package task1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private final String name;
    private final List<Order> orders;

    public User(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }
    public User getUser(String name, List<User> users) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
    public double calculateExpensesForUser(User user, LocalDate startDate, LocalDate endDate) {
        double totalExpenses = 0.0;

        // Перевірка кожного замовлення користувача
        for (Order order : user.getOrders()) {
            LocalDate orderDate = LocalDate.from(order.getOrderDate());

            // Перевірка чи замовлення відбулося в певний період
            if (orderDate.isAfter(startDate) && orderDate.isBefore(endDate)) {
                totalExpenses += order.getTotalPrice();
            }
        }

        return totalExpenses;
    }
    public Map<String, Integer> getTotalQuantityOfProducts(User user) {
        Map<String, Integer> totalQuantity = new HashMap<>();

        // Перевірка кожного замовлення користувача
        for (Order order : user.getOrders()) {
            List<Product> products = order.getProducts();

            // Перевірка кожного продукту замовлення
            for (Product product : products) {
                String productName = product.getName();
                int quantity = product.getQuantity();

                // Оновлення загальної кількості продукту
                totalQuantity.merge(productName, quantity, Integer::sum);
            }
        }

        return totalQuantity;
    }


}

