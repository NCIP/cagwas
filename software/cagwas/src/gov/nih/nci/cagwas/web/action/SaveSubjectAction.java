package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.reports.StudyParticipantReport;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * The SaveSubjectAction class is called when save results is selected
 * from the Subjects report for results < 500.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SaveSubjectAction extends Action
{
	private static Logger logger = Logger.getLogger(SaveSubjectAction.class);
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ArrayList results = (ArrayList)request.getSession().getAttribute("subj.results");
		
		if (results != null)
		{
			logger.debug("Saving subjects report");
			
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment; filename=subjects.txt");
			PrintWriter out = response.getWriter();
			
			// First write out the headers
			out.print(ZipFindingsHelper.getCagwasProperties("table.header.participant")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.gender")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.age")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.affection")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.history")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.population")+"\n");
			
			// Then write out the results
			for (Iterator iterator = results.iterator(); iterator.hasNext();)
            {
				StudyParticipantReport report = (StudyParticipantReport) iterator.next();
	           	out.print(report.getStudySubjectIdentifier()+"\t"+
	       			report.getAdministrativeGenderCode()+"\t"+
	       			report.getAgeAtEnrollment()+"\t"+
	       			report.getCaseControlStatus()+"\t"+
	       			report.getFamilyHistory()+"\t"+
	       			ZipFindingsHelper.getPopulations(report.getPopulationNameCollection())+"\n");
            }
			
			out.close();
		}
		
		return null;
	}

}
