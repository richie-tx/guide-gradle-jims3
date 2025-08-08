/**
 * FOR FACILITY also Includes javascript for IE11 changes.
 */

//validations for update master flow starts
//JQuery on Dom ONLOAD
$(document).ready(function () {
	

	//Birth Date CALENDAR.
	if(typeof  $("#masterDOB") != "undefined"){	
			datePickerSingle($("#masterDOB"),"Birth Date",true);
	}
	
	if(typeof $("#realDateOfBirth") != "undefined"){
		datePickerSingle($("#realDateOfBirth"),"Date Of Birth",true);
	}
	
	//Checked Out DATE CALENDAR.
	if(typeof  $("#checkedOutDate") != "undefined"){	
			datePickerSingle($("#checkedOutDate"),"Checked Out Date",false);
	}
	
	//Purge DATE CALENDAR.
	if(typeof  $("#purgeDate") != "undefined"){		
			datePickerSingle($("#purgeDate"),"Purge Date",true);
	}
	
	//Purge DATE CALENDAR.
	if(typeof  $("#sealedDate") != "undefined"){		
			datePickerSingle($("#sealedDate"),"Sealed Date",true);
	}
	
	//Purge DATE CALENDAR.
	if(typeof  $("#dateOfDeath") != "undefined"){		
			datePickerSingle($("#dateOfDeath"),"Date Of Death",true);
	}
	
	
	//validation for required fields: on click of next
    $("#updateRec").click(function () {
    	 
    	 var lastName = $("#lastName");
    	 var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
    	 var fld = "";
		   if(typeof lastName!="undefined"){
			   if( $("#lastName").val()=="" || $("#lastName").val().length<2){
				  alert("At Least Two Characters Are Required For Last Name.");
				   $("#lastName").focus();
				   return false;
			   }
		   }
		
		   var firstName = $("#firstName");
		   if(typeof firstName!="undefined"){
			   if( $("#firstName").val()==""){
				  alert("At Least One Character Is Required For First Name.");
				   $("#firstName").focus();
				   return false;
			   }
		   }
		   
		   var race = $("#race");
		   if(typeof race!="undefined"){
			   if( $("#race").val()==""){
				  alert("Race is required. Please modify entry");
				  $("#race").focus();
				  return false;
			   }
		   }
		   //SEX validation
		   var sex = $("#sex");
		   if(typeof sex!="undefined"){
			   if( $("#sex").val()==""){
				  alert("Sex is required.");
				  $("#sex").focus();
				  return false;
			   }
		   }
		   // DOB validation
		   var dateOfBirth = $("#masterDOB");
		   if(typeof dateOfBirth!="undefined"){
			   if( $("#masterDOB").val()==""){
				   alert("Date Of Birth is Required. Please modify entry");
				   $("#masterDOB").focus();
				   return false;
			   }
		   }
		   
		   //Age at Death Validation
		   //var ageAtDeathRegex = /^([0-9]{2}$)/
		   var numericRegExp = /^[0-9]*$/;
		   var ageAtDeathVar = $("#ageAtDeath").val();
		   
		   if (ageAtDeathVar > ""){
				if (numericRegExp.test(ageAtDeathVar,numericRegExp) == false) {
					alert("Age At Death must be a number.");
					$("#ageAtDeath").focus();
					return false;
				}
			}
		  
		   var todayDate = new Date();
		   var todayYear = todayDate.getFullYear();
		   var todayMonth = todayDate.getMonth();
		   var todayDay = todayDate.getDate();
		   var date2=new Date($("#masterDOB").val());

		   var age = todayYear - date2.getFullYear(); 
		   if (todayDate.getMonth() < date2.getMonth() - 1)
		   {
		     age--;
		   }
		   if (date2.getMonth() - 1 == todayDate.getMonth() && todayDate.getDate() < date2.getDate())
		   {
		     age--;
		   }
		   
		 			
			if(age<10)
			{
				alert("Date of Birth will equal an age less than 10.  Please confirm entry");
			}
			if(age>17)
			{
				alert("Date of Birth equal an age greater than 17.  Please confirm entry");
			}
		   var SSN1 = $("#SSN1");
		   var SSN2 = $("#SSN2");
		   var SSN3 = $("#SSN3");
		   
		   var ssn1Regex = /^(?!000)([0-9]{3}$)/
		   var ssn2Regex = /^([0-9]{2}$)/
		   var ssn3Regex = /^([0-9]{4}$)/
		   
		   var SSN1 = $.trim(SSN1.val());
		   var SSN2 = $.trim(SSN2.val());
		   var SSN3 = $.trim(SSN3.val());
		   				  
			
		   if (SSN1.length > 0 || SSN2.length > 0 || SSN3.length > 0)
		   {
				if(ssn1Regex.test(SSN1) == false)
				{
					alert("First 3 digits of SSN must be numeric and not equal 000.\n");
					$("#SSN1").focus();
					return false;
				}
				if(ssn2Regex.test(SSN2) == false)
				{
					alert("Second 2 digits of SSN must be numeric.\n");
					$("#SSN2").focus();
					return false;
				}
				if(ssn3Regex.test(SSN3) == false)
				{
					alert("Last 4 digits of SSN must be numeric.\n");
					$("#SSN3").focus();
					return false;
				}	
		   }   
				   
		   if(typeof SSN1!="undefined"){
		
				   if ($("#SSN1").val() != "" && $("#SSN2").val() == "") {
					   alert("All of Social Security number must be entered if there is partial entry.");
					   $("#SSN2").focus();
					   return false;
				   }
				   if ($("#SSN1").val() != "" && $("#SSN3").val() == "") {
				      alert("All of Social Security number must be entered if there is partial entry.");
				      $("#SSN3").focus();
				      return false;
				   }
				   if ($("#SSN2").val()!= "" && $("#SSN1").val() == "") {
					  alert("All of Social Security number must be entered if there is partial entry.");
					  $("#SSN1").focus();
					  return false;
				   }
				   if ($("#SSN2").val() != "" && $("#SSN3").val() == "") {
					  alert("All of Social Security number must be entered if there is partial entry.");
					  $("#SSN3").focus();
					  return false;
				   }
				   if ($("#SSN3").val() !="" && $("#SSN1").val() == "") {
					  alert("All of Social Security number must be entered if there is partial entry.");
					  $("#SSN1").focus();
					  return false;
				   }
				   if ($("#SSN3").val() != "" && $("#SSN2").val() == "") {
					   alert("All of Social Security number must be entered if there is partial entry.");
					   $("#SSN2").focus(); 
					   return false;
					}
		   }
		   
		 
		   if( $("#statusId").val() === "" )
			{
			   alert("Master Status is required. Please modify entry.\n");
			   $("#statusId").focus();
			   return false;
			}
		   
		   var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;		
		   if(alphaNumWithSymbolsRegExp.test($("#caseNotePart1").val()) == false)
			{
			   alert("Invalid Casenotes, only alphanumeric values with special characters !@#$%^&*() allowed.\n");
			   $("#caseNotePart1").focus();
			   return false;
			}	
		   var checkedOutTo = $("#checkedOutTo");
		   var dateOut = $("#checkedOutDate");
		   
		   if(typeof checkedOutTo!="undefined" && typeof dateOut!="undefined"){
			   if( $("#checkedOutTo").val()=="" && dateOut.val()!="" ){
				   alert("Check Out To is required, if Date Out is entered.");
				   checkedOutTo.focus();
				   return false;
			   }
			   if( dateOut.val()=="" && checkedOutTo.val()!="" ){
				   alert("Date Out is required, if Check Out To is entered.");
				   dateOut.focus();
				   return false;
			   }
		   }
		   var detFac = $("#detentionFacilityId");
		   var detStat = $("#detentionStatusId");
		   if(typeof detFac!="undefined" && typeof detStat!="undefined"){
			   if( detFac.val()=="" && detStat.val()!="" ){
				   alert("Detention Facility is required, if Detention Status is entered.");
				   detFac.focus();
				   return false;
			   }
			   
		   }
		   var purge = $("#purgeFlag");		   
		   if(typeof purge !="undefined"){
			   if( $("#purgeFlag").val()!="" && $("#purgeFlag").val()!="="){
				  alert("Purge Flag is invalid.");
				   $("#purgeFlag").focus();
				   return false;
			   }
		   }
		  /* fld = $("#purgeBoxNum");		
		   var purgeBoxRegex = /^([0-9]{3}$)/;		  	 
		   if(typeof fld!="undefined" && $("#purgeBoxNum").val()!=""){
			   if (purgeBoxRegex.test($("#purgeBoxNum").val()) == false) {
			  	alert("Invalid Purge Box, only numeric values allowed.");
				$("#purgeBoxNum").focus();
				return false;
			}*/ //commented for US 175629
		
		   fld = $("#purgeSerNum");	
		   if(typeof fld!="undefined" && $("#purgeSerNum").val()!=""){
			   if (alphaNumericRegExp.test($("#purgeSerNum").val()) == false) {
				   alert("Invalid Purge Serial#, only alphanumeric values allowed.");
				   $("#purgeSerNum").focus();
				   return false;
			   }
		   }
		   if ( true ){
				spinner();
			}
    	
    });
	

});