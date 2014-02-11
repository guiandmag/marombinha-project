package it.delicious.persistence.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;

/**
 * Entity implementation class for Entity: Sell
 *
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Entity
@Table
@SequenceGenerator(name = Sell.SELL_SEQUENCE, sequenceName = Sell.SELL_SEQUENCE, initialValue = 1, allocationSize = 50)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "sell.findAll", query = "SELECT s FROM Sell s")})
public class Sell implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String SELL_SEQUENCE = "SELL_SEQUENCE";
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SELL_SEQUENCE)
	@Column(name = "sell_id", updatable =false)
	@XmlAttribute
	private Integer idSell;
	
	@Column(name = "sell_date", nullable = false, length = 8)
	@Temporal(TemporalType.DATE)
	private Date sellDate;
	
	@Column(name = "sell_total_value", nullable = false, length = 8)
	@Min(0)
	private BigDecimal totalValue;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(referencedColumnName = "users_id", name = "sell_users", nullable = false)
	@ForeignKey(name = "fk_sell_users")
	@Valid
	private Users users;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(referencedColumnName = "address_id", name = "sell_address", nullable = false)
	@ForeignKey(name = "fk_sell_address")
	@Valid
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sell", targetEntity = Orders.class, orphanRemoval = true)
	private List<Orders> orders = new LinkedList<Orders>();

	public Sell() {
		super();
	}

	/**
	 * @param idSell
	 * @param sellDate
	 * @param totalValue
	 * @param users
	 * @param address
	 * @param orders
	 */
	public Sell(Integer idSell, Date sellDate, BigDecimal totalValue,
			Users users, Address address, List<Orders> orders) {
		super();
		this.idSell = idSell;
		this.sellDate = sellDate;
		this.totalValue = totalValue;
		this.users = users;
		this.address = address;
		this.orders = orders;
	}

	/**
	 * @return the idSell
	 */
	public Integer getIdSell() {
		return idSell;
	}

	/**
	 * @param idSell the idSell to set
	 */
	public void setIdSell(Integer idSell) {
		this.idSell = idSell;
	}

	/**
	 * @return the sellDate
	 */
	public Date getSellDate() {
		return sellDate;
	}

	/**
	 * @param sellDate the sellDate to set
	 */
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	/**
	 * @return the totalValue
	 */
	public BigDecimal getTotalValue() {
		return totalValue;
	}

	/**
	 * @param totalValue the totalValue to set
	 */
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the orders
	 */
	public List<Orders> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((idSell == null) ? 0 : idSell.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result
				+ ((sellDate == null) ? 0 : sellDate.hashCode());
		result = prime * result
				+ ((totalValue == null) ? 0 : totalValue.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Sell other = (Sell) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (idSell == null) {
			if (other.idSell != null)
				return false;
		} else if (!idSell.equals(other.idSell))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (sellDate == null) {
			if (other.sellDate != null)
				return false;
		} else if (!sellDate.equals(other.sellDate))
			return false;
		if (totalValue == null) {
			if (other.totalValue != null)
				return false;
		} else if (!totalValue.equals(other.totalValue))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sell [idSell=" + idSell + ", sellDate=" + sellDate
				+ ", totalValue=" + totalValue + ", users=" + users
				+ ", address=" + address + ", orders=" + orders + "]";
	}   
}