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

import co.edu.usbcali.demo.dao.IUsuariosDAO;
import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.modelo.Usuarios;
import co.edu.usbcali.demo.modelo.TiposUsuarios;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class UsuariosDAOTest {
	private final static Logger log=LoggerFactory.getLogger(UsuariosDAOTest.class);
	
	@Autowired
	private IUsuariosDAO UsuariosDAO;
	
	@Autowired
	private ITiposUsuariosDAO TipoUsuariosDAO;
	
	private long cedula=123L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)	
	public void atest() {//crear
		assertNotNull(UsuariosDAO);
		assertNotNull(TipoUsuariosDAO);
		
		Usuarios usuarios=UsuariosDAO.consultarPorCed(cedula);
		assertNull("El usuario  ya existe", usuarios);
		
		usuarios=new Usuarios();
		usuarios.setUsuCedula(cedula);
		usuarios.setUsuNombre("Luis Albeiro Hernandez");
		usuarios.setUsuLogin("luisa");
		usuarios.setUsuClave("1234");
		
		TiposUsuarios tiposUsuarios = TipoUsuariosDAO.consultarPorTipoUsu(20L);
		
		assertNotNull("El tipo de documento no existe", tiposUsuarios);
		
		usuarios.setTiposUsuarios(tiposUsuarios);		
		UsuariosDAO.crear(usuarios);
	}
	@Test
	@Transactional(readOnly=true)
	public void btest() {//consultar
			
			assertNotNull(UsuariosDAO);
			assertNotNull(TipoUsuariosDAO);
			
			Usuarios usuarios = UsuariosDAO.consultarPorCed(cedula);
			assertNotNull("El cliente no existe",usuarios);
			
			log.info("Nombre:"+usuarios.getUsuLogin());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void ctest() {//modificar
		assertNotNull(UsuariosDAO);
		assertNotNull(TipoUsuariosDAO);
		
		Usuarios usuarios=UsuariosDAO.consultarPorCed(cedula);
		assertNotNull("El usuario no existe", usuarios);
		
		usuarios.setUsuCedula(cedula);
		usuarios.setUsuNombre("Isabella hernandez Botero");
		usuarios.setUsuLogin("isa");
		usuarios.setUsuClave("1234");
		
		TiposUsuarios tiposUsuarios =TipoUsuariosDAO.consultarPorTipoUsu(10L);
		assertNotNull("El tipo de documento no existe", tiposUsuarios);
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		UsuariosDAO.modificar(usuarios);
	}
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dtest() {//borrar¿
		assertNotNull(UsuariosDAO);
		assertNotNull(TipoUsuariosDAO);
		
		Usuarios usuarios=UsuariosDAO.consultarPorCed(cedula);
		assertNotNull("El usuario no existe", usuarios);
		
		UsuariosDAO.borrar(usuarios);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void etest() {//consultar todod
		assertNotNull(UsuariosDAO);
		assertNotNull(TipoUsuariosDAO);
		
		List<Usuarios> losUaurios=UsuariosDAO.Consultar();
		
		for (Usuarios usuarios : losUaurios) {
			log.info(usuarios.getUsuLogin());
			log.info(usuarios.getUsuNombre());
		}
	}

}
