package rabbitMQ_data_producer.data_producer.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitMQ_data_producer.data_producer.Config.RabbitMQConfig;
import rabbitMQ_data_producer.data_producer.Model.CurrentStock;


@Service
public class DataProducerServices {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public String SndDataToProduct(CurrentStock stock) throws AmqpException, JsonProcessingException {
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName
                , RabbitMQConfig.routing_key, new ObjectMapper().writeValueAsString(stock));
        System.out.print(new ObjectMapper().writeValueAsString(stock));
        return "Message published";
    }


//   public void SndDataToProduct(CurrentStock currentStock)throws AmqpException {
//        try {
//            String jsonMessage = objectMapper.writeValueAsString(currentStock);
//            rabbitTemplate.convertAndSend("EXCHANGE_NAME", "first_routing_key", jsonMessage);
//            System.out.println("Sent message: " + jsonMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
