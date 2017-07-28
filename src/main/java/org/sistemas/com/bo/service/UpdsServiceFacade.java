package org.sistemas.com.bo.service;

import java.util.List;

import org.sistemas.com.bo.domain.*;

public interface UpdsServiceFacade {
	
	
	/***********************coordinadas***************************/
	Coordenada getCoordenada(int id);
	Coordenada getCoordenadaMover(Coordenada objeto);
	List<Coordenada> getCoordenadas();	
	void insertCoordenada(Coordenada objeto);
	void deleteCoordenada(Coordenada objeto);
	void updateCoordenada(Coordenada objeto);
	
	/***********************coordenadas inicial***************************/
	Coordenadai getCoordenadai(int id);
	Coordenadai getCoordenadaiMover(Coordenadai objeto);
	List<Coordenadai> getCoordenadasi();	
	List<Coordenadai> getCoordenadasip();	
	List<Coordenadai> getCoordenadasic();	
	void insertCoordenadai(Coordenadai objeto);
	void deleteCoordenadai(Coordenadai objeto);
	void updateCoordenadai(Coordenadai objeto);
	
}
