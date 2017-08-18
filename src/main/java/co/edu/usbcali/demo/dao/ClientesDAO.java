package co.edu.usbcali.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.usbcali.demo.modelo.Clientes;
@Repository
@Scope("singleton")

public class ClientesDAO implements IClientesDAO {

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public void crear(Clientes entity) {
		entitymanager.persist(entity);	
	}

	@Override
	public void modificar(Clientes entity) {
		entitymanager.merge(entity);
	}

	@Override
	public void borrar(Clientes entity) {
		entitymanager.remove(entity);
	}

	@Override
	public Clientes consultarPorId(long cliId) {
		return entitymanager.find(Clientes.class,cliId);
	}

	@Override
	public List<Clientes> consultar() {
		String jpql="SELECT cli FROM Clientes cli";
		return entitymanager.createQuery(jpql).getResultList();
	}

}
