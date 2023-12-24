package Sevde.Baris.GoldenGate.Service.UserStock.DetailService;

import Sevde.Baris.GoldenGate.DTO.UserStock.GetAll.UserStockGetAllResponseDTO;
import Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail.UserStockGetDetailResponseDto;

import java.util.List;
import java.util.UUID;

public interface IUserStockDetailService {
    List<UserStockGetAllResponseDTO> getAllUserStockByPortfolioId(UUID id);
    UserStockGetDetailResponseDto getDetail(UUID portfolioId, String stockCode);
}
