package Task.Fruit_Seller.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_name", unique = true)
    private String productName;

    @Column(name = "product_cost_per/kg")
    private long productCost;

    @Column(name = "brand", nullable = false, unique = true)
    private String productBrand;

//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL
//    private CurrentStock currentStock;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private CurrentStock currentStock;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && productCost == product.productCost && Objects.equals(productName, product.productName) && Objects.equals(productBrand, product.productBrand) && Objects.equals(currentStock, product.currentStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productCost, productBrand, currentStock);
    }

}