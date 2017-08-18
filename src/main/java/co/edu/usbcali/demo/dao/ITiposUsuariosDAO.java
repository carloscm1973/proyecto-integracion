package co.edu.usbcali.demo.dao;
import java.util.List;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

public interface ITiposUsuariosDAO {
	public void crear(TiposUsuarios entity);
	public void modificar(TiposUsuarios entity);
	public void borrar(TiposUsuarios entity);
	public TiposUsuarios consultarPorTipoUsu(long tipoUsu );
	public List<TiposUsuarios> consultar();
	
}
