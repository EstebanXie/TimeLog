String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.lTrim = function() {
	return this.replace(/(^\s*)/g, "");
}

String.prototype.rTrim = function() {
	return this.replace(/(\s*$)/g, "");
}

function selectAllRecord(checkit, arrays){
	if(checkit) {
		for (var i=0; i<arrays.length; i++) {
			arrays[i].checked=true;
		}
	} else {
		for (var i=0; i<arrays.length; i++) {
			arrays[i].checked=false;
		}
	}
}
