package org.sistemas.com.bo.domain;

import java.io.Serializable;

public class Coordenadai implements Serializable{
	
	private static final long serialVersionUID = 399566913704100005L;//si
	
	private int id;
	private double lat;
	private double lon;
	private String titulo; // color
	private String tipo; // tipo de figura geometrica 
	private double d; // distancia 
	private String descr; // descripcion 	
	private String cod; // linea de micro (1,5, etc. D,L etc.)
	private String fam; // familia micro
	private String dim; // dimensiones
	private String est; // estado 
	private String pro; // sindicato 
	
	public double getD() {
		return d;
	}


	public void setD(double d) {
		this.d = d;
	}


	public Coordenadai() {
		super();
	}
	
	
	public Coordenadai(double lat, double lon, String titulo) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.titulo = titulo;
	}

	public Coordenadai(double lat, double lon, String titulo, String tipo, double d) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.titulo = titulo;
		this.tipo = tipo;
		this.d = d;
	}
	// (lat,lon,titulo,tipo,d,descr,cod,fam,dim, est, pro)
	public Coordenadai(double lat, double lon, String titulo, String tipo, double d, String descr, String cod, String fam, String dim, String est, String pro) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.titulo = titulo;
		this.tipo = tipo;
		this.d = d;
		this.descr = descr;
		
		this.cod = cod;
		this.fam = fam;
		this.dim = dim;
		this.est = est;
		this.pro = pro;
	}

	public Coordenadai(int id, double lat, double lon, String titulo) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.titulo = titulo;
	}
	
	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getCod() {
		return cod;
	}


	public void setCod(String cod) {
		this.cod = cod;
	}


	public String getFam() {
		return fam;
	}


	public void setFam(String fam) {
		this.fam = fam;
	}


	public String getDim() {
		return dim;
	}


	public void setDim(String dim) {
		this.dim = dim;
	}


	public String getEst() {
		return est;
	}


	public void setEst(String est) {
		this.est = est;
	}


	public String getPro() {
		return pro;
	}


	public void setPro(String pro) {
		this.pro = pro;
		
	}
	////clases s´ring tools 


	

}
