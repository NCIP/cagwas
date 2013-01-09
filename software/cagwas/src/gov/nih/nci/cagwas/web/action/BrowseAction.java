/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisGroup;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisMethod;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationAnalysis;
import gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPPanel;
import gov.nih.nci.caintegrator.domain.study.bean.FrequencyPopulation;
import gov.nih.nci.caintegrator.domain.study.bean.Population;
import gov.nih.nci.caintegrator.domain.study.bean.Study;
import gov.nih.nci.caintegrator.domain.study.bean.SubjectPopulation;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.AnalysisGroupCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPAssociationAnalysisCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.PopulationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.web.form.BrowseForm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
 * The BrowseAction class is called when the Browse Data form posts.
 * It will figure out the state of the form when it was posted and setup
 * the next form appropriately and forward to it.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class BrowseAction extends Action
{
	private static Logger logger = Logger.getLogger(BrowseAction.class);
	
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
		BrowseForm bForm = (BrowseForm)form;
		Long studyId = bForm.getStudyId();
		String query = bForm.getQuery();
		String queryType = "browse";
		logger.debug("Study is " + studyId);
		logger.debug("Query is " + query);
		
		// Put the study in the session
		request.getSession().setAttribute("studyId", studyId);
		logger.debug("Study is " + studyId);
		
		// Get study
		retrieveStudy(request,studyId);
		
		// Then get the appropriate setup elements based on the query type
		String logged = (String)request.getSession().getAttribute("logged");
		List studyIDs = (List)request.getSession().getAttribute("studyIDList");

		if (query.equals("Association"))
		{
			setupAssociations(request, studyId);
			
			// Set the forward 
			forward = mapping.findForward("association");
		}
		else if (query.equals("Population"))
		{
			// Get the population list from the database
			//retrievePopulations(request, studyId);
			retrieveFrequencyPopulations(request, studyId);
			// Get the chromosome list
			retrieveChromosomes(request);
			
			// Get the panel list
			retrievePanels(request, studyId);
			
			// Set the forward 
			forward = mapping.findForward("population");
		}
		else if (query.equals("Subjects"))
		{
			if ((logged != null  && (logged.equals("yes"))  &&
				(studyIDs != null && studyIDs.contains(studyId.toString()))))
			{
				if(getStudyParticipantCount(studyId)> 0){
					// Get the population list from the database
					retrieveSubjectPopulations(request, studyId);
					
					// Get the analysis groups from the database
					retrieveAnalysisGroups(request, studyId);
					
					// Get the age bands from the database
					retrieveAgeBands(request, studyId);
					
					// Get the case controls list
					retrieveCaseControls(request, studyId);
					
					// Set the forward
					forward = mapping.findForward("subjects");
				}else{
					//No subject data available
					request.getSession().setAttribute("query", query);
					request.getSession().setAttribute("queryType", queryType);
					logger.debug("NO Subject Data Available!");
					forward = mapping.findForward("notAvialable");
					
				}
			}
			else if(logged == null  || !logged.equals("yes"))
			{
				request.getSession().setAttribute("ref", mapping.findForward("subjects").getPath().toString());
				forward = mapping.findForward("login");
			}
			else{ //you do not have access to this study
				logger.debug("YOU DO NOT HAVE ACCESS TO THIS STUDY, PLEASE APPLY FOR REGISTERED ACCESS!");
				forward = mapping.findForward("accessWarning");
			}
		}
		else if (query.equals("Genotypes"))
		{
			
			if ((logged != null  && (logged.equals("yes"))  &&
					(studyIDs != null && studyIDs.contains(studyId.toString()))))
			{
				setupGenotype(request);
				
				// Set the forward
				forward = mapping.findForward("genotypes");
			}
			else if(logged == null  || !logged.equals("yes"))
			{
				request.getSession().setAttribute("ref", mapping.findForward("subjects").getPath().toString());
				forward = mapping.findForward("login");
			}
			else{ //you do not have access to this study
				logger.debug("YOU DO NOT HAVE ACCESS TO THIS STUDY, PLEASE APPLY FOR REGISTERED ACCESS!");
				forward = mapping.findForward("accessWarning");
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
	 * Contains all the calls needed to setup the session for an association search. This
	 * method is public so that the unit tests can also call it during their setup.
	 * <P>
	 * @param request The request to populate with the values
	 * @param studyId The Study selected
	 * @throws Exception
	 */
	public void setupAssociations(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the analysis from the database
		retrieveAnalysis(request, studyId);
		
		// Get the chromosome list
		retrieveChromosomes(request);
		
		// Get the panel list
		retrievePanels(request, studyId);
	}
	
	/**
	 * wraps the preprocessing methods for external access
	 * @param request
	 * @throws Exception
	 */
	protected void setupGenotype(HttpServletRequest request) throws Exception
	{
		Long studyId = (Long)request.getSession().getAttribute("studyId");
		retrieveStudy(request,studyId);
		retrievePopulations(request, studyId);		
		retrieveAnalysisGroups(request, studyId);
		retrieveChromosomes(request);
		retrievePanels(request, studyId);
		retrieveAgeBands(request, studyId);
		retrieveCaseControls(request, studyId);
	}
	
	/**
	 * retrieveChromosomes is called to get the chromosomes from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @throws Exception any Exceptions that occur
	 */
	private void retrieveChromosomes(HttpServletRequest request) throws Exception
	{
		// Get the list of chromosomes from the database
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<String> chromosomes = manager.getChromosomes();
		ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();
		Iterator<String> iterator = chromosomes.iterator();
			
		while(iterator.hasNext())
		{
			String value = iterator.next();
			list.add(new LabelValueBean(value, value));
		}
			
		request.getSession().setAttribute("chromosomes", list);
	}
	
	/**
	 * retrievePopulations is called to get the populations from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param study The study to get the populations for
	 * @throws Exception any Exceptions that occur
	 */
	private void retrievePopulations(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the populations from the database
		PopulationCriteria popCrit = new PopulationCriteria(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<Population> population = manager.getPopulations(popCrit);
		request.getSession().setAttribute("populationCol", population);
	}
	/**
	 * retrieveFrequencyPopulations is called to get the Frequency Populations from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param study The study to get the populations for
	 * @throws Exception any Exceptions that occur
	 */
	private void retrieveFrequencyPopulations(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the populations from the database
		PopulationCriteria popCrit = new PopulationCriteria(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<FrequencyPopulation> frequencyPopulation = manager.getFrequencyPopulations(popCrit);
		request.getSession().setAttribute("populationCol", frequencyPopulation);
	}
	/**
	 * retrieveSubjectPopulations is called to get the Subject Populations from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param study The study to get the populations for
	 * @throws Exception any Exceptions that occur
	 */
	private void retrieveSubjectPopulations(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the populations from the database
		PopulationCriteria popCrit = new PopulationCriteria(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<SubjectPopulation> subjectPopulation = manager.getSubjectPopulations(popCrit);
		request.getSession().setAttribute("populationCol", subjectPopulation);
	}
	/**
	 * getStudyParticipantCount is called to get the count of study participants from the database
	 * for use to check if .
	 * <P>
	 * @param study The study to get the study participant count for
	 * @return count Study participant count
	 * @throws Exception any Exceptions that occur
	 */
	private Long getStudyParticipantCount(Long studyId) throws Exception
	{
		// Get the study participant count from the database
		StudyCriteria studyCrit = new StudyCriteria();
        studyCrit.setId(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		return manager.getStudyParticipantCount(studyCrit);		
	}
	
	/**
	 * removeCasePopulation is called to remove the case population from the drop
	 * down list.  This is a temporary patch just for subject searches that will
	 * eventually be removed when the data is provided for this population.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @throws Exception any Exceptions that occur
	 */
	@SuppressWarnings("unchecked")
	private void removeCasePopulation(HttpServletRequest request) throws Exception
	{
		Collection<Population> populationList =
			(Collection<Population>)request.getSession().getAttribute("populationCol");
		
		if (populationList != null)
		{
			Iterator iterator = populationList.iterator();
			Population pop = null;
			
			while(iterator.hasNext())
            {
				pop = (Population) iterator.next();
				if (pop.getName().equalsIgnoreCase("CASE"))
            	   break;
				else
					pop = null;
            }
			
			if (pop != null)
				populationList.remove(pop);
			
			request.getSession().setAttribute("populationCol", populationList);
		}
	}
	
	/**
	 * retrieveAnalysisGroups is called to get the analysis groups from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param study The study name for getting the Analysis
	 * @throws Exception any Exceptions that occur
	 */
	private void retrieveAnalysis(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the analysis from the database
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
        
        // Create the study criteria object so we only get the analysis for the selected study
        StudyCriteria studyCrit = new StudyCriteria();
        studyCrit.setId(studyId);
       
        // First get the list of available analysis methods
        Collection<String> methodTypes = manager.getAnalysisMethodTypes(studyCrit);
        
        request.getSession().setAttribute("methodTypeNum", new Integer(methodTypes.size()));
        		
        for (String type : methodTypes)
        {
        	Long order = new Long(0);
        	Double typeOrder = null;
        	boolean flag=false;
        	Map map = new TreeMap();
        	Map mapsorted = new TreeMap();
        	ArrayList<LabelValueBean> methodList = new ArrayList<LabelValueBean>();
        	       	
        	// Then get the methods for each type
        	logger.debug("Method type is " + type);
        	Collection<SNPAnalysisMethod> methods = manager.getSNPAnalysisMethods(studyCrit, type);
        	ArrayList<SNPAnalysisMethod> sortedMethods = new ArrayList<SNPAnalysisMethod>();
        	for (SNPAnalysisMethod method : methods)
        	{
        		logger.debug("Method name is " + method.getMethodName());
        		logger.debug("Method code is " + method.getRepresensitiveCode());
        		logger.debug("Method type order is " + method.getMethodTypeOrder());
           		order = method.getDisplayOrder();
           		typeOrder = method.getMethodTypeOrder();
           		if (method.getMethodName() == null)
        		{
        			sortedMethods.add(0, method);
        			methodList.add(0, new LabelValueBean("", ""));
        		}
        		else
        		{
        			
        			//to sort & display in the typeOrder 
        			if(typeOrder!= null)
        			{
        				map.put(typeOrder.intValue(), new LabelValueBean(method.getMethodName(), method.getRepresensitiveCode()));
        				mapsorted.put(typeOrder.intValue(), method);
        				flag=true;       				
        			}
        			else
        			{   sortedMethods.add(method);
        				methodList.add(new LabelValueBean(method.getMethodName(), method.getRepresensitiveCode()));
        			}
        		}
        	}
        	    	
        	if(flag)
        		{
        		getSortedMethodList(methodList,map);
        		getSortedMethods(sortedMethods,mapsorted);
        		}
        	
        	 // Get the first selection description to set on load
        	SNPAnalysisMethod method = sortedMethods.get(0);
        	request.getSession().setAttribute("methodDesc" + order.toString(), method.getMethodDescription());	
        	      	
        	// Store the type, and list of selections and descriptions in the session
        	request.getSession().setAttribute("methodType" + order.toString(), type);
        	request.getSession().setAttribute("methodList" + order.toString(), methodList);
        	request.getSession().setAttribute("methods" + order.toString(), sortedMethods);
        	
        }
        
        // Then get the analysis description document from the old objects
        SNPAssociationAnalysisCriteria assocCrit = new SNPAssociationAnalysisCriteria(studyId);

        Collection<SNPAssociationAnalysis> assocObjs = manager.getSNPAssociationAnalysis(assocCrit);

        String assocFileName = null;

        // All associated analysis for a particular study Id will have the pdf file.
        for(SNPAssociationAnalysis assocObj: assocObjs )
        {
            assocFileName = assocObj.getDescription();
            break;
        }

        if(assocFileName != null)
        {
            request.getSession().setAttribute("assocFileName", assocFileName );
        }

	}
	
	/**
	 * retrieveAnalysisGroups is called to get the analysis groups from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param study The study id for getting the analysis groups
	 * @throws Exception any Exceptions that occur
	 */
	private void retrieveAnalysisGroups(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the analysis groups from the database
		AnalysisGroupCriteria agCrit = new AnalysisGroupCriteria(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<SNPAnalysisGroup> agObjs = manager.getSNPAnalysisGroups(agCrit);
		request.getSession().setAttribute("analysisGroups", agObjs);
	}
	
	/**
	 * retrieveAgeBands is called to get the age bands from the database
	 * for use in the forms as drop down lists.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @throws Exception any Exceptions that occur
	 */
	@SuppressWarnings("unchecked")
	private void retrieveAgeBands(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the lower ages from the database
		StudyCriteria studyCrit = new StudyCriteria();
		studyCrit.setId(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<Integer> lower = manager.getAgeLowerLimitValues(studyCrit);
		ArrayList<LabelValueBean> lowerList = new ArrayList<LabelValueBean>();
		Iterator<Integer> iterator = lower.iterator();
			
		while(iterator.hasNext())
		{
			Integer iValue = iterator.next();
			if (iValue != null)
			{
				String value = iValue.toString();
				lowerList.add(new LabelValueBean(value, value));
			}
		}
			
		Collections.sort(lowerList);
		
		request.getSession().setAttribute("lowerAges", lowerList);
		
		// Get the upper ages from the database
		Collection<Integer> upper = manager.getAgeUpperLimitValues(studyCrit);
		ArrayList<LabelValueBean> upperList = new ArrayList<LabelValueBean>();
		iterator = upper.iterator();
			
		while(iterator.hasNext())
		{
			Integer iValue = iterator.next();
			if (iValue != null)
			{
				String value = iValue.toString();
				upperList.add(new LabelValueBean(value, value));
			}
		}
			
		Collections.sort(upperList);
		
		request.getSession().setAttribute("upperAges", upperList);
	}
	
	/**
	 * retrieveCaseControls is called to get the case control list from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @throws Exception any Exceptions that occur
	 */
	@SuppressWarnings("unchecked")
	private void retrieveCaseControls(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the case controls from the database
		StudyCriteria sc = new StudyCriteria();
		sc.setId(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<String> controlList = manager.getCaseControlStatus(sc);
		ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();
		Iterator<String> iterator = controlList.iterator();
			
		while(iterator.hasNext())
		{
			String value = iterator.next();
			if (value != null)
				list.add(new LabelValueBean(value, value));
		}
			
		Collections.sort(list);
		
		request.getSession().setAttribute("caseControls", list);
	}
	
	/**
	 * retrievePanels is called to get the panels from the database
	 * for use in the forms as a drop down list.
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param study The study name for getting the panels
	 * @throws Exception any Exceptions that occur
	 */
	private void retrievePanels(HttpServletRequest request, Long studyId) throws Exception
	{
		// Get the list of panels from the database
		StudyCriteria sc = new StudyCriteria();
		sc.setId(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<SNPPanel> panels = manager.getPanels(sc);
		ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();
		Iterator<SNPPanel> iterator = panels.iterator();
			
		while(iterator.hasNext())
		{
			SNPPanel panel = iterator.next();
			String label = panel.getName();
			String value = panel.getId().toString();
			list.add(new LabelValueBean(label, value));
		}
			
		request.getSession().setAttribute("panels", list);
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
	 * Get the sorted Method List - ordered by TypeOrder
	 * @param methodList The sorted methodList
	 * @param map The map of methodList inserted ordered by TypeOrder
	 */
	private void getSortedMethodList(ArrayList methodList,Map map )
	{
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Integer obj = (Integer)it.next();
	    	methodList.add(map.get(obj.intValue()));
	    }
	}
    
	/**
	 * Get the sorted Methods- ordered by TypeOrder
	 * @param sortedMethods The sorted methods
	 * @param mapsorted The map of methods inserted ordered by TypeOrder
	 */
	private void getSortedMethods(ArrayList sortedMethods,Map mapsorted )
	{
		Iterator it = mapsorted.keySet().iterator();
		while (it.hasNext()) {
			Integer obj = (Integer)it.next();
			sortedMethods.add(mapsorted.get(obj.intValue()));
	    }
	}
	
}
