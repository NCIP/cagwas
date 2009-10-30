<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<div id="container" align="center">

	<div id="top_header" align="center">
		<% String view = (String)session.getAttribute("viewType");
	   	   if ((view != null) && (view.equals("text")))
	   	   { %>
		<div class="view-text">
			<div class="left"><a href="http://www.cancer.gov/" target="_blank">National Cancer Institute</a></div>
			<div class="right"><a href="http://www.nih.gov/" target="_blank">U.S. National Institutes of Health</a> | <a href="http://www.cancer.gov/" target="_blank">www.cancer.gov</a></div>
		</div>
		<% }
		   else
		   { %>
		<img src="images/top_header.gif" alt="NCI Main Logo" border="0" usemap="#top_header"/></div>
		<% } %>
	<div id="header">
		<% if ((view != null) && (view.equals("text")))
	   	   { %>
		<div class="view-text">
			<div class="left">
				<br /><a href="index.html" class="title"><bean:message key="project.title"/> Data Portal </a><br />
				<a href="index.html">Cancer Genetic Markers of Susceptibility</a>			
			</div>
		</div>
		<% }
		   else
		   { %>
		<!-- Utility Nav  -->
		<div id="nav_util">
			<a href="/<bean:message key="href.home"/>">Home</a> | 
		<!-- Check for login -->
			<% if(session.getAttribute("logged") == "yes"){ 
			%>
			    <a href="logout.do">Logout</a>
			<%} else {%>
			    <a href="login.jsp">Login</a>
			<%}%>	
			| <a href="remoteSetup.do?content=contacts">Contact Us</a> 
			
			
			
			|	Visit the <a href="remoteSetup.do?content=studyHome" target="_blank" class="util_nav_link"><strong><bean:message key="project.title"/> Home Site</strong></a>	
		</div>
		<!-- END Utility Nav  -->
		<img src="images/header.jpg" alt="CGEMS Banner" border="0" usemap="#header" class="main_header" />
		<% } %>
	</div>
</div>

<!-- Image Map for top red banner  -->
<map name="top_header">
<area shape="rect" coords="4,2,335,33" href="http://www.cancer.gov/" target="_blank" alt="National Cancer Institute">
<area shape="rect" coords="512,9,682,32" href="http://www.nih.gov/" target="_blank" alt="U.S. National Institutes of Health">
<area shape="rect" coords="694,18,784,33" href="http://www.cancer.gov/" target="_blank" alt="National Cancer Institute">
</map>

<!-- Image Map for header  -->
<map name="header">
<area shape="rect" coords="1,12,326,78" href="/cgems" target="" alt="Cancer Genetic Markers of Susceptibility Data Portal">
<area shape="rect" coords="374,34,457,80" href="http://cgfweb.nci.nih.gov/" target=_blank"" alt="Core Genotyping Facility">
<area shape="rect" coords="465,34,556,80" href="http://ocg.cancer.gov" target="_blank" alt="Office of Cancer Genomics">
<area shape="rect" coords="563,34,646,81" href="http://dceg.cancer.gov" target="_blank" alt="Division of Cancer Epidemiology and Genetics">
<area shape="rect" coords="652,31,744,85" href="http://cabig.cancer.gov" target="_blank" alt="Cancer Biomedical Infomatics Grid caBIG">
</map>