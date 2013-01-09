<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div class="divHeader">
	<h3>
	Study: <% out.println(session.getAttribute("studyName")); %>
	<br>
	Version: <% out.println(session.getAttribute("studyVersion")); %>
	</h3>

	<bean:message key="header.searchSubject"/>
</div>

<div id="searchForm" class="divMain">
<script type="text/javascript">Help.insertHelp("cagwas_search_subject_data_help", "", "padding:8px;float:right;");</script>
<html:form action="searchSubject.do" method="post" styleId="searchSubjForm">
	<logic:iterate id="analysisGroup" name="analysisGroups" type="gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisGroup">
		<html:hidden indexed="false" name="analysisGroup" property="description"/>
	</logic:iterate>

	<h4 align="left">
	<table id="" width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="150">
				<html:radio property="queryType" value="population" styleClass="radio">
					<bean:message key="radio.genotype.population"/>
				</html:radio>
			</td>
			<td width="300">
				<html:select property="population" onchange="checkRadio($('searchSubjForm').queryType[0])">
					<html:option value=""/>
					<html:options collection="populationCol" property="name" labelProperty="name"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td width="150">
				<html:radio property="queryType" value="analysisGroup" styleClass="radio">
					<bean:message key="radio.analysisGroup"/>
				</html:radio>
			</td>
			<td width="300">
				<html:select property="analysisGroup" onchange="changeDescriptionText(this, 'Subject');checkRadio($('searchSubjForm').queryType[1]);">
					<html:option value=""/>
					<html:options collection="analysisGroups" property="name" labelProperty="name"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div id="abstractTxtHeader">
					<bean:message key="label.analysisGroup.description"/>
				</div>
				<div id="abstractTxt">This is a context-sensitive description for the analysis group selected in the above drop down.</div>
			</td>
		</tr>
	</table>
	
<h3 class="ur">AND</h3>

	<table id="" width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0">
		<tr class="e">
			<td width=200><bean:message key="label.gender"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td width=80>
				<html:select property="gender">
					<html:option value=""/>
					<html:option value="MALE"/>
					<html:option value="FEMALE"/>
				</html:select>
			</td>
			<td><bean:message key="label.and"/></td>
		</tr>
		<tr class="o">
			<td><bean:message key="label.age"/></td>
			<td width=60><bean:message key="label.age.lower"/></td>
			<td width=80>
				<html:select property="lowerAge">
					<html:option value=""/>
					<html:options collection="lowerAges" property="value" labelProperty="label"/>
			    	</html:select>
			</td>
			<td width=60><bean:message key="label.age.upper"/></td>
			<td width=80>
				<html:select property="upperAge">
					<html:option value=""/>
					<html:options collection="upperAges" property="value" labelProperty="label"/>
				</html:select>
			</td>
			<td><bean:message key="label.and"/></td>
		</tr>
		<tr class="e">
			<td><bean:message key="label.case.control"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<html:select property="caseControl">
					<html:option value=""/>
					<html:options collection="caseControls" property="value" labelProperty="label"/>
				</html:select>
			</td>
			<td><bean:message key="label.and"/></td>
		</tr>
		<tr class="o">
			<td><bean:message key="label.family.history"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<html:select property="familyHistory">
					<html:option value=""/>
					<html:option value="YES"/>
					<html:option value="NO"/>
				</html:select>
			</td>
			<td></td>
		</tr>
	</table>
	</h4>
	<div class="buttonsBar">
	<br/>
		<html:button property="btnReset" onclick="clearSubjectForm()"><bean:message key="button.reset"/></html:button>
		<html:submit onclick="showWait()"/>
	</div>
</html:form>
</div>
<div id="wait" align="center" style="DISPLAY:none">
<img id="progress" src="images/cagwas_loader_anim.gif"/>
<H3><bean:message key="label.wait"/></H3>
</div>