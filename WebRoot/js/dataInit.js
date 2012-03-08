function checkModify(selectedRecord) {
  var selectCount = 0;
  if (typeof(selectedRecord) == "undefined") {
    alert("请选择一条记录。");
    return false;
  }

  for (var i = 0;i < selectedRecord.length;i++) {
    if (selectedRecord[i].checked) {
        selectCount++;
    }
  }
  
  if(typeof(selectedRecord.length) == 'undefined'){
    if(selectedRecord.value != '' && selectedRecord.checked) {
      selectCount++;
    }
  }
  
  if (selectCount == 0) {
    alert("请选择一条记录。");
    return false;
  } else if (selectCount > 1) {
    alert("只能选择一条记录。");
    return false;
  }
  
  return true;
}

function checkSelected(selectedRecord) {
	  var selectCount = 0;
	  if (typeof(selectedRecord) == "undefined") {
	    return false;
	  }

	  for (var i = 0;i < selectedRecord.length;i++) {
	    if (selectedRecord[i].checked) {
	        selectCount++;
	    }
	  }
	  
	  if(typeof(selectedRecord.length) == 'undefined'){
	    if(selectedRecord.value != '' && selectedRecord.checked) {
	      selectCount++;
	    }
	  }
	  
	  if (selectCount == 0) {
	    return false;
	  }
	  
	  return true;
}

function checkDelete(selectedRecord) {
  var selectCount = 0;
  if (typeof(selectedRecord) == "undefined") {
    alert("请选择一条记录。");
    return false;
  }

  for (var i = 0;i < selectedRecord.length;i++) {
    if (selectedRecord[i].checked) {
      selectCount++;
    }
  }
  
  if(typeof(selectedRecord.length) == 'undefined'){
    if(selectedRecord.value != '' && selectedRecord.checked) {
      selectCount++;
    }
  }
  
  if (selectCount == 0) {
    alert("请选择一条记录。");
    return false;
  }
  
  return confirm("确定要删除吗？");
}