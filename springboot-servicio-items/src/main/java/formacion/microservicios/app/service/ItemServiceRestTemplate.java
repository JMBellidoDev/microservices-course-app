package formacion.microservicios.app.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import formacion.microservicios.app.model.Item;
import formacion.microservicios.app.model.ProductoDto;

@Service
public class ItemServiceRestTemplate implements IItemService {

  private RestTemplate clienteRest;

  @Autowired
  public void setClienteRest(RestTemplate clienteRest) {
    this.clienteRest = clienteRest;
  }

  @Override
  public List<Item> findAll() {

    List<ProductoDto> productos = Arrays
        .asList(clienteRest.getForObject("http://servicio-productos/productos/listar", ProductoDto[].class));

    return productos.stream().map(p -> new Item(p, 1)).toList();
  }

  @Override
  public Item findById(Long id, int cantidad) {

    Map<String, String> pathVariables = new HashMap<>();

    // Mapa de correspondencias entre nombres de path variables y su valor
    pathVariables.put("id", id.toString());

    ProductoDto producto = clienteRest.getForObject("http://servicio-productos/productos/{id}", ProductoDto.class,
        pathVariables);

    return new Item(producto, cantidad);
  }

}
