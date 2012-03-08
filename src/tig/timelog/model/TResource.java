package tig.timelog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TResource implements Serializable{

	private String resourceId;

	private String resourceName;

	private String resourcePId;

	private String resourceUrl;
	
	private String resourceSeq;
	
	private List<TResource> childMenuList = new ArrayList<TResource>();
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourcePId() {
		return resourcePId;
	}

	public void setResourcePId(String resourcePId) {
		this.resourcePId = resourcePId;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public List<TResource> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List<TResource> childMenuList) {
		this.childMenuList = childMenuList;
	}
	
	public void addChildMenu(TResource menu){
		this.childMenuList.add(menu);
	}

	/**
	 * @return the resourceSeq
	 */
	public String getResourceSeq() {
		return resourceSeq;
	}

	/**
	 * @param resourceSeq the resourceSeq to set
	 */
	public void setResourceSeq(String resourceSeq) {
		this.resourceSeq = resourceSeq;
	}
}
