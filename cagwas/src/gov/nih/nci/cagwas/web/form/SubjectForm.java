package gov.nih.nci.cagwas.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * The SubjectForm class is the form validator for the Search Subject
 * form.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.ActionForm
 */
public class SubjectForm extends ActionForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = 6839549089442162961L;
	
	// Population is for the selected population if any
	private String population;
	// AnalysisGroup is for the selected analysis group if any
	private String analysisGroup;
	// QueryType is the value from the radio buttons
	private String queryType;
	// Gender is for the gender value selected from the drop down
	private String gender;
	// LowerAge is for the selection of the lower age band from the drop down
	private String lowerAge;
	// UpperAge is for the selection of the upper age band from the drop down
	private String upperAge;
	// CaseControl is for the selection of the case control status from the drop down
	private String caseControl;
	// FamilyHistory is for the selection of family history from the drop down
	private String familyHistory;
	
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

	public String getAnalysisGroup()
	{
		return analysisGroup;
	}

	public void setAnalysisGroup(String analysisGroup)
	{
		this.analysisGroup = analysisGroup;
	}

	public String getPopulation()
	{
		return population;
	}

	public void setPopulation(String population)
	{
		this.population = population;
	}

	public String getQueryType() 
	{
		return queryType;
	}

	public void setQueryType(String queryType)
	{
		this.queryType = queryType;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getCaseControl()
	{
		return caseControl;
	}

	public void setCaseControl(String caseControl)
	{
		this.caseControl = caseControl;
	}

	public String getFamilyHistory()
	{
		return familyHistory;
	}

	public void setFamilyHistory(String familyHistory)
	{
		this.familyHistory = familyHistory;
	}

	public String getLowerAge()
	{
		return lowerAge;
	}

	public void setLowerAge(String lowerAge)
	{
		this.lowerAge = lowerAge;
	}

	public String getUpperAge()
	{
		return upperAge;
	}

	public void setUpperAge(String upperAge)
	{
		this.upperAge = upperAge;
	}

	/**
	 * isBlank is used to determine if the form had any criteria set or not
	 * <P>
	 * @return boolean 
	 */
	public boolean isBlank()
	{
		boolean result = true;
		
		if ((analysisGroup != null) && (analysisGroup.length() > 0))
			result = false;
		if ((queryType != null) && (queryType.length() > 0))
			result = false;
		if ((gender != null) && (gender.length() > 0))
			result = false;
		if ((lowerAge != null) && (lowerAge.length() > 0))
			result = false;
		if ((upperAge != null) && (upperAge.length() > 0))
			result = false;
		if ((caseControl != null) && (caseControl.length() > 0))
			result = false;
		if ((familyHistory != null) && (familyHistory.length() > 0))
			result = false;
		
		return result;
	}
}
