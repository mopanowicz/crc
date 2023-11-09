package com.example.demoapi.item;

import jakarta.persistence.NoResultException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@Path(ItemController.API_PATH)
public class ItemController {

    static final Logger log = LoggerFactory.getLogger(ItemController.class);

    static final String API_PATH = "/v1/items";

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody Item item) {
        item = itemService.create(item);
        return Response.created(URI.create(API_PATH + "/" + item.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok(itemService.get()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        try {
            return Response.ok(itemService.get(id)).build();
        } catch (NoResultException e) {
            log.error(e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Item item) {
        try {
            return Response.ok(itemService.update(item)).build();
        } catch (NoResultException e) {
            log.error(e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            itemService.delete(id);
            return Response.noContent().build();
        } catch (NoResultException e) {
            log.error(e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
