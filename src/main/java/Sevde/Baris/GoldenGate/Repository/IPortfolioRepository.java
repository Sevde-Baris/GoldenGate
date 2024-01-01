package Sevde.Baris.GoldenGate.Repository;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IPortfolioRepository extends JpaRepository<Portfolio, UUID> {
    Portfolio findByName(String name);
}
