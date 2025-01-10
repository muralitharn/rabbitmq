package Task.Fruit_Seller.Repository;

import Task.Fruit_Seller.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.currentStock.isStockPresent = true")
    Stream<Product> streamAvailableProducts();

    @Query("SELECT p FROM Product p JOIN p.currentStock cs WHERE cs.isStockPresent = true")
    List<Product> findProductsWithStockPresent();
}
