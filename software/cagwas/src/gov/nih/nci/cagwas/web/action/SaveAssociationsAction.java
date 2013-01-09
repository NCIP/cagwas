/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.reports.SNPAssociationFindingReport;

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
		String noOfCategories = (String)request.getParameter("NoOfCategories");
		
		if (results != null)
		{
			logger.debug("Saving association report");
			
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment; filename=associations.txt");
			PrintWriter out = response.getWriter();
			
			// First write out the headers
			out.print(ZipFindingsHelper.getCagwasProperties("table.header.snpId")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.chromosome")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.position")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.genes")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.analysis")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.pvalue")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.rank")+"\t");
			
			// Handle the case study specific case
			//if (studyName.equals(caseStudyName))
			if (noOfCategories != null && noOfCategories.equals("0"))
				{
				 out.print("\n");
				}
			 else if (noOfCategories != null && noOfCategories.equals("2"))
			{
				out.print(ZipFindingsHelper.getCagwasProperties("table.header.eor")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.eor")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.homozygote.risk")+"\n");
			}
			else
			{
				out.print(ZipFindingsHelper.getCagwasProperties("table.header.nonaggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.nonaggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.homozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.aggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.heterozygote.risk")+"\t"+
						ZipFindingsHelper.getCagwasProperties("table.header.aggressive")+" "+
						ZipFindingsHelper.getCagwasProperties("table.header.homozygote.risk")+"\n");
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
				//if (studyName.equals(caseStudyName))
				 if (noOfCategories != null && noOfCategories.equals("0"))
					{
					 out.print("\n");
					}
				 else if (noOfCategories != null && noOfCategories.equals("2"))
				{
					out.print(snpAssociationFindingReport.getCaseHeterozygote()+" "+snpAssociationFindingReport.getCaseHeterozygoteConfidence()+"\t"+
		        			snpAssociationFindingReport.getCaseHomozygote()+" "+snpAssociationFindingReport.getCaseHomozygoteConfidence()+"\n");
				}
				else
				{
					out.print(snpAssociationFindingReport.getNonaggressiveHeterozygote()+" "+snpAssociationFindingReport.getNonaggressiveHeterozygoteConfidence()+"\t"+
		        			snpAssociationFindingReport.getNonaggressiveHomozygote()+" "+snpAssociationFindingReport.getNonaggressiveHomozygoteConfidence()+"\t"+
		        			snpAssociationFindingReport.getAggressiveHeterozygote()+" "+snpAssociationFindingReport.getAggressiveHeterozygoteConfidence()+"\t"+
		        			snpAssociationFindingReport.getAggressiveHomozygote()+" "+snpAssociationFindingReport.getAggressiveHomozygoteConfidence()+"\n");
				}
            }
			
			out.close();
		}
		
		return null;
	}

}
