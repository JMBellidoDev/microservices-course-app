package formacion.microservicios.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

  private ProductoDto productoDto;

  private int cantidad;

  public double getTotal() {
    return productoDto.getPrecio() * cantidad;
  }

}
