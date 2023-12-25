package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.Model.Stock;
import Sevde.Baris.GoldenGate.Service.Stock.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/AllStocks")
public class StockController {
    @Autowired
    IStockService service;
    @RequestMapping("/getStocks")
    public String getStocks(Model model){
        service.updateStockPricesRandomly();
        List<Stock> stocks = service.getAllStock();
        model.addAttribute("allStocks", stocks);
        return "Stocks";
    }
}
