/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.caintegrator.domain.study.bean.Population;
import gov.nih.nci.cagwas.reports.StudyParticipantReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Set;

/**
 * The SortSubjectAction class is called when the user clicks on a column in the
 * subjects report to sort on that column
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SortSubjectAction extends Action
{
	private static Logger logger = Logger.getLogger(SortSubjectAction.class);
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		ActionMessages errors = new ActionMessages();
		ActionForward forward = null;
		String column = (String)request.getParameter("column");
		String order = (String)request.getParameter("order");
		
		ArrayList results = (ArrayList)request.getSession().getAttribute("subj.results");
		
		if (results != null)
		{
			logger.debug("Sorting results by column " + column);
			
			ArrayList<StudyParticipantReport> newResults = null;
			
			if (order.equals("ascending"))
			{
				newResults = sortAscending(results, column);
			}
			else
			{
				newResults = sortDescending(results, column);
			}
			
			request.getSession().setAttribute("subj.results", newResults);
			request.setAttribute("numberSubjects", newResults.size());
			request.setAttribute("sortedColumn", column);
			request.setAttribute("sortOrder", order);
		}
		
		// If there were errors then return to the input page else go on
	    if (!errors.isEmpty())
	    {
	    	addErrors(request, errors);
	    	forward = new ActionForward(mapping.getInput());
	    }
	    else
	    {
			forward = mapping.findForward("success");
	    }
		
		return forward;
	}
	
	/**
	 * The sortAscending method will sort the results in ascending order using the column
	 * provided.
	 * <P>
	 * @param results The results to sort
	 * @param column The column to use for comparison
	 * @return The sorted ArrayList
	 */
	private ArrayList<StudyParticipantReport> sortAscending(ArrayList results, String column)
	{
		ArrayList<StudyParticipantReport> newResults = new ArrayList<StudyParticipantReport>();
		
		int maxSize = results.size();
		while(newResults.size() < maxSize)
		{
			int smallestIndex = 0;
			StudyParticipantReport smallest = (StudyParticipantReport)results.get(0);
			for (int i=1; i<results.size(); i++)
			{
				StudyParticipantReport current = (StudyParticipantReport)results.get(i);
				
				// Handle the column subjectid
				if (column.equals("subjectid"))
				{
					boolean currentIdString = false;
					boolean smallestIdString = false;
					long currentId = 0;
					long smallestId = 0;
					
					String currentIdStr = current.getStudySubjectIdentifier();
					try
					{
						currentId = Long.parseLong(currentIdStr);
					}
					catch(NumberFormatException n)
					{
						currentIdString = true;
					}
					
					String smallestIdStr = smallest.getStudySubjectIdentifier();
					try
					{
						smallestId = Integer.parseInt(smallestIdStr);
					}
					catch(NumberFormatException n)
					{
						smallestIdString = true;
					}
					
					// Handle the different cases of number and string
					if ((!currentIdString) && smallestIdString)
					{
						// If we are number but they are string we are smaller
						smallest = current;
						smallestIndex = i;
					}
					else if (currentIdString && smallestIdString)
					{
						// If we are both strings then let String compare them
						if(currentIdStr.compareTo(smallestIdStr) < 0)
						{
							smallest = current;
							smallestIndex = i;
						}
					}
					else if ((!currentIdString) && (!smallestIdString))
					{
						// If we are both numbers then do the compare ourselves
						if (currentId < smallestId)
						{
							smallest = current;
							smallestIndex = i;
						}
					}
				}
				
				// Handle the column gender
				if (column.equals("gender"))
				{
					String currentGender = current.getAdministrativeGenderCode();
					String smallestGender = smallest.getAdministrativeGenderCode();
					if (currentGender.compareTo(smallestGender) < 0)
					{
						smallest = current;
						smallestIndex = i;
					}
				}
				
				// Handle the column age
				if (column.equals("age"))
				{
					String currentAge = current.getAgeAtEnrollment();
					String smallestAge = smallest.getAgeAtEnrollment();
					if (currentAge.compareTo(smallestAge) < 0)
					{
						smallest = current;
						smallestIndex = i;
					}
				}
				
				// Handle the column affection
				if (column.equals("affection"))
				{
					String currentAffection = current.getCaseControlStatus();
					String smallestAffection = smallest.getCaseControlStatus();
					if (currentAffection.compareTo(smallestAffection) < 0)
					{
						smallest = current;
						smallestIndex = i;
					}
				}
				
				// Handle the column history
				if (column.equals("history"))
				{
					String currentHistory = current.getFamilyHistory();
					String smallestHistory = smallest.getFamilyHistory();
					if (currentHistory.compareTo(smallestHistory) < 0)
					{
						smallest = current;
						smallestIndex = i;
					}
				}
				
				// Handle the column population
				if (column.equals("population"))
				{
					Set<String> populationSet = current.getPopulationNameCollection();
					if (populationSet != null){
						for(String populationName: populationSet){
							String currentPop = populationName;					
							String smallestPop = smallest.getCaseControlStatus();
							if (currentPop.compareTo(smallestPop) < 0)
							{
								smallest = current;
								smallestIndex = i;
							}
						}
					}					
				}
			}
			results.remove(smallestIndex);
			newResults.add(smallest);
		}
		
		return newResults;
	}
	
	/**
	 * The sortDescending method will sort the results in descending order using the column
	 * provided.
	 * <P>
	 * @param results The results to sort
	 * @param column The column to use for comparison
	 * @return The sorted ArrayList
	 */
	private ArrayList<StudyParticipantReport> sortDescending(ArrayList results, String column)
	{
		ArrayList<StudyParticipantReport> newResults = new ArrayList<StudyParticipantReport>();
		
		int maxSize = results.size();
		while(newResults.size() < maxSize)
		{
			int largestIndex = 0;
			StudyParticipantReport largest = (StudyParticipantReport)results.get(0);
			for (int i=1; i<results.size(); i++)
			{
				StudyParticipantReport current = (StudyParticipantReport)results.get(i);
				
				// Handle the column subjectid
				if (column.equals("subjectid"))
				{
					boolean currentIdString = false;
					boolean largestIdString = false;
					long currentId = 0;
					long largestId = 0;
					
					String currentIdStr = current.getStudySubjectIdentifier();
					try
					{
						currentId = Long.parseLong(currentIdStr);
					}
					catch(NumberFormatException n)
					{
						currentIdString = true;
					}
					
					String largestIdStr = largest.getStudySubjectIdentifier();
					try
					{
						largestId = Integer.parseInt(largestIdStr);
					}
					catch(NumberFormatException n)
					{
						largestIdString = true;
					}
					
					// Handle the different cases of number and string
					if (currentIdString && (!largestIdString))
					{
						// If we are string but they are number we are larger
						largest = current;
						largestIndex = i;
					}
					else if (currentIdString && largestIdString)
					{
						// If we are both strings then let String compare them
						if(currentIdStr.compareTo(largestIdStr) > 0)
						{
							largest = current;
							largestIndex = i;
						}
					}
					else if ((!currentIdString) && (!largestIdString))
					{
						// If we are both numbers then do the compare ourselves
						if (currentId > largestId)
						{
							largest = current;
							largestIndex = i;
						}
					}
				}
				
				// Handle the column gender
				if (column.equals("gender"))
				{
					String currentGender = current.getAdministrativeGenderCode();
					String largestGender = largest.getAdministrativeGenderCode();
					if (currentGender.compareTo(largestGender) > 0)
					{
						largest = current;
						largestIndex = i;
					}
				}
				
				// Handle the column age
				if (column.equals("age"))
				{
					String currentAge = current.getAgeAtEnrollment();
					String largestAge = largest.getAgeAtEnrollment();
					if (currentAge.compareTo(largestAge) > 0)
					{
						largest = current;
						largestIndex = i;
					}
				}
				
				// Handle the column affection
				if (column.equals("affection"))
				{
					String currentAffection = current.getCaseControlStatus();
					String largestAffection = largest.getCaseControlStatus();
					if (currentAffection.compareTo(largestAffection) > 0)
					{
						largest = current;
						largestIndex = i;
					}
				}
				
				// Handle the column history
				if (column.equals("history"))
				{
					String currentHistory = current.getFamilyHistory();
					String largestHistory = largest.getFamilyHistory();
					if (currentHistory.compareTo(largestHistory) > 0)
					{
						largest = current;
						largestIndex = i;
					}
				}
				
				// Handle the column population
				if (column.equals("population"))
				{
					Set<String> populationSet = current.getPopulationNameCollection();
					if (populationSet != null){
						for(String populationName: populationSet){
							String currentPop = populationName;
							String largestPop = largest.getCaseControlStatus();
							if (currentPop.compareTo(largestPop) > 0)
							{
								largest = current;
								largestIndex = i;
							}
						}
					}
				}
			}
			results.remove(largestIndex);
			newResults.add(largest);
		}
		
		return newResults;
	}

}
