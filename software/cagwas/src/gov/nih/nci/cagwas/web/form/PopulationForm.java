/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.form;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * The PopulationForm class is the form validator for the Search Population
 * Frequency form.
 * <P>
 * @author mholck
 * @see gov.nih.nci.cagwas.web.form.SNPSearchForm
 */
public class PopulationForm extends SNPSearchForm
{
	// serial version ID for serializable
	private static final long serialVersionUID = 4649526826095162477L;
	
	// Population stores the populations selected
	private String[] population;
	// Weinberg value stores the value for the Hardy Weinberg pValue
	private String weinbergValue;
	// Weinberg operator stores the <= or >= operator selected
	private String weinbergOperator;
	// Allele value stores the value for the Minor Allele Frequency
	private String alleleValue;
	// Allele operator stores the <= or >= operator selected
	private String alleleOperator;
	// Completion value stores the value for the Completion Rate
	private String completionValue;
	// Completion operator stores the <= or >= operator selected
	private String completionOperator;

	/**
	 * The validate method is called when the form is submitted and is
	 * responsible for making sure the input is valid.
	 * <P>
	 * @param mapping The ActionMapping for the posted action
	 * @param request The HttpServletRequest for this post
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = super.validate(mapping, request);
		
		// Validate the population has something selected
		if ((getPopulation() == null) || (getPopulation().length == 0))
		{
			errors.add("population", new ActionMessage("error.population.required"));
		}
		
		// Validate that completion rate is between 0 and 100
		if ((getCompletionValue() != null) && (getCompletionValue().length() >= 1))
		{
			// Verify that if it is set it is a float
			try
			{
				float value = Float.parseFloat(getCompletionValue());
				
				// Then check that it is a valid value
				if ((value > 100.0) || (value < 0.0))
					errors.add("completion", new ActionMessage("error.completion.value"));
			}
			catch(NumberFormatException nfe)
			{
				errors.add("completion", new ActionMessage("error.completion.type"));
			}
		}
		
		// Validate that Hardy Weinberg is between 0 and 1
		if ((getWeinbergValue() != null) && (getWeinbergValue().length() >= 1))
		{
			// Verify that if it is set it is a float
			try
			{
				float value = Float.parseFloat(getWeinbergValue());
				
				// Then check that it is a valid value
				if ((value > 1.0) || (value < 0.0))
					errors.add("weinberg", new ActionMessage("error.weinberg.value"));
			}
			catch(NumberFormatException nfe)
			{
				errors.add("weinberg", new ActionMessage("error.weinberg.type"));
			}
		}
		
		// Validate that Minor Allele Frequency is between 0 and 1
		if ((getAlleleValue() != null) && (getAlleleValue().length() >= 1))
		{
			// Verify that if it is set it is a float
			try
			{
				float value = Float.parseFloat(getAlleleValue());
				
				// Then check that it is a valid value
				if ((value > 1.0) || (value < 0.0))
					errors.add("allele", new ActionMessage("error.allele.value"));
			}
			catch(NumberFormatException nfe)
			{
				errors.add("allele", new ActionMessage("error.allele.type"));
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
		population = new String[1];
		population[0] = "All";
	}

	public String[] getPopulation() {
		return population;
	}

	public void setPopulation(String[] population)
	{
		this.population = population;
	}

	public String getAlleleOperator()
	{
		return alleleOperator;
	}

	public void setAlleleOperator(String alleleOperator)
	{
		this.alleleOperator = alleleOperator;
	}

	public String getAlleleValue()
	{
		if (alleleValue != null)
			return alleleValue.trim();
		else
			return alleleValue;
	}

	public void setAlleleValue(String alleleValue)
	{
		this.alleleValue = alleleValue;
	}

	public String getCompletionOperator()
	{
		return completionOperator;
	}

	public void setCompletionOperator(String completionOperator)
	{
		this.completionOperator = completionOperator;
	}

	public String getCompletionValue()
	{
		if (completionValue != null)
			return completionValue.trim();
		else
			return completionValue;
	}

	public void setCompletionValue(String completionValue)
	{
		this.completionValue = completionValue;
	}

	public String getWeinbergOperator()
	{
		return weinbergOperator;
	}

	public void setWeinbergOperator(String weinbergOperator)
	{
		this.weinbergOperator = weinbergOperator;
	}

	public String getWeinbergValue()
	{
		if (weinbergValue != null)
			return weinbergValue.trim();
		else
			return weinbergValue;
	}

	public void setWeinbergValue(String weinbergValue)
	{
		this.weinbergValue = weinbergValue;
	}

	/**
	 * isBlank is used to determine if the form had any criteria set or not
	 * <P>
	 * @return boolean 
	 */
	public boolean isBlank()
	{
		boolean result = true;
		
		// Check our parent first
		result = super.isBlank();
		List pop = Arrays.asList(population);
		if(pop != null  && pop.size()>= 1  && !pop.contains("All")){
			result = false;
		}
		if ((weinbergValue != null) && (weinbergValue.length() > 0))
			result = false;
		if ((alleleValue != null) && (alleleValue.length() > 0))
			result = false;
		if ((completionValue != null) && (completionValue.length() > 0))
			result = false;
		
		return result;
	}
}
