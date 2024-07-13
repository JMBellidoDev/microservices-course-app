package formacion.microservicios.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import formacion.microservicios.app.model.Item;
import formacion.microservicios.app.model.ProductoDto;
import formacion.microservicios.app.service.IItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/items")
public class ItemController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

  @Value("${configuracion.texto}")
  private String texto;

  @Autowired
  private IItemService itemService;

  @GetMapping("/listar")
  public List<Item> listar(@RequestParam(name = "nombre", required = false) String nombre,
      @RequestHeader(name = "token-request", required = false) String token) {

    System.out.println(nombre + " - " + token);
    return itemService.findAll();
  }

  @GetMapping("/{id}/{cantidad}")
  @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
  @TimeLimiter(name = "items")
  public CompletableFuture<Item> detalle(@PathVariable Long id, @PathVariable int cantidad) {

    // Nombre "items" como identificador para ser llamado más tarde
    return CompletableFuture.supplyAsync(() -> itemService.findById(id, cantidad));
  }

  @GetMapping("/obtener-config")
  public ResponseEntity<Object> obtenerConfig(@Value("${server.port}") String puerto) {

    Map<String, String> json = new HashMap<>();
    json.put("texto", texto);
    json.put("puerto", puerto);

    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  public CompletableFuture<Item> metodoAlternativo(Long id, int cantidad, Throwable e) {

    LOGGER.info(e.getMessage());

    Item item = new Item();
    ProductoDto prod = new ProductoDto();

    item.setCantidad(cantidad);
    prod.setId(id);
    prod.setNombre("Cámara Sony");
    prod.setPrecio(500.01);
    item.setProductoDto(prod);

    return CompletableFuture.supplyAsync(() -> item);
  }

}
