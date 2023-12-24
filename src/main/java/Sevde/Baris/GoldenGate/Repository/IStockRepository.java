package Sevde.Baris.GoldenGate.Repository;

import Sevde.Baris.GoldenGate.Model.Country;
import Sevde.Baris.GoldenGate.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IStockRepository extends JpaRepository<Stock, UUID> {
    List<Stock> findByCountry(Country country);
    Stock findByName(String Name);
}
