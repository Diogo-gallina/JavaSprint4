package br.com.fiap.hal9000.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.dao.UsuarioDao;
import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.factory.ConnectionFactory;
import br.com.fiap.hal9000.model.Usuario;
import br.com.fiap.hal9000.utils.NumberUtils;
import br.com.fiap.hal9000.utils.StringUtils;

public class UsuarioService {
	
	private UsuarioDao usuarioDao;
	
	public UsuarioService() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		usuarioDao = new UsuarioDao(conn);
	}
	
	public void cadastrar(Usuario usuario) throws ClassNotFoundException, SQLException, BadRequestException {
		validar(usuario);
		
		int id = NumberUtils.gerarId(10000000, 99999999);
		
		usuario.setId(id);
		
		usuarioDao.cadastrar(usuario);
	}
	
	public List<Usuario> listar() throws ClassNotFoundException, SQLException{
		return usuarioDao.listar();
	}
	
	public Usuario pesquisar(int id) throws ClassNotFoundException, SQLException, NotFoundException{
		Usuario usuario = usuarioDao.pesquisar(id);
		return usuario;
	}
	
	public void atualizar(Usuario usuario) throws ClassNotFoundException, SQLException, NotFoundException, BadRequestException {
		this.validar(usuario);
		usuarioDao.atualizar(usuario);
	}
	
	public void remover(int id) throws ClassNotFoundException, SQLException, NotFoundException {
		usuarioDao.remover(id);
	}
	
	private void validar(Usuario usuario) throws BadRequestException {
		
		if (StringUtils.isNullOrEmpty(usuario.getNome()) || StringUtils.hasMoreThan(usuario.getNome(), 70)) 
			throw new BadRequestException("Nome inválido, não pode ser nulo e deve ter no maximo 70 caracteres");
		
		if (StringUtils.isNullOrEmpty(usuario.getEmail()) || StringUtils.hasMoreThan(usuario.getEmail(), 80) || !usuario.getEmail().contains("@")) 
			throw new BadRequestException("Email deve ter mais de 80 caracteres, deve conter '@' e não pode ser vazio!");
		
		if (StringUtils.isNullOrEmpty(usuario.getSenha()) || !StringUtils.hasMoreThan(usuario.getSenha(), 7)) 
			throw new BadRequestException("Senha deve ter mais de 8 caracteres e não pode ser vazia!!");
		
	}
	
}
