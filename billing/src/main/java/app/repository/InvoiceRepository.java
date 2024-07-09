/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Invoice;

/** Repositorio de gestión del almacenamiento de facturas */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
