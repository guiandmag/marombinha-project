package it.delicious.persistence.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

/**
 * Entity implementation class for Entity: Users
 * 
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Entity
@Table
@SequenceGenerator(name = Users.USER_SEQUENCE, sequenceName = Users.USER_SEQUENCE, initialValue = 1, allocationSize = 50)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = "Users.findAll",
				query = "SELECT u FROM Users U"),
	@NamedQuery(name = "Users.isUserOk",
				query = "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password "),
	@NamedQuery(name = "Users.getLastUsers",
				query = "SELECT u FROM Users u ORDER BY u.idUser DESC"),
	@NamedQuery(name = "Users.getUsersByName", 
				query = "SELECT u FROM Users u WHERE u.name LIKE :name")
})
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String USER_SEQUENCE = "USER_SEQUENCE";
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_SEQUENCE)
	@Column(name = "users_id", updatable = false)
	@XmlAttribute
	private Integer idUser;
	
	@Column(name="users_username", nullable = false, length = 255)
	@OrderBy
	@XmlAttribute
	private String username;
	
	@Column(name="users_password", nullable = false, length = 255)
	@XmlAttribute
	private String password;
	
	@Column(name="users_name", nullable = false, length = 255)
	@XmlAttribute
	private String name;
	
	@Column(name="users_email", nullable = false, length = 255)
	@Email
	@XmlElement
	private String email;
	
	@Column(name="users_phonenumber", nullable = false, length = 12)
	@Digits(fraction = 0, integer = 12)
	@XmlElement
	private Integer phoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users", targetEntity = Address.class, orphanRemoval = true)
	private List<Address> addressToSend = new LinkedList<Address>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users", targetEntity = Sell.class)
	private List<Sell> sell = new LinkedList<Sell>();

	public Users() {
		super();
	}

	/**
	 * @param idUser
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param phoneNumber
	 * @param addressToSend
	 * @param sell
	 */
	public Users(Integer idUser, String username, String password, String name,
			String email, Integer phoneNumber, List<Address> addressToSend,
			List<Sell> sell) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.addressToSend = addressToSend;
		this.sell = sell;
	}

	/**
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the addressToSend
	 */
	public List<Address> getAddressToSend() {
		return addressToSend;
	}

	/**
	 * @param addressToSend the addressToSend to set
	 */
	public void setAddressToSend(List<Address> addressToSend) {
		this.addressToSend = addressToSend;
	}

	/**
	 * @return the sell
	 */
	public List<Sell> getSell() {
		return sell;
	}

	/**
	 * @param sell the sell to set
	 */
	public void setSell(List<Sell> sell) {
		this.sell = sell;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressToSend == null) ? 0 : addressToSend.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((sell == null) ? 0 : sell.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Users other = (Users) obj;
		if (addressToSend == null) {
			if (other.addressToSend != null)
				return false;
		} else if (!addressToSend.equals(other.addressToSend))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (sell == null) {
			if (other.sell != null)
				return false;
		} else if (!sell.equals(other.sell))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [idUser=" + idUser + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", addressToSend="
				+ addressToSend + ", sell=" + sell + "]";
	}   
}