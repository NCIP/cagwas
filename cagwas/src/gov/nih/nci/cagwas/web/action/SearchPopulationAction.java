package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPFrequencyFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.reports.SNPFrequencyFindingReport;
import gov.nih.nci.cagwas.web.form.PopulationForm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/**
 * The SearchPopulationAction class is called when the Search Population Frequency
 * form is posted.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SearchPopulationAction extends Action
{
	private static Logger logger = Logger.getLogger(SearchPopulationAction.class);
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ActionMessages errors = new ActionMessages();
		ActionForward forward = null;
		PopulationForm popForm = (PopulationForm)form;
		Long studyId = (Long)request.getSession().getAttribute("studyId");
		String panelName = (String)popForm.getPanelName();
		String studyName = (String)request.getSession().getAttribute("studyName");
		String studyVersion = (String) request.getSession().getAttribute("studyVersion");
		
		logger.debug("Study is " + studyId);
		
		if ((panelName != null) && (panelName.length() > 1))
		{
			logger.debug("Panel is " + panelName);
			request.getSession().setAttribute("pop.panel", panelName);
		}
		else
		{
			// Make sure to remove any old panels if none was set
			request.getSession().removeAttribute("pop.panel");
		}
		
		// If they did not provide any criteria then lets redirect and not search
		if (popForm.isBlank())
		{
			logger.debug("Population form was left blank");
			forward = mapping.findForward("criteriaWarn");
			String redirect = ZipFindingsHelper.getAnonymousBrowseFtpUrl() +
				studyName + "/" + studyVersion + "/Population";
			request.setAttribute("bulkUrl", redirect);
			return forward;
		}
        
        // Get FindingsManager service from spring bean factory
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		
		// Get all the needed elements from the form and build the query
		SearchPopulationHelper spHelper = (SearchPopulationHelper)SpringContext.getBean("searchPopulationHelper");
		SNPFrequencyFindingCriteriaDTO freqDTO = spHelper.buildCriteria(popForm, studyId, errors);
		
		AnnotationCriteriaHelper helper = new AnnotationCriteriaHelper();
		AnnotationCriteria annoCrit = helper.buildCriteria(popForm, errors);
		
		// Set the annotation criteria and execute the search
		freqDTO.setAnnotationCriteria(annoCrit);
        
		Collection<SNPFrequencyFinding> findings =
			(Collection<SNPFrequencyFinding>)manager.getFindings(freqDTO, 0, CgemsConstants.MAX_RESULTS + 1);
		
		if (findings.size() <= CgemsConstants.MAX_RESULTS)
		{
			// Create the SNPAssociation reports
			ArrayList<SNPFrequencyFindingReport> results = new ArrayList<SNPFrequencyFindingReport>();
			for(SNPFrequencyFinding snpFrequencyFinding : findings)
			{
	        	if(snpFrequencyFinding != null)
	        	{
	        		SNPFrequencyFindingReport report =
	        			new SNPFrequencyFindingReport(snpFrequencyFinding);
	        		results.add(report);
	        	}
			}
			
			// Now sort the results before display
			Collections.sort(results);
			
			request.getSession().setAttribute("pop.results", results);
			
			logger.debug("Number of SNPs found is " + results.size());
			
			request.setAttribute("numberFindings", results.size());
			request.setAttribute("sortedColumn", "chromosome");
			request.setAttribute("sortOrder", "ascending");
		}
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	    }
	    else
	    {
	    	// If there were more results than we allow then forward to download page
			if (findings.size() > CgemsConstants.MAX_RESULTS)
			{
				// Put the type of search in the session
				request.getSession().setAttribute("searchType", "Population");
				
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
