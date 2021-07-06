package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.edm.model.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Integer>{
	
	@Query("from Producto p order by p.codigo")
	public List<Producto> obtenerTodoProducto();
}
