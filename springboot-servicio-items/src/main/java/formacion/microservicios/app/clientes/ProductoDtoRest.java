package formacion.microservicios.app.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import formacion.microservicios.app.model.ProductoDto;

@FeignClient(name = "servicio-productos")
public interface ProductoDtoRest {

  @GetMapping("/productos/listar")
  public List<ProductoDto> listar();

  @GetMapping("/productos/{id}")
  public ProductoDto detalle(@PathVariable Long id);

}
