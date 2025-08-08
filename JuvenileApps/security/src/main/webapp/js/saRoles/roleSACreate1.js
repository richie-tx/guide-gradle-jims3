<!-- roleSACreate1.js -->
function validateAgencySelect(theForm){
    var msg = "";
    for (var i = 0; i <theForm.length; i++){
		if(theForm.elements[i].type == "radio"){
			if(theForm.elements[i].name == "agencyId"){
				if(theForm.elements[i].checked == true){
					return true;
				}	
			}
		}     
    }
	alert("Agency selection is required.");
    return false;
}
