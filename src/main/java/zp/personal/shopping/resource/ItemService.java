package zp.personal.shopping.resource;

import zp.personal.shopping.bean.Item;
import zp.personal.shopping.mapper.ItemMapper;

import javax.ws.rs.*;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("item")
public class ItemService {
    @Inject
    ItemMapper itemMapper;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<Item> items = itemMapper.getAllItems();

        if (items == null || items.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(items).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") int id) {
        Item item = itemMapper.getItemById(id);

        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(item).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewItem(Map itemMap) {
        Item item = new Item()
            .setName((String)itemMap.get("name"));
        int result = itemMapper.insertItem(item);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItemById(@PathParam("id") int id) {
        Item item = itemMapper.getItemById(id);
        if(item == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        int result = itemMapper.deleteItemById(id);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrCreateItem(Map itemMap) {
        Integer id = (Integer) itemMap.get("id");
        if(id == null || itemMapper.getItemById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Item item = new Item()
            .setId(id)
            .setName((String)itemMap.get("name"));
        int result = itemMapper.updateItem(item);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
