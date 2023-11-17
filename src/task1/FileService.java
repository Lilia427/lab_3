package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private final String basePath;

    public FileService(String basePath) {
        this.basePath = basePath;
    }
    public void saveUserPurchaseHistory(User user, String fileName) {
    }
    public List<Product> loadProducts(String fileName) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(basePath + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productInfo = line.split(",");
                Product product = new Product(productInfo[0], productInfo[1], Double.parseDouble(productInfo[2]), Integer.parseInt(productInfo[3]));
                products.add(product);
            }
        }
        return products;
    }
}
