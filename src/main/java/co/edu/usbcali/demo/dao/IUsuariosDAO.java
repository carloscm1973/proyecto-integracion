package co.edu.usbcali.demo.dao;
import java.util.List;
import co.edu.usbcali.demo.modelo.Usuarios;

public interface IUsuariosDAO {
	public void crear(Usuarios entity);
	public void modificar(Usuarios entity);
	public void borrar(Usuarios entity);
	public Usuarios consultarPorCed(long cedula);
	public List<Usuarios> Consultar();
}
