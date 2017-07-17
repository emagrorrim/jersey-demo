package zp.personal.shopping.resource;

import zp.personal.shopping.bean.Category;
import zp.personal.shopping.bean.Item;
import zp.personal.shopping.mapper.CategoryMapper;
import zp.personal.shopping.mapper.ItemMapper;

import javax.ws.rs.*;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("items")
public class ItemService {
    @Inject
    ItemMapper itemMapper;
    @Inject
    CategoryMapper categoryMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<Item> items = itemMapper.getAllItems();

        if (items == null || items.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Map[] maps = new Map[items.size()];
        for (int i = 0; i < items.size(); i++) {
            maps[i] = items.get(i).toMap();
        }
        return Response.status(Response.Status.OK).entity(maps).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") int id) {
        Item item = itemMapper.getItemById(id);

        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(item.toMap()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewItem(Map itemMap) {
        Category category = categoryMapper.getCategoryByName((String)itemMap.get("category"));
        if (category == null) {
            return Response.status(404).build();
        }
        Item item = new Item()
            .setName((String)itemMap.get("name"))
            .setPrice((int)itemMap.get("price"))
            .setAvailable(0)
            .setCategory(category);
        int result = itemMapper.insertItem(item);
        item = itemMapper.getItemByName((String)itemMap.get("name"));
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("uri", "items/" + item.getId());
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItemById(@PathParam("id") int id) {
        Item item = itemMapper.getItemById(id).setAvailable(1);
        if(item == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        int result = itemMapper.updateItem(item);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", 204);
        return Response.status(204).entity(response).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(Map itemMap) {
        Category category = categoryMapper.getCategoryByName((String)itemMap.get("category"));
        if (category == null) {
            return Response.status(404).build();
        }

        Integer id = (Integer) itemMap.get("id");
        Item item = itemMapper.getItemById(id);
        if(item == null) {
            return Response.status(404).entity("Error: item not found").build();
        }
        item.setAvailable(1);
        itemMapper.updateItem(item);
        item.setPrice((int)itemMap.get("price"))
            .setAvailable(0)
            .setName((String)itemMap.get("name"))
            .setCategory(category);
        int result = itemMapper.insertItem(item);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
