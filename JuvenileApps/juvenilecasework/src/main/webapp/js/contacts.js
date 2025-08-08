//functionality used by the clients tab

$(document).ready(function () {
	var originalRelationshipId	= $("#relationshipId").val();
	var originalDetVisitYes		= $("#detVisitYes").prop ("checked");
	var visitorCapRemoved		= $("#visitorCapRemoved").val();
	
	if(document.title == "JIMS2 - juvenileContactsCreate.jsp")
	{
		hideFNRequired_new();
		var pageState = $("#contactCreateStatus").val(); 
				if(pageState == 'create')
				{
					preSetName();
					$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
					"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").hide();
					
					var detYesBoolean = $("#detVisitYes").prop ("checked");
					if (detYesBoolean){
							$("#detentionVisit").show();
							$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
							"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").show();	
					}
					
					var Over21Boolean = $("#Over21Yes").prop ("checked");
					if (Over21Boolean){
						console.log("21");
						$("#detentionVisit").show();	
				}
					$("#Over21No, #detVisitNo").click(function () {
						$("#state1").val("");
						$("#dlNum").val("");
						$("#dlClass").val("");
						$("#dlExperationDate").val("");
						$("#stateIdNum").val("");
						$("#state2").val("");
						$("#passportNum").val("");
						$("#passportCountry").val("");
						$("#passportExpirationDate").val("");
						
					});
				}
		
				if(pageState == 'update')
					{
					var detYesBoolean = $("#detVisitYes").prop ("checked");
					if (detYesBoolean){
							$("#detentionVisit").show();
							$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
							"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").show();	
					}
					var detNoBoolean = $("#detVisitNo").prop( "checked" );
					if(detNoBoolean){
						$("#detentionVisit").show();
						$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
						"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").hide();
					}
				}
	}
	
	$("#Over21Yes").click(function () {
		$("#detentionVisit").show();
		$("#detVisitNo").attr ('checked', true);
		
	});
    
	$("#Over21No").click(function () {
		$("#detentionVisit").hide();
		$("#detVisitNo").prop ('checked', true);
		$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
		"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").hide();
	});
	
	$("#detVisitYes").click(function(){
		if ( $("#relationshipId").val() != ""
				&& !originalDetVisitYes ){
			if ( $("#detVisitContactsCount").val() >= 5
					&& visitorCapRemoved != "true" ){
	 			alert ("Juvenile has a total of four contacts/family members plus one Defense Attorney with detention visit privileges. " +
	 					"An edit of  detention visit privileges is required to add a new detention visit.");
	 			$("#detVisitNo").prop ('checked', true);
			}
			else if ( $("#daVisit").val() === "true" 
					&& $("#relationshipId").val() == "DA" ) {
				 alert("Only one contact with relationship equal to Defense Attorney is allowed for visitation. Please modify contacts details.");
				 $("#detVisitNo").prop ('checked', true);
			}
			else if ($("#detVisitContactsCount").val() == 4 
					&& $("#daVisit").val() === "false"
					&& $("#relationshipId").val() != "DA"
					&& visitorCapRemoved != "true"){
				 alert ("Juvenile already have a total of four contacts or family members with detention visit privilege. " +
				 		"Please go back and edit the detention visit privileges for other contacts/family members to add a new Detention Visit.");
				 $("#detVisitNo").prop ('checked', true);
			}
			else {		
				 $("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
							"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").show();
			}
		}
		
		if ( originalDetVisitYes ){
			if ($("#detVisitContactsCount").val() == 5 
					&& $("#daVisit").val() === "true"
					&& originalRelationshipId == "DA"
					&& $("#relationshipId").val() != "DA"
					&& visitorCapRemoved != "true"){
					 alert ("Juvenile has a total of four contacts/family members with detention visit privileges. " +
					 		"An edit of  detention visit privileges is required to add a new detention visit.");
					 $("#detVisitNo").prop ('checked', true);
				}
				else if ( $("#daVisit").val() === "true"
						&& originalRelationshipId != "DA"
						&& $("#relationshipId").val() == "DA" ) {
					 alert("Only one contact with relationship equal to Defense Attorney is allowed for visitation. Please modify contacts details.");
					 $("#detVisitNo").prop ('checked', true);
				}
				else {		
					 $("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
								"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").show();
				}
		}
	})
	
	$("#relationshipId").change(function () {
		if( pageState == 'create' 
			&& $("#detVisitYes").is(':checked')) {
				if ( $("#detVisitContactsCount").val() >= 5
						&& visitorCapRemoved != "true" ){
		 			alert ("Juvenile has a total of four contacts/family members plus one Defense Attorney with detention visit privileges. " +
		 					"An edit of  detention visit privileges is required to add a new detention visit.");
		 			 $("#detVisitNo").prop ('checked', true);
				}
				else if ( $("#daVisit").val() === "true" 
						&& $("#relationshipId").val() == "DA" ) {
					 alert("Only one contact with relationship equal to Defense Attorney is allowed for visitation. Please modify contacts details.");
					 $("#detVisitNo").prop ('checked', true);
				}
				else if ($("#detVisitContactsCount").val() == 4 
						&& $("#daVisit").val() === "false"
						&& $("#relationshipId").val() != "DA"
						&& visitorCapRemoved != "true"){
					 alert ("Juvenile already have a total of four contacts or family members with detention visit privilege. " +
					 		"Please go back and edit the detention visit privileges for other contacts/family members to add a new Detention Visit.");
					 $("#detVisitNo").prop ('checked', true);
				} 
				else
				$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
						"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").show();
		}
		if(pageState == 'update'){
				var Over21YesBoolean = $("#Over21Yes").prop ("checked");
				if ( $("#detVisitYes").is(':checked') 
						&& !originalDetVisitYes){
						if ( $("#detVisitContactsCount").val() >= 5  ){
				 			alert ("Juvenile has a total of four contacts/family members plus one Defense Attorney with detention visit privileges. " +
				 					"An edit of  detention visit privileges is required to add a new detention visit.");
				 			$("#detVisitNo").prop ('checked', true);
						}
						else if ( $("#daVisit").val() === "true" 
								&& $("#relationshipId").val() == "DA" ) {
							 alert("Only one contact with relationship equal to Defense Attorney is allowed for visitation. Please modify contacts details.");
							 $("#detVisitNo").prop ('checked', true);
						}
						else if ($("#detVisitContactsCount").val() == 4 
								&& $("#daVisit").val() === "false"
								&& $("#relationshipId").val() != "DA" ){
							 alert ("Juvenile already have a total of four contacts or family members with detention visit privilege. " +
							 		"Please go back and edit the detention visit privileges for other contacts/family members to add a new Detention Visit.");
							 $("#detVisitNo").prop ('checked', true);
						}
						else {		
							 $("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
										"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").show();
						}
				}
				else if ( $("#detVisitYes").is(':checked') 
							&& originalDetVisitYes ){
					
						if ($("#detVisitContactsCount").val() == 5 
							&& $("#daVisit").val() === "true"
							&& originalRelationshipId == "DA"
							&& $("#relationshipId").val() != "DA" ){
							 alert ("Juvenile has a total of four contacts/family members plus one Defense Attorney with detention visit privileges. " +
							 		"An edit of  detention visit privileges is required to add a new detention visit.");
							 $("#detVisitNo").prop ('checked', true);
						}
						else if ( $("#daVisit").val() === "true"
								&& originalRelationshipId != "DA"
								&& $("#relationshipId").val() == "DA" ) {
							 alert("Only one contact with relationship equal to Defense Attorney is allowed for visitation. Please modify contacts details.");
							 $("#detVisitNo").prop ('checked', true);
						}
						else {		
							 $("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
										"#driverLicenseIDInfo5, #driverLicenseIDInfo6 ").show();
						}
				}
						
				
		}	
	});
	
	$("#detVisitNo").click(function () {
		if(pageState == 'create'){
		$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
				"#driverLicenseIDInfo5, #driverLicenseIDInfo6 " ).hide();
		}
		if(pageState == 'update'){
			if($(this).val()=='false'&& $(this).attr('checked')!='checked'){
				var detVisitContactsCount = parseInt($("#detVisitContactsCount").val());
				detVisitContactsCount--;
				$("#detVisitContactsCount").val(""+detVisitContactsCount);
			}
			$("#driverLicenseIDInfo,#driverLicenseIDInfo2,#driverLicenseIDInfo3, #driverLicenseIDInfo4, " +
					"#driverLicenseIDInfo5, #driverLicenseIDInfo6 " ).hide();
		}
	});
	


	if(typeof $("#dlExperationDate")!= "undefined"){
		datePickerSingle( $("#dlExperationDate"),"Expiration Date",false);
	}
	if(typeof $("#passportExpirationDate")!= "undefined"){
		datePickerSingle( $("#passportExpirationDate"),"Expiration Date",false);
	}
		
	$("#relationshipId").on("change", function(){
		hideFNRequired_new();
	});	
	
	$("#contactUpdateNext").on("click", function() {
		
		var addressValid = validateAddressOnly();
		
		if (!addressValid) 
		{
			return false;
		}
		
		if(!validatePhones())
			{
				return false;
			}
		
		var theForm = document.juvenileContactForm;
		if( validateJuvenileContactForm(this.form))
		{
			return (validateAddressFieldsUpdate() && validateCreateFields());
		}
		else
		{
			return false;
		}
		
	});

	
	//$("#physCharCreateNext").on('click',function(){
	//	return validateJuvenilePhysicalCharacteristicsForm(this.form);
	//});
	
	//formerly button push with function validateAddressFields()
	$("#contactCreateNext").on("click", function() {
		addressValid = validateAddressOnly();
		if (!addressValid) 
		{
			return false;
		}
		var theForm = document.juvenileContactForm;
		if( validateJuvenileContactForm(this.form))
		{
			var theConElem = $("#relationshipId");
		    theVal = $(theConElem).val();
		    if(theVal != 'PL')
		    {
		    	var theFN = $("[name='contactName.firstName']");
		     	if($(theFN).val() == '')
		     	{
		     		alert("JIMS: First name is required");
		     		return false;
		     	}
		     }
		    /*return validateAddressOnly();*/
		    return (validateTheNameFields(theForm) && checkPassportExpirationDate(theForm) && validateCreateFields());
		}
		else
		{
			return false;
		}
	});
	
	//used to be setNameFields() and preSetName()
	$("#addressValidate").on("click", function(){
		return (setNameFields() && changeAddrFormActionURL('juvenileContactForm','/JuvenileCasework/validateJuvContactAddress.do','', '/jsp/juvTabContacts/juvenileContactCreate.jsp', true));
	});
	
	function setNameFields()
	{
		var pageState = $("#contactCreateStatus").val();
		if(pageState == 'create')
		{
			var theConElem = $("#relationshipId");
			var theHiddenElem = $("#myHiddenName");			
			// theVal = theConElem.value;
			if($(theConElem).val() == 'PL')
			{
				var theFacilityNameElem = $("#txtFacilityName");
				$(theHiddenElem).val($(theFacilityNameElem).val());
			}
			else
			{
			   	var theLastNameElem = $("#txtLastName");			  
			   	$(theHiddenElem).val($(theLastNameElem).val());
			}
		}
		return true;
	}
	
	function checkPassportExpirationDate(theForm)
	{
	    
	 	var passportExpirationDate = $("[name='passportExpirationDate']");
	 	passportExpirationDate.val(Trim($("[name='passportExpirationDate']").val()));
	 	if($(passportExpirationDate).val() != "")
	 	 	{
		    var noErrors = validateCustomStrutsBasedJS(theForm);
			if (noErrors == true)
		 	{ 
		    	var currDate=new Date();
				var curDate = new Date();
				var currYYYY = curDate.getFullYear().toString();
				var currMM = curDate.getMonth() + 1;
				var currDD = curDate.getDate();
				if (currMM < 10){
					currMM = "0" + currMM;
				}
				if (currDD < 10){
					currDD = "0" + currDD;
				}      		
			      		
				var inDate = passportExpirationDate.val();
				var mm = inDate.substring(0,2);
				var dd = inDate.substring(3,5);
				var yyyy = inDate.substring(6,10);
				var century = inDate.substring(6,8);
				var inDateStr = inDate.substring(6,10) + inDate.substring(0,2) + inDate.substring(3,5); 
				var currDateStr = currYYYY + currMM.toString() + currDD.toString();
				if (inDateStr < currDateStr)
				{
		    		alert("Passport expiration date is past date. Please confirm.");
		    		return;
				} 	
		    }
		}	
		
		return true;		
	}
	
	function preSetName()
	{
		var pageState = $("#contactCreateStatus").val();
		if(pageState == 'create')
		{
			var theConElem = $("#relationshipId");
			var theHiddenElem = $("#myHiddenName");
			if($(theConElem).val() == 'PL')
			{
				var theFacilityNameElem = $("#txtFacilityName");
				$(theHiddenElem).val($(theFacilityNameElem).val());
			}
			else
			{
			 
				var theLastNameElem = $("#txtLastName");
				$(theLastNameElem).val(	$(theHiddenElem).val());
			}
		}

	   return true;
	}

	//used to be hideFNRequired()
	function hideFNRequired_new()
	{
		var theConElem = $("#relationshipId").val();
		
		if(theConElem != "PL")
		{
			$("#fnreq").show();
			$("#fnreqPlacement").hide();
		}
		else
		{
			$("#fnreq").hide();
			$("#fnreqPlacement").show();
		}
	 }
	
	function validatePhones()
	{
		var cellPhoneAreaCode = $("[name='cellPhone.areaCode']");
	   	var cellPhonePrefix = $("[name='cellPhone.prefix']");
	   	var cellPhone4Digit = $("[name='cellPhone.4Digit']");
	   	var workPhoneAreaCode = $("[name='workPhone.areaCode']");
	   	var workPhonePrefix = $("[name='workPhone.prefix']");
	   	var workPhone4Digit = $("[name='workPhone.4Digit']");
	   	var ext = $("[name='workExtension']");
	   	var faxAreaCode = $("[name='fax.areaCode']");
	   	var faxPrefix = $("[name='fax.prefix']");
	   	var fax4Digit = $("[name='fax.4Digit']");

	   	if ($(cellPhoneAreaCode).val() > "" && $(cellPhonePrefix).val() == "") {
	      alert("Cell Phone Prefix must be entered if Cell Phone Area Code is entered.");
	      cellPhonePrefix.focus();
	      return false;
	   }
	   if ($(cellPhonePrefix).val() > "" && $(cellPhone4Digit).val() == "") {
	      alert("Cell Phone Last 4 Digits must be entered if Cell Phone Prefix is entered.");
	      cellPhone4Digit.focus();
	      return false;
	   }
	   if ($(cellPhone4Digit).val() > "" && $(cellPhonePrefix).val() == "") {
	      alert("Cell Phone Prefix must be entered if Cell Phone Last 4 Digits is entered.");
	      cellPhonePrefix.focus();
	      return false;
	   }
	   if ($(cellPhone4Digit).val() > "" && $(cellPhoneAreaCode).val() == "") {
	      alert("Cell Phone Area Code must be entered if Cell Phone Last 4 Digits is entered.");
	      cellPhoneAreaCode.focus();
	      return false;
	      }
	   if ($(workPhoneAreaCode).val() > "" && $(workPhonePrefix).val() == "") {
		  alert("Work Phone Prefix must be entered if Work Phone Area Code is entered.");
		  workPhonePrefix.focus();
		  return false;
	   }
	   if ($(workPhonePrefix).val() > "" && $(workPhone4Digit).val() == "") {
	      alert("Work Phone Last 4 Digits must be entered if Work Phone Prefix is entered.");
	      workPhone4Digit.focus();
	      return false;
	   }
	   if ($(workPhonePrefix).val() == "" && $(workPhone4Digit).val() > "") {
	      alert("Work Phone Prefix must be entered if Work Phone Last 4 Digits is entered.");
	      workPhonePrefix.focus();
	      return false;
	     }
	   if ($(workPhoneAreaCode).val() == "" && $(workPhone4Digit).val() > "") {
	      alert("Work Phone Area Code must be entered if Work Phone Last 4 Digits is entered.");
	      workPhoneAreaCode.focus();
	      return false;
	     } 
	   if ($(ext).val() > "" && $(workPhone4Digit).val() == "") {
	      alert("Work Phone Number must be entered if Work Phone Extension is entered.");
	      workPhoneAreaCode.focus();
	      return false;
	     }
	   if ($(faxAreaCode).val() > "" && $(faxPrefix).val() == "") {
	      alert("Fax Prefix must be entered if Fax Area Code is entered.");
	      faxPrefix.focus();
	      return false;
	   }
	   if ($(faxPrefix).val() > "" && $(fax4Digit).val() == "") {
	      alert("Fax Last 4 Digits must be entered if Fax Prefix is entered.");
	      fax4Digit.focus();
	      return false;
	   }
	   if ($(fax4Digit).val() > "" && $(faxPrefix).val() == "") {
	      alert("Fax Prefix must be entered if Fax Last 4 Digits is entered.");
	      faxPrefix.focus();
	      return false;
	   }
	   if ($(fax4Digit).val() > "" && $(faxAreaCode).val() == "") {
	      alert("Fax Area Code must be entered if Fax Last 4 Digits is entered.");
	      faxAreaCode.focus();
	      return false;
	   }
	   return true;	
	}
	
	function validateAddressFieldsUpdate()
	{
		var addressValid = validateAddressOnly();
		
		if (!addressValid) 
		{
			return false;
		}
		return  validateJuvenileContactForm(this.form);
	}
	
	
	
	function validateCreateFields(){
		
		//Begin Driver License Validation
		   if ($("[name='driverLicenseNum']").val() > "")
		   {
		       if ($("[name='driverLicenseStateId']").val() == "") 
			   {
		          alert("State selection is required when Driver License Number entered.");
		          $("[name='driverLicenseStateId']").focus();
		          return false;
		       }
		       if ($("[name='driverLicenseExpirationDate']").val() == "") 
			   {
		          alert("Expiration Date is required when Driver License Number entered.");
		          $("[name='driverLicenseExpirationDate']").focus();
		          return false;
		       }
		       if ($("[name='driverLicenseClassId']").val() == "") 
			   {
		          alert("Class selection is required when Driver License Number entered.");
		          $("[name='driverLicenseClassId']").focus();
		          return false;
		       }
		   }
		   if ($("[name='driverLicenseStateId']").val() > "")
		   {   
			   if ($("[name='driverLicenseNum']").val() == "") 
			   {
		          alert("Driver License number is required when State is selected.");
		          $("[name='driverLicenseNum']").focus();
		          return false;
		       }
			   if ($("[name='driverLicenseExpirationDate']").val() == "") 
			   {
		          alert("Expiration Date is required when State is selected.");
		          $("[name='driverLicenseExpirationDate']").focus();
		          return false;
		       }
		       if ($("[name='driverLicenseClassId']").val() == "") 
			   {
		          alert("Class selection is required when State is selected.");
		          $("[name='driverLicenseClassId']").focus();
		          return false;
		       }
		   }
		   if ($("[name='driverLicenseExpirationDate']").val() > "")
		   {
			   if ($("[name='driverLicenseNum']").val() == "") 
			   {
		          alert("Driver License number is required when Expiration Date is entered.");
		          $("[name='driverLicenseNum']").focus();
		          return false;
		       }
			   if ($("[name='driverLicenseStateId']").val() == "") 
			   {
		          alert("State selection is required when Expiration Date is entered.");
		          $("[name='driverLicenseStateId']").focus();
		          return false;
		       }
			   if ($("[name='driverLicenseClassId']").val() == "") 
			   {
		          alert("Class selection is required when Expiration Date is entered.");
		          $("[name='driverLicenseClassId']").focus();
		          return false;
		       }
		   }
		   if ($("[name='driverLicenseClassId']").val() > "")
		   {
			   if ($("[name='driverLicenseNum']").val() == "")
		       {
			      alert("Driver License number is required when Class is selected.");
			      $("[name='driverLicenseNum']").focus();
		          return false; 
		       }
		   	   if ($("[name='driverLicenseStateId").val() == "") 
		   	   {
		   		   alert("State selection is required when Class is selected.");
		   		   $("[name='driverLicenseStateId']").focus();
		   		   return false;
		   	   }
		       if ($("[name='driverLicenseExpirationDate']").val() == "") 
			   {
		           alert("Expiration Date is required when Class is selected.");
		           $("[name='driverLicenseExpirationDate']").focus();
		           return false;
		       }
		   }	   
			   
		//End Driver License Validation   
		   
		//Begin State ID Validation
		   if ($("[name='stateIssuedIdNum']").val() > "")
		   {   
			   if ($("[name='issuedByStateId']").val() == "") 
			   {
		          alert("State is required when State Issued ID Card # is entered.");
		          $("[name='issuedByStateId']").focus();
		          return false;
		       }
			   
		   }
		   
		   
		   if ($("[name='issuedByStateId']").val() > "")
		   {   
			   if ($("[name='stateIssuedIdNum']").val() == "") 
			   {
		          alert("State Issued ID Card # is required when State is selected.");
		          $("[name='stateIssuedIdNum']").focus();
		          return false;
		       }
			   
		   }		   
		//End State ID Validation
		   
		//Begin Passport Detail Validation
		   if ( $("[name='passportNum']").val() > "")
		   {
		       if ($("[name='countryOfIssuanceId']").val() == "") 
			   {
		          alert("Country of Issuance selection is required when Passport Number entered.");
		          $("[name='countryOfIssuanceId']").focus();
		          return false;
		       }
		       if ($("[name='passportExpirationDate']").val() == "") 
			   {
		          alert(" Passport Expiration Date is required when Passport Number entered.");
		          $("[name='passportExpirationDate']").focus();
		          return false;
		       }
		   }
		   if ($("[name='countryOfIssuanceId']").val() > "")
		   {   
			   if ($("[name='passportNum']").val() == "") 
			   {
		          alert("Passport number is required when Country Of Issuance is selected.");
		          $("[name='passportNum']").focus();
		          return false;
		       }
			   if ($("[name='passportExpirationDate']").val() == "") 
			   {
		          alert(" Passport Expiration Date is required when Country Of Issuance is selected.");
		          $("[name='passportExpirationDate']").focus();
		          return false;
		       }
		   }
		   if ($("[name='passportExpirationDate']").val() > "")
		   {
			   if ($("[name='passportNum']").val() == "") 
			   {
		          alert("Passport number is required when  Passport Expiration Date is entered.");
		          $("[name='passportNum']").focus();
		          return false;
		       }
			   if ($("[name='countryOfIssuanceId']").val() == "") 
			   {
		          alert("Country Of Issuance selection is required when  Passport Expiration Date is entered.");
		          $("[name='countryOfIssuanceId']").focus();
		          return false;
		       }
			  
		   }
		   	   
			  //end of Passport Detail validation
			return true;  
		}
	
	function validateAddressOnly() {
		
		var state = "";
		var streetName = "";
		var strNum = "";
		var city = "";
		var zipCode = "";
		var zipCode2 = "";

		var msg = "";
		var numbers = /^[ 0-9 ]*$/;
		var streetNumRegex = /^[ a-zA-Z0-9 \.'\-  ]+$/;

		var zipre = /^[ 0-9 ]{5}$/;
		var addzipre = /^[ 0-9 ]{4}$/;

		var valid = true;
		
		if(	($("[name='streetNum']").val() > "") || 
			($("[name='streetName']").val() > "") || 
			($("[name='city']").val() > "") ||
			($("[name='stateId']").val() > "") ||
			($("[name='zipCode']").val() > "")
		  ) 
		{
			strNum = Trim( $("[name='streetNum']").val() );
			streetName = Trim( $("[name='streetName']").val() );
			city = Trim( $("[name='city']").val() );
			var statefld = $("[name='stateId']");
			if (statefld.length > 0)//not actually used
			{
				state = Trim( $("[name='stateId']").val().toUpperCase( ) );
			} else {
				state = Trim( document.forms[ 0 ].state.value.toUpperCase( ) );//what goes here
			}
			zipCode = Trim( $("[name='zipCode']").val() );

			if( strNum == null  ||  strNum == "" )
			{
				if( msg == "" )
				{
					$("[name='streetNum']").focus( );
				}
				msg += "Street Number is required to validate address.\n";
			}
			else if( (streetNumRegex.test( strNum ) == false) )
			{
				$("[name='streetNum']").focus( );
				msg += "Street Number must be a non-zero alphanumeric to validate.\n";
			}
//			else if( isNaN( strNum.charAt( 0 ) ) )
//			{
//				streetNumField.focus( );
//				msg += "The first character in Street Number must be numeric to validate.\n";
//			}

			if( zipCode == null  ||  zipCode == "" )
			{
				if( msg == "" )
				{
					$("[name='zipCode']").focus( );
				}
				msg += "Zip Code is required.\n";
			}
			else if( !zipre.exec( zipCode ) )
			{
				if( msg == "" )
				{
					$("[name='zipCode']").focus( );
				}
				msg += "Zip Code must be a 5 digit number.\n";
			}

			if( streetName == null || streetName == "" )
			{
				if( msg == "" )
				{
					$("[name='streetName']").focus( );
				}
				msg += "Street Name is required to validate address.\n";
			}

			if( city == null || city == "" )
			{
				if( msg == "" )
				{
					$("[name='city']").focus( );
				}
				msg += "City is required to validate address.\n";
			}
			
/*			if( state == null  ||  state != "TX" )
			{
				if( msg == "" )
				{
					document.forms[ 0 ].stateId.focus( );
				}
				msg += "State must be 'TEXAS' to validate address.\n";
			}*/

			if( msg != "" )
			{
				alert( msg );
				valid = false;
			}
			
		}
		return valid;
	}
	
	function validateTheNameFields(theForm)
	{
	   clearAllValArrays();

	   var theConElem = $("#relationshipId");
	   var theHiddenElem = $("#myHiddenName");

	//   theVal = theConElem.value;
	   if($(theConElem).val() == 'PL')
		 {
	   	var theFacilityNameElem = $("#txtFacilityName");

	   	$(theHiddenElem).val($(theFacilityNameElem).val());
	   	customValRequired("txtFacilityName", "Facility Name is required", "");
			addAlphaNumericnSpacewSymbolsValidation("txtFacilityName", "Facility Name must be alphanumeric.");
	   	customValMinLength("txtFacilityName", "Facility Name must be at least 2 characters.",2);

	   	var theFirstNameElem = $("[name='contactName.firstName']");
	   	var theMiddleNameElem = $("[name='contactName.middleName']");

	   	$(theFirstNameElem).val('');
	   	$(theMiddleNameElem).val('');
	   }
	   else
		 {
	   	var theLastNameElem = $("#txtLastName");

	   	$(theHiddenElem).val($(theLastNameElem).val());
	   	customValRequired("txtLastName", "Last Name is required", "");
		addAlphaNumericNoFirstSpacewSymbolsValidation("txtLastName", "Last Name must be alphanumeric.");
	   	customValMinLength("txtLastName", "Last Name must be at least 2 characters.",2);
		 }
	   	var cellPhoneAreaCode = $("[name = 'cellPhone.areaCode']");
	   	var cellPhonePrefix = $("[name = 'cellPhone.prefix']");
	   	var cellPhone4Digit = $("[name = 'cellPhone.4Digit']");
	   	var workPhoneAreaCode = $("[name = 'workPhone.areaCode']");
	   	var workPhonePrefix = $("[name = 'workPhone.prefix']");
	   	var workPhone4Digit = $("[name = 'workPhone.4Digit']");
	   	var ext = $("[name = 'workExtension']");
	   	var faxAreaCode = $("[name = 'fax.areaCode']");
	   	var faxPrefix = $("[name = 'fax.prefix']");
	   	var fax4Digit = $("[name = 'fax.4Digit']");

	   	if ($(cellPhoneAreaCode).val() > "" && $(cellPhonePrefix).val() == "") {
		      alert("Cell Phone Prefix must be entered if Cell Phone Area Code is entered.");
		      cellPhonePrefix.focus();
		      return false;
	   }
	   if ($(cellPhonePrefix).val() > "" && $(cellPhone4Digit).val() == "") {
	      alert("Cell Phone Last 4 Digits must be entered if Cell Phone Prefix is entered.");
	      cellPhone4Digit.focus();
	      return false;
	   }
	   if ($(cellPhone4Digit).val() > "" && $(cellPhonePrefix).val() == "") {
	      alert("Cell Phone Prefix must be entered if Cell Phone Last 4 Digits is entered.");
	      cellPhonePrefix.focus();
	      return false;
	   }
	   if ($(cellPhone4Digit).val() > "" && $(cellPhoneAreaCode).val() == "") {
	      alert("Cell Phone Area Code must be entered if Cell Phone Last 4 Digits is entered.");
	      cellPhoneAreaCode.focus();
	      return false;
	   }
	   if ($(workPhoneAreaCode).val() > "" && $(workPhonePrefix).val() == "") {
		  alert("Work Phone Prefix must be entered if Work Phone Area Code is entered.");
		  workPhonePrefix.focus();
		  return false;
	   }
	   if ($(workPhonePrefix).val() > "" && $(workPhone4Digit).val() == "") {
	      alert("Work Phone Last 4 Digits must be entered if Work Phone Prefix is entered.");
	      workPhone4Digit.focus();
	      return false;
	   }
	   if ($(workPhonePrefix).val() == "" && $(workPhone4Digit).val() > "") {
	      alert("Work Phone Prefix must be entered if Work Phone Last 4 Digits is entered.");
	      workPhonePrefix.focus();
	      return false;
	   }
	   if ($(workPhoneAreaCode).val() == "" && $(workPhone4Digit).val() > "") {
	      alert("Work Phone Area Code must be entered if Work Phone Last 4 Digits is entered.");
	      workPhoneAreaCode.focus();
	      return false;
	   } 
	   if ($(ext).val() > "" && $(workPhone4Digit).val() == "") {
	      alert("Work Phone Number must be entered if Work Phone Extension is entered.");
	      workPhoneAreaCode.focus();
	      return false;
	   }
	   if ($(faxAreaCode).val() > "" && $(faxPrefix).val() == "") {
	      alert("Fax Prefix must be entered if Fax Area Code is entered.");
	      faxPrefix.focus();
	      return false;
	   }
	   if ($(faxPrefix).val() > "" && $(fax4Digit).val() == "") {
	      alert("Fax Last 4 Digits must be entered if Fax Prefix is entered.");
	      fax4Digit.focus();
	      return false;
	   }
	   if ($(fax4Digit).val() > "" && $(faxPrefix).val() == "") {
	      alert("Fax Prefix must be entered if Fax Last 4 Digits is entered.");
	      faxPrefix.focus();
	      return false;
	   }
	   if ($(fax4Digit).val() > "" && $(faxAreaCode).val() == "") {
	      alert("Fax Area Code must be entered if Fax Last 4 Digits is entered.");
	      faxAreaCode.focus();
	      return false;
	   }
	 
	   return validateCustomStrutsBasedJS(theForm);
	}
	
	$("#contactCancel").on('click',function(){
		changeFormActionURL($(this).data("href") , true);
	});	
	
	 
});

function textCounter(field, maxlen) {
	if (field.value.length > maxlen + 1) {
		alert("Your input has been truncated to " +maxlen +" characters.");
	}

	if (field.value.length > maxlen) {
		field.value = field.value.substring(0, maxlen);
	}
} 