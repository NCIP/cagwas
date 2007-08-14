package gov.nih.nci.cgems.web.action;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.domain.study.bean.Study;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cgems.application.zip.ZipFindingsHelper;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

/**
 * The FeedbackSetupAction class is used to get the list of features from the
 * properties file and redirect them to the feedback form.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class FeedbackSetupAction extends Action
{
	private static Logger logger = Logger.getLogger(FeedbackAction.class);
	
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
		ActionMessages errors = new ActionMessages();
		ActionForward forward = null;
		
		// Load the properties files
		Properties mailProperties = new Properties();
		String fileName = System.getProperty(ZipFindingsHelper.getMailPropertiesFilename());
		FileInputStream in = new FileInputStream(fileName);
		mailProperties.load(in);
		
		String featureList = mailProperties.getProperty("feedback.features");
		
		Collection<LabelValueBean> features = new ArrayList<LabelValueBean>();
		
		if (featureList != null)
		{
			// Now break the string into parts using whitespace as the separator
		    String patternStr = ",";
		    String[] featList = featureList.split(patternStr);
		    
		    // Then add them to the features list and pass to the form
			for (int i=0; i < featList.length; i++)
			{
			    String feature = featList[i];
			    features.add(new LabelValueBean(feature, feature));
			}
		}
		else
			logger.error("Feedback feature list is null");
		
		if ((features != null) && (features.size() > 0))
			request.getSession().setAttribute("features", features);
		else
			return mapping.findForward("error");
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	      addErrors(request, errors);
	      forward = new ActionForward(mapping.getInput());
	    }
	    else
	    {
	        forward = mapping.findForward("success");
	    }
		
		return forward;
	}

}
