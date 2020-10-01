package m02aula06.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import m02aula06.basica.Cliente;
import m02aula06.util.GerenciadorConexao;
import m02aula06.util.GerenciadorConexaoMysql;

public class DAOCliente implements DAO<Cliente> {
	private GerenciadorConexao ger;

	public Cliente consulta(Cliente cliente) throws ConexaoException, RepositoryException {
		ger = GerenciadorConexaoMysql.getInstancia();
		Connection c = ger.conectar();

		Cliente aux = null;
		String sql = "SELECT id, email, nome, cpf, dataNascimento, sexo, nomeSocial, apelido, telefone FROM clientes WHERE cpf=? OR email=?";
		try {
			PreparedStatement pstm = c.prepareStatement(sql);
			pstm.setString(1, cliente.getCpf());
			pstm.setString(2, cliente.getEmail());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				aux = new Cliente();
				aux.setId(rs.getInt("id"));
				aux.setCpf(rs.getString("cpf"));
				aux.setNome(rs.getString("nome"));
				aux.setEmail(rs.getString("email"));
				aux.setDataNascimento(rs.getString("dataNascimento"));
				aux.setSexo(rs.getString("sexo"));
				aux.setNomeSocial(rs.getString("nomeSocial"));
				aux.setApelido(rs.getString("apelido"));
				aux.setTelefone(rs.getString("telefone"));

			}
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			ger.desconectar(c);
		}
		return aux;
	}

	public void inserir(Cliente cliente) throws ConexaoException, RepositoryException {
		ger = GerenciadorConexaoMysql.getInstancia();
		Connection c = ger.conectar();
		String sql = "INSERT INTO clientes (nome, email, cpf,  dataNascimento, sexo, nomeSocial, apelido, telefone ) VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pstm = c.prepareStatement(sql);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getEmail());
			pstm.setString(3, cliente.getCpf());
			pstm.setNString(4, cliente.getDataNascimento());
			pstm.setNString(5,cliente.getSexo());
			pstm.setNString(6, cliente.getNomeSocial());
			pstm.setNString(7, cliente.getApelido());
			pstm.setNString(8, cliente.getTelefone());
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			ger.desconectar(c);
		}
	}

	@Override
	public void excluir(Cliente cliente) throws ConexaoException, RepositoryException {
		ger = GerenciadorConexaoMysql.getInstancia();
		Connection c = ger.conectar();
		String sql = "DELETE FROM clientes WHERE id=?";
		try {
			PreparedStatement pstm = c.prepareStatement(sql);
			pstm.setInt(1, cliente.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			ger.desconectar(c);
		}
	}

	@Override
	public void alterar(Cliente cliente) throws ConexaoException, RepositoryException {
		ger = GerenciadorConexaoMysql.getInstancia();
		Connection c = ger.conectar();
		String sql = "UPDATE clientes SET nome=?, email=?, cpf=? WHERE id=?";
		try {
			PreparedStatement pstm = c.prepareStatement(sql);
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getEmail());
			pstm.setString(3, cliente.getCpf());
			pstm.setInt(4, cliente.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			ger.desconectar(c);
		}
	}

	@Override
	public List<Cliente> listarTudo() throws ConexaoException, RepositoryException {
		ger = GerenciadorConexaoMysql.getInstancia();
		Connection c = ger.conectar();

		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Cliente aux = null;
		String sql = "SELECT id, email, nome, cpf, dataNascimento, sexo, nomeSocial, apelido, telefone FROM clientes";
		try {
			Statement pstm = c.createStatement();
			ResultSet rs = pstm.executeQuery(sql);
			while (rs.next()) {
				aux = new Cliente();
				aux.setId(rs.getInt("id"));
				aux.setCpf(rs.getString("cpf"));
				aux.setNome(rs.getString("nome"));
				aux.setEmail(rs.getString("email"));
				aux.setDataNascimento(rs.getString("dataNascimento"));
				aux.setSexo(rs.getString("sexo"));
				aux.setNomeSocial(rs.getString("nomeSocial"));
				aux.setApelido(rs.getString("apelido"));
				aux.setTelefone(rs.getString("telefone"));
				lista.add(aux);
			}
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			ger.desconectar(c);
		}
		return lista;
	}
}
