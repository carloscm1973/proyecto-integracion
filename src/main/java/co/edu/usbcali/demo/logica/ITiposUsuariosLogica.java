package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

public interface ITiposUsuariosLogica {
	
	public TiposUsuarios consultarPorTipoUsu(long tipoUsu );
	public List<TiposUsuarios> consultar();

}
