package co.edu.usbcali.demo.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

public class ClientesTest {
	
	private final static Logger log=LoggerFactory.getLogger(ClientesTest.class);
	
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	
	private static Long cliId=16636L;
	
	@Test
	public void aTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Clientes clientes=entityManager.find(Clientes.class, cliId);
			assertNull("El cliente ya existe", clientes);
			
			clientes=new Clientes();
			clientes.setCliDireccion("Avenida Siempre Viva 123");
			clientes.setCliId(cliId);
			clientes.setCliMail("hsimpson@gmail.com");
			clientes.setCliNombre("Homero J Simpson");
			clientes.setCliTelefono("555-5555");
			
			TiposDocumentos tiposDocumentos =entityManager.find(TiposDocumentos.class, 10L);
			assertNotNull("El tipo de documento no existe", tiposDocumentos);
			
			clientes.setTiposDocumentos(tiposDocumentos);
			
			entityManager.getTransaction().begin();
				entityManager.persist(clientes);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void bTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
			
			Clientes clientes=entityManager.find(Clientes.class, cliId);
			assertNotNull("El cliente no existe",clientes);
			
			log.info(""+clientes.getCliId());
			log.info(clientes.getCliNombre());
			
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void cTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Clientes clientes=entityManager.find(Clientes.class, cliId);
			assertNotNull("El cliente no existe", clientes);
			
			clientes.setCliDireccion("Avenida Siempre Viva 123");
			clientes.setCliId(cliId);
			clientes.setCliMail("hsimpson@gmail.com");
			clientes.setCliNombre("Homero J Simpson");
			clientes.setCliTelefono("555-55555");
			
			TiposDocumentos tiposDocumentos =entityManager.find(TiposDocumentos.class, 20L);
			assertNotNull("El tipo de documento no existe", tiposDocumentos);
			
			clientes.setTiposDocumentos(tiposDocumentos);
			
			entityManager.getTransaction().begin();
				entityManager.merge(clientes);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void dTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
			Clientes clientes=entityManager.find(Clientes.class, cliId);
			assertNotNull("El cliente no existe", clientes);
			
			
			entityManager.getTransaction().begin();
				entityManager.remove(clientes);
			entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void eTest() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persistencia");
		entityManager=entityManagerFactory.createEntityManager();
		
		String jpql="SELECT cli FROM Clientes cli";
		
		List<Clientes> losClientes=entityManager.createQuery(jpql).getResultList();
		
		for (Clientes clientes : losClientes) {
			log.info("Id:"+clientes.getCliId());
			log.info("Nombre:"+clientes.getCliNombre());
			log.info("Mail:"+clientes.getCliMail());
			log.info("Tipo documento:"+clientes.getTiposDocumentos().getTdocNombre());
		}
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	

}








