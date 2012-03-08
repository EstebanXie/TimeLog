package tig.timelog.action;

import java.util.ArrayList;
import java.util.List;

import tig.timelog.model.Paging;
import tig.timelog.model.TEZClient;
import tig.timelog.service.IEZClientService;
import tig.timelog.util.PagingUtil;

import com.opensymphony.xwork2.ActionSupport;

public class EZClientMgmtAction extends ActionSupport {

	private IEZClientService clientService;
	private List<TEZClient> clients;

	private TEZClient editClient;
	private String[] clientIdArray;
	Paging paging = new Paging();

	public String listAllEZClient() {
		clients = clientService.getAllTEZClient(0);

		if (null != clients && clients.size() > 0) {
			PagingUtil.getPaging(paging, clients.size());
			int iStartLimit = PagingUtil.getStartLimit(paging);
			int end = PagingUtil.getLastLimit(iStartLimit, paging,
					clients.size());
			List<TEZClient> pagedList = new ArrayList<TEZClient>();
			for (int i = iStartLimit; i < end; i++) {
				pagedList.add(clients.get(i));
			}

			clients = pagedList;
		}
		return SUCCESS;
	}
	  public String addEZClient() {
		  if(clientService.getClientByName(editClient.getClientName()) != null){
			  this.addActionMessage(super.getText("EZClientalreadyexisted.msg"));
			  return SUCCESS;
		  }
		  clientService.addEZClient(editClient);
	    this.addActionMessage(super.getText("EZClientOperationSuccess.msg"));
	    return SUCCESS;
	  }
	  public String modifyEZClient() {

		  clientService.updateEZClient(editClient);
	    this.addActionMessage(super.getText("EZClientOperationSuccess.msg"));
	    return SUCCESS;
	  }
	  public String deleteEZClient() {
	    if (this.clientIdArray != null && this.clientIdArray.length > 0) {
	      this.clientService.deleteEZClient(this.clientIdArray);
	    }

	    this.addActionMessage(super.getText("EZClientDeleteSuccess.msg"));
	    return SUCCESS;
	  }
		public String toAddClient(){
			return SUCCESS;
		}
		public String toModifyClient(){
			editClient = clientService.getAllTEZClient(Integer.valueOf(clientIdArray[0])).get(0);
			return SUCCESS;
		}		
	public void setClientService(IEZClientService clientService) {
		this.clientService = clientService;
	}

	public IEZClientService getClientService() {
		return clientService;
	}
	public List<TEZClient> getClients() {
		return clients;
	}
	public void setClients(List<TEZClient> clients) {
		this.clients = clients;
	}
	public TEZClient getEditClient() {
		return editClient;
	}
	public void setEditClient(TEZClient editClient) {
		this.editClient = editClient;
	}
	public String[] getClientIdArray() {
		return clientIdArray;
	}
	public void setClientIdArray(String[] clientIdArray) {
		this.clientIdArray = clientIdArray;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
