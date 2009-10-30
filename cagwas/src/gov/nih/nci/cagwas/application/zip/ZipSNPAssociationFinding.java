package gov.nih.nci.cagwas.application.zip;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.application.mail.MailManager;
import gov.nih.nci.caintegrator.application.zip.FileNameGenerator;
import gov.nih.nci.caintegrator.application.zip.ZipItem;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding;
import gov.nih.nci.caintegrator.exceptions.ValidationException;
import gov.nih.nci.caintegrator.studyQueryService.dto.FindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.reports.SNPAssociationFindingReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.text.DateFormat;

import org.apache.log4j.Logger;

/**
 * The ZipSNPAssociationFinding class queries for the SNP associations when the result
 * is large and should be written to a file on the FTP server rather than displayed
 * to the user.
 * <P>
 * @author sahnih, mholck
 */
public class ZipSNPAssociationFinding
{
	private static Logger logger = Logger.getLogger(ZipSNPAssociationFinding.class);
	
	@SuppressWarnings("unchecked")
	public static String zipSNPAssociationFindingCriteriaDTO(String email, String studyName,
			String caseStudyName, FindingCriteriaDTO findingCriteriaDTO)
	{
		String targetFileName = null;
		String filename = null;
		BufferedWriter out = null;
		DateFormat dateFormat = DateFormat.getDateTimeInstance();
		String now = dateFormat.format(new Date());
		final String queryText = "Your query started at " + now + " was\n" + findingCriteriaDTO.toString();
 		final MailManager mailManager = new MailManager(ZipFindingsHelper.getMailPropertiesFilename());
 		
		try
		{
			// Log a message to indicate a background job is running
	 		filename = FileNameGenerator.generateFileName(email);
	 		logger.warn("SNP Association background job " + filename + " begun at " + now);
	 		
			targetFileName = ZipFindingsHelper.getFilePrefix()+"."+filename+".txt";
			
			// Go ahead and write the headers to the file
			out = new BufferedWriter(new FileWriter(ZipFindingsHelper.getInputZipDirectory()+File.separator+targetFileName));

			// First write out the headers
			out.write(ZipFindingsHelper.getCagwasProperties("table.header.snpId")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.chromosome")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.position")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.genes")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.analysis")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.pvalue")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.rank")+"\t");
			
			// Handle the case study specific case
			if (studyName.equals(caseStudyName))
			{
				out.write(ZipFindingsHelper.getCagwasProperties("table.header.eor")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.eor")+" "+
				        ZipFindingsHelper.getCagwasProperties("table.header.homozygote.risk")+"\n");
			}
			else
			{
				out.write(ZipFindingsHelper.getCagwasProperties("table.header.nonaggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.nonaggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.homozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.aggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.aggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.homozygote.risk")+"\n");
			}
			
	        // Then get the findings and write them to the file
	        HashSet results = new HashSet();
	        final List list = Collections.synchronizedList(new ArrayList());
	        final FindingCriteriaDTO safDTO = findingCriteriaDTO;
	        final String e = email;
	        new Thread(new Runnable()
	        {
	             public void run()
	             {
	                 try
	                 {
                         FindingsManager manager = (FindingsManager) SpringContext.getBean("findingsManager");
	                    manager.populateFindings(safDTO, list);
	                 }
	                 catch(Throwable t)
	                 {
	                     logger.error("Error from ZipSNPAssociationsFinding.populateFindings call: ", t);
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
	                        SNPAssociationFinding sf = (SNPAssociationFinding) iterator.next();
	                        SNPAssociationFindingReport snpAssociationFindingReport = new SNPAssociationFindingReport(sf);
	                        out.write(snpAssociationFindingReport.getDbsnpId()+"\t"+
	        						snpAssociationFindingReport.getChromosomeName()+"\t"+
	        						snpAssociationFindingReport.getChromosomeLocation()+"\t"+
	        						ZipFindingsHelper.getGeneBiomarkers(snpAssociationFindingReport.getGeneBiomarkerCollection())+"\t"+
	        						snpAssociationFindingReport.getSnpAssociationAnalysisName()+"\t"+
	        						snpAssociationFindingReport.getPvalue()+"\t"+
	        						snpAssociationFindingReport.getRank()+"\t");
	        				
	        				// Handle the case study specific case
	        				if (studyName.equals(caseStudyName))
	        				{
	        					out.write(snpAssociationFindingReport.getCaseHeterozygote()+"\t"+
	        		        			snpAssociationFindingReport.getCaseHomozygote()+"\n");
	        				}
	        				else
	        				{
	        					out.write(snpAssociationFindingReport.getNonaggressiveHeterozygote()+"\t"+
	        		        			snpAssociationFindingReport.getNonaggressiveHomozygote()+"\t"+
	        		        			snpAssociationFindingReport.getAggressiveHeterozygote()+"\t"+
	        		        			snpAssociationFindingReport.getAggressiveHomozygote()+"\n");
	        				}
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
        logger.warn("SNP Association background job " + filename + " ended at " +
        		dateFormat.format(new Date()));
		
		return targetFileName;
	}

}