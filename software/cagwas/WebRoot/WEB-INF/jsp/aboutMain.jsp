<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="org.apache.commons.httpclient.HttpClient" %>
<%@ page import="org.apache.commons.httpclient.HttpMethod" %>
<%@ page import="org.apache.commons.httpclient.methods.GetMethod" %>

<div id="middle" align="center">
	<div id="content">
		<h1><bean:message key="header.portal"/></h1>
		<div id="copy">
			<div class="right_highlights">
				<div class="spotlight">
					<h1>Spotlight</h1>
					<p>
						<a href="#" onclick="Help.popHelpMain('');return false;">How to Use the <bean:message key="project.title"/> data portal</a>
					</p>
					<% String regUrl = (String)session.getAttribute("registrationUrl"); %>
					<p>
						<a href="<%=regUrl%>" target="_blank">Register to access raw data</a>
					</p>
				</div>
				
				<div class="spotlight">
					<h1><bean:message key="header.portalUpdate"/></h1>
					<%out.print(request.getAttribute("updateContent"));%>
				</div>
			</div>
			
			<%out.print(request.getAttribute("aboutContent"));%>

		</div>
	</div>
</div>			