package gov.nih.nci.cagwas.web.test;

import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisMethod;
import gov.nih.nci.cagwas.reports.SNPAssociationFindingReport;

import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;

/**
 * Tests the SearchAssociationsAction
 * 
 * @author mholck
 *
 */
public class TestSearchAssociationsAction extends CagwasActionTest
{	
	public TestSearchAssociationsAction(String testName)
	{
		super(testName);
	}

	/**
	 * Tests searching using only HUGO gene symbols
	 */
    public void testSuccessfulSearchHUGOOnly()
    {
    	addRequestParameter("hugoList", "MET EGFR");
    	addRequestParameter("idList", " ");
    	
    	// Then call for each study id
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		setupAssociations(studyIds.get(i));
    		successfulSearch(studyIds.get(i), 0);
    	}
    }
    
    /**
	 * Tests searching using only dbSNP ids
	 */
    public void testSuccessfulSearchIDOnly()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", "rs11238345 rs437");
    	
    	// Then call for each study id
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		setupAssociations(studyIds.get(i));
    		successfulSearch(studyIds.get(i), 0);
    	}
    }
    
    public void testSuccessfulSearchHUGOAnalysis()
    {
    	addRequestParameter("hugoList", "MET EGFR");
    	addRequestParameter("idList", " ");
    	
    	// Call for each study id
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		setupAssociations(studyId);
    		
    		// Make sure we test every analysis option
    		Integer num = (Integer)request.getSession().getAttribute("methodTypeNum");
    		int numMethods = num.intValue();
    		for(int j=1; j <= numMethods; j++)
    		{
    			ArrayList<LabelValueBean> methodList =
    				(ArrayList<LabelValueBean>)request.getSession().getAttribute("methodList" + j);
    			
    			for (LabelValueBean method : methodList)
	        	{
    				addRequestParameter("analysisMethod" + j, method.getValue());
    				successfulSearch(studyId, j);
	        	}
    		}
    	}
    }
    
    public void testSuccessfulSearchIDAnalysis()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", "rs11238345 rs437");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		setupAssociations(studyId);
    		
    		// Make sure we test every analysis option
    		Integer num = (Integer)request.getSession().getAttribute("methodTypeNum");
    		int numMethods = num.intValue();
    		for(int j=1; j <= numMethods; j++)
    		{
    			ArrayList<LabelValueBean> methodList =
    				(ArrayList<LabelValueBean>)request.getSession().getAttribute("methodList" + j);
    			
    			for (LabelValueBean method : methodList)
	        	{
    				addRequestParameter("analysisMethod" + j, method.getValue());
    				successfulSearch(studyId, j);
	        	}
    		}
    	}
    }
    
    public void testSuccessfulSearchHUGOAnalysisPvalue()
    {
    	addRequestParameter("hugoList", "MET EGFR");
    	addRequestParameter("idList", " ");
    	addRequestParameter("association", "pvalue");
    	addRequestParameter("pvalue", "0.5");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		setupAssociations(studyId);
    		
    		// Make sure we test every analysis option
    		Integer num = (Integer)request.getSession().getAttribute("methodTypeNum");
    		int numMethods = num.intValue();
    		for(int j=1; j <= numMethods; j++)
    		{
    			ArrayList<LabelValueBean> methodList =
    				(ArrayList<LabelValueBean>)request.getSession().getAttribute("methodList" + j);
    			
    			for (LabelValueBean method : methodList)
	        	{
    				addRequestParameter("analysisMethod" + j, method.getValue());
    				successfulSearch(studyId, j);
	        	}
    		}
    	}
    }
    
    public void testSuccessfulSearchHUGOAnalysisRank()
    {
    	addRequestParameter("hugoList", "MET EGFR");
    	addRequestParameter("idList", " ");
    	addRequestParameter("association", "rank");
    	addRequestParameter("rank", "30000");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		setupAssociations(studyId);
    		
    		// Make sure we test every analysis option
    		Integer num = (Integer)request.getSession().getAttribute("methodTypeNum");
    		int numMethods = num.intValue();
    		for(int j=1; j <= numMethods; j++)
    		{
    			ArrayList<LabelValueBean> methodList =
    				(ArrayList<LabelValueBean>)request.getSession().getAttribute("methodList" + j);
    			
    			for (LabelValueBean method : methodList)
	        	{
    				addRequestParameter("analysisMethod" + j, method.getValue());
    				successfulSearch(studyId, j);
	        	}
    		}
    	}
    }
    
    public void testSuccessfulSearchChromosomeAnalysisPvalue()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("association", "pvalue");
    	addRequestParameter("pvalue", "0.005");
    	addRequestParameter("chromosome", chromosomes.get(6));
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		setupAssociations(studyId);
    		
    		// Make sure we test every analysis option
    		Integer num = (Integer)request.getSession().getAttribute("methodTypeNum");
    		int numMethods = num.intValue();
    		for(int j=1; j <= numMethods; j++)
    		{
    			ArrayList<LabelValueBean> methodList =
    				(ArrayList<LabelValueBean>)request.getSession().getAttribute("methodList" + j);
    			
    			for (LabelValueBean method : methodList)
	        	{
    				addRequestParameter("analysisMethod" + j, method.getValue());
    				successfulSearch(studyId, j);
	        	}
    		}
    	}
    }
    
    public void testSuccessfulSearchChromosomeAnalysisRank()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("chromosome", chromosomes.get(6));
    	addRequestParameter("association", "rank");
    	addRequestParameter("rank", "2000");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		setupAssociations(studyId);
    		
    		// Make sure we test every analysis option
    		Integer num = (Integer)request.getSession().getAttribute("methodTypeNum");
    		int numMethods = num.intValue();
    		for(int j=1; j <= numMethods; j++)
    		{
    			ArrayList<LabelValueBean> methodList =
    				(ArrayList<LabelValueBean>)request.getSession().getAttribute("methodList" + j);
    			
    			for (LabelValueBean method : methodList)
	        	{
    				addRequestParameter("analysisMethod" + j, method.getValue());
    				successfulSearch(studyId, j);
	        	}
    		}
    	}
    }
    
    public void testSuccessfulSearchChromosomeRange()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("chromosome", chromosomes.get(6));
    	addRequestParameter("chromosomeFrom", "1");
    	addRequestParameter("fromUnit", "bp");
    	addRequestParameter("chromosomeTo", "1");
    	addRequestParameter("toUnit", "mb");
    	successfulSearch(studyIds.get(0), 0);
    }
    
    public void testSuccessfulSearchChromosomeRangeToOnly()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("chromosome", chromosomes.get(6));
    	addRequestParameter("chromosomeTo", "1");
    	addRequestParameter("toUnit", "mb");
    	successfulSearch(studyIds.get(0), 0);
    }
    
    @SuppressWarnings("unchecked")
    private void successfulSearch(Long studyId, int analysisSet)
    {
    	Integer num = (Integer)request.getSession().getAttribute("methodTypeNum");
    	
    	// Set the analysis methods to be blank
    	int numMethods = num.intValue();
    	for (int i = 1; i <= numMethods; i++)
    	{
    		if (analysisSet != i)
    		{
	    		switch(i)
	    		{
	    			case 1: addRequestParameter("analysisMethod1", ""); break;
	    			case 2: addRequestParameter("analysisMethod2", ""); break;
	    			case 3: addRequestParameter("analysisMethod3", ""); break;
	    			case 4: addRequestParameter("analysisMethod4", ""); break;
	    			case 5: addRequestParameter("analysisMethod5", ""); break;
	    			case 6: addRequestParameter("analysisMethod6", ""); break;
	    			case 7: addRequestParameter("analysisMethod7", ""); break;
	    			case 8: addRequestParameter("analysisMethod8", ""); break;
	    		}
    		}
    	}
    	
    	// Set the number of associations using the session variable
    	addRequestParameter("numAnalysisMethods", num.toString());
    	setRequestPathInfo("/searchAssociations");
    	request.getSession().setAttribute("studyId", studyId);
    	actionPerform();
    	verifyNoActionErrors();
    	verifyTilesForward("success", "cagwas.associationReport");
        ArrayList<SNPAssociationFindingReport> results =
        	(ArrayList<SNPAssociationFindingReport>)request.getSession().getAttribute("assoc.results");
        assertNotNull(results);
        assertTrue(results.size() > 0);
        System.out.println("Successful search returned " + results.size() + " records");
    }
    
    public void testNoResultsSearchHUGOOnly()
    {
    	addRequestParameter("hugoList", "XYZ PDQ");
    	addRequestParameter("idList", " ");
    	noResultSearch(studyIds.get(0));
    }
    
    public void testNoResultsSearchIDOnly()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", "rs1 rs2");
    	noResultSearch(studyIds.get(0));
    }
    
    @SuppressWarnings("unchecked")
    private void noResultSearch(Long studyId)
    {
    	setRequestPathInfo("/searchAssociations");
    	request.getSession().setAttribute("studyId", studyId);
    	actionPerform();
    	verifyNoActionErrors();
    	verifyTilesForward("success", "cagwas.associationReport");
        ArrayList<SNPAssociationFindingReport> results =
        	(ArrayList<SNPAssociationFindingReport>)request.getSession().getAttribute("assoc.results");
        assertNotNull(results);
        assertTrue(results.size() == 0);
    }
    
    public void testDownloadSearchChromosome()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("chromosome", chromosomes.get(6));
    	setRequestPathInfo("/searchAssociations");
    	request.getSession().setAttribute("study", studyIds.get(0));
    	actionPerform();
    	verifyNoActionErrors();
    	verifyTilesForward("download", "cagwas.download");
        assertNotNull(request.getSession().getAttribute("searchType"));
        assertNotNull(request.getSession().getAttribute("form"));
    }
    
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestSearchAssociationsAction.class);
    }
}
