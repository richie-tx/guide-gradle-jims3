<!-- JavaScript - Add New Goal-->

$(document).ready(function(){
	
	var timeFrame = sessionStorage.getItem("timeFrame") ;

	if ( timeFrame != null  ) {
		$("#tFId").val( timeFrame  );
	}
	
	
	if ("OT" == $("#tFId").val() ) {
		$("#timeFrameDesc").show();
	}
	
	$("#tFId").change(function(){
		sessionStorage.setItem("timeFrame", $(this).val() );
	})
	
	$('a[href="reco"]').click(function(){
		alert("link");
		$("#recommendationTable1").show();
		$("#recommendationTable2").show();
	});
	if(typeof $('#tFId') != "undefined"){
		
		if($("#tFId").val() == 'OT')
			$("#timeFrameDesc").show();
	}
		
	$("[name='currentGoalInfo.timeFrameCd']").on("change", function(){		
		$("#timeFrameDesc").hide();
		if($("#tFId").val() == 'OT')
			$("#timeFrameDesc").show();
		
		$("#tFDId").val("");
	});
});

/*function validateFields(theForm){
	
  
   clearAllValArrays();

		customValRequired("currentGoalInfo.domainTypeCd", "Domain Type is required", "");
		customValRequired("currentGoalInfo.personsResponsibleIds", "Person(s) Responsible is required", "");
  		customValRequired("currentGoalInfo.timeFrameCd", "Time Frame is required", "");
  		customValRequired("currentGoalInfo.goal", "Goal is required", "");
  		addDB2FreeTextValidation("currentGoalInfo.goal", "Goal cannot have invalid symbols or start with blanks");
  		customValMaxLength("currentGoalInfo.goal", "Goal cannot be more than 255 characters",255);
	
	addDB2FreeTextValidation("currentGoalInfo.progressNotes", "Progress Notes cannot have invalid symbols or start with blanks");
	customValMaxLength("currentGoalInfo.progressNotes", "Progress Notes cannot be more than 1000 characters",1000);
		if(validateCustomStrutsBasedJS(theForm)){
		  return validateCaseplanGoalUpdateForm(theForm);
		  }
		  else
		  {
		  	return false;
		  }
 }*/
 
/*
 function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
*/