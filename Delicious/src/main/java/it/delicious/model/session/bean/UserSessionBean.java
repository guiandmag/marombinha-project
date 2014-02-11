/**
 * 
 */
package it.delicious.model.session.bean;

import java.util.List;

import it.delicious.persistence.entity.Address;
import it.delicious.persistence.entity.Admin;
import it.delicious.persistence.entity.Users;

import javax.ejb.Local;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Local
public interface UserSessionBean {

	public Users saveUser(Users us);
	
	public Users setUser(Users us);
	
	public void removeUser(Users us);
	
	public Users getUserById(int idUser);
	
	public Users isUserOk(String username, String password);
	
	public List<Users> getMostActiveUsers();
	
	public List<Users> getLastUsers();
	
	public List<Users> getAllUsers();
	
	public List<Users> getUsersByName(String name);
	
	public void removeAddress(int idAddress);
	
	public Address setAddress(Address addressToUpdate);
	
	public Address getAddress(int idAddress);
	
	public List<Address> getAddressOfUser(Users us);
	
	public List<Address> getAllAddress();
	
	public Admin isAdminOk(String username, String password);
	
}
