package co.edu.usbcali.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Repository
@Scope("singleton")
public class TiposDocumentosDAO implements ITiposDocumentosDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void crear(TiposDocumentos entity) {
		entityManager.persist(entity);
	}

	@Override
	public void modificar(TiposDocumentos entity) {
		entityManager.merge(entity);
	}

	@Override
	public void borrar(TiposDocumentos entity) {
		entityManager.remove(entity);
	}

	@Override
	public TiposDocumentos consultarPorId(long tdocCodigo) {
		return entityManager.find(TiposDocumentos.class, tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> consultar() {
		String jpql="SELECT tdoc FROM TiposDocumentos tdoc";		
		return entityManager.createQuery(jpql).getResultList();
	}

}
