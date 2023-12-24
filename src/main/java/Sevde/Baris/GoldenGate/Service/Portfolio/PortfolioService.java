package Sevde.Baris.GoldenGate.Service.Portfolio;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Repository.IPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PortfolioService implements IPortfolioService{

    @Autowired
    private IPortfolioRepository repository;

    @Override
    public List<Portfolio> getAllPortfolios() {
        return repository.findAll();
    }

    @Override
    public Optional<Portfolio> getPortfolioById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Portfolio createPortfolio(Portfolio portfolio) {
        return repository.save(portfolio);
    }

    @Override
    public void deletePortfolio(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Portfolio> updatePortfolio(UUID id, Portfolio portfolio) {
        Optional<Portfolio> portfolioToUpdateOptional = repository.findById(id);
        if(portfolioToUpdateOptional.isPresent()){
            Portfolio portfolioToUpdate = portfolioToUpdateOptional.get();
            if(portfolio.getName() != null) portfolioToUpdate.setName(portfolio.getName());
            return Optional.of(repository.save(portfolioToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Portfolio> addStockToPortfolio(UUID id, UserStock stock){
        Optional<Portfolio> portfolioOptional = repository.findById(id);
        if(portfolioOptional.isPresent()){
            Portfolio portfolio = portfolioOptional.get();
            return Optional.of(repository.save(portfolio));
        }
        return Optional.empty();
    }
}
