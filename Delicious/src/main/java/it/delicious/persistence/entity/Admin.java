package it.delicious.persistence.entity;

import java.io.Serializable;
import java.lang.Integer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Entity implementation class for Entity: Admin
 * 
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Entity
@Table
@SequenceGenerator(name = Admin.ADMIN_SEQUENCE, sequenceName = Admin.ADMIN_SEQUENCE, initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "admin.findAll", query = "SELECT a FROM Admin a"),
	@NamedQuery(name = "admin.isAdminOk", query = "SELECT a FROM Admin a WHERE a.username = :username AND a.password = :password")
})
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ADMIN_SEQUENCE = "ADMIN_SEQUENCE";
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ADMIN_SEQUENCE)
	@Column(name = "admin_id", updatable = false)
	private Integer idAdmin;
	
	@Column(name="admin_username", nullable = false, length = 255)
	@OrderBy
	@XmlAttribute
	private String username;
	
	@Column(name="admin_password", nullable = false, length = 255)
	@XmlAttribute
	private String password;

	public Admin() {
		super();
	}

	/**
	 * @param idAdmin
	 * @param username
	 * @param password
	 */
	public Admin(Integer idAdmin, String username, String password) {
		super();
		this.idAdmin = idAdmin;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the idAdmin
	 */
	public Integer getIdAdmin() {
		return idAdmin;
	}

	/**
	 * @param idAdmin the idAdmin to set
	 */
	public void setIdAdmin(Integer idAdmin) {
		this.idAdmin = idAdmin;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAdmin == null) ? 0 : idAdmin.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		Admin other = (Admin) obj;
		if (idAdmin == null) {
			if (other.idAdmin != null)
				return false;
		} else if (!idAdmin.equals(other.idAdmin))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "Admin [idAdmin=" + idAdmin + ", username=" + username
				+ ", password=" + password + "]";
	}   
}