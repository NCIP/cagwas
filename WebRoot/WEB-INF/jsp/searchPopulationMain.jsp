<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div id="searchPopHeader">
	<h3>
	Study: <% out.println(session.getAttribute("studyName")); %>
	<br>
	Version: <% out.println(session.getAttribute("studyVersion")); %>
	</h3>

	<bean:message key="header.searchPopulation"/>
</div>

<div id="searchForm">
<div id="searchPopMain">
<script type="text/javascript">Help.insertHelp("cagwas_search_population_help", "", "padding:8px;float:right;");</script>
<html:form action="searchPopulation.do" method="post" enctype="multipart/form-data" styleId="searchPopForm">

	<table>
		<tr>
			<td width="150"><bean:message key="label.population"/></td>
			<td><html:select property="population" multiple="true" style="width:500px">
				<html:option value="All"/>
				<html:options collection="populationCol" property="name" labelProperty="name"/>
			    </html:select>
			    <br/><span class="er"><html:errors property="population"/></span>
			</td>
		</tr>
	</table>
	
<h3 class="ur">AND</h3>

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

	<table id="genomicTable" width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0">
		<tr class="e">
			<td width="250"><bean:message key="label.genomicLocation"/></td>
			<td width="100" style="text-align:right"><bean:message key="label.chromosome"/></td>
			<td><html:select property="chromosome">
				<html:option value=""/>
				<html:options collection="chromosomes" property="value" labelProperty="label"/>
			    </html:select>
			    <br/>
			    <span class="er"><html:errors property="chromosome"/></span> 
			</td>
		</tr>
		<tr class="e">
			<td><b class="message"><bean:message key="label.genomicHint"/></b></td>
			<td style="text-align:right"><bean:message key="label.from"/></td>
			<td>
				<html:text property="chromosomeFrom"/>
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
			    <br/><span class="er"><html:errors property="chromosomeTo"/></span>
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
				<html:file property="hugoFile"/><br/>
				<span class="er"><html:errors property="hugoFile"/></span>
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
				<html:textarea property="idList" cols="35" rows="4"></html:textarea><br/>
			</td>
		</tr>
		<tr class="e">
			<td style="text-align:right">Upload File</td>
			<td>
				<html:file property="idFile"/><br/>
				<span class="er"><html:errors property="idFile"/></span>
			</td>
		</tr>
	</table>
	
<h3 class="ur">AND</h3>

	<table class="queryTable" id="" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr class="e">
			<td width=250><bean:message key="label.weinberg"/></td>
			<td width=150>
			    <html:select property="weinbergOperator">
				<html:option value=">="/>
				<html:option value="<="/>
			    </html:select>
			</td>
			<td><html:text property="weinbergValue"/></td>
			<td>and</td>
		</tr>
		<tr class="e">
			<td colspan="4"><span class="er"><html:errors property="weinberg"/></span></td>
		</tr>
		<tr class="o">
			<td width=250><bean:message key="label.allele"/></td>
			<td width=150>
			    <html:select property="alleleOperator">
				<html:option value=">="/>
				<html:option value="<="/>
			    </html:select>
			</td>
			<td><html:text property="alleleValue"/></td>
			<td>and</td>
		</tr>
		<tr class="o">
			<td colspan="4"><span class="er"><html:errors property="allele"/></span></td>
		</tr>
		<tr class="e">
			<td width=250><bean:message key="label.completion"/></td>
			<td width=150>
			    <html:select property="completionOperator">
				<html:option value=">="/>
				<html:option value="<="/>
			    </html:select>
			</td>
			<td><html:text property="completionValue"/></td>
			<td>%</td>
		</tr>
		<tr class="e">
			<td colspan="4"><span class="er"><html:errors property="completion"/></span></td>
		</tr>
	</table>

	<div class="buttonsBar">
		<html:button property="btnReset" onclick="clearPopForm()"><bean:message key="button.reset"/></html:button>
		<html:submit styleClass="btn" onclick="showWait()"/>
	</div>
</html:form>
</div>
</div>
<div id="wait" align="center" style="DISPLAY:none">
<img id="progress" src="images/cagwas_loader_anim.gif"/>
<H3><bean:message key="label.wait"/></H3>
</div>