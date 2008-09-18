package gov.nih.nci.cagwas.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.log4j.Logger;

/**
 * The ResetAction class is called when the user selects the reset button
 * on any of the forms. It will simply redirect them back to the original
 * form they started from.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class ResetAction extends Action
{
	private static Logger logger = Logger.getLogger(ResetAction.class);
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ActionMessages errors = new ActionMessages();
		ActionForward forward = null;
		
		// Figure out which form to reset to
		String formName = (String)request.getParameter("formName");
		
		// Then forward back to the appropriate form
		if (formName.equals("Association"))
		{
			logger.debug("Resetting to associations form");
			
			// Set the forward 
			forward = mapping.findForward("association");
		}
		else if (formName.equals("Population"))
		{
			logger.debug("Resetting to population form");
			
			// Set the forward 
			forward = mapping.findForward("population");
		}
		else if (formName.equals("Subjects"))
		{
			String logged = (String)request.getSession().getAttribute("logged");
			
			logger.debug("Resetting to subjects form");
			
			if ((logged != null) && (logged.equals("yes")))
			{
				// Set the forward
				forward = mapping.findForward("subjects");
			}
			else
			{
				request.getSession().setAttribute("ref", mapping.findForward("subjects").getPath().toString());
				forward = mapping.findForward("login");
			}
		}
		else if (formName.equals("Genotypes"))
		{
			String logged = (String)request.getSession().getAttribute("logged");
			
			logger.debug("Resetting to genotypes form");
			
			if ((logged != null) && (logged.equals("yes")))
			{
				// Set the forward
				forward = mapping.findForward("genotypes");
			}
			else
			{
				request.getSession().setAttribute("ref", mapping.findForward("genotypes").getPath().toString());
				forward = mapping.findForward("login");
			}
		}
		else if (formName.equals("Login"))
		{
			logger.debug("Resetting to login form");
			
			// Set the forward 
			forward = mapping.findForward("login");
		}
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	    }
		
		return forward;
	}

}
