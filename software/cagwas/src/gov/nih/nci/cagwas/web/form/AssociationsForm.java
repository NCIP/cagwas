/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.form;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * The AssociationsForm class is the form validator for the Search Association
 * Findings form.
 * <P>
 * @author mholck
 * @see gov.nih.nci.cagwas.web.form.SNPSearchForm
 */
public class AssociationsForm extends SNPSearchForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = -7474933898110829232L;
	
	// Analysis stores the analysis name selected
	private String analysis;
	private String analysisMethod1;
	private String analysisMethod2;
	private String analysisMethod3;
	private String analysisMethod4;
	private String analysisMethod5;
	private String analysisMethod6;
	private String analysisMethod7;
	private String analysisMethod8;
	
	private String numAnalysisMethods;
	
	private Hashtable<Long, ArrayList<String>> analysisMethodCodeMap;
	
	// Association is the value of the radio buttons
	private String association;
	// Pvalue is the value of the from the pvalue textbox
	private String pvalue;
	// Rank is the value from the Whole Genome Rank textbox
	private String rank;

	public AssociationsForm()
	{
		analysisMethodCodeMap = new Hashtable<Long, ArrayList<String>>();
	}
	
	/**
	 * The validate method is called when the form is submitted and is
	 * responsible for making sure the input is valid.
	 * <P>
	 * @param mapping The ActionMapping for the posted action
	 * @param request The HttpServletRequest for this post
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = super.validate(mapping, request);
		
		// Validate the pvalue or rank values
		if ((getAssociation() != null) && (getAssociation().equals("pvalue")))
		{
			if ((getPvalue() != null) && (getPvalue().length() >= 1))
			{
				// Verify that if it is set it is a float
				try
				{
					float value = Float.parseFloat(getPvalue());
					
					// Then check that it is a valid value
					if ((value > 1.0) || (value < 0))
						errors.add("pvalue", new ActionMessage("error.pvalue.value"));
				}
				catch(NumberFormatException nfe)
				{
					errors.add("pvalue", new ActionMessage("error.pvalue.type"));
				}
			}
			/*else
			{
				errors.add("pvalue", new ActionMessage("error.pvalue.required"));
			}*/
		}
		
		if ((getAssociation() != null) && (getAssociation().equals("rank")))
		{
			if ((getRank() != null) && (getRank().length() >= 1))
			{
				// Verify that if it is set it is an integer
				try
				{
					Integer.parseInt(getRank());
				}
				catch(NumberFormatException nfe)
				{
					errors.add("rank", new ActionMessage("error.rank.type"));
				}
			}
			/*else
			{
				errors.add("rank", new ActionMessage("error.rank.required"));
			}*/
		}
		
		return errors;
	}
	
	/**
	 * The reset method is called on the loading of this form and anytime the
	 * user selects the reset button.  It can be used to set the default starting
	 * values for the form.
	 * <P>
	 * @param mapping The ActionMapping for the posted action
	 * @param request The HttpServletRequest for this post
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
	}

	public String getAnalysis()
	{
		return analysis;
	}

	public void setAnalysis(String analysis)
	{
		this.analysis = analysis;
	}

	public String getAssociation()
	{
		return association;
	}

	public void setAssociation(String association)
	{
		this.association = association;
	}

	public String getPvalue()
	{
		if (pvalue != null)
			return pvalue.trim();
		else
			return pvalue;
	}

	public void setPvalue(String pvalue)
	{
		this.pvalue = pvalue;
	}

	public String getRank()
	{
		if (rank != null)
			return rank.trim();
		else
			return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public String getAnalysisMethod1()
	{
		return analysisMethod1;
	}

	public void setAnalysisMethod1(String analysisMethod1)
	{
		this.analysisMethod1 = analysisMethod1;
	}

	public String getAnalysisMethod2()
	{
		return analysisMethod2;
	}

	public void setAnalysisMethod2(String analysisMethod2)
	{
		this.analysisMethod2 = analysisMethod2;
	}

	public String getAnalysisMethod3()
	{
		return analysisMethod3;
	}

	public void setAnalysisMethod3(String analysisMethod3)
	{
		this.analysisMethod3 = analysisMethod3;
	}

	public String getAnalysisMethod4()
	{
		return analysisMethod4;
	}

	public void setAnalysisMethod4(String analysisMethod4)
	{
		this.analysisMethod4 = analysisMethod4;
	}

	public String getAnalysisMethod5()
	{
		return analysisMethod5;
	}

	public void setAnalysisMethod5(String analysisMethod5)
	{
		this.analysisMethod5 = analysisMethod5;
	}

	public String getAnalysisMethod6()
	{
		return analysisMethod6;
	}

	public void setAnalysisMethod6(String analysisMethod6)
	{
		this.analysisMethod6 = analysisMethod6;
	}

	public String getAnalysisMethod7() 
	{
		return analysisMethod7;
	}

	public void setAnalysisMethod7(String analysisMethod7)
	{
		this.analysisMethod7 = analysisMethod7;
	}

	public String getAnalysisMethod8()
	{
		return analysisMethod8;
	}

	public void setAnalysisMethod8(String analysisMethod8)
	{
		this.analysisMethod8 = analysisMethod8;
	}
	
	public String getAnalysisMethod(int index)
	{
		String method = "";
		
		if (index == 1)
			method = analysisMethod1;
		else if (index == 2)
			method = analysisMethod2;
		else if (index == 3)
			method = analysisMethod3;
		else if (index == 4)
			method = analysisMethod4;
		else if (index == 5)
			method = analysisMethod5;
		else if (index == 6)
			method = analysisMethod6;
		else if (index == 7)
			method = analysisMethod7;
		else if (index == 8)
			method = analysisMethod8;
		
		return method;
	}

	public String getNumAnalysisMethods()
	{
		return numAnalysisMethods;
	}

	public void setNumAnalysisMethods(String numAnalysisMethods)
	{
		this.numAnalysisMethods = numAnalysisMethods;
	}

	/**
	 * getAnalysisMethodCodes will return the ArrayList of represent codes for
	 * the specific dropdown list using the display order as the index.
	 * <P>
	 * @param index The display order of the checkbox
	 * @return ArrayList of String that has the represent codes to pass to the search
	 */
	public ArrayList<String> getAnalysisMethodCodes(int index)
	{
		ArrayList<String> value = null;
		
		value = analysisMethodCodeMap.get(new Long(index));
		
		return value;
	}

	/**
	 * addAnalysisCode adds a new String code for the analysis method to be used
	 * when performing the search.
	 * <P>
	 * @param index The display order of the checkbox this code belongs to
	 * @param code The String code to be stored
	 */
	public void addAnalysisCode(int index, String code)
	{
		Long key = new Long(index);
		ArrayList<String> codes;
		
		if (analysisMethodCodeMap.containsKey(key))
			codes = analysisMethodCodeMap.get(key);
		else
			codes = new ArrayList<String>();
		
		codes.add(code);
		analysisMethodCodeMap.put(key, codes);
	}
	
	/**
	 * isBlank is used to determine if the form had any criteria set or not
	 * <P>
	 * @return boolean 
	 */
	public boolean isBlank()
	{
		boolean result = true;
		
		// Check our parent first
		result = super.isBlank();
		
		if ((analysis != null) && (analysis.length() > 0))
			result = false;
		// Removed since Breast first selection defaults to a value
		/*if ((analysisMethod1 != null) && (analysisMethod1.length() > 0))
			result = false;*/
		if ((analysisMethod2 != null) && (analysisMethod2.length() > 0))
			result = false;
		if ((analysisMethod3 != null) && (analysisMethod3.length() > 0))
			result = false;
		if ((analysisMethod4 != null) && (analysisMethod4.length() > 0))
			result = false;
		if ((analysisMethod5 != null) && (analysisMethod5.length() > 0))
			result = false;
		if ((analysisMethod6 != null) && (analysisMethod6.length() > 0))
			result = false;
		if ((analysisMethod7 != null) && (analysisMethod7.length() > 0))
			result = false;
		if ((analysisMethod8 != null) && (analysisMethod8.length() > 0))
			result = false;
		if ((association != null) && (association.length() > 0))
			result = false;
		if ((pvalue != null) && (pvalue.length() > 0))
			result = false;
		if ((rank != null) && (rank.length() > 0))
			result = false;
		
		return result;
	}
}
