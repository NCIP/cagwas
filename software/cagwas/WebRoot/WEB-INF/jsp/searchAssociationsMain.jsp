<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div id="searchAssocHeader">
	<h3>
	Study: <% out.println(session.getAttribute("studyName")); %>
	<br>
	Version: <% out.println(session.getAttribute("studyVersion")); %>
	</h3>
	
	<bean:message key="header.searchAssociations"/>
</div>

<div id="searchForm">
<div id="searchAssocMain">

<script type="text/javascript">Help.insertHelp("cagwas_search_association_help", "", "padding:8px;float:right;");</script>

<html:form action="searchAssociations.do" styleId="searchAssocForm" method="post" enctype="multipart/form-data">
	
	<table>
		<%
			String fileName = (String)session.getAttribute("assocFileName");
		%>
		<tr colspan="3">Analysis Name: <a href="getRemotePDF.do?file=<%=fileName%>" target="_blank">[Analysis Description (PDF)]</a></tr>

		<%
			Integer numMethodTypes = (Integer)session.getAttribute("methodTypeNum");
			String number = numMethodTypes.toString();
		%>
		<html:hidden property="numAnalysisMethods" value="<%=number%>"/>
		<%

			int offset = 0;

			for (int i = 1; i <= numMethodTypes.intValue(); i++)
			{
				String label = (String)session.getAttribute("methodType" + i) + ":";
				String propertyName = "analysisMethod" + i;
				String collectionName = "methodList" + i;
				String abstractLabel = (String)session.getAttribute("methodType" + i) + " Abstract:";
				String abstractDiv = "methodAbstractTxt" + i;
				String descName = "methods" + i;
				String jsMethod = "changeAnalysisAbstract(this, " + i + "," + offset + ")";
				java.util.Collection methodList = (java.util.Collection)session.getAttribute(collectionName);
				offset = offset + methodList.size();
				String defaultDesc = (String)session.getAttribute("methodDesc" + i);
		%>
			<logic:iterate id="method" name="<%=descName%>" type="gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisMethod">
				<html:hidden indexed="false" name="method" property="methodDescription"/>
			</logic:iterate>
			<tr>
				<td width="50"></td>
				<td align="right"><%=label%></td>
				<td>
					<html:select property="<%=propertyName%>" styleClass="analysisDD" onchange="<%=jsMethod%>" onmouseover="calcula(this.value)">
						<html:options collection="<%=collectionName%>" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td>
					<div id="abstractTxtHeader">
						<%=abstractLabel%>
					</div>
					<div id="<%=abstractDiv%>"><%=defaultDesc%></div>
				</td>
			</tr>
		<%
			}
		%>
	</table>
	
	<h3 class="ur">AND</h3>

	<table width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0">
		<tr class="e">
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
	
	<table class="queryTable" id="genomicTable" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr class="o">
			<td width="250"><bean:message key="label.genomicLocation"/></td>
			<td width="100" style="text-align:right"><bean:message key="label.chromosome"/></td>
			<td>
				<html:select property="chromosome">
					<html:option value=""/>
					<html:options collection="chromosomes" property="value" labelProperty="label"/>
			    </html:select>
			   <br/>
			    <span class="er"><html:errors property="chromosome"/></span> 
			</td>
		</tr>
		<tr class="o">
			<td><b class="message"><bean:message key="label.genomicHint"/></b></td>
			<td style="text-align:right"><bean:message key="label.from"/></td>
			<td>
				<html:text property="chromosomeFrom"/>
				<html:select property="fromUnit">
				<html:option value="bp"/>
				<html:option value="kb"/>
				<html:option value="mb"/>
			    </html:select>
			    <br/>
			    <span class="er"><html:errors property="chromosomeFrom"/></span>
			</td>
		</tr>
		<tr class="o">
			<td>&nbsp;</td>
			<td style="text-align:right"><bean:message key="label.to"/></td>
			<td>
				<html:text property="chromosomeTo"/>
				<html:select property="toUnit">
				<html:option value="bp"/>
				<html:option value="kb"/>
				<html:option value="mb"/>
			    </html:select>
			   <br/> <span class="er"><html:errors property="chromosomeTo"/></span>
			</td>
		</tr>
		<tr>
			<td colspan="3"><h3 class="ur">AND</h3></td>
		</tr>
		<tr class="e">
			<td rowspan="2">
				<bean:message key="label.hugo"/><br/><br/>
				<b class="message"><bean:message key="label.hugoHint"/></b>
			</td>
			<td style="text-align:right">
				Enter Symbols
			</td>
			<td>	
				<html:textarea property="hugoList" cols="35" rows="4"></html:textarea><br/>
			</td>
		</tr>
		<tr class="e">
			<td style="text-align:right">Upload File</td>
			<td>
				<html:file property="hugoFile"/>
				<br/><span class="er"><html:errors property="hugoFile"/></span>
			</td>
		
		</tr>
		<tr>
			<td colspan="3"><h3 class="ur">AND</h3></td>
		</tr>
		<tr class="o">
			<td rowspan="2">
				<bean:message key="label.identifier"/><br/><br/>
				<b class="message"><bean:message key="label.identifierHint"/></b>
			</td>
			<td style="text-align:right">Enter IDs</td>
			<td>
				<html:textarea property="idList" cols="35" rows="4"></html:textarea><br/>
			</td>
		</tr>
		<tr class="o">
			<td style="text-align:right">Upload File:</td>
			<td>
				<html:file property="idFile"/>
				<br/><span class="er"><html:errors property="idFile"/></span>
			</td>
		</tr>
	</table>
	

	<h3 class="ur">AND</h3>
	
	<table class="queryTable" id="" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr class="e">
			<td width="250"><html:radio property="association" value="pvalue" styleClass="radio">
					<bean:message key="radio.pvalue"/>
			    </html:radio>
			</td>
			<td width="150">&lt;=</td>
			<td><html:text property="pvalue" onfocus="checkRadio($('searchAssocForm').association[0]);"/></td>
		</tr>
		<tr class="e">
			<td colspan="3"><span class="er"><html:errors property="pvalue"/></span></td>
		</tr>
		<tr class="e">
			<td><html:radio property="association" value="rank" styleClass="radio">
				<bean:message key="radio.rank"/>
			    </html:radio>
			</td>
			<td>&lt;=</td>
			<td><html:text property="rank" onfocus="checkRadio($('searchAssocForm').association[1]);"/></td>
		</tr>
		<tr class="e">
			<td colspan="3"><span class="er"><html:errors property="rank"/></span></td>
		</tr>
	</table>
	<div class="buttonsBar">
		<html:button property="btnReset" onclick="clearAssocForm()"><bean:message key="button.reset"/></html:button>
		<html:submit styleClass="btn" onclick="showWait()"/>
	</div>
</html:form>
</div>
</div>
<div id="wait" align="center" style="DISPLAY:none">
<img id="progress" src="images/cagwas_loader_anim.gif"/>
<H3><bean:message key="label.wait"/></H3>
</div>
