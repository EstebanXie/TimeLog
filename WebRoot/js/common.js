$(document).ready(function() {
	$("table").find("tr").each(function(){
		$(this).find("td").each(function(){
			if($(this).text() == '') {
				$(this).append(' ');
			}
		});
	});
});

function forbidEnterKey(e) {
    if(!e) e = window.event;
    if(e.keyCode == 13){
        return false;
    }
}

function _w_table_rowspan(_w_table_id,_w_table_colnum){
    _w_table_firsttd = "";
    _w_table_currenttd = "";
    _w_table_SpanNum = 0;
    _w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");
    _w_table_Obj.each(function(i){
        if(i==0){
            _w_table_firsttd = $(this);
            _w_table_SpanNum = 1;
        }else{
            _w_table_currenttd = $(this);
            if(_w_table_firsttd.text()==_w_table_currenttd.text()){
                _w_table_SpanNum++;
                _w_table_currenttd.hide(); //remove();
                _w_table_firsttd.attr("rowSpan",_w_table_SpanNum);
            }else{
                _w_table_firsttd = $(this);
                _w_table_SpanNum = 1;
            }
        }
    }); 
}

function _w_table_colspan(_w_table_id,_w_table_rownum,_w_table_maxcolnum){
    if(_w_table_maxcolnum == void 0){_w_table_maxcolnum=0;}
    _w_table_firsttd = "";
    _w_table_currenttd = "";
    _w_table_SpanNum = 0;
    $(_w_table_id + " tr:nth-child(" + _w_table_rownum + ")").each(function(i){
        _w_table_Obj = $(this).children();
        _w_table_Obj.each(function(i){
            if(i==0){
                _w_table_firsttd = $(this);
                _w_table_SpanNum = 1;
            }else if((_w_table_maxcolnum>0)&&(i>_w_table_maxcolnum)){
                return "";
            }else{
                _w_table_currenttd = $(this);
                if(_w_table_firsttd.text()==_w_table_currenttd.text()){
                    _w_table_SpanNum++;
                    _w_table_currenttd.hide(); //remove();
                    _w_table_firsttd.attr("colSpan",_w_table_SpanNum);
                }else{
                    _w_table_firsttd = $(this);
                    _w_table_SpanNum = 1;
                }
            }
        });
    });
}
function clearForm() {  
	var formObj = document.forms[0];  
	var formEl = formObj.elements;               
	for (var i=0; i<formEl.length; i++){  
		var element = formEl[i];      
		if(element.disabled==false)
		{
			if (element.type == 'submit') { continue; }  
			if (element.type == 'reset') { continue; }  
			if (element.type == 'button') { continue; }  
			if (element.type == 'hidden') { continue; }                  
			if (element.type == 'text') { element.value = ''; }  
			if (element.type == 'textarea') { element.value = ''; }  
			if (element.type == 'password') { element.value = ''; }  
			if (element.type == 'checkbox') { element.checked = false; }  
			if (element.type == 'radio') { element.checked = false; }  
			if (element.type == 'select-multiple') { element.selectedIndex = 0; }  
			if (element.type == 'select-one') { element.selectedIndex = 0; }  
		}
	}  
} 

// add by yvonne to add numeric validation
function isFloat(str){
    
    if (str=='') {
        return true;
    }
    var reg = /^-?\d+(\.\d{1,2})?$/;
    return reg.test(str);
}
