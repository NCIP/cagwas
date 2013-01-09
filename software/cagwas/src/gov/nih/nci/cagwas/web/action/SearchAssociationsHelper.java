/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import java.util.ArrayList;
import java.util.Collection;

import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPAssociationAnalysisCriteria;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPAssociationFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.util.ArithematicOperator;
import gov.nih.nci.cagwas.web.form.AssociationsForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

public class SearchAssociationsHelper
{
	private static Logger logger = Logger.getLogger(SearchAssociationsHelper.class);
	private SNPAssociationFindingCriteriaDTO criteriaDto;
	
	/**
	 * The buildCriteria method will create and populate the SNPAssociationFindingCriteriaDTO
	 * needed for a search and will return this.
	 * <P>
	 * @param form The form that includes all the needed values for the annotation criteria
	 * @param errors The ActionMessages used to report errors
	 * @return The completed AnnotationCriteria object
	 * @throws Exception
	 */
	public SNPAssociationFindingCriteriaDTO buildCriteria(AssociationsForm assocForm, Long studyId,
			ActionMessages errors) throws Exception
	{
		StudyCriteria studyCrit = new StudyCriteria();
		
		// Set the study
		studyCrit.setId(studyId);
		SNPAssociationFindingCriteriaDTO safDTO = criteriaDto;
        safDTO.setStudyCriteria(studyCrit);
		
		// Setup the analysis criteria if any
        int numMethods = Integer.parseInt(assocForm.getNumAnalysisMethods());
        if(numMethods > 0){
    		Collection<SNPAssociationAnalysisCriteria> analysisCrits = new ArrayList<SNPAssociationAnalysisCriteria>();
    		SNPAssociationAnalysisCriteria saaCrit = new SNPAssociationAnalysisCriteria(studyId);
	        for (int i = 1; i <= numMethods; i++)
	        {
	        	ArrayList<String> codes = assocForm.getAnalysisMethodCodes(i);
	        	if (codes != null)
	        	{
	        		for (String code: codes)
	        		{
	        			logger.debug("Display order " + i + " has code " + code);
	        			saaCrit.addAnalysisMethodTypes(new Long(i),code);
	        		}
	        	}
	        }
			analysisCrits.add(saaCrit);
			safDTO.setSnpAssociationAnalysisCriteriaCollection(analysisCrits);
        }
        
		// Setup the pvalue or rank criteria depending on what was selected
		if ((assocForm.getAssociation() != null) && (assocForm.getAssociation().equals("pvalue")))
		{
			String value = assocForm.getPvalue();
			
			if ((value != null) && (value.length() > 0))
			{
				logger.debug("p-value <= " + value);
				safDTO.setpValue(Float.parseFloat(value), ArithematicOperator.LE);
			}
		}
		
		if ((assocForm.getAssociation() != null) && (assocForm.getAssociation().equals("rank")))
		{
			String rank = assocForm.getRank();
			
			if ((rank != null) && (rank.length() > 0))
			{
				logger.debug("rank <= " + rank);
				safDTO.setRank(Integer.parseInt(rank), ArithematicOperator.LE);
			}
		}
		
		return safDTO;
	}
    public SNPAssociationFindingCriteriaDTO getCriteriaDto() {
        return criteriaDto;
    }
    public void setCriteriaDto(SNPAssociationFindingCriteriaDTO criteriaDto) {
        this.criteriaDto = criteriaDto;
    }
}
