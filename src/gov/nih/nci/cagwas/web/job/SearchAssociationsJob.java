package gov.nih.nci.cgems.web.job;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPAssociationFindingCriteriaDTO;
import gov.nih.nci.cgems.application.zip.ZipSNPAssociationFinding;
import gov.nih.nci.cgems.web.action.AnnotationCriteriaHelper;
import gov.nih.nci.cgems.web.action.SearchAssociationsHelper;
import gov.nih.nci.cgems.web.form.AssociationsForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * The SearchAssociationsJob class is the job to search for associations
 * using the FTP method for large results. This is a quartz job that will
 * be scheduled to run when there are more than MAX_RESULTS returned.
 * <P>
 * @author mholck
 * @see import org.quartz.Job;
 */
public class SearchAssociationsJob implements Job
{
	private static Logger logger = Logger.getLogger(SearchAssociationsJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		// Set the thread priority to a nicer value for background processing
		Thread.currentThread().setPriority(4);
		
		logger.debug("SearchAssociationsJob is running");
		logger.debug("Our priority is " + Thread.currentThread().getPriority());
		
		String email = context.getJobDetail().getJobDataMap().getString("email");
		Long studyId = (Long)context.getJobDetail().getJobDataMap().get("studyId");
		String studyName = context.getJobDetail().getJobDataMap().getString("studyName");	
		String caseStudyName = context.getJobDetail().getJobDataMap().getString("caseStudyName");
		AssociationsForm assocForm = (AssociationsForm)context.getJobDetail().getJobDataMap().get("form");
		
		logger.debug("Email is " + email);
		
		// Get all the needed elements from the form and build the query
		ActionMessages errors = new ActionMessages();
		SearchAssociationsHelper saHelper = (SearchAssociationsHelper) SpringContext.getBean("searchAssociationHelper");
		
		SNPAssociationFindingCriteriaDTO safDTO = null;
		try
		{
			safDTO = saHelper.buildCriteria(assocForm, studyId, errors);
		
			AnnotationCriteriaHelper helper = new AnnotationCriteriaHelper();
			AnnotationCriteria annoCrit = helper.buildCriteria(assocForm, errors);
			
			// Set the annotation criteria
			safDTO.setAnnotationCriteria(annoCrit);
		
			// Execute the search
			ZipSNPAssociationFinding.zipSNPAssociationFindingCriteriaDTO(email, studyName, caseStudyName, safDTO);
		}
		catch(Exception e)
		{
			throw new JobExecutionException();
		}
	}

}
