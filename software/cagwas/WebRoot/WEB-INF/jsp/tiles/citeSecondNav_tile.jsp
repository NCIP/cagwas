<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- Second tier nav -->
<div id="container" align="center">
	<div id="nav">
		<div id="secondary_nav">
			<div id="secondary_bg" >
				<div id="list_container">
					<ul>
						<!--  <li id="overview"><a href="remoteSetup.do?content=cite">Overview</a></li> -->
						<!-- <li id="examples"><a href="remoteSetup.do?content=citeExample">Examples</a></li> -->
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

 		if(top.document.title.toLowerCase().indexOf("cite overview")!=-1 )
		{
 			cur = "overview";
 		}
		else if(top.document.title.toLowerCase().indexOf("cite example")!=-1)
		{
 			cur = "examples";
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
