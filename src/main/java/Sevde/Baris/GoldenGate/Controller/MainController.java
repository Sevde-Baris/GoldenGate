package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.DTO.UserStock.GetAll.UserStockGetAllResponseDTO;
import Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail.UserStockDetailsDTO;
import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Service.Portfolio.IPortfolioService;
import Sevde.Baris.GoldenGate.Service.Stock.IStockService;
import Sevde.Baris.GoldenGate.Service.UserStock.BasicCRUDService.IUserStockBasicCRUDService;
import Sevde.Baris.GoldenGate.Service.UserStock.DetailService.IUserStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private IUserStockDetailService userStockDetailService;
    @Autowired
    private IPortfolioService portfolioService;
    @Autowired
    private IStockService stockService;
    @GetMapping("/mainPage")
    public String getPortfolio(Model model){
        stockService.updateStockPricesRandomly();
        List<Portfolio> allPortfolios = portfolioService.getAllPortfolios();
        double totalStocks = 0;
        double balance = 0;
        double investmentReturn = 0;
        for(Portfolio portfolio: allPortfolios){
            List<UserStockGetAllResponseDTO> stocks = userStockDetailService.getAllUserStockByPortfolioId(portfolio.getId());
            for(UserStockGetAllResponseDTO stock: stocks){
                balance += stock.getTotalPrice();
                totalStocks++;
                List<UserStockDetailsDTO> userStocksDetail = userStockDetailService.getDetail(portfolio.getId(), stock.getStockCode()).getDetails();
                for (UserStockDetailsDTO userStockDetailsDTO : userStocksDetail) {
                    investmentReturn += userStockDetailsDTO.getTotalProfit();
                }

            }
        }
        model.addAttribute("balance", balance);
        model.addAttribute("totalStocks", totalStocks);
        model.addAttribute("investmentReturn", investmentReturn);
        return "MainPage";
    }
}
