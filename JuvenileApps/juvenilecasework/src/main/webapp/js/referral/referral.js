$(document).ready(function () {
	
	var finalMsg="";
	var myForm = document.forms[0];
	$('.rm-link').unbind('click', false);
	
	if($(typeof $("#message") != "undefined" && "#message").val() != "") {
		
		$("#createResealBtn").prop("disabled", true);
	}
	
	if($(typeof $("#addReferralFlag") != "undefined" && "#addReferralFlag").val() == "true") {
		
		$("#addRefBtn").prop("disabled", true);
	}	
	
	 if($(typeof $("#newRefNum") != "undefined" && "#newRefNum").val() != "") 
			$("#updateReferral").prop("disabled", false);
	 else
	   	$("#updateReferral").prop("disabled", true);
	if ($("#action").val() =="createReferral" ){
		
		$("#newRefSource").focus();
	}
	if(typeof $("#newRefDate") != "undefined"){		
		datePickerSingle($("#newRefDate"),"Referral Date",true);
	}
	if(typeof $("#newRefIntakeDate") != "undefined"){		
		datePickerSingle($("#newRefIntakeDate"),"Intake Date",true);
	}
	
	if(typeof $("#offenseDate") != "undefined"){	
		datePickerSingle($("#offenseDate"),"Offense Date",true);
	}
	if(typeof $("#assignmentDateStr") != "undefined"){		
		datePickerSingle($("#assignmentDateStr"),"Assignment Date",true);
	}
	if(typeof $("#probationStartDate") != "undefined"){		
		datePickerSingle($("#probationStartDate"),"Probation Start Date ",true);
	}
	if(typeof $("#probationEndDate") != "undefined"){		
		datePickerSingle($("#probationEndDate"),"Probation End Date ",false);
	}
	if(typeof $("#updateRefStat") != "undefined" && $("#updateRefStat").val()=="ACTIVE"){	
		$("#updtateRefCloseDt").prop("disabled", true);
	}
	if(typeof $("#updtateRefCloseDt") != "undefined"){	
		datePickerSingle($("#updtateRefCloseDt"),"Referral Close Date",true);
	} 
	if(typeof $("#updateOffenseFlag") != "undefined")
	{
		if($("#updateOffenseFlag").val() == 'Y'){		
			$("#updateOffense").prop("disabled", false);
			$("#addToListBtn").prop("disabled", true);
		}
		else
		{
			$("#updateOffense").prop("disabled", true);
			$("#addToListBtn").prop("disabled", false);
		}
	}
	if(typeof $("#updateRefStat") != "undefined" && $("#updateRefStat").val()=='CLOSED'
		 && typeof $("#updateRefStatFlag") != "undefined" && $("#updateRefStatFlag").val()=='loadRef')
	{		
		$("#rd *").attr("disabled", "disabled");
		$("#od *").attr("disabled", "disabled");
		$("#ol *").attr("disabled", "disabled");
		$("#ad *").attr("disabled", "disabled");
		$("#pd *").attr("disabled", "disabled");
		

	}
	//to loop through the referral list radio buttons
	$("input[id^='updateRefNumRadio']").on('click', function (e) {		
		
		$("#updateReferral").prop("disabled", false);
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=loadReferral');
		$('form').submit();
	});
	
	//to loop through the offense list radio buttons
	$("input[id^='offenseNumIdRadio']").on('click', function (e) {		
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=loadOffense');
		$('form').submit();
	});
	
	$("input[id^='chargeReferralRadio']").on('click', function (e) {
		var selected = $("input[id='chargeReferralRadio']:checked").val();
		 $("#refNumber").val(selected); //set it in the form
		$("#adminSelection").val("");
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=adminReferralRequested');
		$('form').submit();
	});
	
	$("input[id^='originalChargeRadio']").on('click', function (e) {
		var selected = $("input[id='originalChargeRadio']:checked").val();
		 $("#refNumber").val(selected); //set it in the form
		$("#adminSelection").val("");
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Reseal Supervision');
		$('form').submit();
	});
	
	// validate offense code - allegation
    $("#validateCode").on('click', function (e) {
    	var fld = $("#offenseCode").val();
    	var val = Trim(fld);
    	if (val == "")
    	{
    		alert("Offense Code Missing, Please Correct And Retry.")
    		$("#allegation").focus();
    		return false;
    	}
    	$("#allegation").val(val.toUpperCase());
    	if ( true ) {
			spinner();
    	}
    	return true;
    });
	
	$("#addToListBtn").click(function () {

	   if($("#newRefNum").val() == "") 
	   {
	      alert("Referral is required to add offense.");
	      $("#newRefNum").focus();
	      return false;
	   }
		if($("#offenseCode").val() == "")
		{
		      alert("Offense Code is required.");
		      $("#offenseCode").focus();
		      return false;
		}
		if($("#offenseDate").val() == "")
		{
		      alert("Offense Date is required.");
		    //referral Validation			
		      $("#offenseDate").focus();
		      return false;
		}
		else
		{
			if(typeof $("#newRefDate").val() != "undefined"){  //cannot be before referral date and can't  have a future date.				
				var refDate = new Date($("#newRefDate").val());
				var offDate = new Date($("#offenseDate").val());
				if( offDate.getTime() > refDate.getTime() )
				{
					alert("Referral Date cannot be prior to Offense Date. Please modify.");
					$("#newRefDate").focus();
					return false;
				}
			}
		}
		var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x3B\x26\x2f\x2c\-]*$/;		
		if(alphaNumericRegExp.test($("#keyMapLocation").val().trim(),alphaNumericRegExp) == false){
			alert("Keymap contains invalid character(s), only alphanumeric values allowed.");
			$("#keyMapLocation").focus();
			return false;
		}
		if(alphaNumWithSymbolsRegExp.test($("#investigationNum").val().trim(),alphaNumericRegExp) == false){
			alert("Investigation # must be alphanumeric with no leading spaces.  The following symbols -'.,/&\\\\;() are allowed after the first alphanumeric character.");
			$("#investigationNum").focus();
			return false;
		}
		$(this).prop("disabled",true);
		if ( true ) {
			spinner();
		}
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Add To List');
		$('form').submit();
	});
	$("#overrideReasonStr").on("change",function(){
		//alert($("#overrideReasonStr").val);
	if($("#overrideReasonStr").val() == "OTH")
		{
		$("#overrideOTHComments").show();
		}
	});
	
	$("#assignmentType").on("change",function(){
		spinner();
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Change Assessment Type');
		$('form').submit();
	});
	
	
	$("#updateOffense").click(function () {	
		if($("#offenseCode").val() == "")		{
		      alert("Offense Code is required.");
		      $("#offenseCode").focus();
		      return false;
		}
		if($("#offenseDate").val() == "")
		{
		      alert("Offense Date is required.");
		    //referral Validation			
		      $("#offenseDate").focus();
		      return false;
		}
		else
		{
			if(typeof $("#newRefDate").val() != "undefined"){  //cannot be before referral date and can't  have a future date.				
				var refDate = new Date($("#newRefDate").val());
				var offDate = new Date($("#offenseDate").val());
				if( offDate.getTime() > refDate.getTime() )
				{
					alert("Referral Date cannot be prior to Offense Date. Please modify.");
					$("#newRefDate").focus();
					return false;
				}
			}
		}
		var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x3B\x26\x2f\x2c\-]*$/;		
		if(alphaNumericRegExp.test($("#keyMapLocation").val().trim(),alphaNumericRegExp) == false){
			alert("Keymap contains invalid character(s), only alphanumeric values allowed.");
			$("#keyMapLocation").focus();
			return false;
		}
		if(alphaNumWithSymbolsRegExp.test($("#investigationNum").val().trim(),alphaNumericRegExp) == false){
			alert("Investigation # must be alphanumeric with no leading spaces.  The following symbols -'.,/&\\\\;() are allowed after the first alphanumeric character.");
			$("#investigationNum").focus();
			return false;
		}	
		if ( true ) {
			spinner();
		}
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Update Offense');
		$('form').submit();
	});
	
	$("#supervisionCat").on("change",function(){
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Change Supervision Cat');
		$('form').submit();
	});
	
	$("#supervisionType").on("change", function(){
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Change Supervision Type');
		$('form').submit()
	});
	
	$("#supervisionTypeU").on("change", function(){
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Change Assignment Type');
		$('form').submit()
	});
	
	$("input[id^='validateBtn']").on('click', function (e) {			
		if($("#jpo").val() == "")
		{
		      alert("Juvenile Probation Officer is invalid. Please modify. ");
		      $("#jpo").focus();
		      return false;
		}
		
		if(($("#jpo").val()).length <5)
		{

	      	alert("Juvenile Probation Officer is invalid. Please modify.");
	      	$("#jpo").focus();
	      	return false;
		}
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;	
		if(alphaNumWithSymbolsRegExp.test($("#jpo").val().trim(),alphaNumWithSymbolsRegExp) == false){
			alert("JPO ID # must be alphanumeric with no leading spaces.  The following symbols !@#$%^&*() are allowed.");
			$("#jpo").focus();
			return false;
		}
		if ( true ) {
			spinner();
		}
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Validate');
		$('form').submit();
	});
