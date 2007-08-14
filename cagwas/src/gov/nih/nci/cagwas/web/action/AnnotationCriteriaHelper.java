package gov.nih.nci.cgems.web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import gov.nih.nci.cgems.web.form.SNPSearchForm;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.PhysicalPositionCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.PanelCriteria;

/**
 * The AnnotationCriteriaHelper class will build the annotation criteria
 * object for a search given a SNPSearchForm.
 * <P>
 * @author mholck
 */
public class AnnotationCriteriaHelper
{
	private static Logger logger = Logger.getLogger(AnnotationCriteriaHelper.class);
	
	/**
	 * The buildCriteria method will create and populate the AnnotationCriteria
	 * needed for a search and will return this.
	 * <P>
	 * @param form The form that includes all the needed values for the annotation criteria
	 * @param errors The ActionMessages used to report errors
	 * @return The completed AnnotationCriteria object
	 */
	public AnnotationCriteria buildCriteria(SNPSearchForm form, ActionMessages errors)
	{
		AnnotationCriteria annoCrit = new AnnotationCriteria();
		
		// Set the panel if any
		String panel = form.getPanel();
		String panelName = form.getPanelName();
		if ((panel != null) && (panel.length() >= 1))
		{
			logger.debug("panel id is " + panel);
			PanelCriteria pc = new PanelCriteria();
			pc.setSnpPanelID(new Long(panel));
			
			if ((panelName != null) && (panelName.length() >= 1))
			{
				logger.debug("panel name is " + panelName);
				pc.setName(panelName);
			}
			
			annoCrit.setPanelCriteria(pc);
		}
		
		// Setup the physical position criteria if any
		String chromosome = form.getChromosome();
		if ((chromosome != null) && (chromosome.length() >= 1))
		{
			logger.debug("chromosome is " + chromosome);
			// If there was a chromosome set then check the from and to criteria as well
			String chromFrom = form.getChromosomeFrom();
			String chromTo = form.getChromosomeTo();
			PhysicalPositionCriteria ppCrit = new PhysicalPositionCriteria();
			ppCrit.setChromosome(chromosome);
			
			if ((chromFrom != null) && (chromFrom.length() >= 1))
			{
				// Handle the from value based on units selected
				if (form.getFromUnit().equals("bp"))
				{
					logger.debug("from value " + chromFrom);
					ppCrit.setStartPosition(Long.parseLong(chromFrom));
				}
				else if (form.getFromUnit().equals("kb"))
				{
					float value = Float.parseFloat(chromFrom);
					value *= 1000;
					logger.debug("from value " + value);
					ppCrit.setStartPosition(new Long((int)value));
				}
				else if (form.getFromUnit().equals("mb"))
				{
					float value = Float.parseFloat(chromFrom);
					value *= 1000000;
					logger.debug("from value " + value);
					ppCrit.setStartPosition(new Long((int)value));
				}
			}
			
			if ((chromTo != null) && (chromTo.length() >= 1))
			{
				// Handle the from value based on units selected
				if (form.getToUnit().equals("bp"))
				{
					logger.debug("to value " + chromTo);
					ppCrit.setEndPosition(Long.parseLong(chromTo));
				}
				else if (form.getToUnit().equals("kb"))
				{
					float value = Float.parseFloat(chromTo);
					value *= 1000;
					logger.debug("to value " + value);
					ppCrit.setEndPosition(new Long((int)value));
				}
				else if (form.getToUnit().equals("mb"))
				{
					float value = Float.parseFloat(chromTo);
					value *= 1000000;
					logger.debug("to value " + value);
					ppCrit.setEndPosition(new Long((int)value));
				}
			}
			
			annoCrit.setPhysicalPositionCriteria(ppCrit);
		}
		
		// Setup the HUGO gene symbols if any
		if ((form.getHugoFile() != null) && (form.getHugoFile().getFileName().length() >= 1))
		{
			logger.debug("HUGO Symbol File is " + form.getHugoFile().getFileName());
			if ((form.getHugoFile().getFileName().endsWith(".txt") || form.getHugoFile().getFileName().endsWith(".TXT"))
					&& (form.getHugoFile().getContentType().equals("text/plain")))
			{
				Collection<String> geneSymbols = new ArrayList<String>();
				
				try
				{
                    InputStream stream = form.getHugoFile().getInputStream();
                    String inputLine = null;
                    BufferedReader inFile = new BufferedReader(
                            new InputStreamReader(stream));

                    int count = 0;
                    
                    while ((inputLine = inFile.readLine()) != null && count < CgemsConstants.MAX_SYMBOLS)
                    {
                    	inputLine = inputLine.trim();
                        count++;
                        logger.debug(inputLine.toUpperCase());
                        geneSymbols.add(inputLine.toUpperCase());
                    }

                    inFile.close();
                    annoCrit.setGeneSymbols(geneSymbols);
                }
				catch (IOException ex)
				{
                    logger.error("Errors when uploading hugo file:" + ex.getMessage());
                    errors.add("hugoFile", new ActionMessage("error.file.access"));
                }
			}
			else
			{
				errors.add("hugoFile", new ActionMessage("error.hugofile.type"));
			}
		}
		else
		{
			Collection<String> geneSymbols = new ArrayList<String>();
			String genes = form.getHugoList();
			
			// Replace any commas with spaces so the split for whitespace will work
			String cleanGenes = genes.replace(',', ' ');
			
			// Now break the string into parts using whitespace as the separator
		    String patternStr = "\\s";
		    String[] geneList = cleanGenes.split(patternStr);
		    
		    // Handle the case where the split says we have one record but it is empty
		    if ((geneList.length >= 1) && (geneList[0].length() >= 1))
		    {
			    // Then add them to the symbol list and pass to the search
			    logger.debug("Gene list has " + geneList.length + " values :");
			    for (int i=0; i < geneList.length; i++)
			    {
			    	String gene = geneList[i].trim();
			    	logger.debug(gene.toUpperCase());
			    	geneSymbols.add(gene.toUpperCase());
			    }
				
				annoCrit.setGeneSymbols(geneSymbols);
		    }
		}
		
		// Setup the dbSNP identifiers if any
		if ((form.getIdFile() != null) && (form.getIdFile().getFileName().length() >= 1))
		{
			logger.debug("dbSNP ID File is " + form.getIdFile().getFileName());
			if ((form.getIdFile().getFileName().endsWith(".txt") || form.getIdFile().getFileName().endsWith(".TXT"))
					&& (form.getIdFile().getContentType().equals("text/plain")))
			{
				Collection<String> dbSNPIds = new ArrayList<String>();
				
				try
				{
                    InputStream stream = form.getIdFile().getInputStream();
                    String inputLine = null;
                    BufferedReader inFile = new BufferedReader(
                            new InputStreamReader(stream));

                    int count = 0;
                    
                    while ((inputLine = inFile.readLine()) != null && count < CgemsConstants.MAX_IDS)
                    {
                    	inputLine = inputLine.trim();
                        count++;
                        logger.debug(inputLine.toLowerCase());
                        dbSNPIds.add(inputLine.toLowerCase());
                    }

                    inFile.close();
                    annoCrit.setSnpIdentifiers(dbSNPIds);
                }
				catch (IOException ex)
				{
                    logger.error("Errors when uploading id file:" + ex.getMessage());
                    errors.add("idFile", new ActionMessage("error.file.access"));
                }
			}
			else
			{
				errors.add("idFile", new ActionMessage("error.idfile.type"));
			}
		}
		else
		{
			Collection<String> dbSNPIds = new ArrayList<String>();
			String ids = form.getIdList();
			
			// Replace any commas with spaces so the split for whitespace will work
			String cleanIDs = ids.replace(',', ' ');
			
			// Now break the string into parts using newline as the separator
		    String patternStr = "\\s";
		    String[] idList = cleanIDs.split(patternStr);
		    
		    // Handle the case where the split says we have one record but it is empty
		    if ((idList.length >= 1) && (idList[0].length() >= 1))
		    {
			    // Then add them to the symbol list and pass to the search
			    logger.debug("dbSNP ID list has " + idList.length + " values :");
			    for (int i=0; i < idList.length; i++)
			    {
			    	String id = idList[i].trim();
			    	logger.debug(id.toLowerCase());
			    	dbSNPIds.add(id.toLowerCase());
			    }
				
			    annoCrit.setSnpIdentifiers(dbSNPIds);
		    }
		}
		
		return annoCrit;
	}
}
