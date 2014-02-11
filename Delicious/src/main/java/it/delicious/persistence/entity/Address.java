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
 * Entity implementation class for Entity: Address
 * 
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Entity
@Table
@SequenceGenerator(name = Address.ADDRESS_SEQUENCE, sequenceName = Address.ADDRESS_SEQUENCE, initialValue = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "address.findAll", query = "SELECT a FROM Address a")})
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ADDRESS_SEQUENCE = "ADDRESS_SEQUENCE";
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ADDRESS_SEQUENCE)
	@Column(name = "address_id", updatable = false)
	@XmlAttribute
	private Integer idAddress;
	
	@Column(name = "address_street", nullable = false, length = 255)
	@XmlAttribute
	private String addressStreet;
	
	@Column(name = "address_zipcode", nullable = false, length = 12)
	@XmlElement
	private String cep;
	
	@Column(name = "address_number", nullable = false, length = 4)
	@XmlElement
	private int numero;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(referencedColumnName = "users_id", name = "address_to_send", nullable = false, columnDefinition = "NUMBER(15)")
	@Valid
	@ForeignKey(name = "fk_address_user")
	private Users users;

	public Address() {
		super();
	}   
	
	/**
	 * @param idAddress
	 * @param addressStreet
	 * @param cep
	 * @param numero
	 * @param users
	 */
	public Address(Integer idAddress, String addressStreet, String cep,
			int numero, Users users) {
		super();
		this.idAddress = idAddress;
		this.addressStreet = addressStreet;
		this.cep = cep;
		this.numero = numero;
		this.users = users;
	}

	/**
	 * @return the idAddress
	 */
	public Integer getIdAddress() {
		return idAddress;
	}

	/**
	 * @param idAddress the idAddress to set
	 */
	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}

	/**
	 * @return the addressStreet
	 */
	public String getAddressStreet() {
		return addressStreet;
	}

	/**
	 * @param addressStreet the addressStreet to set
	 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressStreet == null) ? 0 : addressStreet.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result
				+ ((idAddress == null) ? 0 : idAddress.hashCode());
		result = prime * result + numero;
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
		Address other = (Address) obj;
		if (addressStreet == null) {
			if (other.addressStreet != null)
				return false;
		} else if (!addressStreet.equals(other.addressStreet))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (idAddress == null) {
			if (other.idAddress != null)
				return false;
		} else if (!idAddress.equals(other.idAddress))
			return false;
		if (numero != other.numero)
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
		return "Address [idAddress=" + idAddress + ", addressStreet="
				+ addressStreet + ", cep=" + cep + ", numero=" + numero
				+ ", users=" + users + "]";
	}
}