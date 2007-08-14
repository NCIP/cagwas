package gov.nih.nci.cgems.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.log4j.Logger;

/**
 * The SearchGenotypeAction class is called when the Search Genotype
 * form is posted.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SearchGenotypeAction extends Action
{
	private static Logger logger = Logger.getLogger(SearchGenotypeAction.class);
	
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
		
		logger.debug("Searching for Genotypes");
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	    }
	    else
	    {
	    	// Put the type of search in the session
			request.getSession().setAttribute("searchType", "Genotype");
			
			// Put the form in the session to be passed to the job for scheduling
			request.getSession().setAttribute("form", form);
			
	    	// This search always runs as a download
			forward = mapping.findForward("success");
	    }
		
		return forward;
	}

}
