/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPFrequencyFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.util.ArithematicOperator;
import gov.nih.nci.cagwas.web.form.PopulationForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

public class SearchPopulationHelper
{
	private static Logger logger = Logger.getLogger(SearchPopulationHelper.class);
	private SNPFrequencyFindingCriteriaDTO criteriaDto;
	/**
	 * The buildCriteria method will create and populate the SNPFrequencyFindingCriteriaDTO
	 * needed for a search and will return this.
	 * <P>
	 * @param form The form that includes all the needed values for the annotation criteria
	 * @param errors The ActionMessages used to report errors
	 * @return The completed AnnotationCriteria object
	 */
	public SNPFrequencyFindingCriteriaDTO buildCriteria(PopulationForm popForm, Long studyId,
			ActionMessages errors) throws Exception
	{
		StudyCriteria studyCrit = new StudyCriteria();
		
		// Set the study
		studyCrit.setId(studyId);
		SNPFrequencyFindingCriteriaDTO freqDTO = criteriaDto;
        freqDTO.setStudyCriteria(studyCrit);
		
		// Get the population selection
		String[] populations = popForm.getPopulation();
		
		logger.debug("Populations:");
		for(int i = 0; i < populations.length; i++)
		{
			logger.debug(populations[i]);
		}
		
		// Set the population to the list selected if it was all then do not set anything
		if (!populations[0].equals("All"))
			freqDTO.setPopulationNames(populations);
		else
			logger.debug("Population search for all");
		
		// Now set the hardy weinberg, allele, and completion rate if available
		if ((popForm.getWeinbergValue() != null) && (popForm.getWeinbergValue().length() > 0))
		{
			if (popForm.getWeinbergOperator().equals("<="))
			{
				logger.debug("Hardy Weinberg pValue <=" + popForm.getWeinbergValue());
				freqDTO.setHardyWeinbergPValue(Float.parseFloat(popForm.getWeinbergValue()), ArithematicOperator.LE);
			}
			else if (popForm.getWeinbergOperator().equals(">="))
			{
				logger.debug("Hardy Weinberg pValue >=" + popForm.getWeinbergValue());
				freqDTO.setHardyWeinbergPValue(Float.parseFloat(popForm.getWeinbergValue()), ArithematicOperator.GE);
			}
		}
		
		if ((popForm.getAlleleValue() != null) && (popForm.getAlleleValue().length() > 0))
		{
			if (popForm.getAlleleOperator().equals("<="))
			{
				logger.debug("Minor Allele Frequency <=" + popForm.getAlleleValue());
				freqDTO.setMinorAlleleFrequency(Float.parseFloat(popForm.getAlleleValue()), ArithematicOperator.LE);
			}
			else if (popForm.getAlleleOperator().equals(">="))
			{
				logger.debug("Minor Allele Frequency >=" + popForm.getAlleleValue());
				freqDTO.setMinorAlleleFrequency(Float.parseFloat(popForm.getAlleleValue()), ArithematicOperator.GE);
			}
		}
		
		if ((popForm.getCompletionValue() != null) && (popForm.getCompletionValue().length() > 0))
		{
			double value = Double.parseDouble(popForm.getCompletionValue());
			value /= 100;
			
			if (popForm.getCompletionOperator().equals("<="))
			{
				logger.debug("Completion Rate <=" + popForm.getCompletionValue());
				freqDTO.setCompletionRate(new Double(value), ArithematicOperator.LE);
			}
			else if (popForm.getCompletionOperator().equals(">="))
			{
				logger.debug("Completion Rate >=" + popForm.getCompletionValue());
				freqDTO.setCompletionRate(new Double(value), ArithematicOperator.GE);
			}
		}
		
		return freqDTO;
	}
    public SNPFrequencyFindingCriteriaDTO getCriteriaDto() {
        return criteriaDto;
    }
    public void setCriteriaDto(SNPFrequencyFindingCriteriaDTO criteriaDto) {
        this.criteriaDto = criteriaDto;
    }
}
