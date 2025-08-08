$(document).ready(function () {	
	var action = $("#action").val();
	var currDate= new Date();
	
	var cursorPosition = $("#cursorPosition").val();
	//CURSOR POSITION
	if(cursorPosition=="barNumber"){
		$("#barNumber").focus();
	}
	if(cursorPosition=="attorneyName"){
		$("#attorneyName").focus();
	}
	//CURSOR POSITION
	if (action == "ancillarySetting" || action == "ancillarySettingDisplay" || action == "ancillarySettingUpdate" || action == "ancillaryUpdateFromCourtActivity"){
	$("#courtId").attr("disabled",true);
	$("#courtDate").attr("disabled",true);
	$("#courtTime").focus();
	}
/*	if (action == "ancillaryCourtActivity"){
		$("#petitionNumber").attr("disabled",true);
	}*/ //Bug 70797 Iterative processing required
	if (action == "ancillarySettingUpdate" || action == "ancillaryUpdateFromCourtActivity"){
		$("#filingDate").attr("disabled",true);
	}
	if(typeof $("#courtDate").val() != "undefined"){
		$("#courtDate").datepicker({
			  beforeShowDay: $.datepicker.noWeekends,changeYear: true ,changeMonth: true
		});
	}
	if (action == "ancillarySettingUpdate" || action == "ancillaryUpdateFromCourtActivity"){
		if($("#barNumber").val().trim()== "0"){
	    		$("#barNumber").val("");
    	}
	}

	if(typeof $("#filingDate").val() != "undefined"){  //Filling Date cannot be a future date. But an back date
		datePickerSingle($("#filingDate"),"Filing Date", true);
		//datePickerSingleMaxDte($("#filingDate"),"Filing Date", true,$("#courtDate"),"Court Date");
	}
	
	//the below code handles the Ancillary Display option from the Ancillary Add Page
	$("#ancillarySettingDisplayBtn").on("click",function(){
		var courtDate = $("#courtDate").val();
		var courtId =  $("#courtId").val();
		window.location.href = '/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=ANCILLARY SETTING DISPLAY&courtId='+courtId+'&courtDate='+courtDate;
	});
	
	$("#updateAncillarySettingBtn").on('click', function(){
	
		var checkedRadioVal = $("input[name='docketEventsRec']:checked").val();
		if(	checkedRadioVal == null  ){
		alert("Please Select The Ancillary Setting To Be Updated By Clicking The Radio Button In The Left." );
		return false;
		} 
		
		if ( true ) {
			spinner();
		}
	});
	
	//validateBarNumber
	$("#validateBarNumberBtn").on('click', function(){
		
		if(	$("#barNumber").val() == null || $("#barNumber").val() == "" ){
		alert("Please Enter a Bar Number To Validate And Retry." );
		$("#barNumber").focus();
		return false;
		} 	
		
		if($("#barNumber").val()!="" && $.isNumeric($("#barNumber").val())==false){
 			alert("Bar Number Must be Numeric. Please Correct And Retry.");
 			$("#barNumber").focus();
 			return false;
 		}
		
		if ( true ) {
			spinner();
		}
	});
	
	
    $("#submitAncillarySettingAddBtn, #updateSettingBtn").on('click', function (e) {
    	var courtTime = $("#courtTime").val().trim();
    	var courtStartTime = new Date(0,0,0,07,0,0,0);
    	var courtEndTime = new Date(0,0,0,18,0,0,0);
    	var curCrtTime = new Date(0,0,0,courtTime.substring(0,2),courtTime.substring(2,4),0,0).getTime();
    	var petitionNum = $("#petitionNumber").val().trim();
    	var typeCase = $("#typeCase").val();
    	var respondentName =  $("#respondentName").val().trim();
    	var hearingType = $("#hearingType").val(); //setting reason
    	var barNumber = $("#barNumber").val();
    	var attorneyName = $("#attorneyName").val();
    	var attorneyConnection = $("#attorneyConnectionHAT").val();
    	var filingDate = new Date($("#filingDate").val());
    	var courtDate = new Date($("#courtDate").val());
    	
    	if(courtTime=="")
    	{
    		alert("Court Time is Missing. Please enter Court Time in HHmm format.")  
    		$("#courtTime").focus();
    		  return false;
    	}
    	else
    	{
        	 var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
        	 if(timeFormatRegex.test($("#courtTime").val())== false){
     			  alert("Court Time Minutes Must Be From 00 To 59, Please Correct.");
     			  $("#courtTime").focus();
     			 $("#courtTime").val("");
     			  return false;
     		  }
        	 else {
        		//Check court time between 7Am and 6PM. Military Format.
        		 if (curCrtTime < courtStartTime || curCrtTime > courtEndTime){
        			  alert("Court Time Must Be From 0700 To 1800, Please Correct.");
             		 $("#courtTime").focus();
             		 $("#courtTime").val("");
             		return false;
        			  }
        	 	}
    	}
    	
    	//Petition Number Validation.
    	if(typeof petitionNum!="undefined"){
    		if(petitionNum==""){
	    		alert("Petition Number Missing.");
	    		$("#petitionNumber").focus();
	    		return false;
	    	}
	    	else if (petitionNum.length < 6){
	    		alert("Petition Number must between 6 to 12 characters.");
	    		$("#petitionNumber").focus();
	    		return false;
	    	}
	    	else{
	    	var alphanumericName = /^[a-zA-Z0-9-]+$/;
	    	if(alphanumericName.test(petitionNum) == false){
	    		alert("Petition Number Must be Alphanumeric and Symbol Hyphen is Allowed.");
	    		$("#petitionNumber").focus();
	    		return false;
	    	}
	    		
	    	}
    	}
    	
    	//typeCase validation
    	if(typeof typeCase!="undefined"){
    		if(typeCase==""){
	    		alert("Type Case Missing. Please Select From The Drop Down Menu.");
	    		$("#typeCase").focus();
	    		return false;
	    	}
    	}
    	
    	//respondentName validation
    	if(typeof respondentName!="undefined"){
    		if(respondentName==""){
	    		alert("Respondent Name is Required. Please Correct.");
	    		$("#respondentName").focus();
	    		return false;
	    	}
    	else{
    		var alphanumericSymbolName = /^[a-zA-Z0-9 \.\\\\';,()\x26\x2f\-]*$/;
    		//var numericRegExp = /^[0-9]*$/;
    		if (alphanumericSymbolName.test(respondentName) == false){
    			alert("Respondent Name Must be Alphanumeric or Symbols.");
	    		$("#respondentName").focus();
    			return false;
    			}
    		}
    	}
    	
    	//typeCase validation
    	if(typeof hearingType!="undefined"){
    		if(hearingType==""){
	    		alert("Setting Reason is Required. Please Select From The Drop Down Menu.");
	    		$("#hearingType").focus();
	    		return false;
	    	}
    	}
    	if (action == "ancillarySetting"){ //only mandatory for Ancillary Add
    		//barNum validation
	    	if(typeof barNumber!="undefined"){
	    		if(barNumber != ""){
	    			var numericRegExp = /^[0-9]*$/;
		    		if (numericRegExp.test(barNumber) == false){
		    			alert("Bar Number Must be Numeric.");
			    		$("#barNumber").focus();
		    			return false;
		    			}
	    		}
	    		/*if(barNumber==""){
		    		alert("Attorney Bar Num is Required. Please Correct.");
		    		$("#barNumber").focus();
		    		return false;
		    	}
	    	else{ //barNum Numeric validation
	    		var numericRegExp = /^[0-9]*$/;
	    		if (numericRegExp.test(barNumber) == false){
	    			alert("Bar Number Must be Numeric.");
		    		$("#barNumber").focus();
	    			return false;
	    			}
	    		}*/ // BUG 82265 
	    	}
	    	//attoneyName validation
	    /*	if(typeof attorneyName!="undefined"){
	    		if(attorneyName==""){
		    		alert("Attorney Name is Required. Please Correct.");
		    		$("#attorneyName").focus();
		    		return false;
		    	}
	    	}*/ // BUG 82265 
	    	//Attorney Connection validation
	    	/*if(($("#attorneyConnectionHAT").is(':unchecked') && $("#attorneyConnectionAAT").is(':unchecked') ))
	    		{
	            alert('Please Select an Option (HAT/AAT) for Attorney Connection.');
	            return false;
	    		}*/ // BUG 82265 
	    	//Filing date validation: Cannot be after Court Date
	    	if(typeof filingDate!="undefined"){
	    		if($("#filingDate").val()==""){
		    		alert("Filing Date Value Missing, Please Correct And Retry.");
		    		$("#filingDate").focus();
		    		return false;
		    	}
	    	}
    	}
    	if (action == "ancillarySettingUpdate" || action == "ancillaryUpdateFromCourtActivity"){
     		//barNum Numeric validation on Update
	    	if(typeof barNumber!="undefined"){
	    		if(barNumber!=""){
	    		var numericRegExp = /^[0-9]*$/;
	    		if (numericRegExp.test(barNumber) == false){
	    			alert("Bar Number Must be Numeric.");
		    		$("#barNumber").focus();
	    			return false;
	    			}
	    		}
	    	}
    	}
    		if (filingDate.getTime() > courtDate.getTime()){
	    			alert("Filing Date Cannot be After Court Date");
	    			return false;
	    		}
    		if ( true ) {
    	 		spinner();
    	 	}
    	
    	//Get the issueFlag of the selected Setting Reason/Hearing Type
    	var hearingTypesABList = $("#hearingTypesList").val();
    	var selectedHearingType = $("#hearingType").val();
	 	$.each(jQuery.parseJSON(hearingTypesABList), function(idx, obj) {
	 		if(obj.code == selectedHearingType){
	 			var issueFlag = obj.issueFlag;
	 			$("#issueFlag").val(issueFlag);
	 			return false;
	 		}
		});
	 	
	 	

    });
 
    
    //default Enter key press to Submit Button
    $("input").bind("keydown", function(event) {
	  // track enter key
	  var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
	  if (keycode == 13) { // keycode for enter key
	         // force the 'Enter Key' to implicitly click the submit button
	         $("#submitAncillarySettingAddBtn").click();
	         $("#updateSettingBtn").click();
	        // $("#submitAncillarySettingAddBtn").attr("disabled", "disabled");
	         return false;
	      } else  {
	         return true;
	      }
	}); // end of function
		
});