package gov.nih.nci.cagwas.web.job;

import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyParticipantCriteria;
import gov.nih.nci.cagwas.application.zip.ZipSubjectFinding;
import gov.nih.nci.cagwas.web.action.SearchSubjectHelper;
import gov.nih.nci.cagwas.web.form.SubjectForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * The SearchSubjectJob class is the job to search for subjects
 * using the FTP method for large results. This is a quartz job that will
 * be scheduled to run when there are more than MAX_RESULTS returned.
 * <P>
 * @author mholck
 * @see import org.quartz.Job;
 */
public class SearchSubjectJob implements Job
{
	private static Logger logger = Logger.getLogger(SearchSubjectJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		// Set the thread priority to a nicer value for background processing
		Thread.currentThread().setPriority(4);
		
		logger.debug("SearchSubjectJob is running");
		logger.debug("Our priority is " + Thread.currentThread().getPriority());
		
		String email = context.getJobDetail().getJobDataMap().getString("email");
		Long studyId = (Long)context.getJobDetail().getJobDataMap().get("studyId");
		SubjectForm sForm = (SubjectForm)context.getJobDetail().getJobDataMap().get("form");
		
		logger.debug("Email is " + email);
		
		// Get all the needed elements from the form and build the query
		ActionMessages errors = new ActionMessages();
		SearchSubjectHelper helper = new SearchSubjectHelper();
		StudyParticipantCriteria spCrit = helper.buildCriteria(sForm, studyId, errors);
		ZipSubjectFinding.zipStudyParticipant(email, spCrit);
	}

}
