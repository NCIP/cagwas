<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="org.apache.commons.httpclient.HttpClient" %>
<%@ page import="org.apache.commons.httpclient.HttpMethod" %>
<%@ page import="org.apache.commons.httpclient.methods.GetMethod" %>


<div id="middle" align="left">
	<div id="content">
		<h1><bean:message key="header.siteMap"/></h1>
		<div id="copy">
			<img src="images/sitemap.jpg" alt="Site Map" border="0" usemap="#sitemap"/>
		</div>
	</div>
</div>		

<!-- Image Map for site map  -->
<map name="sitemap">
<area shape="rect" coords="490,0,566,46" href="/<bean:message key="href.home"/>" target="_blank" alt="Data Portal">
<area shape="rect" coords="49,59,162,83" href="aboutSetup.do" target="_blank" alt="About @project_title@">
<area shape="rect" coords="84,93,131,111" href="aboutSetup.do" target="_blank" alt="Overview">
<area shape="rect" coords="81,122,121,141" href="remoteSetup.do?content=study1" target="_blank" alt="Studies">
<area shape="rect" coords="117,51,193,168" href="remoteSetup.do?content=study1" target="_blank" alt="Study1">
<area shape="rect" coords="117,172,183,190" href="remoteSetup.do?content=study2" target="_blank" alt="Study2">
<area shape="rect" coords="86,199,132,217" href="remoteSetup.do?content=glossary" target="_blank" alt="Glossary">
<area shape="rect" coords="77,232,132,250" href="remoteSetup.do?content=contacts" target="_blank" alt="Contact Us">
<area shape="rect" coords="119,256,159,273" href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank" alt="Support">
<area shape="rect" coords="118,284,166,301" href="feedbackSetup.do" target="_blank" alt="Feedback">

<area shape="rect" coords="283,54,360,80" href="browseSetup.do" target="_blank" alt="Browse Data">
<area shape="rect" coords="617,54,737,81" href="downloadSetup.do" target="_blank" alt="Bulk Data download">
<area shape="rect" coords="765,54,822,82" href="remoteSetup.do?content=cite" target="_blank" alt="Cite">
<area shape="rect" coords="780,90,828,110" href="remoteSetup.do?content=cite" target="_blank" alt="Cite Overview">
<!-- <area shape="rect" coords="781,113,830,131" href="remoteSetup.do?content=citeExample" target="_blank" alt="Cite Examples"> -->

<area shape="rect" coords="856,54,933,80" href="remoteSetup.do?content=access" target="_blank" alt="Data Access">
<area shape="rect" coords="891,89,940,107" href="remoteSetup.do?content=access" target="_blank" alt="Data Access Overview">
<area shape="rect" coords="884,109,951,126" href="remoteSetup.do?content=openAccess" target="_blank" alt="Data Access Public Access">
<area shape="rect" coords="884,129,970,148" href="remoteSetup.do?content=controlledAccess" target="_blank" alt="Data access Protected Access">
<!--  Utility Navigation -->
<area shape="rect" coords="437,331,469,348" href="/<bean:message key="href.home"/>" target="_blank" alt="Home">
<area shape="rect" coords="506,330,536,348" href="login.jsp" target="_blank" alt="Login">
<area shape="rect" coords="538,357,590,375" href="login.jsp" target="_blank" alt="Login Area">
<area shape="rect" coords="539,380,596,397" href="http://cagwas-content-ut.nci.nih.gov/cagwas-data-access-policies/app_view" target="_blank" alt="Registered Area">
<area shape="rect" coords="537,405,627,422" href="forgottenPassword.jsp" target="_blank" alt="Forgotten Password">
<area shape="rect" coords="675,331,730,349" href="remoteSetup.do?content=contacts" target="_blank" alt="Contact Us">
<area shape="rect" coords="720,354,761,372" href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank" alt="Support">
<area shape="rect" coords="718,382,768,399" href="feedbackSetup.do" target="_blank" alt="Feedback">
<!--  Footer Navigation -->
<area shape="rect" coords="431,560,466,579" href="/<bean:message key="href.home"/>" target="_blank" alt="Home">
<area shape="rect" coords="559,562,613,579" href="remoteSetup.do?content=contacts" target="_blank" alt="Contact Us">
<area shape="rect" coords="618,560,659,577" href="remoteSetup.do?content=policies" target="_blank" alt="Policies">
<area shape="rect" coords="664,560,722,578" href="http://www3.cancer.gov/accessibility/nci508.htm" target="_blank" alt="Accessibility">
<area shape="rect" coords="728,560,772,579" href="siteMap.jsp" target="_blank" alt="Site Map">
</map>	
<!-- <area shape="rect" coords="471,561,553,577" href="viewText.do?viewType=text" target="_blank" alt="Text Only Version">-->
