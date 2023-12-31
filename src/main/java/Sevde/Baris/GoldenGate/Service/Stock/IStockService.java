package Sevde.Baris.GoldenGate.Service.Stock;

import Sevde.Baris.GoldenGate.Model.Stock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockService {
    List<Stock> getAllStock();
    Optional<Stock> getStockById(UUID id);
    List<Stock> getStockByCountryName(String name);

    Stock getStockByName(String name);

    void updateStockPricesRandomly();
}
