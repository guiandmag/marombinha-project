/**
 * 
 */
package it.delicious.model.session;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
public abstract class ManualCDILookup {

	@SuppressWarnings("unchecked")
	public <T> T getFacadeWithJNDI(Class<T> classToFind){
		BeanManager bm = getBeanManager();
		Bean<T> bean = (Bean<T>) bm.getBeans(classToFind).iterator().next();
		CreationalContext<T> ctx = bm.createCreationalContext(bean);
		T dao = (T) bm.getReference(bean, classToFind, ctx);
		return dao;
	}
	
	public BeanManager getBeanManager(){
		try{
			InitialContext initialContext = new InitialContext();
			return (BeanManager) initialContext.lookup("java:comp/BeanManager");
		} catch (NamingException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