/*	$("#probationStartDate").on("change", function(){
		if(typeof $("#probationEndDate").val() != "undefined" && $("#probationEndDate").val()!=""){
			var startDate = new Date($("#probationStartDate").val());
			var endDate = new Date($("#probationEndDate").val());
			
			if( endDate.getTime() > startDate.getTime() )
			{
				alert("Invalid Probation Begin Date. Please modify.");
				$("#probationStartDate").focus();
				return false;
			}
		}
	});

	$("#probationEndDate").on("change", function(){
		if(typeof $("#probationStartDate").val() != "undefined" && $("#probationStartDate").val()!=""){
			var startDate = new Date($("#probationStartDate").val());
			var endDate = new Date($("#probationEndDate").val());
			
			if( endDate.getTime() > startDate.getTime() )
			{
				alert("Invalid Probation End Date. Please modify.");
				$("#probationEndDate").focus();
				return false;
			}
		}
	});*/
	/**
	 * Add to list button
	 */
	$("#addRefBtn").click(function () {	
		finalMsg="";
		var newRefDate = $("#newRefDate").val();
		var newRefSource = $("#newRefSource").val();
		var newRefIntakeDecision = $("#newRefIntakeDecision").val();
		var newRefIntakeDate = $("#newRefIntakeDate").val();
		var assignmentType = $("#assignmentType").val();
		var supervisionCat = $("#supervisionCat").val();
		var supervisionType = $("#supervisionType").val();
		var jpo = $("#jpo").val();
		var selectedAdmin = $("#adminSelection").val();
		
		isFieldNotEmpty(newRefDate, "Referral Date is required.", "#newRefDate");
		isFieldNotEmpty(newRefSource, "Referral Source is required.", "#newRefSource");
		isFieldNotEmpty(newRefIntakeDecision, "Intake Decision is required.", "#newRefIntakeDecision");
		isFieldNotEmpty(newRefIntakeDate, "Intake Date is required.", "#newRefIntakeDate");
		isFieldNotEmpty(assignmentType, "Assignment Type is required.", "#assignmentType");
		isFieldNotEmpty(supervisionCat, "Supervision Category is required.", "#supervisionCat");
		isFieldNotEmpty(supervisionType, "Supervision Type is required.", "#supervisionType");
		isFieldNotEmpty(jpo, "JPO is required.", "#jpo");
		
		if(selectedAdmin == 'Y' && $("#chargeReferralRadio").prop('checked',false)){
			
			alert("Selection of an Original Charge Referral is required. Please click the radio button next to the appropriate Referral Number.");
			return false;
		}
		
		   if($("#newRefNum").val() == "") 
		   {
		      alert("Referral# is required. Click Create Next Referral button for a new referral.");
		      $("#newRefNum").focus();
		      return false;
		   }
		   // bug no 83378
		   var assignmentType = $("#assignmentType").val();
		   if(typeof assignmentType!="undefined"){
			   var assignmentTypes = ["PCO","PAD","DAD"]
			   if($.inArray(assignmentType,assignmentTypes)!=-1){
				   alert("This assignment type requires the referral is processed in court. Please modify");
				   $("#assignmentType").focus();
				   return false;
			   }
		   }
		   // bug no 83378
		/*var validate = validateCreateReferralForm(myForm);
		if(!validate){
			return false;
		}*/		
		if(typeof $("#newRefDate").val() != "undefined"){  //cannot be before referral date and can't  have a future date.				
			var refDate = new Date($("#newRefDate").val());
			var inDate = new Date($("#newRefIntakeDate").val());			
			if( refDate.getTime() > inDate.getTime() )
			{
				alert("Decision Date cannot be prior to Referral Date. Please modify.");
				$("#newRefIntakeDate").focus();
				return false;
			}
		}
		if(($("#jpo").val()) != ""){
			if(($("#jpo").val()).length <5)
			{

		      	alert("Juvenile Probation Officer is invalid. Please modify.");
		      	$("#jpo").focus();
		      	return false;
			}
		}
		
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;	
		if(alphaNumWithSymbolsRegExp.test($("#jpo").val().trim(),alphaNumWithSymbolsRegExp) == false){
			alert("JPO ID # must be alphanumeric with no leading spaces.  The following symbols !@#$%^&*() are allowed.");
			$("#jpo").focus();
			return false;
		}
		if(typeof $("#assignmentDateStr").val() != "undefined"){  //cannot be before referral date and can't  have a future date.				
			var refDate = new Date($("#newRefDate").val());
			var assignDate = new Date($("#assignmentDateStr").val());			
			if( refDate.getTime() > assignDate.getTime() )
			{
				alert("Assignment Date cannot be prior to Referral Date. Please modify.");
				$("#assignmentDateStr").focus();
				return false;
			}
		}
		
		var date1 = new Date();	    	
		var diff = Math.abs(date1.getTime()-getDateFromFormat($("#assignmentDateStr").val(),"MM/dd/yyyy"));		
		
		if(Math.ceil(diff/(1000*3600*24))>8)
		{		
			alert("Assignment date must not exceed the past seven days.  Please modify or contact Data Corrections for date modification. ");
			$("#assignmentDateStr").focus();
			return false;
		}
		
		
		if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 
		spinner();
		//Disable remove hyperlink
		$('.rm-link').bind('click', false);
		$(this).prop("disabled",true);
		$("#adminSelection").val("");
		$('form')
		.attr(
				'action',
				"/JuvenileCasework/submitCreateReferral.do?submitAction=Add Referral");
		$('form').submit();
		
	});
	
	function isFieldNotEmpty(field, fieldMsg, domElementId){
		if(field == "" || field === null || field === undefined){	
			finalMsg += fieldMsg + "\n";	
		}
		/*return true;*/
	}
	
	$("#updateRefStat1").click(function () {	
		
		$("#updtateRefCloseDt").val('');
		$("#updtateRefCloseDt").prop("disabled", true);
	});
	$("#updateRefStat2").click(function () {	
		
		$("#updtateRefCloseDt").prop("disabled", false);
	});
	/**
	 * Update Referral button
	 */
	
	
