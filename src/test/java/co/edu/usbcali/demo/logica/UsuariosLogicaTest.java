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
import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class UsuariosLogicaTest {
	
	private final static Logger log=LoggerFactory.getLogger(UsuariosLogicaTest.class);
	
	@Autowired
	private IUsuariosLogica usuariosLogica;
	
	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;	
	
	private Long cedula=16636L;

	@Test
	public void atest()throws Exception {//crear
		assertNotNull(usuariosLogica);
		assertNotNull(tiposUsuariosDAO);
		
		Usuarios usuarios=usuariosLogica.consultarPorCed(cedula);
		assertNull("El cliente ya existe", usuarios);
		
		usuarios=new Usuarios();
		usuarios.setUsuCedula(cedula);
		usuarios.setUsuNombre("Luis Albeiro Hernandez");
		usuarios.setUsuLogin("luisa");
		usuarios.setUsuClave("1234");
		
		TiposUsuarios tiposUsuarios =tiposUsuariosDAO.consultarPorTipoUsu(20L);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuariosLogica.crear(usuarios);
		
	}
	
	
	@Test
	public void btest()throws Exception {//consultar
		assertNotNull(usuariosLogica);
		assertNotNull(tiposUsuariosDAO);
		
		Usuarios usuarios=usuariosLogica.consultarPorCed(cedula);
		assertNotNull("El usuario no existe", usuarios);
		
		log.info("Nombre:"+usuarios.getUsuNombre());
		
		
	}
	
	@Test
	public void ctest() throws Exception{ //modificar
		assertNotNull(usuariosLogica);
		assertNotNull(tiposUsuariosDAO);
		
		Usuarios  usuarios=usuariosLogica.consultarPorCed(cedula);
		assertNotNull("El cliente no existe", usuarios);
		
		
		usuarios.setUsuCedula(cedula);
		usuarios.setUsuNombre("Isabella hernandez Botero");
		usuarios.setUsuLogin("isa");
		usuarios.setUsuClave("1234");
		
		TiposUsuarios tiposUsuarios =tiposUsuariosDAO.consultarPorTipoUsu(10L);
		assertNotNull("El tipo de usuario  no existe", tiposUsuarios);
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuariosLogica.modificar(usuarios);
		
	}
	
	@Test
	public void dtest()throws Exception {//borrar
		assertNotNull(usuariosLogica);
		assertNotNull(tiposUsuariosDAO);
		
		Usuarios usuarios=usuariosLogica.consultarPorCed(cedula);
		assertNotNull("El USUARIO no existe", usuarios);
		
		usuariosLogica.borrar(usuarios);
		
	}
	@Test
	
	public void etest() {//consultar todos
		assertNotNull(usuariosLogica);
		assertNotNull(tiposUsuariosDAO);
		
		List<Usuarios> losUsuarios=usuariosLogica.consultar();
		
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getUsuLogin());
			log.info(usuarios.getUsuNombre());
		}
		
		
	}

}




