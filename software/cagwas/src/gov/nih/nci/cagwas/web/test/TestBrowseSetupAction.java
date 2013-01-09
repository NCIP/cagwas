/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.test;

/**
 * Tests the BrowseSetupAction
 * 
 * @author mholck
 *
 */
public class TestBrowseSetupAction extends CagwasActionTest
{
	public TestBrowseSetupAction(String testName)
	{
		super(testName);
	}

    public void testSuccessfulBrowseSetup()
    {
    	setRequestPathInfo("/browseSetup");
    	actionPerform();
    	verifyTilesForward("success", "cagwas.browseData");
        assertNotNull(request.getSession().getAttribute("studies"));
        assertNotNull(request.getSession().getAttribute("studyNames"));
        assertNotNull(request.getSession().getAttribute("studyVersionsMap"));
        verifyNoActionErrors();
    }
    
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestBrowseSetupAction.class);
    }
}
