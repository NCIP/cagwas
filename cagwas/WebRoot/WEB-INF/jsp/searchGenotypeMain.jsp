<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<div class="divHeader">
<h3 style="text-align:right; width:100%; padding:0px;margin:0px;">Study: <% out.println(session.getAttribute("study")); %></h3>
<bean:message key="header.searchGenotype"/>
</div>

<div id="searchForm" class="divMain">
<script type="text/javascript">Help.insertHelp("cagwas_search_genotype_help", "", "padding:8px;float:right;");</script>

<html:form action="searchGenotype.do" method="post" enctype="multipart/form-data" styleId="searchGenoForm">
	<logic:iterate id="analysisGroup" name="analysisGroups" type="gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisGroup">
		<html:hidden indexed="false" name="analysisGroup" property="description"/>
	</logic:iterate>
<br/>
	<table>
		<tr>
			<td width="150">
				<html:radio property="queryType" value="population" styleClass="radio">
					<bean:message key="radio.genotype.population"/>
				</html:radio>
			</td>
			<td width="300">
				<html:select property="population" onchange="checkRadio($('searchGenoForm').queryType[0])">
					<html:option value=""/>
					<html:options collection="populationCol" property="name" labelProperty="name"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="2"><span class="er"><html:errors property="population"/></span></td>
		</tr>
		<tr>
			<td width="150">
				<html:radio property="queryType" value="analysisGroup" styleClass="radio">
					<bean:message key="radio.analysisGroup"/>
				</html:radio>
			</td>
			<td width="300">
				<html:select property="analysisGroup" onchange="changeDescriptionText(this, 'Genotype');checkRadio($('searchGenoForm').queryType[1])">
					<html:option value=""/>
					<html:options collection="analysisGroups" property="name" labelProperty="name"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="2"><span class="er"><html:errors property="analysisGroup"/></span></td>
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

	<table class="queryTable" id="genderTable" width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="200"><bean:message key="label.gender"/></td>
			<td colspan="3">&nbsp;</td>
			<td width="80">
				<html:select property="gender">
					<html:option value=""/>
					<html:option value="MALE"/>
					<html:option value="FEMALE"/>
				</html:select>
			</td>
			<td><bean:message key="label.and"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.age"/></td>
			<td width="60"><bean:message key="label.age.lower"/></td>
			<td width="80">
				<html:select property="lowerAge">
					<html:option value=""/>
					<html:options collection="lowerAges" property="value" labelProperty="label"/>
			    	</html:select>
			</td>
			<td width="60"><bean:message key="label.age.upper"/></td>
			<td width="80">
				<html:select property="upperAge">
					<html:option value=""/>
					<html:options collection="upperAges" property="value" labelProperty="label"/>
				</html:select>
			</td>
			<td><bean:message key="label.and"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.case.control"/></td>
			<td colspan="3">&nbsp;</td>
			<td>
				<html:select property="caseControl">
					<html:option value=""/>
					<html:options collection="caseControls" property="value" labelProperty="label"/>
				</html:select>
			</td>
			<td><bean:message key="label.and"/></td>
		</tr>
		<tr>
			<td><bean:message key="label.family.history"/></td>
			<td colspan="3">&nbsp;</td>
			<td>
				<html:select property="familyHistory">
					<html:option value=""/>
					<html:option value="YES"/>
					<html:option value="NO"/>
				</html:select>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	
