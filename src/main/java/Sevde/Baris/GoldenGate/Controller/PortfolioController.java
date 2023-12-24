package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.DTO.UserStock.GetAll.UserStockGetAllResponseDTO;
import Sevde.Baris.GoldenGate.Model.Portfolio;
import Sevde.Baris.GoldenGate.Service.Portfolio.PortfolioService;
import Sevde.Baris.GoldenGate.Service.UserStock.DetailService.UserStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/Portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private UserStockDetailService userStockService;

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
        model.addAttribute("portfolioId", id);
        return "SelectedPortfolio";
    }

    @GetMapping("/createPortfolio")
    public String createPortfolio(@RequestParam String name, Model model){
        portfolioService.createPortfolio(name);
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        model.addAttribute("portfolios", portfolios);
        return "redirect:/Portfolio/getPortfolio";
    }

    @GetMapping("/deletePortfolio")
    public String deletePortfolio(@RequestParam UUID id, Model model){
        portfolioService.deletePortfolio(id);
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        model.addAttribute("portfolios", portfolios);
        return "redirect:/Portfolio/getPortfolio";
    }

    @GetMapping("/updatePortfolio")
    public String updatePortfolio(@RequestParam UUID id, String name, Model model){
        portfolioService.updatePortfolio(id, name);
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        model.addAttribute("portfolios", portfolios);
        return "redirect:/Portfolio/getPortfolio";
    }
}
