/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the BrowseAction
 * 
 * @author mholck
 *
 */
public class TestBrowseAction extends CagwasActionTest
{
	public TestBrowseAction(String testName)
	{
		super(testName);
	}

    public void testSuccessfulBrowseAssociationStudy()
    {
    	for (int i = 0; i < studyIds.size(); i++)
    		successfulBrowseAssociationStudy(studyIds.get(i));
    }
    
    private void successfulBrowseAssociationStudy(Long studyId)
    {
    	setRequestPathInfo("/browse");
    	addRequestParameter("studyId", studyId.toString());
    	addRequestParameter("query", "Association");
    	actionPerform();
    	verifyTilesForward("association", "cagwas.searchAssociations");
        assertNotNull(request.getSession().getAttribute("methods1"));
        assertNotNull(request.getSession().getAttribute("chromosomes"));
        assertNotNull(request.getSession().getAttribute("panels"));
        verifyNoActionErrors();
    }
    
    public void testSuccessfulBrowsePopulationStudy()
    {
    	for (int i = 0; i < studyIds.size(); i++)
    		successfulBrowsePopulationStudy(studyIds.get(i));
    }
    
    private void successfulBrowsePopulationStudy(Long studyId)
    {
    	setRequestPathInfo("/browse");
    	addRequestParameter("studyId", studyId.toString());
    	addRequestParameter("query", "Population");
    	actionPerform();
    	verifyTilesForward("population", "cagwas.searchPopulation");
        assertNotNull(request.getSession().getAttribute("populationCol"));
        assertNotNull(request.getSession().getAttribute("chromosomes"));
        assertNotNull(request.getSession().getAttribute("panels"));
        verifyNoActionErrors();
    }
    
    public void testSuccessfulBrowseSubjectStudy()
    {
    	for (int i = 0; i < studyIds.size(); i++)
    		successfulBrowseSubjectStudy(studyIds.get(i));
    }
    
    private void successfulBrowseSubjectStudy(Long studyId)
    {
    	setRequestPathInfo("/browse");
    	addRequestParameter("studyId", studyId.toString());
    	addRequestParameter("query", "Subjects");
    	
    	// Set the login state to give them permissions for this study
    	request.getSession().setAttribute("logged", "yes");
    	List<String> studyIDList = new ArrayList<String>();
    	studyIDList.add(studyId.toString());
    	request.getSession().setAttribute("studyIDList", studyIDList);
    	
    	actionPerform();
    	verifyTilesForward("subjects", "cagwas.searchSubject");
        assertNotNull(request.getSession().getAttribute("populationCol"));
        assertNotNull(request.getSession().getAttribute("analysisGroups"));
        assertNotNull(request.getSession().getAttribute("lowerAges"));
        assertNotNull(request.getSession().getAttribute("upperAges"));
        assertNotNull(request.getSession().getAttribute("caseControls"));
        verifyNoActionErrors();
    }
    
    public void testUnauthenticatedBrowseSubject()
    {
    	setRequestPathInfo("/browse");
    	addRequestParameter("studyId", studyIds.get(0).toString());
    	addRequestParameter("query", "Subjects");
    	actionPerform();
    	verifyForward("login");
    	assertNotNull(request.getSession().getAttribute("ref"));
        verifyNoActionErrors();
    }
    
    public void testSuccessfulBrowseGenotypeStudy()
    {
    	for (int i = 0; i < studyIds.size(); i++)
    		successfulBrowseGenotypeStudy(studyIds.get(i));
    }
    
    private void successfulBrowseGenotypeStudy(Long studyId)
    {
    	setRequestPathInfo("/browse");
    	addRequestParameter("studyId", studyId.toString());
    	addRequestParameter("query", "Genotypes");
    	
    	// Set the login state to give them permissions for this study
    	request.getSession().setAttribute("logged", "yes");
    	List<String> studyIDList = new ArrayList<String>();
    	studyIDList.add(studyId.toString());
    	request.getSession().setAttribute("studyIDList", studyIDList);
    	
    	actionPerform();
        assertNotNull(request.getSession().getAttribute("populationCol"));
        assertNotNull(request.getSession().getAttribute("analysisGroups"));
        assertNotNull(request.getSession().getAttribute("chromosomes"));
        assertNotNull(request.getSession().getAttribute("panels"));
        assertNotNull(request.getSession().getAttribute("lowerAges"));
        assertNotNull(request.getSession().getAttribute("upperAges"));
        assertNotNull(request.getSession().getAttribute("caseControls"));
        verifyNoActionErrors();
    }
    
    public void testUnauthenticatedBrowseGenotype()
    {
    	setRequestPathInfo("/browse");
    	addRequestParameter("studyId", studyIds.get(0).toString());
    	addRequestParameter("query", "Genotypes");
    	actionPerform();
    	verifyForward("login");
    	assertNotNull(request.getSession().getAttribute("ref"));
        verifyNoActionErrors();
    }
    
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestBrowseAction.class);
    }
}
