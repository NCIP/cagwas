package gov.nih.nci.cagwas.test.integration;

import gov.nih.nci.caintegrator.studyQueryService.dto.germline.SNPFrequencyFindingCriteriaDTO;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.ChromosomeLookupTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.GenotypeFindingTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.PopulationSearchTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.QCStatusTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.SNPAnalysisMethodLookupTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.SNPAssociationAnalysisCriteriaTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.SNPAssociationAnalysisGroupTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.SNPAssociationFindingsTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.SNPFrequencyFindingTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.SnpAnnotationTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.StudyCriteriaTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.StudyPanelTest;
import gov.nih.nci.caintegrator.studyQueryService.test.germline.SubjectSearchTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class CagwasIntegrationTestSuite {

    /**
     * Assembles a test suite containing all known tests.
     * As new TestCases are defined they can be easily added 
     * to this class
     * @return A non-null test suite.
     */

    public static Test suite() {

      TestSuite suite = new TestSuite();
      
      suite.addTestSuite(SNPAnalysisMethodLookupTest.class);
      suite.addTestSuite(StudyPanelTest.class);
      suite.addTestSuite(ChromosomeLookupTest.class);
      suite.addTestSuite(GenotypeFindingTest.class);
      suite.addTestSuite(QCStatusTest.class);
      suite.addTestSuite(SnpAnnotationTest.class);
      suite.addTestSuite(SNPFrequencyFindingTest.class);
      suite.addTestSuite(StudyPanelTest.class);
      suite.addTestSuite(StudyPanelTest.class);
      suite.addTestSuite(PopulationSearchTest.class);
      suite.addTestSuite(SNPAssociationFindingsTest.class);
      suite.addTestSuite(SNPAssociationAnalysisCriteriaTest.class);
      suite.addTestSuite(SNPAssociationAnalysisGroupTest.class);
      suite.addTestSuite(SNPFrequencyFindingCriteriaDTO.class);
      suite.addTestSuite(StudyCriteriaTest.class);
      suite.addTestSuite(SubjectSearchTest.class);


      return suite;
    }

    /**
     * Runs the test suite.
     */
    public static void main(String args[]) {
      junit.textui.TestRunner.run(suite());
    }
  }
