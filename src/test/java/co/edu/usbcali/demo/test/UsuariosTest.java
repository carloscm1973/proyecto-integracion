package co.edu.usbcali.demo.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.modelo.Usuarios;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

public class UsuariosTest {
	
	private final static Logger log=LoggerFactory.getLogger(UsuariosTest.class);
	
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	
	private static Long cedula=11L;
	
	@Test
	public void aTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Usuarios usuarios=entityManager.find(Usuarios.class, cedula);
			assertNull("El Usuario  ya existe", usuarios);
			
			usuarios = new Usuarios();
			usuarios.setUsuCedula(cedula);
			usuarios.setUsuNombre("Luis Albeiro Hernandez");
			usuarios.setUsuLogin("luisa");
			usuarios.setUsuClave("1234");
			
			TiposUsuarios tipoUsuario =entityManager.find(TiposUsuarios.class, 10L);
			assertNotNull("El tipo de documento no existe", tipoUsuario);
			
			usuarios.setTiposUsuarios(tipoUsuario);
			
			entityManager.getTransaction().begin();
				entityManager.persist(usuarios);
			entityManager.getTransaction().commit();
			
		entityManager.close();
		entityManagerFactory.close();	
	}
	@Test
	public void bTest(){
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Usuarios usuarios=entityManager.find(Usuarios.class, cedula);
			assertNotNull("El usuario no existe",usuarios);
			
			log.info(""+usuarios.getUsuLogin());
			log.info(usuarios.getUsuNombre());
			
		entityManager.close();
		entityManagerFactory.close();
	}
	@Test
	public void cTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Usuarios usuarios=entityManager.find(Usuarios.class, cedula);
			assertNotNull("El usuario no existe", usuarios);
			
			usuarios.setUsuCedula(cedula);
			usuarios.setUsuNombre("Isabella hernandez Botero");
			usuarios.setUsuLogin("isa");
			usuarios.setUsuClave("1234");
			
			TiposUsuarios tipoUsuario =entityManager.find(TiposUsuarios.class, 10L);
			assertNotNull("El tipo de documento no existe", tipoUsuario);
			
			usuarios.setTiposUsuarios(tipoUsuario);
			
			entityManager.getTransaction().begin();
				entityManager.merge(usuarios);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	@Test
	public void dTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Usuarios usuarios=entityManager.find(Usuarios.class, cedula);
			assertNotNull("El usuario no existe", usuarios);
			
			
			entityManager.getTransaction().begin();
				entityManager.remove(usuarios);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	@Test
	public void eTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
		String jpql="SELECT usu FROM Usuarios usu ";
		
		List<Usuarios> losUsuarios=entityManager.createQuery(jpql).getResultList();	
		
		for (Usuarios Usuarios : losUsuarios) {
			log.info("Cedula:"+Usuarios.getUsuCedula());
			log.info("Login:"+Usuarios.getUsuLogin());
			log.info("nombre:"+Usuarios.getUsuNombre());
			log.info("Tipo Usuario:"+Usuarios.getTiposUsuarios().getTusuNombre());
		}
		
		entityManager.close();
		entityManagerFactory.close();
	}	
	
}
