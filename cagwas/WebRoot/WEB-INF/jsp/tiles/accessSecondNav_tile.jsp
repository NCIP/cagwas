<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- Second tier nav -->
<div id="container" align="center">
	<div id="nav">
		<div id="secondary_nav">
			<div id="secondary_bg" >
				<div id="list_container">
					<ul>
						<li id="overview"><a href="remoteSetup.do?content=access">Overview</a></li>
<li id="open"><a href="remoteSetup.do?content=openAccess">Public Access</a></li>
						<% String regUrl = (String)session.getAttribute("registrationUrl"); 
						%>
						<li id="controlled"><a href="<%=regUrl%>" target="#">Registered Access</a></li>					</ul>
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

 		if(top.document.title.toLowerCase().indexOf("data access overview")!=-1 )
		{
 			cur = "overview";
 		}
		else if(top.document.title.toLowerCase().indexOf("open access")!=-1)
		{
 			cur = "open";
 		}
		else if(top.document.title.toLowerCase().indexOf("controlled access")!=-1)
		{
 			cur = "controlled";
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
