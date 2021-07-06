package ar.edu.unju.edm.controller;
import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.ProductoService;

@Controller
public class ProductoController {	
	@Autowired
	@Qualifier("impmysqlproducto")
	ProductoService productoService;
	
	@GetMapping("/producto/mostrar")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", productoService.crearProducto());
		model.addAttribute("productos", productoService.obtenerTodoProducto());
		return("producto");
	}
	
	@GetMapping("/producto/editar/{codigo}")
	public String editarProducto(Model model, @PathVariable(name="codigo") int codigo) throws Exception {
		try {
			Producto productoEncontrado = productoService.encontrarUnProducto(codigo);
			model.addAttribute("unProducto", productoEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoService.crearProducto());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("productos", productoService.obtenerTodoProducto());
		return("producto");
	}

	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@Valid @ModelAttribute("unProducto") Producto nuevoProducto,BindingResult resultado, Model model) {
		if (resultado.hasErrors())
		{
			model.addAttribute("unProducto",nuevoProducto);
			model.addAttribute("productos", productoService.obtenerTodoProducto());
		return ("producto");
		}
		else {
		
		productoService.guardarProducto(nuevoProducto);
		
		return("redirect:/producto/mostrar");}
	}
	
	
	@GetMapping("/producto/eliminarProducto/{codigo}")
	public String eliminarProducto(Model model, @PathVariable(name="codigo") int codigo) {
		try {			
			productoService.eliminarProducto(codigo);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/producto/mostrar";
	}


	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto") Producto productoModificado, Model model) {
		try {
			productoService.modificarProducto(productoModificado);
			model.addAttribute("unProducto", new Producto());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoModificado);			
			model.addAttribute("productos", productoService.obtenerTodoProducto());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("productos", productoService.obtenerTodoProducto());
		return("producto");
	}
}

