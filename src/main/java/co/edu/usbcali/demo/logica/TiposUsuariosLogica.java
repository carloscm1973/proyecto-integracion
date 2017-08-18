package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Service
@Scope("singleton")
public class TiposUsuariosLogica implements ITiposUsuariosLogica {

	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;
	
	@Override
	public TiposUsuarios consultarPorTipoUsu(long tipoUsu) {
		return tiposUsuariosDAO.consultarPorTipoUsu(tipoUsu);
	}

	@Override
	public List<TiposUsuarios> consultar() {
		return tiposUsuariosDAO.consultar();
	}

}
