/**
 *  
 */
package gov.nih.nci.cagwas.application.zip;

import gov.nih.nci.caintegrator.application.zip.ZipItem;

/**
 * @author sahnih
 *
 */
public class caGWASZipItemImpl implements ZipItem
{
	private String filePath;
	private String fileName;
	private String directoryInZip;
	
	/**
	 * @return Returns the directoryInZip.
	 */
	public String getDirectoryInZip()
	{
		return directoryInZip;
	}
	
	/**
	 * @param directoryInZip The directoryInZip to set.
	 */
	public void setDirectoryInZip(String directoryInZip)
	{
		this.directoryInZip = directoryInZip;
	}
	
	/**
	 * @return Returns the fileName.
	 */
	public String getFileName()
	{
		return fileName;
	}
	
	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * @return Returns the filePath.
	 */
	public String getFilePath() 
	{
		return filePath;
	}
	
	/**
	 * @param filePath The filePath to set.
	 */
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
}
