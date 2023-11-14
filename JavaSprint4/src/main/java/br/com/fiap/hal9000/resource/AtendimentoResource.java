package br.com.fiap.hal9000.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Atendimento;
import br.com.fiap.hal9000.service.AtendimentoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/atendimento")
public class AtendimentoResource {
	
	private AtendimentoService atendimentoService;
	
	public AtendimentoResource() throws ClassNotFoundException, SQLException {
		atendimentoService = new AtendimentoService();
	}
	
	//POST http://localhost:8080/JavaSprint4/api/atendimento/ (Cadastra um atendimento)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Atendimento atendimento, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			atendimentoService.cadastrar(atendimento);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(atendimento.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadRequestException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
			.entity(e.getMessage()).build();
		}
	}
	
	//GET http://localhost:8080/JavaSprint4/api/atendimento/ (Listar todos os atendimentos)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Atendimento> lista() throws ClassNotFoundException, SQLException, NotFoundException {
		return atendimentoService.listar();
	}
	
	//GET http://localhost:8080/JavaSprint4/api/atendimento/{id} (Pesquisa atendimento pelo Id)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(atendimentoService.pesquisar(id)).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
