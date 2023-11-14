package br.com.fiap.hal9000.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.dao.FeedbackDao;
import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.factory.ConnectionFactory;
import br.com.fiap.hal9000.model.Feedback;
import br.com.fiap.hal9000.utils.NumberUtils;

public class FeedbackService {
	
	private FeedbackDao feedbackDao;
	
	public FeedbackService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		feedbackDao = new FeedbackDao(conn);
	}
	
	public void cadastrar(Feedback feedback) throws ClassNotFoundException, SQLException, BadRequestException {
		
		boolean tipoPrevisao = feedback.getTipoPrevisao();
		
	    if (tipoPrevisao != true && tipoPrevisao != false) 
	        throw new BadRequestException("Tipo de previsão deve ser true ou false.");
	    
		int id = NumberUtils.gerarId(10000000, 99999999);
		feedback.setId(id);
		
		feedbackDao.cadastrar(feedback);
	}
	
	
	public List<Feedback> listar() throws ClassNotFoundException, SQLException{
		return feedbackDao.listar();
	}
	
	public Feedback pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {
		Feedback feedback = feedbackDao.pesquisar(id);
		return feedback;
	}
	
}
