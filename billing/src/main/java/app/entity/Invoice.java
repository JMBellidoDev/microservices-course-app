/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


/** Factura */
@Entity
@Data
public class Invoice {

  /** ID */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /** ID del cliente al cual pertenece la factura */
  private long customerId;

  /** Número de factura */
  private String number;

  /** Detalle */
  private String detail;

  /** Cantidad */
  private double amount;
}
