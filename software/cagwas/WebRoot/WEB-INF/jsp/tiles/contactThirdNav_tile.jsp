<%--L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- Third tier nav -->
<div id="container" align="center">
	<div id="nav">
		<div id="tert_nav">
			<div id="tert_bg" >
				<div id="list_container">
					<ul>
						<li id="support"><a href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank">Support</a></li>
						<li id="feedback"><a href="feedbackSetup.do">Feedback</a></li>
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
	
 		if(top.document.title.toLowerCase().indexOf("support")!=-1 )
		{
 			cur = "support";
 		}
 		else if(top.document.title.toLowerCase().indexOf("feedback")!=-1)
		{
 			cur = "feedback";
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
