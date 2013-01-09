<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!-- Second tier nav -->
<div id="container" align="center">
	<div id="nav">
		<div id="secondary_nav">
			<div id="secondary_bg" >
				<div id="list_container">
					<ul>
						<li id="overview"><a href="aboutSetup.do">Overview</a></li>
						<li id="studies"><a href="remoteSetup.do?content=study1">Cancers</a></li>
						<li id="glossary"><a href="helpDocs/caGWAS_Online_Help/caGWAS Glossary.5.1.html" target="_blank">Glossary</a></li>
						<li id="contact"><a href="remoteSetup.do?content=contacts">Contact Us</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
 	function highlightActiveTabs()
	{
 		var wht = window.location.toString();
 		var cur = "";
 		//alert(wht);
 		//alert(top.document.title);

 		if(top.document.title.toLowerCase().indexOf("about overview")!=-1 )
		{
 			cur = "overview";
 		}
 		else if(wht.toLowerCase().indexOf("study1")!=-1)
		{
 			cur = "studies";
 		}
		else if(top.document.title.toLowerCase().indexOf("glossary")!=-1)
		{
 			cur = "glossary";
 		}
		else if(top.document.title.toLowerCase().indexOf("contact")!=-1)
		{
 			cur = "contact";
 		}
 		else if(top.document.title.toLowerCase().indexOf("cagwas support")!=-1 || top.document.title.toLowerCase().indexOf("cagwas feedback")!=-1)
		{
 			cur = "contact";
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
