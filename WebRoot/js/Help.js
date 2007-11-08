var Help = {
	url : "helpDocs/caGWAS_Online_Help/index.html?context=caGWAS_Online_Help&topic=",
	popHelp: function(topic)
	{
		WWHAPI_Object("helpDocs/CGEMS_Online_Help", null);
		WWHAPI_DisplayHelpWithNavigation("CGEMS_Online_Help", topic); 
		//alert(Help.url+topic);
	},
	insertHelp: function(topic)	{
		var ex = arguments[1] ? arguments[1] : "";
		var exst = arguments[2] ? arguments[2] : "";
		var htm = "<img "+ ex + " style=\"cursor:pointer;"+ exst + "\" src=\"images/help_icon.gif\" alt=\"help\" onclick=\"Help.popHelp(\'"+topic+"\');\" />";
		document.write(htm);
	},
	popHelpMain: function(topic) {
		var topic = "Welcome";
		WWHAPI_Object("helpDocs/caGWAS_Online_Help", null);
		WWHAPI_DisplayHelpWithNavigation("caGWAS_Online_Help", topic);
		//alert(Help.url+topic);
	}
}

