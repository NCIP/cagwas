package gov.nih.nci.cgems.web.action;

import gov.nih.nci.cgems.application.zip.ZipFindingsHelper;
import gov.nih.nci.cgems.reports.SNPAssociationFindingReport;

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
 * The SaveAssociationsAction class is called when save results is selected
 * from the Associations report for results < 500.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SaveAssociationsAction extends Action
{
	private static Logger logger = Logger.getLogger(SaveAssociationsAction.class);
	
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
		ArrayList results = (ArrayList)request.getSession().getAttribute("assoc.results");
		String studyName = (String)request.getSession().getAttribute("studyName");
		String caseStudyName = (String)request.getSession().getAttribute("caseStudyName");

		
		if (results != null)
		{
			logger.debug("Saving association report");
			
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment; filename=associations.txt");
			PrintWriter out = response.getWriter();
			
			// First write out the headers
			out.print(ZipFindingsHelper.getCgemsProperties("table.header.snpId")+"\t"+
			        ZipFindingsHelper.getCgemsProperties("table.header.chromosome")+"\t"+
			        ZipFindingsHelper.getCgemsProperties("table.header.position")+"\t"+
			        ZipFindingsHelper.getCgemsProperties("table.header.genes")+"\t"+
			        ZipFindingsHelper.getCgemsProperties("table.header.analysis")+"\t"+
			        ZipFindingsHelper.getCgemsProperties("table.header.pvalue")+"\t"+
			        ZipFindingsHelper.getCgemsProperties("table.header.rank")+"\t");
			
			// Handle the case study specific case
			if (studyName.equals(caseStudyName))
			{
				out.print(ZipFindingsHelper.getCgemsProperties("table.header.eor")+" "+
						ZipFindingsHelper.getCgemsProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCgemsProperties("table.header.eor")+" "+
				        ZipFindingsHelper.getCgemsProperties("table.header.homozygote.risk")+"\n");
			}
			else
			{
				out.print(ZipFindingsHelper.getCgemsProperties("table.header.nonaggressive")+" "+
						ZipFindingsHelper.getCgemsProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCgemsProperties("table.header.nonaggressive")+" "+
						ZipFindingsHelper.getCgemsProperties("table.header.homozygote.risk")+"\t"+
						ZipFindingsHelper.getCgemsProperties("table.header.aggressive")+" "+
						ZipFindingsHelper.getCgemsProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCgemsProperties("table.header.aggressive")+" "+
						ZipFindingsHelper.getCgemsProperties("table.header.homozygote.risk")+"\n");
			}
			
			// Then write out the results
			for (Iterator iterator = results.iterator(); iterator.hasNext();)
            {
				SNPAssociationFindingReport snpAssociationFindingReport = (SNPAssociationFindingReport) iterator.next();
				out.print(snpAssociationFindingReport.getDbsnpId()+"\t"+
						snpAssociationFindingReport.getChromosomeName()+"\t"+
						snpAssociationFindingReport.getChromosomeLocation()+"\t"+
						ZipFindingsHelper.getGeneBiomarkers(snpAssociationFindingReport.getGeneBiomarkerCollection())+"\t"+
						snpAssociationFindingReport.getSnpAssociationAnalysisName()+"\t"+
						snpAssociationFindingReport.getPvalue()+"\t"+
						snpAssociationFindingReport.getRank()+"\t");
				
				// Handle the case study specific case
				if (studyName.equals(caseStudyName))
				{
					out.print(snpAssociationFindingReport.getCaseHeterozygote()+"\t"+
		        			snpAssociationFindingReport.getCaseHomozygote()+"\n");
				}
				else
				{
					out.print(snpAssociationFindingReport.getNonaggressiveHeterozygote()+"\t"+
		        			snpAssociationFindingReport.getNonaggressiveHomozygote()+"\t"+
		        			snpAssociationFindingReport.getAggressiveHeterozygote()+"\t"+
		        			snpAssociationFindingReport.getAggressiveHomozygote()+"\n");
				}
            }
			
			out.close();
		}
		
		return null;
	}

}
