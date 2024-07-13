package formacion.microservicios.app.model;

import formacion.microservicios.app.models.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

  private Producto producto;

  private int cantidad;

  public double getTotal() {
    return producto.getPrecio() * cantidad;
  }

}
