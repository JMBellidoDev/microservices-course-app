
package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Cliente del e-commerce */
@Entity
@Data
@NoArgsConstructor
public class Customer {

  /** ID */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /** Nombre completo del cliente */
  private String name;

  /** Número de teléfono */
  private String phone;

}
