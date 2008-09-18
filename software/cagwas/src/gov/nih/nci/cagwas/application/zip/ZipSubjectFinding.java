package gov.nih.nci.cagwas.application.zip;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.application.mail.MailManager;
import gov.nih.nci.caintegrator.application.zip.FileNameGenerator;
import gov.nih.nci.caintegrator.application.zip.ZipItem;
import gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant;
import gov.nih.nci.caintegrator.exceptions.ValidationException;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyParticipantCriteria;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.reports.StudyParticipantReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The ZipSubjectFinding class queries for the subjects when the result
 * is large and should be written to a file on the FTP server rather than displayed
 * to the user.
 * <P>
 * @author mholck
 */
public class ZipSubjectFinding
{
	private static Logger logger = Logger.getLogger(ZipSubjectFinding.class);
	
	@SuppressWarnings("unchecked")
	public static String zipStudyParticipant(String email, StudyParticipantCriteria spCrit)
	{
		String targetFileName = null;
		String filename = null;
		BufferedWriter out = null;
		DateFormat dateFormat = DateFormat.getDateTimeInstance();
		String now = dateFormat.format(new Date());
		final String queryText = "Your query started at " + now + " was\n" + spCrit.toString();
 		final MailManager mailManager = new MailManager(ZipFindingsHelper.getMailPropertiesFilename());

		try
		{
			// Log a message to indicate a background job is running
	 		filename = FileNameGenerator.generateFileName(email);
	 		logger.warn("Subject background job " + filename + " begun at " + now);
	 		
			targetFileName = ZipFindingsHelper.getFilePrefix()+"."+filename+".txt";
			
			// Go ahead and write the headers to the file
			out = new BufferedWriter(new FileWriter(ZipFindingsHelper.getInputZipDirectory()+File.separator+targetFileName));
	        out.write(
		        ZipFindingsHelper.getCagwasProperties("table.header.participant")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.gender")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.age")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.affection")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.history")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.population")+"\n");
			
	        // Then get the findings and write them to the file
	        HashSet results = new HashSet();
	        final List list = Collections.synchronizedList(new ArrayList());
	        final StudyParticipantCriteria spc = spCrit;
	        final String e = email;
	        new Thread(new Runnable()
	        {
	             public void run()
	             {
	                 try
	                 {
                         FindingsManager manager = (FindingsManager) SpringContext.getBean("findingsManager");
	                    manager.populateStudySubjects(spc, list);
	                 }
	                 catch(Throwable t)
	                 {
	                     logger.error("Error from ZipSubjectFinding.populateStudySubjects call: ", t);
	                     mailManager.sendFTPErrorMail(e, queryText);
	                 }
	             }
	         }
	        ).start();

	        int count = 0;
	        do
	        {
	            synchronized(list)
	            {
	                if (list.size() > 0)
	                {
	                     results = (HashSet) list.remove(0);
	                     
	                     logger.debug("Results to be written are: " + results.size());
	                     count += results.size();
	                     
	                     for (Iterator iterator = results.iterator(); iterator.hasNext();)
	                     {
	                    	StudyParticipant subject = (StudyParticipant) iterator.next();
	                    	StudyParticipantReport report = new StudyParticipantReport(subject);
	                    	out.write(report.getStudySubjectIdentifier()+"\t"+
			        			report.getAdministrativeGenderCode()+"\t"+
			        			report.getAgeAtEnrollment()+"\t"+
			        			report.getCaseControlStatus()+"\t"+
			        			report.getFamilyHistory()+"\t"+
			        			ZipFindingsHelper.getPopulations(report.getPopulationNameCollection())+"\n");
	                     }
	                     
	                     logger.debug("Total written for this search: " + count);
	                     
	                     if (results.size() == 0)
	                     {
	                    	 // means no more results are coming
	                         break;
	                     }
	                     
	                     // Set the results to null to let them be garbage collected
	                     results = null;
	                     out.flush();
	                }
	             }
	             Thread.sleep(10);
	         } while(true);
	    }
		catch (IOException e)
		{
	    	logger.error(e.getMessage());
	    	mailManager.sendFTPErrorMail(email, queryText);
	    	return targetFileName;
	    }
		catch (ValidationException e)
		{
			logger.error(e.getMessage());
			mailManager.sendFTPErrorMail(email, queryText);
			return targetFileName;
		}
		catch (Exception e)
		{
	    	logger.error(e.getMessage());
	    	mailManager.sendFTPErrorMail(email, queryText);
	    	return targetFileName;
		}
		finally
		{
			try
			{
				if (out != null)
					out.close();
			}
			catch(IOException ie)
			{
				logger.error("Unable to close the FTP file", ie);
			}
		}
		
		// Zip up the file
		ZipItem zipped = ZipFindingsHelper.fileToZip(targetFileName);
		ZipFindingsHelper.zipFile(zipped);
		
		// Now send the notification email
		ArrayList<String> fileList = new ArrayList<String>();
		fileList.add(zipped.getFileName());
		mailManager.sendFTPMail(email, fileList, queryText);
		
		// Now delete the old text file since it is no longer needed
		boolean success = false;
		File f = new File(ZipFindingsHelper.getInputZipDirectory()+File.separator+targetFileName);
		
		success = f.delete();
		if (!success)
			logger.error("Error trying to delete file " + targetFileName);
		
		// Now log the completion of the job
        logger.warn("Subject background job " + filename + " ended at " +
        		dateFormat.format(new Date()));
        
		return targetFileName;
	}

}
