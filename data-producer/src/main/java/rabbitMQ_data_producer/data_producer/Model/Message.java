package rabbitMQ_data_producer.data_producer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private  String MsgId;
    private String Msg;
    private String MSG_date;

}
