package br.com.fiap.hal9000.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.dao.ModalDao;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.factory.ConnectionFactory;
import br.com.fiap.hal9000.model.Modal;

public class ModalService {
	
	private ModalDao modalDao;
	
	public ModalService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		modalDao = new ModalDao(conn);
	}
	
	public List<Modal> listar() throws ClassNotFoundException, SQLException{
		return modalDao.listar();
	}
	
	public Modal pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException{
		Modal modal = modalDao.pesquisar(id);
		
		return modal;
	}
	
}
