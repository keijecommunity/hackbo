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
}