/**
 * 
 */
package gov.nih.nci.cgems.reports;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import gov.nih.nci.caintegrator.domain.study.bean.Population;
import gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant;

/**
 * @author sahnih
 * The purpose of this object is to help format the report for display
 */
public class StudyParticipantReport {
	private StudyParticipant studyParticipant = null;
	private String nonBlankWhiteSpace = " "; //default
	/**
	 * @param space
	 * @param participant
	 */
	public StudyParticipantReport(String space, StudyParticipant participant) {
		nonBlankWhiteSpace = space;
		studyParticipant = participant;
	}
	/**
	 * @param participant
	 */
	public StudyParticipantReport(StudyParticipant participant) {
		studyParticipant = participant;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant#getFamilyHistory()
	 */
	public String getFamilyHistory() {
		return (studyParticipant != null && studyParticipant.getFamilyHistory()!= null? studyParticipant.getFamilyHistory(): nonBlankWhiteSpace);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant#getStudySubjectIdentifier()
	 */
	public String getStudySubjectIdentifier() {
		return (studyParticipant != null && studyParticipant.getStudySubjectIdentifier() != null ? studyParticipant.getStudySubjectIdentifier():nonBlankWhiteSpace);
	}



	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant#getAgeAtEnrollment()
	 */
	public String getAgeAtEnrollment()
	{
		String ages = nonBlankWhiteSpace;
		
		// Convert the age bands to int values before display
		if (studyParticipant != null && 
				studyParticipant.getAgeAtEnrollment() != null &&
				studyParticipant.getAgeAtEnrollment().getMinValue() != null &&
				studyParticipant.getAgeAtEnrollment().getMaxValue()!= null)
		{
			Double min = studyParticipant.getAgeAtEnrollment().getMinValue();
			Double max = studyParticipant.getAgeAtEnrollment().getMaxValue();
			ages = min.intValue() + "-" + max.intValue();
		}
		
		return ages;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant#getEthnicGroupCode()
	 */
	public String getEthnicGroupCode() {
		return studyParticipant.getEthnicGroupCode();
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant#getCaseControlStatus()
	 */
	public String getCaseControlStatus()
	{
		return (studyParticipant != null && studyParticipant.getCaseControlStatus() != null ? studyParticipant.getCaseControlStatus():nonBlankWhiteSpace);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant#getAdministrativeGenderCode()
	 */
	public String getAdministrativeGenderCode()
	{
		return (studyParticipant != null && studyParticipant.getAdministrativeGenderCode() != null ? studyParticipant.getAdministrativeGenderCode():nonBlankWhiteSpace);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant#getPopulation()#getName()
	 */
	public Set<String> getPopulationNameCollection() {
		Set<String> populationNames = new HashSet<String>();
		if(studyParticipant != null && studyParticipant.getPopulationCollection()!= null ){
			for(Population population:studyParticipant.getPopulationCollection()){
				populationNames.add(population.getName());
			}
		}
		return populationNames;
	}
	/**
	 * @return Returns the nonBlankWhiteSpace.
	 */
	public String getNonBlankWhiteSpace() {
		return nonBlankWhiteSpace;
	}
	/**
	 * @param nonBlankWhiteSpace The nonBlankWhiteSpace to set.
	 */
	public void setNonBlankWhiteSpace(String nonBlankWhiteSpace) {
		this.nonBlankWhiteSpace = nonBlankWhiteSpace;
	}
}
