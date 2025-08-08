$(document).ready(function () {	
	
	// Varying field date inputs
	$("input[id^='resetToDate']").on("click", function( e ) {
		//$(this).datepicker().datepicker("show");	
		var input = $(this);
		  var val = input.val();
		  var $obj = this.id;
		  var docketEventId = $obj.split('-')[1];
		  datePickerSingle($("#resetToDate-"+docketEventId),"Date",false);
		  $("#resetToDate-"+docketEventId).focus();
	});
	
	if(typeof $("#refDate") != "undefined"){
		datePickerSingle($("#refDate"),"Date",false);
	}
	
	if(typeof $("#clDate") != "undefined"){
		datePickerSingle($("#clDate"),"Date",false);
	}
	
	if(typeof $("#intakeDate") != "undefined"){
		datePickerSingle($("#intakeDate"),"Date",false);
	}
	
	if(typeof $("#dispDate") != "undefined"){
		datePickerSingle($("#dispDate"),"Date",false);
	}
	
	if(typeof $("#pStartDate") != "undefined"){
		datePickerSingle($("#pStartDate"),"Date",false);
	}
	
	if(typeof $("#pEndDate") != "undefined"){
		datePickerSingle($("#pEndDate"),"Date",false);
	}
	
	if(typeof $("#pdaDate") != "undefined"){
		datePickerSingle($("#pdaDate"),"Date",false);
	}
	
	if(typeof $("#TJJDrefDate") != "undefined"){
		datePickerSingle($("#TJJDrefDate"),"Date",false);
	}
	
	//defaults all the radio buttons.
	var selectedVal=$("form input:radio");
	  $.each(selectedVal,function(index){ //loops through all the radio buttons.
		  if(typeof radio !== 'undefined' && $(this).val()!==''){ // 
			  $(this).prop("checked",'checked');
		  }
	  });
	  
	$("#selectRefBtn").on("click",function(){

		var referralOID        = $('input:radio[name=referralOID]:checked').val();
    	if( referralOID !="" ){     		
			$("#tempRefOID").val( referralOID ); //set it in the form
			console.log("tempRefOID : " + $("#tempRefOID").val() );
    		$('form').attr('action','/JuvenileCasework/UpdateMsReferralQuery.do?submitAction=Submit');
    		$('form').submit();
    		spinner();
    		 return true;
		 }

	});	
	
	$("#BtnRefresh").on("click",function(){
		//Clear fields on the form
		$("#juvNum").val("");
		$("#refNum").val("");

		});	

	$("#backToQryBtn").on("click",function(){
		//Clear fields on the form
		$('form').attr('action','/JuvenileCasework/UpdateMsReferralQuery.do?clr=Y');
		$('form').submit();
		 return true;

	});
	
	$("#submitButton").on("click",function(form){
	
		var msg = '';
		var allowUpdate =  true;
		var juvNum 	= $("#juvNum").val();
		var refNum 	= $("#refNum").val();
		
		if( juvNum == '' ){
			allowUpdate = false;
			msg = 'Juvenile Number is Required';
		}else{
			if( $.isNumeric( juvNum )){
				
			}else{
				allowUpdate = false;
				msg = 'Juvenile Number is invalid. Please retry search.';
			}
		}
		
		if( refNum != ''){
			
			if( $.isNumeric( refNum )){
				
			}else{
				allowUpdate = false;
				msg = 'Referral Number is invalid. Please retry search.';
			 }
		}
		
		
		if( !allowUpdate ){
    		alert( msg );
    		return false;
			
		 }else{
			 
			$('form').attr('action','/JuvenileCasework/UpdateMsReferralQuery.do?submitAction=Submit');
			$('form').submit();
			 return true;
		 }
		
	});
	
	
	$("#submitBtn").on("click",function(form){
		//docketTime
		var msg = '';
		var allowUpdate =  true;
		var prevJuvNum 	= $("#oldJuvenileNum").val();
		var juvNum 		= $("#juvNum").val();
		var prevRefNum 	= $("#oldReferralNum").val();
		var refNum 		= $("#refNum").val();
		var prevRefDate	= $("#oldReferralDate").val();
		var refDate		= $("#refDate").val();
		var refSource	= $("#referralSource").val();
		var intakeDec	= $("#intakeDecision").val();
		var piaStatus   = $("#piaStatus").val();
		var daLogNum    = $("#daLogNum").val();
		var transNum    = $("#transNum").val();
		var closeDate	= $("#clDate").val();
		var intakeDate  = $("#intakeDate").val();
		var courtNum	= $("#courtId").val();
		var disposition	= $("#crtDisposition").val();
		var crtResult	= $("#crtResult").val();
		var dispDate    = $("#dispDate").val();
		var ArrValidPIA = ['I','P','i','p'];
		
		var today = formatDate(new Date());
		var refDateFormatted = formatDate(refDate);
		var closeDateFormatted = formatDate(closeDate);
		var intakeDateFormatted = formatDate(intakeDate);
		var DispDateFormat     = formatDate(dispDate);
		
		
		if( juvNum == ''
			|| juvNum.length == 0){
			allowUpdate = false;
			msg = 'Juvenile Number is Required';
		}else{
			if( $.isNumeric( juvNum )){
				
			}else{
				allowUpdate = false;
				msg = 'Juvenile Number is Not Numeric';
			}
		}
		
		
		if( refNum == ''
			|| refNum.length == 0  ){
			allowUpdate = false;
			msg = 'Referral Number is Required';
		}else{
			
			if( refNum.length < 4 ){
				
				allowUpdate = false;
				msg = 'Referral Number is less than 4';				
			}
			if( !$.isNumeric( refNum )){
				
				allowUpdate = false;
				msg = 'Referral Number is Not Numeric';
			}
		}
		
		
		if( refDate == '' ){
			allowUpdate = false;
			msg = 'Referral Date is Required';
		}
		
		if( refSource == '' ){
			allowUpdate = false;
			msg = 'Referral Source is Required';
			$("#referralSource").focus();
		}
		
		if( intakeDec == '' ){
			allowUpdate = false;
			msg = 'Referral decision is required';
			$("#intakeDecision").focus();
		}
		
		if( refDateFormatted > today ){
			allowUpdate = false;
			msg = 'The referral date cannot be a future date.  \nPlease modify entry.';
			$("#refDate").focus();
		}
		
		if( closeDateFormatted > 0 ){
			
			if( closeDateFormatted < refDateFormatted){
			allowUpdate = false;
			msg = 'Referral Close Date is before referral date.  \nPlease modify entry.';
			$("#clDate").focus();
			}
			
			if( closeDateFormatted > today ){
				allowUpdate = false;
				msg = 'The referral close date cannot be a future date.  \nPlease modify entry.';
				$("#clDate").focus();
			}
		}
		
		if ( intakeDate == '' ) {
			allowUpdate = false;
			msg = 'Intake Date is required.';
			$("#intakeDate").focus();
		}
		
		if ( intakeDateFormatted > 0 ){
			if( intakeDateFormatted < refDateFormatted){
				allowUpdate = false;
				msg = 'Intake Date is before referral date.  \nPlease modify entry.';
				$("#intakeDate").focus();
				}
				
				if( intakeDateFormatted  > today ){
					allowUpdate = false;
					msg = 'Intake Date cannot equal a future date.  \nPlease modify entry.';
					$("#intakeDate").focus();
				}
		}
		
		if( disposition != '' && dispDate == '' ){
			allowUpdate = false;
			msg = 'Disposition date is required if Disposition code is provided.  \nPlease verify entry.';
			$("#dispDate").focus();
		}
		
	
		if( courtNum == ''){
			
			if(disposition != '' || dispDate !='' || crtResult !=''){
				
				allowUpdate = false;
				msg = 'Court No. is required when Court details are entered. \nPlease verify entry.';
				$("#courtId").focus();
				
			}
			
		}
		
		if( piaStatus != '' ){
			if($.inArray($("#piaStatus").val(),ArrValidPIA)==-1){			
				allowUpdate = false;
				msg = 'Invalid PIA code. Please modify';
			}
		}
		
		if( daLogNum != '' ){
			
			if( daLogNum.length < 6 ){
				allowUpdate = false;
				msg = 'DALog number not valid.';
				$("#daLogNum").focus();
			}
			if( !$.isNumeric( daLogNum )){
				allowUpdate = false;
				msg = 'DALog number should only be a number.';
				$("#daLogNum").focus();
			}
			
		}
		
		if( transNum != '' ){
			
			if( transNum.length < 6 ){
				allowUpdate = false;
				msg = 'JOT transaction number not valid.';
				$("#transNum").focus();
			}
			if( !$.isNumeric( transNum )){
				allowUpdate = false;
				msg = 'JOT transaction number should only be a number.';
				$("#transNum").focus();
			}
			
		}
		
		if ( $("#pEndDate").val().length > 0 ) {
			if ($("#pStartDate").val().length  ===  0 ){
				allowUpdate = false;
				msg = 'Probation Start Date must be specified if there is a Probation End Date';
			} else if ( Date.parse( $("#pEndDate").val() )  <  Date.parse( $("#pStartDate").val() ) ) {
				allowUpdate = false;
				msg = 'Probation Start Date must be a date before Probation End Date';
			}
		}
		
		if ( $("#refTypeInd").val() != ""){
			var regExp = /^[A-Za-z0-9!@#$%^&*()]{1,2}$/;
			if (!(regExp.test( $("#refTypeInd").val() ))) {
				allowUpdate = false;
				msg="Referral Indicator Type must be from 1 to 2 characters. The special chararacters !@#$%^&*() are allowed.";
				
			}
		}
		
		if ( $("#cAssignJPO").val() != ""){
			var regExp = /^[A-Za-z0-9!@#$%^&*()]{3,5}$/;
			if (!( regExp.test( $("#cAssignJPO").val() ))) {
				allowUpdate = false;
				msg="Court Assigned JPO must be from 3 to 5 characters. The special chararacters !@#$%^&*() are allowed. ";
				
			}
		}
		
    	if( !allowUpdate ){
    		alert( msg );
    		return false;
			
		 }else{
			//$("#tempRefNum").val( referralId ); //set it in the form
			 $("#prevJuvNum").val( prevJuvNum );
			 //$('form').attr('action','/JuvenileCasework/PerformUpdateMsReferral.do?submitAction=Submit');
			 $('form').submit();
			 if(true)
				 spinner();
	    	 return true;
		 }

		});

 	function formatDate(date) {
	 	
 		var newStr = '';
 		if( date > ''){
 			
 			var tempDate = new Date(date).toISOString().substr(0, 10);
	 		newStr = tempDate.replace(/-/g, "");
 		}	 		
	 	return newStr;
 	}
 		
});