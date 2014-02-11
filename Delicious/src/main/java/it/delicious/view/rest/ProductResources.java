/**
 * 
 */
package it.delicious.view.rest;

import java.util.List;

import it.delicious.model.session.ManualCDILookup;
import it.delicious.model.session.bean.ProductSessionBean;
import it.delicious.persistence.entity.Product;

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
@Path("/product")
public class ProductResources extends ManualCDILookup {

	@Inject
	private ProductSessionBean bean;
	
	public ProductResources(){
		bean = getFacadeWithJNDI(ProductSessionBean.class);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "list")
	public List<Product> getAllProducts(){
		return bean.getAllProducts();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list/{name}")
	public List<Product> getProductsByName(@PathParam("name") String name){
		return bean.getAllProductsBySpecOrName(name);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get/{id}")
	public Product getProductsById(@PathParam("id") int idProduct){
		return bean.getProductById(idProduct);
	}
}