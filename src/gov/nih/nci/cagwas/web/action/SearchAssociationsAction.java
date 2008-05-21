package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisMethod;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPAssociationFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.reports.SNPAssociationFindingReport;
import gov.nih.nci.cagwas.web.form.AssociationsForm;

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
 * The SearchAssociationsAction class is called when the Search Association Findings
 * form is posted.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SearchAssociationsAction extends Action
{
	private static Logger logger = Logger.getLogger(SearchAssociationsAction.class);
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		ActionMessages errors = new ActionMessages();
		ActionForward forward = null;
		AssociationsForm assocForm = (AssociationsForm)form;
		String studyName = (String)request.getSession().getAttribute("studyName");
		String studyVersion = (String) request.getSession().getAttribute("studyVersion");
		Long studyId = (Long)request.getSession().getAttribute("studyId");
		String caseStudyName = "GWAS STUDY III";
		request.getSession().setAttribute("caseStudyName", caseStudyName);
		String panelName = (String)assocForm.getPanelName();
		
		logger.debug("Study is " + studyName);
		
		if ((panelName != null) && (panelName.length() > 1))
		{
			logger.debug("Panel is " + panelName);
			request.getSession().setAttribute("assoc.panel", panelName);
		}
		else
		{
			// Make sure to remove any old panels if none was set
			request.getSession().removeAttribute("assoc.panel");
		}
		
		// If they did not provide any criteria then lets redirect and not search
		if (assocForm.isBlank())
		{
			logger.debug("Association form was left blank");
			forward = mapping.findForward("criteriaWarn");
			String redirect = ZipFindingsHelper.getAnonymousBrowseFtpUrl() +
				studyName + "/" + studyVersion + "/Association";
			request.setAttribute("bulkUrl", redirect);
			return forward;
		}
		
        // Get FindingsManager service from spring bean factory
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
        
		SNPAssociationFindingCriteriaDTO safDTO;
		AnnotationCriteria annoCrit;
		
		// Populate the analysis method codes before passing the form off
        int numMethods = Integer.parseInt(assocForm.getNumAnalysisMethods());
        for (int i = 1; i <= numMethods; i++)
        {
        	String method = assocForm.getAnalysisMethod(i);
        	
        	// If the method is not set but we know it is valid then get all the codes
        	if (method.length() < 1)
        	{
	        	Collection<SNPAnalysisMethod> methods =
	        		(Collection<SNPAnalysisMethod>)request.getSession().getAttribute("methods" + i);
	        	for (SNPAnalysisMethod meth : methods)
	        	{
	        		assocForm.addAnalysisCode(i, meth.getRepresensitiveCode());
	        	}
        	}
        	else
        	{
        		assocForm.addAnalysisCode(i, method);
        	}
        }
		
		// Get all the needed elements from the form and build the query
		SearchAssociationsHelper saHelper = (SearchAssociationsHelper)SpringContext.getBean("searchAssociationHelper");
		safDTO = saHelper.buildCriteria(assocForm, studyId, errors);
			
		AnnotationCriteriaHelper helper = new AnnotationCriteriaHelper();
		annoCrit = helper.buildCriteria(assocForm, errors);
		
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	      return forward;
	    }
		// Set the annotation criteria and execute the search
		safDTO.setAnnotationCriteria(annoCrit);
        
		Collection<SNPAssociationFinding> findings = 
			(Collection<SNPAssociationFinding>)manager.getFindings(safDTO, 0,
					CagwasConstants.MAX_RESULTS + 1);
		
		if (findings.size() <= CagwasConstants.MAX_RESULTS)
		{
			// Create the SNPAssociation reports
			ArrayList<SNPAssociationFindingReport> results = new ArrayList<SNPAssociationFindingReport>();
			String noOfCategories = null;
			for(SNPAssociationFinding snpAssociationFinding : findings)
			{
	        	if(snpAssociationFinding != null)
	        	{
	        		SNPAssociationFindingReport report =
	        			new SNPAssociationFindingReport(snpAssociationFinding);
	        		results.add(report);
	        		if(snpAssociationFinding.getSnpAssociationAnalysis()!= null){//checking if numberofcategories is null
	        			if(snpAssociationFinding.getSnpAssociationAnalysis().getNumberOfCategories()!=null)
	        			{	
	        			 noOfCategories= snpAssociationFinding.getSnpAssociationAnalysis().getNumberOfCategories().toString();
	        			}
	        		}
	        	}
			}
			
			// Now sort the results for display
			Collections.sort(results);
	        	
			request.getSession().setAttribute("assoc.results", results);
			
			logger.debug("Number of SNPs found is " + results.size());
			
			request.setAttribute("numberFindings", results.size());
			request.setAttribute("sortedColumn", "chromosome");
			request.setAttribute("sortOrder", "ascending");
			if(noOfCategories != null){				
			request.setAttribute("NoOfCategories", noOfCategories);
			}
		}
		
		// If there were errors then return to the input page else go on

    	// If there were more results than we allow then forward to download page
		if (findings.size() > CagwasConstants.MAX_RESULTS)
		{
			// Put the type of search in the session
			request.getSession().setAttribute("searchType", "Associations");
			
			// Put the form in the session to be passed to the job for scheduling
			request.getSession().setAttribute("form", form);
			
			// Set the forward to the download page
			forward = mapping.findForward("download");
		}
		else
			forward = mapping.findForward("success");

		
		return forward;
	}

}
