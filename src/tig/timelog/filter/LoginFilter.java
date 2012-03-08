package tig.timelog.filter;



import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import tig.timelog.constant.ContextConstants;
import tig.timelog.util.DataStoreLocal;

/*
 * Classname : LoginFilter.java
 *
 * Version information
 *
 * Date : Jul 9, 2010
 *
 * Copyright notice
 * @author : Herry.Long
 */
public class LoginFilter implements Filter {
  Logger logger = Logger.getLogger(LoginFilter.class.getName());

  public void destroy() {

  }

  @SuppressWarnings("unchecked")
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
    FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest)servletRequest;
    HttpServletResponse response = (HttpServletResponse)servletResponse;
    HttpSession session = request.getSession(false);
    
    if (needCheckLoginRequestURI(request)) {
      if (session == null || session.getAttribute(ContextConstants.CURRENT_LOGINED_USER_CONTEXT) == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        return;
      }
    }

    // SET CURRENT USER THREAD LOCAL
    session = request.getSession();
    if (session != null && session.getAttribute(ContextConstants.CURRENT_LOGINED_USER_CONTEXT) != null) {
      Map map = new HashMap();
      map.put(ContextConstants.CURRENT_LOGINED_USER_CONTEXT,
          session.getAttribute(ContextConstants.CURRENT_LOGINED_USER_CONTEXT));
      DataStoreLocal.addData(map);
    }

    filterChain.doFilter(request, response);
  }
  
  private boolean needCheckLoginRequestURI(HttpServletRequest request){
	  boolean needCheck = true;
	String currentURI = request.getRequestURI();
	String targetURL = currentURI.substring(currentURI.indexOf("/", 1), currentURI.length());
    boolean isGetCss = currentURI.endsWith(".css");
    boolean isGetJpg = currentURI.endsWith(".jpg");
    boolean isGetGif = currentURI.endsWith(".gif");
    boolean isGetJS = currentURI.endsWith(".js");
    boolean isinitSocietySearch = !(currentURI.lastIndexOf("initSocietySearch.action") == -1)
                                  || currentURI.lastIndexOf("searchAgtBlackListInfo") != -1
                                  || currentURI.lastIndexOf("societySearchPlurAgtInfo")!= -1; //社会公众查询
    //登录的url,社会公众查询  和静态的文件不需要验证用户是否登录
	if("/".equals(targetURL) || "/jsp/login.jsp".equals(targetURL) || "/user/login".equals(targetURL)
		|| isinitSocietySearch 
		|| isGetCss || isGetJpg  || isGetGif || isGetJS){
		needCheck = false;
	}
	return needCheck;
  }


  public void init(FilterConfig arg0) throws ServletException {

  }

}


