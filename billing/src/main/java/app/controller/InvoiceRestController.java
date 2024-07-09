/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.controller;

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

import app.entity.Invoice;
import app.repository.InvoiceRepository;

/** Controlador para API REST de facturas */
@RestController
@RequestMapping("/billing")
public class InvoiceRestController {

  /** Repositorio de facturas */
  InvoiceRepository billingRepository;


  /**
   * Constructor
   * 
   * @param billingRepository Repositorio de facturas
   */
  @Autowired
  public InvoiceRestController(InvoiceRepository billingRepository) {

    this.billingRepository = billingRepository;
  }

  /**
   * Obtiene una lista con todas las facturas almacenadas
   * 
   * @return List(Invoice)
   */
  @GetMapping()
  public List<Invoice> list() {

    return billingRepository.findAll();
  }

  /**
   * Obtiene una factura dado su ID
   * 
   * @param id ID de la factura a buscar
   * @return ResponseEntity(Invoice)
   */
  @GetMapping("/{id}")
  public ResponseEntity<Invoice> get(@PathVariable long id) {

    Optional<Invoice> invoice = billingRepository.findById(id);

    if (invoice.isPresent()) {
      return new ResponseEntity<>(invoice.get(), HttpStatus.OK);

    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Modifica una factura dado su ID y la información referente a ella
   * 
   * @param id ID
   * @param invoice Factura
   * @return ResponseEntity(Invoice)
   */
  @PutMapping("/{id}")
  public ResponseEntity<Invoice> put(@PathVariable String id, @RequestBody Invoice invoice) {

    return null;
  }

  /**
   * Agrega una nueva factura al sistema
   * 
   * @param invoice Nueva factura
   * @return ResponseEntity(Invoice)
   */
  @PostMapping
  public ResponseEntity<Invoice> post(@RequestBody Invoice invoice) {

    Invoice save = billingRepository.save(invoice);
    return ResponseEntity.ok(save);
  }

  /**
   * Elimina una factura del sistema dado su ID
   * 
   * @param id ID de la factura
   * @return ResponseEntity(Invoice)
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Invoice> delete(@PathVariable String id) {

    Optional<Invoice> dto = billingRepository.findById(Long.valueOf(id));

    if (!dto.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    billingRepository.delete(dto.get());
    return ResponseEntity.ok().build();
  }

}
