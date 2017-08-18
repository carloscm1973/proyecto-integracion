package co.edu.usbcali.demo.dao;
import java.util.List;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

public interface ITiposDocumentosDao {	
	public void crear(TiposDocumentos entity);
	public void modificar(TiposDocumentos entity);
	public void borrar(TiposDocumentos entity);
	public TiposDocumentos consultarPorId(long tdocCodigo);
	public List<TiposDocumentos> consultar();
}
