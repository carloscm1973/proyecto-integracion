package co.edu.usbcali.demo.dao;
import java.util.List;
import co.edu.usbcali.demo.modelo.Clientes;

public interface IClientesDAO {
	public void crear(Clientes entity);
	public void modificar(Clientes entity);
	public void borrar(Clientes entity);
	public Clientes consultarPorId(long cliId);
	public List<Clientes> consultar();

}
