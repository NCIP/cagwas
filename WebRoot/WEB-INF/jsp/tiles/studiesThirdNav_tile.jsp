<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- Third tier nav -->
<div id="container" align="center">
	<div id="nav">
		<div id="tert_nav">
			<div id="tert_bg" >
				<div id="list_container">
					<ul>
						<li id="prostate"><a href="remoteSetup.do?content=prostate">Prostate Cancer</a></li>
						<li id="breast"><a href="remoteSetup.do?content=breast">Breast Cancer</a></li>
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
	
 		if(top.document.title.toLowerCase().indexOf("about prostate data")!=-1 )
		{
 			cur = "prostate";
 		}
 		else if(top.document.title.toLowerCase().indexOf("about breast data")!=-1)
		{
 			cur = "breast";
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
