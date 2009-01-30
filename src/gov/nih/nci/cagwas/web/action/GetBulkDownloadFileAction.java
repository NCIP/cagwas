package gov.nih.nci.cagwas.web.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * The AboutSetupAction class is used to get the remote content for the about
 * page and then redirect to it.
 * <P>
 * @author sahnih
 * @see org.apache.struts.action.Action
 */
public class GetBulkDownloadFileAction extends Action
{
	private static Logger logger = Logger.getLogger(GetBulkDownloadFileAction.class);
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
		String dir = (String) request.getSession().getAttribute("filePath");
		String fileName = request.getParameter("file");
		String filePath = dir + "/"+ fileName;
		final ServletOutputStream out = response.getOutputStream(); 
		response.setContentType("application/octet-stream");
		
		response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + "\"" );
		File file = null;
		try {
			file = new File(filePath);
			response.setContentLength( (int)file.length() );
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1) {
				out.write(buf, 0, bytesRead);
			}
			is.close();
			out.close();
		}catch (IOException ioe){
			logger.error("IO exception in sending file " +  file.toString() + ioe.getMessage());
		}

		return  null;
	}

}
