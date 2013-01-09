/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * The DownloadForm class is the form validator for the download form.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.ActionForm
 */
public class DownloadForm extends ActionForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = 4360701217770869403L;
	
	// email is the email address the user enters
	private String email;

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
		if ((getEmail() == null) || (getEmail().length() < 1))
			errors.add("email", new ActionMessage("error.email.required"));
		else
		{
			// Validate the email address is somewhat valid
			String email = getEmail();
			StringBuffer sb1 = new StringBuffer("@");
			StringBuffer sb2 = new StringBuffer(".");
			if ((!email.contains(sb1)) || (!email.contains(sb2)))
				errors.add("email", new ActionMessage("error.email.invalid"));
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
		String addr = (String)request.getSession().getAttribute("email");
		this.email = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
