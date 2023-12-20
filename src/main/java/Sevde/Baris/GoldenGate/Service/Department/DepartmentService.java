package Sevde.Baris.GoldenGate.Service.Department;

import Sevde.Baris.GoldenGate.Model.Department;
import Sevde.Baris.GoldenGate.Repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService{
    @Autowired
    private IDepartmentRepository repository;
    @Override
    public List<Department> getDepartment() {
        return repository.findAll();
    }
}
