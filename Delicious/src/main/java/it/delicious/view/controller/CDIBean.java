/**
 * 
 */
package it.delicious.view.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
public abstract class CDIBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Produces
	@SessionScoped
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private transient Logger log;
	
	private T selectedBean;
	
	public T getSelectedBean() {
		return selectedBean;
	}

	public void setSelectedBean(T selectedBean) {
		this.selectedBean = selectedBean;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
}