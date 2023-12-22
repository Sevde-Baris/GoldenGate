package Sevde.Baris.GoldenGate.Repository;

import Sevde.Baris.GoldenGate.Model.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IUserStockRepository extends JpaRepository<UserStock, UUID> {

}
