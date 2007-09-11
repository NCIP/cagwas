package gov.nih.nci.cagwas.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * The ViewTextAction class is called when the the user selects the
 * text-only/images link from the footer.  This sets a session variable that
 * is either text or image.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class ViewTextAction extends Action
{
	private static Logger logger = Logger.getLogger(ViewTextAction.class);
	
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
		String view = (String)request.getParameter("viewType");
		
		logger.debug("Switching text/image view to " + view);
		
		request.getSession().setAttribute("viewType", view);
		
		String inputPage = request.getHeader("Referer");
		inputPage = inputPage.substring(inputPage.indexOf("cagwas") + 6);
		forward = new ActionForward(inputPage, true);
		
		logger.debug("Source page was " + inputPage);
		
		return forward;
	}

}
