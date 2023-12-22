package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Service.Portfolio.PortfolioService;
import Sevde.Baris.GoldenGate.Service.UserStock.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
