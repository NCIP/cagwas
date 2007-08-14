package gov.nih.nci.cgems.web.action;

import java.util.ArrayList;
import java.util.Collection;

import gov.nih.nci.caintegrator.studyQueryService.dto.germline.AnalysisGroupCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.GenotypeFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.PopulationCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyParticipantCriteria;
import gov.nih.nci.cgems.web.form.GenotypeForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

public class SearchGenotypeHelper
{
	private static Logger logger = Logger.getLogger(SearchGenotypeHelper.class);
	private GenotypeFindingCriteriaDTO criteriaDto;
    
	/**
	 * The buildCriteria method will create and populate the GenotypeFindingCriteriaDTO
	 * needed for a search and will return this.
	 * <P>
	 * @param form The form that includes all the needed values for the annotation criteria
	 * @param errors The ActionMessages used to report errors
	 * @return The completed AnnotationCriteria object
	 */
	public GenotypeFindingCriteriaDTO buildCriteria(GenotypeForm genoForm, Long studyId,
			ActionMessages errors) throws Exception
	{
		StudyParticipantCriteria spCrit = new StudyParticipantCriteria();
		StudyCriteria studyCrit = new StudyCriteria();
		
		// Set the study
		studyCrit.setId(studyId);
		GenotypeFindingCriteriaDTO gfDTO = criteriaDto;
        gfDTO.setStudyCriteria(studyCrit);
		
		// Handle the population or analysis group if applicable
		if ((genoForm.getQueryType() != null) && (genoForm.getQueryType().length() > 1))
		{
			// Handle the population
			if (genoForm.getQueryType().equals("population"))
			{
				if ((genoForm.getPopulation() != null) && (genoForm.getPopulation().length() > 1))
				{
					logger.debug("population is " + genoForm.getPopulation());
					Collection<String> names = new ArrayList<String>();
			        names.add(genoForm.getPopulation());
			        PopulationCriteria popCrit = new PopulationCriteria(names);
			        spCrit.setPopulationCriteria(popCrit);
				}
			}
			
			// Handle the analysis group
			if (genoForm.getQueryType().equals("analysisGroup"))
			{
				if ((genoForm.getAnalysisGroup() != null) && (genoForm.getAnalysisGroup().length() > 1))
				{
					logger.debug("analysis group is " + genoForm.getAnalysisGroup());
					AnalysisGroupCriteria crit = new AnalysisGroupCriteria(studyId);
					String[] names = new String[1];
			        names[0] = genoForm.getAnalysisGroup();
			        crit.setNames(names);
			        spCrit.setAnalysisGroupCriteria(crit);
				}
			}
		}
		
		// Handle the study criteria if provided
		if ((genoForm.getGender() != null) && (genoForm.getGender().length() > 1))
		{
			logger.debug("gender is " + genoForm.getGender());
			Collection<String> ca = new ArrayList<String>();
	        ca.add(genoForm.getGender());
	        spCrit.setAdministrativeGenderCodeCollection(ca);
		}
		
		if ((genoForm.getLowerAge() != null) && (genoForm.getLowerAge().length() > 1))
		{
			logger.debug("lower age is " + genoForm.getLowerAge());
			spCrit.setLowerAgeLimit(new Integer(genoForm.getLowerAge()));
		}
		
		if ((genoForm.getUpperAge() != null) && (genoForm.getUpperAge().length() > 1))
		{
			logger.debug("upper age is " + genoForm.getUpperAge());
			spCrit.setUpperAgeLimit(new Integer(genoForm.getUpperAge()));
		}
		
		if ((genoForm.getCaseControl() != null) && (genoForm.getCaseControl().length() > 1))
		{
			logger.debug("case control is " + genoForm.getCaseControl());
			spCrit.setCaseControlStatus(genoForm.getCaseControl());
		}
		
		if ((genoForm.getFamilyHistory() != null) && (genoForm.getFamilyHistory().length() > 1))
		{
			logger.debug("family history is " + genoForm.getFamilyHistory());
			Collection<String> cf = new ArrayList<String>();
	        cf.add(genoForm.getFamilyHistory());
	        spCrit.setFamilyHistoryCollection(cf);
		}
		
		// Handle the status for QC
		if ((genoForm.getQcStatus() != null) && (genoForm.getQcStatus().length() > 1))
		{
			logger.debug("status QC is " + genoForm.getQcStatus());
			gfDTO.setQcStatus(genoForm.getQcStatus());
		}
		
		gfDTO.setStudyParticipantCriteria(spCrit);
		
		return gfDTO;
	}

    public GenotypeFindingCriteriaDTO getCriteriaDto() {
        return criteriaDto;
    }

    public void setCriteriaDto(GenotypeFindingCriteriaDTO criteriaDto) {
        this.criteriaDto = criteriaDto;
    }
}
