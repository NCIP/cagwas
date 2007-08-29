package gov.nih.nci.cagwas.test.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class caGWASUIIntegrationTest extends SeleneseTestCase {
    public void setUp() {
        selenium = new DefaultSelenium("localhost", 4446, "*firefox",
                "http://localhost:8080");
        selenium.start();
    }

//    public void testSnpAssociation() throws Exception {
//        selenium.open("/cagwas");
//        selenium.click("//input[@value='Browse Data']");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("//input[@value='Submit']");
//        selenium.waitForPageToLoad("30000");
//        selenium.select("analysis",
//                "label=Incidence density sampling, Adjusted score test");
//        selenium.select("panel", "label=Illumina HumanHap300");
//        selenium.type("hugoList", "EGFR");
//        selenium.click("//input[@value='Submit']");
//        selenium.waitForPageToLoad("30000");
//        verifyTrue(selenium
//                .isTextPresent("SNP Association Finding Report - (45 results)"));
//        checkForVerificationErrors();
//    }
//
//    public void testPopulationFinding() {
//        selenium.open("/cagwas/");
//        selenium.click("//input[@value='Browse Data']");
//        selenium.waitForPageToLoad("30000");
//        selenium.click("document.browseForm.query[1]");
//        selenium.click("//input[@value='Submit']");
//        selenium.waitForPageToLoad("30000");
//        selenium.removeSelection("population", "label=All");
//        selenium.addSelection("population", "label=CONTROL");
//        selenium.select("panel", "label=Illumina HumanHap300");
//        selenium.type("hugoList", "EGFR");
//        selenium.click("//input[@value='Submit']");
//        selenium.waitForPageToLoad("30000");
//        verifyTrue(selenium
//                .isTextPresent("SNP Frequency Report - (45 results)"));
//        checkForVerificationErrors();
//    }

	/**
	 * SNP Association Finding Search by SNP Panel for caGWAS Breast Cancer WGAS Phase 1
	 * 
	 * @throws Exception
	 * 
	 */
	public void testSelenium_caGWAS_TC2_6_Breast() throws Exception {	
		selenium.open("/cagwas/");
		selenium.click("link=Browse Data");
		selenium.waitForPageToLoad("30000");
		selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
		selenium.click("//input[@value='Submit']");
		selenium.waitForPageToLoad("30000");
		selenium.select("panel", "label=Illumina HumanHap550");
		selenium.type("hugoList", "BRCA1");
		selenium.click("//input[@value='Submit']");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("exact:Panel: Illumina HumanHap550"));
		verifyTrue(selenium.isTextPresent("dbSNP ID"));
		verifyTrue(selenium.isTextPresent("Chromosome"));
		verifyTrue(selenium.isTextPresent("Physical Position (bp)"));
		verifyTrue(selenium.isTextPresent("Associated Genes"));
		verifyTrue(selenium.isTextPresent("Analysis Name"));
		verifyTrue(selenium.isTextPresent("p-value"));
		verifyTrue(selenium.isTextPresent("Whole Genome Rank"));
		verifyTrue(selenium.isTextPresent("Estimated Odds Ratios"));
		verifyTrue(selenium.isTextPresent("Heterozygote Risk"));
		verifyTrue(selenium.isTextPresent("Homozygote Risk"));
		verifyTrue(selenium.isTextPresent("rs9911630"));
		verifyTrue(selenium.isTextPresent("17"));
		verifyTrue(selenium.isTextPresent("38441868"));
		verifyTrue(selenium.isTextPresent("ARHN"));
		verifyTrue(selenium.isTextPresent("VAT1"));
		verifyTrue(selenium.isTextPresent("BRCA1"));
		verifyTrue(selenium.isTextPresent("Unadjusted score test"));
		verifyTrue(selenium.isTextPresent("0.914318"));
		verifyTrue(selenium.isTextPresent("483166"));
		verifyTrue(selenium.isTextPresent("0.963"));
		verifyTrue(selenium.isTextPresent("0.9788"));
		checkForVerificationErrors();
	}

	 /**
	  * SNP Association Finding Search by SNP Panel
	  *     for caGWAS Breast Cancer WGAS Phase 1
 	  * Test Case 2 Test data set 2.7
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC2_7_Breast() throws Exception {
			selenium.open("/caGWAS/");
			selenium.click("link=Browse Data");
			selenium.waitForPageToLoad("30000");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.select("panel", "label=Illumina HumanHap550");
			selenium.type("hugoList", "BRCA1\nBRCA2");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyTrue(selenium.isTextPresent("SNP Association Finding Report - (88 results)"));
			checkForVerificationErrors();
		}
	
	 /**
	  * SNP Association Finding Search by SNP Panel
	  *     for caGWAS Breast Cancer WGAS Phase 1
	  * Test Case 2 Test data set 2.10
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC2_10_Breast() throws Exception {
			selenium.open("/cagwas/");
			selenium.click("link=Browse Data");
			selenium.waitForPageToLoad("30000");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.select("chromosome", "label=1");
			selenium.type("chromosomeFrom", "1");
			selenium.select("fromUnit", "label=mb");
			selenium.type("chromosomeTo", "2");
			selenium.select("toUnit", "label=mb");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyEquals("Study: caGWAS Breast Cancer WGAS Phase 1", selenium.getText("//h3"));
			verifyEquals("caGWAS SNP Association Finding Report", selenium.getTitle());
			verifyTrue(selenium.isTextPresent("SNP Association Finding Report - (110 results)"));
			checkForVerificationErrors();
		}
	 
	/**
	  * SNP Association Finding Search by SNP Panel
	  *     for caGWAS Breast Cancer WGAS Phase 1
	  * Test Case 2 Test data set 2.12
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC2_12_Breast() throws Exception {
			selenium.open("/cagwas/");
			selenium.click("//input[@value='Browse Data']");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.select("chromosome", "label=1");
			selenium.type("chromosomeFrom", "1");
			selenium.select("fromUnit", "label=mb");
			selenium.type("chromosomeTo", "10");
			selenium.select("toUnit", "label=mb");
			selenium.type("idList", "rs1998760\nrs1998760\nrs3762444\nrs3762444");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyTrue(selenium.isTextPresent("exact:Study: caGWAS Breast Cancer WGAS Phase 1"));
			verifyTrue(selenium.isTextPresent("SNP Association Finding Report - (4 results)   Save Results"));
			verifyTrue(selenium.isElementPresent("link=dbSNP ID"));
			verifyTrue(selenium.isTextPresent("rs1998760"));
			verifyTrue(selenium.isTextPresent("rs1998760"));
			verifyTrue(selenium.isTextPresent("rs3762444"));
			verifyTrue(selenium.isTextPresent("rs3762444"));
			verifyTrue(selenium.isTextPresent("Chromosome"));
			verifyTrue(selenium.isTextPresent("1"));
			checkForVerificationErrors();
		}

	/**
	  * SNP Association Finding Search by SNP Panel
	  *     for caGWAS Breast Cancer WGAS Phase 1
	  * Test Case 2 Test data set 2.13
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC2_13_Breast() throws Exception {
			selenium.open("/cagwas/");
			selenium.click("//input[@value='Browse Data']");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.select("chromosome", "label=1");
			selenium.type("chromosomeFrom", "1");
			selenium.type("chromosomeTo", "3000000");
			selenium.click("association");
			selenium.type("pvalue", "0.1");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyTrue(selenium.isTextPresent("exact:Study: caGWAS Breast Cancer WGAS Phase 1"));
			verifyTrue(selenium.isTextPresent("SNP Association Finding Report - (33 results)   Save Results"));
			checkForVerificationErrors();
		}

	/**
	  * Population Frequency Report for caGWAS Breast Cancer WGAS Phase 1
	  * Test Case 3 Test data set 3.1
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC3_1_Breast() throws Exception {
			selenium.open("/cagwas/");
			selenium.click("link=Browse Data");
			selenium.waitForPageToLoad("30000");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("document.browseForm.query[1]");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.select("chromosome", "label=Y");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyTrue(selenium.isTextPresent("exact:Study: caGWAS Breast Cancer WGAS Phase 1"));
			verifyEquals("caGWAS SNP Population Frequency Report", selenium.getTitle());
			verifyTrue(selenium.isTextPresent("Your search criteria produced no results"));
			checkForVerificationErrors();
		}

	/**
	  * Population Frequency Report for caGWAS Breast Cancer WGAS Phase 1
	  * Test Case 3 Test data set 3.2
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC3_2_Breast() throws Exception {
			selenium.open("/cagwas/about.jsp");
			selenium.click("link=Browse Data");
			selenium.waitForPageToLoad("30000");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("document.browseForm.query[1]");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.select("chromosome", "label=XY");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyEquals("caGWAS SNP Population Frequency Report", selenium.getTitle());
			verifyTrue(selenium.isTextPresent("exact:Study: caGWAS Breast Cancer WGAS Phase 1"));
			verifyTrue(selenium.isTextPresent("SNP Frequency Report - (30 results)   Save Results"));
			verifyTrue(selenium.isTextPresent("dbSNP ID"));
			verifyTrue(selenium.isTextPresent("Chromosome"));
			verifyTrue(selenium.isTextPresent("Physical Position (bp)"));
			verifyTrue(selenium.isTextPresent("Associated Genes"));
			verifyTrue(selenium.isTextPresent("Population"));
			verifyTrue(selenium.isTextPresent("Completion Rate (N/M)"));
			verifyTrue(selenium.isTextPresent("Hardy Weinberg pValue"));
			verifyTrue(selenium.isTextPresent("Allele"));
			verifyTrue(selenium.isTextPresent("Allele Count (Frequency)"));
			verifyTrue(selenium.isTextPresent("Genotype"));
			verifyTrue(selenium.isTextPresent("Genotype Count (Frequency)"));
			checkForVerificationErrors();
		}
	
	/**
	  * Population Frequency Report for caGWAS Breast Cancer WGAS Phase 1
	  * Test Case 3 Test data set 3.3 and 3.3a
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC3_3_Breast() throws Exception {
			selenium.open("/cagwas/");
			selenium.click("link=Browse Data");
			selenium.waitForPageToLoad("30000");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("document.browseForm.query[1]");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.select("chromosome", "label=X");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyEquals("caGWAS Download Page", selenium.getTitle());
			verifyTrue(selenium.isTextPresent("Your query returned more than 500 results."));
			selenium.click("//input[@value='Back']");
			selenium.waitForPageToLoad("30000");
			selenium.removeSelection("population", "label=All");
			selenium.addSelection("population", "label=CONTROL");
			selenium.select("chromosome", "label=X");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyEquals("caGWAS Download Page", selenium.getTitle());
			verifyTrue(selenium.isTextPresent("Your query returned more than 500 results."));
			selenium.click("//input[@value='Back']");
			selenium.waitForPageToLoad("30000");
			checkForVerificationErrors();
		}
	
	/**
	  * Population Frequency Report for caGWAS Breast Cancer WGAS Phase 1
	  * Test Case 4 Test data set 3.4 and 3.4a
	  * 	 
	  * @throws Exception
	  *
	  */
	public void testSelenium_caGWAS_TC3_4_Breast() throws Exception {
			selenium.open("/cagwas/");
			selenium.click("link=Browse Data");
			selenium.waitForPageToLoad("30000");
			selenium.select("study", "label=caGWAS Breast Cancer WGAS Phase 1");
			selenium.click("document.browseForm.query[1]");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			selenium.removeSelection("population", "label=All");
			selenium.addSelection("population", "label=CASE");
			selenium.select("panel", "label=Illumina HumanHap550");
			selenium.select("chromosome", "label=X");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyEquals("caGWAS Download Page", selenium.getTitle());
			verifyTrue(selenium.isTextPresent("exact:Study: caGWAS Breast Cancer WGAS Phase 1"));
			verifyTrue(selenium.isTextPresent("Your query returned more than 500 results."));
			selenium.click("//input[@value='Back']");
			selenium.waitForPageToLoad("30000");
			selenium.removeSelection("population", "label=All");
			selenium.addSelection("population", "label=CASE");
			selenium.select("panel", "label=Illumina HumanHap550");
			selenium.select("chromosome", "label=X");
			selenium.click("//input[@value='Submit']");
			selenium.waitForPageToLoad("30000");
			verifyEquals("caGWAS Download Page", selenium.getTitle());
			verifyTrue(selenium.isTextPresent("exact:Study: caGWAS Breast Cancer WGAS Phase 1"));
			verifyTrue(selenium.isTextPresent("Your query returned more than 500 results."));
			checkForVerificationErrors();
		}
	
	
	
    public static Test suite() {
        TestSuite suit = new TestSuite();
        suit.addTest(new TestSuite(CagwasUIIntegrationTest.class));
        return suit;
    }
}
