<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div id="bulkHeader">
<h3 style="text-align:right; width:100%; padding:0px;margin:0px;">Study: <% out.println(session.getAttribute("study")); %></h3>

	<bean:message key="header.download"/>
</div>

<div id="bulkMain">

<script type="text/javascript">Help.insertHelp("cagwas_asynchronized_data_request_help", "", "padding:8px;float:right;");</script>

<% String searchType = (String)session.getAttribute("searchType");
if (searchType.equals("Genotype"))
{%>
<p align="left"><bean:message key="label.download.genotype"/></p>
<%} else {%>
<p align="left"><bean:message key="label.download.results"/></p>
<%}%>
<p align="left"><bean:message key="label.download.message"/></p>

<html:form action="download.do">
	<p align="left">
		<bean:message key="label.email"/>
		<html:text property="email" size="80"/>
	</p>
	<p align="left">
		<span class="er"><html:errors property="email"/></span>
	</p>
<% if ((searchType.equals("Genotype")) || (searchType.equals("Subject")))
{%>
	<p align="left">
		<bean:message key="label.download.confidentiality"/>
	</p>
<%}%>
	<p align="left">
		<bean:message key="label.download.notify"/>
	</p>
	<input type="button" onclick="history.back()" class="btn" value="Back"/>
	<html:submit styleClass="btn"/>
</html:form>

</div>