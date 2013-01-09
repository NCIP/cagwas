<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%
	/*
	* HTML head specifying the JS and CSS req'd files
	*/

%>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
 
	<LINK href="css/cagwas_data.css" rel="stylesheet" type="text/css">
	<LINK href="css/cagwas.css" rel="stylesheet" type="text/css">
	<LINK href="css/report.css" rel="stylesheet" type="text/css">
	<% String view = (String)session.getAttribute("viewType");
	   if ((view != null) && (view.equals("text")))
	   { %>
		<LINK href="css/view_text-only.css" rel="stylesheet" type="text/css">
	<% } %>


	<script language="javascript" src="js/caIntScript.js"></script>
	<script language="javascript" src="js/cagwas.js"></script>
	<script language="javascript" src="js/Help.js"></script>
	<script language="javascript" src="js/wwhapi.js"></script>
	<script language="javascript" src="js/wwhbaseurl.js"></script>
	
	<script language="javascript" src="js/common/browserSniff.js"></script>
	<script language="javascript" src="js/common/stripeScript.js"></script>
	<script language="javascript" src="js/common/prototype.js"></script>
	<script language="javascript" src="js/common/fat.js"></script>
	
	<script language="javascript" src="js/common/overlib.js"></script>
	<script language="javascript" src="js/common/overlib_hideform.js"></script>
	<script language="javascript" src="js/sorttable.js"></script>