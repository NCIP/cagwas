<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
		
<div class="divHeader">
	<bean:message key="label.registration.submitted"/>
</div>

<div align="center">
	<table>
		<tr>
			<th colspan="6"><bean:message key="table.header.registration.applicant"/></th>
		</tr>
		<tr>
			<td><bean:message key="label.registration.legalname"/></td>
			<td><bean:write name="applicant" property="legalName"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.department"/></td>
			<td width="180"><bean:write name="applicant" property="department"/></td>
			<td><bean:message key="label.registration.division"/></td>
			<td width="180"><bean:write name="applicant" property="division"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.street1"/></td>
			<td><bean:write name="applicant" property="street1"/></td>
			<td><bean:message key="label.registration.street2"/></td>
			<td><bean:write name="applicant" property="street2"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.city"/></td>
			<td><bean:write name="applicant" property="city"/></td>
			<td><bean:message key="label.registration.state"/></td>
			<td><bean:write name="applicant" property="state"/></td>
			<td><bean:message key="label.registration.zipcode"/></td>
			<td><bean:write name="applicant" property="zipcode"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.country"/></td>
			<td><bean:write name="applicant" property="country"/></td>
		</tr>
		<tr>
			<td colspan="2"><bean:message key="label.registration.appType"/></td>
			<td><bean:write name="applicant" property="applicationType"/></td>
		</tr>
		<tr>
			<td colspan="2"><bean:message key="label.registration.project"/></td>
			<td colspan="4"><bean:write name="applicant" property="projectName"/></td>
		</tr>
		<tr>
			<th colspan="6"><bean:message key="table.header.registration.pi"/></th>
		</tr>
		<tr>
			<td><bean:message key="label.registration.name"/></td>
			<td><bean:write name="applicant" property="piName"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.position"/></td>
			<td><bean:write name="applicant" property="piPosition"/></td>
			<td><bean:message key="label.registration.organization"/></td>
			<td><bean:write name="applicant" property="piOrganization"/></td>
		</tr
		<tr>
			<td><bean:message key="label.registration.department"/></td>
			<td><bean:write name="applicant" property="piDepartment"/></td>
			<td><bean:message key="label.registration.division"/></td>
			<td><bean:write name="applicant" property="piDivision"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.street1"/></td>
			<td><bean:write name="applicant" property="piStreet1"/></td>
			<td><bean:message key="label.registration.street2"/></td>
			<td><bean:write name="applicant" property="piStreet2"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.city"/></td>
			<td><bean:write name="applicant" property="piCity"/></td>
			<td><bean:message key="label.registration.state"/></td>
			<td><bean:write name="applicant" property="piState"/></td>
			<td><bean:message key="label.registration.zipcode"/></td>
			<td><bean:write name="applicant" property="piZipcode"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.country"/></td>
			<td><bean:write name="applicant" property="piCountry"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.phone"/></td>
			<td><bean:write name="applicant" property="piPhone"/></td>
			<td><bean:message key="label.registration.fax"/></td>
			<td><bean:write name="applicant" property="piFax"/></td>
			<td><bean:message key="label.registration.email"/></td>
			<td><bean:write name="applicant" property="piEmail"/></td>
		</tr>
		<tr>
			<th colspan="6"><bean:message key="table.header.registration.aor"/></th>
		</tr>
		<tr>
			<td><bean:message key="label.registration.name"/></td>
			<td><bean:write name="applicant" property="aorName"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.position"/></td>
			<td><bean:write name="applicant" property="aorPosition"/></td>
			<td><bean:message key="label.registration.organization"/></td>
			<td><bean:write name="applicant" property="aorOrganization"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.department"/></td>
			<td><bean:write name="applicant" property="aorDepartment"/></td>
			<td><bean:message key="label.registration.division"/></td>
			<td><bean:write name="applicant" property="aorDivision"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.street1"/></td>
			<td><bean:write name="applicant" property="aorStreet1"/></td>
			<td><bean:message key="label.registration.street2"/></td>
			<td><bean:write name="applicant" property="aorStreet2"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.city"/></td>
			<td><bean:write name="applicant" property="aorCity"/></td>
			<td><bean:message key="label.registration.state"/></td>
			<td><bean:write name="applicant" property="aorState"/></td>
			<td><bean:message key="label.registration.zipcode"/></td>
			<td><bean:write name="applicant" property="aorZipcode"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.country"/></td>
			<td><bean:write name="applicant" property="aorCountry"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.registration.phone"/></td>
			<td><bean:write name="applicant" property="aorPhone"/></td>
			<td><bean:message key="label.registration.fax"/></td>
			<td><bean:write name="applicant" property="aorFax"/></td>
			<td><bean:message key="label.registration.email"/></td>
			<td><bean:write name="applicant" property="aorEmail"/></td>
		</tr>
		<tr>
			<th colspan="6"><bean:message key="table.header.registration.soi"/></th>
		</tr>
		<tr>
			<td colspan="6"><bean:write name="applicant" property="statementOfIntent"/></td>
		</tr>
		<tr>
			<th colspan="6"><bean:message key="table.header.registration.duc"/></th>
		</tr>
		<tr>
			<td><bean:message key="label.registration.name"/></td>
			<td><bean:write name="applicant" property="ducName"/></td>
			<td><bean:message key="label.registration.title"/></td>
			<td><bean:write name="applicant" property="ducTitle"/></td>
			<td><bean:message key="label.registration.date"/></td>
			<td><bean:write name="applicant" property="ducDate"/></td>
		</tr>
	</table>
</div>