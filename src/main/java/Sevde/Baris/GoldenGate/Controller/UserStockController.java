package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.DTO.UserStock.GetDetail.UserStockGetDetailResponseDto;
import Sevde.Baris.GoldenGate.Model.Stock;
import Sevde.Baris.GoldenGate.Model.UserStock;
import Sevde.Baris.GoldenGate.Service.Country.ICountryService;
import Sevde.Baris.GoldenGate.Service.Portfolio.IPortfolioService;
import Sevde.Baris.GoldenGate.Service.Stock.IStockService;
import Sevde.Baris.GoldenGate.Service.UserStock.BasicCRUDService.IUserStockBasicCRUDService;
import Sevde.Baris.GoldenGate.Service.UserStock.DetailService.IUserStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller()
@RequestMapping("/StockDetail")
public class UserStockController {

    @Autowired
    private IUserStockDetailService detailService;
    @Autowired
    private IUserStockBasicCRUDService basicService;
    @Autowired
    private IPortfolioService portfolioService;
    @Autowired
    private ICountryService countryService;
    @Autowired
    private IStockService stockService;

    @GetMapping("/getStockDetail")
    public String getStockDetail(@RequestParam String stockCode, UUID portfolioId, Model model){
        UserStockGetDetailResponseDto request = detailService.getDetail(portfolioId, stockCode);
        model.addAttribute("stockDetail", request);
        return "StockDetail";
    }

    @GetMapping("/addNewStock")
    public String openAddStockPage(@RequestParam UUID portfolioId, Model model){
        model.addAttribute("countryNames", countryService.getAllCountryNames());
        model.addAttribute("portfolioId", portfolioId);
        return "AddNewStock";
    }

    @GetMapping("/createNewStock")
    public String createNewStock(@RequestParam String stockName, String purchasingDate, Double purchasingPrice, Double purchasedLotAmount, UUID portfolioId, Model model) throws ParseException {
        Stock stock = stockService.getStockByName(stockName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        UserStock userStock = new UserStock();
        userStock.setStock(stock);
        userStock.setPurchasingPrice(purchasingPrice);
        userStock.setPurchasedLotAmount(purchasedLotAmount);
        userStock.setPurchasingDate(dateFormat.parse(purchasingDate));
        userStock.setPortfolio(portfolioService.getPortfolioById(portfolioId).get());
        basicService.createUserStock(userStock);
        model.addAttribute("portfolioId", portfolioId);
        return "redirect:/Portfolio/getPortfolioById?id=" + portfolioId;
    }

    @GetMapping("/getStocksByCountry")
    public ResponseEntity<List<String>> getStocksByCountry(@RequestParam("country") String country) {
        List<Stock> stocks = stockService.getStockByCountryName(country);
        List<String> stockNames = new ArrayList<>();

        for (Stock stock : stocks) {
            stockNames.add(stock.getName());
        }

        return ResponseEntity.ok().body(stockNames);
    }

    @GetMapping("/deleteStock")
    public String deleteStockById(@RequestParam UUID userStockId, UUID portfolioId){
        basicService.deleteUserStock(userStockId);
        return "redirect:/Portfolio/getPortfolioById?id=" + portfolioId;
    }
}
