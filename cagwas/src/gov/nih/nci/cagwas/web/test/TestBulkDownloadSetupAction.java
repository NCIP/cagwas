package gov.nih.nci.cgems.web.test;

/**
 * Tests the BulkSetupAction
 * 
 * @author achickerur
 *
 */
public class TestBulkDownloadSetupAction extends CgemsActionTest
{
	public TestBulkDownloadSetupAction(String testName)
	{
		super(testName);
	}

    public void testSuccessfulBulkDownloadSetup()
    {
    	setRequestPathInfo("/downloadSetup");
    	actionPerform();
    	//verifyTilesForward("success", "cgems.download"");
        //assertNotNull(request.getAttribute("studies"));
        verifyNoActionErrors();
    }
    
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestBulkDownloadSetupAction.class);
    }
}
