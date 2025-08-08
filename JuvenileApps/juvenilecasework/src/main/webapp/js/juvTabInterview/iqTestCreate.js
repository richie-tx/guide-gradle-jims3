$(document).ready(function () {


	//used for multiple test create pages
	if(typeof $("#recTestDate") != "undefined")
	{		
		datePickerSingle( $("#recTestDate"),"Test Date",true);
	}
	
	$("#validateIqTestForm").on("click", function() {
		
		var testDate = $("[name='iqRec.testDate']").val();
		var testName = $("[name='iqRec.testNameId']").val();
		var fullScore = $("[name='iqRec.fullScore']").val();
		var retVal = true;
		var msg = "";
		if( testDate == "" )
		{
			msg += "Date is required.\n";
			retVal = false;
		}else{
			
			if(!validateDate( testDate )){
				msg += "Date is not a valid date.\n";
				retVal = false;
			}
		}
		if( testName == "" )
		{
		    msg += "Name is required.\n";
		    retVal = false;
		}
		
		if( fullScore ==  "" )
		{
		    msg += "IQ Score(Composite/Full) is required.\n";
		    retVal = false;
		}else{
			
			if( !validateScore( fullScore )){
				msg+= "Full IQ Score must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}
		}
		if( $("[name='iqRec.performanceScore']").val() != ""){
			if( !validateScore($("[name='iqRec.performanceScore']").val())){
				msg+= "Performance Score must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}			
		}
		if( $("[name='iqRec.verbalScore']").val() != ""){
			if( !validateScore($("[name='iqRec.verbalScore']").val())){
				msg+= "Verbal Score must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}
		}
		if( $("[name='iqRec.verbalComprehension']").val() != ""){
			if( !validateScore($("[name='iqRec.verbalComprehension']").val())){
				msg+= "Verbal Comprehension must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}
		}
		if( $("[name='iqRec.perceptualReasoning']").val() != ""){
			if( !validateScore($("[name='iqRec.perceptualReasoning']").val())){
				msg+= "Perceptual Reasoning must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}
		}
		if( $("[name='iqRec.nonVerbalIQ']").val() != ""){
			if( !validateScore($("[name='iqRec.nonVerbalIQ']").val())){
				msg+= "Non-Verbal IQ must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}
		}
		if( $("[name='iqRec.processingSpeed']").val() != ""){
			if( !validateScore($("[name='iqRec.processingSpeed']").val())){
				msg+= "Processing Speed must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}
		}
		if( $("[name='iqRec.workingMemory']").val() != ""){
			if( !validateScore($("[name='iqRec.workingMemory']").val())){
				msg+= "Working Memory must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}
		}
		if( $("[name='iqRec.pictorialIQ']").val() != ""){
			if( !validateScore($("[name='iqRec.pictorialIQ']").val())){
				msg+= "Pictorial IQ must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}			
		}
		if( $("[name='iqRec.geometricIQ']").val() != ""){
			if( !validateScore($("[name='iqRec.geometricIQ']").val())){
				msg+= "Geometric IQ must be numeric. Value cannot exceed 200 and should be at least 2 digits.\n";
				retVal = false;
			}			
		}
		if(!retVal){
			
			alert( msg );
			return retVal;
		}else{
			return true;
		}		
	});
	
	function validateDate( testdate ) {
	    var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/ ;
	    return date_regex.test(testdate);
	 }
	
	function validateScore( score ) {
	    var score_regex = /^(0?[1-9][0-9]|1[0-9][0-9]|200)$/;
	    return score_regex.test(score);
	 }
	

});




