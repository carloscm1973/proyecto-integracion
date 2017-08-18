package co.edu.usbcali.demo.DAO;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ClientesDAO;
import co.edu.usbcali.demo.dao.IClientesDAO;
import co.edu.usbcali.demo.dao.ITiposDocumentosDao;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class ClientesDAOTest {	
	private final static Logger log=LoggerFactory.getLogger(ClientesDAOTest.class);
	
	@Autowired
	private IClientesDAO clientesDAO;
	
	@Autowired
	private ITiposDocumentosDao tiposDocumentosDAO;
	
	private long cliId=16636L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)	
	public void atest() {//crear
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);
		
		Clientes clientes=clientesDAO.consultarPorId(cliId);
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
		
		clientesDAO.crear(clientes);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void btest() {//consultar
			
			assertNotNull(clientesDAO);
			assertNotNull(tiposDocumentosDAO);
			
			Clientes clientes=clientesDAO.consultarPorId(cliId);
			assertNotNull("El cliente no existe",clientes);
			
			log.info("Nombre:"+clientes.getCliNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void ctest() {//modificar
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);
		
		Clientes clientes=clientesDAO.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		
		
		clientes.setCliDireccion("Avenida Siempre Viva 123");
		clientes.setCliMail("hsimpson@hotmail.com");
		clientes.setCliNombre("Homero J Simpson");
		clientes.setCliTelefono("555-5555-44");
		
		TiposDocumentos tiposDocumentos =tiposDocumentosDAO.consultarPorId(10L);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		
		clientes.setTiposDocumentos(tiposDocumentos);		
		clientesDAO.modificar(clientes);
		
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dtest() {//borrar¿
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);
		
		Clientes clientes=clientesDAO.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		
		clientesDAO.borrar(clientes);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void etest() {//consultar todod
		assertNotNull(clientesDAO);
		assertNotNull(tiposDocumentosDAO);
		
		List<Clientes> losClientes=clientesDAO.consultar();
		
		for (Clientes clientes : losClientes) {
			log.info(clientes.getCliNombre());
			log.info(clientes.getCliMail());
		}
	}

}
