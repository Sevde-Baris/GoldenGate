package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail.UserStockGetDetailResponseDto;
import Sevde.Baris.GoldenGate.Service.UserStock.DetailService.IUserStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;

@Controller()
@RequestMapping("/StockDetail")
public class UserStockController {

    @Autowired
    private IUserStockDetailService service;
    @GetMapping("/getStockDetail")
    public String getStockDetail(@RequestParam String stockCode, UUID portfolioId, Model model){
        UserStockGetDetailResponseDto request = service.getDetail(portfolioId, stockCode);
        model.addAttribute("stockDetail", request);
        return "StockDetail";
    }
}
