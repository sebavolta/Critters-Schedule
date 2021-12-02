package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public void save(Schedule schedule) {
        this.scheduleRepository.save(schedule);
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
            theEmployees.add(this.employeeRepository.findById(employeeId).get());
        }

        List<Pet> thePets = new ArrayList<>();

        for (Long petId : scheduleDTO.getPetIds()) {
            thePets.add(this.petRepository.findById(petId).get());
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
