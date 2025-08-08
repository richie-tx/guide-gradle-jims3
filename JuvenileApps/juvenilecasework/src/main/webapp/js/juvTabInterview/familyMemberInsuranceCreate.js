//<!-- JavaScript - Insurance Type Check -->
//modified to use JQuery

$(document).ready(function () {
	
	  //formerly function expandSections()
	  $("[name='currentInsurance.insuranceTypeId']").on('change', function(){
		  var valId = $("[name='currentInsurance.insuranceTypeId']").val();
		 
		  if(valId == "" || valId == "UNIN")
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
	  

		$("[name='currentInsurance.insuranceCarrier']").on('keyup mouseout',function(){
			textLimit($(this),100);
		});
		

		$("[name='currentInsurance.policyNumber']").on('keyup mouseout',function(){
			textLimit($(this),12);
		});

	//formarly function validateAddInsurance(theForm)
	$("#addInsuranceList").on('click', function(){
		var valId = $("[name='currentInsurance.insuranceTypeId']").val();
		var insuranceCarrier = $("[name='currentInsurance.insuranceCarrier']").val();
		var policyNumber =  $("[name='currentInsurance.policyNumber']").val();
		   
		if(valId == "UNIN"){
			return true;
		}
			
		
		if (valId == "") {
		      alert("Insurance Type selection is required.");
		      $("[name='currentInsurance.insuranceTypeId']").focus();
		      return false;
		}
		
		if(insuranceCarrier == "" && valId != "UNKN" ){
			alert("Insurance Carrier is required");
			$("[name='currentInsurance.insuranceCarrier']").focus();
			return false;
		}

			/*var theForm = document.juvenileMemberForm;
			return validateJuvenileMemberFormIns(theForm);*/
		
	});
	
	/*function validateInsuranceFields(theForm){
	
	   if ($("[name='currentInsurance.insuranceTypeId']").val() == "") {
	      alert("Insurance Type selection is required.");
	      $("[name='currentInsurance.insuranceTypeId']").focus();
	      return false;
	   }
	   return validateJuvenileMemberFormIns(theForm);
	}*/
});
