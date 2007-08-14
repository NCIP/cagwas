package gov.nih.nci.cgems.web.test;

import gov.nih.nci.cgems.reports.StudyParticipantReport;

import java.util.ArrayList;

/**
 * Tests the SearchSubjectAction
 * 
 * @author mholck
 *
 */
public class TestSearchSubjectAction extends CgemsActionTest
{
	public TestSearchSubjectAction(String testName)
	{
		super(testName);
	}

    public void testSuccessfulSearchPopulationGenderHistory()
    {
    	addRequestParameter("population", populationNames.get(0));
    	addRequestParameter("gender", "MALE");
    	addRequestParameter("familyHistory", "YES");
    	successfulSearch(studyIds.get(0));
    }
    
    public void testSuccessfulSearchPopulationGenderAge()
    {
    	addRequestParameter("population", populationNames.get(0));
    	addRequestParameter("gender", "MALE");
    	addRequestParameter("lowerAge", "55");
    	addRequestParameter("upperAge", "64");
    	successfulSearch(studyIds.get(0));
    }
    
    @SuppressWarnings("unchecked")
    private void successfulSearch(Long studyId)
    {
    	setRequestPathInfo("/searchSubject");
    	request.getSession().setAttribute("study", studyId);
    	actionPerform();
    	verifyTilesForward("success", "cgems.subjectReport");
        ArrayList<StudyParticipantReport> results =
        	(ArrayList<StudyParticipantReport>)request.getSession().getAttribute("results");
        assertNotNull(results);
        assertTrue(results.size() > 0);
        verifyNoActionErrors();
    }
    
    public void testDownloadSearchPopulation()
    {
    	addRequestParameter("population", populationNames.get(0));
    	setRequestPathInfo("/searchSubject");
    	request.getSession().setAttribute("study", studyIds.get(0));
    	actionPerform();
    	verifyTilesForward("download", "cgems.download");
        assertNotNull(request.getSession().getAttribute("searchType"));
        assertNotNull(request.getSession().getAttribute("form"));
        verifyNoActionErrors();
    }
    
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestSearchSubjectAction.class);
    }
}
