/**
 * 
 */
package gov.nih.nci.cagwas.application.zip;

import gov.nih.nci.caintegrator.application.zip.ZipConfig;
import gov.nih.nci.caintegrator.application.zip.ZipItem;
import gov.nih.nci.caintegrator.application.zip.ZipManager;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker;
import gov.nih.nci.caintegrator.domain.study.bean.Population;
import gov.nih.nci.cagwas.application.zip.caGWASZipItemImpl;

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
	private static String CAGWAS_PROPERTIES = "cagwasResources.properties";
	private static String CAGWAS_ZIP_PROPERTIES = "gov.nih.nci.cagwas.zip.properties";
	private static String CAGWAS_MAIL_PROPERTIES = "gov.nih.nci.cagwas.mail.properties";
	private static Properties cagwasProperties;	
	private static String inputZipDirectory = "D:";
	private static String outputZipDirectory = "D:";
	private static String anonymousBrowseFtpUrl ;
	private static String secureFtpUrl;
	private static String directoryInZip = "CAGWAS";
	private static String filePrefix = "caintegrator_hidden";
	private static String genotypeDirectory = "D:";
	//private static String ftpURL = "ftp://cbiocagwasftp.nci.nih.gov";
    static
    {
    	// Handle exceptions
    	try
    	 {
    		cagwasProperties = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader()
								.getResourceAsStream(CAGWAS_PROPERTIES);
			cagwasProperties.load(in);		
			in.close();
			
     	 }
    	catch(IOException ioe) 
    	{
			logger.error(ioe.getMessage());
    		throw new RuntimeException("Could not read cagwas configuration file");
    	}
    	ZipConfig zipConfig = ZipConfig.getInstance(CAGWAS_ZIP_PROPERTIES);
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
	    	zipItem = new caGWASZipItemImpl();
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
	        zipper.setTarget(getInputZipDirectory()+File.separator+zipItem.getFileName());
	        zipper.setBreakIntoMultipleFileIfLarge(false);
	        zipper.setZipPropertyFilename(CAGWAS_ZIP_PROPERTIES);
	        zipper.run();
		}
	}
	/**
	 * @return Returns the cagwasProperties.
	 */
	public static String getCagwasProperties(String propertyName) {
		return cagwasProperties.getProperty(propertyName);
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
		return CAGWAS_MAIL_PROPERTIES;
	}

	public static String getGenotypeDirectory(){
		return genotypeDirectory;
	}

}