<!-- JavaScript - Add Placement-->

function validateFields(theForm){
	//trim(theForm["currentPlacementInfo.entryDate"]);
	//trim(theForm["currentPlacementInfo.reasonChildRequiresPlacement"]);
	//trim(theForm["currentPlacementInfo.specificServicesProvided"]);
	//trim(theForm["currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas"]);	
	//trim(theForm["currentPlacementInfo.expectedReleaseDate"]);
	var  reasonForPlacement = Trim(theForm["currentPlacementInfo.reasonChildRequiresPlacement"].value );
	var spServicesProvided = Trim(theForm["currentPlacementInfo.specificServicesProvided"].value);
	var placedOutsideTexas = Trim(theForm["currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas"].value);
	var check = false;
	var message = "";
   myOption = -1;
   if (reasonForPlacement == "") {
      alert("Please explain why child requires placement.");
     theForm["currentPlacementInfo.reasonChildRequiresPlacement"].focus();
      return false;
   }
  
    if (spServicesProvided == "") {
      alert("Please explain what specific services are being provided to safely meet child's needs.");
     theForm["currentPlacementInfo.specificServicesProvided"].focus();
      return false;
   }
   
    if (placedOutsideTexas == "") {
      alert("Please explain why placement of child outside Texas is in the best interest of the child.");
     theForm["currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas"].focus();
      return false;
   }

   if (theForm["currentPlacementInfo.levelOfCareId"].value == "") {
      alert("Level of care required.");
     theForm["currentPlacementInfo.levelOfCareId"].focus();
      return false;
   }  
   
   if (theForm["currentPlacementInfo.permanencyPlanId"].value == "") {
      alert("Permanency Plan required.");
       theForm["currentPlacementInfo.permanencyPlanId"].focus();
      return false;
   }
   if (theForm["currentPlacementInfo.entryDate"].value == "") {
      message = "Entry Date is required."+"\n"
      check = true;
	}
  if (theForm["currentPlacementInfo.expectedReleaseDate"].value == "") {
      message = message + "Expected Release Date is required."+"\n"
      check = true;
   }
  if(check){
	  alert(message);
	  theForm["currentPlacementInfo.entryDate"].focus();
	  theForm["currentPlacementInfo.expectedReleaseDate"].focus();
      return false;
  }
   if( validateCaseplanValidateForm(theForm)){
   			clearAllValArrays();
			customValRequired("currentPlacementInfo.reasonChildRequiresPlacement", "Please explain why child requires placement are required.");
			addDB2FreeTextValidation("currentPlacementInfo.reasonChildRequiresPlacement", "Please explain why child requires placement must not contain invalid symbols such as < > {.");
			customValMaxLength ("currentPlacementInfo.reasonChildRequiresPlacement", "Please explain why child requires placement max length is 1000 characters.",1000);
			
			customValRequired("currentPlacementInfo.specificServicesProvided", "Please explain what specific services are being provided to safely meet child's needs are required.");
			addDB2FreeTextValidation("currentPlacementInfo.specificServicesProvided", "Please explain what specific services are being provided to safely meet child's needs must not contain invalid symbols such as < > {.");
			customValMaxLength ("currentPlacementInfo.specificServicesProvided", "Please explain what specific services are being provided to safely meet child's needs max length is 1000 characters.",1000);
			
			customValRequired("currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas", "Please explain why placement of child outside Texas is in the best interest of the child are required.");
			addDB2FreeTextValidation("currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas", "Please explain why placement of child outside Texas is in the best interest of the child must not contain invalid symbols such as < > {.");
			customValMaxLength ("currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas", "Please explain why placement of child outside Texas is in the best interest of the child max length is 1000 characters.",1000);
			
			addDB2FreeTextValidation("currentPlacementInfo.specialNotes", "Special Notes must not contain invalid symbols such as < > {.");
			customValMaxLength ("currentPlacementInfo.specialNotes", "Special Notes max length is 255 characters.",255);
			return validateCustomStrutsBasedJS(theForm);
   }
   else{
   	return false;
   }
   
 }
/*
function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}*/
