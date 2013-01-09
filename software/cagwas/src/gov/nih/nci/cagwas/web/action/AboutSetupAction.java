/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.cagwas.web.DeleteOldResultsFilesJob;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.quartz.SchedulerException;

/**
 * The AboutSetupAction class is used to get the remote content for the about
 * page and then redirect to it.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class AboutSetupAction extends Action
{
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
		
		RemoteContentHelper rcHelp = new RemoteContentHelper();
		
		// Get the about static content
		String aboutContent = rcHelp.getRemoteContent("remote.about");
		
		if (aboutContent != null)
			request.setAttribute("aboutContent", aboutContent);
		
		// Get the update static content
		String updateContent = rcHelp.getRemoteContent("remote.update");
		
		if (updateContent != null){
			request.setAttribute("updateContent", updateContent);
		}else{
			request.setAttribute("updateContent", "");
		}
		
		// Get the spotlight static content
		String spotlightContent = rcHelp.getRemoteContent("remote.spotlight");
		if (spotlightContent != null){
			request.setAttribute("spotlightContent", spotlightContent);
		}else{
			request.setAttribute("spotlightContent", "");
		}
		
		// Get the registrationText static content
		String registrationText = rcHelp.getRemoteContent("remote.registrationText");
		if (registrationText != null){
			request.getSession().setAttribute("registrationText", registrationText);
		}else{
			request.getSession().setAttribute("registrationText", "");
		}		
		// Get the loginText static content
		String loginText = rcHelp.getRemoteContent("remote.loginText");
		if (loginText != null){
			request.getSession().setAttribute("loginText", loginText);
		}else{
			request.getSession().setAttribute("loginText", "");
		}
		// Get the URL for the user registration
		String remoteUrl = rcHelp.getProperty("remote.url");
		String remoteRegistration = rcHelp.getProperty("remote.registration");
		if(!remoteRegistration.contains("http")){
			request.getSession().setAttribute("registrationUrl", remoteUrl + remoteRegistration);
		}else{
			request.getSession().setAttribute("registrationUrl", remoteRegistration);
		}
		
		//start up scheduler to DeleteOldResultsFilesJob
    	if(System.getProperty("rembrandt.scheduler.started") == null){
    		DeleteOldResultsFilesJob.getInstance();
    		System.setProperty("rembrandt.scheduler.started","TRUE");
    	}

		
		// If we did not get content then forward to an error page
	    if ((aboutContent == null) || (updateContent == null))
	    {
	    	forward = mapping.findForward("ploneError");
	    }
	    else
	    {
	        forward = mapping.findForward("success");
	    }
		
		return forward;
	}

}
