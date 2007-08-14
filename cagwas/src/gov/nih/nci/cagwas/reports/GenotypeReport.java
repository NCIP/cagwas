package gov.nih.nci.cgems.reports;

import gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation;
import gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding;

/**
 * The GenotypeReport class provides the properties needed for the Genotype
 * report.
 * <P>
 * @author mholck
 */
public class GenotypeReport
{
	private GenotypeFinding genotypeFinding = null;
	private String nonBlankWhiteSpace = " "; //default
	private SNPAnnotation snpAnnotation = null;
	
	/**
	 * @param space
	 * @param genotypeFinding
	 */
	public GenotypeReport(String space, GenotypeFinding genotypeFinding)
	{
		nonBlankWhiteSpace = space;
		this.genotypeFinding = genotypeFinding;
		this.snpAnnotation = genotypeFinding.getSnpAnnotation();
	}
	
	/**
	 * @param genotypeFinding
	 */
	public GenotypeReport(GenotypeFinding genotypeFinding)
	{
		this.genotypeFinding = genotypeFinding;
		this.snpAnnotation = genotypeFinding.getSnpAnnotation();
	}
	
	/**
	 * @return Returns the dbSNPid
	 * @see gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation#getDbsnpId()
	 */
	public String getDbsnpId()
	{
		return (snpAnnotation != null &&
				snpAnnotation.getDbsnpId()!= null ?
					snpAnnotation.getDbsnpId():
					nonBlankWhiteSpace)	;
	}
	
	/**
	 * @return Returns the allele1
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getAllele1()
	 */
	public String getAllele1()
	{
		return (genotypeFinding != null && genotypeFinding.getAllele1() != null ? genotypeFinding.getAllele1():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the allele2
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getAllele2()
	 */
	public String getAllele2()
	{
		return (genotypeFinding != null && genotypeFinding.getAllele2() != null ? genotypeFinding.getAllele2():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the quality score
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getQualityScore()
	 */
	public String getQualityScore()
	{
		return (genotypeFinding != null && genotypeFinding.getQualityScore() != null ? genotypeFinding.getQualityScore().toString():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the QC Status
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getStatus()
	 */
	public String getStatus()
	{
		return (genotypeFinding != null && genotypeFinding.getStatus() != null ? genotypeFinding.getStatus():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the normalized x value
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getNormalizedXIntensity()
	 */
	public String getNormalizedXIntensity()
	{
		return (genotypeFinding != null && genotypeFinding.getNormalizedXIntensity() != null ? genotypeFinding.getNormalizedXIntensity().toString():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the normalized y value
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getNormalizedYIntensity()
	 */
	public String getNormalizedYIntensity()
	{
		return (genotypeFinding != null && genotypeFinding.getNormalizedYIntensity() != null ? genotypeFinding.getNormalizedYIntensity().toString():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the raw x value
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getRawXIntensity()
	 */
	public String getRawXIntensity()
	{
		return (genotypeFinding != null && genotypeFinding.getRawXIntensity() != null ? genotypeFinding.getRawXIntensity().toString():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the normalized y value
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getRawYIntensity()
	 */
	public String getRawYIntensity()
	{
		return (genotypeFinding != null && genotypeFinding.getRawYIntensity() != null ? genotypeFinding.getRawYIntensity().toString():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the study participant ID
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getSpecimen()
	 */
	public String getStudyParticipantID()
	{
		return (genotypeFinding != null && genotypeFinding.getSpecimen().getStudyParticipant().getId() != null ?
				genotypeFinding.getSpecimen().getStudyParticipant().getId().toString():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the specimen ID
	 * @see gov.nih.nci.caintegrator.domain.finding.variation.germline.bean.GenotypeFinding#getSpecimen()
	 */
	public String getSpecimenID()
	{
		return (genotypeFinding != null && genotypeFinding.getSpecimen().getId() != null ?
				genotypeFinding.getSpecimen().getId().toString():nonBlankWhiteSpace);
	}
	
	/**
	 * @return Returns the nonBlankWhiteSpace.
	 */
	public String getNonBlankWhiteSpace()
	{
		return nonBlankWhiteSpace;
	}
	
	/**
	 * @param nonBlankWhiteSpace The nonBlankWhiteSpace to set.
	 */
	public void setNonBlankWhiteSpace(String nonBlankWhiteSpace)
	{
		this.nonBlankWhiteSpace = nonBlankWhiteSpace;
	}
}
