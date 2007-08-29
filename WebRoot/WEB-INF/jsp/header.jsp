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
				<br /><a href="index.html" class="title">caGWAS Data Portal </a><br />
				<a href="index.html">Cancer Genetic Markers of Susceptibility</a>			
			</div>
		</div>
		<% }
		   else
		   { %>
		<!-- Utility Nav  -->
		<div id="nav_util">
			<a href="/cagwas">Home</a> | <a href="login.jsp">Login</a> | <a href="remoteSetup.do?content=contacts">Contact Us</a> |	Visit the <a href="http://cagwas.cancer.gov" target="_blank" class="util_nav_link"><strong>caGWAS Home Site</strong></a>	
		</div>
		<!-- END Utility Nav  -->
		<img src="images/study_header.jpg" alt="caGWAS Banner/Your study logo" border="0" usemap="#studyheader" class="main_header" /><img src="images/logo_header.jpg" alt="caGWAS Banner/Related logo" border="0" usemap="#logoheader" class="main_header" />
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
<map name="studyheader">
<area shape="rect" coords="5,20,348,78" href="/cagwas" target="" alt="Cancer Genetic Markers of Susceptibility Data Portal">
</map>
<map name="logoheader">
<area shape="rect" coords="25,36,117,80" href="http://cgfweb.nci.nih.gov/" target=_blank"" alt="Core Genotyping Facility">
<area shape="rect" coords="120,44,212,80" href="http://ocg.cancer.gov" target="_blank" alt="Office of Cancer Genomics">
<area shape="rect" coords="215,45,300,81" href="http://dceg.cancer.gov" target="_blank" alt="Division of Cancer Epidemiology and Genetics">
<area shape="rect" coords="305,31,396,80" href="http://cabig.cancer.gov" target="_blank" alt="Cancer Biomedical Infomatics Grid caBIG">
</map>