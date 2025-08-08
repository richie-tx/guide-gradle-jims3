//<!-- JavaScript - Benefits Check -->
//modified to use JQuery

$(document).ready(function () {
	//formerly function validateBenefitFields(theForm){
	$("#addBenefitsList").on('click',function(){
		if ($("[name='currentBenefit.eligibilityTypeId']").val() == "") {
			alert("Type of Eligibility selection is required.");
			$("[name='currentBenefit.eligibilityTypeId']").focus();
			return false;
		}
		
		if($("#benefitsYes").is(':checked'))
		{
			if($("#receivedAmtId").val()== 0)
			{
				alert("If receiving benefits Received Amount is required.");
				$("#receivedAmtId").focus();
				return false;
			}
			var regExp = /^[1-9][0-9]*$/;
			var fld= $("#receivedAmtId");
			fld.val(Trim($(fld).val()));
		
			if(regExp.test($(fld).val(), regExp)==false)
			{
				alert("Received Amount must be numeric and cannot have leading 0s.");
		 		fld.focus();
		 		return false;
			}
			if(fld.val().length<2)
			{
				alert("Received Amount must be at least 2 digits.");
		 		fld.focus();
		 		return false;
			}
		}
			return true;
	});
	
	$("#benefitsYes").click(function(){		
		$("#receivingBenefitsId").show();
	});
	$("#benefitsNo").click(function(){		
		$("#receivingBenefitsId").hide();
	});
	
});


