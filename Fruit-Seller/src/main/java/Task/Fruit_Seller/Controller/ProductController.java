package Task.Fruit_Seller.Controller;

import Task.Fruit_Seller.Entity.Product;
import Task.Fruit_Seller.Entity.CurrentStock;
import Task.Fruit_Seller.Exceptions.ProductException;
import Task.Fruit_Seller.Repository.currentStockRepository;
import Task.Fruit_Seller.Services.ProductServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/product-api")
public class ProductController {

    @Autowired
    ProductServices ProductServicesCom;

    @Autowired
    currentStockRepository currentStockRepo;

    @GetMapping("/get-all-available-product")
    public ResponseEntity<Map<CurrentStock, Product>> getAllAvailableProducts() throws ProductException, JsonProcessingException, InterruptedException {
        Map<CurrentStock, Product> response = ProductServicesCom.getAllAvailableProduct();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-product/by-name/{ProductName}")
    public ResponseEntity<Product> getProductByName(@PathVariable(required = true) String ProductName) throws ProductException {
        Product ProductIdem = ProductServicesCom.getProductByName(ProductName);
        return new ResponseEntity<>(ProductIdem, HttpStatus.OK);
    }


    @PatchMapping("/update-Stock/{id}")
    public ResponseEntity<String> updateStockDetails(@PathVariable Long id ,@RequestBody CurrentStock stock) throws ProductException, IOException {
        String response = ProductServicesCom.updateStocksBYCache(id,stock);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity<CurrentStock> getStockDetails(@PathVariable Long id) {
        CurrentStock repoStock = ProductServicesCom.getStock(id);
        if (repoStock != null) {
            return new ResponseEntity<>(repoStock, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/product-to-db")                      // summa
    public String product_to_db(@RequestBody Product RequestedProduct) throws ProductException {
        return ProductServicesCom.saveToDB(RequestedProduct) ? "to db is done" : "could not save";
    }
}
