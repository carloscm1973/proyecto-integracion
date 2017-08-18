package co.edu.usbcali.demo.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IClientesDAO;
import co.edu.usbcali.demo.dao.ITiposDocumentosDao;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class ClientesLogica implements IClientesLogica {

	@Autowired
	private IClientesDAO clientesDAO;

	@Autowired
	private ITiposDocumentosDao tiposDocumentosDAO;

	@Autowired
	private Validator validator;

	public void validarClientes(Clientes clientes) throws Exception {
		try {
			Set<ConstraintViolation<Clientes>> constraintViolations = validator.validate(clientes);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Clientes> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}

				throw new Exception(strMessage.toString());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void crear(Clientes entity) throws Exception {
		if (entity == null) {
			throw new Exception("El cliente es nulo");
		}

		validarClientes(entity);

		Clientes clientes = clientesDAO.consultarPorId(entity.getCliId());

		if (clientes != null) {
			throw new Exception("El cliente ya existe");
		}

		clientesDAO.crear(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(Clientes entity) throws Exception {
		if (entity == null) {
			throw new Exception("El cliente es nulo");
		}

		validarClientes(entity);

		clientesDAO.modificar(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(Clientes entity) throws Exception {
		if (entity == null) {
			throw new Exception("El cliente es nulo");
		}

		Clientes clientes = clientesDAO.consultarPorId(entity.getCliId());

		if (clientes == null) {
			throw new Exception("El cliente que desea borrar no existe");
		}

		clientesDAO.borrar(clientes);
	}

	@Override
	@Transactional(readOnly = true)
	public Clientes consultarPorId(long cliId) throws Exception {
		return clientesDAO.consultarPorId(cliId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Clientes> consultar() {
		return clientesDAO.consultar();
	}

}
