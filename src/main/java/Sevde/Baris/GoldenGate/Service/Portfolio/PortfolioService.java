package Sevde.Baris.GoldenGate.Service.Portfolio;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Repository.IPortfolioRepository;
import Sevde.Baris.GoldenGate.Repository.IUserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PortfolioService implements IPortfolioService{

    @Autowired
    private IPortfolioRepository portfolioRepository;

    @Autowired
    private IUserStockRepository userStockRepository;

    @Override
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    @Override
    public Portfolio createPortfolio(String name) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(name);
        return portfolioRepository.save(portfolio);
    }

    @Override
    public void deletePortfolio(UUID id) {
        userStockRepository.deleteAllByPortfolioId(id);
        portfolioRepository.deleteById(id);
    }

    @Override
    public Optional<Portfolio> updatePortfolio(UUID id, String name) {
        Optional<Portfolio> portfolioToUpdateOptional = portfolioRepository.findById(id);
        if(portfolioToUpdateOptional.isPresent()){
            Portfolio portfolioToUpdate = portfolioToUpdateOptional.get();
            if(name != null) portfolioToUpdate.setName(name);
            return Optional.of(portfolioRepository.save(portfolioToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Portfolio> addStockToPortfolio(UUID id, UserStock stock){
        Optional<Portfolio> portfolioOptional = portfolioRepository.findById(id);
        if(portfolioOptional.isPresent()){
            Portfolio portfolio = portfolioOptional.get();
            return Optional.of(portfolioRepository.save(portfolio));
        }
        return Optional.empty();
    }
}
