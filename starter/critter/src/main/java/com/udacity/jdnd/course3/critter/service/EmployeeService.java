package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void save(Employee newEmployee) {
        this.employeeRepository.save(newEmployee);
    }

    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.findById(id).get();
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long id) {
        Employee foundEmployee = this.employeeRepository.findById(id).isPresent() ? this.employeeRepository.findById(id).get() : null;

        if (foundEmployee != null) {
            foundEmployee.setDaysAvailable(daysAvailable);
        }
    }

    public List<Employee> findEmployeesForService(LocalDate daysAvailable, Set<EmployeeSkill> skillsRequested) {
        List<Employee> employeeList = this.employeeRepository.findEmployeesByDaysAvailableAndSkillsIn(daysAvailable.getDayOfWeek(), skillsRequested);
        List<Employee> availableEmployeeList = new ArrayList<>();

        for(Employee employee : employeeList){
            if(employee.getSkills().containsAll(skillsRequested)){
                availableEmployeeList.add(employee);
            }
        }

        return availableEmployeeList;

    }



    public EmployeeDTO EmployeeToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getSkills());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());

        return employeeDTO;
    }

    public Employee dtoToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setSkills(employeeDTO.getSkills());

        return employee;
    }

}
