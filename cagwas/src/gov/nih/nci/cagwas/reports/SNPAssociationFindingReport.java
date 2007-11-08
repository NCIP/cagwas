/**
 * 
 */
package gov.nih.nci.cagwas.reports;

import java.util.Collection;
import java.util.Set;

import gov.nih.nci.caintegrator.domain.analysis.snp.bean.OddsRatio;
import gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker;
import gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation;

/**
 * @author sahnih
 *
 */
public class SNPAssociationFindingReport implements Comparable
{
	private static final String CASE_VS_CONTROL = "CASE_VS_CONTROL";
	private static final String AGGRESSIVE_VS_CONTROL = "AGGRESSIVE_VS_CONTROL";
	private static final String NON_AGGRESSIVE_VS_CONTROL = "NON_AGGRESSIVE_VS_CONTROL";
	private SNPAssociationFinding snpAssociationFinding = null;
	private String nonBlankWhiteSpace = " "; //default
	private SNPAnnotation snpAnnotation = null;
	private Collection<OddsRatio> oddsRatioCollection = null;
	
	/**
	 * @param finding
	 */
	public SNPAssociationFindingReport(SNPAssociationFinding finding) {
		snpAssociationFinding = finding;
		oddsRatioCollection = finding.getOddsRatioCollection();		
		snpAnnotation = finding.getSnpAnnotation();
	}
	/**
	 * @param space
	 * @param finding
	 */
	public SNPAssociationFindingReport(String space, SNPAssociationFinding finding) {
		nonBlankWhiteSpace = space;
		snpAssociationFinding = finding;
		snpAnnotation = finding.getSnpAnnotation();
		oddsRatioCollection = finding.getOddsRatioCollection();
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#getPvalue()
	 */
	public String getPvalue() {
		return (snpAssociationFinding != null &&
				snpAssociationFinding.getPvalue()!= null ?
					snpAssociationFinding.getPvalue().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#getRank()
	 */
	public String getRank() {
		return (snpAssociationFinding != null &&
				snpAssociationFinding.getRank()!= null ?
					snpAssociationFinding.getRank().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#getSnpAssociationAnalysis()#getName()
	 */
	public String getSnpAssociationAnalysisName() {
		return (snpAssociationFinding != null &&
				snpAssociationFinding.getSnpAssociationAnalysis()!= null &&
				snpAssociationFinding.getSnpAssociationAnalysis().getName() != null?
					snpAssociationFinding.getSnpAssociationAnalysis().getName():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation#getChromosomeLocation()
	 */
	public String getChromosomeLocation() {
		return (snpAnnotation != null &&
				snpAnnotation.getChromosomeLocation()!= null ?
					snpAnnotation.getChromosomeLocation().toString():
					nonBlankWhiteSpace)	;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation#getChromosomeName()
	 */
	public String getChromosomeName() {
		return (snpAnnotation != null &&
				snpAnnotation.getChromosomeName()!= null ?
					snpAnnotation.getChromosomeName():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation#getDbsnpId()
	 */
	public String getDbsnpId() {
		return (snpAnnotation != null &&
				snpAnnotation.getDbsnpId()!= null ?
					snpAnnotation.getDbsnpId():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getNonaggressiveHomozygote() {
		if(oddsRatioCollection != null){
			for(OddsRatio oddsRatio:oddsRatioCollection){
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(NON_AGGRESSIVE_VS_CONTROL)){
					return (oddsRatio.getHomozygoteOddsRatio() != null  ?
							oddsRatio.getHomozygoteOddsRatio().toString():
								nonBlankWhiteSpace)	;
				}
			}
		}
		return nonBlankWhiteSpace;
//		return (snpAssociationFinding != null &&
//				snpAssociationFinding.getOrNonaggressiveHomozygote()!= null ?
//					snpAssociationFinding.getOrNonaggressiveHomozygote().toString():
//					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getNonaggressiveHeterozygote() {
		if(oddsRatioCollection != null){
			for(OddsRatio oddsRatio:oddsRatioCollection){
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(NON_AGGRESSIVE_VS_CONTROL))
				{
					return (oddsRatio.getHeterozygoteOddsRatio() != null  ?
							oddsRatio.getHeterozygoteOddsRatio().toString():
								nonBlankWhiteSpace)	;
				}
			}
		}
		return nonBlankWhiteSpace;
//		return (snpAssociationFinding != null &&
//				snpAssociationFinding.getOrNonaggressiveHeterozygote()!= null ?
//					snpAssociationFinding.getOrNonaggressiveHeterozygote().toString():
//					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getAggressiveHomozygote() {
		if(oddsRatioCollection != null){
			for(OddsRatio oddsRatio:oddsRatioCollection){
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(AGGRESSIVE_VS_CONTROL)){
					return (oddsRatio.getHomozygoteOddsRatio() != null  ?
							oddsRatio.getHomozygoteOddsRatio().toString():
								nonBlankWhiteSpace)	;
				}
			}
		}
		return nonBlankWhiteSpace;
//		return (snpAssociationFinding != null &&
//				snpAssociationFinding.getOrAggressiveHomozygote()!= null ?
//					snpAssociationFinding.getOrAggressiveHomozygote().toString():
//					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getAggressiveHeterozygote() {
		if(oddsRatioCollection != null){
			for(OddsRatio oddsRatio:oddsRatioCollection){
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(AGGRESSIVE_VS_CONTROL)){
					return (oddsRatio.getHeterozygoteOddsRatio() != null  ?
							oddsRatio.getHeterozygoteOddsRatio().toString():
								nonBlankWhiteSpace)	;
				}
			}
		}
		return nonBlankWhiteSpace;
//		return (snpAssociationFinding != null &&
//				snpAssociationFinding.getOrAggressiveHeterozygote()!= null ?
//					snpAssociationFinding.getOrAggressiveHeterozygote().toString():
//					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getCaseHomozygote() {
		if(oddsRatioCollection != null){
			for(OddsRatio oddsRatio:oddsRatioCollection){
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(CASE_VS_CONTROL)){
					return (oddsRatio.getHomozygoteOddsRatio() != null  ?
							oddsRatio.getHomozygoteOddsRatio().toString():
								nonBlankWhiteSpace)	;
				}
			}
		}
		return nonBlankWhiteSpace;
//		return (snpAssociationFinding != null &&
//				snpAssociationFinding.getOrCaseHomozygote()!= null ?
//					snpAssociationFinding.getOrCaseHomozygote().toString():
//					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getCaseHeterozygote() {
		if(oddsRatioCollection != null){
			for(OddsRatio oddsRatio:oddsRatioCollection){
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(CASE_VS_CONTROL)){
					return (oddsRatio.getHeterozygoteOddsRatio() != null  ?
							oddsRatio.getHeterozygoteOddsRatio().toString():
								nonBlankWhiteSpace)	;
				}
			}
		}
		return nonBlankWhiteSpace;
//		return (snpAssociationFinding != null &&
//				snpAssociationFinding.getOrCaseHeterozygote()!= null ?
//					snpAssociationFinding.getOrCaseHeterozygote().toString():
//					nonBlankWhiteSpace)	;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getNonaggressiveHomozygoteConfidence()
	{
		if(oddsRatioCollection != null)
		{
			for(OddsRatio oddsRatio:oddsRatioCollection)
			{
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(NON_AGGRESSIVE_VS_CONTROL))
				{
					String lowerConfidence = (oddsRatio.getHomozygoteLowerConfidenceBound() != null  ?
							oddsRatio.getHomozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					String upperConfidence = (oddsRatio.getHomozygoteUpperConfidenceBound() != null  ?
							oddsRatio.getHomozygoteUpperConfidenceBound().toString():
								nonBlankWhiteSpace);
					return "(" + lowerConfidence + "-" + "upperConfidence" + ")";
				}
			}
		}
		return nonBlankWhiteSpace;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getAggressiveHomozygoteConfidence()
	{
		if(oddsRatioCollection != null)
		{
			for(OddsRatio oddsRatio:oddsRatioCollection)
			{
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(AGGRESSIVE_VS_CONTROL))
				{
					String lowerConfidence = (oddsRatio.getHomozygoteLowerConfidenceBound() != null  ?
							oddsRatio.getHomozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					String upperConfidence = (oddsRatio.getHomozygoteUpperConfidenceBound() != null  ?
							oddsRatio.getHomozygoteUpperConfidenceBound().toString():
								nonBlankWhiteSpace);
					return "(" + lowerConfidence + "-" + "upperConfidence" + ")";
				}
			}
		}
		return nonBlankWhiteSpace;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getNonaggressiveHeterozygoteConfidence()
	{
		if(oddsRatioCollection != null)
		{
			for(OddsRatio oddsRatio:oddsRatioCollection)
			{
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(NON_AGGRESSIVE_VS_CONTROL))
				{
					String lowerConfidence = (oddsRatio.getHeterozygoteLowerConfidenceBound() != null  ?
							oddsRatio.getHeterozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					String upperConfidence = (oddsRatio.getHeterozygoteUpperConfidenceBound() != null  ?
							oddsRatio.getHeterozygoteUpperConfidenceBound().toString():
								nonBlankWhiteSpace);
					return "(" + lowerConfidence + "-" + "upperConfidence" + ")";
				}
			}
		}
		return nonBlankWhiteSpace;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getAggressiveHeterozygoteConfidence()
	{
		if(oddsRatioCollection != null)
		{
			for(OddsRatio oddsRatio:oddsRatioCollection)
			{
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(AGGRESSIVE_VS_CONTROL))
				{
					String lowerConfidence = (oddsRatio.getHeterozygoteLowerConfidenceBound() != null  ?
							oddsRatio.getHeterozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					String upperConfidence = (oddsRatio.getHeterozygoteUpperConfidenceBound() != null  ?
							oddsRatio.getHeterozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					return "(" + lowerConfidence + "-" + "upperConfidence" + ")";
				}
			}
		}
		return nonBlankWhiteSpace;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getCaseHomozygoteConfidence()
	{
		if(oddsRatioCollection != null)
		{
			for(OddsRatio oddsRatio:oddsRatioCollection)
			{
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(CASE_VS_CONTROL))
				{
					String lowerConfidence = (oddsRatio.getHomozygoteLowerConfidenceBound() != null  ?
							oddsRatio.getHomozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					String upperConfidence = (oddsRatio.getHomozygoteUpperConfidenceBound() != null  ?
							oddsRatio.getHomozygoteUpperConfidenceBound().toString():
								nonBlankWhiteSpace);
					return "(" + lowerConfidence + "-" + "upperConfidence" + ")";
				}
			}
		}
		return nonBlankWhiteSpace;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding#get()
	 */
	public String getCaseHeterozygoteConfidence()
	{
		if(oddsRatioCollection != null)
		{
			for(OddsRatio oddsRatio:oddsRatioCollection)
			{
				if(oddsRatio.getName()!= null && oddsRatio.getName().equals(CASE_VS_CONTROL))
				{
					String lowerConfidence = (oddsRatio.getHeterozygoteLowerConfidenceBound() != null  ?
							oddsRatio.getHeterozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					String upperConfidence = (oddsRatio.getHeterozygoteUpperConfidenceBound() != null  ?
							oddsRatio.getHeterozygoteLowerConfidenceBound().toString():
								nonBlankWhiteSpace);
					return "(" + lowerConfidence + "-" + "upperConfidence" + ")";
				}
			}
		}
		return nonBlankWhiteSpace;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation#getGeneBiomarkerCollection()
	 */
	public Set<GeneBiomarker> getGeneBiomarkerCollection() {
		return snpAnnotation.getGeneBiomarkerCollection();
	}
	/**
	 * @return Returns the nonBlankWhiteSpace.
	 */
	public String getNonBlankWhiteSpace() {
		return nonBlankWhiteSpace;
	}
	/**
	 * @param nonBlankWhiteSpace The nonBlankWhiteSpace to set.
	 */
	public void setNonBlankWhiteSpace(String nonBlankWhiteSpace) {
		this.nonBlankWhiteSpace = nonBlankWhiteSpace;
	}
	
	/**
	 * The compareTo method is used to compare this object with another and determine
	 * is this is less, equal, or greater than that object.  This is used in sorting
	 * these objects.
	 * <P>
	 * @param o The object to compare this to
	 * @return int
	 */
	public int compareTo(Object o) throws ClassCastException
	{
		int returnValue = 0;
		SNPAssociationFindingReport otherReport;
		boolean currentChromString = false;
		boolean otherChromString = false;
		
		// Must be another SNPAssociationFindingReport object
		if (!(o instanceof SNPAssociationFindingReport))
		    throw new ClassCastException("A SNPAssociationFindingReport object expected.");
		else
			otherReport = (SNPAssociationFindingReport)o;
		
		int currentChromo = 0;
		int otherChromo = 0;
		
		String currentChrom = getChromosomeName();
		try
		{
			currentChromo = Integer.parseInt(currentChrom);
		}
		catch(NumberFormatException n)
		{
			currentChromString = true;
		}
		
		String otherChrom = otherReport.getChromosomeName();
		try
		{
			otherChromo = Integer.parseInt(otherChrom);
		}
		catch(NumberFormatException n)
		{
			otherChromString = true;
		}
		
		// Handle the different cases of number and string
		if (currentChromString && (!otherChromString))
		{
			// If we are a string but the other is not we are greater
			returnValue = 1;
		}
		else if ((!currentChromString) && otherChromString)
		{
			// If we are number but they are string we are less
			returnValue = -1;
		}
		else if (currentChromString && otherChromString)
		{
			// If we are both strings then let String compare them
			returnValue = currentChrom.compareTo(otherChrom);
		}
		else
		{
			// If we are both numbers then do the compare ourselves
			returnValue = currentChromo - otherChromo;
		}
		
		// Now check if we are both the same then sort by location
		if (returnValue == 0)
		{
			long currentLoc = Long.parseLong(getChromosomeLocation());
			long otherLoc = Long.parseLong(otherReport.getChromosomeLocation());
			returnValue = (int)(currentLoc - otherLoc);
		}
		
		return returnValue;
	}
}
