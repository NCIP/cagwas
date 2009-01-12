package gov.nih.nci.cagwas.web.filter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

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
        	if(isloginpage!=null && ( isloginpage.endsWith("login.do") || 
        			isloginpage.contains("remoteSetup.do") || 
        			isloginpage.contains("aboutSetup.do") ))	{
        		//just continue, so they can login
        		chain.doFilter(request, response);
                return;
        	}
        	//this is how CGEMS checks for login in the app
            HttpSession session = ((HttpServletRequest) request).getSession();
            String logged = (String)session.getAttribute("logged");
            if(session != null && logged != null && logged.equals("yes")){
                	authorized = true;
            }
        }

        if (authorized) {
            chain.doFilter(request, response);
            return;
        } else if (filterConfig != null) {
            String unauthorizedPage = filterConfig.getInitParameter("unauthorizedPage");
            if (unauthorizedPage != null && !"".equals(unauthorizedPage)) {
                filterConfig.getServletContext().getRequestDispatcher(unauthorizedPage).forward(request, response);
                return;
            }
        }

        throw new ServletException("Unauthorized access, unable to forward to login page");

    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     */
    public void destroy() {
        filterConfig = null;
    }

}

