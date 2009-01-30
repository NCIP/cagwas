package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.domain.study.bean.Study;
import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.web.form.BrowseForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/**
 * The BulkDownloadAction class is called when the Bulk Data form posts.
 * It will figure out the state of the form when it was posted and setup
 * the next form appropriately and forward to it.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class BulkDownloadAction extends Action
{
	private static Logger logger = Logger.getLogger(BulkDownloadAction.class);

	
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
		
		// Figure out the state of the form
		BrowseForm bForm = (BrowseForm)form;
		Long studyId = bForm.getStudyId();
		String query = bForm.getQuery();

		logger.debug("Study is " + studyId);
		logger.debug("Query is " + query);
		
		retrieveStudy(request,studyId);
		String studyName = (String) request.getSession().getAttribute("studyName");
		String studyVersion = (String) request.getSession().getAttribute("studyVersion");
		// Then get the appropriate setup elements based on the query type
		if ((query.equals("Association")) || (query.equals("Population")))
		{
			// Set the forward
			forward = null;
			String redirect = ZipFindingsHelper.getOutputZipDirectory() + studyName + "/" + studyVersion + "/" + query;
			request.setAttribute("filePath", redirect);
			request.getSession().setAttribute("filePath", redirect);
			
			if(dirHasFiles(request,redirect,query)){
				forward = mapping.findForward("download");			
				logger.debug("redirecting to download");
			}else{
				logger.debug("NO Subject Data Available!");
				forward = mapping.findForward("notAvialable");
			}
		}
		if ((query.equals("Subjects")) || (query.equals("Genotypes")))
		{
			// Set the forward
			forward = null;
			String redirect = ZipFindingsHelper.getGenotypeDirectory() + studyName + "/" + studyVersion + "/" + query;
			request.setAttribute("filePath", redirect);
			request.getSession().setAttribute("filePath", redirect);
			
			if(dirHasFiles(request,redirect,query)){
				forward = mapping.findForward("download");			
				logger.debug("redirecting to download");
			}else{
				logger.debug("NO Subject Data Available!");
				forward = mapping.findForward("notAvialable");
			}
		}
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	    }
		
		return forward;
	}
	/**
	 * retrieveStudy is called to get the study object from the database
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param studyId The study Id for getting the study object
	 * @throws Exception any Exceptions that occur
	 */
	private void retrieveStudy(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the list of studies from the database
		SearchStudyHelper studyHelp = new SearchStudyHelper();
		Study study = studyHelp.retrieveStudy(studyId);
		
		if(study != null)
		{
			request.getSession().setAttribute("studyName", study.getName());
			request.getSession().setAttribute("studyVersion", study.getVersion());
			request.getSession().setAttribute("study", study.getName()+" Version: "+study.getVersion());
		}
	}
	/**
	 * retrieveStudy is called to get the study object from the database
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param studyId The study Id for getting the study object
	 * @throws Exception any Exceptions that occur
	 */
	private boolean dirHasFiles(HttpServletRequest request, String path, String query) throws Exception
	{
		boolean isFiles = true;
		try {
			java.io.File folder = new java.io.File(path);
			java.io.File[] files = folder.listFiles();
			if(files == null ||(files != null && files.length == 0)){
				isFiles = false;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		if(!isFiles)
		{
			request.getSession().setAttribute("query", query);
			request.getSession().setAttribute("queryType", "bulkdownload");

		}
		return isFiles;
	}
}
