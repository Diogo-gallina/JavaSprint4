package br.com.fiap.hal9000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Usuario;

public class UsuarioDao {
	
	private Connection conn;
	
	public UsuarioDao(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(Usuario usuario) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " T_HP_USUARIO_PORTO (cd_usuario_porto, nm_usuario, ds_senha,"
				+ " ds_email) "
				+ "values (?, ?, ?, ?)");

		stm.setInt(1, usuario.getId());
		stm.setString(2, usuario.getNome());
		stm.setString(3, usuario.getSenha());
		stm.setString (4, usuario.getEmail());

		stm.executeUpdate();
	}
	
	public List<Usuario> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from T_HP_USUARIO_PORTO");

		ResultSet result = stm.executeQuery();

		List<Usuario> lista = new ArrayList<Usuario>();
		while (result.next()) {
			Usuario usuario = parse(result);
			lista.add(usuario);
		}
		    
		return lista;
	}
	
	public Usuario pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " T_HP_USUARIO_PORTO where cd_usuario_porto = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) 
			throw new NotFoundException("Usuario não encontrado");
		
		Usuario usuario = parse(result);
		
		return usuario;
	}
	
	public void atualizar(Usuario usuario) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("update T_HP_USUARIO_PORTO set nm_usuario = ?, ds_senha = ?,"
				+ " ds_email = ? where cd_usuario_porto = ?");
		stm.setString(1, usuario.getNome());
		stm.setString(2, usuario.getSenha());
		stm.setString(3, usuario.getEmail());
		stm.setInt(4, usuario.getId());
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Usuario não encontrado");
	}

	public void remover(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("delete from T_HP_USUARIO_PORTO where cd_usuario_porto = ?");

		stm.setInt(1, id);

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Usuario não encontrado");
	}
	
	private Usuario parse(ResultSet result) throws SQLException {
		
		int id = result.getInt("cd_usuario_porto");
		String nome = result.getString("nm_usuario");
		String email = result.getString("ds_email");
		String senha = result.getString("ds_senha");
		
		Usuario usuario = new Usuario(id, nome, senha, email);

		return usuario;
	}
}
