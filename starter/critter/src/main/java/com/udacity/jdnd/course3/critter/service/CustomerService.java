package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetService petService;

    public Customer save(Customer newCustomer) {
        return this.customerRepository.save(newCustomer);
    }

    public List<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer findCustomerByPetId(Long id) {
        return this.customerRepository.findByPetIdsContaining(id);
    }

    public Customer findById(Long id) {
        return this.customerRepository.findById(id).get();
    }


    public CustomerDTO customerToDTO(Customer currentCustomer) {
        CustomerDTO customerDTO = new CustomerDTO();


        customerDTO.setPetIds(currentCustomer.getPets());
        customerDTO.setId(currentCustomer.getId());
        customerDTO.setName(currentCustomer.getName());
        customerDTO.setPhoneNumber(currentCustomer.getPhoneNumber());
        customerDTO.setNotes(currentCustomer.getNotes());

        return customerDTO;
    }

    public Customer DtoToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());

        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());

        customer.setPets(customerDTO.getPetIds());

        return customer;
    }
}
