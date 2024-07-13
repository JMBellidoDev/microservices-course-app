package formacion.microservicios.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import formacion.microservicios.app.model.Item;

@Service
public interface IItemService {

  public List<Item> findAll();

  public Item findById(Long id, int cantidad);

}
