package br.com.fiap.hal9000.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Usuario;
import br.com.fiap.hal9000.service.UsuarioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/usuario")
public class UsuarioResource {

	private UsuarioService usuarioService;
	
	public UsuarioResource() throws ClassNotFoundException, SQLException {
		usuarioService = new UsuarioService();
	}
	
	//POST http://localhost:8080/JavaSprint4/api/usuario/ (Cadastra um usuario)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Usuario usuario, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			usuarioService.cadastrar(usuario);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(usuario.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadRequestException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
			.entity(e.getMessage()).build();
		}
	}
	
	//GET http://localhost:8080/JavaSprint4/api/usuario/ (Listar todos os usuarios)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> lista() throws ClassNotFoundException, SQLException {
		return usuarioService.listar();
	}
	
	//GET http://localhost:8080/JavaSprint4/api/usuario/{id} (Pesquisa usuario pelo Id)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(usuarioService.pesquisar(id)).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	//PUT http://localhost:8080/JavaSprint4/api/usuario/{id} (Atualiza um usuario)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Usuario usuario, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			usuario.setId(id);
			usuarioService.atualizar(usuario);
			return Response.ok().build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadRequestException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	//DELETE http://localhost:8080/JavaSprint4/api/usuario/{id} (Apaga um usuario)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			usuarioService.remover(id);
			return Response.noContent().build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
