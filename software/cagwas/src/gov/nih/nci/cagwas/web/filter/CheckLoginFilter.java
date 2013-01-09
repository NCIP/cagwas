/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.filter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/** 
 */
public class CheckLoginFilter implements Filter {

    private FilterConfig filterConfig = null;

    /**
     * Called by the web container to indicate to a filter that it is being
     * placed into service.
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * The doFilter method of the Filter is called by the container each time a
     * request/response pair is passed through the chain due to a client request
     * for a resource at the end of the chain.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	//TESTING - this block will simply bypass the filtering
//    	if(true){
//	    	chain.doFilter(request, response);
//	        return;
//    	}
    	
        boolean authorized = false;
        if (request instanceof HttpServletRequest) {
        	String isloginpage = ((HttpServletRequest) request).getRequestURI();
            boolean isRequestedSessionIdFromURL = ((HttpServletRequest) request).isRequestedSessionIdFromURL();
        	if(isloginpage!=null && !isRequestedSessionIdFromURL &&( 
        			isloginpage.endsWith("login.do") || 
        			isloginpage.endsWith("reset.do") || 
        			isloginpage.contains("remoteSetup.do") || 
        			isloginpage.contains("downloadZipFile.do") || 
        			isloginpage.endsWith("/cgems/") ||
        			isloginpage.endsWith("/cagwas/") ||
        			isloginpage.contains("aboutSetup.do") ))	{
        		//just continue, so they can login
        		generateNewSession((HttpServletRequest) request);
        		chain.doFilter(request, response);
                return;
        	}
        	//this is how CGEMS checks for login in the app
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            if (session!= null && !isRequestedSessionIdFromURL){
	            String logged = (String)session.getAttribute("logged");
	            if(logged != null && logged.equals("yes")){
	                	authorized = true;
	            }
            }
        }

        if (authorized) {
            chain.doFilter(request, response);
            return;
        } else if (filterConfig != null) {
            String unauthorizedPage = filterConfig.getInitParameter("unauthorizedPage");
            if (unauthorizedPage != null && !"".equals(unauthorizedPage)) {
            	generateNewSession((HttpServletRequest) request);
                filterConfig.getServletContext().getRequestDispatcher(unauthorizedPage).forward(request, response);
                return;
            }
        }

        throw new ServletException("Unauthorized access, unable to forward to login page");

    }
    private void generateNewSession(HttpServletRequest httpRequest){
    	 HttpSession session = httpRequest.getSession();
         HashMap<String, Object> old = new HashMap<String, Object>();
         Enumeration<String> keys = (Enumeration<String>) session.getAttributeNames();
         while (keys.hasMoreElements()) {
           String key = keys.nextElement();
           old.put(key, session.getAttribute(key));
         }
         //session invalidated 
         session.invalidate();
         session = httpRequest.getSession(true);
         for (Map.Entry<String, Object> entry : old.entrySet()) {
           session.setAttribute(entry.getKey(), entry.getValue());
         }

    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     */
    public void destroy() {
        filterConfig = null;
    }

}

