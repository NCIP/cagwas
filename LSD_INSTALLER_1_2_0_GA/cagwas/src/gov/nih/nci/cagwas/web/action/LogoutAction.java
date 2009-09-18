package gov.nih.nci.cagwas.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * The LogoutAction class is called when the the user selects the
 * logout link from the crumb bar.  This removes their login
 * information and signifies they are a non-authenticated user.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class LogoutAction extends Action
{
	private static Logger logger = Logger.getLogger(LogoutAction.class);
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		ActionForward forward = null;
		
		logger.debug("Logging out user");
		
		// Remove the logged in credential attributes
		request.getSession().removeAttribute("logged");
		request.getSession().removeAttribute("name");
		
	    forward = mapping.findForward("success");
		
		return forward;
	}

}
