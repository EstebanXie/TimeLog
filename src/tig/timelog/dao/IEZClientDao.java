package tig.timelog.dao;

import java.util.List;

import tig.timelog.model.TEZClient;

public interface IEZClientDao extends IBaseDao{
	void addEZClient(TEZClient client);
	void deleteEZClient(Integer id);
	void updateEZClient(TEZClient client);
	List<TEZClient> getAllTEZClient(Integer clientId);
	public TEZClient getClientByName(String clientName);
}
