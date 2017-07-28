package org.sistemas.com.bo.domain;

import java.io.Serializable;

public class Coordenada implements Serializable{
	
	private static final long serialVersionUID = 399566913704100004L;//si
	
	private int id;
	private double lat;
	private double lon;
	private String titulo;
	private int idi;
	
	public Coordenada() {
		super();
	}
	
	
	public Coordenada(double lat, double lon, String titulo) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.titulo = titulo;
	}


	public Coordenada(double lat, double lon, String titulo, int idi) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.titulo = titulo;
		this.idi = idi;
	}


	public Coordenada(int id, double lat, double lon, String titulo) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.titulo = titulo;
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


	public int getIdi() {
		return idi;
	}


	public void setIdi(int idi) {
		this.idi = idi;
	}
	
	

}
