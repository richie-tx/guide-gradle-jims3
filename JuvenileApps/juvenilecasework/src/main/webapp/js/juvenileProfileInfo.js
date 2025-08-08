//Using JQuery
$(document).ready(function () {

	var finalMsg;
	var SsnFinalMsg;
	if(typeof $("#profileInfoDateId") != "undefined")
	{		
		datePickerSingle( $("#profileInfoDateId"),"Date of Birth",true);
	}
	if(typeof $("#fromFacilityId") != "undefined" && $("#fromFacilityId").val()=="true")
	{		
		$("#pChar0").show();
		$("#imgId").attr('src','/JuvenileCasework/images/contract.gif');
		$("#fromFacilityId").val("");
		document.getElementById( "pChar0" ).className = 'visibleTR';
	}
	if(typeof $("#dlExpDateId") != "undefined")
	{		
		datePickerSingle( $("#dlExpDateId"),"DL Exp Date",false);
	}
	if(typeof $("#isTattoo") != "undefined")
	{		
		
		if($("#isTattoo").val()=='true')
		{
			$("#photo0").attr('class', 'visibleTR');
			$("#photoId").attr('src','/JuvenileCasework/images/contract.gif');
		}
		else
		{
			$("#photo0").attr('class', 'hidden');
			$("#photoId").attr('src','/JuvenileCasework/images/expand.gif');
		}
	}
	if(typeof $("#sentToDPSDateId")!= "undefined")
	{
		datePickerSingle( $("#sentToDPSDateId"),"Date Sent to DPS",false);
	}
	
	
	if(typeof $("#expDate") != "undefined"){
		datePickerSingle($("#expDate"),"Expiration Date",false);
	}
	
	//Used by multiple Pages for casework.js functions
	//---------------------------------------------------------------------------------
	$("#aliasCreateBack,#aliasBackDelete,#aliasBackSummary,#masterUpdateBack,#updateSuccessBack," +
			"#physCharCreateBack,#physCharSummBack,#scarTatooBack,#tatooConfirmBack").on("click",function(){
		goNav("back");
	});
	
	$("#updateSuccessSubmit,#physCharSummFinish").click(function(){
		return disableSubmitButtonCasework($(this));
		
	});
	
	//juvenileAliasInfoCreate.jsp
	//---------------------------------------------------------------------------------
	$("#aliasValidation").on('click',function(){
		return validateJuvenileAliasInfoForm(this.form);
	});
	
	//juvenileProfileMasterUpdate.jsp
	//---------------------------------------------------------------------------------
		
	$("#masterUpdateCancel").on("click",function(){		
		goNav('/JuvenileCasework/displayJuvenileMasterInformation.do');
	});
	
	//formerly function adoptCallback( isAdopted ){
	$("[name='adopted']").on("change",function(){
		if( $(this).val() == 'true' )
		{
			$("#adoptFailedRow").show();
			$("#adoptCommentsRow").show();
		}
		else
		{
			$("#adoptFailedRow").hide();
			$("#adoptCommentsRow").hide();
		}
	});
	
	//formerly function checkDLNum( tForm ){
	$("[name='driverLicenseNum']").on('blur',function(){
		if( $("[name='driverLicenseNum']").val() == "" )
		{
			$("[name='driverLicenseStateId']").val("");
			$("[name='driverLicenseClassId']").val("");
			$("[name='driverLicenseExpireDate']").val("");
		}
	});
	
	//formerly function flipBirthCounty(){
	$("[name='countryId'],[name='stateId']").on('change',function(){
	  var theCountySelect = $("[name='birthCountyId']");
	  var theStateSelect = $("[name='stateId']");
	  var theCountrySelect = $("[name='countryId']");
	  var selectedCounty = $("[name='birthCountyId']").val();
	  var selectedState = $("[name='stateId']").val();
	  var selectedCountry = $("[name='countryId']").val();
	  
	  if(selectedState == "")
	  {
		  $(theStateSelect).val("TX");
	   selectedState = $("[name='stateId']").val();
	  }
	  
	  if(selectedCountry == "")
	  {
		  $(theCountrySelect).val("US");
		  selectedCountry = $("[name='countryId']").val();
	  }
	  
	  if( selectedCountry == "US" && selectedState == "TX")
	  {  	
		  $(theCountySelect).prop("disabled",false);
	    if( selectedCounty == "")
	   	{
	    	$("[name='birthCountyId']").val(harrisCountyDropDownValue);
	   	}
	  }
	  else 
	  {
		$(theCountySelect).val("");
	    $(theCountySelect).prop("disabled",true);
	  }

	  if (selectedCountry != "US")
		{  
		  $(theStateSelect).val("");
	  	$(theStateSelect).prop("disabled",true);
	  }
	  else
		{
	  	$(theStateSelect).prop("disabled",false);
	  }
	});
	
	//formerly function validateThisForm(){
	////U.S 88526
	$("#next").on("click",function (){
		return(
				birthDateValidation(document.forms[0].dateOfBirth.value,'Date of Birth',10, 20) && 
				validateId(document.forms[0]) && 
				validateAdoption(document.forms[0]) &&
				validateJuvenileProfileMainForm(document.forms[0]) && validateDriversLicense()); //&&
				//validateRace(document.forms[0])) ; //added to make the field Race required
	});
	
	function validateId(dl){ 
		clearAllValArrays();
		var elem = $("[name='driverLicenseNum']").val();

		if( elem != null )
		{
			if( elem != "" )
			{ 
		   		customValRequired("driverLicenseStateId","DL State is Required","");
				customValRequired("driverLicenseClassId","DL Class is Required","");
				customValRequired("driverLicenseExpireDate","DL Exp Date is Required","");
			}
			else if( $("[name='driverLicenseStateId']").val() != ""  ||
					$("[name='driverLicenseClassId']").val() != ""  ||
					$("[name='driverLicenseExpireDate']").val() != "" )
			{
				alert( "DL Number is required if other Driver License information is entered." ) ;
				$("[name='driverLicenseNum']").focus() ;
				return( false ) ;
			}
		}

		return validateCustomStrutsBasedJS(dl) ;
	}
	
	function validateAdoption(el){
		clearAllValArrays();	
		addNumericValidation("failedPlacements", "Failied Placements must be numeric.");

		if($("[name='adopted']:checked").val() == 'true')
		{
		    customValRequired("failedPlacements", "Failed Placements is required if the juvenile is adopted.");
		    customValRequired("adoptionComments", "Adoption comments are required if the juvenile is adopted.");
		    addDB2FreeTextValidation("adoptionComments", "Adoption Comments must be alphanumeric with no leading spaces and the following symbols . '  & ; ,  ( ) / along with spaces are allowed.");
		    customValMaxLength("adoptionComments", "Adoption comments cannot be longer than 55 characters",55);
	  	}

		return validateCustomStrutsBasedJS(el) ;
	}
	//all functions called on page start. Put in back to avoid errors
	if(document.title == "JIMS2 - juvenileProfileMasterUpdate.jsp")
	{
		$("[name='countryId']").trigger("change");
		if($("[name='adopted']").val() == "true")
		{
			$("[name='adopted']").trigger("change");
		}
		if($("[name='matchDetectedSSN']").val() == "true")
		{
			var file = '/JuvenileCasework/jsp/juvTabMain/juvenileMatchingMembersList.jsp';
			var window = 'matchingJuvs';
			msgWindow = open(file,window,'resizable=yes, scrollbar=yes, width=800,height=600');

			  if (msgWindow.opener == null)
			  { 
			    msgWindow.opener = self;
			  }
		}
	}
	
	//juvenilePhysicalCharacteristicCreate.jsp
	//---------------------------------------------------------------------------------
	$("#physCharCreateNext").on('click',function(){
		/*return validateJuvenilePhysicalCharacteristicsForm(this.form);*/
		finalMsg="";
		var buildId=$("#buildId").val();
		var feet=$("#height").val();
		var inches=$("#heightInch").val();
		var weight=$("#weight").val();
		
/*		isPhysCharRequired(buildId,feet,inches,weight);
		isPhysCharNumeric(buildId,feet,inches,weight);
		isPhysCharInRange(buildId,feet,inches,weight);*/

		return isPhysCharRequired(buildId,feet,inches,weight) && isPhysCharNumeric(buildId,feet,inches,weight) && isPhysCharInRange(buildId,feet,inches,weight);
		
	});
	
   function isPhysCharRequired(buildId,feet,inches,weight){
	   finalMsg="";
	    isPhysCharNotEmpty(buildId, "Build Id is required.", "#buildId");
		isPhysCharNotEmpty(feet, "Height in feet is required.", "#height");
		isPhysCharNotEmpty(inches, "Height in inches is required.", "#heightInch");
		isPhysCharNotEmpty(weight, "Weight is required.", "#weight");
		
		if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 
		return true;
   }
   
   function isPhysCharNumeric(buildId,feet,inches,weight){
	   finalMsg="";
	   isPhysCharNotNumeric(feet, "Height in feet must be numeric.", "#height");
	   isPhysCharNotNumeric(inches, "Height in inches must be numeric.", "#heightInch");
	   isPhysCharNotNumeric(weight, "Weight must be numeric.", "#weight");
	   
	   if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 
	   return true;
   }
	
   
   function isPhysCharInRange(buildId,feet,inches,weight){
	   finalMsg="";
	   isPhysCharNotInRange(feet, "Height in feet is not in the range 3 through 8.", 3 , 8, "#height");
	   isPhysCharNotInRange(inches, "Height in inches is not in the range 0 through 11.", 0 , 11, "#heightInch");
	   isPhysCharNotInRange(weight, "weight is not in the range 50 through 499.", 50 , 499, "#weight") ;
	   
	   if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 
	   return true;
   }
   

	function isPhysCharNotEmpty(field, fieldMsg, domElementId){
		if(field == ""){
			finalMsg += fieldMsg + "\n";	
		}
	}
	
	function isPhysCharNotNumeric(field, fieldMsg, domElementId){
		 if(isNaN(field)){
			 finalMsg += fieldMsg + "\n";	
		}
	
	}
	
	function isPhysCharNotInRange(field, fieldMsg, lowerRange, upperRange, domElementId ) {
		if(field<lowerRange || field>upperRange){
			 finalMsg += fieldMsg + "\n";	
		}

	}
		
	
	//juvenileScarsAndTatoos.jsp
	//---------------------------------------------------------------------------------
	//function textLimit(field){ accumulates new and old text to check for length
	$("[name='newOtherTattooComments']").on('keyup mouseout',function(){
		var allOtherTattooComments = $("[name='allOtherTattooComments']");
		if ($(this).val().length + $(allOtherTattooComments).val().length > 1500){
	  		$(this).val($(this).val().substring(0, 1500 - $(allOtherTattooComments).val().length));
	  		alert('Your input has been truncated to 1500 characters!');
		}
	});
	
	//juvenileScarsAndTattoosUpdateConfirmation.jsp
	//---------------------------------------------------------------------------------
	//custom version of disable submit closer resembling that of app.js
	$("#tatooConfirmFinish").click(function(){	
		changeFormActionURL("/JuvenileCasework/saveJuvenileTattooAndScarsCreateSummary.do",false); 
		//disableSubmitButtonCasework($(this));	
	});
	
	$("#profileMasterDetails").click(function(){	
		changeFormActionURL("/JuvenileCasework/displayJuvenileMasterInformation.do",true); 
	});
	

	$("#jisCreateBack").on('click',function(){
		goNav('back');
	});
	
	/*$("#masterUpdateReturn").click(function(){		
		changeFormActionURL("/JuvenileCasework/displayJuvenileProfileUpdate.do",false); 
	});*/ //89662
	
	$("#masterSSNUpdateSubmit").on('click',function(){
		var ssn1=$("#SSN1").val();
		var ssn2=$("#SSN2").val();
		var ssn3=$("#SSN3").val();
		
		if(ssn1 == "000"){
			alert("First three digits of Social Security Number is invalid.");
			return false;
		}
		return isSsnRequired(ssn1,ssn2,ssn3) && isSsnNumeric(ssn1,ssn2,ssn3) && isSsnInRange(ssn1,ssn2,ssn3);
		
	
	});
	
	
	function isSsnRequired(ssn1,ssn2,ssn3){		
		
		SsnFinalMsg="";
		
	    isFieldNotEmpty(ssn1, "First three digits of Social Security Number is required.", "#SSN1");
	    isFieldNotEmpty(ssn2, "Middle two digits of Social Security Number is required.", "#SSN2");
	    isFieldNotEmpty(ssn3, "Last four digits of Social Security Number is required.", "#SSN3");	
	    
	    if(SsnFinalMsg != ""){
			alert(SsnFinalMsg);
			return false;
		} 
	   return true;
	}
	
	function isSsnNumeric(ssn1,ssn2,ssn3){
		
		SsnFinalMsg="";

		 isFieldNumeric(ssn1, "First three digits of Social Security Number must be numeric.", "#SSN1");
		 isFieldNumeric(ssn2, "Middle two digits of Social Security Number must be numeric.", "#SSN2"); 
		 isFieldNumeric(ssn3, "Last four digits of Social Security Number must be numeric.", "#SSN3");
		   
		   if(SsnFinalMsg != ""){
				alert(SsnFinalMsg);
				return false;
			} 
		   return true;
		
	}
	
	function isSsnInRange(ssn1,ssn2,ssn3){
		
		SsnFinalMsg="";
		
		isFieldInRange(ssn1, "First three digits of Social Security Number cannot be less than 3 characters", 3 , "#SSN1");
		isFieldInRange(ssn2, "Middle two digits of Social Security Number cannot be less than 2 characters", 2 , "#SSN2");
		isFieldInRange(ssn3, "Last four digits of Social Security Number cannot be less than 4 characters", 4 , "#SSN3");
		
		if(SsnFinalMsg != ""){
			alert(SsnFinalMsg);
			return false;
		} 
	   return true;
	}
	
	function isFieldNumeric(field, fieldMsg, domElementId){
		regEx=/^[0-9]*$/;
		 if(isNaN(field) || regEx.test(field) == false){
			 SsnFinalMsg += fieldMsg + "\n";
		}
	
	}
	
	function isFieldNotEmpty(field, fieldMsg, domElementId){
		if(field == ""){
			 SsnFinalMsg += fieldMsg + "\n";
		}

	}
	
	function isFieldInRange(field, fieldMsg, range, domElementId ) {
		if(field.length<range){
			SsnFinalMsg += fieldMsg + "\n";
		}

	}

	
	
	$("#jisListCreateNext").on('click', function(){
		
		
		var fld1 = $("[name='selectedJISAgencies']");
		var selectedCount=0;
		var selectedAgency;
		if($(fld1).length)
		{			
			for (var i = 0; i < fld1.length; i++) 
			{ 				
				if (fld1[i].checked == true)
				{
					selectedCount++;
					selectedAgency=fld1[i].value;
				} 
			}
		}
		
		if(selectedCount==0)
		{
			alert("Agency selection is required to proceed.")
			return false;
		}
		var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
		var fld = ($("#otherAgency").val()).trim();
		if(selectedAgency=="OTH")
		{			
			if($("#otherAgency").val()=="")
			{
				alert("Text entry required for the selection OTHER.")
				$("#otherAgency").focus();
				return false;
			}
			else if($("#otherAgency").val().length<3)
			{
				alert("Other Agency should have atleast 3 characters.")
				$("#otherAgency").focus();
				return false;
			}
			else if (alphaNumericRegExp.test(fld,alphaNumericRegExp) == false) {
				alert("Other Agency contains invalid character(s), only alphanumeric values allowed.");
				$("#otherAgency").focus();
				return false;
			}
		}
		else if($("#otherAgency").val()!="")
		{
			alert("OTHER must be selected to have Other Agency.");
			$("#otherAgency").focus();
			return false;
		}
	});
	
	function validateDriversLicense(){
		var result = true;
		var driverLicenseNum = $("#driverLicenseNum").val().trim();
		
		regExp = /^[a-zA-Z0-9#,./' \-]*$/;
		
		if(!regExp.test(driverLicenseNum, regExp))
		{
			alert("only alphanumeric characters and the following special characters ('#', '/', '.', ',', '-') are allowed");
			$("#driverLicenseNum").focus();
	 		result = false;
		}
		
		return result;
	}
		
});