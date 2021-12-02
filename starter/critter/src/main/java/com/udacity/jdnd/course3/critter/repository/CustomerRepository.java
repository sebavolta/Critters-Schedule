package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*@Query("select * Customer c " +
            "JOIN  p On p.customerId=:id " +
            "where c.id=:id")*/
    //Customer findCustomerByPetId(@Param("id") long id);

    Customer findByPetsIdContaining(@Param("id") long id);
}
