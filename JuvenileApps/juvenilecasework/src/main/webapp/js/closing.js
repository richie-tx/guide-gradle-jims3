
$(document).ready(function () {
	var facilityMessage = $("#facilityMessage").val();
	var transferCasefileId = $("#transferCasefileId").val();
	if (facilityMessage == "true"){
		alert("Juvenile is currently in a facility. Facility admit records are now associated to "+transferCasefileId+".Complete the casefile closure process.");   
	}
	var isTransferredOffense = $("#transferredOffense").val();
	var supervisionNumber = $("#supervisionNum").val();

	if(isTransferredOffense=="false"){
		alert("Transferred Offense Information Needs to Be Added.\n Casefile Closing cannot be submitted.");
		goNav('/JuvenileCasework/displayJuvenileCasefileAssignedReferralsList.do?submitAction=Transfer&casefileId='+supervisionNumber+'&supervisionNum='+supervisionNumber);
		return false;
	}	
	
	
if(typeof $("#supvEndDate")!= "undefined"){
	datePickerSingle($("#supvEndDate"),"Casefile End Date",true);
}
if(typeof $("#deathDate")!= "undefined"){
	datePickerSingle($("#deathDate"));
}
//hidden elements
$("#sodOptional").hide(); 
$("#sodRequired").hide();

// Limit text on keyup and mouseout
$("#closingCmnts").on('keyup mouseout',function(){
	textLimit($(this),4100);
	return false;
}); 

$("#closingEval").on('keyup mouseout',function(){
	textLimit($(this),5200);
	return false;
});

var fld1 = document.getElementById("cntrlRef");
if (fld1 != null) {
	if (fld1.length == 1) {
		fld1.selectedIndex = 1;
		//$("#cntrlRef").prop("selectedIndex",1);
	}
	if (fld1.length > 1){
		fld2 = document.getElementById("cntrlRefId").value;
		for (x=1; x<fld1.length; x++) {
			var refNum = fld1.options[x].value.split('-')[0];			
			if (trim(refNum) == fld2) {	
				fld1.selectedIndex = x;
				break;
			}
		}
	}
}
if(typeof $("#sodBlank").val()!= "undefined") { 
	fld1 = $("#supvOutCome").val(); //document.getElementById("supvOutCome");
	var val1 = "";
	var val2 = "";
	var val3 = $("#hdnSkipSubOutCome").val();
	var val4 = $("#hdnSupCatId").val();
	console.log(val3);
	if (fld1 != null) {
		if ($("#supvOutCome").val()!=""){
			val1 = $("#supvOutCome").val();
			val2 = $("#selectedOutcomeId").val(); //document.getElementById("selectedOutcomeId").value;
			if (val1 == "S" && val3 != "true" && val4 != "PP"){
				$("#sodBlank").hide();
				$("#sodOptional").show();
				$("#sodRequired").hide();
				fld2 = $("#sodOptionalId"); //document.getElementById("sodOptionalId");
				$("#sodOptionalId").val(val2);
//				for (x=0; x< fld2.length; x++){
//					
//					if (fld2.options[x].value == val2) {
//						document.getElementById("sodOptionalId").selectedIndex == x;
//						break;
//					}	
//				}
			}
			if (val1 == "X" && val3 != "true"){
				$("#sodBlank").hide();
				$("#sodOptional").hide();
				$("#sodRequired").show();
				fld2 = $("#sodRequiredId"); //document.getElementById("sodRequiredId");
				$("#sodRequiredId").val(val2);
//				for (x=0; x< fld2.length; x++){
//					alert(fld2.options[x].value);
//					if (fld2.options[x].value == val2) {
//						document.getElementById("sodRequiredId").selectedIndex == x;
//						break;
//					}	
//				}
			}
		}
	}
}	


$("#supvOutCome").on("change",function(){
	var supervisionOutcome = $("#supvOutCome").val();
	var val3 = $("#hdnSkipSubOutCome").val();
	var val4 = $("#hdnSupCatId").val();
	if(typeof $("#sodBlank").val()!= "undefined") {
		$("#sodBlank").show();
		$("#sodOptional").hide();
		$("#sodRequired").hide();
		if (supervisionOutcome == "S" && val3 != "true" && val4 != "PP")
		{
			$("#sodBlank").hide();
			$("#sodOptional").show();
			$("#sodRequired").hide();
			$("#sodOptionalId").val("");
		}
		if (supervisionOutcome == "X" && val3 != "true")
		{
			$("#sodBlank").hide();
			$("#sodOptional").hide();
			$("#sodRequired").show();
			$("#sodRequiredId").val("");
		}
		
	}	
});

$("#next").click(function(){
	
	var supervisionOutcome = $("#supvOutCome").val();
	var controllingRef = $("#cntrlRef").val();
	
	var activationDateStr;
	var supvEndDateStr;
	
	var supvEndDate = $("#supvEndDate").val();
	if(typeof $("#supvEndDate")!= "undefined" && supvEndDate==""){
		alert("Casefile End Date is required.\n");
		$("#supvEndDate").focus();
		return false;
	}
	
	var activationDate = $("#activationDate").val();
	if(typeof activationDate !="undefined"){
		activationDateStr = new Date(activationDate);
	}
	if(typeof $("#supvEndDate")!= "undefined" ){
		supvEndDateStr = new Date(supvEndDate);
	}

	if(supvEndDateStr < activationDateStr){
		alert("Supervision End Date cannot be prior to Casefile Activation Date of " + activationDate + ".\n");
		$("#supvEndDate").focus();
		return false;
	}	
			
	
	if(typeof $("#supvOutCome")!= "undefined" && supervisionOutcome==""){
		alert("Supervision Outcome selection is required.\n");
		$("#supvOutCome").focus();
		return false;
	}
	
	 var age = $("#deathAge").val();
	 
	 if ( age == null || age.length == 0) {
		 age = 0;
	 }
	 
	 if (! $.isNumeric(age) ) {
		 alert("Age must be numeric.");
		 return false;
	 }
	

	if(typeof $("#cntrlRef")!= "undefined" && controllingRef==""){
		alert("Controlling Referral selection is required.\n");
		$("#cntrlRef").focus();
		return false;
	}
	
	var closingEval = $("#closingEval").val();
	if(typeof $("#closingEval")!= "undefined" && closingEval==""){
		alert("Closing Evaluation entry is required.\n");
		$("#closingEval").focus();
		return false;
	}
	

	if(typeof $("#sodBlank")!= "undefined" && $("#supvOutCome option").is(":selected")){
		if($("#sodBlank").is(":visible")){
			$("#selectedOutcomeId").val("");
			$("#selectedOutcomeDesc").val("");
		}
		
		if($("#sodOptional").is(":visible")){
			if($("#sodOptionalId").val()==""){
				$("#selectedOutcomeId").val("");
				$("#selectedOutcomeDesc").val("");
			}else{
				$("#selectedOutcomeId").val($("#sodOptionalId option:selected").val());
				$("#selectedOutcomeDesc").val($("#sodOptionalId option:selected").text());
			}
		}
		
		if($("#sodRequired").is(":visible")){
			var selectedValue= $("#sodRequiredId");
			var selectedText= $("#sodRequiredId");
		
			if($("#sodRequiredId").val()==""){
				alert("Supervision Outcome Description selection is required.\n");
				$("#sodRequiredId").focus();
				return false;
			}else{
				$("#selectedOutcomeId").val($("#sodRequiredId option:selected").val());
				$("#selectedOutcomeDesc").val($("#sodRequiredId option:selected").text());
			}
		}
	}

	var msg = "";	
	var earliestDate = $("#earliestAsgnDate").val();// document.getElementById('earliestAsgnDate').value;
	var caseEndDate =  $("#supvEndDate").val(); //document.getElementById('supvEndDate').value;
	var earliestDateCaseFile = $("#earliestAsgnDateCaseNum").val(); //document.getElementById('earliestAsgnDateCaseNum').value;
	var juvenileNum = $("#juvNum").val(); //document.getElementById('juvNum').value;
	var closingCasefileNum =  $("#supervisionNum").val(); //document.getElementById('supervisionNum').value;
	
	var earlistActDt = new Date(earliestDate);
	var caseEndDt = new Date(caseEndDate);	
 	if (caseEndDt > earlistActDt)
	{
	  var alertMsg = "SUPERVISION DATE OVERLAP";
	  alertMsg = alertMsg + "\n";
 	  alertMsg = alertMsg + "\nSupervision# "+earliestDateCaseFile+" with assignment date "+earliestDate+" overlaps Supervision# "+closingCasefileNum+" with closing date "+caseEndDate+" . ";
 	  alertMsg = alertMsg + "\n";
 	  alertMsg = alertMsg + "\nWhat would you like to do?";
 	  alertMsg = alertMsg + "\n";
	  alertMsg = alertMsg + "\nOK - Proceed with casefile closing date : "+caseEndDate+" ?";
	  alertMsg = alertMsg + "\nCancel - Return to Casefile closing page to fix closing date?";
	  if(confirm(alertMsg)){
			return true;
	  }else{
			return false;
	  }
	} 
 	
 	 if (true) {
		 spinner();
	 }
	
});

});