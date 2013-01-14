/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.domain.study.bean.Study;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

/**
 * The BrowseSetupAction class is used to get the available studies and populate a
 * collection and put it in the session so the browseData page can use it to
 * display the drop down selection of studies.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class BrowseSetupAction extends Action
{
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
		
		// Now get the studies available from the data access layer
		StudyCriteria studyCrit = new StudyCriteria();
        
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
        
        ArrayList<LabelValueBean> studyNamesList = new ArrayList<LabelValueBean>();
       
        HashMap<String, List<LabelValueBean>> studyVersionMap = new HashMap<String, List<LabelValueBean>>();
        
        SearchStudyHelper studyHelp = new SearchStudyHelper();
        Collection<String> studyNames = studyHelp.retrieveStudyNames();
        
        for(String studyName:studyNames)
        {
        	ArrayList<LabelValueBean> studyVersionsList = new ArrayList<LabelValueBean>();
        	
        	studyNamesList.add(new LabelValueBean(studyName,studyName));
        	Collection<Study> studies = studyHelp.retrieveStudies(studyName);
        	
        	for(Study s : studies)
        	{
        		studyVersionsList.add(new LabelValueBean(s.getVersion(), String.valueOf(s.getId())));
        	}
        	
        	Collections.sort(studyVersionsList);
        	Collections.reverse(studyVersionsList);
        	studyVersionMap.put(studyName, studyVersionsList);
        }

        if(studyNamesList != null && studyNamesList.size()>0)
        {
        	request.getSession().setAttribute("studyNames", studyNamesList);
        	request.getSession().setAttribute("studyVersionsMap", studyVersionMap);
        }
        else
        {
        	return mapping.findForward("error");
        }
        
		Collection<Study> studyObjs = manager.getStudies(studyCrit);
		
		if ((studyObjs != null) && (studyObjs.size() > 0))
			request.getSession().setAttribute("studies", studyObjs);
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
