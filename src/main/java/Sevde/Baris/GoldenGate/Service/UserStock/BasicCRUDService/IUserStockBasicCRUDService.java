package Sevde.Baris.GoldenGate.Service.UserStock.BasicCRUDService;

import Sevde.Baris.GoldenGate.Model.UserStock;

import java.util.Optional;
import java.util.UUID;

public interface IUserStockBasicCRUDService {
    Optional<UserStock> getUserStockById(UUID id);

    UserStock createUserStock(UserStock userStock);

    Optional<Object> updateUserStock(UUID id, UserStock userStock);

    void deleteUserStock(UUID id);
}
