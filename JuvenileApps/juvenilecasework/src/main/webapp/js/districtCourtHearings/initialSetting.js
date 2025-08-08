$(document).ready(function () {
	var action = $("#action").val();
	var actionError = $("#actionError").val();
	var cursorPosition = $("#cursorPosition").val();
	var actionMsg = $("#actionMessage").val(); 
	var currDate= new Date(); //current Date	
	var isCourtActionReady = $("#isCourtActionReady").val();
	
	if(isCourtActionReady == "false"){
		$("#courtActionBtn").attr("disabled",true);
	}
	
	$("#hearingCorrectionBtn").attr("disabled",true);	
	if(action=="subpoenas"||action=="initialSetting"||actionError!=""||actionMsg!="")
	{	
		$("#petitionCorrectionBtn").attr("disabled",true);//setting petition correction button disabled when hearing info is present or no petition present		
	}
	if(action=="subpoenas")
	{	
		$("#hearingCorrectionBtn").attr("disabled",false);		
	}
	//pre-populate on click of back.
	var subpoenasTobePrinted = $('#selectedSubpoenas').val().split("-");
	if(typeof  subpoenasTobePrinted!= "undefined"){
		$.each(subpoenasTobePrinted, function(key, value) {
			$("#"+value).prop('checked',true);
		});
	}
	
	//CURSOR POSITION
	if(cursorPosition=="Allegation"){
		$("#allegation").focus();
	}
	if(cursorPosition=="courtId"){
		$("#courtId").focus();
	}
	if(cursorPosition=="hearingType"){
		$("#hearingType").focus();
	}
	if(cursorPosition=="barNumber"){
		$("#barNumber").focus();
	}
	//CURSOR POSITION
	
	//holiday check calendar.
	var juvenileDatesList = $("#holidaysList").val();
	if(typeof $("#courtDate").val() != "undefined"){
		datePickerSingleHolidaysWithBackDate($("#courtDate"),"Court Date",false,juvenileDatesList,$("#filingDate").val(),"Filing Date");
	}
		
	//On load
	/*if($("#petitionStatus").val()=="F"){
		$("#petitionAmendment").val("");
		$("#petitionAmendment").attr("disabled", "disabled");// disabled it when petition is created. Amendment comes only during a petition update.
	
	}*/
	//atleast one petition should exists.If a Court calendar record is found for the JUV#/Referral# combination but a referral record is not found, user cannot modify/populate Hearing Information fields (Court, Date, Time or Hearing Type, 
	//Attorney Bar Number or Attorney Connection). Petition fields are modifiable.
	if(typeof $("#petitionType").val() != "undefined" && $("#petitionType").val()=="" || ($("courtId").val()!="" && $("referralDate").val()=="")){
	  		$("#hearingTbl").find("input,select").attr("disabled", "disabled");
	  		$("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
	  		if($("#actionError").val()==""){
	  			$("#petitionStatus").focus();
	  		}
	}
	// DISABLE IF PETITION ALREADY EXISTS.
	if(action=="courtHearing"){
		if(typeof $("#allegation").val() != "undefined" && $("#allegation").val()!=""){
		    $("#petitionTbl").find("input,select").attr("disabled", "disabled");
		    $("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
		    if($("#actionError").val()==""){
		    	$("#courtId").focus();
		    }
		 }
		
		//Determine if the status = ‘Filed or Re-open’ on the Petition Record.  
		//If true, display “SUBPOENAS CAN NOT BE PRINTED – SELECT PETITION UPDATE OPTION.”  User cannot modify or populate any field.
		if(typeof $("#petitionStatus").val() != "undefined" && ($("#petitionStatus").val()=="F" || $("#petitionStatus").val()=="R")&& ($("#courtDate").val()!="" && $("#actionError").val()=="")){
				$("#petitionTbl").find("input,select").attr("disabled", "disabled");
				$("#hearingTbl").find("input,select").attr("disabled", "disabled");
		   	//	$("#subpoenaTbl").find("input,select").attr("disabled", "disabled"); Not right Discussed with regina. Applies for reprint.
		   	//	$("#submitInitialSettingBtn").attr("disabled", "disabled");
		}
	}
	
	if(action=="subpoenas"){
		$("#petitionTbl").find("input,select").attr("disabled", "disabled");
		$("#hearingTbl").find("input,select").attr("disabled", "disabled");
		$("#subpoenaTbl").find("input,select").attr("disabled", false); 
	}
	
	var isMotherIncarceratedIdval = $("#isMotherIncarceratedId").val();
	var isFatherIncarceratedIdval = $("#isFatherIncarceratedId").val();
	var isFatherDeceasedIdval = $("#isFatherDeceasedId").val();
	var isMotherDeceasedIdval = $("#isMotherDeceasedId").val();
	
	if(isMotherIncarceratedIdval=="Y"){
		$("#M").attr("disabled",true); 
	}
	if(isFatherIncarceratedIdval=="Y"){
		$("#F").prop("disabled",true);
	}
	if(isFatherDeceasedIdval=="Y"){
		$("#F").prop("disabled",true);
	}
	if(isMotherDeceasedIdval=="Y"){
		//$("#M").prop("disabled",true);
		$("#M").attr("disabled",true);
	}
	
	
	if(action=="validateOffenseCd"){
	  	$("#hearingTbl").find("input,select").attr("disabled", "disabled");
	  	$("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
	   	$("#petitionStatus").focus();
	}
	
	if(action=="validateBarNumber" || action=="validateBarNumber"){
		$("#petitionTbl").find("input,select").attr("disabled", "disabled");
	  	$("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
	   	$("#barNumber").focus();
	}
	if(action=="searchAttorney"){
		$("#petitionTbl").find("input,select").attr("disabled", "disabled");
	  	$("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
	   	$("#attorneyName").focus();
	}
	
	if(	$("#hearingCorrectionFlg").val()=="hearingCorrection"){
		  $("#hearingTbl").find("input,select").attr("disabled", false);
		  $("#hearingCorrectionBtn").attr("disabled",true);
		  $("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
	}
	if(	$("#petitionCorrectionFlg").val()=="petitionCorrection"){
		  $("#petitionTbl").find("input,select").attr("disabled", false);
		  $("#petitionCorrectionBtn").attr("disabled",true);
		  $("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
	}
	/*$("#petitionStatus").on('change',function(){
		if($("#petitionStatus").val()=="F"){
			$("#petitionAmendment").val("");
			$("#petitionAmendment").attr("disabled", "disabled");// disabled it when petition is created. Amendment comes only during a petition update.
		
		}else{
			$("#petitionAmendment").attr("disabled", false);
		}
	});*/
	
	if(typeof $("#filingDate").val() != "undefined"){  //cannot be before referral date and can't  have a future date.
		datePickerSingleMinMaxDte($("#filingDate"),"Filing Date", true, $("#referralDate").val(),"Referral Date");
	}
	
	if(typeof $("#preparationDate").val() != "undefined"){
		datePickerSingle($("#preparationDate"),"Preparation Date",false);
	}
	
    $("#submitInitialSettingBtn").on('click', function (e) {
    	var currentYear = (new Date).getFullYear();
    	var petitionNum = $("#petitionNumber").val().trim();
    	var petitionStatus = $("#petitionStatus").val();
    	var filingDate = $("#filingDate").val();
    	var petitionType =  $("#petitionType").val().trim();
    	var referralDate = $("#referralDate").val();
    	var petitionAllegation = $("#allegation").val();
    	

    	//Petition Number Validation.
    	if(typeof petitionNum!="undefined"){
	    	if(petitionNum==""){
	    		alert("Petition Number Missing.");
	    		$("#petitionNumber").focus();
	    		return false;
	    	}else{
	    		if(petitionNum.toLowerCase().match("^j")) {
	    			if((petitionNum.length<8 || petitionNum.length>=9)){ //bug fix #58789
	    				alert("If petition number entry begins with ‘J’ (using a juvenile number), must be followed by 7 digits.");
	    				$("#petitionNumber").focus();
	    				return false;
	    			}else{
	    				if($.isNumeric(petitionNum.substring(1,8))==false){
	    					alert("If petition number entry begins with ‘J’ (using a juvenile number), must be followed by 7 digits.");
		    				$("#petitionNumber").focus();
		    				return false;
	    				}
	    			}
	    		}
	    		else
	    		{
	    			if((petitionAllegation=="RESEAL" || petitionAllegation=="AUSEAL") && petitionStatus=="R"){}	    				
	    			else
	    			{
		    			if(petitionNum=="1998" || petitionNum=="1999"){
				    		if(petitionStatus!="R"){
				    			alert("Invalid Petition Number - First 4 Digits Wrong.");
				    			$("#petitionNumber").focus();
				    			return false;
				    		}
				    	}else if(petitionStatus=="F" && petitionNum.length>10){
				    		alert("Invalid Petition Number - Must Be 9 Digits With J On End.");
			    			$("#petitionNumber").focus();
			    			return false;
				    	}	    			
				    	else if($.isNumeric(petitionNum.substring(0,9))==false){			    		
				    		alert("Invalid Petition Number - First 9 Digits Wrong.");
			    			$("#petitionNumber").focus();
			    			return false;
				    	}else if(new Date(petitionNum.substring(0,4)).getFullYear()>currentYear){
				    		alert("Invalid Petition Number - First 4 Digits Wrong.");
			    			$("#petitionNumber").focus();
			    			return false;
				    	}else if(((petitionNum.substring(0,4)=="1998" || petitionNum.substring(0,4)=="1999") && $.isNumeric(petitionNum.substring(4,9)==false)) || petitionNum.length>10){
				    		alert("Invalid Petition Number - First 9 Digits Wrong.");
			    			$("#petitionNumber").focus();
			    			return false;
				    	}else if(!petitionNum.substring(petitionNum.length-1).toLowerCase().match("j$")){
				    		alert("Invalid Petition Number - Must End With J."); 
			    			$("#petitionNumber").focus();
			    			return false;
				    	}
		    			
		    			/*if(petitionNum=="J" || petitionNum=="j"){
			    		alert("Petition Number Must Be Greater Than Zero.");
			    		$("#petitionNumber").focus();
			    		return false;
			    		}*/ //NOT VALID AS it gets covered with juvenile no check
	    			}
	    			
	    		}//end of else(2)
	    	} //end of else(1)
		    	
    	}
    	
    	//petition Date Validation.
    	if(typeof filingDate!="undefined"){
    		var currDate= new Date();
    		var currTime = currDate.getHours() + ":" + currDate.getMinutes();
     		var currTimeStr ="";
         	if(typeof currTime != "undefined" && currTime!=""){
         	  currTimeStr =  new Date(0,0,0,currTime.split(":")[0],currTime.split(":")[1],0,0).getTime();
         	}
    		if(filingDate==""){
	    		alert("Petition Date Missing.");
	    		$("#filingDate").focus();
	    		return false;
	    	}else{
		 		filingDate = new Date(filingDate);
		 		referralDate = new Date(referralDate);
		 		
		 		if(filingDate < referralDate){
		 			alert("Petition Date Cannot Be Before Referral Date - "+ $("#referralDate").val()+".");
		    		$("#filingDate").focus();
		    		return false;
		 		}
	    	}
    	}
    	
    	//petitionType validation
    	if(typeof petitionType!="undefined"){
    		if(petitionType==""){
	    		alert("Petition Type Missing.Petition Type Must Be D or C.");
	    		$("#petitionType").focus();
	    		return false;
	    	}
    	}
    	
    	//petitionStatus validation
    	if(typeof petitionStatus!="undefined"){
    		if(petitionStatus==""){
	    		alert("Petition Status Missing. Petition Status Must Be Filed or Reopened.");
	    		$("#petitionStatus").focus();
	    		return false;
	    	}
    	}
    	
    	if(typeof petitionAllegation!="undefined"){
    		if(petitionAllegation==""){
	    		alert("Allegation Value Missing, Please Correct And Retry.");
	    		$("#allegation").focus();
	    		return false;
	    	}
    	}
    	
    
    	if(action=="courtHearing" || action=="validateBarNumber" || action=="searchAttorney")// bug no: 84856
    	{
    		 // court validation.
	    	 // Court hearing validation
    		var courtId = $("#courtId").val();
	        if($("#courtId").val()==""){ //courtID
	    		alert("Court Value Missing.");
	    		$("#reqJuvNum").hide();
	    		$("#reqRef").hide();
	    		$("#reqDate").show();
	    		$("#reqCourtId").show();
	    		$("#courtId").focus();
	    		return false;
	    	}else{ 
	    		$("#petitionCorrectionBtn").attr("disabled",true);///setting petition correction button when hearing info is present	    		
	    	    if(courtId!="313" && courtId!="314" && courtId!="315"){
	    			alert("Invalid  Court, Valid Courts are 313, 314, 315.");
	    			$("#reqJuvNum").hide();
	    			$("#reqRef").hide();
	    			$("#reqDate").show();
	    			$("#reqCourtId").show();
	    			$("#courtId").focus();
	    			return false;
	    	   }
	    	}
	        if($("#courtTime").val()==""){ //court Date
	    		alert("Invalid Court Time, Please Correct and Retry.");
	    		$("#reqJuvNum").hide();
	    		$("#reqRef").hide();
	    		$("#reqDate").show();
	    		$("#reqCourtId").show();
	    		$("#courtTime").focus();
	    		return false;
	    	}else{
	    		 var time = $("#courtTime").val();
	    		 var courtTime = new Date(0,0,0,time.substring(0,2),time.substring(2,4),0,0).getTime();
	    		 var startTime = new Date(0,0,0,07,00,0,0).getTime(); //7 am
	    		 var endTime = new Date(0,0,0,18,00,0,0).getTime(); //6 pm
	    		 
	    		 var value = timeValidationHHMMFormat($("#courtTime"),"Court Time");
	    		 if(value==false){
	    			 return false;
	    		 }else{
	    			// 7 am and 6pm validation.
	    			if(courtTime<startTime || courtTime>endTime){
	    	    		alert("Invalid Court Time, Please Correct.")
	    	    		$("#courtTime").focus();
	    	    		return false;
	    	    	}
	    		 }
	    	}
	        if($("#courtDate").val()==""){ //court Date
	    		alert("Court Date is required.");
	    		$("#reqJuvNum").hide();
	    		$("#reqRef").hide();
	    		$("#reqDate").show();
	    		$("#reqCourtId").show();
	    		$("#courtDate").focus();
	    		return false;
	    	}
	
	        if($("#hearingType").val()==""){
	        	alert("Hearing Type Missing. Please enter Hearing Type.");
	        	$("#hearingType").focus();
	        	return false;
	        }

	    	if($("#barNumber").val()!="" && $.isNumeric($("#barNumber").val())==false){
	    			alert("Bar Number Is Numeric. Please Correct And Retry.");
	    			$("#barNumber").focus();
	    			return false;
	    	}
    	}
        
    	if(action=="subpoenas" && $("#hearingCorrectionFlg").val()==""){
    		if($("#plaintiff").val()=="" || $("#plaintiff").val().trim().length<2){
	        	alert("Invalid Plaintiff Name, Correct And Click Submit.");
	        	$("#plaintiff").focus();
	        	return false;
	        }
    		if(!$('input[name=selectedSubpoenasToBePrinted]').is(':checked')){
    			alert("User Must Identify At Least One Subpoena Party Type To Print Subpoenas");    			
    			//$("#J").focus();
    			//bug fix for 133866
    			$("#J").css("box-shadow","1px 1px 2px 2px grey");
    			return false;
    		}
    		else
    		{  
    			
	        	var subpoenasTobePrinted = $('input:checked[name=selectedSubpoenasToBePrinted]').val();
	        	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtInitialSetting.do?submitAction=Submit&data="+subpoenasTobePrinted);
	        	/*spinner();
	        	alert("Please wait until the subpeona is generated, click OK to proceed. Do not re-submit.")
	        	$.ajax({
	    	        url: "/JuvenileCasework/submitJuvenileDistrictCourtInitialSetting.do?submitAction=Submit&data="+subpoenasTobePrinted,
	    	        type: 'post',
	    	        success: function(data, textStatus , xhr) {
	    	        	if(100==xhr.status)
	    	        	{
	    	         		$(".overlay").remove();	 	    	         		
	    	         		setTimeout(function(){alert("Document can be located in the Download folder")}, 1000);	
	    	        	}
	    	        },
	    	        error: function (jqXhr, textStatus, errorMessage) { // error callback 
	    	        	$(".overlay").remove();
	    	            console.log(errorMessage);
	    	        }
	    	    });*/
	        }
    	}
    });
    
   /* function submitForm(val,id){
		//create an iframe dynamically
		var ifrm = document.createElement('iframe');
		ifrm.setAttribute('id', id);
		//ifrm.setAttribute('name', id); //when not set opens new tabs and closes.
		$("#pdf-download").append(ifrm); // append it to an div
		$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtInitialSetting.do?submitAction=Submit&data="+val);
		$('form').submit();
		
		//remove once submitted iframe dynamically
		if($("iframe[id^=content]")){
			$("iframe[id^=content]").remove();
			$('form').removeAttr("target");
		}
		///jQuery.ajaxSetup({ cache: false });
    }*/
    
	//bug fix #63228
    
    $("#initialSettingCourtMainMenuBtn").on('click', function (e) {
    	spinner();
    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtInitialSetting.do?submitAction=Court Main Menu");
		$('form').submit();
    });
    
    $("#petitionUpdateBtn").on('click', function (e) {
    	var subpoenasTobePrinted = $('input:checked[name=selectedSubpoenasToBePrinted]');
    	if(typeof subpoenasTobePrinted.val() == "undefined"){
    		$("#selectedSubpoenas").val("noSubpoenasSelected");
    	}
    	spinner();
    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtInitialSetting.do?submitAction=Petition Update");
		$('form').submit();
    });
    
    //bug fix #63228
     
    if(typeof $("#finalDispEntered").val() != "undefined" && $("#finalDispEntered").val()=="false"){
    	if($("#petitionStatus").val()=="R"){
    		alert("Reopen Not valid, No Final Decision Found.");
    		$("#petitionStatus").focus();
    	}
    	return false;
    }
    
    // validate offense code - allegation
    $("#validateCode").on('click', function (e) {
    	var fld = $("#allegation").val();
    	var val = Trim(fld);
    	if (val == "")
    	{
    		alert("Allegation Value Missing, Please Correct And Retry.")
    		$("#allegation").focus();
    		return false;
    	}
    	$("#allegation").val(val.toUpperCase());
    	if ( true ) {
    		spinner();
    	}
    	return true;
    });
    
    //default to submit button on enter
    $(document).bind('keypress', function(e) {
    	// track enter key
	  var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
	  if (keycode == 13) { // keycode for enter key
	         // force the 'Enter Key' to implicitly click the submit button
	         $("#submitInitialSettingBtn").click();
	         return false;
	      } else  {
	         return true;
	      }
	}); // end of function
    
    $("#validateBarNumber").on('click', function (e) {
    	  if($("#barNumber").val().trim()==""){
    		  alert("Please Enter A Value To Validate Bar Number. Please Correct And Retry.");
    		  $("#barNumber").focus();
    		  return false;
    	  }
    	
    	  if($("#barNumber").val()!="" && $.isNumeric($("#barNumber").val())==false){
    			alert("Bar Number Is Numeric.Please Correct And Retry.");
    			$("#barNumber").focus();
    			return false;
    		}
    	  
    	  if ( true ) {
    		  spinner();
    	  }
    });	
    
    $("#petitionCorrectionBtn").on('click', function (e) {
    	spinner();
    	$("#petitionCorrectionBtn").attr("disabled",true);
    	$("#petitionTbl").find("input,select").attr("disabled", false);
    	$("#hearingTbl").find("input,select").attr("disabled", "disabled");
    	$("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
    	action="petitionChange";
    	//alert(action);
    	$("#petitionCorrectionFlg").val("petitionCorrection");
    	$(".overlay").remove();
    	return false;
    });
    
    $("#hearingCorrectionBtn").on('click', function (e) {
    	spinner();
    	$("#hearingCorrectionBtn").attr("disabled",true);
    	$("#hearingTbl").find("input,select").attr("disabled", false);
    	$("#subpoenaTbl").find("input,select").attr("disabled", "disabled");
    	$("#petitionTbl").find("input,select").attr("disabled", "disabled");
    	$("#attorneyName").attr("disabled",true);
    	action="courtHearing";
    	$("#hearingCorrectionFlg").val("hearingCorrection");
    	$(".overlay").remove();
    	return false;
    });
    
    $("#courtActionBtn").click(function(){
    	spinner();
        $('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtInitialSetting.do?submitAction=Court Action');
         $(this).prop("disabled",true);
        $('form').submit();
    });
    
    $("#submitReferralSearch").click(function(){
    	spinner();
        $('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtInitialSetting.do?submitAction=Referral Summary');
         $(this).prop("disabled",true);
        $('form').submit();
    });
    
});