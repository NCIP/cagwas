/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.job;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.GenotypeFindingCriteriaDTO;
import gov.nih.nci.cagwas.application.zip.ZipGenotypeFinding;
import gov.nih.nci.cagwas.web.action.AnnotationCriteriaHelper;
import gov.nih.nci.cagwas.web.action.SearchGenotypeHelper;
import gov.nih.nci.cagwas.web.form.GenotypeForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * The SearchGenotypeJob class is the job to search for genotypes
 * using the FTP method for large results. This is a quartz job that will
 * be scheduled to run for all genotype searches.
 * <P>
 * @author mholck
 * @see import org.quartz.Job;
 */
public class SearchGenotypeJob implements Job
{
	private static Logger logger = Logger.getLogger(SearchGenotypeJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		logger.debug("SearchGenotypeJob is running");
		
		String email = context.getJobDetail().getJobDataMap().getString("email");
		Long studyId = (Long)context.getJobDetail().getJobDataMap().get("studyId");
		GenotypeForm genoForm = (GenotypeForm)context.getJobDetail().getJobDataMap().get("form");
		
		logger.debug("Email is " + email);
		logger.debug("Study is " + studyId);
		
		// Get all the needed elements from the form and build the query
		ActionMessages errors = new ActionMessages();
		SearchGenotypeHelper sgHelper = (SearchGenotypeHelper) SpringContext.getBean("searchGenotypeHelper");
		
		try
		{
			GenotypeFindingCriteriaDTO gfDTO = sgHelper.buildCriteria(genoForm, studyId, errors);
			
			AnnotationCriteriaHelper helper = new AnnotationCriteriaHelper();
			AnnotationCriteria annoCrit = helper.buildCriteria(genoForm, errors);
			
			// Set the annotation criteria and execute the search
			gfDTO.setAnnotationCriteria(annoCrit);
			//ZipGenotypeFinding.zipGenotypeFindingCriteriaDTO(email, gfDTO);
			// Temporary test for reading the files
			ZipGenotypeFinding.findGenotypeFile(email, gfDTO);
		}
		catch (Exception e)
		{
			throw new JobExecutionException();
		}
	}

}
