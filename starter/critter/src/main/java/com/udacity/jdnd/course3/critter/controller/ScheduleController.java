package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = this.scheduleService.DtoToSchedule(scheduleDTO);
        this.scheduleService.save(schedule);

        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> foundSchedules = this.scheduleService.findAllSchedules();

        return foundSchedules.stream()
                .map(schedule -> this.scheduleService.scheduleToDTO(schedule)
                ).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = this.petService.getSinglePet(petId);

        List<Schedule> schedules = scheduleService.getScheduleByPet(pet);

        return schedules.stream()
                .map(schedule -> this.scheduleService.scheduleToDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = this.employeeService.findEmployeeById(employeeId);

        return employee.getSchedule().stream()
                .map(schedule -> this.scheduleService.scheduleToDTO(schedule)
                ).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = this.customerService.findById(customerId);

        Set<ScheduleDTO> scheduleDTOSet = new HashSet<>();

        for (Long petId: customer.getPets()) {
            List<ScheduleDTO> scheduleDTOList = getScheduleForPet(petId);
            scheduleDTOSet.addAll(scheduleDTOList);
        }

        return new ArrayList<>(scheduleDTOSet);
    }
}
