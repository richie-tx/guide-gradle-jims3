$(document).ready(function () {	
	var action = $("#action").val();
	var actionError = $("#actionError").val();
	var cursorPosition = $("#cursorPosition").val();
	var currDate= new Date(); //current Date	
    var backDate = new Date("01/01/1960");
    var fmtBackDate=  (backDate.getMonth() + 1) + '/' + backDate.getDate() + '/' +  backDate.getFullYear();
    var fmtBackDateStr = fmtBackDate;

	//holiday check calendar.
	var juvenileDatesList = $("#holidaysList").val();
	if(typeof $("#courtDate").val() != "undefined"){
		datePickerSingleHolidaysWithBackDate($("#courtDate"),"Court Date",false,juvenileDatesList,$("#filingDate").val(),"Filing Date");
	}

	if(typeof $("#filingDate").val() != "undefined"){  //cannot be before referral date and can't  have a future date.
		datePickerSingleMinMaxDte($("#filingDate"),"Filing Date", true, $("#referralDate").val(),"Referral Date");
	}
	
	if(typeof $("#amendmentDate").val() != "undefined"){  
		datePickerSingleBackDate($("#amendmentDate"),"Amendment Date",false,fmtBackDate);
	}
	
	if(typeof $("#preparationDate").val() != "undefined"){
		datePickerSingle($("#preparationDate"),"Preparation Date",false);
	}
	
	//pre-populate on click of back.
	var subpoenasTobePrinted = $('#selectedSubpoenas').val().split("-");
	if(typeof  subpoenasTobePrinted!= "undefined"){
		$.each(subpoenasTobePrinted, function(key, value) {
			$("#"+value).prop('checked',true);
		});
	}
	
/*	var isMotherIncarceratedIdval = $("#isMotherIncarceratedId").val();
	var isFatherIncarceratedIdval = $("#isFatherIncarceratedId").val();
	if(isMotherIncarceratedIdval=="Y"){
		$("#M").prop("disabled",true);
	}
	if(isFatherIncarceratedIdval=="Y"){
		$("#F").prop("disabled",true);
	}*/
	/*if(isFatherDeceasedId=="Y"){
		$("#M").prop("disabled",true);
	}
	if(isMotherDeceasedId=="Y"){
		$("#M").prop("disabled",true);
	}*/
	
	// cannot delete as such .. need to come back from main menu and delete the record.
	if(action=="submitPetitionUpdate"){
		$("#deletePetitionBtn").prop("disabled",true);
		$("#submitBtn").prop("disabled",true);
		$("#validateOffenseBtn").prop("disabled",true);
		$("#searchForOffenseBtn").prop("disabled",true);
	}
	if(action=="deletePetition"){
		$("#submitBtn").prop("disabled",true);
		$("#printSubpoenasBtn").prop("disabled",true);
		$("#deletePetitionBtn").prop("disabled",true);
		$("#validateOffenseBtn").prop("disabled",true);
		$("#searchForOffenseBtn").prop("disabled",true);
	}

	//call back function.validate on click of submit and press of enter key
	var validate = function(){
	 	var currAction = $("#currAction").val("submitPetitionUpdate");
    	var currentYear = (new Date).getFullYear();
    	var petitionNum = $("#petitionNumber").val().trim();
    	var petitionStatus = $("#petitionStatus").val();
    	var filingDate = $("#filingDate").val();
    	var petitionType =  $("#petitionType").val().trim();
    	var referralDate = $("#referralDate").val();
    	var petitionAllegation = $("#allegation").val();
    	var petitionAmendment = $("#petitionAmendment").val();
    	var amendmentDate = $("#amendmentDate").val();

    	//Petition Number Validation.
    	if(typeof petitionNum!="undefined"){
	    	if(petitionNum==""){
	    		alert("Petition Number Missing.");
	    		$("#petitionNumber").focus();
	    		return false;
	    	}else{
	    		if(petitionNum.toLowerCase().match("^j")) {
	    			if((petitionNum.length<8 || petitionNum.length>=9)){ //bug fix #58789
	    				alert("If petition number entry begins with 'J' (using a juvenile number), must be followed by 7 digits.");
	    				$("#petitionNumber").focus();
	    				return false;
	    			}else{
	    				if($.isNumeric(petitionNum.substring(1,8))==false){
	    					alert("If petition number entry begins with 'J' (using a juvenile number), must be followed by 7 digits.");
		    				$("#petitionNumber").focus();
		    				return false;
	    				}
	    			}
	    		}
	    		else{
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
				    	}else if($.isNumeric(petitionNum.substring(0,9))==false){
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
	    			}
	    		}//end of else(2)
	    	} //end of else(1)
		    	
    	}
    	
    	//petition Date Validation.
    	if(typeof filingDate!="undefined"){
    		filingDate = new Date(filingDate);
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
		 		referralDate = new Date(referralDate);
		 		if(filingDate < referralDate){
		 			alert("Petition Date Cannot Be Before Referral Date - "+ $("#referralDate").val()+".");
		    		$("#filingDate").focus();
		    		return false;
		 		}
	    	}
    		//1960 validation bug#64270
    		fmtBackDate = new Date(fmtBackDate);
    		if(filingDate< fmtBackDate){
    		 	alert("Filing Date cannot be before "+fmtBackDateStr+".");
    		 	$("#filingDate").focus();
    		 	return false;
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
    	
    	if(typeof petitionAmendment!="undefined"){
    		if(petitionAmendment!=""){
    			if(amendmentDate==""){
    				alert("Amendment Date is required, if amendment number is selected.");
    				$("#amendmentDate").focus();
    				return false;
    			}
    			//1960 validation bug#64270
    			amendmentDate = new Date(amendmentDate);
        		fmtBackDate = new Date(fmtBackDate);
        		if(amendmentDate< fmtBackDate){
        		 	alert("Amendment Date cannot be before "+fmtBackDateStr+".");
        		 	$("#amendmentDate").focus();
        		 	return false;
        		 }
	    	}
    	}
    	
    	if(typeof amendmentDate!="undefined"){
    		if(amendmentDate!=""){
    			if(petitionAmendment==""){
    				alert("Amendment Number is required, if amendment Date is selected.");
    				$("#petitionAmendment").focus();
    				return false;
    			}
	    	}
    	}
    	//if no errors, submit the petition changes.
    	if ( true ){
    		spinner();
    	}
    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Submit");
    	$('form').submit();
 }
	
//On click of the submit button
 $("#submitBtn").click(validate);
 
//enter key		 
$(document).bind("keydown", function(event) {
    if (event.which == 13){
    	validate();
    }
});
	
 //on click of print subpoena button.
 $("#printSubpoenasBtn").on('click', function (e) {
	if(!$('input[name=selectedSubpoenasToBePrinted]').is(':checked')){
		alert("User Must Identify At Least One Subpoena Party Type To Print Subpoenas");
		$("#J").focus();
		return false;
	}
	 
 	if($("#plaintiff").val()=="" || $("#plaintiff").val().trim().length<2){
	        	alert("Invalid Plaintiff Name, Correct And Retry.");
	        	$("#plaintiff").focus();
	        	return false;
      }else{
	       var subpoenasTobePrinted = $('input:checked[name=selectedSubpoenasToBePrinted]').val();
	       $('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Print Subpoenas&data="+subpoenasTobePrinted);
    		/*spinner();
    		$.ajax({
    	        url: "/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Print Subpoenas&data="+subpoenasTobePrinted,
    	        type: 'post',
    	        success: function(data, textStatus , xhr) {
    	         	if (200 == xhr.status){
    	         		$(".overlay").remove();
    	         	}
    	        }
    	    });*/
    		$('form').submit();
	       /*	$.each(subpoenasTobePrinted, function(key, value) {
	       		var val = $(value).val();
	       		// if no member name, do not submit the form, skip that value.
	       		if(val=="F" && typeof  $("#filterFather").val() != "undefined"){
	       			return;
	       		}else if(val=="M" && typeof $("#filterMother").val()!="undefined"){
	       			return;
	       		}else if(val=="O"){
	       			if(typeof $("#filterCareGiver").val()!="undefined" && typeof $("#filterGuardian").val()!="undefined")
	       			return;
	       		}
	       		//create an id to match the iframe and target
	       		var id = "content" + val;
	       		$('form').attr('target',id);	        		
	       		submitForm($(value).val(),id);// interval to set multiple submits.
	       	});*/ 
	  }//else
 });
 
//submit subpoenas 
/* function submitForm(val,id){
		//create an iframe dynamically
		var ifrm = document.createElement('iframe');
		ifrm.setAttribute('id', id);
		//ifrm.setAttribute('name', id); //when not set opens new tabs and closes.
		$("#pdf-download").append(ifrm); // append it to an div
		$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Print Subpoenas&data="+val);
		$('form').submit();
		
		//remove once submitted iframe dynamically
		if($("iframe[id^=content]")){
			$("iframe[id^=content]").remove();
			$('form').removeAttr("target");
		}
		//jQuery.ajaxSetup({ cache: false });
 }
 */
 
/* //default to submit button on enter
 $(document).bind("keydown,click", function(event) {
	  // track enter key
	  var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
	  if (keycode == 13) { // keycode for enter key
	         // force the 'Enter Key' to implicitly click the submit button
	         $("#submitBtn").click();
	
	         return false;
	      } else  {
	         return true;
	      }
	}); // end of function
*/ 
	//bug fix #63228
 
 $("#deletePetitionBtn").on('click', function (e) {
	spinner();
 	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Delete Petition");
	$('form').submit();
 });
 
 $("#courtMainMenuBtn").on('click', function (e) {
	spinner();
 	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Court Main Menu");
		$('form').submit();
 });
 
 $("#validateOffenseBtn").on('click', function (e) {
	 spinner();
	 $('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Validate Offense");
		$('form').submit();
	 });
 
 $("#searchForOffenseBtn").on('click', function (e) {
	 spinner();
	 	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Search For Offense");
			$('form').submit();
	 });
 
 $("#back").on('click', function (e) {
	 	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtPetitionUpdate.do?submitAction=Back");
			$('form').submit();
	 });
 $("#submitReferralInquiry").on('click', function (e) {
 	$(this).prop("disabled",true);
 	spinner();
 	$('form').attr('action',"/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=REFERRAL INQUIRY");
		$('form').submit();
 });
 $("#courtActivity").click(function(e){
 	$(this).prop("disabled", true);
 	spinner();
 	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?fromPage=court&submitAction=courtActivityByYouth');
		$('form').submit();
 });
 
 if(typeof $("#finalDispEntered").val() != "undefined" && $("#finalDispEntered").val()=="false"){
 	if($("#petitionStatus").val()=="R"){
 		alert("Reopen Not valid, No Final Decision Found.");
 		$("#petitionStatus").focus();
 	}
 	return false;
 }
 
 // validate offense code - allegation
 $("#validateOffenseBtn").on('click', function (e) {
 	var fld = $("#allegation").val();
 	var val = Trim(fld);
 	if (val == "")
 	{
 		alert("Allegation Value Missing, Please Correct And Retry.")
 		$("#allegation").focus();
 		return false;
 	}
 	$("#allegation").val(val.toUpperCase());
 	if ( true ){
 		spinner();
 	}
 	return true;
 });
 
});