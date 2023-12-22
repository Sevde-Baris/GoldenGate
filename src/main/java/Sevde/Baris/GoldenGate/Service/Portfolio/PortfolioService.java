package Sevde.Baris.GoldenGate.Service.Portfolio;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Repository.IPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService implements IPortfolioService{

    @Autowired
    private IPortfolioRepository repository;

    @Override
    public List<Portfolio> getAllPortfolios() {
        return repository.findAll();
    }
}
