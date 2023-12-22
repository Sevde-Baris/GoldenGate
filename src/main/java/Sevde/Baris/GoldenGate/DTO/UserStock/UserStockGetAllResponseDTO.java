package Sevde.Baris.GoldenGate.DTO.UserStock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserStockGetAllResponseDTO {
    private String stockCode;
    private Double totalLotAmount;
    private Double averageCost;
    private Double totalPrice;
    private Double dailyProfit;
    private Double dailyProfitRate;

    public UserStockGetAllResponseDTO(String stockCode, Double totalLotAmount, Double averageCost, Double totalPrice, Double dailyProfit, Double dailyProfitRate) {
        this.stockCode = stockCode;
        this.totalLotAmount = totalLotAmount;
        this.averageCost = averageCost;
        this.totalPrice = totalPrice;
        this.dailyProfit = dailyProfit;
        this.dailyProfitRate = dailyProfitRate;
    }

    public UserStockGetAllResponseDTO() {
    }
}
