package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.Person;
import errorhandling.EntityNotFoundException;

import errorhandling.InternalErrorException;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PersonFacade FACADE =  PersonFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String jsonInput){
        PersonDTO person = GSON.fromJson(jsonInput, PersonDTO.class);
        PersonDTO returned = FACADE.create(person);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }


    @POST
    @Path("/{id}/address")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@PathParam("id") int id, String jsonInput) throws EntityNotFoundException {
        AddressDTO address = GSON.fromJson(jsonInput, AddressDTO.class);
        AddressDTO returned = FACADE.create(id, address);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) throws EntityNotFoundException {
        PersonDTO person = FACADE.getById(id);
        return Response.ok().entity(GSON.toJson(person)).build();
    }

    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

//    throws EntityNotFoundException

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") int id, String jsonInput)  {
        PersonDTO personDTO = GSON.fromJson(jsonInput, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO updated = FACADE.update(personDTO);
        return Response.ok().entity(GSON.toJson(updated)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePerson(@PathParam("id") int id) throws EntityNotFoundException, InternalErrorException {
        PersonDTO deleted = FACADE.deletePerson(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }

    @DELETE
    @Path("{id}/address")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteAddress(@PathParam("id") int id) throws EntityNotFoundException, InternalErrorException {
        PersonDTO personWithoutAddress = FACADE.deleteAddress(id);
        return Response.ok().entity(GSON.toJson(personWithoutAddress)).build();
    }



}
