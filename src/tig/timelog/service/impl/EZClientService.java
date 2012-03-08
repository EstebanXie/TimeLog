package tig.timelog.service.impl;

import java.util.List;

import tig.timelog.dao.IEZClientDao;
import tig.timelog.model.TEZClient;
import tig.timelog.service.IEZClientService;

public class EZClientService implements IEZClientService{
	private IEZClientDao clientDao; 

	public IEZClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(IEZClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public void addEZClient(TEZClient client) {
		clientDao.addEZClient(client);
	}

	@Override
	public void deleteEZClient(String[] ids) {
		for(String id: ids)
			clientDao.deleteEZClient(Integer.valueOf(id));
	}

	@Override
	public List<TEZClient> getAllTEZClient(Integer clientId) {
		return clientDao.getAllTEZClient(clientId);
	}

	@Override
	public void updateEZClient(TEZClient client) {
		clientDao.updateEZClient(client);
	}

	@Override
	public TEZClient getClientByName(String clientName) {
		
		return clientDao.getClientByName(clientName);
	}

}
