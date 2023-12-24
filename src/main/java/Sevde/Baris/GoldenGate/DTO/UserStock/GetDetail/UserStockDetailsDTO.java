package Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserStockDetailsDTO {
    private Date purchasingDate;
    private Double purchasedLotAmount;
    private Double purchasingPrice;
    private Double cost;
    private Double totalProfit;
    private Double totalProfitRate;
    public UserStockDetailsDTO(Date purchasingDate, Double purchasedLotAmount, Double purchasingPrice, Double cost, Double totalProfit, Double totalProfitRate) {
        this.purchasingDate = purchasingDate;
        this.purchasedLotAmount = purchasedLotAmount;
        this.purchasingPrice = purchasingPrice;
        this.cost = cost;
        this.totalProfit = totalProfit;
        this.totalProfitRate = totalProfitRate;
    }

    public UserStockDetailsDTO() {
    }
}