<h3 class="ur">AND</h3>

	<div style="color:#ff0000; padding-left:25px; width:100%">
		<bean:message key="label.annotation.required"/><br/><br/>
		<span class="er"><html:errors property="annotation"/></span>
	</div>

	<table width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0">
		<tr class="o">
			<td><bean:message key="label.panel"/></td>
			<html:hidden property="panelName"/>
			<td>
				<html:select property="panel" onchange="panelName.value=this.options[this.selectedIndex].text;">
					<html:option value=""/>
					<html:options collection="panels" property="value" labelProperty="label"/>
			    </html:select> 
			</td>
		</tr>
	</table>
	
	<table class="queryTable" id="genomicTable" width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr class="e">
			<td width="250"><bean:message key="label.genomicLocation"/></td>
			<td width="100" style="text-align:right"><bean:message key="label.chromosome"/></td>
			<td><html:select property="chromosome">
				<html:option value=""/>
				<html:options collection="chromosomes" property="value" labelProperty="label"/>
			    </html:select>
			</td>
		</tr>
		<tr class="e">
			<td><b class="message"><bean:message key="label.genomicHint"/></b></td>
			<td style="text-align:right"><bean:message key="label.from"/></td>
			<td><html:text property="chromosomeFrom"/>
				<html:select property="fromUnit">
				<html:option value="bp"/>
				<html:option value="kb"/>
				<html:option value="mb"/>
			    </html:select>
			    <br/><span class="er"><html:errors property="chromosomeFrom"/></span>
			</td>
		</tr>
		<tr class="e">
			<td>&nbsp;</td>
			<td style="text-align:right"><bean:message key="label.to"/></td>
			<td>
				<html:text property="chromosomeTo"/>
				<html:select property="toUnit">
				<html:option value="bp"/>
				<html:option value="kb"/>
				<html:option value="mb"/>
			    </html:select>
			    <br/>
			    <span class="er"><html:errors property="chromosomeTo"/></span>
			</td>
		</tr>
		<tr>
			<td colspan="3"><h3 class="ur">AND</h3></td>
		</tr>
		<tr class="o">
			<td rowspan="2">
				<bean:message key="label.hugo"/><br/><br/>
				<b class="message"><bean:message key="label.hugoHint"/></b>	
			</td>
			<td style="text-align:right">Enter Symbols</td>
			<td>
				<html:textarea property="hugoList" cols="35" rows="4"></html:textarea><br/>
			</td> 
		</tr>
		<tr class="o">
			<td style="text-align:right">Upload File</td>
			<td>
				<html:file property="hugoFile"/>
				<br/><span class="er"><html:errors property="hugoFile"/></span>
			</td>
		</tr>
		<tr>
			<td colspan="3"><h3 class="ur">AND</h3></td>
		</tr>
		<tr class="e">
			<td rowspan="2">
				<bean:message key="label.identifier"/><br/><br/>
				<b class="message"><bean:message key="label.identifierHint"/></b>
			</td>
			<td style="text-align:right">Enter IDs</td>
			<td>
				<html:textarea property="idList" cols="35" rows="4"></html:textarea>
			</td>
		</tr>
		<tr class="e">
			<td style="text-align:right">Upload File</td>
			<td>
				<br/><html:file property="idFile"/>
				<br/><span class="er"><html:errors property="idFile"/></span>
			</td>
		</tr>
		
	</table>
	<script type="text/javascript">
	//	Stripe.stripe("genomicTable", "#eee", "#ccc", "false");
		Stripe.stripe("genderTable", "#eee", "#ccc", "false");
	</script>
	
<h3 class="ur">AND</h3>

	<table class="queryTable" id="" width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr class="o">
			<td width="250"><bean:message key="label.qc"/></td>
			<td width="150">
			    <html:select property="qcStatus">
				<html:option value="QC+"/>
				<html:option value="QC-"/>
				<html:option value=""/>
			    </html:select>
			</td>
		</tr>
	</table>

	<div class="buttonsBar">
		<html:button property="btnReset" onclick="clearGenotypeForm()"><bean:message key="button.reset"/></html:button>
		<html:submit onclick="showWait()"/>
	</div>
</html:form>
</div>
<div id="wait" align="center" style="DISPLAY:none">
<img id="progress" src="images/cagwas_loader_anim.gif"/>
<H3><bean:message key="label.wait"/></H3>
</div>