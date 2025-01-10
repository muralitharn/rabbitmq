package Task.Fruit_Seller.Repository;

import Task.Fruit_Seller.Entity.CurrentStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface currentStockRepository extends JpaRepository<CurrentStock, Long> {

    //List<CurrentStock> findByIsStockPresent(boolean IsStockPresent);
   List<CurrentStock> findByIsStockPresentTrue();

//    @Query("SELECT c FROM CurrentStock c WHERE c.isStockPresent = true")
//    List<CurrentStock> findAllWhereStockIsPresent();
//    @Query(value = "SELECT * FROM current_stock WHERE is_stock_present = :isStockPresent", nativeQuery = true)
//    List<CurrentStock> findByIsStockPresent(@Param("isStockPresent") boolean isStockPresent);
}
