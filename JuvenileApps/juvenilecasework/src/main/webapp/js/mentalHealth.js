//functionality used by Mental Health

$(document).ready(function () {
	
	//used with casework.js textLimit function
	$("#dsmmedicalDiagnosis,#dsmaxisIVComments,#atRecrecommendations").on('keyup mouseout',function(){
		textLimit($(this),255);
		return false;
	});
	$("[name='subsAssessmentComments'],[name='hospRec.hospitalizationReason']").on('keyup mouseout',function(){
		textLimit($(this),250);
		return false;
	});
	
	$("#othReasonNotDone").on('keyup mouseout',function(){
		textLimit($(this),250);
		return false;
	});
	//new Calendar drop down
	if(typeof $("#soiDate1")!= "undefined"){
		datePickerSingle( $("#soiDate1"),"Date of Scheduled Office Interview",false);
	}
	if(typeof $("#soiDate2")!= "undefined"){
		datePickerSingle( $("#soiDate2"),"Date of Scheduled Office Interview",false);
	}
	//used for multiple test create pages
	if(typeof $("#recTestDate") != "undefined")
	{		
		datePickerSingle( $("#recTestDate"),"Test Date",true);
	}
	if(typeof $("#admissionDate") != "undefined" && typeof $("#releaseDate") != "undefined")
	{		
		datePickerRange( $("#admissionDate"),$("#releaseDate"),"Admission Date","Release Date");		
	}
	
	//script from maysi.jsp - MAYSI
	
	if(document.title == 'JIMS2 - maysi.jsp')
	{
		setReasonNotDoneMaysi();
	}
	
	function setReasonNotDoneMaysi()
	{
		var pageState = $("#maysiPageStatus").val();
		if(pageState == 'create')
		{
			var myElem = $("[name='administer']");  //radio Yes[0] or No[1]
//			alert(myElem + "  " + myElem.length);
	  		$("#reasonNotDoneRow").hide();
	  		$("#schdOffIntDateRow1").hide();
			if(myElem[1].checked)
			{
				$("#reasonNotDoneRow").show();
		  		fld = $("#notDone");
		  		if ($(fld).val() == "OIP")
		  		{
		  			$("#schdOffIntDateRow1").show();
		  		}
		  	}
		}
		if(pageState == "update")
		{
			fld = $("#notDoneU");
			if ($(fld).val() == "OIP")
			{
				$("#schdOffIntDateRow2").show();
			}
		}
	}
	
	$("[name='administer']").on("change", function(){
		if($(this).val() == "no")
		{
			$("#reasonNotDoneRow").show();
			$("#notDone").val("");
			$("#othReasonNotDone").val("");
		}
		else
		{
			$("#otherReasonNotDone").hide();
			$("#reasonNotDoneRow").hide();
		}
	});
	
	$("#maysiSubmitButton").on("click", function (){
		var msg = "" ;
		var fld = $("[name='administer']");
		var othReason = $("#othReasonNotDone");
		if (!fld[0].checked && !fld[1].checked)
		{
			msg = "Have you or are you going to administer the MAYSI today? is required\n" ;
		}
		if ( fld[1].checked){
			fld = $("#notDone");
			if ($(fld).val() == "")
			{
				msg = "Identify reason MAYSI was not administered is required.\n" ;
			} 
			if ($(fld).val() == "OIP")
			{	
				fld2 = $("#soiDate1");
				if (fld2.value == "")
				{
					if (msg == "")
					{
						fld2.focus();	
					}
					msg += "Date of Scheduled Office Interview is required.\n";
				} else {
					result = maysivalidateDate($(fld2).val(),"Date of Scheduled Office Interview");
					if (result != "")
					{
						if (msg == "")
						{
							fld2.focus();	
						}
						msg += result;	
					}	
				}
			}
			if ($(fld).val() == "OTH")
				{			
					fld3 = $("#othReasonNotDone");
					if ($(fld3).val() == "")
					{
						msg += "Detailed reason for other required.\n";
					}	
				}
		}
		fld = $("#refNum");
		if ($(fld).val() == "")
		{
			msg += "Referral Number is required\n";
			
		}	
		fld = $("#locUnit");
		if ($(fld).val() == "")
		{
			msg += "Location Unit is required\n";
			
		}	
		fld = $("#lenStay");
		if ($(fld).val() == "")
		{
			msg += "How long has youth been here? is required\n";
			
		}	
		fld = $("#typeFacility");
		if ($(fld).val() == "")
		{
			msg += "Type of Facility is required\n";
			
		}	
		fld = $("[name='hasPreviousMAYSI']");
		if( !fld[0].checked && !fld[1].checked )
		{
			msg += "Has the youth taken a MAYSI before? is required" ;
		}
	  	if( msg != "" )
		{
			alert( msg ) ;
			return( false ) ;
		}
		
		return( true ) ;
	});
	
	$("#maysiUpdateButton").on("click", function () {
		msg = "";
		var fld = $("#notDoneU");
		if ($(fld).val() == "")
		{
			msg = "Identify reason MAYSI was not administered is required.\n";
			fld.focus();

		}
		if ($(fld).val() == "OIP")
		{	
			fld2 = $("#soiDate2");
			if ($(fld).val() == "")
			{
				if (msg == "")
				{
					fld2.focus();	
				}
				msg += "Date of Scheduled Office Interview is required.";
			} else {
				result = maysivalidateDate($(fld).val(),"Date of Scheduled Office Interview");
				if (result != "")
				{
					if (msg == "")
					{
						fld2.focus();	
					}
					msg += result;	
				}	
			}
		}	
		if (msg == "")
		{	
			return true;
		}
		alert(msg);
		return false;
	});
	
	//used by maysiSubmitButton  & maysiUpdateButton
	function maysivalidateDate(fldValue, fldName)
	{
		var msg = "";
		var numericRegExp = /^[0-9]*$/;
		if (fldValue == "")
		{
			msg = fldName + " is required.\n";
			return msg;
		}
		if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
		{
			msg = fldName + " must be in the MM/DD/YYYY format.\n";
			return msg;
		}
		var dValues = fldValue.split('/');
		var month = dValues[0];
		var day = dValues[1];
		var year = dValues[2];

		if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
			msg = fldName + " is not a valid date.\n";
			return msg;	
		} 

		if (month.length != 2 || day.length != 2 || year.length != 4) {
			msg = fldName + " must be in the MM/DD/YYYY format.\n";
			return msg;	
		} 

	    if (month < 1 || month > 12)
	    {
			msg = fldName + " is not valid.\n";
			return msg;		
	    }
	    if (day < 1 || day > 31) {
			msg = fldName + " is not valid.\n";
			return msg;		
	    }
	    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
	    {
			msg = fldName + " is not valid.\n";
			return msg;	
	    }
	    if (month == 2) {
	        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	        if (day > 29 || (day == 29 && !leap)) {
				msg = fldName + " is not valid.\n";
				return msg;	
	        }
	    } 
	    // check date to be current or future value
	    if (msg == ""){
			var dt = fldValue + " 23:59:59";
			var fldDateTime = new Date(dt);
			var curDateTime = new Date();
			if (fldDateTime < curDateTime){
				msg = fldName + " must be current or future date.\n";
				return msg;				
			}    	
	    }
	 	return msg;
	}
	
	$("#maysiFormResetButton").on("click", function() {
		var theForm = document.mentalHealthForm;
		$(theForm)[0].reset();
		$("#reasonNotDoneRow").hide();
		$("#schdOffIntDateRow1").hide();
		$("#schdOffIntDateRow2").hide();
		$("#otherReasonNotDone").hide();
	})
	
	$("#notDone").on("change", function(){
		$("#schdOffIntDateRow1").hide();
		$("#otherReasonNotDone").hide();
		$("#othReasonNotDone").val("");
		if (this.value == "OIP")
		{
			$("#schdOffIntDateRow1").show();
		}
		$("#soiDate1").val("");
		
		if (this.value == "OTH")
		{
			$("#otherReasonNotDone").show();
		}
	});
	$("#notDoneU").on("change", function(){
		$("#schdOffIntDateRow2").hide();
		if (this.value == "OIP")
		{
			$("#schdOffIntDateRow2").show();
		}
		$("#soiDate2").val("");
	});
	
	//script from subAssess.jsp - MAYSI
	$("[name='subsAssessmentReferral']").on("change", function() {
		if($(this).val() == "true"){
			$("#providerType").show();
		}
		else{
			$("#providerType").hide();
		}
	});
	
	$("#subAssessFormSubmit").on("click", function() {
		var msg = "";
	  	if( $("[name='subsAssessmentReferral']")[0].checked == false  &&
	  		$("[name='subsAssessmentReferral']")[1].checked == false )
	    {
	      msg = "Was the child referred to a mental health professional for a subsequent assessment based on the MAYSI results?  is required." ;
	    }
	  	else
	  	{
	 	    if( $("[name='subsAssessmentReferral']")[0].checked == true  &&
	 	    	$("[name='providerReferredTypeId']").val() == "")
	 	    {
	 	    	msg = "To what type of provider was the juvenile referred?  is required." ;
	      	}
	 	    else if( $("[name='subsAssessmentReferral']")[1].checked == true )
	 	    {
	 	    	$("[name='providerReferredTypeId']").val("") ;
	 	    }
	  	}
	  	
  		if( $("[name='wasSubsAssessmentCompleted']")[0].checked == false  &&
  		  	$("[name='wasSubsAssessmentCompleted']")[1].checked == false )
	  	{
	  		msg += "\nWas a subsequent assessment completed on this youth?  is required." ;
	  	}
	  	
	  	if( msg != "" )
	  	{
	 		alert( msg ) ;
	 		return( false ) ;	    
	  	}

		return( true ) ;
	});
	
	//mentalHealthHospitalizationCreate.jsp - Hospitalization
	$("#hospitalizationNext").on("click", function() {
	
		//var val1 = validateHospitalizationDates(theForm);
		if( validateHospitalizationDates(this.form) ){
			
			return validateHospitalizationForm(this.form);
		}	
		else{
			return false;
		}	
		
	});
	
	//used with hospitalizationNext
	function validateHospitalizationDates(thisForm)
	{
		if($("[name='hospRec.admissionDate']").val() == "")
		{
			alert("Admission Date is required.\n");
			$("[name='hospRec.admissionDate']").focus();
			return false;
		}
		if($("[name='hospRec.releaseDate']").val() == "")
		{
	    alert("Release Date is required.\n");
	    $("[name='hospRec.releaseDate']").focus();
	    return false;
		}

		var releasedate1 = $("[name='hospRec.releaseDate']").val();
		var chkdate1fmt = isDate(releasedate1,'MM/dd/yyyy');
		if (chkdate1fmt == false)
		{
	    alert("Release Date is invalid.  Valid format is mm/dd/yyyy.");
	    $("[name='hospRec.releaseDate']").focus();    
	    return false;		
		}

		var admdate2 = $("[name='hospRec.admissionDate']").val();
		var chkdate2fmt = isDate(admdate2,'MM/dd/yyyy');
		if (chkdate2fmt == false)
		{
	    alert("Admission Date is invalid.  Valid format is mm/dd/yyyy.");
	    $("[name='hospRec.admissionDate']").focus();      
	    return false;		
		}
		
		var mydate = new Date();	
		var year = mydate.getFullYear();
		var month = mydate.getMonth()+1;
		if(month < 10)
		{
			month = "0" +month;
		}
		var daym = mydate.getDate();
		if (daym < 10)
		{
			 daym = "0" +daym ;
		}	 
		var currentDateStr = month +"/" +daym +"/" +year ;	 	
	    if(compareDates(admdate2,'MM/dd/yyyy',currentDateStr,'MM/dd/yyyy')==1)
		{
	    	alert("Admission date cannot be a future date.");
	    	$("[name='hospRec.admissionDate']").focus();
	  	 	return false;
		}	

		if(compareDates(releasedate1,'MM/dd/yyyy',currentDateStr,'MM/dd/yyyy')==1)
		{
			alert("Release date cannot be a future date.");
			$("[name='hospRec.releaseDate']").focus();
			return false;
		}

		var chk = compareDates(admdate2,'MM/dd/yyyy',releasedate1,'MM/dd/yyyy');
		if( chk == 1)
		{
			alert("Release Date cannot be before Admission date.");
			$("[name='hospRec.releaseDate']").focus();    
			return false;
		}
		else
		{
			return true;
		}
	}
	//used with hospitalizationNext
	function validateHospitalization(thisForm) 
	{	
		var msg = "";
		
	/*	if(validateNewHospForm(thisForm))
		{
			
		}
		else{
		 return false;
		 }*/
		//trim(thisForm['hospRec.facilityName']);
		 $("[name='hospRec.facilityName']").val(Trim($("[name='hospRec.facilityName']").val()));
		 $("[name='hospRec.admissionDate']").val(Trim($("[name='hospRec.admissionDate']").val()));
	     $("[name='hospRec.releaseDate']").val(Trim($("[name='hospRec.releaseDate']").val()));
	     $("[name='hospRec.hospitalizationReason']").val(Trim($("[name='hospRec.hospitalizationReason']").val()));

	     
		// 28 nov 2006 - mjt - following validation added
		
		if( $("[name='hospRec.facilityName']").val() == "" )
		{
			alert("Facility Name is required.\n");
			$("[name='hospRec.facilityName']").focus();
			return false;
		}
		
		if($("[name='hospRec.hospitalizationReason']").val() == "")
		{
			alert("Hospitalization reason is required.\n");
			$("[name='hospRec.hospitalizationReason']").focus();
			return false;
		}
		if($("[name='hospRec.admissionTypeCd']").val() <= "")
		{
			alert("Admission Type is required.\n");
			$("[name='hospRec.admissionTypeCd']").focus();
			return false;
		}

		 if ($("[name='hospRec.physicianPhone.areaCode']").val() != "" && $("[name='hospRec.physicianPhone.prefix']").val() == "") {
	      alert("All of Phone number must be entered if there is partial entry.");
		 $("[name='hospRec.physicianPhone.prefix']").focus();
	      return false;
	  	 }
		   if ($("[name='hospRec.physicianPhone.areaCode']").val() != "" && $("[name='hospRec.physicianPhone.last4Digit']").value == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.last4Digit']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.prefix']").val() != "" && $("[name='hospRec.physicianPhone.areaCode']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.areaCode']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.prefix']").val() != "" && $("[name='hospRec.physicianPhone.last4Digit']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.last4Digit']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.last4Digit']").val() != "" && $("[name='hospRec.physicianPhone.areaCode']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.areaCode']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.last4Digit']").val() != "" && $("[name='hospRec.physicianPhone.prefix']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.prefix']").focus();
		      return false;
		   }
		   
	    
	}
	
	//Mental Health - DSM
	//mentalHealthDSMTestResultsCreate.jsp - DSM
	//if statements for onload linked to functions
	if(document.title == 'JIMS2 - mentalHealthDSMTestResultsCreate.jsp')
	{
		enableDiagnosis();
		checkOnDSMLoad();//originally checkOnLoad()
	}
	
	$("[name='diagnosisLink']").on('click',function(){
		changeFormActionURL($(this).data("href") , true);
	});
	
	$("[name = 'dsmRec.axisIPrimaryScore'],[name = 'dsmRec.axisISecondaryScore'],[name = 'dsmRec.axisITertiaryScore']," +
	  "[name = 'dsmRec.axisIFourth'],[name = 'dsmRec.axisIFifth'],[name = 'dsmRec.axisIIPrimaryScore'],[name = 'dsmRec.axisIISecondaryScore']," +
	  "[name = 'dsmRec.axisIIIPrimaryScore'],[name = 'dsmRec.axisIIISecondaryScore'],[name = 'dsmRec.diagnosis10']").keyup(function (){
		enableDiagnosis();
	});
	function enableDiagnosis()
	{
		var diag = $("[name='dsmRec.axisIPrimaryScore']");		
		var diag2 = $("[name='dsmRec.axisISecondaryScore");
		var diag3 = $("[name='dsmRec.axisITertiaryScore");
		var diag4 = $("[name='dsmRec.axisIFourth");
		var diag5 = $("[name='dsmRec.axisIFifth");
		var diag6 = $("[name='dsmRec.axisIIPrimaryScore");
		var diag7 = $("[name='dsmRec.axisIISecondaryScore");
		var diag8 = $("[name='dsmRec.axisIIIPrimaryScore");
		var diag9 = $("[name='dsmRec.axisIIISecondaryScore");
		var diag10 = $("[name='dsmRec.diagnosis10");
		
		
		if($(diag).val() != "" && $(diag2).val() != "" && $(diag3).val() != "" && $(diag4).val() != "" && $(diag5).val() != "" && $(diag6).val() != "" && $(diag7).val() != "" && $(diag8).val() != "" && $(diag9).val() != "")
		{
			$(diag10).prop('disabled', false);
		}
		else if($(diag).val() != "" && $(diag2).val() != "" && $(diag3).val() != "" && $(diag4).val() != "" && $(diag5).val() != "" && $(diag6).val() != "" && $(diag7).val() != "" && $(diag8).val() != "")
		{
			$(diag9).prop('disabled', false);
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else if($(diag).val() != "" && $(diag2).val() != "" && $(diag3).val() != "" && $(diag4).val() != "" && $(diag5).val() != "" && $(diag6).val() != "" && $(diag7).val() != "")
		{
			$(diag8).prop('disabled', false);
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else if($(diag).val() != "" && $(diag2).val() != "" && $(diag3).val() != "" && $(diag4).val() != "" && $(diag5).val() != "" && $(diag6).val() != "")
		{
			$(diag7).prop('disabled', false);
			
			if($(diag8).val() == ""){
				$(diag8).prop('disabled', true);
			}else{
				$(diag8).prop('disabled', false);
			}		
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else if($(diag).val() != "" && $(diag2).val() != "" && $(diag3).val() != "" && $(diag4).val() != "" && $(diag5).val() != "")
		{
			$(diag6).prop('disabled', false);
			
			if($(diag7).val() == ""){
				$(diag7).prop('disabled', true);
			}else{
				$(diag7).prop('disabled', false);
			}		
			if($(diag8).val() == ""){
				$(diag8).prop('disabled', true);
			}else{
				$(diag8).prop('disabled', false);
			}		
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else if($(diag).val() != "" && $(diag2).val() != "" && $(diag3).val() != "" && $(diag4).val() != "")
		{
			$(diag5).prop('disabled', false);
			
			if($(diag6).val() == ""){
				$(diag6).prop('disabled', true);
			}else{
				$(diag6).prop('disabled', false);
			}		
			if($(diag7).val() == ""){
				$(diag7).prop('disabled', true);
			}else{
				$(diag7).prop('disabled', false);
			}		
			if($(diag8).val() == ""){
				$(diag8).prop('disabled', true);
			}else{
				$(diag8).prop('disabled', false);
			}		
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else if($(diag).val() != "" && $(diag2).val() != "" && $(diag3).val() != "")
		{
			$(diag4).prop('disabled', false);
			
			if($(diag5).val() == ""){
				$(diag5).prop('disabled', true);
			}else{
				$(diag5).prop('disabled', false);
			}		
			if($(diag6).val() == ""){
				$(diag6).prop('disabled', true);
			}else{
				$(diag6).prop('disabled', false);
			}		
			if($(diag7).val() == ""){
				$(diag7).prop('disabled', true);
			}else{
				$(diag7).prop('disabled', false);
			}		
			if($(diag8).val() == ""){
				$(diag8).prop('disabled', true);
			}else{
				$(diag8).prop('disabled', false);
			}		
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else if($(diag).val() != "" && $(diag2).val() != "")
		{
			$(diag3).prop('disabled', false);
			
			if($(diag4).val() == ""){
				$(diag4).prop('disabled', true);
			}else{
				$(diag4).prop('disabled', false);
			}
			if($(diag5).val() == ""){
				$(diag5).prop('disabled', true);
			}else{
				$(diag5).prop('disabled', false);
			}		
			if($(diag6).val() == ""){
				$(diag6).prop('disabled', true);
			}else{
				$(diag6).prop('disabled', false);
			}		
			if($(diag7).val() == ""){
				$(diag7).prop('disabled', true);
			}else{
				$(diag7).prop('disabled', false);
			}		
			if($(diag8).val() == ""){
				$(diag8).prop('disabled', true);
			}else{
				$(diag8).prop('disabled', false);
			}		
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else if($(diag).val() != "")
		{
			$(diag2).prop('disabled', false);
			
			if($(diag3).val() == ""){
				$(diag3).prop('disabled', true);
			}else{
				$(diag3).prop('disabled', false);
			}		
			if($(diag4).val() == ""){
				$(diag4).prop('disabled', true);
			}else{
				$(diag4).prop('disabled', false);
			}
			if($(diag5).val() == ""){
				$(diag5).prop('disabled', true);
			}else{
				$(diag5).prop('disabled', false);
			}		
			if($(diag6).val() == ""){
				$(diag6).prop('disabled', true);
			}else{
				$(diag6).prop('disabled', false);
			}		
			if($(diag7).val() == ""){
				$(diag7).prop('disabled', true);
			}else{
				$(diag7).prop('disabled', false);
			}		
			if($(diag8).val() == ""){
				$(diag8).prop('disabled', true);
			}else{
				$(diag8).prop('disabled', false);
			}		
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}
		else
		{
			if($(diag2).val() == ""){
				$(diag2).prop('disabled', true);
			}else{
				$(diag2).prop('disabled', false);
			}		
			if($(diag3).val() == ""){
				$(diag3).prop('disabled', true);
			}else{
				$(diag3).prop('disabled', false);
			}		
			if($(diag4).val() == ""){
				$(diag4).prop('disabled', true);
			}else{
				$(diag4).prop('disabled', false);
			}
			if($(diag5).val() == ""){
				$(diag5).prop('disabled', true);
			}else{
				$(diag5).prop('disabled', false);
			}		
			if($(diag6).val() == ""){
				$(diag6).prop('disabled', true);
			}else{
				$(diag6).prop('disabled', false);
			}		
			if($(diag7).val() == ""){
				$(diag7).prop('disabled', true);
			}else{
				$(diag7).prop('disabled', false);
			}		
			if($(diag8).val() == ""){
				$(diag8).prop('disabled', true);
			}else{
				$(diag8).prop('disabled', false);
			}		
			if($(diag9).val() == ""){
				$(diag9).prop('disabled', true);
			}else{
				$(diag9).prop('disabled', false);
			}
			if($(diag10).val() == ""){
				$(diag10).prop('disabled', true);
			}else{
				$(diag10).prop('disabled', false);
			}
		}		
	}
	//originally checkOnLoad()
	function checkOnDSMLoad()
	{	
		var hn = $("[name='dsmRec.mentalHealthNeeded']");
		var mentalHealthNeeded = $("#mhNeeded").val();
		var mentalHealthTreatment = $("#mhTreatment").val();
		
		var mentalHealthNeededStr = $("#mhNeededStr").val();
		var mentalHealthTreatmentStr = $("#mhTreatmentStr").val();
		
		if(mentalHealthNeededStr=="")
			return;
		if (mentalHealthNeeded=="false" && mentalHealthNeededStr=="No")
		{			
			$("#mhNeededStr").value="No";
			$("#treatment").hide();
			$("#diagnosis").hide();
			hn[1].checked=true;	
			$("[name='dsmRec.mentalHealthTreatment']")[0].checked=false;
			$("[name='dsmRec.mentalHealthTreatment']")[1].checked=false;
			$("[name='dsmRec.dsmivId']")[0].selectedIndex=0;
			return true;
		}
		else if (mentalHealthNeeded=="true" && mentalHealthNeededStr=="Yes")
		{		
			hn[0].checked=true;	
			$("#mhNeededStr").value="Yes";	
			if(mentalHealthTreatment=="true")
			{			
				$("[name='dsmRec.mentalHealthTreatment']")[0].checked=true;	
			}
			else
			{
				if(mentalHealthTreatmentStr=="No")
				{
					$("[name='dsmRec.mentalHealthTreatment']")[0].checked=false;
					$("[name='dsmRec.mentalHealthTreatment']")[1].checked=true;
				}
				else
				{
					$("[name='dsmRec.mentalHealthTreatment']")[0].checked=false;
					$("[name='dsmRec.mentalHealthTreatment")[1].checked=false;
				}
			}
			$("#treatment").show();
			$("#diagnosis").show();
			return true;
		}
		return false;
	}
	
	//used by #DSMTestResultsNext
	function validateDSMCreateFields()
	{
		var desc1 = $('#dsmIPrimaryScoreDesc').val();;
		var desc2 = $('#dsmISecondaryScoreDesc').val();
		var desc3 = $('#dsmITertiaryScoreDesc').val();
		var desc4 = $('#dsmIFourthDesc').val();
		var desc5 = $('#dsmIFifthDesc').val();
		var desc6 = $('#dsmIIPrimaryScoreDesc').val();
		var desc7 = $('#dsmIISecondaryScoreDesc').val();
		var desc8 = $('#dsmIIIPrimaryScoreDesc').val();
		var desc9 = $('#dsmIIISecondaryScoreDesc').val();
		var desc10 = $('#dsmDiagnosis10Desc').val();
		var diag1 = $("[name='dsmRec.axisIPrimaryScore']");		
		var diag2 = $("[name='dsmRec.axisISecondaryScore");
		var diag3 = $("[name='dsmRec.axisITertiaryScore");
		var diag4 = $("[name='dsmRec.axisIFourth");
		var diag5 = $("[name='dsmRec.axisIFifth");
		var diag6 = $("[name='dsmRec.axisIIPrimaryScore");
		var diag7 = $("[name='dsmRec.axisIISecondaryScore");
		var diag8 = $("[name='dsmRec.axisIIIPrimaryScore");
		var diag9 = $("[name='dsmRec.axisIIISecondaryScore");
		var diag10 = $("[name='dsmRec.diagnosis10");

		
		if(desc1 == "")
		{
			 alert("Diagnosis 1 is required.");
			 diag1.focus();
	     return false;	  
		}
		if($(diag1).val() !="" || desc1 != "")
		{
			if($(diag1).val() !="" && desc1 == ""){
				alert("Diagnosis 1 Description is required.");
				diag1.focus();
		     	return false;	  
			}else if($(diag1).val() =="" && desc1 != ""){
				alert("Diagnosis 1 Code is required.");
				diag1.focus();
		     	return false;	
			}
		}
		if($(diag2).val() !="" || desc2 != "")
		{
			if($(diag2).val() !="" && desc2 == ""){
				alert("Diagnosis 2 Description is required.");
				diag2.focus();
		     	return false;	  
			}else if($(diag2).val() =="" && desc2 != ""){
				alert("Diagnosis 2 Code is required.");
				diag2.focus();
		     	return false;	
			}
		}
		if($(diag3).val() !="" || desc3 != "")
		{
			if($(diag3).val() !="" && desc3 == ""){
				alert("Diagnosis 3 Description is required.");
				diag3.focus();
		     	return false;	  
			}else if($(diag3).val() =="" && desc3 != ""){
				alert("Diagnosis 3 Code is required.");
				diag3.focus();
		     	return false;	
			}
		}
		if($(diag4).val() !="" || desc4 != "")
		{
			if($(diag4).val() !="" && desc4 == ""){
				alert("Diagnosis 4 Description is required.");
				diag4.focus();
		     	return false;	  
			}else if($(diag4).val() =="" && desc4 != ""){
				alert("Diagnosis 4 Code is required.");
				diag4.focus();
		     	return false;	
			}
		}
		if($(diag5).val() !="" || desc5 != "")
		{
			if($(diag5).val() !="" && desc5 == ""){
				alert("Diagnosis 5 Description is required.");
				diag5.focus();
		     	return false;	  
			}else if($(diag5).val() =="" && desc5 != ""){
				alert("Diagnosis 5 Code is required.");
				diag5.focus();
		     	return false;	
			}
		}
		if($(diag6).val() !="" || desc6 != "")
		{
			if($(diag6).val() !="" && desc6 == ""){
				alert("Diagnosis 6 Description is required.");
				diag6.focus();
		     	return false;	  
			}else if($(diag6).val() =="" && desc6 != ""){
				alert("Diagnosis 6 Code is required.");
				diag6.focus();
		     	return false;	
			}
		}
		if($(diag7).val() !="" || desc7 != "")
		{
			if($(diag7).val() !="" && desc7 == ""){
				alert("Diagnosis 7 Description is required.");
				diag7.focus();
		     	return false;	  
			}else if($(diag7).val() =="" && desc7 != ""){
				alert("Diagnosis 7 Code is required.");
				diag7.focus();
		     	return false;	
			}
		}
		if($(diag8).val() !="" || desc8 != "")
		{
			if($(diag8).val() !="" && desc8 == ""){
				alert("Diagnosis 8 Description is required.");
				diag8.focus();
		     	return false;	  
			}else if($(diag8).val() =="" && desc8 != ""){
				alert("Diagnosis 8 Code is required.");
				diag8.focus();
		     	return false;	
			}
		}
		if($(diag9).val() !="" || desc9 != "")
		{
			if($(diag9).val() !="" && desc9 == ""){
				alert("Diagnosis 9 Description is required.");
				diag9.focus();
		     	return false;	  
			}else if($(diag9).val() =="" && desc9 != ""){
				alert("Diagnosis 9 Code is required.");
				diag9.focus();
		     	return false;	
			}
		}
		if($(diag10).val() !="" || desc10 != "")
		{
			if($(diag10).val() !="" && desc10 == ""){
				alert("Diagnosis 10 Description is required.");
				diag10.focus();
		     	return false;	  
			}else if($(diag10).val() =="" && desc10 != ""){
				alert("Diagnosis 10 Code is required.");
				diag10.focus();
		     	return false;	
			}
		}
		
		return true;
		
	}
	
	//bug report #28078 - mentalHealthDSMTestResultsCreate.jsp
	//TestDate does not check if false/empty field
	$("#DSMTestResultsNext").on("click", function() {
		if(validateDSMCreateFields())
		{
				var hn = $("[name = 'dsmRec.mentalHealthNeeded']");
				var ht = $("[name = 'dsmRec.mentalHealthTreatment']");
				var id = $("[name = 'dsmRec.dsmivId']");
				var td = $("[name = 'dsmRec.testDate']");

				var tdDate = $(td).val();
				if(tdDate == "")
				{
					alert("TestDate is required.");
					td.focus(); 
					return false;
				}
				var chkdatefmt = isDate(tdDate,'MM/dd/yyyy');
				if (chkdatefmt == false)
				{
				    alert("TestDate is invalid.  Valid format is mm/dd/yyyy.");
				    td.focus();    
				    return false;		
				}
				var mydate = new Date();	
				var year = mydate.getFullYear();
				var month = mydate.getMonth()+1;
				if (month < 10)
				{
					month = "0" +month ;
				} 
				var daym = mydate.getDate();
				if (daym < 10)
				{
					daym = "0" +daym ;
				} 
				var currentDateStr = month +"/" +daym +"/" +year ;
				if(compareDates(tdDate,'MM/dd/yyyy',currentDateStr,'MM/dd/yyyy')==1)
				{
				  	alert("TestDate date cannot be a future date.");
				  	td.focus();
				  	return false;
				}
				
				/*if (hn[0].checked==false && id[0].selectedIndex==0){*/
				if (hn[0].checked==false && hn[1].checked == false){
		            alert("Mental Health Needs selection is required.");		           
		            return false;
			    }
			
				if (hn[0].checked==true)
				{
					
					if (id[0].selectedIndex==0 && ht[1].checked==false && ht[0].checked==false){
						alert("DSM V Diagnosis selection is required.\nMental Health treatment is required.");
						id[0].focus();
						return false;
					}
			
					if (id[0].selectedIndex==0 && (ht[1].checked==true || ht[0].checked==true) ){
						alert("DSM V Diagnosis selection is required.");
						id[0].focus();
						return false;
					}
					if (id[0].selectedIndex!=0 && (ht[1].checked==false && ht[0].checked==false) ){
						alert("Mental Health treatment is required.");
						ht[0].focus();
						return false;
					}
				}
				
				
		}
		else {
			return false;
		}
		return true;
	});
	
	//the following replace the validateDSMVDiagnosisCode() function
	$("#validateDSMVDiagnosisCode_IPrimaryScore").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisIPrimaryScore','axisIPrimaryScoreDesc');
	});
	$("#validateDSMVDiagnosisCode_ISecondaryScore").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisISecondaryScore','axisISecondaryScoreDesc');
	});
	$("#validateDSMVDiagnosisCode_ITertiaryScore").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisITertiaryScore','axisITertiaryScoreDesc');
	});
	$("#validateDSMVDiagnosisCode_IFourth").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisIFourth','axisIFourthDesc');
	});
	$("#validateDSMVDiagnosisCode_IFifth").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisIFifth','axisIFifthDesc');
	});
	$("#validateDSMVDiagnosisCode_IIPrimaryScore").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisIIPrimaryScore','axisIIPrimaryScoreDesc');
	});
	$("#validateDSMVDiagnosisCode_IISecondaryScore").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisIISecondaryScore','axisIISecondaryScoreDesc');
	});
	$("#validateDSMVDiagnosisCode_IIIPrimaryScore").on('click', function(){
		return validateDSMVDiagnosisCode('dsmRec.axisIIIPrimaryScore','axisIIIPrimaryScoreDesc');
	});
	$("#validateDSMVDiagnosisCode_IIISecondaryScore").on('click', function(){
			return validateDSMVDiagnosisCode('dsmRec.axisIIISecondaryScore','axisIIISecondaryScoreDesc');
	});
	$("#validateDSMVDiagnosisCode_diagnosis10").on('click', function(){
		return validateDSMVDiagnosisCode('dsmRec.diagnosis10','diagnosis10Desc');
	});
	function validateDSMVDiagnosisCode(dsmCode, dsmCodeDesc) 
	{
		var dsmDiagnosisCode = $("[name = '"+dsmCode+"']");
		$(dsmDiagnosisCode).val(Trim($(dsmDiagnosisCode).val()));
		var dsmCodeVal = $(dsmDiagnosisCode).val();
		if(dsmCodeVal == "")
		{
	  		alert("Diagnosis code has to be provided for validation.");
	  		dsmDiagnosisCode.focus();
	    	return false;
		}
		
		if( /[^a-zA-Z0-9\.]/.test(dsmCodeVal) ) {
		    alert('Invalid Diagnosis code');
		    return false;
		}
		
		//setting hidden varible to identify the description field to fill in
		$("[name = 'activeDiagnosisField']").val(dsmCodeDesc);
	 	return true;
	}
	
	//radio button extenders
	$("[name='dsmRec.mentalHealthNeeded']").on('change', function(){
		var choice = this.value;
		if(choice == "Yes"){
			$("#treatment").show();
			$("#diagnosis").show();	
			$("#mhNeededStr").val("Yes");
		    }
		else{
			$("#treatment").hide();
			$("#diagnosis").hide();
			var hn = $("[name='dsmRec.mentalHealthNeeded']");
			$("[name='dsmRec.mentalHealthTreatment']")[0].checked=false;
			$("[name='dsmRec.mentalHealthTreatment']")[1].checked=false;
			$("[name='dsmRec.mentalHealthTreatment']").val("No");
			$("[name='dsmRec.dsmivId']").val("");
			$("#mhNeededStr").val("No");
		}
	});
	
	$("[name='dsmRec.mentalHealthTreatment']").on('change', function(){
		if (this.value == "No"){
			$("#mhTreatmentStr").val("No");
		}
	});
	
	//Mental Health - DSM 
	//diagnosisDSMVSearch.jsp - DSM V Diagnosis
	if(document.title == 'JIMS2 Juvenile Casework - diagnosisDSMVSearch.jsp')
	{
		checkForSingleResult();
		$(":radio").prop('checked', false);//used to be clearSelected function
	}
	
	function checkForSingleResult() {
	    var rbs = $("[name='selectedMed']");
		if (rbs.length == 1){
			rbs[0].checked = true;
			$("#select").show();
			$("#noselect").hide();
		}
	}
	
	$("[name='selectedDsmCode']").on('click', function (){
		//alert(document.getElementById("select").class);
		$("#select").show();
		$("#noselect").hide();	
	});
	
	$("#findDSMDiagnosis").on("click",function (){
		if($("[name = 'searchDSMV.dsmCode']").val()=="" && $("[name = 'searchDSMV.dsmCodeDescription']").val()=="")
		 {
		 	alert("At least one field is required for search");
		 	$("[name = 'searchDSMV.dsmCode']").focus();
		 	return false;
		}
		return true;
	});
	
	//Mental Health - Achievement 
	//mentalHealthATTestResultsCreate.jsp - Achievement
	$("#ATTestResultsNext").on("click", function() {
		//var thisForm = document.forms[0];
		$("[name = 'atRec.testDate']").val(Trim($("[name = 'atRec.testDate']").val()));
		$("[name = 'atRec.arithmeticGradeLevel']").val(Trim($("[name = 'atRec.arithmeticGradeLevel']").val()));
		$("[name = 'atRec.arithmeticScore']").val(Trim($("[name = 'atRec.arithmeticScore']").val()));
		$("[name = 'atRec.readingGradeLevel']").val(Trim($("[name = 'atRec.readingGradeLevel']").val()));
		$("[name = 'atRec.readingScore']").val(Trim($("[name = 'atRec.readingScore']").val())); 
		//add composite atRec.readingCompositeScore
		//$("[name = 'atRec.readingCompositeLevel']").val(Trim($("[name = 'atRec.readingCompositeLevel']").val()));
		$("[name = 'atRec.readingCompositeScore']").val(Trim($("[name = 'atRec.readingCompositeScore']").val()));
		$("[name = 'atRec.spellingGradeLevel']").val(Trim($("[name = 'atRec.spellingGradeLevel']").val()));
		$("[name = 'atRec.spellingScore']").val(Trim($("[name = 'atRec.spellingScore']").val()));
		//add sentence comprehension
		$("[name = 'atRec.sentenceCompletionLevel']").val(Trim($("[name = 'atRec.sentenceCompletionLevel']").val()));
		$("[name = 'atRec.sentenceCompletionScore']").val(Trim($("[name = 'atRec.sentenceCompletionScore']").val()));
	  	
	  	if ( $("[name = 'atRec.readingScore']").val() >"" &&  $("[name = 'atRec.readingGradeLevel']").val() =="") {
			alert("Word Reading Level is required if Word Reading Score is entered.");
			$("[name = 'atRec.readingGradeLevel']").focus();
			return false;
		}
	  	
	  	if ( $("[name = 'atRec.readingScore']").val() =="" &&  $("[name = 'atRec.readingGradeLevel']").val() >"") {
			alert("Word Reading Score is required if Word Reading Level is entered.");
			$("[name = 'atRec.readingScore']").focus();
			return false;
		}
	  	  	 	
	  	//if ( $("[name = 'atRec.readingCompositeScore']").val() =="" &&  $("[name = 'atRec.readingCompositeLevel']").val() >"") {
		//	alert("Reading Composite Score is required if Reading Composite Level is entered.");
		//	$("[name = 'atRec.readingCompositeScore']").focus();
		//	return false;
		//}
	  	
	  	//if ( $("[name = 'atRec.readingCompositeScore']").val() >"" &&  $("[name = 'atRec.readingCompositeLevel']").val() =="") {
		//	alert("Reading Composite Level is required if Reading Composite Score is entered.");
		//	$("[name = 'atRec.readingCompositeLevel']").focus();
		//	return false;
		//}
	  	
	  	if ( $("[name = 'atRec.spellingScore']").val() =="" &&  $("[name = 'atRec.spellingGradeLevel']").val() >"") {
			alert("Spelling Score is required if Spelling Grade Level is entered.");
			$("[name = 'atRec.spellingScore']").focus();
			return false;
		}
	  	
	  	if ( $("[name = 'atRec.spellingScore']").val() >"" &&  $("[name = 'atRec.spellingGradeLevel']").val() =="") {
			alert("Spelling Grade Level is required if Spelling Score is entered.");
			$("[name = 'atRec.spellingGradeLevel']").focus();
			return false;
		}
	  	
	  	if ( $("[name = 'atRec.sentenceCompletionScore']").val() =="" &&  $("[name = 'atRec.sentenceCompletionLevel']").val() >"") {
			alert("Sentence Comprehension Score is required if Sentence Comprehension Level is entered.");
			$("[name = 'atRec.sentenceCompletionScore']").focus();
			return false;
		}
	  	
	  	if ( $("[name = 'atRec.sentenceCompletionScore']").val() >"" &&  $("[name = 'atRec.sentenceCompletionLevel']").val() =="") {
			alert("Sentence Comprehension Level is required if Sentence Comprehension Score is entered.");
			$("[name = 'atRec.sentenceCompletionLevel']").focus();
			return false;
		}
		return(validateAchievementFields(this.form));	
	});
	//used with ATTestResultsNext
	function validateAchievementFields(theForm)
	{
		clearAllValArrays();
		var levelMask=/^((0?[0-9]|1[0-2])\.[0-9]|[0-9 ]|[kK]|[hH][sS]|[pP][hH][sS])$/;
		var scoreMask=/^(>?[[1-9][0-9]|1[0-9][0-9]|200|0?[1-9][0-9])$/;
		customValRequired("atRec.testDate", "Test Date is required.","");
	    addMMDDYYYYDateValidation("atRec.testDate","Test Date must be in date format (mm/dd/yyyy). ");
	    customValRequired("atRec.testNameId", "Test Name is required.","");

	    customValMinLength("atRec.readingScore", "Reading Score cannot be less than 2 characters.","2");
	    customValMask("atRec.readingScore", "Word Reading Score must be alphanumeric. Greater than sign '>' allowed before numbers. Reading Score can not be greater than 200.",scoreMask);
	    
	    customValMinLength("atRec.readingGradeLevel", "Reading Level cannot be less than 1 characters.","1");
	    customValMask("atRec.readingGradeLevel", "Word Reading Level must be alphanumeric. Valid Values are 0 through 9, K, HS, and PHS. Decimal point is only symbol allowed. Decimal point required if zero is first number.  Numbers must be present on both sides of decimal point if used.",levelMask);
	    
	    customValMinLength("atRec.readingCompositeScore", "Reading Composite Score cannot be less than 2 characters.","2");
	    customValMask("atRec.readingCompositeScore", "Reading Composite Score must be alphanumeric. Greater than sign '>' allowed before numbers. Reading Composite Score can not be greater than 200.",scoreMask);
	    
	   // customValMinLength("atRec.readingCompositeLevel", "Reading Composite Level cannot be less than 1 characters.","1");
	    //customValMask("atRec.readingCompositeLevel", "Reading Composite Level must be alphanumeric. Valid Values are 0 through 9, K, HS, and PHS. Decimal point is only symbol allowed. Decimal point required if zero is first number.  Numbers must be present on both sides of decimal point if used.",levelMask);

	    customValMinLength("atRec.spellingScore", "Spelling Score cannot be less than 2 characters.","2");
	    customValMask("atRec.spellingScore", "Spelling Score must be alphanumeric. Greater than sign '>' allowed before numbers. Spelling Score can not be greater than 200.",scoreMask);
	    
	    customValMinLength("atRec.spellingGradeLevel", "Spelling Level cannot be less than 1 characters.","1");
	    customValMask("atRec.spellingGradeLevel", "Spelling Level must be alphanumeric. Valid Values are 0 through 9, K, HS, and PHS. Decimal point is only symbol allowed. Decimal point required if zero is first number.  Numbers must be present on both sides of decimal point if used.",levelMask);
	    
	   // customValRequired("atRec.arithmeticScore",  "Arithmetic Score is required.","");
	    //customValMinLength("atRec.arithmeticScore", "Arithmetic Score cannot be less than 2 characters.","2");
	    customValMask("atRec.arithmeticScore", "Math Computation must be alphanumeric. Greater than sign '>' allowed before numbers. Arithmetic Score can not be greater than 200.",scoreMask);
	    
	   // customValRequired("atRec.arithmeticGradeLevel", "Math Computation Grade Level is required.","");
	   // customValMinLength("atRec.arithmeticGradeLevel", "Math Computation Grade Level cannot be less than 1 characters.","1");
	    customValMask("atRec.arithmeticGradeLevel", "Math Computation Grade Level must be alphanumeric. Valid Values are 0 through 9, K, HS, and PHS. Decimal point is only symbol allowed. Decimal point required if zero is first number.  Numbers must be present on both sides of decimal point if used.",levelMask);
	        
	    customValMinLength("atRec.sentenceCompletionScore", "Sentence Comprehension Score cannot be less than 2 characters.","2");
	    customValMask("atRec.sentenceCompletionScore", "Sentence Comprehension Score must be alphanumeric. Greater than sign '>' allowed before numbers. Sentence Comprehension Score can not be greater than 200.",scoreMask);
	        
	    customValMinLength("atRec.sentenceCompletionLevel", "Sentence Comprehension Level cannot be less than 1 characters.","1");
	    customValMask("atRec.sentenceCompletionLevel", "Sentence Comprehension Level must be alphanumeric. Valid Values are 0 through 9, K, HS, and PHS. Decimal point is only symbol allowed. Decimal point required if zero is first number.  Numbers must be present on both sides of decimal point if used.",levelMask);
	    
	    return validateCustomStrutsBasedJS(theForm);
	}
	
	//Mental Health - AdaptiveFunctioning
	//mentalHealthAFTestResultsCreate.jsp - AdaptiveFunctioning
	$("#AFTestResultsNext").on("click", function() {
		var retVal = true;
		var msg = "";
		if($("[name='afRec.testDate']").val() == "")
		{
			msg += "Date is required.\n";
			retVal = false;
		}
		if($("[name='afRec.testNameId']").val() == "")
		{
		    msg += "Test name is required.\n";
			retVal = false;
		}
		
		if($("[name='afRec.standardScore']").val() == "")
		{
		    msg += "Standard score is required.\n";
			retVal = false;
		}else{
			
			if( !validateScore($("[name='afRec.standardScore']").val())){
				msg+= "Standard Score must be numeric.\n";
				retVal = false;
			}
		}
		
		if(!retVal){
			
			alert( msg );
			return retVal;
		}else{
			return msg;
		}		
	});
	
	function validateScore( score ) {
	    var score_regex = /^[0-9]*$/;
	    return score_regex.test(score);
	 }
	
	//Mental Health - AdaptiveBehavior
	//mentalHealthABTestResultsCreate.jsp - AdaptiveBehavior
	$("#ABTestResultsNext").on('click',function(){
		var retVal = true;
		var msg = "";
		if($("[name='abRec.testDate']").val() == "")
		{
			msg += "Date is required.\n";
			retVal = false;
		}else{
			if( !validateDate($("[name='abRec.testDate']").val())){
				msg+= "Date is invalid.  Valid format is mm/dd/yyyy.\n";
				retVal = false;
			}
		}

		if($("[name='abRec.communicationScore']").val() == "")
		{
		    msg += "Communication score is required.\n";
			retVal = false;
		}else{
			
			if( !validateABScore($("[name='abRec.communicationScore']").val())){
				msg+= "Communication must be numeric. Value cannot exceed 100.\n";
				retVal = false;
			}
		}
		
		if($("[name='abRec.livingScore']").val() == "")
		{
		    msg +=  "Daily Living is required.\n";
			retVal = false;
		}else{
			
			if( !validateABScore($("[name='abRec.livingScore']").val())){
				msg+= "Daily Living must be numeric. Value cannot exceed 100.\n";
				retVal = false;
			}
		}
		
		if($("[name='abRec.socialScore']").val() == "")
		{
		    msg += "Socialization is required.\n";
			retVal = false;
		}else{
			
			if( !validateABScore($("[name='abRec.socialScore']").val())){
				msg+= "Socialization must be numeric. Value cannot exceed 100.\n";
				retVal = false;
			}
		}
		
		if(!retVal){
			
			alert( msg );
			return retVal;
		}else{
			return true;
		}		
	});
	
	function validateABScore( score ) {
	    var score_regex = /^(0?[0-9]?[0-9]|100)$/;
	    return score_regex.test(score);
	 }
	
	function validateDate( testdate ) {
	    var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/ ;
	    return date_regex.test(testdate);
	 }
	
});
