package br.com.fiap.hal9000.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.dao.AtendimentoDao;
import br.com.fiap.hal9000.dao.EnderecoDao;
import br.com.fiap.hal9000.dao.FeedbackDao;
import br.com.fiap.hal9000.dao.ModalDao;
import br.com.fiap.hal9000.dao.UsuarioDao;
import br.com.fiap.hal9000.dao.VeiculoDao;
import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.factory.ConnectionFactory;
import br.com.fiap.hal9000.model.Atendimento;
import br.com.fiap.hal9000.utils.NumberUtils;
import br.com.fiap.hal9000.utils.StringUtils;

public class AtendimentoService {

	private AtendimentoDao atendimentoDao;
	
	public AtendimentoService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		atendimentoDao = new AtendimentoDao(conn, new ModalDao(conn), new UsuarioDao(conn), new VeiculoDao(conn), new EnderecoDao(conn), new FeedbackDao(conn));
	}
	
	public void cadastrar(Atendimento atendimento) throws ClassNotFoundException, SQLException, BadRequestException {
		validar(atendimento);
		
		int id = NumberUtils.gerarId(10000000, 99999999);
		
		atendimento.setId(id);
		
		atendimentoDao.cadastrar(atendimento);
	}
	
	public List<Atendimento> listar() throws ClassNotFoundException, SQLException, NotFoundException{
		return atendimentoDao.listar();
	}
	
	public Atendimento pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException {
		Atendimento atendimento = atendimentoDao.pesquisar(id);
		return atendimento;
	}
	
	private void validar(Atendimento atendimento) throws BadRequestException {
		
		if (NumberUtils.isNullOrLessThan0(atendimento.getModal().getId())) 
			throw new BadRequestException("Id do Modal não pode ser nulo nem menor que 0");
		
		if (NumberUtils.isNullOrLessThan0(atendimento.getUsuario().getId())) 
			throw new BadRequestException("Id do Usuario não pode ser nulo nem menor que 0");
		
		if (NumberUtils.isNullOrLessThan0(atendimento.getVeiculo().getId())) 
			throw new BadRequestException("Id do Veiculo não pode ser nulo nem menor que 0");
		
		if (NumberUtils.isNullOrLessThan0(atendimento.getEndereco().getId())) 
			throw new BadRequestException("Id do Endereço não pode ser nulo nem menor que 0");
		
		if (NumberUtils.isNullOrLessThan0(atendimento.getFeedback().getId())) 
			throw new BadRequestException("Id do Feedback não pode ser nulo nem menor que 0");
		
		if (StringUtils.isNullOrEmpty(atendimento.getOs()) || StringUtils.hasMoreThan(atendimento.getOs(), 20))
			throw new BadRequestException("OS não pode ser vazia nem nula e não pode ter mais de 20 caracteres");

		if (StringUtils.isNullOrEmpty(atendimento.getComplexidade()) || StringUtils.hasMoreThan(atendimento.getComplexidade(), 100))
			throw new BadRequestException("Complexidade não pode ser vazia nem nula e não pode ter mais de 100 caracteres");	
	
	}
	
}