/*	$("#newRefIntakeDecision").on("change",function(){
		$("#newRefIntakeDate").datepicker('setDate', null);
		alert("Intake Decision has been modified. Please update Intake Decision Date.");
		$("#newRefIntakeDate").focus();
	});*/
	
	var updNewRefSource = $("#updNewRefSource").val();
	var updNewRefIntakeDecision = $("#updNewRefIntakeDecision").val();

	$("#newRefSource").on("change",function(){
		updNewRefSource=$("#newRefSource").val();
	});
	$("#newRefIntakeDecision").on("change",function(){
		updNewRefIntakeDecision=$("#newRefIntakeDecision").val();
	});

	
	$("#updateReferral").click(function () {	
	
		/*var validate = validateCreateReferralForm(myForm);
		if(!validate){
			return false;
		}*/	
		
		finalMsg="";
		var newRefDate = $("#newRefDate").val();
		var newRefIntakeDate = $("#newRefIntakeDate").val();

		isFieldNotEmpty(newRefDate, "Referral Date is required.", "#newRefDate");
		isFieldNotEmpty(updNewRefSource, "Referral Source is required.", "#updNewRefSource");
		isFieldNotEmpty(updNewRefIntakeDecision, "Intake Decision is required.", "#updNewRefIntakeDecision");
		isFieldNotEmpty(newRefIntakeDate, "Intake Date is required.", "#newRefIntakeDate");

		
		if(typeof $("#newRefDate").val() != "undefined"){  //decision date cannot be before referral date and can't  have a future date.				
			var refDate = new Date($("#newRefDate").val());
			var inDate = new Date($("#newRefIntakeDate").val());			
			if( refDate.getTime() > inDate.getTime() )
			{
				alert("Decision Date cannot be prior to Referral Date. Please modify.");
				$("#newRefIntakeDate").focus();
				return false;
			}
		}
		if(typeof $("#newRefDate").val() != "undefined"){  //referral date cannot be before offense date and can't  have a future date.				
			var refDate = new Date($("#newRefDate").val());
			var offDate = new Date($("#offenseDate").val());
			if( offDate.getTime() > refDate.getTime() )
			{
				alert("Referral Date cannot be prior to Offense Date. Please modify.");
				$("#newRefDate").focus();
				return false;
			}
		}	
		
		if(($("#jpo").val()) != ""){
			if(($("#jpo").val()).length <5)
			{
	
		      	alert("Juvenile Probation Officer is invalid. Please modify.");
		      	$("#jpo").focus();
		      	return false;
			}
		}	
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;	
		if(alphaNumWithSymbolsRegExp.test($("#jpo").val().trim(),alphaNumWithSymbolsRegExp) == false){
			alert("JPO ID # must be alphanumeric with no leading spaces.  The following symbols !@#$%^&*() are allowed.");
			$("#jpo").focus();
			return false;
		}
		if($("#updateRefStat2").prop('checked') && $("#updtateRefCloseDt").val() == "")
		{
			alert("Referral Status = Closed. Close Date is required.");
			$("#updtateRefCloseDt").focus();
			return false;
		}
		
		
		if( $("#updateRefStat2").prop('checked'))
		{			
			var refCloseDate = new Date($("#updtateRefCloseDt").val());
			var offDate = new Date($("#earliestOffenseDate").val());
			var refDate = new Date($("#newRefDate").val());
			var intakeDate = new Date($("#newRefIntakeDate").val());
			var courtdecisionDate = new Date($("#courtdecisionDate").val());
			var formattedintakeDate = formatDate(intakeDate);
			var formattedreffcloseDate = formatDate(refCloseDate);
			var formattedcourtdecisionDate = formatDate(courtdecisionDate);
			
			if( refDate.getTime() > refCloseDate.getTime() )
			{
				alert("Close Date cannot be prior to Referral Date.  Please modify.");
				$("#updtateRefCloseDt").focus();
				return false;
			}
			
			if( offDate.getTime() > refCloseDate.getTime() )
			{
				alert("Referral Close Date cannot be before Offense Date.  Please modify.");
				$("#updtateRefCloseDt").focus();
				return false;
			}
		
			/*var intakeDec = $("#newRefIntakeDecision").val();
			if(intakeDec=="")
			{
				alert("Referral Decision is required to close referral.");
				$("#newRefIntakeDecision").focus();
				return false;
			}*/
			//var intakeDec = $("#newRefIntakeDate").val(); 
			if( formattedintakeDate > formattedreffcloseDate )
			{
				alert("Referral Close Date cannot be before Intake Date.  Please modify.");
				$("#updtateRefCloseDt").focus();
				return false;
			}			
			if( formattedcourtdecisionDate > formattedreffcloseDate )
			{
				alert("Referral Close Date cannot be before Court Decision Date.  Please modify.");
				$("#updtateRefCloseDt").focus();
				return false;
			}
		}
		//check probation start and end date
		if(typeof $("#probationStartDate").val() != "undefined" && $("#probationStartDate").val()!=""){
			if(typeof $("#probationEndDate").val() == "undefined" || $("#probationEndDate").val()==""){
				alert("Probation End Date is required if Probation Start Date entered. Please modify.");
				$("#probationEndDate").focus();
				return false;
			}
		}	
		
		if(typeof $("#probationEndDate").val() != "undefined" && $("#probationEndDate").val()!=""){
			if(typeof $("#probationStartDate").val() == "undefined" || $("#probationStartDate").val()==""){
				alert("Probation Start Date is required if Probation End Date entered. Please modify.");
				$("#probationStartDate").focus();
				return false;
			}
		}
		
		if(typeof $("#probationStartDate").val() != "undefined" && $("#probationStartDate").val()!=""  && typeof $("#probationEndDate").val() != "undefined" && $("#probationEndDate").val()!="")
		{			
			var startDate = new Date($("#probationStartDate").val());
			var endDate = new Date($("#probationEndDate").val());
			
			if( startDate.getTime() > endDate.getTime() )
			{
				alert("Invalid Probation End Date. Please modify.");
				$("#probationEndDate").focus();
				return false;
			}
			
			
		}
		
		var updateOffenseMsg = $("#updateOffenseMsg").val();
//		if($("#updateOffenseMsg").val()!= "" && $("#updateOffenseMsg").val()!= "undefined" ){
		if($("#updateOffenseMsg").val()== "yes" ){
			alert("Juvenile age is 17-21. Please verify assignment details and modify in Manage Assignment, if appropriate. Contact Data Corrections for additional support.");
			return false;
		}
		
		
		if(finalMsg != ""){
			alert(finalMsg);
			return false;
		}
		
		if ( true ) {
			spinner();
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
	$("#searchBtn").click(function(){
		$('form').attr("action","/JuvenileCasework/searchOfficerProfile.do?clr=Y&requestOrigin=CR");
		$('form').submit();
	});
	
	$("#createResealBtn").click(function(){
		$('form').attr('action','/JuvenileCasework/submitCreateReferral.do?submitAction=Create Reseal');
		 $(this).prop("disabled",true);
		$('form').submit();
	});
	
	$("#createNextResealBtn").click(function(){
		$('form').attr('action','/JuvenileCasework/processReferralBriefing.do?submitAction=Create Reseal');
		 $(this).prop("disabled",true);
		$('form').submit();
	});
	
	if(typeof $("#updateAsmntTypeFlag") != "undefined" && $("#updateAsmntTypeFlag").val() == 'Y' && typeof $("#loadAssmntFlag") != "undefined" && $("#loadAssmntFlag").val() == 'N')
	{	
		$("#updateAsmntTypeFlag").val("");
		alert("Modifying this offense type requires a new assignment type.  Please modify.");	
	}
	
});