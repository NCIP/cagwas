/**
 * 
 */
package gov.nih.nci.cgems.application.zip;

import gov.nih.nci.caintegrator.application.zip.ZipConfig;
import gov.nih.nci.caintegrator.application.zip.ZipItem;
import gov.nih.nci.caintegrator.application.zip.ZipManager;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker;
import gov.nih.nci.caintegrator.domain.study.bean.Population;
import gov.nih.nci.cgems.application.zip.CGEMSZipItemImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author sahnih
 *
 */
public class ZipFindingsHelper {
	private static Logger logger = Logger.getLogger(ZipFindingsHelper.class);
	private static String CGEMS_PROPERTIES = "cgemsResources.properties";
	private static String CGEMS_ZIP_PROPERTIES = "gov.nih.nci.cgems.zip.properties";
	private static String CGEMS_MAIL_PROPERTIES = "gov.nih.nci.cgems.mail.properties";
	private static Properties cgemsProperties;	
	private static String inputZipDirectory = "D:";
	private static String outputZipDirectory = "D:";
	private static String anonymousBrowseFtpUrl ;
	private static String secureFtpUrl;
	private static String directoryInZip = "CGEMS";
	private static String filePrefix = "caintegrator_hidden";
	private static String genotypeDirectory = "D:";
	//private static String ftpURL = "ftp://cbiocgemsftp.nci.nih.gov";
    static
    {
    	// Handle exceptions
    	try
    	 {
    		cgemsProperties = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader()
								.getResourceAsStream(CGEMS_PROPERTIES);
			cgemsProperties.load(in);		
			in.close();
			
     	 }
    	catch(IOException ioe) 
    	{
			logger.error(ioe.getMessage());
    		throw new RuntimeException("Could not read cgems configuration file");
    	}
    	ZipConfig zipConfig = ZipConfig.getInstance(CGEMS_ZIP_PROPERTIES);
    	inputZipDirectory = zipConfig.getZipLocation();
    	outputZipDirectory = zipConfig.getFtpLocation();
    	genotypeDirectory = zipConfig.getGenotypeLocation();
    	anonymousBrowseFtpUrl = zipConfig.getAnonymousBrowseFtpUrl();
    	secureFtpUrl = zipConfig.getSecureFtpUrl();
    	filePrefix = zipConfig.getFilePrefix();
    }

	public static String getGeneBiomarkers(Set<GeneBiomarker> geneBiomarkerSet)
	{
		String geneList = "";
		if(geneBiomarkerSet != null)
		{
			for(GeneBiomarker geneBiomarker : geneBiomarkerSet)
			{
				geneList = geneList + geneBiomarker.getHugoGeneSymbol() + "|";				
			}
			//remove Last |
			if(geneList.endsWith("|"))
			{
				geneList = geneList.substring(0,geneList.lastIndexOf("|"));
			}
		}
		return geneList;
	}
	public static String getPopulations(Set<String> populationSet)
	{
		String populationList = "";
		if(populationSet != null)
		{
			for(String populationName : populationSet)
			{
				populationList = populationList + populationName + "|";				
			}
			//remove Last |
			if(populationList.endsWith("|"))
			{
				populationList = populationList.substring(0,populationList.lastIndexOf("|"));
			}
		}
		return populationList;
	}

	public static ZipItem fileToZip(String fileName){
		ZipItem zipItem = null;
        /////Setup the Zip File
		String targetFileName = fileName;
		String targetZipFileName = fileName.replace(".txt", ".zip");
		String filePath = getInputZipDirectory()+File.separator+targetFileName;
	    File f = new File(filePath);
	    if (f.exists()) {
	    	zipItem = new CGEMSZipItemImpl();
	        zipItem.setFileName(targetZipFileName);
	        zipItem.setFilePath(filePath);
	        zipItem.setDirectoryInZip(directoryInZip);

	    }
		return zipItem;
	}
	public static void zipFile(ZipItem zipItem){
		if(zipItem != null){
			// start zipping and wait for it to finish
			ZipManager zipper = new ZipManager();
			List<ZipItem> zipItemCollection = new ArrayList<ZipItem>();
			zipItemCollection.add(zipItem);
			zipper.setItems(zipItemCollection);
	        zipper.setTarget(getOutputZipDirectory()+File.separator+zipItem.getFileName());
	        zipper.setBreakIntoMultipleFileIfLarge(false);
	        zipper.setZipPropertyFilename(CGEMS_ZIP_PROPERTIES);
	        zipper.run();
		}
	}
	/**
	 * @return Returns the cgemsProperties.
	 */
	public static String getCgemsProperties(String propertyName) {
		return cgemsProperties.getProperty(propertyName);
	}

	/**
	 * @return Returns the directoryInZip.
	 */
	public static String getDirectoryInZip() {
		return directoryInZip;
	}

	/**
	 * @return Returns the filePrefix.
	 */
	public static String getFilePrefix() {
		return filePrefix;
	}

	/**
	 * @return Returns the inputZipDirectory.
	 */
	public static String getInputZipDirectory() {
		return inputZipDirectory;
	}

	/**
	 * @return Returns the outputZipDirectory.
	 */
	public static String getOutputZipDirectory() {
		return outputZipDirectory;
	}

	/**
	 * @return Returns the anonymousBrowseFtpUrl.
	 */
	public static String getAnonymousBrowseFtpUrl() {
		return anonymousBrowseFtpUrl;
	}

	/**
	 * @return Returns the secureFtpUrl.
	 */
	public static String getSecureFtpUrl() {
		return secureFtpUrl;
	}

	public static String getMailPropertiesFilename(){
		return CGEMS_MAIL_PROPERTIES;
	}

	public static String getGenotypeDirectory(){
		return genotypeDirectory;
	}

}
