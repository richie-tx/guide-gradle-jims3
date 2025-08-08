//javaScripts for caseAssignmentDataControlSearchList.jsp
function validateSelect(){
	var	msg = "Case History Assignment selection required.";
	var rbs = document.getElementsByName("selectedValue");
	if (rbs.length > 0){
		for (x=0; x<rbs.length; x++){
			if (rbs[x].checked == true){
				msg = "";
				break;
			}	
		}
	}
	if (msg == ""){ 		  
		return true;
	}
	alert(msg);
	rbs[0].focus();
	return false
}
function checkSingleHistory(){
	var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}
}