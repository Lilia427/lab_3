package task1;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            FileService fileService = new FileService("C:/lb/");

            User alice = new User("Alice");
            User vika = new User("Vika");
            User ivan = new User("Ivan");
            User maksym = new User("Maksym");

            List<Product> aliceOrders = new ArrayList<>();
            Product p1 = new Product("Apple", "Fruit", 100, 5);
            Product p2 = new Product("Milk", "Dairy", 50, 3);
            Product p3 = new Product("Bread", "Bakery", 30, 2);

            aliceOrders.add(p1);
            aliceOrders.add(p2);
            aliceOrders.add(p3);

            alice.addOrder(new Order(aliceOrders));

            fileService.saveUserPurchaseHistory(alice, "alice_purchase_history.txt");

            List<Product> products = fileService.loadProducts("products.txt");
            Store store = new Store(products);

            store.addUser(alice);
            store.addUser(vika);
            store.addUser(ivan);
            store.addUser(maksym);

            List<Product> vikaOrders = new ArrayList<>();
            Product p6 = new Product("Chicken", "Meat", 200, 1);
            Product p7 = new Product("Potatoes", "Vegetable", 60, 5);

            vikaOrders.add(p6);
            vikaOrders.add(p7);

            vika.addOrder(new Order(vikaOrders));

            List<Product> ivanOrders = new ArrayList<>();
            Product p8 = new Product("Watermelon", "Fruit", 120, 2);

            ivanOrders.add(p8);

            ivan.addOrder(new Order(ivanOrders));

            List<Product> maksymOrders = new ArrayList<>();
            Product p9 = new Product("Beef", "Meat", 250, 3);
            Product p10 = new Product("Cucumbers", "Vegetable", 40, 4);

            maksymOrders.add(p9);
            maksymOrders.add(p10);

            maksym.addOrder(new Order(maksymOrders));

            List<User> users = new ArrayList<>();
            users.add(alice);
            users.add(vika);
            users.add(ivan);
            users.add(maksym);

            StoreService storeService = new StoreService(products, users);

            LocalDate startDate = LocalDate.of(2023, 1, 1);
            LocalDate endDate = LocalDate.now();

            for (User user : users) {
                double expenses = user.calculateExpensesForUser(user, startDate, endDate);
                Map<String, Integer> totalQuantity = user.getTotalQuantityOfProducts(user);

                System.out.println("User: " + user.getName());
                System.out.println("Expenses: " + expenses);
                System.out.println("Total quantity of products:");
                for (Map.Entry<String, Integer> entry : totalQuantity.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println();
            }

            double maxRevenue = storeService.findMaxRevenueForDay();
            Product popularProduct = storeService.findMostPopularProduct();

            System.out.println("Max revenue for a day: " + maxRevenue);
            System.out.println("Most popular product: " + popularProduct.getName());

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
