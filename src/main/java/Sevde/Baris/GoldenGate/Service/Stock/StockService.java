package Sevde.Baris.GoldenGate.Service.Stock;

import Sevde.Baris.GoldenGate.Model.Stock;
import Sevde.Baris.GoldenGate.Repository.ICountryRepository;
import Sevde.Baris.GoldenGate.Repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService implements IStockService{

    @Autowired
    private IStockRepository stockRepository;
    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> getStockById(UUID id) {
        return stockRepository.findById(id);
    }

    @Override
    public List<Stock> getStockByCountryName(String name) {
        return stockRepository.findByCountry(countryRepository.findByName(name));
    }

    @Override
    public Stock getStockByName(String name){
        return  stockRepository.findByName(name);
    }
}
