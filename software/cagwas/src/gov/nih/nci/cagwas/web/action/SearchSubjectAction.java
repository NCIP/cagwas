/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyParticipantCriteria;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.reports.StudyParticipantReport;
import gov.nih.nci.cagwas.web.form.SubjectForm;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/**
 * The SearchSubjectAction class is called when the Search Subject
 * form is posted.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SearchSubjectAction extends Action
{
	private static Logger logger = Logger.getLogger(SearchSubjectAction.class);
	
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
		SubjectForm sForm = (SubjectForm)form;
		Long studyId = (Long)request.getSession().getAttribute("studyId");
		String studyName = (String)request.getSession().getAttribute("studyName");
		String studyVersion = (String) request.getSession().getAttribute("studyVersion");
		
		SearchSubjectHelper helper = new SearchSubjectHelper();
		StudyParticipantCriteria spCrit = helper.buildCriteria(sForm, studyId, errors);
		
		// If they did not provide any criteria then lets redirect and not search
		if (sForm.isBlank())
		{
			logger.debug("Subject form was left blank");
			forward = mapping.findForward("criteriaWarn");
			String redirect = ZipFindingsHelper.getSecureFtpUrl() +
				studyName + "/" + studyVersion + "/Subjects";
			request.setAttribute("bulkUrl", redirect);
			logger.debug("redirecting to " + redirect);
			return forward;
		}
		
        // Get FindingsManager service from spring bean factory
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
        
		// Execute the search
		Collection<StudyParticipant> subjects = manager.getStudySubjects(spCrit, 0, CagwasConstants.MAX_RESULTS + 1);
		
		// Create the SNPAssociation reports
		ArrayList<StudyParticipantReport> results = new ArrayList<StudyParticipantReport>();
		for(StudyParticipant participant : subjects)
		{
        	if(participant != null)
        	{
        		StudyParticipantReport report = new StudyParticipantReport(participant);
        		results.add(report);
        	}
		}
		
		request.getSession().setAttribute("subj.results", results);
		
		logger.debug("Number of subjects found is " + subjects.size());
		
		request.setAttribute("numberSubjects", subjects.size());
		request.setAttribute("sortedColumn", "chromosome");
		request.setAttribute("sortOrder", "ascending");
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	    }
	    else
	    {
	    	// If there were more results than we allow then forward to download page
			if (subjects.size() > CagwasConstants.MAX_RESULTS)
			{
				// Put the type of search in the session
				request.getSession().setAttribute("searchType", "Subject");
				
				// Put the form in the session to be passed to the job for scheduling
				request.getSession().setAttribute("form", form);
				
				// Set the forward to the download page
				forward = mapping.findForward("download");
			}
			else
				forward = mapping.findForward("success");
	    }
		
		return forward;
	}

}
