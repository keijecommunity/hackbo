package org.sistemas.com.bo.service;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sistemas.com.bo.domain.*;
import org.sistemas.com.bo.persistence.UpdsMapper;


@Service
public class UpdsService  implements Serializable{
	/********************************************************************/	
	@Autowired
	private UpdsMapper updsMapper;
	public UpdsMapper getUpdsMapper() {	return updsMapper;	}		
		
	
	public String holamundo(){return "hola"; };
	
}
