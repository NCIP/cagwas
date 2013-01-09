/*L
 * Copyright SAIC.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cagwas/LICENSE.txt for details.
 */

package gov.nih.nci.cagwas.web.action;

import gov.nih.nci.cagwas.application.zip.ZipFindingsHelper;
import gov.nih.nci.cagwas.application.zip.ZipSNPFrequencyFinding;
import gov.nih.nci.cagwas.reports.SNPFrequencyFindingReport;

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
 * The SavePopulationAction class is called when save results is selected
 * from the Population Frequency report for results < 500.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.Action
 */
public class SavePopulationAction extends Action
{
	private static Logger logger = Logger.getLogger(SavePopulationAction.class);
	
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
		ArrayList results = (ArrayList)request.getSession().getAttribute("pop.results");
		
		if (results != null)
		{
			logger.debug("Saving population frequency report");
			
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment; filename=frequency.txt");
			PrintWriter out = response.getWriter();
			
			// First write out the headers
			out.print(ZipFindingsHelper.getCagwasProperties("table.header.snpId")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.chromosome")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.position")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.genes")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.population")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.completion")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.weinberg")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.allele")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.allele.count")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.genotype")+"\t"+
			        ZipFindingsHelper.getCagwasProperties("table.header.genotype.count")+"\n");
			
			// Then write out the results
			for (Iterator iterator = results.iterator(); iterator.hasNext();)
            {
				SNPFrequencyFindingReport snpFrequencyFindingReport = (SNPFrequencyFindingReport) iterator.next();
				out.print(snpFrequencyFindingReport.getDbsnpId()+"\t"+
	       			snpFrequencyFindingReport.getChromosomeName()+"\t"+
	       			snpFrequencyFindingReport.getChromosomeLocation()+"\t"+
	       			ZipFindingsHelper.getGeneBiomarkers(snpFrequencyFindingReport.getGeneBiomarkerCollection())+"\t"+
	       			snpFrequencyFindingReport.getPopulationName()+"\t"+
	       			snpFrequencyFindingReport.getCompletionRate()+ "% " + snpFrequencyFindingReport.getCompletionRateValues() +"\t"+
	       			snpFrequencyFindingReport.getHardyWeinbergPValue()+"\t"+
	       			snpFrequencyFindingReport.getReferenceAllele()+ "|" + snpFrequencyFindingReport.getOtherAllele()+"\t"+
	       			ZipSNPFrequencyFinding.getAlleleCount(snpFrequencyFindingReport)+"\t"+
	       			snpFrequencyFindingReport.getReferenceAllele()+snpFrequencyFindingReport.getReferenceAllele()+"|"+
	       			snpFrequencyFindingReport.getReferenceAllele()+snpFrequencyFindingReport.getOtherAllele()+"|"+
	       			snpFrequencyFindingReport.getOtherAllele()+snpFrequencyFindingReport.getOtherAllele()+"\t"+
	       			ZipSNPFrequencyFinding.getGenotypeCount(snpFrequencyFindingReport)+"\n");
            }
			
			out.close();
		}
		
		return null;
	}

}
