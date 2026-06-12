package net.javaguides.ems.controller;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * Create a new employee
     * POST /api/employees
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    
    /**
     * Get all employees
     * GET /api/employees
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    /**
     * Get employee by ID
     * GET /api/employees/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
    /**
     * Update employee
     * PUT /api/employees/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    
    /**
     * Delete employee
     * DELETE /api/employees/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);
    }
    
    /**
     * Get employees by department
     * GET /api/employees/department/{department}
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@PathVariable String department) {
        List<EmployeeDto> employees = employeeService.getEmployeesByDepartment(department);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    /**
     * Search employees by first name
     * GET /api/employees/search?firstName=John
     */
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> searchEmployeesByName(@RequestParam String firstName) {
        List<EmployeeDto> employees = employeeService.searchEmployeesByName(firstName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    /**
     * Get employee by email
     * GET /api/employees/email/{email}
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String email) {
        EmployeeDto employee = employeeService.getEmployeeByEmail(email);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}

