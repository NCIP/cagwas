package gov.nih.nci.cgems.web.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllActionTests
{

	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test for gov.nih.nci.cgems.web.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestBrowseSetupAction.class);
		suite.addTestSuite(TestBrowseAction.class);
		suite.addTestSuite(TestSearchAssociationsAction.class);
		suite.addTestSuite(TestSearchPopulationAction.class);
		//$JUnit-END$
		return suite;
	}

}
