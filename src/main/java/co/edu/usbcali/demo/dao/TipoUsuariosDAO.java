package co.edu.usbcali.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Repository
@Scope("singleton")
public class TipoUsuariosDAO implements ITiposUsuariosDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void crear(TiposUsuarios entity) {
		entityManager.persist(entity);
	}

	@Override
	public void modificar(TiposUsuarios entity) {
		entityManager.merge(entity);
	}

	@Override
	public void borrar(TiposUsuarios entity) {
		entityManager.remove(entity);

	}

	@Override
	public TiposUsuarios consultarPorTipoUsu(long tipoUsu) {
		return entityManager.find(TiposUsuarios.class,tipoUsu);
	}

	@Override
	public List<TiposUsuarios> consultar() {
		String jpql="SELECT tusu FROM TiposUsuarios tusu";
		return entityManager.createQuery(jpql).getResultList();
	}

}
