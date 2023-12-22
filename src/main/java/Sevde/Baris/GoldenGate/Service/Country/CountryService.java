package Sevde.Baris.GoldenGate.Service.Country;

import Sevde.Baris.GoldenGate.Model.Country;
import Sevde.Baris.GoldenGate.Repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private ICountryRepository repository;
    @Override
    public List<Country> getAllCountry() {
        return repository.findAll();
    }
    @Override
    public Optional<Country> getCountryById(UUID id) {
        return repository.findById(id);
    }
}
