/**
 * 
 */
package gov.nih.nci.cagwas.reports;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.util.Set;

import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker;
import gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation;
import gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding;
import gov.nih.nci.caintegrator.domain.study.bean.Population;

/**
 * @author sahnih
 *
 */
public class SNPFrequencyFindingReport implements Comparable
{
	private SNPFrequencyFinding snpFrequencyFinding = null;
	private String nonBlankWhiteSpace = " "; //default
	private SNPAnnotation snpAnnotation = null;
	private Population population = null;
	private StringBuffer alleleFrequency = new StringBuffer();
	private StringBuffer otherFrequency = new StringBuffer();
	private StringBuffer homozygoteFrequency = new StringBuffer();
	private StringBuffer heterozygoteFrequency = new StringBuffer();
	private StringBuffer otherHomozygoteFrequency = new StringBuffer();
	private enum CountType
	{
		HETEROZYGOTE,
		HOMOZYGOTE,
		OTHERZYGOTE,
		ALLELE,
		OTHERALLELE
	}
	
	/**
	 * @param finding
	 */
	public SNPFrequencyFindingReport(SNPFrequencyFinding finding) {
		snpFrequencyFinding = finding;
		snpAnnotation = finding.getSnpAnnotation();
		population = finding.getPopulation();
	}
	/**
	 * @param space
	 * @param finding
	 */
	public SNPFrequencyFindingReport(SNPFrequencyFinding finding,String space) {
		nonBlankWhiteSpace = space;
		snpFrequencyFinding = finding;
		snpAnnotation = finding.getSnpAnnotation();
		population = finding.getPopulation();
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.study.bean.Population#getName()
	 */
	public String getPopulationName() {
		return (population != null && population.getName()!= null ? population.getName(): nonBlankWhiteSpace);
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
	 * @see gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation#getGeneBiomarkerCollection()
	 */
	public Set<GeneBiomarker> getGeneBiomarkerCollection() {
		return snpAnnotation.getGeneBiomarkerCollection();
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getCompletionRate()
	 */
	public String getCompletionRate()
	{
		StringBuffer completionRate = new StringBuffer(nonBlankWhiteSpace);
		if(snpFrequencyFinding != null)
		{
		   DecimalFormat completionFormat = new DecimalFormat("00.########");
		   FieldPosition fieldPosition = new FieldPosition(0);
		   StringBuffer completion = new StringBuffer();
		   completionRate = completionFormat.format((snpFrequencyFinding.getCompletionRate().doubleValue() * 100), completion, fieldPosition);
		}
		return completionRate.toString();
	}
	
	public String getCompletionRateValues()
	{
		String value = null;
		
		if(snpFrequencyFinding != null)
		{
			int n = snpFrequencyFinding.getReferenceHomozygoteCount().intValue();
			n += snpFrequencyFinding.getOtherHomozygoteCount().intValue();
			n += snpFrequencyFinding.getHeterozygoteCount().intValue();
			int m = n;
			m += snpFrequencyFinding.getMissingGenotypeCount().intValue();
			value = "(" + n + "/" + m + ")";
		}
		
		return value;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getHardyWeinbergPValue()
	 */
	public String getHardyWeinbergPValue() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getHardyWeinbergPValue()!= null ?
						snpFrequencyFinding.getHardyWeinbergPValue().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getHeterozygoteCount()
	 */
	public String getHeterozygoteCount() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getHeterozygoteCount()!= null ?
						snpFrequencyFinding.getHeterozygoteCount().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getMinorAlleleFrequency()
	 */
	public String getMinorAlleleFrequency() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getMinorAlleleFrequency()!= null ?
						snpFrequencyFinding.getMinorAlleleFrequency().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getMissingAlleleCount()
	 */
	public String getMissingAlleleCount() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getMissingAlleleCount()!= null ?
						snpFrequencyFinding.getMissingAlleleCount().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getMissingGenotypeCount()
	 */
	public String getMissingGenotypeCount() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getMissingGenotypeCount()!= null ?
						snpFrequencyFinding.getMissingGenotypeCount().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getOtherAllele()
	 */
	public String getOtherAllele() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getOtherAllele()!= null ?
						snpFrequencyFinding.getOtherAllele():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getOtherAlleleCount()
	 */
	public String getOtherAlleleCount() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getOtherAlleleCount()!= null ?
						snpFrequencyFinding.getOtherAlleleCount().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getOtherHomozygoteCount()
	 */
	public String getOtherHomozygoteCount() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getOtherHomozygoteCount()!= null ?
						snpFrequencyFinding.getOtherHomozygoteCount().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getReferenceAllele()
	 */
	public String getReferenceAllele() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getReferenceAllele()!= null ?
						snpFrequencyFinding.getReferenceAllele():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getReferenceAlleleCount()
	 */
	public String getReferenceAlleleCount() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getReferenceAlleleCount()!= null ?
						snpFrequencyFinding.getReferenceAlleleCount().toString():
					nonBlankWhiteSpace)	;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.snpFrequency.bean.SNPFrequencyFinding#getReferenceHomozygoteCount()
	 */
	public String getReferenceHomozygoteCount() {
		return (snpFrequencyFinding != null &&
				snpFrequencyFinding.getReferenceHomozygoteCount()!= null ?
						snpFrequencyFinding.getReferenceHomozygoteCount().toString():
					nonBlankWhiteSpace)	;
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
	 * @return Returns the otherFrequency.
	 */
	public StringBuffer getOtherFrequency() {
		formatAlleleCount(CountType.OTHERALLELE);
		return otherFrequency;
	}
	/**
	 * @return Returns the alleleFrequency.
	 */
	public StringBuffer getAlleleFrequency() {
		formatAlleleCount(CountType.ALLELE);
		return alleleFrequency;
	}
	/**
	 * @return Returns the heterozygoteFrequency.
	 */
	public StringBuffer getHeterozygoteFrequency() {
		formatGenotypeCount(CountType.HETEROZYGOTE);
		return heterozygoteFrequency;
	}
	/**
	 * @return Returns the homozygoteFrequency.
	 */
	public StringBuffer getHomozygoteFrequency()
	{
		formatGenotypeCount(CountType.HOMOZYGOTE);
		return homozygoteFrequency;
	}
	/**
	 * @return Returns the otherHomozygoteFrequency.
	 */
	public StringBuffer getOtherHomozygoteFrequency() {
		formatGenotypeCount(CountType.OTHERZYGOTE);
		return otherHomozygoteFrequency;
	}
	private void formatGenotypeCount(CountType type)
	{
		if(snpFrequencyFinding != null)
		{
		   int homozygoteCount = snpFrequencyFinding.getReferenceHomozygoteCount().intValue();
		   int heterozygoteCount = snpFrequencyFinding.getHeterozygoteCount().intValue();
		   int otherzygoteCount = snpFrequencyFinding.getOtherHomozygoteCount().intValue();
		   int totalCount = homozygoteCount + heterozygoteCount + otherzygoteCount;
		   double homoFrequency = ((double)homozygoteCount / (double)totalCount);
		   double heteroFrequency = ((double)heterozygoteCount / (double)totalCount);
		   double otherzygoteFrequency = ((double)otherzygoteCount / (double)totalCount);
		   FieldPosition fieldPosition = new FieldPosition(0);
		   DecimalFormat decimalFormat = new DecimalFormat("0.###");
		   if (type == CountType.HOMOZYGOTE)
		   {
			   homozygoteFrequency = new StringBuffer();
			   decimalFormat.format(homoFrequency , homozygoteFrequency , fieldPosition);
		   }
		   if (type == CountType.HETEROZYGOTE)
		   {
			   heterozygoteFrequency = new StringBuffer();
			   decimalFormat.format(heteroFrequency , heterozygoteFrequency , fieldPosition);
		   }
		   if (type == CountType.OTHERZYGOTE)
		   {
			   otherHomozygoteFrequency = new StringBuffer();
			   decimalFormat.format(otherzygoteFrequency , otherHomozygoteFrequency , fieldPosition);
		   }
		}
	}
	private  void formatAlleleCount(CountType type)
	{
		if(snpFrequencyFinding != null)
		{
		   FieldPosition fieldPosition = new FieldPosition(0);
		   int alleleCount = snpFrequencyFinding.getReferenceAlleleCount().intValue();
		   int otherCount = snpFrequencyFinding.getOtherAlleleCount().intValue();
		   int total = alleleCount + otherCount;
		   double alleleFreq = ((double)alleleCount / (double)total);
		   double otherFreq = ((double)otherCount / (double)total);
		   DecimalFormat format = new DecimalFormat("0.###");
		   if (type == CountType.ALLELE)
		   {
			   alleleFrequency = new StringBuffer();
			   format.format(alleleFreq , alleleFrequency , fieldPosition);
		   }
		   if (type == CountType.OTHERALLELE)
		   {
			   otherFrequency = new StringBuffer();
			   format.format(otherFreq , otherFrequency , fieldPosition);
		   }
		}
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
		SNPFrequencyFindingReport otherReport;
		boolean currentChromString = false;
		boolean otherChromString = false;
		
		// Must be another SNPAssociationFindingReport object
		if (!(o instanceof SNPFrequencyFindingReport))
		      throw new ClassCastException("A SNPFrequencyFindingReport object expected.");
		else
			otherReport = (SNPFrequencyFindingReport)o;
		
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
