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
import gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding;
import gov.nih.nci.caintegrator.exceptions.ValidationException;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.GenotypeFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.reports.GenotypeReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The ZipGenotypeFinding class queries for the genotypes when the result
 * is large and should be written to a file on the FTP server rather than displayed
 * to the user.
 * <P>
 * @author mholck
 */
public class ZipGenotypeFinding
{
	private static Logger logger = Logger.getLogger(ZipGenotypeFinding.class);
	
	@SuppressWarnings("unchecked")
	public static String zipGenotypeFindingCriteriaDTO(String email, GenotypeFindingCriteriaDTO genoCrit)
	{
		String targetFileName = null;
		String filename = null;
		BufferedWriter out = null;
		DateFormat dateFormat = DateFormat.getDateTimeInstance();
		String now = dateFormat.format(new Date());
		final String queryText = "Your query started at " + now + " was\n" + genoCrit.toString();
 		final MailManager mailManager = new MailManager(ZipFindingsHelper.getMailPropertiesFilename());

		try
		{
			// Log a message to indicate a background job is running
	 		filename = FileNameGenerator.generateFileName(email);
	 		logger.warn("Genotype background job " + filename + " begun at " + now);
	 		
			targetFileName = ZipFindingsHelper.getFilePrefix()+"."+filename+".txt";
			
			// Go ahead and write the headers to the file
			out = new BufferedWriter(new FileWriter(ZipFindingsHelper.getInputZipDirectory()+File.separator+targetFileName));
	        out.write(
		        ZipFindingsHelper.getCagwasProperties("table.header.snpId")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.participant")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.specimen")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.allele1")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.allele2")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.quality")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.status")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.normalx")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.normaly")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.rawx")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.rawy")+"\n");
			
	        // Then get the findings and write them to the file
	        HashSet results = new HashSet();
	        final List list = Collections.synchronizedList(new ArrayList());
	        final GenotypeFindingCriteriaDTO crit = genoCrit;
	        final String e = email;
	        new Thread(new Runnable()
	        {
	             public void run()
	             {
	                 try
	                 {
                        FindingsManager manager = (FindingsManager) SpringContext.getBean("findingsManager");
	                    manager.populateFindings(crit, list);
	                 }
	                 catch(Throwable t)
	                 {
	                     logger.error("Error from ZipGenotypeFinding.populateFindings call: ", t);
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
	                    	GenotypeFinding genotype = (GenotypeFinding) iterator.next();
	                    	GenotypeReport report = new GenotypeReport(genotype);
	                    	out.write(report.getDbsnpId()+"\t"+
			        			report.getStudyParticipantID()+"\t"+
			        			report.getSpecimenID()+"\t"+
			        			report.getAllele1()+"\t"+
			        			report.getAllele2()+"\t"+
			        			report.getQualityScore()+"\t"+
			        			report.getStatus()+"\t"+
			        			report.getNormalizedXIntensity()+"\t"+
			        			report.getNormalizedYIntensity()+"\t"+
			        			report.getRawXIntensity()+"\t"+
			        			report.getRawYIntensity()+"\n");
	                     }
	                     
	                     logger.debug("Total written for user " + email + " this search: " + count);
	                     
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
        logger.warn("Genotype background job " + filename + " ended at " +
        		dateFormat.format(new Date()));
        
		return targetFileName;
	}

	/**
	 * findGenotypeFile will search flat files for SNPs instead of querying the database.
	 * This was done because the DB performance was very slow for Genotype queries. It still
	 * uses the DTO to just get the list of SNP ids and then searches the files for those
	 * SNPs.
	 * <P>
	 * @param email The email address to send the FTP notification or error to
	 * @param genoCrit The search criteria used to get the SNP ids
	 * @return String The filename of the created results
	 */
	public static String findGenotypeFile(String email, GenotypeFindingCriteriaDTO genoCrit)
	{
		String targetFileName = null;
		BufferedWriter out = null;
		final String queryText = "Your query was\n" + genoCrit.toString();
 		final MailManager mailManager = new MailManager(ZipFindingsHelper.getMailPropertiesFilename());

		try
		{
			targetFileName = ZipFindingsHelper.getFilePrefix()+"."+FileNameGenerator.generateFileName(email)+".txt";
			
			// Go ahead and write the headers to the file
			out = new BufferedWriter(new FileWriter(ZipFindingsHelper.getInputZipDirectory()+File.separator+targetFileName));
	        out.write(
		        ZipFindingsHelper.getCagwasProperties("table.header.snpId")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.participant")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.specimen")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.allele1")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.allele2")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.quality")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.status")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.normalx")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.normaly")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.rawx")+"\t"+
		        ZipFindingsHelper.getCagwasProperties("table.header.rawy")+"\n");
			
	        // Get the list of SNP ids that match the initial criteria
	        Collection<String> snpIds = genoCrit.getAnnotationCriteria().getSnpIdentifiers();
	        String status = genoCrit.getQCStatus();
	        
	        // Then get the list of files to search in the appropriate directory
	        File searchDir = new File(ZipFindingsHelper.getGenotypeDirectory());
	        
	        String files[] = searchDir.list();
	        
	        for (int i = 0; i < files.length; i++)
	        {
	        	String filename = searchDir.getAbsolutePath() + System.getProperty("file.separator") + files[i];
	        	logger.debug("Searching file " + filename + " for SNPs");
	        	searchFile(snpIds, status, filename, out);
	        }
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
		
		return targetFileName;
	}
	
	/**
	 * searchFile takes a collection of SNP ids and a status and searches the passed in filename
	 * for SNPs that match this.
	 * <P>
	 * @param snpIds The SNP ids to search for
	 * @param status The QC status to check as well while searching
	 * @param filename The filename to search for these SNPs
	 * @param out The output to send the results to
	 */
	private static void searchFile(Collection<String> snpIds, String status, String filename, BufferedWriter out)
	{
		BufferedReader in = null;
		
		try
		{
			in = new BufferedReader(new FileReader(filename));
			
			String line = in.readLine();
			while (line != null)
			{
				String patternStr = "\\|";
			    String[] snps = line.split(patternStr);
			    
			    // See if we found our SNP id
			    if (snpIds.contains(snps[0]))
			    {
			    	// Then check the status
			    	if ((snps.length > 6) && (status.equalsIgnoreCase(snps[6])))
			    	{
				    	for (int i=0; i < snps.length; i++)
				    		out.write(snps[i] + "\t");
				    	out.write("\n");
			    	}
			    }
				line = in.readLine();
			}
			
			logger.debug("Finished searching file " + filename);
		}
		catch (Exception e)
		{
			logger.error("Unable to read the genotype files", e);
		}
	}
}