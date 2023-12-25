package Sevde.Baris.GoldenGate.Service.Stock;

import Sevde.Baris.GoldenGate.Model.Stock;
import Sevde.Baris.GoldenGate.Repository.ICountryRepository;
import Sevde.Baris.GoldenGate.Repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class StockService implements IStockService{

    @Autowired
    private IStockRepository stockRepository;
    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAllByOrderByNameAsc();
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

    @Override
    public void updateStockPricesRandomly() {
        List<Stock> stocks = stockRepository.findAll();

        Random random = new Random();

        for (Stock stock : stocks) {
            double currentPrice = stock.getCurrentPrice();

            double percentageChange = (random.nextDouble() - 0.5) * 0.03;

            // Calculate the new price with the random change
            double newPrice = currentPrice * (1 + percentageChange);

            // Update the stock's price
            stock.setPriceFiveYearsAgo(stock.getPriceOneYearAgo());
            stock.setPriceOneYearAgo(stock.getPriceOneMonthAgo());
            stock.setPriceOneMonthAgo(stock.getPriceThreeMonthsAgo());
            stock.setPriceThreeMonthsAgo(stock.getPriceOneWeekAgo());
            stock.setPriceOneWeekAgo(stock.getPriceYesterday());
            stock.setPriceYesterday(currentPrice);
            stock.setCurrentPrice(newPrice);

            // Save the updated stock in the database
            stockRepository.save(stock);
        }
    }
}
