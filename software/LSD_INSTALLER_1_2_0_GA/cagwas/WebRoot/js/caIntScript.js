function spawnx(url,winw,winh, name) {

  var w = window.open(url, name,
      "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + 
      ",scrollbars=yes,resizable=yes");
	
	//check for pop-up blocker
	if (w==null || typeof(w)=="undefined") {
		alert("You have pop-ups blocked.  Please click the highlighted link at the top of this page to view the report.  You may disable your pop-up blocker for this site to avoid doing this in the future.");
		/*
		if(document.all) {
			  document.all.popup.visible = "true"; 
			  document.all.popup.className = "pop";
		      document.all.popup.innerText = "You have pop-ups blocked.  Click <a href=\"javascript:spawnx('"+url+"',"+winw+","+winh+",'"+name+"');\">here</a> to view the report."; 
			  
		    } else { 
			  document.getElementById('popup').visible = "true";
			  document.getElementById('popup').className= "pop";
		      document.getElementById('popup').innerHTML = "You have pop-ups blocked.  Click <a href=\"javascript:spawnx('"+url+"',"+winw+","+winh+",'"+name+"');\">here</a> to view the report."; 	  
		} 
		*/
		document.write("<Br><Br><span class=\"pop\">You have pop-ups blocked.  Click <a href=\"javascript:spawnx('"+url+"',"+winw+","+winh+",'"+name+"');\">here</a> to view the report.</span>");
		//scroll(0, 8000);
	} else {
		w.focus();

	}
} 

function spawn(url,winw,winh) {
  var w = window.open(url, "_blank",
      "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + 
      ",scrollbars=yes,resizable=yes");
} 



function hideLoadingMessage(){
	if(document.getElementById('spnLoading') != null)
			document.getElementById('spnLoading').style.display = "none" ;
}
		

function checkToggle(box, id)	{
	if(box.value == 'false')
		toggleDiv(id, false);
	else
		toggleDiv(id, true);
}

function toggleDiv(id, state)	{
	if(state)
	    document.getElementById(id).style.display = "none";
	else
		document.getElementById(id).style.display = "block";	
}
 
 
//http://johankanngard.net/2005/11/14/remove-an-element-in-a-javascript-array/
Array.prototype.remove = function(s){
	for(i=0;i<this.length;i++){
		if(s==this[i]) this.splice(i, 1);
	}
}

//http://simon.incutio.com/archive/2004/05/26/addLoadEvent
function addLoadEvent(func) {
	var oldonload = window.onload;
  	if (typeof window.onload != 'function') {
    	window.onload = func;
  	} 
  	else {
	    window.onload = function() {
	    	if (oldonload) {
	        	oldonload();
	      	}
			func();
	    }
 	}
}