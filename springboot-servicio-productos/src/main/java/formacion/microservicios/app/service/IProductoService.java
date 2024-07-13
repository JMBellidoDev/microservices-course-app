package formacion.microservicios.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import formacion.microservicios.app.entity.Producto;

@Service
public interface IProductoService {

  public List<Producto> findAll();

  public Producto findById(Long id);

}
