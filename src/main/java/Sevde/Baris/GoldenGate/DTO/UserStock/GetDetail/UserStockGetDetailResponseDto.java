package Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail;

import Sevde.Baris.GoldenGate.DTO.UserStock.GetAll.UserStockGetAllResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class UserStockGetDetailResponseDto {
    UserStockGetAllResponseDTO roughData;
    List<UserStockDetailsDTO> details;

    public UserStockGetDetailResponseDto(UserStockGetAllResponseDTO roughData, List<UserStockDetailsDTO> details) {
        this.roughData = roughData;
        this.details = details;
    }

    public UserStockGetDetailResponseDto() {
    }
}
