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
	
	public String holamundo(){return "hola"; };	
}
