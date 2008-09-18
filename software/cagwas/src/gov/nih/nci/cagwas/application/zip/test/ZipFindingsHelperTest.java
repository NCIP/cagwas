package gov.nih.nci.cagwas.application.zip.test;

import gov.nih.nci.caintegrator.application.mail.MailManager;
import gov.nih.nci.caintegrator.application.zip.ZipItem;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPAssociationFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPFrequencyFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.dto.study.StudyCriteria;
import gov.nih.nci.caintegrator.util.ArithematicOperator;
import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.application.zip.ZipSNPAssociationFinding;
import gov.nih.nci.cagwas.application.zip.ZipSNPFrequencyFinding;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;

public class ZipFindingsHelperTest extends TestCase {

	/*
	 * Test method for 'gov.nih.nci.cagwas.application.zip.ZipFindingsHelper.zipSNPAssociationFindingCriteriaDTO(FindingCriteriaDTO)'
	 */
	public void testZipSNPAssociationFindingCriteriaDTO()
	{
		String email="sahnih@mail.nih.gov";
		String study="caGWAS Prostate Cancer WGAS Phase 1A";
		Long studyId = new Long(1);
		
		StudyCriteria studyCrit = new StudyCriteria();
		
		// Set the study name
		try
		{
			studyCrit.setId(studyId);
			SNPAssociationFindingCriteriaDTO assocCrit = new SNPAssociationFindingCriteriaDTO(studyCrit);
			assocCrit.setpValue(new Float(0.4), ArithematicOperator.LE);
			final MailManager mailManager = new MailManager(ZipFindingsHelper.getMailPropertiesFilename());

			String targetFileName = ZipSNPAssociationFinding.zipSNPAssociationFindingCriteriaDTO( email, study, study, assocCrit);
			ZipItem zipItem = ZipFindingsHelper.fileToZip(targetFileName);
			ZipFindingsHelper.zipFile(zipItem);
			
			//Verify Zip File Exsists before sending email
			File newZipFile = new File(ZipFindingsHelper.getOutputZipDirectory()+File.separator+zipItem.getFileName());
            if(newZipFile.exists()  && newZipFile.isFile() && newZipFile.length() > 0)
            {
            	ArrayList<String> fileNameList = new ArrayList<String>();
                fileNameList.add(zipItem.getFileName());
   			 	mailManager.sendFTPMail("sahnih@mail.nih.gov",fileNameList, null);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Test method for 'gov.nih.nci.cagwas.application.zip.ZipFindingsHelper.zipGenotypeFindingCriteriaDTO(FindingCriteriaDTO)'
	 */
	public void testZipGenotypeFindingCriteriaDTO() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'gov.nih.nci.cagwas.application.zip.ZipFindingsHelper.zipSNPFrequencyFindingCriteriaDTO(FindingCriteriaDTO)'
	 */
	public void testZipSNPFrequencyFindingCriteriaDTO()
	{
		String study="caGWAS Prostate Cancer WGAS Phase 1A";
		StudyCriteria studyCrit = new StudyCriteria();
		
		try
		{
			// Set the study name
			studyCrit.setName(study);
			SNPFrequencyFindingCriteriaDTO crit = new SNPFrequencyFindingCriteriaDTO(studyCrit);
			crit.setMinorAlleleFrequency(new Float(0.7), ArithematicOperator.GE);
			crit.setPopulationNames(new String[] {"CASE_EARLY"});
			ZipSNPFrequencyFinding.zipSNPFrequencyFindingCriteriaDTO("sahnih@mail.nih.gov", crit);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
