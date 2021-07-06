package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IClienteDAO;
import ar.edu.unju.edm.service.IClienteService;
@Service
@Qualifier("impmysql")
public class ClienteServiceMySQL implements IClienteService{	
	@Autowired
	Cliente unCliente;
	
	@Autowired
	IClienteDAO clienteDAO;

	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	
	public Cliente encontrarUnCliente(int id) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.findById(id).orElseThrow(()->new Exception("El cliente no existe"));
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) throws Exception{
		// TODO Auto-generated method stub
		Cliente clienteAModificar = clienteDAO.findByNroDocumento(clienteModificado.getNroDocumento()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		cambiarCliente(clienteModificado, clienteAModificar);
		clienteDAO.save(clienteAModificar);
	}
	
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		//observen que vamos a pasar todos los atributos del cliente que viene, hacia el cliente que ya está en la BD
		hacia.setNroDocumento(desde.getNroDocumento());
		hacia.setNombreApellido(desde.getNombreApellido());
		hacia.setTipoDocumento(desde.getTipoDocumento());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNumTelefono(desde.getNumTelefono());
		hacia.setEmail(desde.getEmail());
		//observen que NO se ha cambiado el id, ya que ese atributo no debería permitirse cambiar
	}

	public void eliminarCliente(int id) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findById(id).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}
}
