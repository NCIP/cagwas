/**
 * 
 */
package gov.nih.nci.cagwas.web;

import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.caintegrator.application.mail.MailConfig;
import gov.nih.nci.caintegrator.application.schedule.DeleteOldFilesJob;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.TriggerUtils;


/**
 * @author sahnih
 *
 */
public class DeleteOldResultsFilesJob {
private static DeleteOldResultsFilesJob instance = null;
private static Logger logger = Logger.getLogger(DeleteOldResultsFilesJob.class);
    
    private DeleteOldResultsFilesJob(){
        super();
        createDeleteOldResultsFilesJob();
    }
    
    private void createDeleteOldResultsFilesJob() {
		JobDetail deleteOldResultsFilesJob = new JobDetail("DeleteOldResultsFilesJob", null, DeleteOldFilesJob.class);

		if (deleteOldResultsFilesJob != null)
		{				
			String dirPath = System.getProperty(ZipFindingsHelper.getOutputZipDirectory());
			String fileRetentionPeriodInDays = MailConfig.getInstance(ZipFindingsHelper.getMailPropertiesFilename()).getFileRetentionPeriodInDays();

			deleteOldResultsFilesJob.getJobDataMap().put("dirPath", dirPath);
			deleteOldResultsFilesJob.getJobDataMap().put("fileRetentionPeriodInDays", fileRetentionPeriodInDays);

		}
		
		try {
			SchedulerPlugIn.scheduleWork(deleteOldResultsFilesJob, TriggerUtils.makeDailyTrigger("DeleteOldResultsFilesJob", 0, 0));
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		} //run it daily at midnight			

	}

	public static DeleteOldResultsFilesJob getInstance() {
        
          if (instance == null) {
            instance = new DeleteOldResultsFilesJob();
          }
          return instance;
    }
}
