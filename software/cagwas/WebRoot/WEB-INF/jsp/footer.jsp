<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!-- Start Footer-->
<div class="right">
	<a href="http://caintegrator-info.nci.nih.gov/" target="_blank"><img src="images/logo_cal.gif" border="0" class="view-images" alt="Powered by caIntegrator" /></a></div>
</div>	
<div id="footer" align="center">
	<a href="/<bean:message key="href.home"/>">Home</a> | <%
		String view = (String)session.getAttribute("viewType");
		if ((view == null) || (view.equals("image")))
		{ %>
			<a href="viewText.do?viewType=text">Text-Only Version</a>
		<%}
		else
		{%>
		 	<a href="viewText.do?viewType=image">Image Version</a>
		<%}%> | <a href="remoteSetup.do?content=contacts">Contact Us</a> | <a href="remoteSetup.do?content=policies">Policies</a> | <a href="http://www3.cancer.gov/accessibility/nci508.htm">Accessibility</a> | <a href="siteMap.jsp">Site Map</a>
	<br /><br />
	<a href="http://www.cancer.gov/" target="_blank"><img src="images/footer_logo_nci.jpg" alt="National Cancer Institute" width="63" height="39" border="0"></a>
	<a href="http://www.nih.gov/" target="_blank"><img src="images/footer_logo_nih.jpg" alt="National Institutes of Health" width="48" height="39" border="0"></a>
	<img src="images/spacer.gif" alt="" width="6" height="39">
	<a href="http://www.dhhs.gov/" target="_blank"><img src="images/footer_logo_hhs.jpg" alt="Department of Health and Human Services" width="41" height="39" border="0"></a>
	<a href="http://www.firstgov.gov/" target="_blank"><img src="images/footer_logo_firstgov.jpg" alt="FirstGov.gov" width="101" height="39" border="0"></a>
</div>
<!-- END Footer  -->
