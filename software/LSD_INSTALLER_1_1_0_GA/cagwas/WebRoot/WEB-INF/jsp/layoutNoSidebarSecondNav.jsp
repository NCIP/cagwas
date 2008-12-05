<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
/*
 *		this is the main tiles template for the form based pages
*/
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title><tiles:getAsString name="title"/></title>
	<tiles:insert attribute="htmlHead"/> <%-- include html head --%>	
</head>

<body>
	<div id="masthead">
	 <%-- include div for overlib --%>
    <tiles:insert attribute="overlib"/>
    <%-- include header --%>
    <div align="center">
    	<tiles:insert attribute="header"/> 
    </div>
    <tiles:insert attribute="crumbMenu"/>
    <tiles:insert attribute="secondNav"/>
    <div align="center">
		<div class="content"> 			
				<div style="width:763px; border:0px solid red;text-align:left;">
					<%-- include the main form --%>
					<tiles:insert attribute="mainForm"/> 
				</div>	
			<tiles:insert attribute="footer"/> 
		</div>
	</div>
	
</div>
</body>
</html>