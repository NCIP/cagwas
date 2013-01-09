<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationFinding" %>
<%@ page import="gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAssociationAnalysis" %>
<%@ page import="gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation" %>
<%@ page import="gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker" %>
<%@ page import="java.util.Set" %>
<br clear="both"/>
<script type="text/javascript">Help.insertHelp("cagwas_association_report_help", "", "padding:8px;float:right;");</script>
<h3 align="left">Study: <% out.println(session.getAttribute("study")); %></h3>
<%
		String studyName = (String)request.getSession().getAttribute("studyName");
		String caseStudyName = (String)request.getSession().getAttribute("caseStudyName");
		String panel = (String)session.getAttribute("assoc.panel");
		String noOfCategories = (String)request.getAttribute("NoOfCategories");
if (panel != null)
{%>
	<h3 align="left">Panel: <% out.println(panel); %></h3>
<%}%>

<%
Integer num = (Integer)request.getAttribute("numberFindings");
int numFindings = num.intValue();
if (numFindings == 0){
	out.print("Your search criteria produced no results");%>
	<br><br>
	<div align="center">
	<a href="#" onClick="javascript:history.go(-2); return false;">Go Back to Browse</a>
	<br>
	</div>
	<%
}
else
{
%>

<%
	String sortedColumn = (String)request.getAttribute("sortedColumn");
	String sortOrder = (String)request.getAttribute("sortOrder");
%>

<table border="0" cellpadding="0" cellspacing="0" id="searchAssocTable" class="reportTable">
	<caption>
		<bean:message key="table.caption.association"/> - (<%out.print(request.getAttribute("numberFindings"));%> results)
		&nbsp;
		<div class="save">
			<a href="saveAssociations.do"><img src="images/page_save.png" align="right" border="0"/></a>
			<a href="saveAssociations.do?NoOfCategories=<%=noOfCategories%>">Save Results</a>
		</div>
	</caption>
	<tr id="searchAssocTableHeader">
		<% if ((sortedColumn.equals("snpid")) && (sortOrder.equals("ascending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortAssociations.do?column=snpid&order=descending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.snpId"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("snpid")) && (sortOrder.equals("descending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortAssociations.do?column=snpid&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.snpId"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortAssociations.do?column=snpid&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.snpId"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("chromosome")) && (sortOrder.equals("ascending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortAssociations.do?column=chromosome&order=descending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.chromosome"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("chromosome")) && (sortOrder.equals("descending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortAssociations.do?column=chromosome&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.chromosome"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortAssociations.do?column=chromosome&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.chromosome"/></a></th>
		<% } %>
		<th><bean:message key="table.header.position"/></th>
		<th><bean:message key="table.header.genes"/></th>
		<% if ((sortedColumn.equals("analysis")) && (sortOrder.equals("ascending"))) { %>
			<th>
				<a href="sortAssociations.do?column=analysis&order=descending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.analysis"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("analysis")) && (sortOrder.equals("descending"))) { %>
			<th>
				<a href="sortAssociations.do?column=analysis&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.analysis"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortAssociations.do?column=analysis&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.analysis"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("pvalue")) && (sortOrder.equals("ascending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortAssociations.do?column=pvalue&order=descending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.pvalue"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("pvalue")) && (sortOrder.equals("descending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortAssociations.do?column=pvalue&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.pvalue"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortAssociations.do?column=pvalue&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.pvalue"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("rank")) && (sortOrder.equals("ascending"))) { %>
			<th>
				<a href="sortAssociations.do?column=rank&order=descending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.rank"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("rank")) && (sortOrder.equals("descending"))) { %>
			<th>
				<a href="sortAssociations.do?column=rank&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.rank"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortAssociations.do?column=rank&order=ascending&NoOfCategories=<%=noOfCategories%>"><bean:message key="table.header.rank"/></a></th>
		<% } %>
		<% if (noOfCategories != null && noOfCategories.equals("0"))
			{%>
			<% }
		else if (noOfCategories != null && noOfCategories.equals("2"))
		{%>
			<th colspan="2"><bean:message key="table.header.eor"/></th>		
		<%} else{%>
			<th colspan="2"><bean:message key="table.header.nonaggressive"/></th>
			<th colspan="2"><bean:message key="table.header.aggressive"/></th>
		<% }%>
	</tr>
	<tr id="searchAssocTableHeader">
	<% if (noOfCategories != null && noOfCategories.equals("0"))
	{%>
	<% }
	   else if (noOfCategories != null && noOfCategories.equals("2"))
	{%>
		<th colspan="7"></th>
		<th><bean:message key="table.header.heterozygote.risk"/></th>
		<th><bean:message key="table.header.homozygote.risk"/></th>
	<%}
	else
	{%>
		<th colspan="7"></th>
		<th><bean:message key="table.header.heterozygote.risk"/></th>
		<th><bean:message key="table.header.homozygote.risk"/></th>
		<th><bean:message key="table.header.heterozygote.risk"/></th>
		<th><bean:message key="table.header.homozygote.risk"/></th>
	<%}%>
	</tr>
	<logic:iterate id="result" name="assoc.results" type="gov.nih.nci.cagwas.reports.SNPAssociationFindingReport">
		<tr>
			<td>
				<a href="<bean:message key="url.snps"/><bean:write name="result" property="dbsnpId"/>" target="_blank">
					<bean:write name="result" property="dbsnpId"/>
				</a>
			</td>
			<td><bean:write name="result" property="chromosomeName"/></td>
			<td><bean:write name="result" property="chromosomeLocation"/></td>
			<td>
				<% Set markers = result.getGeneBiomarkerCollection();
					request.setAttribute("markers", markers); %>
				<logic:iterate id="gene" name="markers" type="gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker">
					<a href="<bean:message key="url.genes"/><bean:write name="gene" property="hugoGeneSymbol"/>" target="_blank">
						<bean:write name="gene" property="hugoGeneSymbol"/>
					</a>
					&nbsp;
				</logic:iterate>
			</td>
			<td><bean:write name="result" property="snpAssociationAnalysisName"/></td>
			<td><bean:write name="result" property="pvalue"/></td>
			<td><bean:write name="result" property="rank"/></td>
			<% if (noOfCategories != null && noOfCategories.equals("0"))
			{%>
			<% }
				else if (noOfCategories != null && noOfCategories.equals("2"))
			{%>
				<td>
				<bean:write name="result" property="caseHeterozygote"/>
				<bean:write name="result" property="caseHeterozygoteConfidence"/>
				</td>
				<td>
				<bean:write name="result" property="caseHomozygote"/>
				<bean:write name="result" property="caseHomozygoteConfidence"/>
				</td>
			<%}
			else
			{%>
				<td>
					<bean:write name="result" property="nonaggressiveHeterozygote"/>
					<bean:write name="result" property="nonaggressiveHeterozygoteConfidence"/>	
				</td>
				<td>
					<bean:write name="result" property="nonaggressiveHomozygote"/>
					<bean:write name="result" property="nonaggressiveHomozygoteConfidence"/>	
				</td>
				<td>
					<bean:write name="result" property="aggressiveHeterozygote"/>
					<bean:write name="result" property="aggressiveHeterozygoteConfidence"/>
				
				</td>
				<td>
					<bean:write name="result" property="aggressiveHomozygote"/>
					<bean:write name="result" property="aggressiveHomozygoteConfidence"/>
				</td>
			<%}%>
		</tr>
	</logic:iterate>
</table>
<script language="javascript">
	Stripe.stripe("searchAssocTable", "#eee", "#ccc");
</script>
<%
}
%>