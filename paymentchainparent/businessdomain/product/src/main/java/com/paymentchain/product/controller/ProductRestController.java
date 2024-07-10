/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.controller;

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

import com.paymentchain.product.entity.Product;
import com.paymentchain.product.repository.ProductRepository;

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/product")
public class ProductRestController {

  private ProductRepository productRepository;

  @Autowired
  public ProductRestController(ProductRepository productRepository) {

    this.productRepository = productRepository;
  }

  @GetMapping()
  public List<Product> list() {
    return productRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> get(@PathVariable long id) {

    Optional<Product> product = productRepository.findById(id);

    if (product.isPresent()) {
      return new ResponseEntity<>(product.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> put(@PathVariable long id, @RequestBody Product product) {

    Optional<Product> optionalProduct = productRepository.findById(id);
    ResponseEntity<Product> responseEntity;

    if (optionalProduct.isPresent()) {

      Product newProduct = optionalProduct.get();
      newProduct.setName(product.getName());
      newProduct.setCode(product.getCode());

      Product save = productRepository.save(newProduct);

      responseEntity = new ResponseEntity<>(save, HttpStatus.OK);

    } else {
      responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return responseEntity;
  }

  @PostMapping
  public ResponseEntity<Product> post(@RequestBody Product product) {

    Product save = productRepository.save(product);
    return ResponseEntity.ok(save);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Product> delete(@PathVariable long id) {

    productRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
