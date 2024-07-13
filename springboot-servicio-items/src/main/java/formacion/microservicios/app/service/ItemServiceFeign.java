package formacion.microservicios.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import formacion.microservicios.app.clientes.ProductoDtoRest;
import formacion.microservicios.app.model.Item;

@Service("itemServiceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

  private ProductoDtoRest clienteFeign;

  @Autowired
  public ItemServiceFeign(ProductoDtoRest clienteFeign) {
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

}
