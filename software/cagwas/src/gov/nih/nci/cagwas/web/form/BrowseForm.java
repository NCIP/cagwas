package gov.nih.nci.cagwas.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * The BrowseForm class is the form validator for the Browse Data form.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.ActionForm
 */
public class BrowseForm extends ActionForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = -8620338418796014946L;
	
	// Study is the study selected from the drop down list on the form
	private Long studyId;
	// Query is the type of search they selected
	private String query;
	// Name of the selected study
	private String studyName;
	
	private String studyVersion;

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
		
		// Validate that they have selected a study and a query type
		if ((getStudyId() == null) )
				errors.add("studyId", new ActionMessage("error.study.required"));
		
		if ((getQuery() == null) || (getQuery().length() < 1))
				errors.add("query", new ActionMessage("error.query.required"));
		
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
		this.query = "Association";
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	/**
	 * @return Returns the studyId.
	 */
	public Long getStudyId() {
		return studyId;
	}

	/**
	 * @param studyId The studyId to set.
	 */
	public void setStudyId(Long studyId) {
		this.studyId = studyId;
	}

	/**
	 * @return Returns the studyName.
	 */
	public String getStudyName() {
		return studyName;
	}

	/**
	 * @param studyName The studyName to set.
	 */
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getStudyVersion() {
		return studyVersion;
	}

	public void setStudyVersion(String studyVersion) {
		this.studyVersion = studyVersion;
	}

}
