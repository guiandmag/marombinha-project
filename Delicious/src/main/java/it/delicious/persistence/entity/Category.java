package it.delicious.persistence.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Category
 * 
 * @author Guilherme Magalhães - guiandmag@gmail.com
 *
 */
@Entity
@Table
@SequenceGenerator(name = Category.CATEGORY_SEQUENCE, sequenceName = Category.CATEGORY_SEQUENCE, initialValue = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "category.findAll", query = "SELECT c FROM Category c"),
	@NamedQuery(name = "category.getCategoryByName", query = "SELECT c FROM Category c WHERE c.categoryActive = TRUE AND c.nameCategory = :nameCategory"),
	@NamedQuery(name = "category.getCategoriesByName", query = "SELECT c FROM Category c WHERE c.categoryActive = TRUE AND c.nameCategory LIKE :nameCategory"),
	@NamedQuery(name = "category.getActiveCategories", query = "SELECT c FROM Category c WHERE c.categoryActive = TRUE ORDER BY c.nameCategory")
})
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String CATEGORY_SEQUENCE = "CATEGORY_SEQUENCE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CATEGORY_SEQUENCE)
	@Column(name = "category_id", updatable = false)
	@XmlAttribute
	private Integer idCategory;
	
	@Column(name = "category_name", nullable = false, length = 255, updatable = true)
	@XmlAttribute
	@OrderBy
	private String nameCategory;
	
	@Column(name = "category_active", nullable = false)
	@XmlElement
	private boolean categoryActive = true;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true)
	@XmlElementWrapper
	private List<Product> product = new LinkedList<Product>();

	public Category() {
		super();
	}   
	
	/**
	 * @param idCategory
	 * @param nameCategory
	 * @param categoryActive
	 * @param product
	 */
	public Category(Integer idCategory, String nameCategory,
			boolean categoryActive, List<Product> product) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.categoryActive = categoryActive;
		this.product = product;
	}

	public Integer getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	/**
	 * @return the nameCategory
	 */
	public String getNameCategory() {
		return nameCategory;
	}
	/**
	 * @param nameCategory the nameCategory to set
	 */
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	/**
	 * @return the categoryActive
	 */
	public boolean isCategoryActive() {
		return categoryActive;
	}
	/**
	 * @param categoryActive the categoryActive to set
	 */
	public void setCategoryActive(boolean categoryActive) {
		this.categoryActive = categoryActive;
	}
	/**
	 * @return the product
	 */
	public List<Product> getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (categoryActive ? 1231 : 1237);
		result = prime * result
				+ ((idCategory == null) ? 0 : idCategory.hashCode());
		result = prime * result
				+ ((nameCategory == null) ? 0 : nameCategory.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Category other = (Category) obj;
		if (categoryActive != other.categoryActive)
			return false;
		if (idCategory == null) {
			if (other.idCategory != null)
				return false;
		} else if (!idCategory.equals(other.idCategory))
			return false;
		if (nameCategory == null) {
			if (other.nameCategory != null)
				return false;
		} else if (!nameCategory.equals(other.nameCategory))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", nameCategory="
				+ nameCategory + ", categoryActive=" + categoryActive
				+ ", product=" + product + "]";
	}
}