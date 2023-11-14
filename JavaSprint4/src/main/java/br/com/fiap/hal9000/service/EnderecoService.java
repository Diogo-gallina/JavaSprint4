package br.com.fiap.hal9000.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.dao.EnderecoDao;
import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.factory.ConnectionFactory;
import br.com.fiap.hal9000.model.Endereco;
import br.com.fiap.hal9000.utils.NumberUtils;
import br.com.fiap.hal9000.utils.StringUtils;

public class EnderecoService {

private EnderecoDao enderecoDao;
	
	public EnderecoService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		enderecoDao = new EnderecoDao(conn);
	}
	
	public void cadastrar(Endereco endereco) throws ClassNotFoundException, SQLException, BadRequestException {
		validar(endereco);
		
		int id = NumberUtils.gerarId(10000000, 99999999);
		
		endereco.setId(id);
		
		enderecoDao.cadastrar(endereco);
	}
	
	public List<Endereco> listar() throws ClassNotFoundException, SQLException{
		return enderecoDao.listar();
	}
	
	public Endereco pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException{
		Endereco endereco = enderecoDao.pesquisar(id);
		return endereco;
	}
	
	public void atualizar(Endereco endereco) throws ClassNotFoundException, SQLException, NotFoundException, BadRequestException {
		this.validar(endereco);
		enderecoDao.atualizar(endereco);
	}
	
	public void remover(int id) throws ClassNotFoundException, SQLException, NotFoundException {
		enderecoDao.remover(id);
	}
	
	private void validar(Endereco endereco) throws BadRequestException {
		
		if (StringUtils.isNullOrEmpty(endereco.getLogradouro()) || StringUtils.hasMoreThan(endereco.getLogradouro(), 100)) 
			throw new BadRequestException("Logradouro inválido, não pode ser nulo e deve ter no maximo 100 caracteres");
		
		if (StringUtils.isNullOrEmpty(endereco.getBairro()) || StringUtils.hasMoreThan(endereco.getBairro(), 100)) 
			throw new BadRequestException("Bairro inválido, não pode ser nulo e deve ter no maximo 100 caracteres");
		
		if (StringUtils.isNullOrEmpty(endereco.getCep()) || !StringUtils.has(endereco.getCep(), 8)) 
			throw new BadRequestException("Cep invalido, CEP deve ter exatamente 8 caracteres!");
		
		if (NumberUtils.isNullOrLessThan0(endereco.getNumero()))
			throw new BadRequestException("Número não pode ser nulo e nem menor que zero!");
		
	}
	
}
