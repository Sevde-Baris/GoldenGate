package Sevde.Baris.GoldenGate.Service.UserStock;

import Sevde.Baris.GoldenGate.DTO.UserStock.UserStockGetAllResponseDTO;
import Sevde.Baris.GoldenGate.Model.UserStock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserStockService {
    List<UserStockGetAllResponseDTO> getAllUserStock();
    Optional<UserStock> getUserStockById(UUID id);
    UserStock createUserStock(UserStock userStock);
    Optional<Object> updateUserStock(UUID id, UserStock userStock);
    void deleteUserStock(UUID id);
}
