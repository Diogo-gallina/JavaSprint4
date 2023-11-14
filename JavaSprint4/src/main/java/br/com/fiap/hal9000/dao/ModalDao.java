package br.com.fiap.hal9000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Modal;
import br.com.fiap.hal9000.utils.EnumUtils;

public class ModalDao {
	
	private Connection conn;
	
	public ModalDao(Connection conn) {
		this.conn = conn;
	}
	
	public List<Modal> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from T_HP_MODAL");

		ResultSet result = stm.executeQuery();

		List<Modal> lista = new ArrayList<Modal>();
		while (result.next()) {
			Modal modal = parse(result);
			lista.add(modal);
		}
		    
		return lista;
	}
	
	public Modal pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " T_HP_MODAL where cd_modal = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) 
			throw new NotFoundException("Modal não encontrado");
		
		Modal modal = parse(result);
		
		return modal;
	}
	
	private Modal parse(ResultSet result) throws SQLException {
		
		int id = result.getInt("cd_modal");
		String tipoModal = result.getString("tp_modal");
		
		Modal modal = new Modal(id, EnumUtils.converteStringParaEnumTipoModal(tipoModal));

		return modal;
	}
	
}
