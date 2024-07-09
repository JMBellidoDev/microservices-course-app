
package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Customer;

/** Repositorio de clientes */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
