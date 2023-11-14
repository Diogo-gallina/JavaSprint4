package br.com.fiap.hal9000.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.dao.VeiculoDao;
import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.factory.ConnectionFactory;
import br.com.fiap.hal9000.model.Veiculo;
import br.com.fiap.hal9000.utils.NumberUtils;
import br.com.fiap.hal9000.utils.StringUtils;

public class VeiculoService {
	
	private VeiculoDao veiculoDao;
	
	public VeiculoService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		veiculoDao = new VeiculoDao(conn);
	}
	
	public void cadastrar(Veiculo veiculo) throws ClassNotFoundException, SQLException, BadRequestException {
		validar(veiculo);
		
		int id = NumberUtils.gerarId(10000000, 99999999);
		
		veiculo.setId(id);
		
	}
	
	public List<Veiculo> listar() throws ClassNotFoundException, SQLException{
		return veiculoDao.listar();
	}
	
	public Veiculo pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException{
		Veiculo veiculo = veiculoDao.pesquisar(id);
		return veiculo;
	}
	
	public void atualizar(Veiculo veiculo) throws ClassNotFoundException, SQLException, NotFoundException, BadRequestException {
		this.validar(veiculo);
		veiculoDao.atualizar(veiculo);
	}
	
	private void validar(Veiculo veiculo) throws BadRequestException {
		
		if (StringUtils.isNullOrEmpty(veiculo.getMarca()) || StringUtils.hasMoreThan(veiculo.getMarca(), 20)) 
			throw new BadRequestException("Marca inválida, não pode ser nula e deve ter no maximo 20 caracteres");
		
		if (NumberUtils.isNullOrLessThan0(veiculo.getCarga())) 
			throw new BadRequestException("Carga não pode ser nula ou menor do que 0");
		
		if (NumberUtils.isNullOrLessThan0(veiculo.getTara())) 
			throw new BadRequestException("Tara não pode ser nula ou menor do que 0");
		
		if (NumberUtils.isNullOrLessThan0(veiculo.getTamanho())) 
			throw new BadRequestException("Tamanho não pode ser nulo ou menor do que 0");
		
	    if (StringUtils.isNullOrEmpty(veiculo.getTipoVeiculo()) || StringUtils.hasMoreThan(veiculo.getTipoVeiculo(), 80)) 
	        throw new BadRequestException("Tipo de veiculo inválido");
	    
	}
	
	
}
