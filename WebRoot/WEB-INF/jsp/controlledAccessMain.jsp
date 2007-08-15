<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div id="middle" align="center">
	<div id="content">
		<h1>CaGWAS Controlled Access</h1>
		<div id="copy">
			
			<%out.print(request.getAttribute("controlledContent"));%>

		</div>
	</div>
</div>			