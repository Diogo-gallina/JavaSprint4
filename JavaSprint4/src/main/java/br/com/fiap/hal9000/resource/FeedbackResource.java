package br.com.fiap.hal9000.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.hal9000.exception.BadRequestException;
import br.com.fiap.hal9000.exception.NotFoundException;
import br.com.fiap.hal9000.model.Feedback;
import br.com.fiap.hal9000.service.FeedbackService;
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

@Path("/feedback")
public class FeedbackResource {

	private FeedbackService feedbackService;
	
	public FeedbackResource() throws ClassNotFoundException, SQLException {
		feedbackService = new FeedbackService();
	}
	
	
	//POST http://localhost:8080/JavaSprint4/api/feedback/ (Cadastra um feedback)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Feedback feedback, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			feedbackService.cadastrar(feedback);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(feedback.getId()));
			return Response.created(uriBuilder.build()).build();
		} catch (BadRequestException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
			.entity(e.getMessage()).build();
		}
	}
	
	//GET http://localhost:8080/JavaSprint4/api/feedback/ (Listar todos os feedbacks)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Feedback> lista() throws ClassNotFoundException, SQLException {
		return feedbackService.listar();
	}
	
	//GET http://localhost:8080/JavaSprint4/api/feedback/{id} (Pesquisa feedback pelo Id)
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response busca(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			return Response.ok(feedbackService.pesquisar(id)).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
