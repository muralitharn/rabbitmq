package rabbitMQ_data_producer.data_producer.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitMQ_data_producer.data_producer.Model.CurrentStock;
import rabbitMQ_data_producer.data_producer.Services.DataProducerServices;

@RestController
@RequestMapping("/data-producer")
public class RecordToProductDataBase {


@Autowired
DataProducerServices dataProducerServices;


    @PostMapping("/snd-dataToProduct")
    public String sndDataToProduct(@RequestBody CurrentStock stock) throws AmqpException, JsonProcessingException {
        dataProducerServices.SndDataToProduct(stock);
        return "Message published";
    }
}
