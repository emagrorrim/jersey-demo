package zp.personal.shopping.resource;

import zp.personal.shopping.bean.Item;
import zp.personal.shopping.mapper.ItemMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("item")
public class ItemService {
    @Inject
    private ItemMapper itemMapper;

    ItemService() {}
    ItemService(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

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
}
