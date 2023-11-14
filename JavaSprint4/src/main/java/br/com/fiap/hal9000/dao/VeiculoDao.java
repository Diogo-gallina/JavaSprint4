package br.com.fiap.hal9000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Veiculo;

public class VeiculoDao {
	private Connection conn;
	
	public VeiculoDao(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(Veiculo veiculo) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " T_HP_VEICULO‎ (cd_veiculo, nm_marca, nr_carga_veiculo,"
				+ " nr_tara_veiculo, nr_tamanho_veiculo, tp_veiculo) "
				+ "values (?, ?, ?, ?, ?, ?)");

		stm.setInt(1, veiculo.getId());
		stm.setString(2, veiculo.getMarca());
		stm.setDouble(3, veiculo.getCarga());
		stm.setDouble (4, veiculo.getTara());
		stm.setDouble (5, veiculo.getTamanho());
		stm.setString (6, veiculo.getTipoVeiculo());
		
		stm.executeUpdate();
	}
	
	public List<Veiculo> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from T_HP_VEICULO");

		ResultSet result = stm.executeQuery();

		List<Veiculo> lista = new ArrayList<Veiculo>();
		while (result.next()) {
			Veiculo veiculo = parse(result);
			lista.add(veiculo);
		}
		    
		return lista;
	}
	
	public Veiculo pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from T_HP_VEICULO where cd_veiculo = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) 
			throw new NotFoundException("Veiculo não encontrado");
		
		Veiculo veiculo = parse(result);
		
		return veiculo;
	}
	
	public void atualizar(Veiculo veiculo) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("update T_HP_VEICULO set nm_marca = ?, nr_carga_veiculo = ?, nr_tara_veiculo = ?, nr_tamanho_veiculo = ?, tp_veiculo = ? where cd_veiculo = ?");

		stm.setString(1, veiculo.getMarca());
		stm.setDouble(2, veiculo.getCarga());
		stm.setDouble (3, veiculo.getTara());
		stm.setDouble (4, veiculo.getTamanho());
		stm.setString (5, veiculo.getTipoVeiculo());
		stm.setInt(6, veiculo.getId());
		
		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Veiculo não encontrado");
	}
	
	private Veiculo parse(ResultSet result) throws SQLException {
		
		int id = result.getInt("cd_veiculo");
		String marca = result.getString("nm_marca");
		Double carga = result.getDouble("nr_carga_veiculo");
		Double tara = result.getDouble("nr_tara_veiculo");
		Double tamanho = result.getDouble("nr_tamanho_veiculo");
		String tipoVeiculo = result.getString("tp_veiculo");
		
		Veiculo veiculo = new Veiculo(id, marca, carga, tara, tamanho, tipoVeiculo);

		return veiculo;
	}

}
