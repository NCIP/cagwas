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

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

/**
 * @author sahnih
 *
 */
public class SearchStudyHelper
{
	private static Logger logger = Logger.getLogger(SearchStudyHelper.class);
	
	/**
	 * retrieveStudy is called to get the study object from the database
	 * <P>
	 * @param request The HttpServletRequest for this post
	 * @param studyId The study Id for getting the study object
	 * @throws Exception any Exceptions that occur
	 */
	public Study retrieveStudy(Long studyId) throws Exception
	{
		Study study = null;
		
		// Get the list of studies from the database
		StudyCriteria sc = new StudyCriteria();
		sc.setId(studyId);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<Study> studies = manager.getStudies(sc);
		
		if(studies != null && studies.size() == 1)
		{
			Iterator<Study> iterator = studies.iterator();
			study = iterator.next();
		}
		
		return study;
	}
	
	/**
	 * retrieveStudyNames is called to get the set of study names from the database
	 * <P>
	 * @throws Exception any Exceptions that occur
	 */
	public Set<String> retrieveStudyNames() throws Exception
	{
		Set<String> studyNames = new TreeSet<String>();
		Study study = null;
		
		// Get the list of studies from the database
		StudyCriteria sc = new StudyCriteria();
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<Study> studies = manager.getStudies(sc);
		Iterator<Study> iterator = studies.iterator();
		
		while(iterator.hasNext())
		{		
			study = iterator.next();
			if(study != null && study.getName()!= null)
			{
				studyNames.add(study.getName());
			}
		}
		
		return studyNames;
	}
	
	/**
	 * retrieveStudies is called to get the studys object based on Study name from the database
	 * <P>
	 * @param studyName The study name for getting the study objects
	 * @return
	 * @throws Exception any Exceptions that occur
	 */
	public Collection<Study> retrieveStudies(String studyName) throws Exception
	{
		// Get the list of studies from the database
		StudyCriteria sc = new StudyCriteria();
		
		sc.setName(studyName);
        FindingsManager manager = (FindingsManager)SpringContext.getBean("findingsManager");
		Collection<Study> studies = manager.getStudies(sc);		
		
		return studies;
	}
}
