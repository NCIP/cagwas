<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<div id="container" align="center">
	<div id="nav">
		<div id="top"><img src="images/spacer.gif" border="0"  height="1" width="700"/></div>
			<div id="middle">
				<ul>
					<li id="homeTab"><a href="aboutSetup.do">About <bean:message key="project.title"/> Data</a></li>
					<li id="browseTab"><a href="browseSetup.do">Browse Data</a></li>
					<li id="bulkTab"><a href="downloadSetup.do">Bulk Data Downloads</a></li>
					<li id="citeTab"><a href="remoteSetup.do?content=cite">Cite Data</a></li>
					<li id="accessTab"><a href="remoteSetup.do?content=access">Data Access</a></li>
				</ul>
			</div>
		<div id="bottom"><img src="images/spacer.gif" border="0"  height="1" width="800"/></div>
	</div>
</div>

<script type="text/javascript">
 	function highlightActiveTabs()
	{
 		var wht = window.location.toString();
 		var cur = "";
 		//alert(wht);
 		//alert(top.document.title);

 		if(top.document.title.toLowerCase().indexOf("about")!=-1 )
		{
 			cur = "homeTab";
 		}
 		else if(top.document.title.toLowerCase().indexOf("search")!=-1 || top.document.title.toLowerCase().indexOf("browse")!=-1 || wht.indexOf("browse")!=-1 || wht.indexOf("logout")!=-1)
		{
 			cur = "browseTab";
 		}
 		else if(wht.indexOf("downloadSetup")!=-1 || wht.indexOf("bulkDownload")!=-1 || top.document.title.toLowerCase().indexOf("download")!=-1  )
		{
 			cur = "bulkTab";
 		}
		else if(top.document.title.toLowerCase().indexOf("cite")!=-1 )
		{
 			cur = "citeTab";
 		}
 		else if(top.document.title.toLowerCase().indexOf("access")!=-1 )
		{
 			cur = "accessTab";
 		}
  		try
		{
			//alert("cur is " + cur);
 			if(cur!="" && $(cur) && $(cur).childNodes[0] && $(cur).childNodes[0].nodeName == "A")
			{
				$(cur).className = "selected";
 				$(cur).childNodes[0].className = "selected";
 			}
 		}
 		catch(er)
		{}
 	}

 	highlightActiveTabs();
 </script>
