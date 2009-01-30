package gov.nih.nci.cagwas.web.action;

/**
 * The CagwasConstants class defines some constant values that are used
 * throughout the code.  They are defined here so they can easily be
 * changed in only one place.
 * <P>
 * @author mholck
 */
public class CagwasConstants
{
	// The maximum number of gene symbols allowed
	public static int MAX_SYMBOLS = 100;
	// The maximum number of dbSNP ids allowed
	public static int MAX_IDS = 1000;
	// The maximum number of findings to be displayed
	public static int MAX_RESULTS = 500;
	
	public static final String dlpath = "dl.download?f=";
}
