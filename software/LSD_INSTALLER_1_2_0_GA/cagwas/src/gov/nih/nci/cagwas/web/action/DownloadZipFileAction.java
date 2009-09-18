package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.caintegrator.application.mail.MailConfig;
import gov.nih.nci.caintegrator.application.mail.MailManager;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * The DownloadZipFileAction class is used to get the zip file 
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class DownloadZipFileAction extends Action
{
	private static Logger logger = Logger.getLogger(DownloadZipFileAction.class);
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

		ActionForward forward = null;
		String zipFileName = (String)request.getParameter("zipFile");
		if(zipFileName == null){
			zipFileName = (String) request.getSession().getAttribute("zipFile");
		}
		boolean fileExists = doesFileExists(zipFileName);
		// Check if the user is logged in
		String logged = (String)request.getSession().getAttribute("logged");
		//if already logged in  and file exists than download file
		request.getSession().setAttribute("zipFile", zipFileName);
		if ((logged != null  && (logged.equals("yes"))))
			{
				if(fileExists){
					// Set the forward
					request.getSession().setAttribute("filePath", ZipFindingsHelper.getInputZipDirectory());
					String fileRetentionPeriodInDays = MailConfig.getInstance(ZipFindingsHelper.getMailPropertiesFilename()).getFileRetentionPeriodInDays();
					request.getSession().setAttribute("fileRetentionPeriodInDays", fileRetentionPeriodInDays);

					forward = mapping.findForward("downloadZipFile");			
					logger.debug("redirecting to download");	
				} else{ //file probably got deleted after 5 days
					logger.debug("File does not exsist");
					request.getSession().removeAttribute("filePath");
					forward = mapping.findForward("downloadZipFile");
				}
			}
		else if((logged == null  || !logged.equals("yes")))
		{
			// Set the forward
			logger.debug("redirecting to login");		
			forward = mapping.findForward("login");	
		}
		else{ //you do not have access to this study
			logger.debug("YOU DO NOT HAVE ACCESS TO THIS STUDY, PLEASE APPLY FOR REGISTERED ACCESS!");
			logger.debug("File does not exsist");
			request.getSession().removeAttribute("filePath");
			forward = mapping.findForward("accessWarning");
		}

		return forward;
	}
	private boolean doesFileExists(String zipFileName) {
		if(zipFileName != null){
			String filePath = ZipFindingsHelper.getInputZipDirectory()+File.separator+zipFileName;
			File file = null;
				file = new File(filePath);
				if (file.exists()){
					return true;
				}
		}
		return false;
	}

}
