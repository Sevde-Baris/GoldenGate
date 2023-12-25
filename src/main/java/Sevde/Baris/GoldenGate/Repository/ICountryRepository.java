package Sevde.Baris.GoldenGate.Repository;

import Sevde.Baris.GoldenGate.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ICountryRepository extends JpaRepository<Country, UUID> {
    Country findByName(String name);
}
