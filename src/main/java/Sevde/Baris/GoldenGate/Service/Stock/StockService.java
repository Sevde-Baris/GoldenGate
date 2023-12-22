package Sevde.Baris.GoldenGate.Service.Stock;

import Sevde.Baris.GoldenGate.Model.Country;
import Sevde.Baris.GoldenGate.Model.Stock;
import Sevde.Baris.GoldenGate.Repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService implements IStockService{

    @Autowired
    private IStockRepository repository;

    @Override
    public List<Stock> getAllStock() {
        return repository.findAll();
    }

    @Override
    public Optional<Stock> getStockById(UUID id) {
        return repository.findById(id);
    }

    // it will be checked.
    @Override
    public List<Stock> getStockByCountry(Country country) {
        return repository.findByCountry(country);
    }
}
