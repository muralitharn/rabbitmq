package Task.Fruit_Seller.data_producer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentStock {

    private long id;

    private boolean isStockPresent;

    private double inStockAvailability;


}
