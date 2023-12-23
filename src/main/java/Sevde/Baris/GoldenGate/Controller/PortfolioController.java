package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.DTO.UserStock.UserStockGetAllResponseDTO;
import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Service.Portfolio.PortfolioService;
import Sevde.Baris.GoldenGate.Service.UserStock.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/Portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private UserStockService userStockService;

    @GetMapping("/getPortfolio")
    public String getPortfolio(Model model){
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        model.addAttribute("portfolios", portfolios);
        return "Portfolio";
    }

    @GetMapping("/getPortfolioById")
    public String getPortfolioById(@RequestParam UUID id, Model model){
        List<UserStockGetAllResponseDTO> userStocks = userStockService.getAllUserStockByPortfolioId(id);
        model.addAttribute("stocks", userStocks);
        return "SelectedPortfolio";
    }
}
