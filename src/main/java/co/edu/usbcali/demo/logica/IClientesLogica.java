package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;

public interface IClientesLogica {
	
	public void crear(Clientes entity)throws Exception;
	public void modificar(Clientes entity)throws Exception;
	public void borrar(Clientes entity)throws Exception;
	public Clientes consultarPorId(long cliId)throws Exception;
	public List<Clientes> consultar();

}
