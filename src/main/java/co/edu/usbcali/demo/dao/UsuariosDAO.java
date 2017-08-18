package co.edu.usbcali.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Usuarios;
@Repository
@Scope("singleton")

public class UsuariosDAO implements IUsuariosDAO {

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public void crear(Usuarios entity) {
		entitymanager.persist(entity);
	}

	@Override
	public void modificar(Usuarios entity) {
		entitymanager.merge(entity);
	}

	@Override
	public void borrar(Usuarios entity) {
		entitymanager.remove(entity);
	}

	@Override
	public Usuarios consultarPorCed(long cedula) {
		return entitymanager.find(Usuarios.class,cedula);
	}

	@Override
	public List<Usuarios> Consultar() {
		String jpql="SELECT USU FROM Usuarios USU";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
