//<!-- JavaScript - Address Check -->
//Converted to use JQuery
$(document).ready(function () {

	// set state TX and county Harris as default
	if ( $("#stateId").val() == "" ){
		$("#stateId").val("TX");
	}
	
	if ( $("#county1").val() == ""  ){
		$("#county1").val("101");
	}
	
	
	// check coming from casefile closing required fixes
	var currentMemberId = $("#memberId").val();
	var finalMsg="";
	
	if(currentMemberId == ''){
		alert("A Guardian must be created to proceed.");
		$("#validateAddress").prop("disabled",true);
	}
	//formerly function validateAddressFields(theForm)
	$("#validateAddress").on("click", function(){
		
		finalMsg="";
		var streetNumber=$("#streetNumber").val();
		var streetName=$("#streetName").val();
		var city=$("#city").val();
		var zipCode=$("#zipCode").val().trim();
		var additionalZipCode=$("#additionalZipCode").val().trim();
		var stateId=$("#stateId").val();
		var addressType=$("#addressType").val();
		var apt=$("#apt").val();
		
		return isAddressRequired(streetNumber,streetName,city,zipCode,stateId,addressType) && isAddressAlphaNumeric(streetNumber,streetName,city,zipCode,additionalZipCode,apt,addressType) && isAddressInRange(city,zipCode,additionalZipCode);
	   
	});
	
	function isAddressRequired(streetNumber,streetName,city,zipCode,stateId,addressType){	

		finalMsg="";
		isFieldNotEmpty(streetNumber, "Street Number is required.", "#streetNumber");
		isFieldNotEmpty(streetName, "Street Name is required.", "#streetName"); 
		isFieldNotEmpty(city, "City is required.", "#city");
		isFieldNotEmpty(stateId, "State is required.", "#stateId");
		isFieldNotEmpty(zipCode, "Zip Code is required.", "#zipCode");
		isFieldNotEmpty(addressType, "Address Type is required.", "#addressType");
		
		if(finalMsg == ""){
			return true;
		}else {
			alert(finalMsg);
			return false;
		} 
	}
	
	function isAddressInRange(city,zipCode,additionalZipCode){
		return isFieldInRange(city, "City", 2 , "#city") && isFieldInRange(zipCode, "Zip Code", 5 , "#zipCode") && isFieldInRange(additionalZipCode, "Zip Code", 4 , "#additionalZipCode") ;
	}
	
	
	function isAddressAlphaNumeric(streetNumber,streetName,city,zipCode,additionalZipCode,apt,addressType){
		
		finalMsg="";
		isFieldAlphaNumeric(streetNumber,"Street Number must be alphanumeric.",/^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/,"#streetNumber");
		isFieldAlphaNumeric(streetName,"Street Name must be alphanumeric.",/^[a-zA-Z0-9][a-zA-Z0-9 \'.,/\();\-]*$/,"#streetName");
		isFieldAlphaNumeric(apt,"Apt/Suite must be alphanumeric.",/^[a-zA-Z0-9\s]*$/,"#apt");
		isFieldAlphaNumeric(city,"City must be alphanumeric.",/^[a-zA-Z0-9][a-zA-Z0-9 \'\-]*$/,"#city");
		isFieldNumeric(zipCode, "zip Code must be numeric.", "#zipCode");
		isFieldNumeric(additionalZipCode, "zip Code must be numeric.", "#additionalZipCode");

		
		if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 
	   return true;
		
	}
	
	function isFieldNotEmpty(field, fieldMsg, domElementId){
		if(field == "" || field === null || field === undefined){	
			finalMsg += fieldMsg + "\n";	
		}
		/*return true;*/
	}
	
	function isFieldInRange(field, fieldMsg, range, domElementId ) {
		if(field.length<range && field!=""){
			alert(fieldMsg +" cannot be less than " + range + " characters");
			$(domElementId).focus();
			return false;
		}
		return true;
	}
	
	function isFieldAlphaNumeric(field,fieldMsg,alphaNum,domElementId){
		
		var letters = alphaNum;
		if(letters.test(field) == false && field!=""){
			finalMsg += fieldMsg + "\n";
		}
	}
	
	function isFieldNumeric(field, fieldMsg, domElementId){
		 if(isNaN(field) && field!=""){
			 finalMsg += fieldMsg + "\n";
		}
	/*	return true;*/
		
	}
	
	//added in from familyMemberAddressCreate.jsp file
	//formerly function newWindow(file,window)
	//newWindow('/JuvenileCasework/submitManageFamilyMemberAddressAction.do?submitAction=Find','selectionAddressWindow')
	$("#newWindow").on("click",function(){
		var file = '/JuvenileCasework/submitManageFamilyMemberAddressAction.do?submitAction=Find';
		var window = 'selectionAddressWindow';
		msgWindow = open(file,window,'resizable=yes, scrollbar=yes, width=800,height=600');
		if (msgWindow.opener == null) 
			msgWindow.opener = self;
	});
	
	// formerly function populateUnknownAddress(el, checkboxName)
	$("[name='unknownAddress']").on("click", function(){

	//	var theForm = el.form;
	//	var checkAllName = el.name;
	//	var objCheckBoxes = document.getElementsByName(checkboxName);
	//	var countCheckBoxes = objCheckBoxes.length;
	
		if($(this).prop("checked"))
		{
			$("[name='currentAddress.streetNumber']").val("0000");
			$("[name='currentAddress.streetNumSuffixId']").val("");
			$("[name='currentAddress.streetName']").val("UNKNOWN");
			$("[name='currentAddress.streetTypeId']").val("");
			$("[name='currentAddress.aptNumber']").val("");
			$("[name='currentAddress.city']").val("UNKNOWN");
			$("[name='currentAddress.stateId']").val("TX");
			$("[name='currentAddress.zipCode']").val("00000");
			$("[name='currentAddress.additionalZipCode']").val("");
			$("[name='currentAddress.addressTypeId']").val("RES");
			$("[name='currentAddress.countyId']").val("");
		} else {
			   
			$("[name='currentAddress.streetNumber']").val("");
			$("[name='currentAddress.streetNumSuffixId']").val("");
			$("[name='currentAddress.streetName']").val("");
			$("[name='currentAddress.streetTypeId']").val("");
			$("[name='currentAddress.aptNumber']").val("");
			$("[name='currentAddress.city']").val("");
			$("[name='currentAddress.stateId']").val("");
			$("[name='currentAddress.zipCode']").val("");
			$("[name='currentAddress.additionalZipCode']").val("");
			$("[name='currentAddress.addressTypeId']").val("");
			$("[name='currentAddress.countyId']").val("");
		}
	});
	
	// formerly function populateOutOfCountry(el, checkboxName)
	$("[name='outOfCountry']").on("click", function(){
	//	var theForm = el.form;
	//	var checkAllName = el.name;
	//	var objCheckBoxes = document.getElementsByName(checkboxName);
	//	var countCheckBoxes = objCheckBoxes.length;
	
		if($(this).prop("checked"))
		{
			$("[name='currentAddress.streetNumber']").val("0000");
			$("[name='currentAddress.streetNumSuffixId']").val("");
			$("[name='currentAddress.streetName']").val("UNKNOWN");
			$("[name='currentAddress.streetTypeId']").val("");
			$("[name='currentAddress.aptNumber']").val("");
			$("[name='currentAddress.city']").val("UNKNOWN");
			$("[name='currentAddress.stateId']").val("AP");
			$("[name='currentAddress.zipCode']").val("00000");
			$("[name='currentAddress.additionalZipCode']").val("");
			$("[name='currentAddress.addressTypeId']").val("RES");
			$("[name='currentAddress.countyId']").val("");
		} else {
			   
			$("[name='currentAddress.streetNumber']").val("");
			$("[name='currentAddress.streetNumSuffixId']").val("");
			$("[name='currentAddress.streetName']").val("");
			$("[name='currentAddress.streetTypeId']").val("");
			$("[name='currentAddress.aptNumber']").val("");
			$("[name='currentAddress.city']").val("");
			$("[name='currentAddress.stateId']").val("");
			$("[name='currentAddress.zipCode']").val("");
			$("[name='currentAddress.additionalZipCode']").val("");
			$("[name='currentAddress.addressTypeId']").val("");
			$("[name='currentAddress.countyId']").val("");
		}
	});
	
	//formerly function refreshPage(theForm){
	$("[name='reset']").on("click", function(){
		$("[name='currentAddress.streetNumber']").val("");
		$("[name='currentAddress.streetNumSuffixId']").val("");
		$("[name='currentAddress.streetName']").val("");
		$("[name='currentAddress.streetTypeId']").val("");
		$("[name='currentAddress.aptNumber']").val("");
		$("[name='currentAddress.city']").val("");
		$("[name='currentAddress.stateId']").val("");
		$("[name='currentAddress.zipCode']").val("");
		$("[name='currentAddress.additionalZipCode']").val("");
		$("[name='currentAddress.addressTypeId']").val("");
		$("[name='currentAddress.countyId']").val("");
	});

	
	//added in from familyMemberAddressSelectionList.jsp file
	var currentSelectedMemberAddr=0;

	//formerly function setMemberAddr(memberNumAddr){
	$("[name='twoLane']").on("change", function(){
		currentSelectedMemberAddr=$('input[name=twoLane]:checked').val();
		$("#hiddenVal").val(currentSelectedMemberAddr);
	});

	function load(file,target) {
	    if (target != '')
	        target.window.location.href = file;
	    else
	        window.location.href = file;
	}

	//formerly function loadAddress(file){
	$("#loadAddress").on('click', function(){
		var file = '/JuvenileCasework/submitManageFamilyMemberAddressAction.do?submitAction=Select';
		var myURL=file + "&selectedValue=" + currentSelectedMemberAddr;
		load(myURL,top.opener);
		window.close();
	});
	
	$("#updateBtn").on('click', function () {
    	spinner();
    	$('form').attr('action',"/JuvenileCasework/submitManageFamilyMemberAddressAction.do?submitAction=Update");
		$('form').submit();
    });
});

