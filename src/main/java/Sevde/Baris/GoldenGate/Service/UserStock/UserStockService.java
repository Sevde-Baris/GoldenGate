package Sevde.Baris.GoldenGate.Service.UserStock;

import Sevde.Baris.GoldenGate.DTO.UserStock.UserStockGetAllResponseDTO;
import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Repository.IUserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserStockService implements IUserStockService{

    @Autowired
    private IUserStockRepository repository;

    public List<UserStockGetAllResponseDTO> getAllUserStock() {
        List<UserStock> allUserStocks = repository.findAll();
        List<UserStockGetAllResponseDTO> result = new ArrayList<>();

        for (UserStock userStock : allUserStocks) {
            String stockCode = userStock.getStock().getCode();

            // Check if the stockCode is already in the result list
            UserStockGetAllResponseDTO existingDTO = findDTOByStockCode(result, stockCode);

            if (existingDTO != null) {
                // Update existing DTO
                existingDTO.setTotalLotAmount(calculateTotalLotAmount(existingDTO, userStock));
                existingDTO.setTotalPrice(calculateTotalPrice(existingDTO, userStock));
                existingDTO.setDailyProfit(calculateDailyProfit(existingDTO, userStock));
                existingDTO.setDailyProfitRate(calculateDailyProfitRate(userStock));
            } else {
                // Create a new DTO
                UserStockGetAllResponseDTO newDTO = new UserStockGetAllResponseDTO(
                        stockCode,
                        userStock.getPurchasedLotAmount(),
                        userStock.getPurchasingPrice(),
                        userStock.getPurchasingPrice() * userStock.getPurchasedLotAmount(),
                        (userStock.getStock().getPriceYesterday() - userStock.getStock().getCurrentPrice()) * userStock.getPurchasedLotAmount(),
                        (userStock.getStock().getCurrentPrice() - userStock.getStock().getPriceYesterday()) / userStock.getStock().getPriceYesterday() * 100
                );
                result.add(newDTO);
            }
        }
        // Calculate average cost for each DTO
        for (UserStockGetAllResponseDTO dto : result) {
            if (dto.getTotalLotAmount() > 0) {
                dto.setAverageCost(dto.getTotalPrice() / dto.getTotalLotAmount());
            } else {
                dto.setAverageCost(0.0);
            }
        }
        return result;
    }

    private Double calculateTotalLotAmount(UserStockGetAllResponseDTO dto, UserStock userStock){
        return dto.getTotalLotAmount() + userStock.getPurchasedLotAmount();
    }

    private Double calculateTotalPrice(UserStockGetAllResponseDTO dto, UserStock userStock){
        return dto.getTotalPrice() + userStock.getPurchasingPrice() * userStock.getPurchasedLotAmount();
    }

    private Double calculateDailyProfit(UserStockGetAllResponseDTO dto, UserStock userStock){
        return dto.getTotalLotAmount() * (userStock.getStock().getCurrentPrice() - userStock.getStock().getPriceYesterday());
    }

    private Double calculateDailyProfitRate(UserStock userStock){
        return (userStock.getStock().getCurrentPrice() - userStock.getStock().getPriceYesterday()) / userStock.getStock().getPriceYesterday() * 100;
    }

    private UserStockGetAllResponseDTO findDTOByStockCode(List<UserStockGetAllResponseDTO> list, String stockCode) {
        for (UserStockGetAllResponseDTO dto : list) {
            if (dto.getStockCode().equals(stockCode)) {
                return dto;
            }
        }
        return null;
    }
    @Override
    public Optional<UserStock> getUserStockById(UUID id) {
        return repository.findById(id);
    }
    @Override
    public UserStock createUserStock(UserStock userStock) {
        return repository.save(userStock);
    }
    @Override
    public Optional<Object> updateUserStock(UUID id, UserStock userStock) {
        Optional<UserStock> stockToUpdateOptional = repository.findById(id);
        if(stockToUpdateOptional.isPresent()){
            UserStock stockToUpdate = stockToUpdateOptional.get();
            if(userStock.getPortfolios() != null) stockToUpdate.setPortfolios(userStock.getPortfolios());
            if(userStock.getPurchasingDate() != null) stockToUpdate.setPurchasingDate(userStock.getPurchasingDate());
            if(userStock.getPurchasingPrice() != null) stockToUpdate.setPurchasingPrice(userStock.getPurchasingPrice());
            if(userStock.getPurchasedLotAmount() != null) stockToUpdate.setPurchasedLotAmount(userStock.getPurchasedLotAmount());
            return Optional.of(repository.save(stockToUpdate));
        }
        return Optional.empty();
    }
    @Override
    public void deleteUserStock(UUID id) {
        repository.deleteById(id);
    }
}
