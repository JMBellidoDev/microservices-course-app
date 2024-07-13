package formacion.microservicios.app.repository;

import org.springframework.data.repository.CrudRepository;

import formacion.microservicios.app.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
