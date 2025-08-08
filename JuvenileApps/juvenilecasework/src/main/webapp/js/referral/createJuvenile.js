/**
 * Create Juvenile JQuery
 */
$(document).ready(function () {
	
	var myForm = document.forms[0];
	var action = $("#action").val();
	//var updateJuvenileSsn = false;
	if (action=="updateJuvenile"){
		$("#juvenileNum").attr("disabled", true);
		$('#AddNewGuardian').hide();
		$("#lastName").focus();

		//BUG 80391
		$("#updateGuardianBtn").prop("disabled",true);

		//to loop through the whole radio buttons
		$("input[id^='memBeanIdRadio']").on('click', function (e) {
			$("#updateGuardianBtn").prop("disabled", false);
		});
		
		//Go button in School Section 
		$("#changeSchlDistGoBtn").on('click', function(){
			$('form').attr('action','/JuvenileCasework/displayCreateJuvenile.do?submitAction=GO');
			$('form').submit();
		});
		
		$("#updateGuardianBtn").on('click', function(){
			var checkedRadioVal = $("input[name='selectedMemBean']:checked").val();
			if(	checkedRadioVal == null  ){
				alert("Please select the family member to be updated by clicking the radio button on the left." );
				return false;
			}
			$('form').submit();
			goNav('/JuvenileCasework/processReferralBriefing.do?submitAction=Load Member&famMemId='+checkedRadioVal);
	});
		
		$("#updateGuardnRecordBtn").on('click', function(){
			//BUG 80520 changes
			 if(!$("#incarcerated,#deceased").is(':checked')){
				 $("#incarceratedFlag").val("N");
			 }
			 else  if($("#incarcerated").is(':checked'))
			 {
				 $("#incarceratedFlag").val("I");
			 }
			 else  if($("#deceased").is(':checked'))
			 {
				 $("#incarceratedFlag").val("D");
			 }
			//BUG 80520 changes ENDS
		   if($("#memLastName").val() == "") 
		   {
		      alert("Parent/Guardian Last Name is required.");
		      $("#memLastName").focus();
		      return false;
		   }
		   if($("#memFirstName").val() == "") 
		   {
		      alert("Parent/Guardian First Name is required.");
		      $("#memFirstName").focus();
		      return false;
		   }
		   
		   if($("#relationship").val() == "") 
		   {
		      alert("Parent/Guardian Relationship is required.");
		      $("#relationship").focus();
		      return false;
		   }
		   
		  if(!$("#incarcerated,#deceased").is(':checked')){
			   if ($("#memAddStreetNumber").val() == "") 
			   {
			      alert("Parent/Guardian Address Street Number is required.");
			      $("#memAddStreetNumber").focus();
			      return false;
			   }
			   if ($("#memAddStreetName").val() == "") 
			   {
			      alert("Parent/Guardian Address Street Name is required.");
			      $("#memAddStreetName").focus();
			      return false;
			   }
			   if ($("#memAddCity").val() == "") 
			   {
			      alert("Parent/Guardian Address City is required.");
			      $("#memAddCity").focus();
			      return false;
			   }
			   if ($("#memAddStateId").val() == "") 
			   {
			      alert("Parent/Guardian Address State selection is required.");
			      $("#memAddStateId").focus();
			      return false;
			   }
			   if ($("#memAddStateId").val() == "") 
			   {
			      alert("Parent/Guardian Address State selection is required.");
			      $("#memAddStateId").focus();
			      return false;
			   }
			   if ($("#memAddZipCode").val() == "") 
			   {
			      alert("Parent/Guardian Address Zip Code is required.");
			      $("#memAddZipCode").focus();
			      return false;
			   }
			   if(!$("#memAddMobile").is(":checked") && !$("#memAddHome").is(":checked")){
					alert("Parent/Guardian Phone Type selection is required");
					$("#memAddHome").focus();
					return false;
			  	}
				   //phone validation
			 if($("#memAddAreaCode").val()=="" || $("#memAddPrefix").val()=="" || $("#memAddLast4Digit").val()=="" ){
				   alert("Please Enter a Valid Phone Number");
				   if($("#memAddAreaCode").val()=="")
				   {
					   $("#memAddAreaCode").focus();
				   }
				   else if($("#memAddPrefix").val()=="")
				   {
				   		$("#memAddPrefix").focus();
				   }
				   else if($("#memAddLast4Digit").val()=="")
				   {
				  		  $("#memAddLast4Digit").focus();
				   }
				   return false;
				}
			   
			   /*if(!$("#memAddUnknownPhoneId").is(":checked")){
				   $("#memAddPrimaryIndId").prop("checked",true); //default if none selected.
			   }*/ /*BUG 85197*/
		  }
		  
		  if (validateFields() == false) {
				return false;
			}
		  
		  
		});
		
		/*$("#addNewGuardianBtn").on('click', function(){
		//$('#AddNewGuardian').show(); //no need
	});*/
		
		/*$("#addGuardianToListBtn").on('click', function(){
		});*/
		
	}//end Update Juvenile Changes
	
	// 89530 done on the jsp
	//$("#dateOut").attr("disabled",true);
	//$("#lastUpdate").attr("disabled",true);
	//$("#operatorId").attr("disabled",true);
	// 89530 done on the jsp
	
	//juvenileDOB DATE CALENDAR.
	if(typeof $("#dateOfBirth") != "undefined"){
		datePickerSingle($("#dateOfBirth"),"Date Of Birth",true);
	}
	
	//real DOB DATE CALENDAR.
	if(typeof $("#realDateOfBirth") != "undefined"){
		datePickerSingle($("#realDateOfBirth"),"Date Of Birth",true);
	}
	
	//validate 'realDateOfBirth' - US 172672
	$("#realDateOfBirth").change(function() {
								
		var isGreater = isRealDOBGreater();
		if(isGreater){
			alert('Real DOB cannot be greater than the original date of birth.');
			//var dob = $("#dateOfBirth").val();
			$("#realDateOfBirth").val("");
			$("#realDateOfBirth").focus();
			return false;
		}
	});
	
	//allow update of realDateOfBirth for users that have feature - JCW_JCW_REF_REALDOB_UPDATE 
	var isRealDOBUpdateAllowed = $("#isRealDOBUpdateAllowed").val();
	
	if(isRealDOBUpdateAllowed === "true"){
		$("#realDateOfBirth").attr("disabled", false);
		//document.getElementById("realDateOfBirth").disabled = false;
	}
	
	//dateOut  CALENDAR. //89530
	if(typeof  $("#dateOut") != "undefined"){
		datePickerSingle($("#dateOut"),"Date Out",true);
	}
	
	$("#findBtn").on("click", function(){
		var schlName = document.getElementByName("schoolName");
		//alert(schlName);
		if (schlName == null){
			alert("Please enter a school name to search." );
			return false;
		}
	});
	
	$("#schDistId").on("change",function()
	{
		var mySchoolElem=document.getElementsByName('schoolId')[0];
		mySchoolElem.options.length = 0;
		mySchoolElem.options[0] = new Option( "Please Select", "", false, false );
	});
	
	function validateFields() {
		if ($('#memLastName').length
				&& isFieldAlphaNumeric($("#memLastName").val(),
						/^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
			alert("Parent/Guardian Last Name must be alphanumeric.");
			$("#memLastName").focus();
			return false;
		}

		if ($('#memFirstName').length
				&& isFieldAlphaNumeric(
						$("#memFirstName").val(),
						/^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
			alert("Parent/Guardian First Name must be alphanumeric.");
			$("#memFirstName").focus();
			return false;
		}

		if ($("input[name='selectedMemberBean.middleName']").length
				&& isFieldAlphaNumeric(
						$(
								"input[name='selectedMemberBean.middleName']")
								.val(),
						/^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
			alert("Parent/Guardian Middle Name must be alphanumeric.");
			$("#memFirstName").focus();
			return false;
		}
		
		var SSN1O = $("input[name='selectedMemberBean.SSN.SSN1']");
		var SSN2O = $("input[name='selectedMemberBean.SSN.SSN2']");
		var SSN3O = $("input[name='selectedMemberBean.SSN.SSN3']");

		var ssn1Regex = /^(?!000)([0-9]{3}$)/
		var ssn2Regex = /^([0-9]{2}$)/
		var ssn3Regex = /^([0-9]{4}$)/

		var SSN1 = $.trim(SSN1O.val());
		var SSN2 = $.trim(SSN2O.val());
		var SSN3 = $.trim(SSN3O.val());					
		

		if (SSN1.length > 0
				|| SSN2.length > 0
				|| SSN3.length > 0) {
			if (ssn1Regex.test(SSN1) == false && SSN1 != 'XXX' && SSN1.length > 0) {
				alert("First 3 digits of SSN must be numeric and not equal 000.\n");
				SSN1O.focus();
				return false;
			}
			if (ssn2Regex.test(SSN2) == false && SSN2 != 'XX' && SSN2.length > 0) {
				alert("Second 2 digits of SSN must be numeric.\n");
				SSN2O.focus();
				return false;
			}
			if (ssn3Regex.test(SSN3) == false && SSN3.length > 0) {
				alert("Last 4 digits of SSN must be numeric.\n");
				SSN3O.focus();
				return false;
			}
		}

		if (typeof SSN1O != "undefined") {

			if (SSN1O.val() != ""
					&& SSN2O.val() == "") {
				alert("All of Social Security number must be entered if there is partial entry.");
				SSN2O.focus();
				return false;
			}
			if (SSN1O.val() != ""
					&& SSN3O.val() == "") {
				alert("All of Social Security number must be entered if there is partial entry.");
				SSN3O.focus();
				return false;
			}
			if (SSN2O.val() != ""
					&& SSN1O.val() == "") {
				alert("All of Social Security number must be entered if there is partial entry.");
				SSN1O.focus();
				return false;
			}
			if (SSN2O.val() != ""
					&& SSN3O.val() == "") {
				alert("All of Social Security number must be entered if there is partial entry.");
				SSN3O.focus();
				return false;
			}
			if (SSN3O.val() != ""
					&& SSN1O.val() == "") {
				alert("All of Social Security number must be entered if there is partial entry.");
				SSN1O.focus();
				return false;
			}
			if (SSN3O.val() != ""
					&& SSN2O.val() == "") {
				alert("All of Social Security number must be entered if there is partial entry.");
				SSN2O.focus();
				return false;
			}
			
		}
	
		

		if ($('#incarcerated').length
				&& !$("#incarcerated,#deceased").is(':checked')) {
			if (isFieldAlphaNumeric($("#memAddStreetNumber")
					.val(), /^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
				alert("Parent/Guardian Address Street Number must be alphanumeric.");
				$("#memAddStreetNumber").focus();
				return false;
			}

			if (isFieldAlphaNumeric($("#memAddStreetName")
					.val(), /^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
				alert("Parent/Guardian Address Street Name must be alphanumeric.");
				$("#memAddStreetName").focus();
				return false;
			}

			if (isFieldAlphaNumeric(
					$("#memAddAptNumber").val(),
					/^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
				alert("Parent/Guardian Apt/Suite must be alphanumeric.");
				$("#memAddAptNumber").focus();
				return false;
			}

			if (isFieldAlphaNumeric($("#memAddCity").val(),
					/^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
				alert("Parent/Guardian Address City must be alphanumeric.");
				$("#memAddCity").focus();
				return false;
			}

			if (isFieldAlphaNumeric($("#memAddZipCode").val(),
					/^\d{5}$/) == false) {
				alert("ZIP code must be 5 or 9 digits.");
				$("#memAddZipCode").focus();
				return false;
			}

			if (isFieldAlphaNumeric($(
					"#memAddAdditionalZipCode").val(),
					/^\d{4}$/) == false) {
				alert("ZIP code must be 5 or 9 digits.");
				$("#memAddAdditionalZipCode").focus();
				return false;
			}
			if (isFieldAlphaNumeric($("#memAddAreaCode").val(),
					/^\d{3}$/) == false) {
				alert("Area code must be 3 digits.");
				$("#memAddAreaCode").focus();
				return false;
			}
			if (isFieldAlphaNumeric($("#memAddPrefix").val(),
					/^\d{3}$/) == false) {
				alert("Phone Prefix code must be 3 digits.");
				$("#memAddPrefix").focus();
				return false;
			}
			if (isFieldAlphaNumeric($("#memAddLast4Digit")
					.val(), /^\d{4}$/) == false) {
				alert("Phone Lat 4 must be digits.");
				$("#memAddLast4Digit").focus();
				return false;
			}

			if (isFieldAlphaNumeric($("#memAddExt").val(),
					/^[0-9]{1,6}$/) == false) {
				alert("Phone Extension must be numeric.");
				$("#memAddExt").focus();
				return false;
			}

		}

		return true;

	}

	function isFieldAlphaNumeric(field, alphaNum) {

		var letters = alphaNum;
		if (letters.test(field) == false && field != "") {
			return false;
		}
	}
	
	function wipeOutHomeSchools()
	{
		var mySchoolElem=document.getElementsByName('homeSchoolId')[0];

		mySchoolElem.options.length = 0;
		mySchoolElem.options[0] = new Option( "Please Select", "", false, false );
	}
	
	function setSpecificSchoolName(el)
	{
		var fld1 = document.getElementById("specificSchName");
		fld1.value = "";
		fld1.readonly = false;
		if (el.checked == true)
		{	
			fld1.value = "UNKNOWN";
			fld1.readonly = true;
		}
	}

	function checkSelectPA(el)
	{
		var elOptVal = el.options[el.selectedIndex].value;
		if (elOptVal == "HS"){
			el2 = document.getElementById("sasId");
			for (x=0; x<el2.options.length; x++)
			{
				if (el2.options[x].value == "HS"){
					el2.options.selectedIndex = x;
					break;
				}
			}
		}
		
		else if(elOptVal=="SE"){
		    resetHandicappedCon(true);
		    showHide('splEdu', 1 );
			
		}
		else{
			resetHandicappedCon(true);
			showHide('splEdu', 0 );
		}
	}
	// do the school validation only when the district is entered. Else that section is optional.
	if(typeof $("#schDistId") != "undefined"){
		
		if($("#schDistId").val()!="" || $("#schoolId").val()!="" || $("#gradeLevelId").val()!="" ||  $("#exitTypeId").val()!="" || $("#prgmAttendingId").val()!=""){
			
			$('#prgmAttendingId').on('change', function(){		
				showPAId();
			});
			
			function showPAId()
			{
				if ($('#prgmAttendingId').val() == "HS"){
					$("#sasId").val($('#prgmAttendingId').val()).attr("selected","selected");			
				}
				
				else if($('#prgmAttendingId').val() == "SE"){
				    $("#splEduCategoryId").val($("splEduCategoryId option:first").val());
				    $('#splEdu').show();
					
				}
				else{
					 $("#splEduCategoryId").val($("splEduCategoryId option:first").val());
					$('#splEdu').hide();
				}		
			}
			
			var selVal1 = $("#schDist").val();
			if (selVal1 == "050" || selVal1 == "051" || selVal1 == "141")
			{			
				var selVal2 = $("#schoolId").val();
				if (selVal1 == "050")
				{
					// show field for Private Elementry, Private Jr.High or Private High only			
					if (selVal2 == "001" || selVal2 == "300" || selVal2 == "500")
					{
						$('#specificNamefld').show();
					}	
				}
				if (selVal1 == "051")
				{
					// show field for Elementry, Middle, Jr.High or High School only			
					if (selVal2 == "001" || selVal2 == "300" || selVal2 == "301" || selVal2 == "500")
					{
						$('#specificNamefld').show();
					}		
				}
				if (selVal1 == "141") // show field for any school 
				{
					$('#specificNamefld').show();
				} 
			}
			
			var selVal3 = $('#prgmAttendingId').val();
			
			if (selVal3 == "SE")
			{
				$("#splEdu").show();		
			}
			
		}
	}

	$("#juvUnknownAddress").on("click", function(){
		if($(this).prop("checked"))
		{
			$("[name='juvAddress.streetNumber']").val("0000");
			$("[name='juvAddress.streetNumSuffixId']").val("");
			$("[name='juvAddress.streetName']").val("UNKNOWN");
			$("[name='juvAddress.streetTypeId']").val("");
			$("[name='juvAddress.aptNumber']").val("");
			$("[name='juvAddress.city']").val("UNKNOWN");
			$("[name='juvAddress.stateId']").val("TX");
			$("[name='juvAddress.zipCode']").val("00000");
			$("[name='juvAddress.additionalZipCode']").val("");
			$("[name='juvAddress.addressTypeId']").val("RES");
			$("[name='juvAddress.countyId']").val("");
		}
		else 
		{
			$("[name='juvAddress.streetNumber']").val("");
			$("[name='juvAddress.streetNumSuffixId']").val("");
			$("[name='juvAddress.streetName']").val("");
			$("[name='juvAddress.streetTypeId']").val("");
			$("[name='juvAddress.aptNumber']").val("");
			$("[name='juvAddress.city']").val("");
			$("[name='juvAddress.stateId']").val("");
			$("[name='juvAddress.zipCode']").val("");
			$("[name='juvAddress.additionalZipCode']").val("");
			$("[name='juvAddress.addressTypeId']").val("");
			$("[name='juvAddress.countyId']").val("");
		}
		$("#juvOutOfCountryId").attr("checked",false);
	});
	
	
	$("#juvOutOfCountryId").on("click", function(){
		if($(this).prop("checked"))
		{
			$("[name='juvAddress.streetNumber']").val("0000");
			$("[name='juvAddress.streetNumSuffixId']").val("");
			$("[name='juvAddress.streetName']").val("UNKNOWN");
			$("[name='juvAddress.streetTypeId']").val("");
			$("[name='juvAddress.aptNumber']").val("");
			$("[name='juvAddress.city']").val("UNKNOWN");
			$("[name='juvAddress.stateId']").val("AP");
			$("[name='juvAddress.zipCode']").val("00000");
			$("[name='juvAddress.additionalZipCode']").val("");
			$("[name='juvAddress.addressTypeId']").val("RES");
			$("[name='juvAddress.countyId']").val("");
		} 
		else 
		{
			$("[name='juvAddress.streetNumber']").val("");
			$("[name='juvAddress.streetNumSuffixId']").val("");
			$("[name='juvAddress.streetName']").val("");
			$("[name='juvAddress.streetTypeId']").val("");
			$("[name='juvAddress.aptNumber']").val("");
			$("[name='juvAddress.city']").val("");
			$("[name='juvAddress.stateId']").val("");
			$("[name='juvAddress.zipCode']").val("");
			$("[name='juvAddress.additionalZipCode']").val("");
			$("[name='juvAddress.addressTypeId']").val("");
			$("[name='juvAddress.countyId']").val("");
		}
		$("#juvUnknownAddress").attr("checked",false);
	});
	
	//incarcerated and deceased.
	
	$("#incarcerated").on("click",function(){
		if($("#incarcerated").is(":checked")){
			$("#memAddStreetNumber").val("");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("");
			$("#memAddStateId").val("");
			$("#memAddZipCode").val("");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("");
			$("#memAddCountyId").val("");
			$("#memAddAreaCode").val("");
			$("#memAddPrefix").val("");
			$("#memAddLast4Digit").val("");
			$("#memAddExt").val("");
			$("#memAddPrimaryIndId").attr('checked',false);
			$("#memAddUnknownPhoneId").val("");
			$("#memAddHome").val("");
			$("#memAddMobile").val("");
			
			$("#deceased").attr('checked',false);
			$("[id*=memAdd]").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
		}else{
			$("#memAddStreetNumber").val("");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("");
			$("#memAddStateId").val("");
			$("#memAddZipCode").val("");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("");
			$("#memAddCountyId").val("");
			
			$("#memAddAreaCode").val("");
			$("#memAddPrefix").val("");
			$("#memAddLast4Digit").val("");
			$("#memAddExt").val("");
			$("#memAddPrimaryIndId").val("");
			$("#memAddUnknownPhoneId").val("");
			$("#memAddHome").val("");
			$("#memAddMobile").val("");
			$("[id*=memAdd]").prop("disabled",false).css({ 'background-color' : '', 'opacity' : '' });
		}
	});
	
	$("#deceased").on("click",function(){
		if($("#deceased").is(":checked")){
			$("#memAddStreetNumber").val("");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("");
			$("#memAddStateId").val("");
			$("#memAddZipCode").val("");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("");
			$("#memAddCountyId").val("");
			
			$("#memAddAreaCode").val("");
			$("#memAddPrefix").val("");
			$("#memAddLast4Digit").val("");
			$("#memAddExt").val("");
			$("#memAddPrimaryIndId").attr('checked',false);
			$("#memAddUnknownPhoneId").val("");
			$("#memAddHome").val("");
			$("#memAddMobile").val("");
			
			$("#incarcerated").attr('checked',false);
			$("[id*=memAdd]").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
		}else{
			$("#memAddStreetNumber").val("");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("");
			$("#memAddStateId").val("");
			$("#memAddZipCode").val("");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("");
			$("#memAddCountyId").val("");
			$("[id*=memAdd]").prop("disabled",false).css({ 'background-color' : '', 'opacity' : '' });
		}
	});
	
	//out of country stuff
	$("#memAddUnknownAddress").on("click", function(){
		if($(this).prop("checked"))
		{
			$("#memAddStreetNumber").val("0000");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("UNKNOWN");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("UNKNOWN");
			$("#memAddStateId").val("TX");
			$("#memAddZipCode").val("00000");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("RES");
			$("#memAddCountyId").val("");
		}else {
			$("#memAddStreetNumber").val("");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("");
			$("#memAddStateId").val("");
			$("#memAddZipCode").val("");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("");
			$("#memAddCountyId").val("");
		}
		$("#memAddCountyId").prop("disabled",false);
		$("#memAddOutOfCountry").attr("checked",false);
	});
	
	
	$("#memAddOutOfCountry").on("click", function(){
		if($(this).prop("checked"))
		{
			$("#memAddStreetNumber").val("0000");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("UNKNOWN");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("UNKNOWN");
			$("#memAddStateId").val("AP");
			$("#memAddZipCode").val("00000");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("RES");
			$("#memAddCountyId").val("");
		} else {
			$("#memAddStreetNumber").val("");
			$("#memAddStreetNumSuffixId").val("");
			$("#memAddStreetName").val("");
			$("#memAddStreetTypeId").val("");
			$("#memAddAptNumber").val("");
			$("#memAddCity").val("");
			$("#memAddStateId").val("");
			$("#memAddZipCode").val("");
			$("#memAddAdditionalZipCode").val("");
			$("#memAddAddressTypeId").val("");
			$("#memAddCountyId").val("");
		}
		$("#memAddUnknownAddress").attr("checked",false);
		$("#memAddCountyId").prop("disabled",true);
	});
	
	
	//primary and unknown indicator
	$("#memAddPrimaryIndId").on("click",function(){
		  $("#memAddUnknownPhoneId").attr("checked",false);
	});
	 
	$("#memAddUnknownPhoneId").on("click",function(){
	  $("#memAddPrimaryIndId").attr("checked",false);
	});
	 
	
	 $("#backBtn").on('click', function (e) {
		 	spinner();
	    	$('form').attr('action',"/JuvenileCasework/displayCreateJuvenile.do?submitAction=Back");
			$('form').submit();
	    });	 
	 $("#cancelBtn").on('click', function (e) {
		 	spinner();	    	
	    });
	
	 
	//formerly function populateUnknownPhone(el, checkboxName)
		$("#memAddUnknownPhoneId").on('click', function(){
			if($(this).prop('checked') == true)
			{
				$("#memAddAreaCode").val("000");
				$("#memAddPrefix").val("000");
				$("#memAddLast4Digit").val("0000");
				$("#memAddExt").val("");
			}
			else{
				$("#memAddAreaCode").val("");
				$("#memAddPrefix").val("");
				$("#memAddLast4Digit").val("");
				$("#memAddExt").val("");
			}
		});
	/**
	 * Add to list button
	 */
	$("#addToListBtn").click(function () {
		   if($("#memLastName").val() == "") 
		   {
		      alert("Last Name is required.");
		      $("#memLastName").focus();
		      return false;
		   }
		   if($("#memFirstName").val() == "") 
		   {
		      alert("First Name is required.");
		      $("#memFirstName").focus();
		      return false;
		   }
		   
		   if($("#relationship").val() == "") 
		   {
		      alert("Relationship is required.");
		      $("#relationship").focus();
		      return false;
		   }
		   
		  if(!$("#incarcerated,#deceased").is(':checked')){
			   if ($("#memAddStreetNumber").val() == "") 
			   {
			      alert("Street Number is required.");
			      $("#memAddStreetNumber").focus();
			      return false;
			   }
			   if ($("#memAddStreetName").val() == "") 
			   {
			      alert("Street Name is required.");
			      $("#memAddStreetName").focus();
			      return false;
			   }
			   if ($("#memAddCity").val() == "") 
			   {
			      alert("City is required.");
			      $("#memAddCity").focus();
			      return false;
			   }
			   if ($("#memAddStateId").val() == "") 
			   {
			      alert("State selection is required.");
			      $("#memAddStateId").focus();
			      return false;
			   }
			   if ($("#memAddStateId").val() == "") 
			   {
			      alert("State selection is required.");
			      $("#memAddStateId").focus();
			      return false;
			   }
			   if ($("#memAddZipCode").val() == "") 
			   {
			      alert("Zip Code is required.");
			      $("#memAddZipCode").focus();
			      return false;
			   }
			   /*if ($("#memAddZipCode").val()!="" && $("#memAddAdditionalZipCode").val() == "") 
			   {
		      		alert("Additional Zip Code is required.");
		      		$("#memAddAdditionalZipCode").focus();
		      		return false;
			   }*/
			   
			   if(!$("#memAddMobile").is(":checked") && !$("#memAddHome").is(":checked")){
					alert("Phone Type selection is required");
					$("#memAddHome").focus();
					return false;
			  	}
				   //phone validation
			 if($("#memAddAreaCode").val()=="" || $("#memAddPrefix").val()=="" || $("#memAddLast4Digit").val()=="" ){
				   alert("Please Enter a Valid Phone Number");
				   if($("#memAddAreaCode").val()=="")
				   {
					   $("#memAddAreaCode").focus();
				   }
				   else if($("#memAddPrefix").val()=="")
				   {
				   		$("#memAddPrefix").focus();
				   }
				   else if($("#memAddLast4Digit").val()=="")
				   {
				  		  $("#memAddLast4Digit").focus();
				   }
				   return false;
				}
			   
			   /*if(!$("#memAddUnknownPhoneId").is(":checked")){
				   $("#memAddPrimaryIndId").prop("checked",true); //default if none selected.
			   }*/ /* BUG 85197 */
		  }
		  
		//juvenile Validation
			var validate = validateJuvenileReferralForm(myForm);
			if(!validate){
				return false;
			}
			else
				spinner();
				
		/*	
		$('form').attr('action','/JuvenileCasework/displayCreateJuvenile.do?submitAction=Add To List');
		$('form').submit();*/
			
			if (action != "updateJuvenile"){
				//juvenile Validation
				
					$('form').attr('action','/JuvenileCasework/displayCreateJuvenile.do?submitAction=Add To List');
					$('form').submit();
				}
			
	});
	
	/**
	 * Refresh button
	 */
	$("#refresh").click(function () {
		$('form').attr('action','/JuvenileCasework/displayCreateJuvenile.do?submitAction=Refresh Create');
		$('form').submit();
	});
	//on submit
	$("#nextBtn").click(function () {
		 var lastName = $("#lastName");
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
				  alert("Race is required.");
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
		   var dateOfBirth = $("#dateOfBirth");
		   if(typeof dateOfBirth!="undefined"){
			   if( $("#dateOfBirth").val()==""){
				   alert("Date Of Birth is Required.");
				   $("#dateOfBirth").focus();
				   return false;
			   }
		   }
		   		   
		   //checked out and date out validations bug no #89530
		   var checkedOutTo = $("#checkedOutTo");
		   var dateOut = $("#dateOut");
		   
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
		   
		   if ( "updateJuvenile" != $("#action").val() ) {
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
				 
			/*	   //Invalid social security number
				   //validation for repeatation no not allowed pending
				   var invalidSSNFlg=false;
				   var ssn = $("#SSN1").val() + $("#SSN2").val() + $("#SSN3").val();
				  				   
				   //Mother father or other designee in JIMS1 associated to a ne juvenile Master and casefile.
				   if (ssn == "666666666") {
					   invalidSSNFlg = true;
				   }
				   
				   //Individual has never had a social security number.
				   if (ssn == "777777777") {
					   	invalidSSNFlg = true;
				   }
				   
				   // applied for SSN, application approval is pending.
				   if (ssn == "888888888") {
					   	invalidSSNFlg = true;
				   }
				   
				   //SSN unknown.
				   if (ssn == "999999999") {
					   	invalidSSNFlg = true;
				   }
				   
				   if(!invalidSSNFlg){
					   alert("Invalid Social Security Number. Please enter a valid SSN.");
					   $("#SSN1").focus();
					   return false;
				   }*/ //commented for BUG 87483
				}
		   }
			//address validations.
			if($("#juvStreetNumberId").val()!="" || $("#juvStreetName").val()!="" || $("#juvCity").val()!="" 
				|| $("#juvStateId").val()!="" || $("#juvZipCode").val()!="" || $("#juvAddressTypeId").val()!=""){
		
				if ($("#juvStreetNumberId").val() == "") 
				{
				     alert("Street Number is required.");
				     $("#juvStreetNumberId").focus();
				     return false;
				}
			
				if ($("#juvStreetName").val() == "") 
				{
				     alert("Street Name is required.");
				     $("#juvStreetName").focus();
				     return false;
				}
				if ($("#juvCity").val() == "") 
				{
				    alert("City is required.");
				    $("#juvCity").focus();
				    return false;
				}
				if ($("#juvStateId").val() == "") 
				{
				     alert("State selection is required.");
				     $("#juvStateId").focus();
				     return false;
				}
				if ($("#juvStateId").val() == "") 
				{
				    alert("State selection is required.");
				    $("#juvStateId").focus();
				    return false;
				}
				if ($("#juvZipCode").val() == "") 
				{
				    alert("Zip Code is required.");
				    $("#juvZipCode").focus();
				    return false;
				}
				else if ($("juvAdditionalZipCode").val() == "") 
				{
			    		alert("Additional Zip Code is required.");
			     		$("#juvAdditionalZipCode").focus();
			     		return false;
				}
				if ($("#juvAddressTypeId").val() == "") 
				{
				    alert("Address Type is required.");
				    $("#juvAddressTypeId").focus();
				    return false;
				}
			}
			
			 
			//school validation
			if ($("#schDistId").val() != "" && $("#schoolId").val() == "") 
				{
				  alert("School Name is required when School District is chosen.");
				  $("#schoolId").focus();
				  return false;
				}
			
			
			//BUG 87799
			if (action != "updateJuvenile"){
			if ($("#sasId").val()!= "" || $("#prgmAttendingId").val() != ""  || $("#gradeLevelId").val() != "") {
				if ($("#schDistId").val() == "" && $("#schoolId").val() == "") 
					{
					 alert("School Name and School District is required when School Information is provided.");
					  $("#schDistId").focus();
					  return false;
					}
			}
			}else{
				if ($("#attendanceStatusId").val()!= "" || $("#prgmAttendingId").val() != ""  || $("#gradeLevelId").val() != "") {
					if ($("#schDistId").val() == "" && $("#schoolId").val() == "") 
						{
						 alert("School Name and School District is required when School Information is provided.");
						  $("#schDistId").focus();
						  return false;
						}
				}
			}
			

			if ($('#checkedOutTo').length && isFieldAlphaNumeric($("#checkedOutTo").val(),
																	/^[a-zA-Z0-9][a-zA-Z0-9 \.'\-]*$/) == false) {
														alert("Checked Out To must be alphanumeric.");
														$("#checkedOutTo").focus();
														return false;
													}
			
			var validate = validateJuvenileReferralForm(myForm);
			if(!validate){
				return false;
			}
			
			if ( true ) {
				spinner();
			} else {
				$(".overlay").remove();
			}
			
			//BUG 87799 ends		
			if (action != "updateJuvenile"){
			//juvenile Validation
			
			$('form').attr('action','/JuvenileCasework/displayCreateJuvenile.do?submitAction=Next');
			$('form').submit();
			} else{
				$('form').attr('action','/JuvenileCasework/processReferralBriefing.do?submitAction=Next');
				$('form').submit();
			}
			
			if(isRealDOBGreater()){
				alert('Real DOB cannot be greater than the original date of birth');
				//var dob = $("#dateOfBirth").val();
				$("#realDateOfBirth").val("");
				$("#realDateOfBirth").focus();
				return false;
			}
			
		}); //on submit
	
	
	function isRealDOBGreater(){
		var result = false;
		
		var realDateOfBirth = $("#realDateOfBirth").val();
		var originalDOB = $("#dateOfBirth").val();
		
		   if(originalDOB && realDateOfBirth){
			   
			   var rDob = Date.parse(realDateOfBirth);
			   var oDob = Date.parse(originalDOB);
			   
			   if(rDob > oDob){
				   result = true;
			   }
		   }
		   
		return result;
	}
	
});

