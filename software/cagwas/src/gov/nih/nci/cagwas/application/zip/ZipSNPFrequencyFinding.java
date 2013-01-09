/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.application.zip;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.application.mail.MailManager;
import gov.nih.nci.caintegrator.application.zip.FileNameGenerator;
import gov.nih.nci.caintegrator.application.zip.ZipItem;
import gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding;
import gov.nih.nci.caintegrator.exceptions.ValidationException;
import gov.nih.nci.caintegrator.studyQueryService.dto.FindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.reports.SNPFrequencyFindingReport;

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
 * The ZipSNPFrequencyFinding class queries for the SNP frequencies when the result
 * is large and should be written to a file on the FTP server rather than displayed
 * to the user.
 * <P>
 * @author sahnih, mholck
 */
public class ZipSNPFrequencyFinding
{
	private static Logger logger = Logger.getLogger(ZipSNPFrequencyFinding.class);

	@SuppressWarnings("unchecked")
	public static String zipSNPFrequencyFindingCriteriaDTO(String email, FindingCriteriaDTO findingCriteriaDTO)
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
	 		logger.warn("SNP Frequency background job " + filename + " begun at " + now);
	 		
			targetFileName = ZipFindingsHelper.getFilePrefix()+"."+filename+".txt";
			
			// Go ahead and write the headers to the file
		    out = new BufferedWriter(new FileWriter(ZipFindingsHelper.getInputZipDirectory()+File.separator+targetFileName));
		    out.write(
		    	ZipFindingsHelper.getCagwasProperties("table.header.snpId")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.chromosome")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.position")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.genes")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.population")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.completion")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.weinberg")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.allele")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.allele.count")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.genotype")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.genotype.count")+"\n");
		        
		    // Then get the findings and write them to the file
		    HashSet results = new HashSet();
	        final List list = Collections.synchronizedList(new ArrayList());
	        final FindingCriteriaDTO freqDTO = findingCriteriaDTO;
	        final String e = email;
	        new Thread(new Runnable()
	        {
	             public void run()
	             {
	                 try
	                 {
                         FindingsManager manager = (FindingsManager) SpringContext.getBean("findingsManager");
	                    manager.populateFindings(freqDTO, list);
	                 }
	                 catch(Throwable t)
	                 {
	                     logger.error("Error from ZipSNPFrequencyFinding.populateFindings call: ", t);
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
	                    	SNPFrequencyFinding snpFrequencyFinding = (SNPFrequencyFinding) iterator.next();
	                    	SNPFrequencyFindingReport snpFrequencyFindingReport = new SNPFrequencyFindingReport(snpFrequencyFinding);
	                    	out.write(snpFrequencyFindingReport.getDbsnpId()+"\t"+
			        			snpFrequencyFindingReport.getChromosomeName()+"\t"+
			        			snpFrequencyFindingReport.getChromosomeLocation()+"\t"+
			        			ZipFindingsHelper.getGeneBiomarkers(snpFrequencyFindingReport.getGeneBiomarkerCollection())+"\t"+
			        			snpFrequencyFindingReport.getPopulationName()+"\t"+
			        			snpFrequencyFindingReport.getCompletionRate()+ "% " + snpFrequencyFindingReport.getCompletionRateValues() +"\t"+
			        			snpFrequencyFindingReport.getHardyWeinbergPValue()+"\t"+
			        			snpFrequencyFindingReport.getReferenceAllele()+ "|" + snpFrequencyFindingReport.getOtherAllele()+"\t"+
			        			getAlleleCount(snpFrequencyFindingReport)+"\t"+
			        			snpFrequencyFindingReport.getReferenceAllele()+snpFrequencyFindingReport.getReferenceAllele()+"|"+
			        			snpFrequencyFindingReport.getReferenceAllele()+snpFrequencyFindingReport.getOtherAllele()+"|"+
			        			snpFrequencyFindingReport.getOtherAllele()+snpFrequencyFindingReport.getOtherAllele()+"\t"+
			        			getGenotypeCount(snpFrequencyFindingReport)+"\n");
	                     }
	                     
	                     logger.debug("Total written for this search: " + count);
	                     
	                     if (results.size() == 0)
	                     {
	                         // means no more results are coming
	                         break;
	                     }
	                     
	                     //	Set the results to null to let them be garbage collected
	                     results = null;
	                     out.flush();
	                }
	             }
	             Thread.sleep(10);
	         } while(true);
		}
		catch (IOException ie)
		{
		    logger.error(ie.getMessage());
		    mailManager.sendFTPErrorMail(email, queryText);
		    return null;
		}
		catch (ValidationException ve)
		{
			logger.error(ve.getMessage());
		   	mailManager.sendFTPErrorMail(email, queryText);
		   	return null;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		   	mailManager.sendFTPErrorMail(email, queryText);
		   	return null;
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
        logger.warn("SNP Frequency background job " + filename + " ended at " +
        		dateFormat.format(new Date()));
		
		return null;
	}

	/**
	 * A helper method for formatting the genotype count output
	 * <P>
	 * @param snpFrequencyFindingReport The report object for getting the data
	 * @return The formatted string
	 */
	public static String getGenotypeCount(SNPFrequencyFindingReport snpFrequencyFindingReport)
	{
		String genotypeCount = snpFrequencyFindingReport.getReferenceHomozygoteCount() + "(" +
			snpFrequencyFindingReport.getHomozygoteFrequency() + ")" + "|" +
			snpFrequencyFindingReport.getHeterozygoteCount() + "(" +
			snpFrequencyFindingReport.getHeterozygoteFrequency() + ")" + "|" +
			snpFrequencyFindingReport.getOtherHomozygoteCount() + "(" +
			snpFrequencyFindingReport.getOtherHomozygoteFrequency() + ")";
		
		return genotypeCount;
	}

	/**
	 * A helper method for formatting the allele count output
	 * <P>
	 * @param snpFrequencyFindingReport The report object for getting the data
	 * @return The formatted string
	 */
	public static String getAlleleCount(SNPFrequencyFindingReport snpFrequencyFindingReport)
	{
		String formattedAlleleCount = snpFrequencyFindingReport.getReferenceAlleleCount() + "(" +
			snpFrequencyFindingReport.getAlleleFrequency() + ")" + "|" +
			snpFrequencyFindingReport.getOtherAlleleCount() + "(" +
			snpFrequencyFindingReport.getOtherFrequency() + ")";
		
		return formattedAlleleCount;
	}
}