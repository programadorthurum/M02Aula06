package m02aula06.model;

import java.util.ArrayList;
import java.util.List;

import m02aula06.basica.Cliente;
import m02aula06.repository.ConexaoException;
import m02aula06.repository.DAOCliente;
import m02aula06.repository.RepositoryException;

public class RNClienteExcluir {

	private void excluir(Cliente cliente) throws ConexaoException, RepositoryException {
		DAOCliente dao = new DAOCliente();
			dao.excluir(null);
	
		
	}
	
}
