package tig.timelog.service;

import java.util.List;

import tig.timelog.model.TEZClient;

public interface IEZClientService {
	void addEZClient(TEZClient client);
	void deleteEZClient(String[] ids);
	void updateEZClient(TEZClient client);
	public TEZClient getClientByName(String clientName);
	List<TEZClient> getAllTEZClient(Integer clientId);
}
