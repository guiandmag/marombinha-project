package it.delicious.persistence.entity;

import java.io.Serializable;
import java.lang.Integer;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;

/**
 * Entity implementation class for Entity: Product
 * 
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Entity
@Table
@SequenceGenerator(name = Product.PRODUCT_SEQUENCE, sequenceName = Product.PRODUCT_SEQUENCE, initialValue = 1, allocationSize = 50)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "product.findAll", query = "SELECT p FROM Product p"),
	@NamedQuery(name = "product.getProductByName", query = "SELECT p FROM Product p INNER JOIN p.category c WHERE c.categoryActive = TRUE AND p.nameProduct LIKE :nameProduct"),
	@NamedQuery(name = "product.getAllProductsBySpecOrName", query = "SELECT p FROM Product p WHERE p.nameProduct LIKE :nameProduct OR p.specProduct LIKE :nameProduct")
})
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PRODUCT_SEQUENCE = "PRODUCT_SEQUENCE";
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PRODUCT_SEQUENCE)
	@Column(name = "product_id", updatable = false)
	@XmlAttribute
	private Integer idProduct;
	
	@Column(name = "product_name", nullable = true, length = 255)
	@XmlElement
	@OrderBy
	private String nameProduct;
	
	@Column(name = "product_spec", nullable = false, length = 500)
	@XmlElement
	private String specProduct;

	@Column(name = "product_photo", nullable = false, length = 255)
	@XmlElement
	private String photoProduct;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(referencedColumnName = "category_id", name = "product_category", nullable = false, columnDefinition = "NUMBER(15)")
	@ForeignKey(name = "fk_product_category")
	@Valid
	@XmlElement
	private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(referencedColumnName = "orders_id", name = "product_orders", nullable = false, columnDefinition = "NUMBER(15)")
	@ForeignKey(name = "fk_product_orders")
	@Valid
	@XmlElement
	private Orders orders;
	
	public Product() {
		super();
	}

	/**
	 * @param idProduct
	 * @param nameProduct
	 * @param specProduct
	 * @param photoProduct
	 * @param category
	 * @param orders
	 */
	public Product(Integer idProduct, String nameProduct, String specProduct,
			String photoProduct, Category category, Orders orders) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.specProduct = specProduct;
		this.photoProduct = photoProduct;
		this.category = category;
		this.orders = orders;
	}

	/**
	 * @return the idProduct
	 */
	public Integer getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * @return the nameProduct
	 */
	public String getNameProduct() {
		return nameProduct;
	}

	/**
	 * @param nameProduct the nameProduct to set
	 */
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	/**
	 * @return the specProduct
	 */
	public String getSpecProduct() {
		return specProduct;
	}

	/**
	 * @param specProduct the specProduct to set
	 */
	public void setSpecProduct(String specProduct) {
		this.specProduct = specProduct;
	}

	/**
	 * @return the photoProduct
	 */
	public String getPhotoProduct() {
		return photoProduct;
	}

	/**
	 * @param photoProduct the photoProduct to set
	 */
	public void setPhotoProduct(String photoProduct) {
		this.photoProduct = photoProduct;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the orders
	 */
	public Orders getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((idProduct == null) ? 0 : idProduct.hashCode());
		result = prime * result
				+ ((nameProduct == null) ? 0 : nameProduct.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result
				+ ((photoProduct == null) ? 0 : photoProduct.hashCode());
		result = prime * result
				+ ((specProduct == null) ? 0 : specProduct.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		if (nameProduct == null) {
			if (other.nameProduct != null)
				return false;
		} else if (!nameProduct.equals(other.nameProduct))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (photoProduct == null) {
			if (other.photoProduct != null)
				return false;
		} else if (!photoProduct.equals(other.photoProduct))
			return false;
		if (specProduct == null) {
			if (other.specProduct != null)
				return false;
		} else if (!specProduct.equals(other.specProduct))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct="
				+ nameProduct + ", specProduct=" + specProduct
				+ ", photoProduct=" + photoProduct + ", category=" + category
				+ ", orders=" + orders + "]";
	}   
}