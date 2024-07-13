package formacion.microservicios.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formacion.microservicios.app.entity.Producto;
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

}
