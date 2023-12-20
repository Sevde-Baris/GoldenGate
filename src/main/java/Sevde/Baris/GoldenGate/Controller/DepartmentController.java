package Sevde.Baris.GoldenGate.Controller;

import Sevde.Baris.GoldenGate.Model.Department;
import Sevde.Baris.GoldenGate.Service.Department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;
    @GetMapping("getDepartment")
    public String getDepartment(Model model){
        List<Department> departments = service.getDepartment();
        model.addAttribute("departments", departments);
        return "DepartmentPage";
    }
}
