/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.application.mail.MailManager;
import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.web.bean.Applicant;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.log4j.Logger;

/**
 * The RegistrationAction class is called when the user submits a user
 * request form via the PDF document SF 424.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class RegistrationAction extends Action
{
	private static Logger logger = Logger.getLogger(RegistrationAction.class);
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ActionMessages errors = new ActionMessages();
		ActionForward forward = null;
		
		logger.debug("User Registration action called");
		
		// Get the applicant details from the request
		Applicant appl = getApplicant(request);
		
		// Put the applicant bean in the request so the information can be displayed to the user
		request.setAttribute("applicant", appl);
		
		// Now send the request email with this information
		final MailManager mailManager = new MailManager(ZipFindingsHelper.getMailPropertiesFilename());
		
		mailManager.sendUserRequestMail(appl.toString());
		
		if (errors.isEmpty())
	    {
			forward = mapping.findForward("success");
	    }
		else
		{
			forward = mapping.findForward("error");
		}
		
		return forward;
	}

	/**
	 * getApplicant parses the parameters from the request object posted to get all
	 * the relevant details needed for a user request
	 * <P>
	 * @param request The HttpServletRequest that contains the parameters
	 * @return Applicant
	 */
	public Applicant getApplicant(HttpServletRequest request)
	{
		Applicant appl = new Applicant();
		
		Enumeration params = request.getParameterNames();
		
		logger.debug("Parameters");
		
		while(params.hasMoreElements())
		{
			String paramName = (String)params.nextElement();
			String paramValue = (String)request.getParameter(paramName);
			
			logger.debug(paramName + " = " + paramValue);
			
			// Get the applicant information and populate the applicant bean
			if (paramName.contains("applicant"))
			{
				if (paramName.contains("legal_name"))
					appl.setLegalName(paramValue);
				if (paramName.contains("department"))
					appl.setDepartment(paramValue);
				if (paramName.contains("division"))
					appl.setDivision(paramValue);
				if (paramName.contains("street1"))
					appl.setStreet1(paramValue);
				if (paramName.contains("street2"))
					appl.setStreet2(paramValue);
				if (paramName.contains("city"))
					appl.setCity(paramValue);
				if (paramName.contains("state"))
					appl.setState(paramValue);
				if (paramName.contains("zip_code"))
					appl.setZipcode(paramValue);
				if (paramName.contains("country"))
					appl.setCountry(paramValue);
			}
			
			// Get a few more related fields for applicant
			if (paramName.contains("type_of_application"))
			{
				if (paramValue.equals("1"))
					appl.setApplicationType("New");
				if (paramValue.equals("2"))
					appl.setApplicationType("Resubmission");
				if (paramValue.equals("3"))
					appl.setApplicationType("Renewal");
				if (paramValue.equals("4"))
					appl.setApplicationType("Continuation");
				if (paramValue.equals("5"))
					appl.setApplicationType("Revision");
			}
			if (paramName.contains("descriptive_title"))
			{
				appl.setProjectName(paramValue);
			}
			
			// Get the PI information and populate the applicant bean
			if (paramName.contains("pi"))
			{	
				if (paramName.contains("prefix"))
					appl.setPiPrefix(paramValue);
				if (paramName.contains("first_name"))
					appl.setPiFirstName(paramValue);
				if (paramName.contains("middle_name"))
					appl.setPiMiddleName(paramValue);
				if (paramName.contains("last_name"))
					appl.setPiLastName(paramValue);
				if (paramName.contains("suffix"))
					appl.setPiSuffix(paramValue);
				if (paramName.contains("position"))
					appl.setPiPosition(paramValue);
				if (paramName.contains("organization"))
					appl.setPiOrganization(paramValue);
				if (paramName.contains("department"))
					appl.setPiDepartment(paramValue);
				if (paramName.contains("division"))
					appl.setPiDivision(paramValue);
				if (paramName.contains("street1"))
					appl.setPiStreet1(paramValue);
				if (paramName.contains("street2"))
					appl.setPiStreet2(paramValue);
				if (paramName.contains("city"))
					appl.setPiCity(paramValue);
				if (paramName.contains("state"))
					appl.setPiState(paramValue);
				if (paramName.contains("zip_code"))
					appl.setPiZipcode(paramValue);
				if (paramName.contains("country"))
					appl.setPiCountry(paramValue);
				if (paramName.contains("phone"))
					appl.setPiPhone(paramValue);
				if (paramName.contains("fax"))
					appl.setPiFax(paramValue);
				if (paramName.contains("email"))
					appl.setPiEmail(paramValue);
			}
			
			// Get the authorized rep information and populate the applicant bean
			if (paramName.contains("aor"))
			{
				if (paramName.contains("prefix"))
					appl.setAorPrefix(paramValue);
				if (paramName.contains("first_name"))
					appl.setAorFirstName(paramValue);
				if (paramName.contains("middle_name"))
					appl.setAorMiddleName(paramValue);
				if (paramName.contains("last_name"))
					appl.setAorLastName(paramValue);
				if (paramName.contains("suffix"))
					appl.setAorSuffix(paramValue);
				if (paramName.contains("position"))
					appl.setAorPosition(paramValue);
				if (paramName.contains("organization"))
					appl.setAorOrganization(paramValue);
				if (paramName.contains("department"))
					appl.setAorDepartment(paramValue);
				if (paramName.contains("division"))
					appl.setAorDivision(paramValue);
				if (paramName.contains("street1"))
					appl.setAorStreet1(paramValue);
				if (paramName.contains("street2"))
					appl.setAorStreet2(paramValue);
				if (paramName.contains("city"))
					appl.setAorCity(paramValue);
				if (paramName.contains("state"))
					appl.setAorState(paramValue);
				if (paramName.contains("zip_code"))
					appl.setAorZipcode(paramValue);
				if (paramName.contains("country"))
					appl.setAorCountry(paramValue);
				if (paramName.contains("phone"))
					appl.setAorPhone(paramValue);
				if (paramName.contains("fax"))
					appl.setAorFax(paramValue);
				if (paramName.contains("email"))
					appl.setAorEmail(paramValue);
			}
			
			// Get the statement of intent information and populate the applicant bean
			if (paramName.contains("statement_of_intent"))
			{
				appl.setStatementOfIntent(paramValue);
			}
			
			// Get the data use certification information and populate the applicant bean
			if (paramName.contains("duc"))
			{
				if (paramName.contains("name"))
					appl.setDucName(paramValue);
				if (paramName.contains("date"))
					appl.setDucDate(paramValue);
				if (paramName.contains("title"))
					appl.setDucTitle(paramValue);
			}
		}
		
		return appl;
	}
}
