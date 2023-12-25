package Sevde.Baris.GoldenGate.DTO.UserStock.GetAll;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class UserStockGetAllResponseDTO {
    private UUID id;
    private String stockCode;
    private String stockName;
    private Double totalLotAmount;
    private Double averageCost;
    private Double totalPrice;
    private Double dailyProfit;
    private Double dailyProfitRate;

    public UserStockGetAllResponseDTO(UUID id, String stockCode, String stockName, Double totalLotAmount, Double averageCost, Double totalPrice, Double dailyProfit, Double dailyProfitRate) {
        this.id = id;
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.totalLotAmount = totalLotAmount;
        this.averageCost = averageCost;
        this.totalPrice = totalPrice;
        this.dailyProfit = dailyProfit;
        this.dailyProfitRate = dailyProfitRate;
    }

    public UserStockGetAllResponseDTO() {
    }
}
