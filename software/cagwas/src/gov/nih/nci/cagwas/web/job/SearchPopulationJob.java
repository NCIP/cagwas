package gov.nih.nci.cagwas.web.job;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPFrequencyFindingCriteriaDTO;
import gov.nih.nci.cagwas.application.zip.ZipSNPFrequencyFinding;
import gov.nih.nci.cagwas.web.action.AnnotationCriteriaHelper;
import gov.nih.nci.cagwas.web.action.SearchPopulationHelper;
import gov.nih.nci.cagwas.web.form.PopulationForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * The SearchPopulationJob class is the job to search for population frequencies
 * using the FTP method for large results. This is a quartz job that will
 * be scheduled to run when there are more than MAX_RESULTS returned.
 * <P>
 * @author mholck
 * @see import org.quartz.Job;
 */
public class SearchPopulationJob implements Job
{
	private static Logger logger = Logger.getLogger(SearchPopulationJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		// Set the thread priority to a nicer value for background processing
		Thread.currentThread().setPriority(4);
		
		logger.debug("SearchPopulationJob is running");
		logger.debug("Our priority is " + Thread.currentThread().getPriority());
		
		String email = context.getJobDetail().getJobDataMap().getString("email");
		Long studyId = (Long)context.getJobDetail().getJobDataMap().get("studyId");
		PopulationForm popForm = (PopulationForm)context.getJobDetail().getJobDataMap().get("form");
		
		logger.debug("Email is " + email);
		
		// Get all the needed elements from the form and build the query
		ActionMessages errors = new ActionMessages();
		SearchPopulationHelper spHelper = (SearchPopulationHelper) SpringContext.getBean("searchPopulationHelper");
		
		try
		{
			SNPFrequencyFindingCriteriaDTO freqDTO = spHelper.buildCriteria(popForm, studyId, errors);
			
			AnnotationCriteriaHelper helper = new AnnotationCriteriaHelper();
			AnnotationCriteria annoCrit = helper.buildCriteria(popForm, errors);
			
			// Set the annotation criteria and execute the search
			freqDTO.setAnnotationCriteria(annoCrit);
			ZipSNPFrequencyFinding.zipSNPFrequencyFindingCriteriaDTO(email, freqDTO);
		}
		catch (Exception e)
		{
			throw new JobExecutionException();
		}
	}

}
