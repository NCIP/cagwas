var Help = {
	url : "helpDocs/CaGWAS_Online_Help/index.html?context=CaGWAS_Online_Help&topic=",
	popHelp: function(topic) {
		window.open (Help.url+topic, "Help", "status,scrollbars,resizable,width=800,height=500");  
		//alert(Help.url+topic);
	},
	insertHelp: function(topic)	{
		var ex = arguments[1] ? arguments[1] : "";
		var exst = arguments[2] ? arguments[2] : "";
		var htm = "<img "+ ex + " style=\"cursor:pointer;"+ exst + "\" src=\"images/help_icon.gif\" alt=\"help\" onclick=\"Help.popHelp(\'"+topic+"\');\" />";
		document.write(htm);
	},
	popHelpMain: function(topic) {
		var _url = "helpDocs/CaGWAS_Online_Help/index.html?context=CaGWAS_Online_Help&topic=";
		_url = "helpDocs/CaGWAS_Online_Help/WelcometoCaGWASOnlineHelp.1.1.html";
		window.open (_url+topic, "Help", "status,scrollbars,resizable,width=800,height=500");  
		//window.open (_url+topic, "Help", "alwaysRaised,dependent,status,scrollbars,resizable,width=800,height=500");  
		//alert(Help.url+topic);
	}
}

