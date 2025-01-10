package Task.Fruit_Seller.Services;


import Task.Fruit_Seller.Entity.Product;
import Task.Fruit_Seller.Entity.CurrentStock;
import Task.Fruit_Seller.Exceptions.ProductException;
import Task.Fruit_Seller.Repository.ProductRepository;
import Task.Fruit_Seller.Repository.currentStockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ProductServices {

    @Autowired
    ProductRepository ProductRepo;

    @Autowired
    currentStockRepository currentStockRepo;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @Transactional
    public Map<CurrentStock, Product> getAllAvailableProduct() throws ProductException, JsonProcessingException, InterruptedException {

        Map<CurrentStock, Product> availableProductMap = new HashMap<>();
        List<CurrentStock> availableStocks = currentStockRepo.findByIsStockPresentTrue();
        for (CurrentStock availableStock : availableStocks) {
            String redisKey = "stock" + availableStock.getId();
            if (redisTemplate.hasKey(redisKey)) {
                String value = redisTemplate.opsForValue().get(redisKey);
                CurrentStock redisStock = new ObjectMapper().readValue(value, CurrentStock.class);
                availableProductMap.put(redisStock, availableStock.getProduct());
                continue;
            }
            availableProductMap.put(availableStock, availableStock.getProduct());
        }

        if (availableProductMap.isEmpty()) {
            throw new ProductException(" no product available in mart");
        }
        return availableProductMap;
    }

    public CurrentStock getStock(Long id) {
        if (redisTemplate.hasKey("stock" + id)) {
            Object obj = redisTemplate.opsForValue().get("stock" + id);
            CurrentStock redisStock = (CurrentStock) obj;
            return redisStock;
        }
        CurrentStock stock = currentStockRepo.findById(id).get();
        return stock;
    }

    public Product getProductByName(String productName) throws ProductException {
        Product totalProduct = ProductRepo.findByProductName(productName);
        if (Objects.isNull(totalProduct)) {                               // waste
            throw new ProductException(" no such product available ");
        }
        return totalProduct;
    }

    public boolean saveToDB(Product product) throws ProductException {
        if (product.getCurrentStock() != null) {
            CurrentStock stock = product.getCurrentStock();
            stock.setProduct(product);
        }
        boolean response = (Objects.isNull(ProductRepo.save(product))) ? false : true;
        if (response == false) {
            throw new ProductException("put the json properly || data not saved");
        }

        return response;
    }

    public String updateStocksBYCache(long id, CurrentStock stock) throws ProductException, IOException {
        CurrentStock updatedCurrentStock = currentStockRepo.findById(id).get();
        updatedCurrentStock.setInStockAvailability(stock.getInStockAvailability());
        updatedCurrentStock.setProduct(stock.getProduct());
        String value = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(updatedCurrentStock);
        if (redisTemplate.hasKey("stock" + updatedCurrentStock.getId())) {
            redisTemplate.delete("stock" + updatedCurrentStock.getId());
        }
        redisTemplate.opsForValue().set("stock" + updatedCurrentStock.getId(), value);
        return "stock updated in redis ";
    }


//    @RabbitListener(queues = "PRODUCT")
//    public void receiveMessage(String message) {
//        try {
//            // Deserialize JSON string to CurrentStock
//            CurrentStock currentStock = objectMapper.readValue(message, CurrentStock.class);
//            System.out.println("Received message: " + currentStock);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

