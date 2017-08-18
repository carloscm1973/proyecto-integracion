package co.edu.usbcali.demo.logica;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.dao.IUsuariosDAO;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class UsuariosLogica implements IUsuariosLogica {

	@Autowired
	private IUsuariosDAO usuariosDAO;
	
	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;
	
	@Autowired
	private Validator validator;
	
	public void validarUsuarios(Usuarios usuarios) throws Exception {
	    try {
	        Set<ConstraintViolation<Usuarios>> constraintViolations = validator.validate(usuarios);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
	                strMessage.append(constraintViolation.getPropertyPath()
	                                                     .toString());
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
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void crear(Usuarios entity) throws Exception {
		if(entity==null){
			throw new Exception("El Usuario es nulo");
		}
		
		validarUsuarios(entity);
		
		Usuarios Usuarios=usuariosDAO.consultarPorCed(entity.getUsuCedula());
		
		if(Usuarios!=null){
			throw new Exception("El usuario ya existe");
		}
		
		usuariosDAO.crear(entity);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Usuarios entity) throws Exception {
		if(entity==null){
			throw new Exception("El usuario es nulo");
		}
		
		validarUsuarios(entity);
		
		usuariosDAO.modificar(entity);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Usuarios entity) throws Exception {
		if(entity==null){
			throw new Exception("El cliente es nulo");
		}
		
		Usuarios usuarios=usuariosDAO.consultarPorCed(entity.getUsuCedula());
		
		if(usuarios==null){
			throw new Exception("El usuario que desea borrar no existe");
		}
		
		usuariosDAO.borrar(usuarios);

	}

	@Override
	@Transactional(readOnly=true)
	public Usuarios consultarPorCed(long cedula) throws Exception {
		return usuariosDAO.consultarPorCed(cedula);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> consultar() {
		return usuariosDAO.Consultar();
	}

}
