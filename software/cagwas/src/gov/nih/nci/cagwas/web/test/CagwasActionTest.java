/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import gov.nih.nci.caintegrator.application.configuration.ConfigurationListener;
import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisMethod;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationAnalysis;
import gov.nih.nci.caintegrator.domain.study.bean.Population;
import gov.nih.nci.caintegrator.domain.study.bean.Study;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPAssociationAnalysisCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.PopulationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.studyQueryService.germline.FindingsManager;
import gov.nih.nci.cagwas.web.action.BrowseAction;
import gov.nih.nci.cagwas.web.action.SearchStudyHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.struts.util.LabelValueBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;

import servletunit.struts.MockStrutsTestCase;

/**
 * CagwasActionTest is a base class that handles the initial setup for
 * all mock struts test cases. For this to work 1 change needs to be
 * done from the production configuration.
 * 
 * 1. Copy the caintegrator-spec applicationContext-service.xml file
 * over to test alongside our applicationContext-junit.xml file.
 * 
 * @author mholck
 *
 */
public class CagwasActionTest extends MockStrutsTestCase
{
	protected static ArrayList<Long> studyIds;
	protected static Collection<Study> studyObjs;
	protected static ArrayList<LabelValueBean> studyNamesList;
	protected static HashMap<String, List<LabelValueBean>> studyVersionMap;
	protected static ArrayList<String> chromosomes;
	
	protected ArrayList<String> populationNames;

	// Initialize the Spring statically so it is only done once for each test class
	static
	{
        MockServletContext sc = new MockServletContext("");
        sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
        					"/WEB-INF/test/applicationContext*.xml");
        ServletContextListener contextListener = new ConfigurationListener();
        ServletContextEvent event = new ServletContextEvent(sc);
        contextListener.contextInitialized(event);
        
        populateStudies();
        populateChromosomes();
    }
	
	public CagwasActionTest(String testName)
	{
		super(testName);
	}
	
	/**
	 * Uses the caGWAS DTO to retrieve the list of available studies. These are
	 * used in the tests to make sure every study is searched.
	 */
	private static void populateStudies()
	{
		studyIds = new ArrayList<Long>();
		StudyCriteria studyCrit = new StudyCriteria();
        
		try
		{
			FindingsManager manager = (FindingsManager) SpringContext
					.getBean("findingsManager");
			
			SearchStudyHelper studyHelp = new SearchStudyHelper();
			List<String> studyIdList = new ArrayList<String>();
			studyIdList.add("1");
	        Collection<String> studyNames = studyHelp.retrieveStudyNames(studyIdList);
	        
	        ArrayList<LabelValueBean> studyVersionsList = new ArrayList<LabelValueBean>();
        	studyNamesList = new ArrayList<LabelValueBean>();
            
            studyVersionMap = new HashMap<String, List<LabelValueBean>>();
            
	        for(String studyName:studyNames)
	        {	
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
	        
			studyObjs = manager.getStudies(studyCrit);
				
			if ((studyObjs != null) && (studyObjs.size() > 0))
			{
				for (Study study : studyObjs)
				{
					studyIds.add(study.getId());
				}
			}
			else
				assertTrue(false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			assertTrue(false);
		}        
	}
	
	/**
	 * Uses the caGWAS DTO to retrieve the list of available chromosomes. These are
	 * used in the tests to make sure every chromosome can be searched.
	 */
	private static void populateChromosomes()
	{
		chromosomes = new ArrayList<String>();
		
		try
		{
			FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
			Collection<String> chromos = manager.getChromosomes();
			
			if ((chromos != null) && (chromos.size() > 0))
			{
				for (String chromo : chromos)
				{
					chromosomes.add(chromo);
				}
			}
			else
				assertTrue(false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			assertTrue(false);
		}        
	}
	
	/**
	 * setupAssociations will setup everything for an association search just like the
	 * application does.
	 * <P>
	 * @param studyId
	 */
	protected void setupAssociations(Long studyId)
	{
		BrowseAction ba = new BrowseAction();
		
		try
		{
			// Setup the analysis selections
			ba.setupAssociations(request, studyId);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}
	
	/**
	 * Uses the caGWAS DTO to retrieve the list of available populations for a specific
	 * study. These are used in the tests to make sure every population for every
	 * study is searched.
	 * <P>
	 * @param study The study to get the analysis for
	 */
	protected void populatePopulations(Long studyId)
	{
		populationNames = new ArrayList<String>();
		
		try
		{
			PopulationCriteria popCrit = new PopulationCriteria(studyId);
	        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
			Collection<Population> populations = manager.getPopulations(popCrit);
			if ((populations != null) && (populations.size() > 0))
			{
				for (Population pop : populations)
				{
					populationNames.add(pop.getName());
				}
			}
			else
				assertTrue(false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
