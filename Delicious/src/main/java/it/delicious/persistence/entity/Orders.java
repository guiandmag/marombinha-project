package it.delicious.persistence.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;

/**
 * Entity implementation class for Entity: Orders
 * 
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Entity
@Table
@SequenceGenerator(name = Orders.ORDER_SEQUENCE, sequenceName = Orders.ORDER_SEQUENCE, initialValue = 1, allocationSize = 50)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "orders.findAll", query = "SELECT o FROM Orders o")})
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ORDER_SEQUENCE = "ORDER_SEQUENCE";
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORDER_SEQUENCE)
	@Column(name = "orders_id", updatable = false)
	@XmlAttribute
	private Integer idOrders;
	
	@Column(name = "orders_qntproduct", nullable = false, length = 3)
	@Min(1)
	private int qntProduct = 1;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(referencedColumnName = "sell_id", name = "orders_sell", nullable = false)
	@ForeignKey(name = "fk_orders_sell")
	@Valid
	private Sell sell;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orders", orphanRemoval = true)
	@XmlElementWrapper
	private List<Product> products = new LinkedList<Product>();

	public Orders() {
		super();
	}

	/**
	 * @param idOrders
	 * @param qntProduct
	 * @param sell
	 * @param products
	 */
	public Orders(Integer idOrders, int qntProduct, Sell sell,
			List<Product> products) {
		super();
		this.idOrders = idOrders;
		this.qntProduct = qntProduct;
		this.sell = sell;
		this.products = products;
	}

	/**
	 * @return the idOrders
	 */
	public Integer getIdOrders() {
		return idOrders;
	}

	/**
	 * @param idOrders the idOrders to set
	 */
	public void setIdOrders(Integer idOrders) {
		this.idOrders = idOrders;
	}

	/**
	 * @return the qntProduct
	 */
	public int getQntProduct() {
		return qntProduct;
	}

	/**
	 * @param qntProduct the qntProduct to set
	 */
	public void setQntProduct(int qntProduct) {
		this.qntProduct = qntProduct;
	}

	/**
	 * @return the sell
	 */
	public Sell getSell() {
		return sell;
	}

	/**
	 * @param sell the sell to set
	 */
	public void setSell(Sell sell) {
		this.sell = sell;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOrders == null) ? 0 : idOrders.hashCode());
		result = prime * result
				+ ((products == null) ? 0 : products.hashCode());
		result = prime * result + qntProduct;
		result = prime * result + ((sell == null) ? 0 : sell.hashCode());
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
		Orders other = (Orders) obj;
		if (idOrders == null) {
			if (other.idOrders != null)
				return false;
		} else if (!idOrders.equals(other.idOrders))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (qntProduct != other.qntProduct)
			return false;
		if (sell == null) {
			if (other.sell != null)
				return false;
		} else if (!sell.equals(other.sell))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Orders [idOrders=" + idOrders + ", qntProduct=" + qntProduct
				+ ", sell=" + sell + ", products=" + products + "]";
	}   
}