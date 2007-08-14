package gov.nih.nci.cgems.web.test;

/**
 * Tests the BrowseSetupAction
 * 
 * @author mholck
 *
 */
public class TestBrowseSetupAction extends CgemsActionTest
{
	public TestBrowseSetupAction(String testName)
	{
		super(testName);
	}

    public void testSuccessfulBrowseSetup()
    {
    	setRequestPathInfo("/browseSetup");
    	actionPerform();
    	verifyTilesForward("success", "cgems.browseData");
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
