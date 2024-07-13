package formacion.microservicios.app.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import formacion.microservicios.app.models.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoRest {

  @GetMapping("/productos/listar")
  public List<Producto> listar();

  @GetMapping("/productos/{id}")
  public Producto detalle(@PathVariable Long id);

  @PostMapping("/productos/crear")
  public Producto crear(@RequestBody Producto producto);

  @PutMapping("/productos/editar")
  public Producto editar(@RequestBody Producto producto, @PathVariable Long id);

  @DeleteMapping("/productos/eliminar/{id}")
  public void eliminar(@PathVariable Long id);

}
