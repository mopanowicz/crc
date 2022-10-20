package com.example.demoapi.controller;

import com.example.demoapi.dto.ItemDTO;
import com.example.demoapi.service.ItemService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;

@Path(ItemController.API_PATH)
public class ItemController {

    static final Logger log = LoggerFactory.getLogger(ItemController.class);

    static final String API_PATH = "/v1/items";

    @Inject
    ItemService itemService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody ItemDTO dto) {
        dto = itemService.create(dto);
        return Response.created(URI.create(API_PATH + "/" + dto.getId())).build();
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
    public Response update(ItemDTO dto) {
        try {
            return Response.ok(itemService.update(dto)).build();
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
