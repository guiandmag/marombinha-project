/**
 * 
 */
package it.delicious.model.session.bean;

import java.util.List;

import it.delicious.persistence.entity.Category;
import it.delicious.persistence.entity.Product;

import javax.ejb.Local;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Local
public interface ProductSessionBean {
	
	public Product saveProduct(Product prod);
	
	public Product setProduct(Product prod);
	
	public void removeProduct(Product prod);
	
	public Product getProductById(int idProduct);
	
	public List<Product> getAllProducts();
	
	public List<Product> getProductsByName(String name);;

	public List<Product> getAllProductsBySpecOrName(String nameOfProduct);
	
	public Category saveCategory(Category cat);
	
	public Category setCategory(Category cat);
	
	public void removeCategory(Category cat);
	
	public Category getCategoryById(int idCategory);
	
	public Category getCategoryByName(String name);
	
	public List<Category> getAllCategory();
	
	public List<Category> getCategoriesByName(String name);
	
	public List<Category> getActiveCategories();

}
