/**
 * 
 */
package it.delicious.model.session.bean.imple;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import it.delicious.model.session.SessionBean;
import it.delicious.model.session.bean.SellSessionBean;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SellSessionBeanImple extends SessionBean implements
		SellSessionBean {

	private static final long serialVersionUID = 1L;

}
