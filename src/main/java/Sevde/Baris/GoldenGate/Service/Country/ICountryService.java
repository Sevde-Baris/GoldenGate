package Sevde.Baris.GoldenGate.Service.Country;

import Sevde.Baris.GoldenGate.Model.Country;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICountryService {
    List<Country> getAllCountry();
    Optional<Country> getCountryById(UUID id);
    List<String> getAllCountryNames();
}
