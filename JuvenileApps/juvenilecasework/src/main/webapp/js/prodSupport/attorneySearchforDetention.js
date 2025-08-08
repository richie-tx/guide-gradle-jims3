$(document).ready(function () {
    $("#submitBtn").on('click', function (e) 
    	{
    	var fld1 = Trim(document.getElementById("attorneyName").value);
    	document.getElementById("attorneyName").value = fld1.toUpperCase();
    	
    	var str = fld1;
    	if (str == "")	 {
    	 	alert("Attorney Name is required for search");
    	 	document.getElementById("attorneyName").focus();
    	 	return false;
    	}
    	var msg = "";
    	var numericRegExp = /^[0-9]*$/;
    	var alphaNumericRegExp = /^[A-Z0-9]*$/;
    	var shortDescRegExp = /^[A-Z.,_ \-()\x2A\x2C]*$/;	
    	fld1 = document.getElementById("attorneyName");
    	if (fld1.value > "") {
    		if (shortDescRegExp.test(fld1.value,shortDescRegExp) == false) {
    			if (msg == ""){
    				fld1.focus();
    			}
    			msg = "Attorney Name contains invalid search character -- such as > ? @ or ~ ! < or Numbers.\n";
    			alert(msg);
    			return false; 
    		}
    		if (numericRegExp.test(fld1.value,numericRegExp) == true) {
    			if (msg == ""){
    				fld1.focus();
    			}
    			msg = "Attorney Name cannot be Numeric";
    			alert(msg);
    			return false; 
    		}
    	}
    	
    	if(fld1.value.trim().length<3){
        	alert("Attorney Name Must Contain At Least 3 Characters.");
        	fld1.focus();
        	return false;
        }
    	if(fld1.value.indexOf("%") != -1){
    		alert('Invalid Attorney Name. Please use * for partial search');
    		fld1.focus();
    		return false;
    	}
    	var attorneyName=document.getElementById("attorneyName").value;	
    	$('form')
		.attr(
				'action',
				"/JuvenileCasework/PerformUpdateDetentionCourtRecord.do?submitAction=Find Attorney&attorneyName="+attorneyName);
				$('form').submit();
					 			
    });
    
}); 
		  