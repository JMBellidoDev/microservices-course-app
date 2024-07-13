package formacion.microservicios.app.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductoDto {

  private Long id;

  private String nombre;

  private double precio;

  private LocalDate createdAt;

}
