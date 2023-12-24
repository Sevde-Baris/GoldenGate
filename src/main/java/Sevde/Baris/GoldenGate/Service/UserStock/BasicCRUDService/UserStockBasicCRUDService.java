package Sevde.Baris.GoldenGate.Service.UserStock.BasicCRUDService;

import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Repository.IPortfolioRepository;
import Sevde.Baris.GoldenGate.Repository.IUserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserStockBasicCRUDService implements IUserStockBasicCRUDService {
    @Autowired
    private IUserStockRepository userStockRepository;

    @Override
    public Optional<UserStock> getUserStockById(UUID id) {
        return userStockRepository.findById(id);
    }
    @Override
    public UserStock createUserStock(UserStock userStock) {
        return userStockRepository.save(userStock);
    }
    @Override
    public Optional<Object> updateUserStock(UUID id, UserStock userStock) {
        Optional<UserStock> stockToUpdateOptional = userStockRepository.findById(id);
        if(stockToUpdateOptional.isPresent()){
            UserStock stockToUpdate = stockToUpdateOptional.get();
            if(userStock.getPurchasingDate() != null) stockToUpdate.setPurchasingDate(userStock.getPurchasingDate());
            if(userStock.getPurchasingPrice() != null) stockToUpdate.setPurchasingPrice(userStock.getPurchasingPrice());
            if(userStock.getPurchasedLotAmount() != null) stockToUpdate.setPurchasedLotAmount(userStock.getPurchasedLotAmount());
            return Optional.of(userStockRepository.save(stockToUpdate));
        }
        return Optional.empty();
    }
    @Override
    public void deleteUserStock(UUID id) {
        userStockRepository.deleteById(id);
    }
}
