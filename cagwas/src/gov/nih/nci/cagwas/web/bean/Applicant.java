package gov.nih.nci.cagwas.web.bean;

/**
 * The Applicant class contains all the information for a user request for an
 * account.
 * <P>
 * @author mholck
 */
public class Applicant
{
	private String legalName = "";
	private String department = "";
	private String division = "";
	private String street1 = "";
	private String street2 = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	private String country = "";
	private String piFirstName = "";
	private String piLastName = "";
	private String piMiddleName = "";
	private String piPrefix = "";
	private String piSuffix = "";
	private String piPosition = "";
	private String piOrganization = "";
	private String piDepartment = "";
	private String piDivision = "";
	private String piStreet1 = "";
	private String piStreet2 = "";
	private String piCity = "";
	private String piState = "";
	private String piZipcode = "";
	private String piCountry = "";
	private String piPhone = "";
	private String piFax = "";
	private String piEmail = "";
	private String aorFirstName = "";
	private String aorLastName = "";
	private String aorMiddleName = "";
	private String aorPrefix = "";
	private String aorSuffix = "";
	private String aorPosition = "";
	private String aorOrganization = "";
	private String aorDepartment = "";
	private String aorDivision = "";
	private String aorStreet1 = "";
	private String aorStreet2 = "";
	private String aorCity = "";
	private String aorState = "";
	private String aorZipcode = "";
	private String aorCountry = "";
	private String aorPhone = "";
	private String aorFax = "";
	private String aorEmail = "";
	private String applicationType = "";
	private String projectName = "";
	private String ducName = "";
	private String ducDate = "";
	private String ducTitle = "";
	private String statementOfIntent = "";
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public void setPiFirstName(String piFirstName) {
		this.piFirstName = piFirstName;
	}
	public void setPiLastName(String piLastName) {
		this.piLastName = piLastName;
	}
	public void setPiMiddleName(String piMiddleName) {
		this.piMiddleName = piMiddleName;
	}
	public void setPiPrefix(String piPrefix) {
		this.piPrefix = piPrefix;
	}
	public void setPiSuffix(String piSuffix) {
		this.piSuffix = piSuffix;
	}
	public String getPiName()
	{
		return piPrefix + " " + piFirstName + " " + piMiddleName + " " +
			piLastName + " " + piSuffix;
	}
	public String getPiCity() {
		return piCity;
	}
	public void setPiCity(String piCity) {
		this.piCity = piCity;
	}
	public String getPiCountry() {
		return piCountry;
	}
	public void setPiCountry(String piCountry) {
		this.piCountry = piCountry;
	}
	public String getPiDepartment() {
		return piDepartment;
	}
	public void setPiDepartment(String piDepartment) {
		this.piDepartment = piDepartment;
	}
	public String getPiDivision() {
		return piDivision;
	}
	public void setPiDivision(String piDivision) {
		this.piDivision = piDivision;
	}
	public String getPiEmail() {
		return piEmail;
	}
	public void setPiEmail(String piEmail) {
		this.piEmail = piEmail;
	}
	public String getPiFax() {
		return piFax;
	}
	public void setPiFax(String piFax) {
		this.piFax = piFax;
	}
	public String getPiOrganization() {
		return piOrganization;
	}
	public void setPiOrganization(String piOrganization) {
		this.piOrganization = piOrganization;
	}
	public String getPiPhone() {
		return piPhone;
	}
	public void setPiPhone(String piPhone) {
		this.piPhone = piPhone;
	}
	public String getPiPosition() {
		return piPosition;
	}
	public void setPiPosition(String piPosition) {
		this.piPosition = piPosition;
	}
	public String getPiState() {
		return piState;
	}
	public void setPiState(String piState) {
		this.piState = piState;
	}
	public String getPiStreet1() {
		return piStreet1;
	}
	public void setPiStreet1(String piStreet1) {
		this.piStreet1 = piStreet1;
	}
	public String getPiStreet2() {
		return piStreet2;
	}
	public void setPiStreet2(String piStreet2) {
		this.piStreet2 = piStreet2;
	}
	public String getPiZipcode() {
		return piZipcode;
	}
	public void setPiZipcode(String piZipcode) {
		this.piZipcode = piZipcode;
	}
	public String getAorCity() {
		return aorCity;
	}
	public void setAorCity(String aorCity) {
		this.aorCity = aorCity;
	}
	public String getAorCountry() {
		return aorCountry;
	}
	public void setAorCountry(String aorCountry) {
		this.aorCountry = aorCountry;
	}
	public String getAorDepartment() {
		return aorDepartment;
	}
	public void setAorDepartment(String aorDepartment) {
		this.aorDepartment = aorDepartment;
	}
	public String getAorDivision() {
		return aorDivision;
	}
	public void setAorDivision(String aorDivision) {
		this.aorDivision = aorDivision;
	}
	public String getAorEmail() {
		return aorEmail;
	}
	public void setAorEmail(String aorEmail) {
		this.aorEmail = aorEmail;
	}
	public String getAorFax() {
		return aorFax;
	}
	public void setAorFax(String aorFax) {
		this.aorFax = aorFax;
	}
	public void setAorFirstName(String aorFirstName) {
		this.aorFirstName = aorFirstName;
	}
	public void setAorLastName(String aorLastName) {
		this.aorLastName = aorLastName;
	}
	public void setAorMiddleName(String aorMiddleName) {
		this.aorMiddleName = aorMiddleName;
	}
	public String getAorOrganization() {
		return aorOrganization;
	}
	public void setAorOrganization(String aorOrganization) {
		this.aorOrganization = aorOrganization;
	}
	public String getAorPhone() {
		return aorPhone;
	}
	public void setAorPhone(String aorPhone) {
		this.aorPhone = aorPhone;
	}
	public String getAorPosition() {
		return aorPosition;
	}
	public void setAorPosition(String aorPosition) {
		this.aorPosition = aorPosition;
	}
	public void setAorPrefix(String aorPrefix) {
		this.aorPrefix = aorPrefix;
	}
	public String getAorState() {
		return aorState;
	}
	public void setAorState(String aorState) {
		this.aorState = aorState;
	}
	public String getAorStreet1() {
		return aorStreet1;
	}
	public void setAorStreet1(String aorStreet1) {
		this.aorStreet1 = aorStreet1;
	}
	public String getAorStreet2() {
		return aorStreet2;
	}
	public void setAorStreet2(String aorStreet2) {
		this.aorStreet2 = aorStreet2;
	}
	public void setAorSuffix(String aorSuffix) {
		this.aorSuffix = aorSuffix;
	}
	public String getAorZipcode() {
		return aorZipcode;
	}
	public void setAorZipcode(String aorZipcode) {
		this.aorZipcode = aorZipcode;
	}
	public String getAorName()
	{
		return aorPrefix + " " + aorFirstName + " " + aorMiddleName + " " +
			aorLastName + " " + aorSuffix;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDucDate() {
		return ducDate;
	}
	public void setDucDate(String ducDate) {
		this.ducDate = ducDate;
	}
	public String getDucName() {
		return ducName;
	}
	public void setDucName(String ducName) {
		this.ducName = ducName;
	}
	public String getDucTitle() {
		return ducTitle;
	}
	public void setDucTitle(String ducTitle) {
		this.ducTitle = ducTitle;
	}
	public String getStatementOfIntent() {
		return statementOfIntent;
	}
	public void setStatementOfIntent(String statementOfIntent) {
		this.statementOfIntent = statementOfIntent;
	}
	
	@Override
	public String toString()
	{
		String msg = "";
		
		msg += "Application Type: " + applicationType + "\n";
		msg += "Applicants project: " + projectName + "\n";
		
		// Append the applicant information on
		msg += "\nApplicant:\n";
		msg += legalName + "\n";
		msg += department + " " + division + "\n";
		msg += street1 + " " + street2 + "\n";
		msg += city + "," + state + " " + zipcode + " " + country + "\n";
		
		// Append the PI information
		msg += "\nProject Director/Principal Investigator:\n";
		msg += piPrefix + " " + piFirstName + " " + piMiddleName + " " +
			piLastName + " " + piSuffix + "\n";
		msg += piPosition + " " + piOrganization + "\n";
		msg += piDepartment + " " + piDivision + "\n";
		msg += piStreet1 + " " + piStreet2 + "\n";
		msg += piCity + "," + piState + " " + piZipcode + " " + piCountry + "\n";
		msg += "Phone:" + piPhone + " Fax:" + piFax + " Email:" + piEmail + "\n";
		
		// Append the AOR information
		msg += "\nAuthorized Respresentative:\n";
		msg += aorPrefix + " " + aorFirstName + " " + aorMiddleName + " " +
			aorLastName + " " + aorSuffix + "\n";
		msg += aorPosition + " " + aorOrganization + "\n";
		msg += aorDepartment + " " + aorDivision + "\n";
		msg += aorStreet1 + " " + aorStreet2 + "\n";
		msg += aorCity + "," + aorState + " " + aorZipcode + " " + aorCountry + "\n";
		msg += "Phone:" + aorPhone + " Fax:" + aorFax + " Email:" + aorEmail + "\n";
		
		// Append the soi information
		msg += "\nStatement of Intent:\n";
		msg += statementOfIntent + "\n";
		
		// Append the Data Use Consent
		msg += "\nData Use Consent:\n";
		msg += ducName + " " + ducTitle + " " + ducDate + "\n";
		
		return msg;
	}
}
