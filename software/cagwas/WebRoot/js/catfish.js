// JavaScript Document

/* Stack up window.onload events using this function from Simon Willison - http://www.sitepoint.com/blog-post-view.php?id=171578 */
function _addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}

function wrapFish() {
	var catfish = document.getElementById('catfish');
	var subelements = [];
	for (var i = 0; i < document.body.childNodes.length; i++) {
 		subelements[i] = document.body.childNodes[i];
	}

	var zip = document.createElement('div');    // Create the outer-most div (zip)
	zip.id = 'zip';                      // call it zip

	for (var i = 0; i < subelements.length; i++) {
	zip.appendChild(subelements[i]); 
	}
	document.body.appendChild(zip); // add the major div
	document.body.appendChild(catfish); // add the catfish after the zip
}

_addLoadEvent(function() {
	wrapFish();
});
 
 //window.onload = wrapFish;  
    // run that function!
