
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

import app.entity.Customer;
import app.repository.CustomerRepository;

/** Controlador para API REST para la gestión de clientes */
@RestController
@RequestMapping("/customer")
public class CustomerController {

  /** Repositorio de clientes */
  private CustomerRepository customerRepository;


  /**
   * Constructor
   * 
   * @param customerRepository Repositorio de clientes
   */
  @Autowired
  public CustomerController(CustomerRepository customerRepository) {

    this.customerRepository = customerRepository;
  }

  /**
   * Lista todos los clientes almacenados
   * 
   * @return List(Customer)
   */
  @GetMapping()
  public List<Customer> findAll() {

    return customerRepository.findAll();
  }

  /**
   * Obtiene un cliente concreto a partir de su id
   * 
   * @param id ID del cliente a buscar
   * @return Customer
   */
  @GetMapping("/{id}")
  public ResponseEntity<Customer> get(@PathVariable Long id) {

    // Se busca el cliente y se responde en función de si se encuentra o no
    Optional<Customer> optCustomer = customerRepository.findById(id);

    if (optCustomer.isPresent()) {

      return ResponseEntity.ok(optCustomer.get());

    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

  }

  /**
   * Actualiza un cliente del sistema
   * 
   * @param id ID del cliente almacenado
   * @param customer Nueva información del cliente a almacenar
   * @return ResponseEntity(Customer)
   */
  @PutMapping("/{id}")
  public ResponseEntity<Customer> put(@PathVariable Long id, @RequestBody Customer customer) {

    Optional<Customer> optCustomer = customerRepository.findById(id);
    ResponseEntity<Customer> responseEntity;

    // Si se encuentra, se modifican todos sus valores para actualizarlo
    if (optCustomer.isPresent()) {

      Customer foundCustomer = optCustomer.get();
      foundCustomer.setName(customer.getName());
      foundCustomer.setPhone(customer.getPhone());

      Customer updatedCustomer = customerRepository.save(foundCustomer);
      responseEntity = ResponseEntity.ok(updatedCustomer);

    } else {
      responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return responseEntity;
  }

  /**
   * Guarda un nuevo cliente en el sistema
   * 
   * @param customer Nuevo cliente a almacenar
   * @return ResponseEntity(Customer)
   */
  @PostMapping
  public ResponseEntity<Customer> post(@RequestBody Customer customer) {

    Customer savedCustomer = customerRepository.save(customer);

    return ResponseEntity.ok(savedCustomer);
  }

  /**
   * Elimina un cliente del sistema. En caso de no encontrarse, no se realizará ninguna otra operación, ni se informará de ello
   * 
   * @param id ID del cliente a eliminar
   * @return ResponeEntity(Customer)
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Customer> delete(@PathVariable Long id) {

    customerRepository.deleteById(id);

    return ResponseEntity.ok().build();
  }


}
