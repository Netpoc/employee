package net.javarest.ems_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javarest.ems_backend.dto.EmployeeDto;
import net.javarest.ems_backend.entity.Employee;
import net.javarest.ems_backend.exception.ResourceNotFoundException;
import net.javarest.ems_backend.mapper.EmployeeMapper;
import net.javarest.ems_backend.repository.EmployeeRepository;
import net.javarest.ems_backend.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapTEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
                
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }


    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> 
            new ResourceNotFoundException("Employee does not exsist with given id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }


    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                    .collect(Collectors.toList());
    }


    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("Employee with id" + employeeId + "does not exsist.")
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }


    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("Employee with id" + employeeId + "does not exsist.")
        );
        employeeRepository.deleteById(employeeId);
        
    }

}
