package rabbitMQ_data_producer.data_producer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentStock {

    private long id;

    private boolean isStockPresent;

    private double inStockAvailability;


}
