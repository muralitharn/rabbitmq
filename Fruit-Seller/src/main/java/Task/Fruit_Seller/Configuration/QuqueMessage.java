package Task.Fruit_Seller.Configuration;

import Task.Fruit_Seller.Entity.CurrentStock;
import Task.Fruit_Seller.Repository.currentStockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class QuqueMessage {

    private final List<String> batch = new ArrayList<>();


    @Autowired
    currentStockRepository currentStockRepo;

 @RabbitListener(queues = "PRODUCT")
    public String receiveMessage(@Payload String JsonString) throws JsonProcessingException {
        System.out.print(JsonString);
     CurrentStock stock= new ObjectMapper().readValue(JsonString, CurrentStock.class);
     CurrentStock updateStock=currentStockRepo.findById(stock.getId()).get();
     updateStock.setStockPresent(true);
     updateStock.setInStockAvailability(stock.getInStockAvailability());
     currentStockRepo.save(updateStock);
        return null;
    }


}
