/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.respository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

  private CustomerRepository customerRepository;

  @Autowired
  public CustomerRestController(CustomerRepository customerRepository) {

    this.customerRepository = customerRepository;
  }

  @GetMapping()
  public List<Customer> list() {
    return customerRepository.findAll();
  }

  @GetMapping("/{id}")
  public Customer get(@PathVariable long id) {

    Optional<Customer> customer = customerRepository.findById(id);

    return customer.orElse(null);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> put(@PathVariable long id, @RequestBody Customer input) {

    Optional<Customer> optionalcustomer = customerRepository.findById(id);
    ResponseEntity<Customer> responseEntity;

    if (optionalcustomer.isPresent()) {

      Customer newcustomer = optionalcustomer.get();
      newcustomer.setName(input.getName());
      newcustomer.setPhone(input.getPhone());

      Customer save = customerRepository.save(newcustomer);

      responseEntity = new ResponseEntity<>(save, HttpStatus.OK);

    } else {
      responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return responseEntity;
  }

  @PostMapping
  public ResponseEntity<Customer> post(@RequestBody Customer input) {

    Customer save = customerRepository.save(input);
    return ResponseEntity.ok(save);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Customer> delete(@PathVariable long id) {

    customerRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
