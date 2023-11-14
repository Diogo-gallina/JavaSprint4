package br.com.fiap.hal9000.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Veiculo;
import br.com.fiap.hal9000.service.VeiculoService;
import jakarta.ws.rs.Consumes;
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

@Path("/veiculo")
public class VeiculoResource {

	private VeiculoService veiculoService;
	
	public VeiculoResource() throws ClassNotFoundException, SQLException {
		veiculoService = new VeiculoService();
	}
	
	//POST http://localhost:8080/JavaSprint4/api/veiculo/ (Cadastra um veiculo)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Veiculo veiculo, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			veiculoService.cadastrar(veiculo);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(veiculo.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadRequestException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
			.entity(e.getMessage()).build();
		}
	}
	
	//GET http://localhost:8080/JavaSprint4/api/veiculo/ (Listar todos os veiculo)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Veiculo> lista() throws ClassNotFoundException, SQLException {
		return veiculoService.listar();
	}
	
	//GET http://localhost:8080/JavaSprint4/api/veiculo/{id} (Pesquisa veiculo pelo Id)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(veiculoService.pesquisar(id)).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	//PUT http://localhost:8080/JavaSprint4/api/veiculo/{id} (Atualiza um veiculo)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Veiculo veiculo, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			veiculo.setId(id);
			veiculoService.atualizar(veiculo);
			return Response.ok().build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadRequestException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
}
