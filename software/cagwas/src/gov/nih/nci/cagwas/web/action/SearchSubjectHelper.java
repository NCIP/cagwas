/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import java.util.ArrayList;
import java.util.Collection;

import gov.nih.nci.caintegrator.studyQueryService.dto.germline.AnalysisGroupCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.PopulationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyParticipantCriteria;
import gov.nih.nci.cagwas.web.form.SubjectForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

public class SearchSubjectHelper
{
	private static Logger logger = Logger.getLogger(SearchSubjectHelper.class);
	
	/**
	 * The buildCriteria method will create and populate the StudyParticipantCriteria
	 * needed for a search and will return this.
	 * <P>
	 * @param form The form that includes all the needed values for the annotation criteria
	 * @param errors The ActionMessages used to report errors
	 * @return The completed AnnotationCriteria object
	 */
	public StudyParticipantCriteria buildCriteria(SubjectForm sForm, Long studyId, ActionMessages errors)
	{
		StudyParticipantCriteria spCrit = new StudyParticipantCriteria();
		StudyCriteria studyCrit = new StudyCriteria();
		
		// Set the study name
		studyCrit.setId(studyId);
		spCrit.setStudyCriteria(studyCrit);
		
		// Handle the population or analysis group if applicable
		if ((sForm.getQueryType() != null) && (sForm.getQueryType().length() > 1))
		{
			// Handle the population
			if (sForm.getQueryType().equals("population"))
			{
				if ((sForm.getPopulation() != null) && (sForm.getPopulation().length() > 1))
				{
					logger.debug("population is " + sForm.getPopulation());
					Collection<String> names = new ArrayList<String>();
			        names.add(sForm.getPopulation());
			        PopulationCriteria popCrit = new PopulationCriteria(names);
			        spCrit.setPopulationCriteria(popCrit);
				}
			}
			
			// Handle the analysis group
			if (sForm.getQueryType().equals("analysisGroup"))
			{
				if ((sForm.getAnalysisGroup() != null) && (sForm.getAnalysisGroup().length() > 1))
				{
					logger.debug("analysis group is " + sForm.getAnalysisGroup());
					AnalysisGroupCriteria crit = new AnalysisGroupCriteria(studyId);
					String[] names = new String[1];
			        names[0] = sForm.getAnalysisGroup();
			        crit.setNames(names);
			        spCrit.setAnalysisGroupCriteria(crit);
				}
			}
		}
		
		// Handle the study criteria if provided
		if ((sForm.getGender() != null) && (sForm.getGender().length() > 1))
		{
			logger.debug("gender is " + sForm.getGender());
			Collection<String> ca = new ArrayList<String>();
	        ca.add(sForm.getGender());
	        spCrit.setAdministrativeGenderCodeCollection(ca);
		}
		
		if ((sForm.getLowerAge() != null) && (sForm.getLowerAge().length() > 1))
		{
			logger.debug("lower age is " + sForm.getLowerAge());
			int value = (int)Double.parseDouble(sForm.getLowerAge());
			spCrit.setLowerAgeLimit(new Integer(value));
		}
		
		if ((sForm.getUpperAge() != null) && (sForm.getUpperAge().length() > 1))
		{
			logger.debug("upper age is " + sForm.getUpperAge());
			int value = (int)Double.parseDouble(sForm.getUpperAge());
			spCrit.setUpperAgeLimit(new Integer(value));
		}
		
		if ((sForm.getCaseControl() != null) && (sForm.getCaseControl().length() > 1))
		{
			logger.debug("case control is " + sForm.getCaseControl());
			spCrit.setCaseControlStatus(sForm.getCaseControl());
		}
		
		if ((sForm.getFamilyHistory() != null) && (sForm.getFamilyHistory().length() > 1))
		{
			logger.debug("family history is " + sForm.getFamilyHistory());
			Collection<String> cf = new ArrayList<String>();
	        cf.add(sForm.getFamilyHistory());
	        spCrit.setFamilyHistoryCollection(cf);
		}
		
		return spCrit;
	}
}
