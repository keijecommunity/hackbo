package org.sistemas.com.bo.persistence;

import java.util.List;


import org.sistemas.com.bo.domain.*;



import java.util.HashMap;

public interface UpdsMapper {
	
	
	
	
	
	/**************************************************/
	Coordenada getCoordenada(int id);
	Coordenada getCoordenadaMover(Coordenada objeto);
	List<Coordenada> getCoordenadas();	
	void insertCoordenada(Coordenada objeto);
	void deleteCoordenada(Coordenada objeto);
	void updateCoordenada(Coordenada objeto);
	
	/**************************************************/
	Coordenadai getCoordenadai(int id);
	Coordenadai getCoordenadaiMover(Coordenadai objeto);
	List<Coordenadai> getCoordenadasi();	
	List<Coordenadai> getCoordenadasip();	
	List<Coordenadai> getCoordenadasic();	
	void insertCoordenadai(Coordenadai objeto);
	void deleteCoordenadai(Coordenadai objeto);
	void updateCoordenadai(Coordenadai objeto);
}
