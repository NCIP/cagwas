package gov.nih.nci.cgems.web.action;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.application.mail.MailManager;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisGroup;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisMethod;
import gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPPanel;
import gov.nih.nci.caintegrator.domain.study.bean.Population;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.AnalysisGroupCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.PopulationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cgems.application.zip.ZipFindingsHelper;
import gov.nih.nci.cgems.web.form.BrowseForm;
import gov.nih.nci.cgems.web.form.FeedbackForm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

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
 * The FeedbackAction class is called when the Feedback form posts.
 * It will generate an email with the feedback information.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class FeedbackAction extends Action
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
        
		// Figure out the state of the form
		FeedbackForm fForm = (FeedbackForm)form;
		String like = fForm.getLikeFeature();
		String dislike = fForm.getDislikeFeature();
		String comment = fForm.getComment();
		
		// Now send the feedback email with this information
		final MailManager mailManager = new MailManager(ZipFindingsHelper.getMailPropertiesFilename());
		
		//mailManager.sendFeedbackMail(comment, like, dislike);
		
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
