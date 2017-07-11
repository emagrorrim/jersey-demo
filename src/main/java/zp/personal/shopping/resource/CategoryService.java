package zp.personal.shopping.resource;

import zp.personal.shopping.bean.Category;
import zp.personal.shopping.mapper.CategoryMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("category")
public class CategoryService {
    @Inject
    CategoryMapper categoryMapper;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<Category> categories = categoryMapper.getAllCategories();

        if (categories == null || categories.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(categories).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") int id) {
        Category category = categoryMapper.getCategoryById(id);

        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(category).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewItem(Map itemMap) {
        Category category = new Category()
            .setName((String)itemMap.get("name"));
        int result = categoryMapper.insertCategory(category);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItemById(@PathParam("id") int id) {
        Category category = categoryMapper.getCategoryById(id);
        if(category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        int result = categoryMapper.deleteCategoryById(id);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrCreateItem(Map itemMap) {
        Integer id = (Integer) itemMap.get("id");
        if(id == null || categoryMapper.getCategoryById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Category category = new Category()
            .setId(id)
            .setName((String)itemMap.get("name"));
        int result = categoryMapper.updateCategory(category);
        if(result == 0) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
