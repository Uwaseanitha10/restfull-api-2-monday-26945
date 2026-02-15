

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afanyu.question4_ecommerce_api.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "iPhone 15", "Apple Smartphone", 999.99, "Electronics", 50, "Apple"));
        products.add(new Product(2L, "Galaxy S24", "Samsung Smartphone", 899.99, "Electronics", 40, "Samsung"));
        products.add(new Product(3L, "MacBook Air", "Lightweight Laptop", 1199.99, "Computers", 15, "Apple"));
        products.add(new Product(4L, "Dell XPS 13", "Business Laptop", 1050.00, "Computers", 10, "Dell"));
        products.add(new Product(5L, "AirPods Pro", "Wireless Earbuds", 249.00, "Accessories", 100, "Apple"));
        products.add(new Product(6L, "Sony WH-1000XM5", "Noise Cancelling", 350.00, "Accessories", 25, "Sony"));
        products.add(new Product(7L, "Coffee Maker", "Automatic Drip", 85.00, "Appliances", 30, "Keurig"));
        products.add(new Product(8L, "Air Fryer", "Healthy cooking", 120.00, "Appliances", 0, "Ninja"));
        products.add(new Product(9L, "Yoga Mat", "Non-slip grip", 25.00, "Fitness", 60, "Lululemon"));
        products.add(new Product(10L, "Dumbbell Set", "Adjustable weight", 150.00, "Fitness", 5, "Bowflex"));
    }


    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, 
                                        @RequestParam(defaultValue = "10") int limit) {
        int start = Math.min(page * limit, products.size());
        int end = Math.min(start + limit, products.size());
        return products.subList(start, end);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/price-range")
    public List<Product> getByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    @GetMapping("/in-stock")
    public List<Product> getInStock() {
        return products.stream()
                .filter(p -> p.getStockQuantity() > 0)
                .collect(Collectors.toList());
    }

   
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
