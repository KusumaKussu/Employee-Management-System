package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    /**
     * Convert Employee entity to EmployeeDto
     */
    public EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

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

    /**
     * Convert EmployeeDto to Employee entity
     */
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employee.setPhoneNumber(dto.getPhoneNumber());

        return employee;
    }
}

