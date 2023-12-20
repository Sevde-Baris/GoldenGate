package Sevde.Baris.GoldenGate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/People")
public class PeopleController {

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("something", "that comes from controller.");
        return "People";
    }

    @GetMapping("/page2")
    public String page2(){
        return "page2";
    }
}
