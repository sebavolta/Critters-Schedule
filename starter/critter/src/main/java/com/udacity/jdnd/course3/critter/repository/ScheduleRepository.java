package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findScheduleByPets(Pet pet);
}
    /*SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
        FROM Orders
        INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerI*/