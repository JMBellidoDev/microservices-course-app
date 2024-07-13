package formacion.microservicios.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import formacion.microservicios.app.model.Item;
import formacion.microservicios.app.models.Producto;

@Service
public interface IItemService {

  public List<Item> findAll();

  public Item findById(Long id, int cantidad);

  public ResponseEntity<Producto> save(Producto producto);

  public ResponseEntity<Producto> update(Producto producto, Long id);

  public ResponseEntity<Producto> delete(Long id);

}
