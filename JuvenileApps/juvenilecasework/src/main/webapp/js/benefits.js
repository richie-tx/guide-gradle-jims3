//functionality used by benefits

$(document).ready(function () {
	
	//calls casework.js's disable submit button
	$("#BenefitCreateAdd,#BenefitDetailFinish,#benefitInsureDetFinish").click(function(){
		disableSubmitButtonCasework($(this));
		
	});
		
	$("#validateInsuranceAddToList").on('click',function(){

		var retVal = true;
		var msg = "";
		var testValues = new Array("UNIN", "UNKN");
		var insuranceType = $("[name='currentInsurance.insuranceTypeId']");
		var insuranceCarrier = $("[name='currentInsurance.insuranceCarrier']");
		
		if( typeof insuranceType != 'undefined' && insuranceType.val() == ""){
			msg += "Insurance Type is required.\n";
			retVal = false;
		}
		if( typeof insuranceCarrier != 'undefined' && ( insuranceCarrier.val() == "" && jQuery.inArray(insuranceType.val(), testValues) == -1)){
			msg += "Insurance Carrier is required.\n";
			retVal = false;
			
		}		
		
		if( $("[name='currentInsurance.policyNumber']").val() != ""){
			if( $("[name='currentInsurance.policyNumber']").val().length < 2 ){
				msg += "Policy Number must be more than 1 character.\n";
				retVal = false;
			}
			if(!validatePolicy( $("[name='currentInsurance.policyNumber']").val() )){
				msg += "Policy Number must be alphanumeric.\n";
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
	
	function validatePolicy( policyNum ) {
	    var p_regex = /^[a-zA-Z0-9 \'\\-]*$/;
	    return p_regex.test( policyNum );
	 }
	
	
	$("#benefitsReturn").on('click',function(){
		changeFormActionURL($(this).data("href") , true);
	});
	
	$("#reviewAssessmentBtn").on("click",function(){
		changeFormActionURL('/JuvenileCasework/displayBenefitsAssessmentReview.do?submitAction=Review Assessment',true);
	});
	
	$("#submitBtnNext").on("click",function(){
		changeFormActionURL('/JuvenileCasework/displayBenefitsAssessmentSummary.do?submitAction=Next',true);
	});
	
	$("#submitBtnFinish").on("click",function(){
		changeFormActionURL('/JuvenileCasework/submitReviewBenefitsAssessment.do?submitAction=Finish',true);
	});
	
	//ends with trait id call update button
	$("[styleId$=benefitId]").click(function(){			
		$("#benefitUpdate").attr("disabled",false);
	});
	
	$("#benefitUpdate").on("click",function(){
		changeFormActionURL('/JuvenileCasework/submitJuvenileBenefitsCreate.do?submitAction=Update',true);
	})
	
	//used in juvenileInsuranceCreate.jsp to control the form fields
	//formerly function expandInsuranceSections()
	$("[name='juvenileBenefitsInsuranceForm']").on("change",function() {
		var valId = $("[name='currentInsurance.insuranceTypeId']").val();
		
		if(valId == "" || valId == "UNIN" || valId == "UNKN")
		{
			$("#insCarrier").hide();
			$("#policyNum").hide();
			//clear values
			$("[name='currentInsurance.policyNumber']").val("");
			$("[name='currentInsurance.insuranceCarrier']").val("");
		}
		else
		{
			$("#insCarrier").show();
			$("#policyNum").show();
		}
		
	});
	
	//used in juvenileInsuranceCreate.jsp to submit the insurance form field
	//use to be function validateAddInsurance(theForm)
	$("#juvBenefInsurFormSubmit").on("click", function() {
		var theForm = document.juvenileBenefitsInsuranceForm;
		var valId = $("[name='currentInsurance.insuranceTypeId']").val();
		if(valId == "UNIN" || valId == "UNKN")
			return true;
		else
		{
			//struts JuvenileInsuranceCreate validation
			return validateJuvenileInsuranceCreate(theForm);
		}
	});
	
	$("#benefitsYes").click(function(){
		$("#receivingBenefitsId").show();
	});
	
	$("#benefitsNo").click(function(){
		$("#receivingBenefitsId").hide();
	});
	
	$("#addToListId").click(function(){
		if(validateJuvenileBenefitsCreate(this.form))
		{
			if($("#benefitsYes").is(':checked'))
			{
				var regExp = /^[1-9][0-9]*$/;
				var fld= $("#receivedAmtId");
				fld.val(Trim($(fld).val()));

				if(fld.val().length<2)
				{
					if(regExp.test($(fld).val(), regExp)==true)
					{
					alert("Received Amount must be at least 2 digits.");
			 		fld.focus();
			 		return false;
					}
				}else{
					if(regExp.test($(fld).val(), regExp)==false)
					{
						alert("Received Amount must be numeric and cannot have leading 0s.");
				 		fld.focus();
				 		return false;
					}
				}
			}
		}
		else
			return false;
	});
	
	$("#benefitsAddToList").on('click',function(){

		if( validateJuvenileBenefitsInsuranceForm(this.form) )
		{
			if($("#benefitsYes").is(':checked'))
			{
				var regExp = /^[1-9][0-9]*$/;
				var fld= $("#receivedAmtId");
				fld.val(Trim($(fld).val()));

				if(fld.val().length <2 )
				{
					if( regExp.test($(fld).val(), regExp)==true)
					{
					alert("Received Amount must be at least 2 digits.");
			 		fld.focus();
			 		return false;
					}
				}else{
					if(regExp.test($(fld).val(), regExp)==false)
					{
						alert("Received Amount must be numeric and cannot have leading 0s.");
				 		fld.focus();
				 		return false;
					}
				}
			}
		}
		else{
			return false;
		}		

	});
	
	$("#updateFinishButton").click(function(){
			
		if(typeof $("#benefitStatusId").val() != 'undefined' && $("#benefitStatusId").val()=="")
		{
			alert("Benefit status is required.");
			$("#benefitStatusId").focus();
			return false;
		}
		else
			changeFormActionURL('/JuvenileCasework/submitJuvenileBenefitsCreate.do?submitAction=Finish Update',false);
	});
});
