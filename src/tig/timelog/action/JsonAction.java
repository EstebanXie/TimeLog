package tig.timelog.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tig.timelog.model.TProject;
import tig.timelog.service.ICodeTableService;

import com.googlecode.jsonplugin.JSONUtil;
import com.opensymphony.xwork2.ActionSupport;

public class JsonAction extends ActionSupport{
	private ICodeTableService codeTableService;
	
	private List<TProject> projects = new ArrayList(); 

	public List<TProject> getProjects() {
		return projects;
	}

	public void setProjects(List<TProject> projects) {
		this.projects = projects;
	}

	public ICodeTableService getCodeTableService() {
		return codeTableService;
	}

	public void setCodeTableService(ICodeTableService codeTableService) {
		this.codeTableService = codeTableService;
	}
	
	public String getProjectInfo(){
		
		System.out.println("jaosn call");
		return SUCCESS;
	}
	
	public String returnList(){
		
		return "list";
	}
}
