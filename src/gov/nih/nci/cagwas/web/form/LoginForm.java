package gov.nih.nci.cgems.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * The LoginForm class is the form validator for the login form.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.ActionForm
 */
public class LoginForm extends ActionForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = -5047534992128384313L;
	
	// Username is the username they logged in with
	private String username;
	// Password is the value of the password they provided
	private String password;

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
		if ((getUsername() == null) || (getUsername().length() < 1))
				errors.add("username", new ActionMessage("error.username.required"));
		
		if ((getPassword() == null) || (getPassword().length() < 1))
				errors.add("password", new ActionMessage("error.password.required"));
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
