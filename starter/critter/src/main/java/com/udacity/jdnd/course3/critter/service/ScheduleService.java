package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;

import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    public Schedule save(Schedule schedule) {
        Schedule sc = this.scheduleRepository.save(schedule);

        sc.getEmployees().stream().forEach(employee -> {
            Employee e = this.employeeService.findEmployeeById(employee.getId());

            List<Schedule> scheduleList = new ArrayList<>();
            scheduleList.add(sc);
            e.setSchedule(scheduleList);

            this.employeeService.save(e);
        });

        return sc;
    }

    public List<Schedule> findAllSchedules() {
        return this.scheduleRepository.findAll();
    }


    public List<Schedule> getScheduleByPet(Pet pet) {
        return this.scheduleRepository.findScheduleByPets(pet);
    }


    public Schedule DtoToSchedule(ScheduleDTO scheduleDTO) {
        Schedule newSchedule = new Schedule();

        List<Employee> theEmployees = new ArrayList<>();

        for (Long employeeId : scheduleDTO.getEmployeeIds()) {
            theEmployees.add(this.employeeService.findEmployeeById(employeeId));
        }

        List<Pet> thePets = new ArrayList<>();

        for (Long petId : scheduleDTO.getPetIds()) {
            thePets.add(this.petService.getSinglePet(petId));
        }

        newSchedule.setEmployees(theEmployees);
        newSchedule.setPets(thePets);

        newSchedule.setId(scheduleDTO.getId());
        newSchedule.setSkills(scheduleDTO.getActivities());
        newSchedule.setDate(scheduleDTO.getDate());

        return newSchedule;
    }

    public ScheduleDTO scheduleToDTO(Schedule schedule) {
        ScheduleDTO newScheduleDTO = new ScheduleDTO();

        List<Long> theEmployees = new ArrayList<>();

        for (Employee employee : schedule.getEmployees()) {
            theEmployees.add(employee.getId());
        }

        List<Long> thePets = new ArrayList<>();

        for (Pet pet : schedule.getPets()) {
            thePets.add(pet.getId());
        }

        newScheduleDTO.setPetIds(thePets);
        newScheduleDTO.setEmployeeIds(theEmployees);

        newScheduleDTO.setId(schedule.getId());
        newScheduleDTO.setDate(schedule.getDate());
        newScheduleDTO.setActivities(schedule.getSkills());

        return newScheduleDTO;
    }


}
