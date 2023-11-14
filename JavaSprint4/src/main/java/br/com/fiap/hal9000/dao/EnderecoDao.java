package br.com.fiap.hal9000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Endereco;

public class EnderecoDao {
	
	private Connection conn;
	
	public EnderecoDao(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(Endereco endereco) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " T_HP_ENDERECO (cd_endereco, ds_logradouro, ds_bairro,"
				+ " nr_cep,nr_endereco) "
				+ "values (?, ?, ?, ?, ?)");

		stm.setInt(1, endereco.getId());
		stm.setString(2, endereco.getLogradouro());
		stm.setString(3, endereco.getBairro());
		stm.setString (4, endereco.getCep());
		stm.setInt (5, endereco.getNumero());

		stm.executeUpdate();
	}
	
	public List<Endereco> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from T_HP_ENDERECO");

		ResultSet result = stm.executeQuery();

		List<Endereco> lista = new ArrayList<Endereco>();
		while (result.next()) {
			Endereco endereco = parse(result);
			lista.add(endereco);
		}
		    
		return lista;
	}
	
	public Endereco pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " T_HP_ENDERECO where cd_endereco = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) 
			throw new NotFoundException("Endereço não encontrado");
		
		Endereco endereco = parse(result);
		
		return endereco;
	}
	
	public void atualizar(Endereco endereco) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("update T_HP_ENDERECO set ds_logradouro = ?, ds_bairro = ?,"
				+ " nr_cep = ?, nr_endereco = ? where cd_endereco = ?");
		stm.setString(1, endereco.getLogradouro());
		stm.setString(2, endereco.getBairro());
		stm.setString(3, endereco.getCep());
		stm.setInt(4, endereco.getNumero());
		stm.setInt(5, endereco.getId());
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Endereco não encontrado");
	}

	public void remover(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("delete from T_HP_ENDERECO where cd_endereco = ?");

		stm.setInt(1, id);

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Endereço não encontrado");
	}
	
	private Endereco parse(ResultSet result) throws SQLException {
		
		int id = result.getInt("cd_endereco");
		String logradouro = result.getString("ds_logradouro");
		String bairro = result.getString("ds_bairro");
		String cep = result.getString("nr_cep");
		int numero = result.getInt("nr_endereco");
		
		Endereco endereco = new Endereco(id, logradouro, bairro, cep, numero);

		return endereco;
	}
	
}
