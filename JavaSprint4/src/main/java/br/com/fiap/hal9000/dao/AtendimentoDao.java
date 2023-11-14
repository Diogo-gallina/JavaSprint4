package br.com.fiap.hal9000.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Atendimento;
import br.com.fiap.hal9000.model.Endereco;
import br.com.fiap.hal9000.model.Feedback;
import br.com.fiap.hal9000.model.Modal;
import br.com.fiap.hal9000.model.Usuario;
import br.com.fiap.hal9000.model.Veiculo;

public class AtendimentoDao {
	
	private Connection conn;
	private ModalDao modalDao;
	private UsuarioDao usuarioDao;
	private VeiculoDao veiculoDao;
	private EnderecoDao enderecoDao;
	private FeedbackDao feedbackDao;

	public AtendimentoDao(Connection conn, ModalDao modalDao, UsuarioDao usuarioDao, VeiculoDao veiculoDao, EnderecoDao enderecoDao, FeedbackDao feedbackDao) {
	    this.conn = conn;
	    this.modalDao = modalDao;
	    this.usuarioDao = usuarioDao;
	    this.veiculoDao = veiculoDao;
	    this.enderecoDao = enderecoDao;
	    this.feedbackDao = feedbackDao;
	}
	
	public void cadastrar(Atendimento atendimento) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " T_HP_ATENDIMENTO (cd_atendimento, cd_modal, cd_usuario_porto,"
				+ " cd_veiculo, cd_endereco, cd_feedback, ds_os, dt_atendimento, nm_complexidade) "
				+ "values (?, ?, ?, ?, ?, ?, ?, sysdate, ?)");

		stm.setInt(1, atendimento.getId());
		stm.setInt(2, atendimento.getModal().getId());
		stm.setInt(3, atendimento.getUsuario().getId());
		stm.setInt (4, atendimento.getVeiculo().getId());
		stm.setInt (5, atendimento.getEndereco().getId());
		stm.setInt (6, atendimento.getFeedback().getId());
		stm.setString (7, atendimento.getOs());
		atendimento.setDataAtendimento(LocalDateTime.now());
		stm.setString (8, atendimento.getComplexidade());

		stm.executeUpdate();
	}
	
	
	public List<Atendimento> listar() throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from T_HP_ATENDIMENTO");

		ResultSet result = stm.executeQuery();

		List<Atendimento> lista = new ArrayList<Atendimento>();
		while (result.next()) {
			Atendimento atendimento = parse(result);
			lista.add(atendimento);
		}
		    
		return lista;
	}
	
	public Atendimento pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " T_HP_ATENDIMENTO where cd_atendimento = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) 
			throw new NotFoundException("Atendimento não encontrado");
		
		Atendimento atendimento = parse(result);
		
		return atendimento;
	}	
	
	private Atendimento parse(ResultSet result) throws SQLException, ClassNotFoundException, NotFoundException {
		int id = result.getInt("cd_atendimento");
		String os = result.getString("ds_os");
		LocalDateTime dataAtendimento = result.getObject("dt_atendimento", LocalDateTime.class);
		String complexidade = result.getString("nm_complexidade");
		
		int cdModal = result.getInt("cd_modal");
		int cdUsuario = result.getInt("cd_usuario_porto");
		int cdVeiculo = result.getInt("cd_veiculo");
		int cdEndereco = result.getInt("cd_endereco");
		int cdFeedback = result.getInt("cd_feedback");
		
		
		Atendimento atendimento = new Atendimento(id, os, dataAtendimento, complexidade);
		
		if (cdModal != 0) {
			Modal modal = modalDao.pesquisar(cdModal);
			atendimento.setModal(modal);
		}
		if (cdUsuario != 0) {
			Usuario usuario = usuarioDao.pesquisar(cdUsuario);
			atendimento.setUsuario(usuario);
		}
		if (cdVeiculo != 0) {
			Veiculo veiculo = veiculoDao.pesquisar(cdVeiculo);
			atendimento.setVeiculo(veiculo);
		}
		if (cdEndereco != 0) {
			Endereco endereco = enderecoDao.pesquisar(cdEndereco);
			atendimento.setEndereco(endereco);
		}
		if (cdFeedback != 0) {
			Feedback feedback = feedbackDao.pesquisar(cdFeedback);
			atendimento.setFeedback(feedback);
		}
		
		return atendimento;
	}
	
}
