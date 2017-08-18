package co.edu.usbcali.demo.logica;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.demo.dao.ITiposDocumentosDao;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ClientesLogicaTest {
	
	private final static Logger log=LoggerFactory.getLogger(ClientesLogicaTest.class);
	
	@Autowired
	private IClientesLogica clientesLogica;
	
	@Autowired
	private ITiposDocumentosDao tiposDocumentosDAO;	
	
	private Long cliId=16636L;

	@Test
	public void atest()throws Exception {//crear
		assertNotNull(clientesLogica);
		assertNotNull(tiposDocumentosDAO);
		
		Clientes clientes=clientesLogica.consultarPorId(cliId);
		assertNull("El cliente ya existe", clientes);
		
		clientes=new Clientes();
		clientes.setCliDireccion("Avenida Siempre Viva 123");
		clientes.setCliId(cliId);
		clientes.setCliMail("hsimpson@gmail.com");
		clientes.setCliNombre("Homero J Simpson");
		clientes.setCliTelefono("555-5555");
		
		TiposDocumentos tiposDocumentos =tiposDocumentosDAO.consultarPorId(20L);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		
		clientes.setTiposDocumentos(tiposDocumentos);
		
		clientesLogica.crear(clientes);
		
	}
	
	
	@Test
	public void btest()throws Exception {//consultar
		assertNotNull(clientesLogica);
		assertNotNull(tiposDocumentosDAO);
		
		Clientes clientes=clientesLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		
		log.info("Nombre:"+clientes.getCliNombre());
		
		
	}
	
	@Test
	public void ctest() throws Exception{ //modificar
		assertNotNull(clientesLogica);
		assertNotNull(tiposDocumentosDAO);
		
		Clientes clientes=clientesLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		
		
		clientes.setCliDireccion("Avenida Siempre Viva 123");
		clientes.setCliMail("hsimpson@hotmail.com");
		clientes.setCliNombre("Homero J Simpson");
		clientes.setCliTelefono("555-5555-44");
		
		TiposDocumentos tiposDocumentos =tiposDocumentosDAO.consultarPorId(10L);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		
		clientes.setTiposDocumentos(tiposDocumentos);
		
		clientesLogica.modificar(clientes);
		
	}
	
	@Test
	public void dtest()throws Exception {//borrar
		assertNotNull(clientesLogica);
		assertNotNull(tiposDocumentosDAO);
		
		Clientes clientes=clientesLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		
		clientesLogica.borrar(clientes);
		
	}
	@Test
	
	public void etest() {//consultar todos
		assertNotNull(clientesLogica);
		assertNotNull(tiposDocumentosDAO);
		
		List<Clientes> losClientes=clientesLogica.consultar();
		
		for (Clientes clientes : losClientes) {
			log.info(clientes.getCliNombre());
			log.info(clientes.getCliMail());
		}
		
		
	}

}




