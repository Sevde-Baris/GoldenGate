package Sevde.Baris.GoldenGate.Service.Portfolio;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Model.UserStock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPortfolioService {
    List<Portfolio> getAllPortfolios();
    Optional<Portfolio> getPortfolioById(UUID id);
    Portfolio createPortfolio(Portfolio portfolio);
    void deletePortfolio(UUID id);
    Optional<Portfolio> updatePortfolio(UUID id, Portfolio portfolio);

    Optional<Portfolio> addStockToPortfolio(UUID id, UserStock stock);

    Optional<Portfolio> removeStockFromPortfolio(UUID portfolioId, UUID stockId);
}
