<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!-- Third tier nav -->
<div id="container" align="center">
<div id="nav">
<div id="tert_nav">
<div id="tert_bg">
<div id="list_container">
<ul>
	<li id="study1"><a
		href="remoteSetup.do?content=study1"><bean:message
		key="study1.title" /> Cancer</a></li>
	<li id="study2"><a
		href="remoteSetup.do?content=study2"><bean:message
		key="study2.title" /> Cancer</a></li>
	<li id="study3"><a
		href="remoteSetup.do?content=study3"><bean:message
		key="study3.title" /> Cancer</a></li>
	<li id="study4"><a
		href="remoteSetup.do?content=study4"><bean:message
		key="study4.title" /> Cancer</a></li>
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
	
 		if(top.document.title.toLowerCase().indexOf("about <bean:message key="study1.title"/> data")!=-1 )
		{
 			cur = "<bean:message key="study1.title"/>";
 		}
 		else if(top.document.title.toLowerCase().indexOf("about <bean:message key="study2.title"/> data")!=-1)
		{
 			cur = "<bean:message key="study2.title"/>";
 		}
		else if(top.document.title.toLowerCase().indexOf("about <bean:message key="study3.title"/> data")!=-1)
		{
 			cur = "<bean:message key="study3.title"/>";
 		}
 		else if(top.document.title.toLowerCase().indexOf("about <bean:message key="study4.title"/> data")!=-1)
		{
 			cur = "<bean:message key="study4.title"/>";
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
