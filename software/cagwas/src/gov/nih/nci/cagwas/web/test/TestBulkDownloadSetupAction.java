/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.test;

/**
 * Tests the BulkSetupAction
 * 
 * @author achickerur
 *
 */
public class TestBulkDownloadSetupAction extends CagwasActionTest
{
	public TestBulkDownloadSetupAction(String testName)
	{
		super(testName);
	}

    public void testSuccessfulBulkDownloadSetup()
    {
    	setRequestPathInfo("/downloadSetup");
    	actionPerform();
    	//verifyTilesForward("success", "cagwas.download"");
        //assertNotNull(request.getAttribute("studies"));
        verifyNoActionErrors();
    }
    
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestBulkDownloadSetupAction.class);
    }
}
