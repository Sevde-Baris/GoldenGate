package Sevde.Baris.GoldenGate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainPage {
    @GetMapping("/mainPage")
    public String getPortfolio(){
        return "MainPage";
    }
}
