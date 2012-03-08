var currEle = null;
function setCurrentElement(ele){
	currEle = ele;
}
function clearCurrentElement(ele){
	currEle = null;
}
function disableButton(){
	if(currEle != null){
		newDiv(currEle);
		currEle.className = "DisableTableBtn";
		currEle.onclick = getRs;
		currEle = null;
	}
}

function getRs(){
	return false;
}

function newDiv(ele){
	var div_body=document.createElement("div");
	div_body.setAttribute('id','div_body');   
	div_body.style.position = "absolute";
	div_body.style.height = ele.offsetHeight+5 + "px";
	div_body.style.width = ele.offsetWidth+10 + "px";
	div_body.style.top = ele.getBoundingClientRect().top+document.documentElement.scrollTop - 3 + document.body.scrollTop;
	div_body.style.left = ele.getBoundingClientRect().left+document.documentElement.scrollLeft -5 + document.body.scrollLeft;
	div_body.style.backgroundColor = "#CCCCCC";
	div_body.style.display = "";
	div_body.style.filter= "Alpha(Opacity=50)";
	document.body.appendChild(div_body);
}