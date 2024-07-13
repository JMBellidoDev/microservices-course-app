package formacion.microservicios.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import formacion.microservicios.app.clientes.ProductoRest;
import formacion.microservicios.app.model.Item;
import formacion.microservicios.app.models.Producto;

@Service("itemServiceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

  private ProductoRest clienteFeign;

  @Autowired
  public ItemServiceFeign(ProductoRest clienteFeign) {
    this.clienteFeign = clienteFeign;
  }

  @Override
  public List<Item> findAll() {
    return clienteFeign.listar().stream().map(p -> new Item(p, 1)).toList();
  }

  @Override
  public Item findById(Long id, int cantidad) {
    return new Item(clienteFeign.detalle(id), cantidad);
  }

  @Override
  public ResponseEntity<Producto> save(Producto producto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(clienteFeign.crear(producto));
  }

  @Override
  public ResponseEntity<Producto> update(Producto producto, Long id) {

    return ResponseEntity.ok(clienteFeign.editar(producto, id));
  }

  @Override
  public ResponseEntity<Producto> delete(Long id) {

    clienteFeign.eliminar(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
