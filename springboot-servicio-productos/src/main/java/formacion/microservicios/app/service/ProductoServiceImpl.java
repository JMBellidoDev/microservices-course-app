package formacion.microservicios.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import formacion.microservicios.app.entity.Producto;
import formacion.microservicios.app.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

  /** Repositorio de productos */
  @Autowired
  private ProductoRepository productoRepository;

  /**
   * COnstructor
   * 
   * @param productoRepository Repositorio de productos
   */
  public ProductoServiceImpl(ProductoRepository productoRepository) {

    this.productoRepository = productoRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Producto> findAll() {
    return (List<Producto>) productoRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Producto findById(Long id) {

    Optional<Producto> optProducto = productoRepository.findById(id);
    return optProducto.orElse(null);
  }

}
