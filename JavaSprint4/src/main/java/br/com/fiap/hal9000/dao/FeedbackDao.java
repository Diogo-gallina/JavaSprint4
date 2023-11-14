package br.com.fiap.hal9000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Feedback;

public class FeedbackDao {

	private Connection conn;
	
	public FeedbackDao(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(Feedback feedback) throws ClassNotFoundException, SQLException {
	    PreparedStatement stm = conn.prepareStatement("INSERT INTO T_HP_FEEDBACK (cd_feedback, dt_feedback, tp_previsao_correta) values (?, sysdate, ?)");

	    stm.setInt(1, feedback.getId());
	    feedback.setDataFeedback(LocalDateTime.now());
	    stm.setBoolean(2, feedback.getTipoPrevisao());
	    
	    stm.executeUpdate();
	}

	
	public List<Feedback> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from T_HP_FEEDBACK");

		ResultSet result = stm.executeQuery();

		List<Feedback> lista = new ArrayList<Feedback>();
		while (result.next()) {
			Feedback feedback = parse(result);
			lista.add(feedback);
		}
		    
		return lista;
	}
	
	public Feedback pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " T_HP_FEEDBACK where cd_feedback = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) 
			throw new NotFoundException("Feedback não encontrado");
		
		Feedback feedback = parse(result);
		
		return feedback;
	}

	public void remover(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("delete from T_HP_FEEDBACK where cd_feedback = ?");

		stm.setInt(1, id);

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Feedback não encontrado");
	}
	
	private Feedback parse(ResultSet result) throws SQLException {
	    int id = result.getInt("cd_feedback");
	    LocalDateTime dataFeedback = result.getObject("dt_feedback", LocalDateTime.class);
	    boolean tipoPrevisao = result.getBoolean("tp_previsao_correta");

	    Feedback feedback = new Feedback(id, dataFeedback, tipoPrevisao);

	    return feedback;
	}
	
}
