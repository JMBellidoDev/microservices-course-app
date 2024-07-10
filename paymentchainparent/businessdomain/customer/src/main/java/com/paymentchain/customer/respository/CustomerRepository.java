/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.paymentchain.customer.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentchain.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
