package br.com.fiap.hal9000.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Modal;
import br.com.fiap.hal9000.service.ModalService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/modal")
public class ModalResource {
	
	private ModalService modalService;
	
	public ModalResource() throws ClassNotFoundException, SQLException {
		modalService = new ModalService();
	}
	
	//GET http://localhost:8080/JavaSprint4/api/modal/ (Listar todos os modais)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Modal> lista() throws ClassNotFoundException, SQLException {
		return modalService.listar();
	}
	
	//GET http://localhost:8080/JavaSprint4/api/modal/{id} (Pesquisa modal pelo Id)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(modalService.pesquisar(id)).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
