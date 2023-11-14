package br.com.fiap.hal9000.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Endereco;
import br.com.fiap.hal9000.service.EnderecoService;
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

@Path("/endereco")
public class EnderecoResource {

	private EnderecoService enderecoService;
	
	public EnderecoResource() throws ClassNotFoundException, SQLException {
		enderecoService = new EnderecoService();
	}
	
	//POST http://localhost:8080/JavaSprint4/api/endereco/ (Cadastra um endereco)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Endereco endereco, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			enderecoService.cadastrar(endereco);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(endereco.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadRequestException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
			.entity(e.getMessage()).build();
		}
	}
	
	//GET http://localhost:8080/JavaSprint4/api/endereco/ (Listar todos os enderecos)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Endereco> lista() throws ClassNotFoundException, SQLException {
		return enderecoService.listar();
	}
	
	//GET http://localhost:8080/JavaSprint4/api/endereco/{id} (Pesquisa endereco pelo Id)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(enderecoService.pesquisar(id)).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	//PUT http://localhost:8080/JavaSprint4/api/endereco/{id} (Atualiza um endereco)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Endereco endereco, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			endereco.setId(id);
			enderecoService.atualizar(endereco);
			return Response.ok().build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadRequestException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	//DELETE http://localhost:8080/JavaSprint4/api/endereco/{id} (Apaga um endereco)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			enderecoService.remover(id);
			return Response.noContent().build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
