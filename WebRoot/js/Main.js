    var curOpac = 0;
    var filterTimer;
    var isIE = /internet explorer/i.test(window.navigator.appName);
    
    function MyScroll(cnt, control){
        this.data = [];
        this.interval = 3000;
        this.timer;
        this.container = cnt;
        this.curFrame = 0;
        this.oldFrame = 0;
        this.controls = control;
        Global = this;        

        this.run = function(){
            this.timer = window.setInterval("Global.showFrame()", this.interval);
        }
        
        this.go = function(i){
            curOpac = 0;
            this.curFrame = i;
            this.stop();
            this.showFrame();
            this.run();
        }
        
        this.stop = function(){
            window.clearInterval(this.timer);
            window.clearInterval(filterTimer);
        }

        this.showFrame = function(){

            this.controls[this.oldFrame].style.className = "white";
            this.controls[this.curFrame].style.className = "gray";

            if(isIE) this.container.style.filter = "alpha(opacity=0)";
            else this.container.style.cssText = "-moz-opacity:0";

            this.container.innerHTML = this.data[this.curFrame];
            filterTimer = window.setInterval("blend()", 100);
            
            this.oldFrame = this.curFrame;
            this.curFrame ++;
            if(this.curFrame == this.data.length){
                this.curFrame = 0;
            }            
        }
    }

    function blend(){
        curOpac+=10;
        if(isIE) Global.container.style.filter='alpha(opacity=' + curOpac + ')';
        else Global.container.style.cssText = "-moz-opacity:" + curOpac/100.0;

        if(curOpac == 100){
            curOpac = 0;
            window.clearInterval(filterTimer);
        }
    }
    //开始
    
    function startIt(){
        var imgArr = [];
        for(var i=0;i<10;i++){
            imgArr[i] = new Image();
        }
        imgArr[0].src = "def_Pic/1.jpg";
        imgArr[1].src = "def_Pic/2.jpg";
        imgArr[2].src = "def_Pic/3.jpg";
        imgArr[3].src = "def_Pic/4.jpg";
        imgArr[4].src = "def_Pic/5.jpg";
        imgArr[5].src = "def_Pic/6.jpg";
        imgArr[6].src = "def_Pic/7.jpg";
        imgArr[7].src = "def_Pic/8.jpg";
        imgArr[8].src = "def_Pic/9.jpg";
        imgArr[9].src = "def_Pic/10.jpg";
        
        var mainTb = document.getElementById("mainTb");
        if(mainTb != null) {
          var controlArr = mainTb.getElementsByTagName("span");
          for(i=0;i<controlArr.length;i++){
              controlArr[i].tag = i;
              controlArr[i].onclick = function(){
                  myScroll.go(this.tag);
              }
          }
  
          var myScroll = new MyScroll($("cnt"), controlArr);
          myScroll.data.push("<img src='" + imgArr[0].src + "'>");
          myScroll.data.push("<img src='" + imgArr[1].src + "'>");
          myScroll.data.push("<img src='" + imgArr[2].src + "'>");
          myScroll.data.push("<img src='" + imgArr[3].src + "'>");
          myScroll.data.push("<img src='" + imgArr[4].src + "'>");
          myScroll.data.push("<img src='" + imgArr[5].src + "'>");
          myScroll.data.push("<img src='" + imgArr[6].src + "'>");
          myScroll.data.push("<img src='" + imgArr[7].src + "'>");
          myScroll.data.push("<img src='" + imgArr[8].src + "'>");
          myScroll.data.push("<img src='" + imgArr[9].src + "'>");
          
          myScroll.go(0);
        }
    }
    
    window.onload = startIt;

    function $(id){ return document.getElementById(id);}
	

function Tab(mod,cursor,n){
	for(i=1;i<=n;i++){
		var nav=document.getElementById(mod+i);
		var cont=document.getElementById(mod+"_"+"cont"+i);
		nav.className=i==cursor?"current":"";
		cont.style.display=i==cursor?"block":"none";
	
	}
	
}

// 搜索下拉框
function selector(e, i) {
	var sObj = $(e);
	var tObj = sObj.getElementsByTagName('a')[0];
	var lObj = sObj.getElementsByTagName('div')[0];
	var vObj = $(i);
	
	if(sObj==null || vObj==null) return;
	
	var a = sObj.getElementsByTagName('a');
	var l = a.length;
	for(var i=0; i<l; i++) {
		a[i].onclick = function() {return false;}
		a[i].onfocus = function() {try{blur();}catch(e){}}
	}
	lObj.style.display = 'none';
	sObj.onclick = function(e) {open(e);}
	document.onclick = function() {close();}
	
	function open(e) {
		e = e || event;
		lObj.style.display='';
		sObj.onclick = function(e) {select(e);}
		e.stopPropagation && (e.preventDefault(), e.stopPropagation()) || (e.cancelBubble = true, e.returnValue = false);
	}
	function close() {
		lObj.style.display='none';
		sObj.onclick = function(e) {open(e);}
	}
	function select(e) {
		e = e || event;
		e = e.target || e.srcElement;
		var tag = e.tagName.toLowerCase();
		if(tag!='a' || e==tObj) return;

		vObj.value = e.getAttribute('rel');
		tObj.innerHTML = e.innerHTML;
	}
}