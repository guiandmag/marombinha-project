/**
 * 
 */
package it.delicious.model.session.bean.imple;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import it.delicious.model.session.SessionBean;
import it.delicious.model.session.bean.UserSessionBean;
import it.delicious.persistence.entity.Address;
import it.delicious.persistence.entity.Admin;
import it.delicious.persistence.entity.Users;
import it.delicious.util.SecurityUtil;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserSessionBeanImple extends SessionBean implements
		UserSessionBean {

	private static final long serialVersionUID = 1L;

	@Override
	public Users saveUser(Users us) {
		getEm().persist(us);
		return us;
	}

	@Override
	public Users setUser(Users us) {
		return getEm().merge(us);
	}

	@Override
	public void removeUser(Users us) {
		us = getEm().merge(us);
		getEm().remove(us);
	}

	@Override
	public Users getUserById(int idUser) {
		return getPojo(Users.class, idUser);
	}

	@Override
	public Users isUserOk(String username, String password) {
		try{
			return getEm().createNamedQuery("Users.isUserOk", Users.class).setParameter("username", username).setParameter("password", SecurityUtil.convertStringToMD5(password)).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Users> getMostActiveUsers() {
		Query qr = getEm().createNativeQuery("SELECT Users.users_id, COUNT(*) AS qtd FROM Sell GROUP BY Users.users_id ORDER BY qtd DESC LIMIT 10");
		@SuppressWarnings("unchecked")
		List<Object> returned = qr.getResultList();
		StringBuilder sb = new StringBuilder();
		for (Object object : returned) {
			Integer idUser = (Integer) ((Object[]) object)[0];
			sb.append(idUser + ",");
		}
		if(sb.toString().isEmpty()){
			return new LinkedList<Users>();
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		
		return getList(Users.class, "SELECT us FROM Users us WHERE us.users_id IN (" + sb.toString() + ")");
	}

	@Override
	public List<Users> getLastUsers() {
		try{
			return getEm().createNamedQuery("Users.getLastUsers" + "10", Users.class).getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Users> getAllUsers() {
		try{
			return getEm().createNamedQuery("Users.findAll", Users.class).getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public List<Users> getUsersByName(String name) {
		try{
			return getEm().createNamedQuery("Users.getUsersByName", Users.class).setParameter("name", "%"  + name + "%").getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}

	@Override
	public void removeAddress(int idAddress) {
		Address addr = getEm().getReference(Address.class, idAddress);
		getEm().remove(addr);
	}

	@Override
	public Address setAddress(Address addressToUpdate) {
		return getEm().merge(addressToUpdate);
	}

	@Override
	public Address getAddress(int idAddress) {
		return getPojo(Address.class, idAddress);
	}

	@Override
	public List<Address> getAddressOfUser(Users us) {
		return getList(Address.class, "SELECT addr FROM Address addr WHERE addr.address_to_send = ?1 ORDER BY addr.address_street");
	}

	@Override
	public List<Address> getAllAddress() {
		try{
			return getEm().createNamedQuery("address.findAll", Address.class).getResultList();
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
	
	@Override
	public Admin isAdminOk(String username, String password) {
		try{
			return getEm().createNamedQuery("Admin.isUserOk", Admin.class).setParameter("username", username).setParameter("password", SecurityUtil.convertStringToMD5(password)).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e);
		}
		return null;
	}
}