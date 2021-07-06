package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.service.ProductoService;

@Service
@Qualifier("impmysqlproducto")
public class ProductoServiceMySQL implements ProductoService{
    @Autowired
	Producto unProducto;
    @Autowired
    IProductoDAO productoDAO;
    
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}
	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	@Override
	public void modificarProducto(Producto productoModificado) throws Exception {
		// TODO Auto-generated method stub
		Producto productoAModificar = productoDAO.findById(productoModificado.getCodigo()).orElseThrow(()->new Exception("El producto no fue encontrado"));
	cambiarProducto(productoModificado, productoAModificar);
	productoDAO.save(productoAModificar);
	
	}
	@Override
	public void eliminarProducto(int codigo) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("------------entrando a eliminar en imp---------"+codigo);
		Producto productoAEliminar = productoDAO.findById(codigo).orElseThrow(()->new Exception("El producto no fue encontrado"));
		System.out.println("------------entrando a borrar---------"+codigo);
		System.out.println("codigo de productoAEliminar: "+productoAEliminar.getCodigo());
		productoDAO.delete(productoAEliminar);
		
	}
	@Override
	public Producto obtenerProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Producto> obtenerTodoProducto() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDAO.findAll() ;
	}
	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Producto encontrarUnProducto(int codigo)  throws Exception{
		// TODO Auto-generated method stub
		
		return productoDAO.findById(codigo).orElseThrow(()->new Exception("El producto no existe"));
	}
	private void cambiarProducto(Producto desde, Producto hacia) {
		hacia.setNombre(desde.getNombre());
		hacia.setPrecio(desde.getPrecio());
		hacia.setMarca(desde.getMarca());
		hacia.setStock(desde.getStock());
	}
	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Producto obtenerProductoCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return productoDAO.findById(codigo).orElseThrow();
	}
    
}
