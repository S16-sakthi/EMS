package com.example.demo.service;

import com.example.demo.model.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    void saveEmployee(EmployeeDTO employeeDTO);
    void deleteEmployeeById(Long id);
}