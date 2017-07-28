package org.sistemas.com.bo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sistemas.com.bo.domain.*;
import org.sistemas.com.bo.persistence.UpdsMapper;

@Service
public class UpdsServiceImpl implements UpdsServiceFacade {

	/********************************************************************/	
	@Autowired
	private UpdsMapper updsMapper;
	public UpdsMapper getUpdsMapper() {	return updsMapper;	}		
	public UpdsServiceImpl(){}
	
	
	
	
	public Coordenada getCoordenada(int id){return updsMapper.getCoordenada(id); };
	public Coordenada getCoordenadaMover(Coordenada objeto){return updsMapper.getCoordenadaMover(objeto); };
	public List<Coordenada> getCoordenadas(){return updsMapper.getCoordenadas();};	
	public void insertCoordenada(Coordenada objeto){updsMapper.insertCoordenada(objeto);};
	public void deleteCoordenada(Coordenada objeto){updsMapper.deleteCoordenada(objeto);};
	public void updateCoordenada(Coordenada objeto){updsMapper.updateCoordenada(objeto);};
	
/*********************************************************************/	
	
	public Coordenadai getCoordenadai(int id){return updsMapper.getCoordenadai(id); };
	public Coordenadai getCoordenadaiMover(Coordenadai objeto){return updsMapper.getCoordenadaiMover(objeto); };
	public List<Coordenadai> getCoordenadasi(){return updsMapper.getCoordenadasi();};	
	public List<Coordenadai> getCoordenadasip(){return updsMapper.getCoordenadasip();};	
	public List<Coordenadai> getCoordenadasic(){return updsMapper.getCoordenadasic();};	
	public void insertCoordenadai(Coordenadai objeto){updsMapper.insertCoordenadai(objeto);};
	public void deleteCoordenadai(Coordenadai objeto){updsMapper.deleteCoordenadai(objeto);};
	public void updateCoordenadai(Coordenadai objeto){updsMapper.updateCoordenadai(objeto);};

}
