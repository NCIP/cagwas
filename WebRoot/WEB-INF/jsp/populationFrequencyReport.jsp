<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="gov.nih.nci.caintegrator.domain.annotation.snp.bean.SNPAnnotation" %>
<%@ page import="gov.nih.nci.caintegrator.domain.study.bean.Population" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="java.text.FieldPosition" %>
<%@ page import="java.text.DecimalFormat" %>
<br clear="both"/>
<script type="text/javascript">Help.insertHelp("cagwas_population_report_help", "", "padding:8px;float:right;");</script>
<h3 align="left">Study: <% out.println(session.getAttribute("study")); %></h3>
<%
String panel = (String)session.getAttribute("pop.panel");
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


		
<table border="0" class="reportTable" id="searchPopulationTable">
	<caption>
		<bean:message key="table.caption.frequency"/> - (<%out.print(request.getAttribute("numberFindings"));%> results)
		&nbsp;
		<div class="save">
		<a href="savePopulation.do"><img src="images/page_save.png" align="right" border="0"/></a>
		<a href="savePopulation.do">Save Results </a>
		</div>
	</caption>
	<tr>
		<% if ((sortedColumn.equals("snpid")) && (sortOrder.equals("ascending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortPopulation.do?column=snpid&order=descending"><bean:message key="table.header.snpId"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("snpid")) && (sortOrder.equals("descending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortPopulation.do?column=snpid&order=ascending"><bean:message key="table.header.snpId"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortPopulation.do?column=snpid&order=ascending"><bean:message key="table.header.snpId"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("chromosome")) && (sortOrder.equals("ascending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortPopulation.do?column=chromosome&order=descending"><bean:message key="table.header.chromosome"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("chromosome")) && (sortOrder.equals("descending"))) { %>
			<th style="white-space:nowrap">
				<a href="sortPopulation.do?column=chromosome&order=ascending"><bean:message key="table.header.chromosome"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortPopulation.do?column=chromosome&order=ascending"><bean:message key="table.header.chromosome"/></a></th>
		<% } %>
		<th><bean:message key="table.header.position"/></th>
		<th><bean:message key="table.header.genes"/></th>
		<% if ((sortedColumn.equals("population")) && (sortOrder.equals("ascending"))) { %>
			<th>
				<a href="sortPopulation.do?column=population&order=descending"><bean:message key="table.header.population"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("population")) && (sortOrder.equals("descending"))) { %>
			<th>
				<a href="sortPopulation.do?column=population&order=ascending"><bean:message key="table.header.population"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortPopulation.do?column=population&order=ascending"><bean:message key="table.header.population"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("rate")) && (sortOrder.equals("ascending"))) { %>
			<th>
				<a href="sortPopulation.do?column=rate&order=descending"><bean:message key="table.header.completion"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("rate")) && (sortOrder.equals("descending"))) { %>
			<th>
				<a href="sortPopulation.do?column=rate&order=ascending"><bean:message key="table.header.completion"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortPopulation.do?column=rate&order=ascending"><bean:message key="table.header.completion"/></a></th>
		<% } %>
		<th><bean:message key="table.header.weinberg"/></th>
		<th><bean:message key="table.header.allele"/></th>
		<% if ((sortedColumn.equals("alleleFreq")) && (sortOrder.equals("ascending"))) { %>
			<th>
				<a href="sortPopulation.do?column=alleleFreq&order=descending"><bean:message key="table.header.allele.count"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("alleleFreq")) && (sortOrder.equals("descending"))) { %>
			<th>
				<a href="sortPopulation.do?column=alleleFreq&order=ascending"><bean:message key="table.header.allele.count"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th><a href="sortPopulation.do?column=alleleFreq&order=ascending"><bean:message key="table.header.allele.count"/></a></th>
		<% } %>
		<th><bean:message key="table.header.genotype"/></th>
		<th><bean:message key="table.header.genotype.count"/></th>
	</tr>
	<logic:iterate id="result" name="pop.results" type="gov.nih.nci.cagwas.reports.SNPFrequencyFindingReport">
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
				</logic:iterate>
				&nbsp;
			</td>
			<td><bean:write name="result" property="populationName"/></td>
			<td>
				 <bean:write name="result" property="completionRate"/>
				 <br>
				 <bean:write name="result" property="completionRateValues"/>
			</td>
			<td><bean:write name="result" property="hardyWeinbergPValue"/></td>
			<td>
				<bean:write name="result" property="referenceAllele"/>
				<br>
				<bean:write name="result" property="otherAllele"/>
			</td>
			<td>
				<span  style="white-space:nowrap">
				<bean:write name="result" property="referenceAlleleCount"/>
				(<bean:write name="result" property="alleleFrequency"/>)
				</span>
				<br>
				<span  style="white-space:nowrap">
				<bean:write name="result" property="otherAlleleCount"/>
				(<bean:write name="result" property="otherFrequency"/>)
				</span>
			</td>
			<td>
				<bean:write name="result" property="referenceAllele"/><bean:write name="result" property="referenceAllele"/>
				<br>
				<bean:write name="result" property="referenceAllele"/><bean:write name="result" property="otherAllele"/>
				<br>
				<bean:write name="result" property="otherAllele"/><bean:write name="result" property="otherAllele"/>
			</td>
			<td>
				<span  style="white-space:nowrap"><bean:write name="result" property="referenceHomozygoteCount"/>
				(<bean:write name="result" property="homozygoteFrequency"/>)
				</span>
				<br>
				<span  style="white-space:nowrap">
				<bean:write name="result" property="heterozygoteCount"/>
				(<bean:write name="result" property="heterozygoteFrequency"/>)
				</span>
				<br>
				<span  style="white-space:nowrap">
				<bean:write name="result" property="otherHomozygoteCount"/>
				(<bean:write name="result" property="otherHomozygoteFrequency"/>)
				</span>
			</td>
		</tr>
	</logic:iterate>
</table>
<script language="javascript">
	Stripe.stripe("searchPopulationTable", "#eee", "#ccc");
</script>
<%
}
%>