package gov.nih.nci.cagwas.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 * The SNPSearchForm class is a base class that has all the elements
 * needed for the SNP annotation criteria that will be common to
 * multiple forms.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.ActionForm
 */
public class SNPSearchForm extends ActionForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = -2423028677878603041L;
	
	// Chromosome is for the selected chromosome from the drop down list
	private String chromosome;
	// ChromosomeFrom is for the from value for chromosome
	private String chromosomeFrom;
	// FromUnit is for the units of the chromosomeFrom value
	private String fromUnit;
	// ChromosomeTo is for the to value for chromosome
	private String chromosomeTo;
	// ToUnit is for the units of the chromosomeTo value
	private String toUnit;
	// HugoList is for the list of HUGO gene symbols
	private String hugoList;
	// IdList is for the list of dbSNP identifiers
	private String idList;
	// hugoFile is the file upload for the HUGO gene list
	private FormFile hugoFile;
	// idFile is the file upload for the dbSNP Identifiers list
	private FormFile idFile;
	// panel is for the panel selection
	private String panel;
	// panelName is the text name for the selected panel
	private String panelName;

	/**
	 * The validate method is called when the form is submitted and is
	 * responsible for making sure the input is valid.
	 * <P>
	 * @param mapping The ActionMapping for the posted action
	 * @param request The HttpServletRequest for this post
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		
		// If they selected a chromosome then make sure a from and to was provided
		/*
		if ((getChromosome() != null) && (getChromosome().length() >= 1))
		{
			if ((getChromosomeFrom() == null) || (getChromosomeFrom().length() < 1))
				errors.add("chromosomeFrom", new ActionMessage("error.from.required"));
			if ((getChromosomeTo() == null) || (getChromosomeTo().length() < 1))
				errors.add("chromosomeTo", new ActionMessage("error.to.required"));
		}*/
		
		//If the chromosomeFrom and chromosomeTo values are entered, make sure chromosome is selected
		if(((getChromosomeFrom() != null) && (getChromosomeFrom().length() >= 1))&& 
					((getChromosomeTo() != null) && (getChromosomeTo().length() >= 1)))
		{
				if ((getChromosome() == null) ||(getChromosome().length() < 1))
				{	
				  errors.add("chromosome", new ActionMessage("error.chromosome.required"));
				}  
		}
		
		
		// If they provided a chromosome from value then check the units and type
		if ((getChromosomeFrom() != null) && (getChromosomeFrom().length() >= 1))
		{
			if (getFromUnit().equals("bp"))
			{
				// bp units should be integer
				try
				{
					Long.parseLong(getChromosomeFrom());
				}
				catch(NumberFormatException nfe)
				{
					errors.add("chromosomeFrom", new ActionMessage("error.from.unitType.bp"));
				}
			}
			else
			{
				// otherwise it should be a float
				try
				{
					Float.parseFloat(getChromosomeFrom());
				}
				catch(NumberFormatException nfe)
				{
					errors.add("chromosomeFrom", new ActionMessage("error.from.unitType"));
				}
			}
		}
		
		// If they provided a chromosome to value then check the units and type
		if ((getChromosomeTo() != null) && (getChromosomeTo().length() >= 1))
		{
			if (getToUnit().equals("bp"))
			{
				// bp units should be integer
				try
				{
					Long.parseLong(getChromosomeTo());
				}
				catch(NumberFormatException nfe)
				{
					errors.add("chromosomeTo", new ActionMessage("error.to.unitType.bp"));
				}
			}
			else
			{
				// otherwise it should be a float
				try
				{
					Float.parseFloat(getChromosomeTo());
				}
				catch(NumberFormatException nfe)
				{
					errors.add("chromosomeTo", new ActionMessage("error.to.unitType"));
				}
			}
		}
		
		
		
		return errors;
	}
	
	/**
	 * The reset method is called on the loading of this form and anytime the
	 * user selects the reset button.  It can be used to set the default starting
	 * values for the form.
	 * <P>
	 * @param mapping The ActionMapping for the posted action
	 * @param request The HttpServletRequest for this post
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
	}

	public String getChromosome()
	{
		return chromosome;
	}

	public void setChromosome(String chromosome)
	{
		this.chromosome = chromosome;
	}

	public String getChromosomeFrom()
	{
		if (chromosomeFrom != null)
			return chromosomeFrom.trim();
		else
			return chromosomeFrom;
	}

	public void setChromosomeFrom(String chromosomeFrom)
	{
		this.chromosomeFrom = chromosomeFrom;
	}

	public String getFromUnit()
	{
		return fromUnit;
	}

	public void setFromUnit(String fromUnit)
	{
		this.fromUnit = fromUnit;
	}

	public String getChromosomeTo()
	{
		if (chromosomeTo != null)
			return chromosomeTo.trim();
		else
			return chromosomeTo;
	}

	public void setChromosomeTo(String chromosomeTo)
	{
		this.chromosomeTo = chromosomeTo;
	}

	public String getToUnit()
	{
		return toUnit;
	}

	public void setToUnit(String toUnit)
	{
		this.toUnit = toUnit;
	}

	public String getHugoList()
	{
		if (hugoList != null)
			return hugoList.trim();
		else
			return hugoList;
	}

	public void setHugoList(String hugoList)
	{
		this.hugoList = hugoList;
	}

	public String getIdList()
	{
		if (idList != null)
			return idList.trim();
		else
			return idList;
	}

	public void setIdList(String idList)
	{
		this.idList = idList;
	}

	public FormFile getHugoFile()
	{
		return hugoFile;
	}

	public void setHugoFile(FormFile hugoFile)
	{
		this.hugoFile = hugoFile;
	}

	public FormFile getIdFile()
	{
		return idFile;
	}

	public void setIdFile(FormFile idFile)
	{
		this.idFile = idFile;
	}

	public String getPanel()
	{
		return panel;
	}

	public void setPanel(String panel)
	{
		this.panel = panel;
	}

	public String getPanelName()
	{
		return panelName;
	}

	public void setPanelName(String panelName)
	{
		this.panelName = panelName;
	}

	/**
	 * isBlank is used to determine if the form had any criteria set or not
	 * <P>
	 * @return boolean 
	 */
	public boolean isBlank()
	{
		boolean result = true;
		
		if ((chromosome != null) && (chromosome.length() > 0))
			result = false;
		if ((chromosomeFrom != null) && (chromosomeFrom.length() > 0))
			result = false;
		if ((chromosomeTo != null) && (chromosomeTo.length() > 0))
			result = false;
		if ((hugoList != null) && (hugoList.length() > 0))
			result = false;
		if ((idList != null) && (idList.length() > 0))
			result = false;
		if ((hugoFile != null) && (hugoFile.getFileSize() > 0))
			result = false;
		if ((idFile != null) && (idFile.getFileSize() > 0))
			result = false;
		if ((panel != null) && (panel.length() > 0))
			result = false;
		if ((panelName != null) && (panelName.length() > 0))
			result = false;
		
		return result;
	}
}
