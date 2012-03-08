function checkNumber(obj) {
    if (isFloat(obj.value*1)){
       return true;
    } else {
       alert("请输入数字（最多2位小数）");
       obj.focus();
       return false;
    }
}

function isFloat(str){
    if (str=='') {
        return true;
    }
    var reg = /^-?\d+(\.\d{1,2})?$/;
    return reg.test(str);
}

function checkItems() {
	var agtPremiums = document.getElementsByName("agtPremium");
	var agtComms = document.getElementsByName("agtComm");
	var agtActComms = document.getElementsByName("agtActComm");
	for (var i=0; i<agtPremiums.length; i++) {
		if (agtPremiums[i].value!=null && agtPremiums[i].value!=""){
			if (!checkNumber(agtPremiums[i])) {
				return false;
			}
		}
	}
	for (var i=0; i<agtComms.length; i++) {
		if (agtComms[i].value!=null && agtComms[i].value!="") {
			if (!checkNumber(agtComms[i])) {
				return false;
			}
		}
	}
	
	for (var i=0; i<agtActComms.length; i++) {
		if (agtActComms[i].value!=null && agtActComms[i].value!="") {
			if (!checkNumber(agtActComms[i])) {
				return false;
			}
		}
	}
	
	//if (!checkResuired()) {
	//	return false;
	//}
	return true;
}

function checkItemsOnBack() {
	var agtPremiums = document.getElementsByName("agtPremium");
	var agtComms = document.getElementsByName("agtComm");
	var agtActComms = document.getElementsByName("agtActComm");
	for (var i=0; i<agtPremiums.length; i++) {
		if (agtPremiums[i].value!=null && agtPremiums[i].value!=""){
			if (!checkNumber(agtPremiums[i])) {
				return false;
			}
		}
	}
	for (var i=0; i<agtComms.length; i++) {
		if (agtComms[i].value!=null && agtComms[i].value!="") {
			if (!checkNumber(agtComms[i])) {
				return false;
			}
		}
	}
	
	for (var i=0; i<agtActComms.length; i++) {
		if (agtActComms[i].value!=null && agtActComms[i].value!="") {
			if (!checkNumber(agtComms[i])) {
				return false;
			}
		}
	}

	return true;
}

function sumPremium() {
	var agtPremiums = document.getElementsByName("agtPremium");
	var sumPremium = parseFloat("0.00");
	for (var i=0; i<agtPremiums.length; i++) {
		if (agtPremiums[i].value!=null && agtPremiums[i].value!="") {
			sumPremium = sumPremium + parseFloat(agtPremiums[i].value);
		}
	}
	document.forms[0].elements["agtPremiumSum"].value = sumPremium.toFixed(2);
}

function sumCommission() {
	var agtComms = document.getElementsByName("agtComm");
	var sumComm = parseFloat("0.00");
	for (var i=0; i<agtComms.length; i++) {
		if (agtComms[i].value!=null && agtComms[i].value!="") {
			sumComm = sumComm + parseFloat(agtComms[i].value);
		}
	}
	document.forms[0].agtCommSum.value = sumComm.toFixed(2);
}

function sumActualCommission() {
	var agtActComms = document.getElementsByName("agtActComm");
	var sumActComm = parseFloat("0.00");
	for (var i=0; i<agtActComms.length; i++) {
		if (agtActComms[i].value!=null && agtActComms[i].value!="") {
			sumActComm = sumActComm + parseFloat(agtActComms[i].value);
		}
	}
	document.forms[0].agtActCommSum.value = sumActComm.toFixed(2);
}