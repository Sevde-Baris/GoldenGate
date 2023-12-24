package Sevde.Baris.GoldenGate.Service.Portfolio;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Model.UserStock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPortfolioService {
    List<Portfolio> getAllPortfolios();
    Portfolio createPortfolio(String name);
    void deletePortfolio(UUID id);
    Optional<Portfolio> updatePortfolio(UUID id, String name);
    Optional<Portfolio> addStockToPortfolio(UUID id, UserStock stock);
}
