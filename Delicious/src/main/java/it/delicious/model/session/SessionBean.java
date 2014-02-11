/**
 * 
 */
package it.delicious.model.session;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
public abstract class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	public transient Logger log;
	
	@PersistenceUnit(name = "deliciousDS")
	private EntityManager em;
	
	public SessionBean(){
		super();
	}
	
	public EntityManager getEm(){
		return em;
	}

	@SuppressWarnings("unchecked")
	public <T> List <T> getList(Class<T> classToCast, String query, Object... objects){
		Query qr = createQuery(query, objects);
		return qr.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List <T> getLimitedList(Class<T> classToCast, String query, int limit, Object... objects){
		Query qr = createQuery(query, objects);
		qr.setMaxResults(limit);
		return qr.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List <T> getNamedList(Class<T> classToCast, String namedQuery, Object... objects){
		Query qr = em.createNamedQuery(namedQuery);
		for (int i = 0; i < objects.length; i++) {
			Object object = objects[i];
			qr.setParameter(i + 1, object);
		}
		return qr.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getPojo(Class<T> classToCast, String query, Object... objects){
		Query qr = createQuery(query, objects);
		return (T) qr.getSingleResult();
	}
	
	public <T> T getPojo(Class<T> classToCast, Serializable primarykey){
		return em.getReference(classToCast, primarykey);
	}
	
	public int execute(String query, Object... objects){
		Query qr = createQuery(query, objects);
		return qr.executeUpdate();
	}

	private Query createQuery(String query, Object[] objects) {
		Query qr = em.createQuery(query);
		if (objects != null){
			for (int i = 0; i < objects.length; i++) {
				Object object = objects[i];
				qr.setParameter(i + 1, object);
			}
		}
		return qr;
	}
}