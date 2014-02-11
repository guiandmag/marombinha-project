package it.delicious.view.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import it.delicious.persistence.entity.Admin;

/**
 * @author Guilherme Magalhaes - guiandmag@gmail.com
 *
 */
@SessionScoped
@Named(value = "adminFace")
public class AdminFace extends CDIBean<Admin> {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String login;
	
	@NotNull
	private String password;
	
	private boolean adminUserLogged = false;
	
	public String doLogin(){
		if(getLogin().equals("admin") && getPassword().equals("admin")){
			setAdminUserLogged(true);
		} else {
			setAdminUserLogged(false);
		}
		if(isAdminUserLogged()){
			return "main.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Errado! Tente Novamente", "Login Errado! Tente Novamente"));
			return "index.xhtml";
		}
	}
	
	public String doCancel(){
		return "/Delicious/";
	}
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * @return the adminUserLogged
	 */
	public boolean isAdminUserLogged() {
		return adminUserLogged;
	}
	/**
	 * @param adminUserLogged the adminUserLogged to set
	 */
	public void setAdminUserLogged(boolean adminUserLogged) {
		this.adminUserLogged = adminUserLogged;
	}
}