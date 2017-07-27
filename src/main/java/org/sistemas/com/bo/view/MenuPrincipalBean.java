package org.sistemas.com.bo.view;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.sistemas.com.bo.domain.*;


import org.sistemas.com.bo.service.UpdsService;




public class MenuPrincipalBean  implements Serializable {  
	/*******************SERVICIOS*****************************/		
	private UpdsService updsService;	
	public void setUpdsService(UpdsService updsService)	
	{
		this.updsService = updsService;	
	
		
	}	
	
	
	
	/*********************LISTA*************************/
	
    
    
	
/**************************LOGIN*************************/
	
	
	private String usuario="admin";
	private String password="";
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void acceso(ActionEvent actionEvent)
	{
		usuario="";
		password="";
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/comun/facelets/faceletsInicio.xhtml?"))
    			.append("faces-redirect=true").toString()); 
	}
	
	public void salir(ActionEvent actionEvent)
	{
		
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/seguridad/faceletsLogin.xhtml?"))
    			.append("faces-redirect=true").toString()); 
	}
	
	public void cerrar(ActionEvent actionEvent)
	{
		
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/comun/facelets/faceletsInicio.xhtml?"))
    			.append("faces-redirect=true").toString()); 
	}
private String username="admin";
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;
		
		if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
			loggedIn = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", username);
			password="";
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
	    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
	    			("/paginas/comun/facelets/faceletsInicio.xhtml?"))
	    			.append("faces-redirect=true").toString()); 
		} else {
			loggedIn = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!!!", "Acceso denegado");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("loggedIn", loggedIn);
	}
	
	
	
	
	/**************************/
	
	
   
    





	/************************************************/
	
	private Date hour;
	private Date fecha;
	
	private String fechastring;
	private String horastring;
	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getFechaString(Date d) {		
		DateFormat df;		
        String dateString=null;
        df=new SimpleDateFormat("dd-MM-yyyy");
        dateString=df.format(d);		
		return dateString;
	}
	
	public String getHoraString(Date d) {		
		DateFormat df;		
        String dateString=null;
        df=new SimpleDateFormat("HH:mm:ss");
        dateString=df.format(d);		
		return dateString;
	}

	public String getFechastring() {
		fecha = new Date();
		fechastring=getFechaString(fecha);
		return fechastring;
	}

	public void setFechastring(String fechastring) {
		this.fechastring = fechastring;
	}

	public String getHorastring() {
		hour = new Date();
		horastring=getHoraString(hour);
		return horastring;
	}

	public void setHorastring(String horastring) {
		this.horastring = horastring;
	}
	
	
    
}  
   