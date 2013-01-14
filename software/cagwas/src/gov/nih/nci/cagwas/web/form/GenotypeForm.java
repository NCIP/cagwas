/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * The GenotypeForm class is the form validator for the Search Genotype
 * form.
 * <P>
 * @author mholck
 * @see gov.nih.nci.cagwas.web.form.SNPSearchForm
 */
public class GenotypeForm extends SNPSearchForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = -1540556620512946157L;
	
	// Population is for the selected population if any
	private String population;
	// AnalysisGroup is for the selected analysis group if any
	private String analysisGroup;
	// QueryType is the value from the radio buttons
	private String queryType;
	// qcStatus is the selection from the Status QC drop down
	private String qcStatus;
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
		
		// If they select a radio button for population or anaylsis group they need a selection
		if (getQueryType() != null)
		{
			if ((getQueryType().equals("population")) &&
					((getPopulation() == null) || getPopulation().length() < 1))
				errors.add("population", new ActionMessage("error.population.required"));
			if ((getQueryType().equals("analysisGroup")) &&
					((getAnalysisGroup() == null) || getAnalysisGroup().length() < 1))
				errors.add("analysisGroup", new ActionMessage("error.analysisGroup.required"));
		}
		
		// Make sure that they provided some annotation criteria values
		if ((getChromosome() == null || getChromosome().length() < 1) &&
				(getChromosomeFrom() == null || getChromosomeFrom().length() < 1) &&
				(getChromosomeTo() == null || getChromosomeTo().length() < 1) &&
				(getHugoList() == null || getHugoList().length() < 1) &&
				(getIdList() == null || getIdList().length() < 1) &&
				(getHugoFile() == null || getHugoFile().getFileName() == null || getHugoFile().getFileName().length() < 1) &&
				(getIdFile() == null || getIdFile().getFileName() == null || getIdFile().getFileName().length() < 1))
		{
			errors.add("annotation", new ActionMessage("error.annotation.required"));
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

	public String getAnalysisGroup() {
		return analysisGroup;
	}

	public void setAnalysisGroup(String analysisGroup) {
		this.analysisGroup = analysisGroup;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(String qcStatus) {
		this.qcStatus = qcStatus;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCaseControl() {
		return caseControl;
	}

	public void setCaseControl(String caseControl) {
		this.caseControl = caseControl;
	}

	public String getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getLowerAge() {
		return lowerAge;
	}

	public void setLowerAge(String lowerAge) {
		this.lowerAge = lowerAge;
	}

	public String getUpperAge() {
		return upperAge;
	}

	public void setUpperAge(String upperAge) {
		this.upperAge = upperAge;
	}

}
