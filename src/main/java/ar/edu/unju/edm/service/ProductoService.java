package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
@Service
public interface ProductoService {
	public void guardarProducto(Producto unProducto);
	public Producto obtenerProductoCodigo(Integer codigo);
	public Producto crearProducto();
	public void modificarProducto(Producto productoModificado) throws Exception;
	public void eliminarProducto(int codigo) throws Exception;
	public Producto obtenerProducto(String nombreProducto);
	public List<Producto> obtenerTodoProducto();
	public Producto obtenerProductoNuevo();
	public Producto encontrarUnProducto(int codigo) throws Exception;
	public Producto obtenerUltimoProducto();
}
