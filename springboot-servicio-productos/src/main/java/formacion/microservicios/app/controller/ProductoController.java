package formacion.microservicios.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import formacion.microservicios.app.models.Producto;
import formacion.microservicios.app.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

  private IProductoService productoService;

  @Value("${server.port}")
  private Integer port;

  @Autowired
  public void setProductoService(IProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping("/listar")
  public List<Producto> listar() {
    return productoService.findAll().stream().map(p -> {
      p.setPort(port);
      return p;
    }).toList();
  }

  @GetMapping("/{id}")
  public Producto detalle(@PathVariable Long id) {

    Producto prod = productoService.findById(id);

    prod.setPort(port);
    return prod;
  }

  @PostMapping("/crear")
  public ResponseEntity<Producto> crear(@RequestBody Producto producto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
  }

  @PutMapping("/editar/{id}")
  public ResponseEntity<Producto> editar(@RequestBody Producto producto, @PathVariable Long id) {

    Producto productoDb = productoService.findById(id);

    productoDb.setNombre(producto.getNombre());
    productoDb.setPrecio(producto.getPrecio());

    return ResponseEntity.ok(productoService.save(productoDb));
  }

  @DeleteMapping("/eliminar/{id}")
  public ResponseEntity<Producto> eliminar(@PathVariable Long id) {

    productoService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
