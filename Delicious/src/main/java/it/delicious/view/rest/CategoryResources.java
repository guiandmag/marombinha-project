/**
 * 
 */
package it.delicious.view.rest;

import java.util.List;

import it.delicious.model.session.ManualCDILookup;
import it.delicious.model.session.bean.ProductSessionBean;
import it.delicious.persistence.entity.Category;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Path("category")
public class CategoryResources extends ManualCDILookup {
	
	@Inject
	private ProductSessionBean bean;

	public CategoryResources(){
		bean = getFacadeWithJNDI(ProductSessionBean.class);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "list")
	public List<Category> getCategories(){
		return bean.getAllCategory();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "list/{name}")
	public List<Category> getCategoriesByName(@PathParam("name") String name){
		return bean.getCategoriesByName(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "get/{id}")
	public Category getCategoriesById(@PathParam("id") int idCategory){
		return bean.getCategoryById(idCategory);
	}
}