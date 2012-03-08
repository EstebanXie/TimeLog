package tig.timelog.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextUtil {
	private static ApplicationContext applicationContext;
	private ApplicationContextUtil(){
		
	}
	public static ApplicationContext rebuildApplicationContext() { 
		return new FileSystemXmlApplicationContext(
	    	"/WebRoot/WEB-INF/applicationContext*.xml");	
		}
	
	public static ApplicationContext getApplicationContext() { 
		if (applicationContext == null) {
			applicationContext = rebuildApplicationContext();
		}
		return applicationContext; 
	} 
}
