<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="gov.nih.nci.caintegrator.domain.study.bean.Population" %>
<%@ page import="gov.nih.nci.caintegrator.domain.common.bean.NumericMeasurement" %>
<%@ page import="java.util.Set" %>
<br clear="both"/>
<script type="text/javascript">Help.insertHelp("cagwas_subject_data_report_help", "", "padding:8px;float:right;");</script><br clear="left"/>
<h3 align="left">Study: <% out.println(session.getAttribute("study")); %></h3>

<%
Integer num = (Integer)request.getAttribute("numberSubjects");
int numSubjects = num.intValue();
if (numSubjects == 0){
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

<table border="0" class="reportTable" id="searchSubjectTable" style="width:100%">
	<caption>
		<bean:message key="table.caption.subject"/> - (<%out.print(request.getAttribute("numberSubjects"));%> subjects)
		&nbsp;
		<div class="save">
		<a href="saveSubject.do"><img src="images/page_save.png" align="right" border="0"/></a>
		<a href="saveSubject.do">Save Results</a>
		</div>
	</caption>
	<tr>
		<% if ((sortedColumn.equals("subjectid")) && (sortOrder.equals("ascending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=subjectid&order=descending"><bean:message key="table.header.participant"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("subjectid")) && (sortOrder.equals("descending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=subjectid&order=ascending"><bean:message key="table.header.participant"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th style="width:20%"><a href="sortSubject.do?column=subjectid&order=ascending"><bean:message key="table.header.participant"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("gender")) && (sortOrder.equals("ascending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=gender&order=descending"><bean:message key="table.header.gender"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("gender")) && (sortOrder.equals("descending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=gender&order=ascending"><bean:message key="table.header.gender"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th style="width:20%"><a href="sortSubject.do?column=gender&order=ascending"><bean:message key="table.header.gender"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("age")) && (sortOrder.equals("ascending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=age&order=descending"><bean:message key="table.header.age"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("age")) && (sortOrder.equals("descending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=age&order=ascending"><bean:message key="table.header.age"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th style="width:20%"><a href="sortSubject.do?column=age&order=ascending"><bean:message key="table.header.age"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("affection")) && (sortOrder.equals("ascending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=affection&order=descending"><bean:message key="table.header.affection"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("affection")) && (sortOrder.equals("descending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=affection&order=ascending"><bean:message key="table.header.affection"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th style="width:20%"><a href="sortSubject.do?column=affection&order=ascending"><bean:message key="table.header.affection"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("history")) && (sortOrder.equals("ascending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=history&order=descending"><bean:message key="table.header.history"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("history")) && (sortOrder.equals("descending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=history&order=ascending"><bean:message key="table.header.history"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th style="width:20%"><a href="sortSubject.do?column=history&order=ascending"><bean:message key="table.header.history"/></a></th>
		<% } %>
		<% if ((sortedColumn.equals("population")) && (sortOrder.equals("ascending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=population&order=descending"><bean:message key="table.header.population"/></a>
				<img src="images/darr.gif"/>
			</th>
		<% } else if ((sortedColumn.equals("population")) && (sortOrder.equals("descending"))) { %>
			<th style="width:20%">
				<a href="sortSubject.do?column=population&order=ascending"><bean:message key="table.header.population"/></a>
				<img src="images/uarr.gif"/>
			</th>
		<% } else { %>
			<th style="width:20%"><a href="sortSubject.do?column=population&order=ascending"><bean:message key="table.header.population"/></a></th>
		<% } %>
	</tr>
	<logic:iterate id="subject" name="subj.results" type="gov.nih.nci.cagwas.reports.StudyParticipantReport">
		<tr>
			<td><bean:write name="subject" property="studySubjectIdentifier"/></td>
			<td><bean:write name="subject" property="administrativeGenderCode"/></td>
			<td><bean:write name="subject" property="ageAtEnrollment"/></td>
			<td><bean:write name="subject" property="caseControlStatus"/></td>
			<td><bean:write name="subject" property="familyHistory"/></td>
			<td>
				<% Set pops = subject.getPopulationNameCollection();
					request.setAttribute("pops", pops); %>
				<logic:iterate id="pop" name="pops" type="java.lang.String">
					<bean:write name="pop"/>
				</logic:iterate>
				&nbsp;
			</td>
		</tr>
	</logic:iterate>
</table>
<script language="javascript">
	Stripe.stripe("searchSubjectTable", "#eee", "#ccc");
</script>
<%
}
%>