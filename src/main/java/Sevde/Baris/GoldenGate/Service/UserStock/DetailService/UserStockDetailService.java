package Sevde.Baris.GoldenGate.Service.UserStock.DetailService;

import Sevde.Baris.GoldenGate.DTO.UserStock.GetAll.UserStockGetAllResponseDTO;
import Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail.UserStockDetailsDTO;
import Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail.UserStockGetDetailResponseDto;
import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Repository.IUserStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserStockDetailService implements IUserStockDetailService {

    @Autowired
    private IUserStockRepository repository;

    public List<UserStockGetAllResponseDTO> getAllUserStockByPortfolioId(UUID id) {
        List<UserStock> allUserStocksInPortfolio = repository.findByPortfolioId(id);
        return combineUserStocks(allUserStocksInPortfolio);
    }

    @Override
    public UserStockGetDetailResponseDto getDetail(UUID portfolioId, String stockCode){
        List<UserStock> stocks = repository.findByPortfolioIdAndStockCode(portfolioId, stockCode);
        List<UserStockDetailsDTO> details = new ArrayList<>();
        for(UserStock stock : stocks){
            details.add(
                    new UserStockDetailsDTO(
                        stock.getPurchasingDate(),
                        stock.getPurchasedLotAmount(),
                        stock.getPurchasingPrice(),
                        stock.getPurchasingPrice() * stock.getPurchasedLotAmount(),
                        calculateTotalProfit(stock),
                        calculateTotalProfitRate(stock)
                    )
            );
        }
        return new UserStockGetDetailResponseDto(combineUserStocks(stocks).getFirst(), details);
    }

    private List<UserStockGetAllResponseDTO> combineUserStocks(List<UserStock> stocks){
        List<UserStockGetAllResponseDTO> result = new ArrayList<>();

        for (UserStock userStock : stocks) {
            String stockCode = userStock.getStock().getCode();
            String stockName = userStock.getStock().getName();
            UUID stockId = userStock.getId();

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
                        stockId,
                        stockCode,
                        stockName,
                        userStock.getPurchasedLotAmount(),
                        userStock.getPurchasingPrice(),
                        userStock.getPurchasingPrice() * userStock.getPurchasedLotAmount(),
                        (userStock.getStock().getCurrentPrice() - userStock.getStock().getPriceYesterday()) * userStock.getPurchasedLotAmount(),
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

    private Double calculateTotalProfit(UserStock userStock){
        return (userStock.getStock().getCurrentPrice() - userStock.getPurchasingPrice()) * userStock.getPurchasedLotAmount();
    }

    private Double calculateTotalProfitRate(UserStock userStock){
        return (userStock.getStock().getCurrentPrice() - userStock.getPurchasingPrice()) / userStock.getPurchasingPrice() * 100;
    }

    private UserStockGetAllResponseDTO findDTOByStockCode(List<UserStockGetAllResponseDTO> list, String stockCode) {
        for (UserStockGetAllResponseDTO dto : list) {
            if (dto.getStockCode().equals(stockCode)) {
                return dto;
            }
        }
        return null;
    }

}
