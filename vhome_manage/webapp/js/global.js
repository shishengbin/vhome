function doNode(node) {
	node.style.cursor = "pointer";
	var left = node.src.substring(0, node.src.indexOf(".png"));
	var right = node.src.substring(node.src.indexOf(".png"), node.src.length);
	
	var img = new Image();
	img.src = node.src;
	var rimg = new Image();
	rimg.src = left + "-over" + right;
	
	node.onmouseover = function() { node.src = rimg.src; }
	node.onmouseout = function() { node.src = img.src; }
}

function doRollovers() {
	var nodes = new Array();
	var elems = document.getElementsByTagName('*');
	var pattern = new RegExp("(^|\\s)rollover(\\s|$)");
	for (i = 0, j = 0; i < elems.length; i++) {
		if (pattern.test(elems[i].className) && elems[i].src) {
			nodes[j] = elems[i];
			j++;
		}
	}

	for (var x = 0; x < nodes.length; x++) {
		doNode(nodes[x]);
	}
}

function getQueryVal(param) {
		qs = window.location.search.substring(1);
		vals = qs.split("&");
		for (i=0;i<vals.length;i++) {
			ar = vals[i].split("=");
			if (ar[0] == param) {
				return ar[1];
			}
		}
}


doInit = function() {
	if (document.all && document.getElementById) {
		var rslt = navigator.appVersion.match(/MSIE (\d+\.\d+)/, '');
		if (rslt != null && Number(rslt[1]) >= 5.5 && Number(rslt[1]) < 7) {
			nav_root = document.getElementById("nav");
			for (i = 0; i < nav_root.childNodes.length; i++) {
				node = nav_root.childNodes[i];
				if (node.id && node.id.indexOf("nav-") != -1) {
					node.onmouseover = function() { this.className = "over"; }
					node.onmouseout = function() { this.className = this.className.replace("over", ""); }
				}
			}
		}
	}
	
	doRollovers();
}

window.onload = doInit;


function toggleTextonly() {
	/*
	var textonly = ce.Cookie.read("TEXTONLY");
	if (!ce.check(textonly) || textonly == "false") { ce.Cookie.write("TEXTONLY", "true", 365, "/"); } else { ce.Cookie.write("TEXTONLY", "false", 365, "/"); }
	var loc = window.location.href;
	var pound_loc = loc.indexOf("#")
	if (pound_loc != -1) { loc = loc.substr(0, pound_loc); }
	window.location.href = loc;*/
}

