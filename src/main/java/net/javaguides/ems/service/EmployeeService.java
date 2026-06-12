package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Create a new employee
     */
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setPosition(employeeDto.getPosition());
        employee.setSalary(employeeDto.getSalary());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());

        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    /**
     * Get employee by ID
     */
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return convertToDto(employee);
    }

    /**
     * Get all employees
     */
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Update employee
     */
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setPosition(employeeDto.getPosition());
        employee.setSalary(employeeDto.getSalary());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());

        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToDto(updatedEmployee);
    }

    /**
     * Delete employee
     */
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    /**
     * Get employees by department
     */
    public List<EmployeeDto> getEmployeesByDepartment(String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Get employees by name (search)
     */
    public List<EmployeeDto> searchEmployeesByName(String firstName) {
        List<Employee> employees = employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
        return employees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Get employee by email
     */
    public EmployeeDto getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
        return convertToDto(employee);
    }

    /**
     * Convert Entity to DTO
     */
    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setPosition(employee.getPosition());
        dto.setSalary(employee.getSalary());
        dto.setPhoneNumber(employee.getPhoneNumber());
        return dto;
    }
}

