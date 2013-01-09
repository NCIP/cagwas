/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.log4j.Logger;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import gov.nih.nci.cagwas.web.SchedulerPlugIn;
import gov.nih.nci.cagwas.web.form.DownloadForm;
import gov.nih.nci.cagwas.web.job.SearchAssociationsJob;
import gov.nih.nci.cagwas.web.job.SearchPopulationJob;
import gov.nih.nci.cagwas.web.job.SearchGenotypeJob;
import gov.nih.nci.cagwas.web.job.SearchSubjectJob;

/**
 * The DownloadAction class is called when the Download form posts.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class DownloadAction extends Action
{
	private static Logger logger = Logger.getLogger(DownloadAction.class);
	
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
		
		// Figure out the state of the form
		DownloadForm dForm = (DownloadForm)form;
		String email = dForm.getEmail();
		Date now = new Date();
		
		Long studyId = (Long)request.getSession().getAttribute("studyId");
		String studyName = (String)request.getSession().getAttribute("studyName");
		String caseStudyName = (String)request.getSession().getAttribute("caseStudyName");
		String searchType = (String)request.getSession().getAttribute("searchType");
		ActionForm searchForm = (ActionForm)request.getSession().getAttribute("form");
		
		logger.debug("Search Type is " + searchType);
		
		// Schedule the job for immediate execution and only once
		JobDetail jobDetail = null;
		Trigger trigger = TriggerUtils.makeImmediateTrigger(0, 0);
		
		if (searchType != null)
		{
			// If the query was association then setup the association job
			if (searchType.equals("Associations"))
			{
				jobDetail = new JobDetail("searchAssociationsJob" + email + now, null, SearchAssociationsJob.class);
				// Trigger name must be unique so include type and email
				trigger.setName("immediateTriggerAssociations" + email + now);
			}
			
			// If the search was for population then setup that job
			if (searchType.equals("Population"))
			{
				jobDetail = new JobDetail("searchPopulationJob" + email + now, null, SearchPopulationJob.class);
				// Trigger name must be unique so include type and email
				trigger.setName("immediateTriggerPopulation" + email + now);
			}
			
			// If the search was for genotype then setup that job
			if (searchType.equals("Genotype"))
			{
				jobDetail = new JobDetail("searchGenotypeJob" + email + now, null, SearchGenotypeJob.class);
				// Trigger name must be unique so include type and email
				trigger.setName("immediateTriggerGenotype" + email + now);
			}
			
			// If the search was for genotype then setup that job
			if (searchType.equals("Subject"))
			{
				jobDetail = new JobDetail("searchSubjectJob" + email + now, null, SearchSubjectJob.class);
				// Trigger name must be unique so include type and email
				trigger.setName("immediateTriggerSubject" + email + now);
			}
			
			// Add the form and email address to the job details
			if (jobDetail != null)
			{
				jobDetail.getJobDataMap().put("form", searchForm);
				jobDetail.getJobDataMap().put("email", email);
				jobDetail.getJobDataMap().put("studyId", studyId);
				jobDetail.getJobDataMap().put("studyName", studyName);
				jobDetail.getJobDataMap().put("caseStudyName", caseStudyName);
			}
			
			// Now remove the session attributes
			request.getSession().removeAttribute("queryType");
			request.getSession().removeAttribute("form");
		}

		if (jobDetail != null)
			SchedulerPlugIn.scheduleWork(jobDetail, trigger);
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	    }
	    else
	    	forward = mapping.findForward("success");
		
		return forward;
	}

}
