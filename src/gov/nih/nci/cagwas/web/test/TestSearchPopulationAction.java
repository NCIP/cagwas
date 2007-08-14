package gov.nih.nci.cgems.web.test;

import gov.nih.nci.cgems.reports.SNPFrequencyFindingReport;

import java.util.ArrayList;

/**
 * Tests the SearchAssociationsAction
 * 
 * @author mholck
 *
 */
public class TestSearchPopulationAction extends CgemsActionTest
{	
	public TestSearchPopulationAction(String testName)
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
    	for(int i=0; i < studyIds.size(); i++)
    		successfulSearch(studyIds.get(i));
    }
    
    /**
	 * Tests searching using only dbSNP ids
	 */
    public void testSuccessfulSearchIDOnly()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", "rs11238345 rs437");
    	for(int i=0; i < studyIds.size(); i++)
    		successfulSearch(studyIds.get(i));
    }
    
    public void testSuccessfulSearchHUGOPopulation()
    {
    	addRequestParameter("hugoList", "MET EGFR");
    	addRequestParameter("idList", " ");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		populatePopulations(studyId);
    		
    		for(int j=0; j < populationNames.size(); j++)
    		{
    			addRequestParameter("population", populationNames.get(j));
    			successfulSearch(studyId);
    		}
    	}
    }
    
    public void testSuccessfulSearchIDPopulation()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", "rs11238345 rs437");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		populatePopulations(studyId);
    		
    		for(int j=0; j < populationNames.size(); j++)
    		{
    			addRequestParameter("population", populationNames.get(j));
    			successfulSearch(studyId);
    		}
    	}
    }
    
    public void testSuccessfulSearchHUGOPopulationWeinbergPvalue()
    {
    	addRequestParameter("hugoList", "MET EGFR");
    	addRequestParameter("idList", " ");
    	addRequestParameter("weinbergOperator", ">=");
    	addRequestParameter("weinbergValue", "0.5");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		populatePopulations(studyId);
    		
    		for(int j=0; j < populationNames.size(); j++)
    		{
    			addRequestParameter("population", populationNames.get(j));
    			successfulSearch(studyId);
    		}
    	}
    }
    
    public void testSuccessfulSearchChromosomePopulationWeinberg()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("chromosome", "7");
    	addRequestParameter("weinbergOperator", "<=");
    	addRequestParameter("weinbergValue", "0.003");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		populatePopulations(studyId);
    		
    		for(int j=0; j < populationNames.size(); j++)
    		{
    			addRequestParameter("population", populationNames.get(j));
    			successfulSearch(studyId);
    		}
    	}
    }
    
    public void testSuccessfulSearchChromosomePopulationWeinbergMinorAllele()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("chromosome", "7");
    	addRequestParameter("weinbergOperator", "<=");
    	addRequestParameter("weinbergValue", "0.1");
    	addRequestParameter("alleleOperator", "<=");
    	addRequestParameter("alleleValue", "0.1");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		populatePopulations(studyId);
    		
    		for(int j=0; j < populationNames.size(); j++)
    		{
    			addRequestParameter("population", populationNames.get(j));
    			successfulSearch(studyId);
    		}
    	}
    }
    
    public void testSuccessfulSearchChromosomePopulationWeinbergMinorAlleleCompletion()
    {
    	addRequestParameter("hugoList", " ");
    	addRequestParameter("idList", " ");
    	addRequestParameter("chromosome", "7");
    	addRequestParameter("weinbergOperator", "<=");
    	addRequestParameter("weinbergValue", "0.1");
    	addRequestParameter("alleleOperator", "<=");
    	addRequestParameter("alleleValue", "0.1");
    	addRequestParameter("completionOperator", "<=");
    	addRequestParameter("completionValue", "99");
    	for(int i=0; i < studyIds.size(); i++)
    	{
    		Long studyId = studyIds.get(i);
    		populatePopulations(studyId);
    		
    		for(int j=0; j < populationNames.size(); j++)
    		{
    			addRequestParameter("population", populationNames.get(j));
    			successfulSearch(studyId);
    		}
    	}
    }
    
    @SuppressWarnings("unchecked")
    private void successfulSearch(Long studyId)
    {
    	setRequestPathInfo("/searchPopulation");
    	request.getSession().setAttribute("studyId", studyId);
    	actionPerform();
    	verifyTilesForward("success", "cgems.populationReport");
        ArrayList<SNPFrequencyFindingReport> results =
        	(ArrayList<SNPFrequencyFindingReport>)request.getSession().getAttribute("pop.results");
        assertNotNull(results);
        assertTrue(results.size() > 0);
        verifyNoActionErrors();
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
    	setRequestPathInfo("/searchPopulation");
    	request.getSession().setAttribute("studyId", studyId);
    	actionPerform();
    	verifyTilesForward("success", "cgems.populationReport");
        ArrayList<SNPFrequencyFindingReport> results =
        	(ArrayList<SNPFrequencyFindingReport>)request.getSession().getAttribute("pop.results");
        assertNotNull(results);
        assertTrue(results.size() == 0);
        verifyNoActionErrors();
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
    	verifyTilesForward("download", "cgems.download");
        assertNotNull(request.getSession().getAttribute("searchType"));
        assertNotNull(request.getSession().getAttribute("form"));
    }
    
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestSearchPopulationAction.class);
    }
}
