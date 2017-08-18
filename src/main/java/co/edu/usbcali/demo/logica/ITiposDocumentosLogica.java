package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposDocumentos;

public interface ITiposDocumentosLogica {
	
	public TiposDocumentos consultarPorId(long tdocCodigo);
	public List<TiposDocumentos> consultar();

}
