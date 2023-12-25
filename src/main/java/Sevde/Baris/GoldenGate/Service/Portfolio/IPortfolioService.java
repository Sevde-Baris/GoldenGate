package Sevde.Baris.GoldenGate.Service.Portfolio;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Model.UserStock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPortfolioService {
    List<Portfolio> getAllPortfolios();
    void createPortfolio(String name);
    void deletePortfolio(UUID id);
    void updatePortfolio(UUID id, String name);
    Optional<Portfolio> addStockToPortfolio(UUID id, UserStock stock);
    Optional<Portfolio> getPortfolioById(UUID id);
}
