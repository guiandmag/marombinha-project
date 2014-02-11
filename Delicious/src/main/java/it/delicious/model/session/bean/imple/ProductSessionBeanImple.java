/**
 * 
 */
package it.delicious.model.session.bean.imple;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import it.delicious.model.session.SessionBean;
import it.delicious.model.session.bean.ProductSessionBean;
import it.delicious.persistence.entity.Category;
import it.delicious.persistence.entity.Product;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProductSessionBeanImple extends SessionBean implements
		ProductSessionBean {

	private static final long serialVersionUID = 1L;

	@Override
	public Product saveProduct(Product prod) {
		getEm().persist(prod);
		return prod;
	}

	@Override
	public Product setProduct(Product prod) {
		return getEm().merge(prod);
	}

	@Override
	public void removeProduct(Product prod) {
		Product productToRemove = getEm().merge(prod);
		getEm().remove(productToRemove);
	}

	@Override
	public Product getProductById(int idProduct) {
		return getPojo(Product.class, idProduct);
	}

	@Override
	public List<Product> getAllProducts() {
		try{
			return getEm().createNamedQuery("product.findAll", Product.class).getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Product> getProductsByName(String nameProduct) {
		try{
			return getEm().createNamedQuery("product.getProductByName", Product.class).setParameter("nameCategory", "%" + nameProduct + "%").getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Product> getAllProductsBySpecOrName(String nameOfProduct) {
		try{
			return getEm().createNamedQuery("product.getAllProductsBySpecOrName", Product.class).setParameter("nameProduct", nameOfProduct).getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public Category saveCategory(Category cat) {
		getEm().persist(cat);
		return cat;
	}

	@Override
	public Category setCategory(Category cat) {
		return getEm().merge(cat);
	}

	@Override
	public void removeCategory(Category cat) {
		Category categoryToRemove = getEm().merge(cat);
		getEm().remove(categoryToRemove);
	}

	@Override
	public Category getCategoryById(int idCategory) {
		return getPojo(Category.class, idCategory);
	}

	@Override
	public Category getCategoryByName(String name) {
		try{
			return getEm().createNamedQuery("category.getCategoryByName", Category.class).setParameter("nameCategory", name).getSingleResult();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Category> getAllCategory() {
		try{
			return getEm().createNamedQuery("category.findAll", Category.class).getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Category> getCategoriesByName(String name) {
		try{
			return getEm().createNamedQuery("category.getCategoriesByName", Category.class).setParameter("nameCategory", "%" + name + "%").getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Category> getActiveCategories() {
		try{
			return getEm().createNamedQuery("category.getActiveCategories", Category.class).getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
}