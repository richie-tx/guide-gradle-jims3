$(document).ready(function () {
	var myForm = document.forms[0];

	//juvenileDOB DATE CALENDAR.
	if(typeof $("#juvenileDOB") != "undefined"){
		datePickerSingle($("#juvenileDOB"),"Date of Birth",true);
	}
	
	//juvenilereferralDOB DATE CALENDAR. #52137
	if(typeof $("#dateOfBirth") != "undefined"){
		$("#dateOfBirth").on("mousedown",function(){
			datePickerSingle($("#dateOfBirth"),"Date of Birth",true);
		}); 
	}
	
	var referral = document.URL.split('?')[1];
	
	var searchType = $("#searchType");
	if(typeof searchType !="undefined"){
		if(referral == null || $("#referralAction").val()=="referralSearchResults"){
			if($("#searchType").val() == "JNAM"){
				$("#searchType").val("JNAM");
			}
		}else{
			$("#searchType").val("JNUM");
		}
		evalSearch(myForm);
	}
	
	//on change of search type
	$("#searchType").on("change",function(){
		evalSearch(myForm)
	}); //on change of search type.
		
	//on submit
	$("#submitBtn").click(function () {
		var searchType = $("#searchType");
		if(typeof searchType !="undefined"){
			var searchType = $("#searchType").val();
			   if(searchType=="JNAM"){
				   var lastName = $("#lastName");
				   if(typeof lastName!="undefined"){
					   if( $("#lastName").val()==""){
						  alert("Juvenile Last Name is required for the entered search criteria.");
						   $("#lastName").focus();
						   return false;
					   }
				   }
				   
				   
			   }else if(searchType=="JNUM"){
				   var juvenileNum = $("#juvenileNum");
				   if(typeof juvenileNum!="undefined"){
					   
					   if($("#juvenileNum").val()==""){
						   alert("Juvenile Number is required.");
						   $("#juvenileNum").focus();
						   return false;
					   }
				   }
			   }
			 //task 171828
			   else if(searchType=="JDALG"){
				   var dalog = $("#dalogNum");
				   if(typeof dalog!="undefined"){
					   
					   if($("#dalogNum").val()==""){
						   alert("DA Log Number is required.");
						   $("#dalogNum").focus();
						   return false;
					   }
				   }
			   }
			   else if(searchType=="JDOB"){
				   var dateOfBirth = $("#dateOfBirth");
				   if(typeof dateOfBirth!="undefined"){
					   if($("#dateOfBirth").val() ==""){
						   alert("Juvenile Date of Birth is required.");
						   $("#dateOfBirth").focus();
						   return false;	
					   }
				   }
			   }else  if(searchType=="JSSN"){
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
						   if($("#SSN1").val() == "" && $("#SSN2").val() == "" && $("#SSN3").val() == "") {
							      alert("Social Security Number is required.Please enter a valid SSN.");
							      $("#SSN1").focus();
							      return false;
							}
						    else
							{
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
							   
							   //Invalid social security number
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
							   
							   if(invalidSSNFlg){
								   alert("Invalid Social Security Number. Please enter a valid SSN.");
								   $("#SSN1").focus();
								   return false;
							   }
							}
				   		}
			   		}
				}  

								
		}); //on submit
	
	// on click of create juvenile button.
	$("#createJuvenileBtn").click(function () {
		$('form').attr('action','/JuvenileCasework/displayCreateJuvenile.do?submitAction=Create Juvenile');
		$('form').submit();
	});
	
	// on click of SUBMIT button.
	$("#refBriefgBtn").click(function () {
		var juvNum = $("input[name='juvenileProfilesRec']:checked").val();
		if(	juvNum == null  ){
		alert("Please select a juvenile by clicking on the radio button." );
		return false;
		}
		$('form').attr('action','/JuvenileCasework/displayJuvenileBriefingDetails.do?submitAction=referralLink&juvenileNum='+juvNum);
		$('form').submit();
	});
	
	
	//eval search 
	//CHANGE HERE
	function evalSearch(myForm){
		var searchType = $("#searchType").val();
		
		$("#juvenileName").hide();
		$("#juvNumber").hide();
		$("#juvDOB").hide();
		$("#juvSSN").hide();
		$("#dalogNumber").hide();
		
		
		if(searchType=="JNAM"){
			$("#juvNumber").hide();
			$("#juvenileName").show();
			$("#juvDOB").hide();
			$("#juvSSN").hide();	
			$("#dalogNumber").hide();
			$("#lastName").focus();
			if(typeof $("#referralAction")!="undefined" && $("#referralAction").val()!="referral")
	   		{
				$("#searchResults").show();
				$("#referralAction").val("referral");
	    	}
			else
	    	{
	    		$("#searchResults").hide();
	    	}
	   		clearJuvNumFields();
	   		clearJuvDOBFields(); 
	   		
		}else if(searchType=="JNUM"){
			$("#juvNumber").show();
			$("#juvenileName").hide();
			$("#juvDOB").hide();
			$("#juvSSN").hide();
			$("#dalogNumber").hide();
	    	$("#juvenileNum").focus();
	    	$("#referralAction").val()
			if(typeof $("#referralAction")!="undefined" && $("#referralAction").val()!="referral")
	   		{
				$("#searchResults").show();
				$("#referralAction").val("referral");
	    	}
			else
	    	{
	    		$("#searchResults").hide();
	    	}
	    	clearJuvNameFields();
	    	clearJuvDOBFields();
		}
		else if (searchType=="JDOB")
		{
			$("#juvDOB").show();
			$("#juvenileName").hide();
			$("#juvNumber").hide();
			$("#juvSSN").hide();
			$("#dalogNumber").hide();
	    	//$("#dateOfBirth").focus(); #52137
	    	if(typeof $("#referralAction")!="undefined" && $("#referralAction").val()!="referral")
	   		{
				$("#searchResults").show();
				$("#referralAction").val("referral");
	    	}
	    	else
	    	{
	    		$("#searchResults").hide();
	    	}
	    	clearJuvNameFields();
	    	clearJuvNumFields();
		}
		else if (searchType=="JSSN")
		{
			$("#juvSSN").show();
			$("#juvDOB").hide();
			$("#juvNumber").hide();
			$("#juvenileName").hide();
			$("#dalogNumber").hide();
			$("#SSN1").focus();
			if(typeof $("#referralAction")!="undefined" && $("#referralAction").val()!="referral")
	   		{
				$("#searchResults").show();
				$("#referralAction").val("referral");
	    	}
		}
		//task 171828
		else if (searchType=="JDALG")
		{
				$("#juvSSN").hide();
				$("#juvDOB").hide();
				$("#juvNumber").hide();
				$("#juvenileName").hide();
				$("#dalogNumber").show();
				$("#dalogNum").focus();
				if(typeof $("#referralAction")!="undefined" && $("#referralAction").val()!="referral")
		   		{
					$("#searchResults").show();
					$("#referralAction").val("referral");
		    	}
			else
	    	{
	    		$("#searchResults").hide();
	    	}
	    	clearJuvNameFields();
	    	clearJuvNumFields();
		}
	}
	
	/**
	 * clearJuvNameFields
	 */
	function clearJuvNameFields()
	{
	  $("#lastName").val("");
	  $("#firstName").val("");
	  $("#middleName").val("");
	}
	
	/**
	 * clearJuvNumFields
	 */
	function clearJuvNumFields()
	{
	  if(typeof $("#juvenileNum")!="undefined"){
		  $("#juvenileNum").val("");
	  }
	}
	
	/**
	 * clearJuvDOBFields
	 */
	function clearJuvDOBFields()
	{
 	   if(typeof $("#dateOfBirth")!="undefined"){
		 $("#dateOfBirth").val("");
		}
	}
});