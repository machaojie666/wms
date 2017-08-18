package com._520it.wms.mapper;

import com._520it.wms.domain.Client;
import com._520it.wms.query.ClientQueryObject;
import java.util.List;

public interface ClientMapper {
	void save(Client entity);
	
	void update(Client entity);
	
	void delete(Long id);
	
    Client get(Long id);
    
	List<Client> list();
	
    Long getTotalCount(ClientQueryObject qo);
    
    List<Client> getListData(ClientQueryObject qo);
}