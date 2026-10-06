package com.example.demo.service.impl;

import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found for id: " + id));
        return toDTO(employee);
    }

    @Override
    public void saveEmployee(EmployeeDTO dto) {
        Employee employee;
        if (dto.getId() != null) {
            employee = repository.findById(dto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found for id: " + dto.getId()));
        } else {
            employee = new Employee();
        }

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        repository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete: Employee not found for id: " + id);
        }
        repository.deleteById(id);
    }

    private EmployeeDTO toDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setDepartment(entity.getDepartment());
        dto.setSalary(entity.getSalary());
        return dto;
    }
}