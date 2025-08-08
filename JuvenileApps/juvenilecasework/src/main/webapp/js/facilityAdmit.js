/**
 * FOR FACILITY also Includes javascript for IE11 changes.
 */

//validations for modify admit flow starts
//JQuery on Dom ONLOAD
$(document).ready(function () {
	var currDate= new Date();
	var otherFlag=0; // retain other if selected.
	var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
	
	$("#searchFacility").click(function(){	
		goNav('/JuvenileCasework/displayJuvenileProfileSearch.do?isFacility=true');
	});

	function enableTraitsUpdateButton(button){
		$("#facUpdateTraitStatus").prop('disabled',false);
		return button;
	}
	
	//ends with trait id call update button
	$("[styleId$=traitId]").click(function(){
		enableTraitsUpdateButton($(this));
	});
	
	
	if(typeof $("#admitDate") != "undefined"){
		datePickerSingle($("#admitDate"),"Admit date", true);
		//$("#admitDate").datepicker();
	}	

	 if($("#severitySubtypeId").val()== "R"){
	    	$("[id$=true]").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
	    	$("[id$=R]").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
	    }
	if(typeof $("#detainedFacilityId").val() != "undefined" && $("#detainedFacilityId").val()!=""){		
		
		var placementType = $("#assocTypeSel").val().split('|');
		if(placementType[2] == "B")
		{	
			 $("#sec").prop('disabled', false);
			 $("#nsec").prop('disabled', false);
		}		
		else if(placementType[2] == "N" || placementType[2] == "S")
		{
			
			 $("#sec").prop('disabled', true);
			 $("#nsec").prop('disabled', true);
		}
		
		var facWithPlacement = $("#detainedFacilityId").val();		
		 $("#assocTypeSel").val(facWithPlacement).attr("selected","selected");
		 $("#secureStatId1").show();
		 $("#secureStatId2").show();
		$("#admitReasonId1").show();	
		//$("#admitReasonId2").show();
		
		if($("#placementTypeId").val()=="D")
		{						
			$("#locId1").show();					
			$("#locId12").show();
		}
		else
		{					
			$("#locId1").show();
			$("#locId12").hide();
			
		}
		$("#admitReason option").each(function(){
			if($(this).text()== $("#admitReasonStrId").val())
				$(this).attr("selected","selected");
		});
		if($("#facilityStatusId").val() == "N"){
			
			$("#assocTypeSel").prop("disabled",true);
			$("#admitReason").prop("disabled",true);
			$("#secureStatId2").prop("disabled",true);
			$("#refOffId").prop("disabled",true)
		}
		
	}
	else{
		
		if($("#facilityStatusId").val() == "N"){
			$("#admitReason option").each(function(){
				if($(this).text()== $("#admitReasonStrId").val())
					$(this).attr("selected","selected");
			})	
			$("#assocTypeSel").prop("disabled",true);
			$("#admitReason").prop("disabled",true);
			$("#secureStatId2").prop("disabled",true);
			$("#refOffId").prop("disabled",true)
		}
		else{
			$("#assocTypeSel").prop("disabled",false);
			$("#admitReason").prop("disabled",false);
			$("#secureStatId2").prop("disabled",false);
			$("#refOffId").prop("disabled",false)
		}
		
			
	}
	
	if($("#facilityStatusId").val() == "N"){
		
	}

	//trucate if not truncated on mouseout or key up.
	// Limit text on keyup and mouseout
	$("#admissionCommentsId").on('keyup mouseout',function(){		
		textLimit($(this),350);
		return false;
	});
	//limit text to the specified characters
	function textLimit(field,maxlen) {
		if(field.val().length > maxlen) 
		{
			field.val(field.val().substring(0,maxlen));
		    alert("Your input has been truncated to "+maxlen+" characters");
		}
	}
	
	
	// Limit text on keyup and mouseout
	$("#saReasonOtherComments").on('keyup mouseout',function(){
		textLimit($(this),50);
		return false;
	});
	

	//on change.
	$("#admitReason").on("change",function(){
		
		var PIAStatus =$("#PIAStatus").val();		
		var admitReason = $("#admitReason").val();		
		var codeWithDetType = $(this).val().split('|');	
		var placement = $("#assocTypeSel").val();
		var placementType = placement.split('|');		
		if(codeWithDetType[0]=="DIV" || codeWithDetType[0]=="DIP"){
		  	//If admit reason is ‘diversion’, then the facility’s Secure Indicator must be ‘Non-secure;’ otherwise, display notice: “Secure Status flag must be ‘Non-secure’ when Admit Reason is Diversion.”			
				var secStatus=$("#nsec").is(':checked');			
	    		if((typeof placementType[1] != "undefined" && placementType[1]!='D') || !secStatus){
	    			alert("Admit Reason of 'Diversion' can only be selected for Facility with TJJDPlacementType='D' and secure status 'N'.");
	    			return false;
	    		}		    	
			
		}
		//check the detention type for the admit reason
		if(codeWithDetType[1]=="P")
		{
			//check PIA status of booking referral
			if(typeof PIAStatus != "undefined"){
				if(PIAStatus!='P'){
					alert("Admit Reason invalid. Admit Reason has Detention Type P, Booking Referral PIA Status is not P.Please select new admit Reason.");
					return false;
				}
			}
		}
	});
	
	$("#assocTypeSel").on("change",function(){
		
		 $("#secureStatId1").show();
		 $("#secureStatId2").show();
		$("#admitReasonId1").show();	
		//$("#admitReasonId2").show();	
	
		var placementType = $("#assocTypeSel").val().split('|');
		var facilities= $('input[name^=facsIter]');
		if(typeof facilities != "undefined")
		{			
			$.each(facilities,function(index){
				
				if(placementType[0]==$(this).val())
				{
					$(".loc1Span").html($(this).next().val());
					$(".loc2Span").html($(this).next().next().val());
					$(".loc3Span").html($(this).next().next().next().val());
					
				}
			});
		}
		
		if(placementType[1] == "D")
		{						
			$("#locId1").show();					
			$("#locId12").show();
		}
		else
		{					
			$("#locId1").show();
			$("#locId12").hide();
		}
		
		if(placementType[2] == "B")
		{			
			 $("#sec").prop('checked', true);
			 $("#sec").prop('disabled', false);
			 $("#nsec").prop('disabled', false);
		}		
		else if(placementType[2] == "N")
		{
			 $("#nsec").prop('checked', true);
			 $("#secureStatusId").val("N");
			 $("#sec").prop('disabled', true);
			 $("#nsec").prop('disabled', true);
		}
		else if(placementType[2] == "S")
		{
			 $("#sec").prop('checked', true);
			 $("#secureStatusId").val("S");
			 $("#sec").prop('disabled', true);
			 $("#nsec").prop('disabled', true);
		}
	});
		
	//show the special attention reason on click of constant watch and close observation
	$("#sa1,#sa2").click(function () {
		$("#saReasonId").show();
		//bug fix for 145271
		$("[name='selectedSAReasons']").prop("checked",false);
		showOther = 0;
		otherFlag=0;
		/*if(otherFlag==1){
			  $("#saReasonOther1").show();
			  $("#saReasonOther2").show();
		}*/
		$("#saReasonOther1").hide();
		$("#saReasonOther2").hide();
		//
	});
    
	$("#sa3").click(function () {
		//bug fix for 145271
		showOther = 0;
		otherFlag=0;
		//
		$("#saReasonId").hide();
		$("#saReasonOther1").hide();
		$("#saReasonOther2").hide();
	
	});
	
	//if defaulted
	if($("#sa1,#sa2").is(':checked')){
		$("#saReasonId").show();		
		if(otherFlag==1){
			  $("#saReasonOther1").hide();
			  $("#saReasonOther2").hide();
		}
	}
	if($("#sa3").is(':checked')){
		$("#saReasonId").hide();
		$("#saReasonOther1").hide();
		$("#saReasonOther2").hide();
		
	}
	
	var showOther=0;
	//default special attention reason.
	var selected = $("input[type='radio'][name='specialAttentionCd']:checked");
	var sa = $(selected).val();
	var tempFld= $('#defaultSAReason').val();
	if(typeof  tempFld!= "undefined"){
		selectedReasons=tempFld.split(',');
	}
	//defaulting selected sareasons
	var saReasons=$('input[name=selectedSAReasons]');	
	if(sa!='NO'){
		
		if(typeof  selectedReasons!= "undefined"){
			$.each(selectedReasons,function(idx,element){ //loops through the selected sareasons from the previous record
							
					  $.each(saReasons,function(index){  //loops through all the elements of sa reasons.							
						  if($(this).val()==element){ // check if the selected element is matched with the master list							 
							  $(this).prop('checked', true); // select it
							  if($(this).val()=='Other') // if other selected, show the other reason comments.
							  {
								  $("#saReasonOther1").show();
								  $("#saReasonOther2").show();
								  $("#saReasonOtherComments").focus();
								otherFlag=1;
							  }
						  }
						  if($(this).is(':checked') && $(this).val()=="Other"){
								 showOther = 1 ;
							 }else{
								 if( $(this).val()=="Other"){
									 showOther = 0;
									 otherFlag=0;
								 }
							 }
						  
						  if(showOther==0){
							 
							  $("#saReasonOther1").hide();
							  $("#saReasonOther2").hide();
						  }else{
							  $("#saReasonOther1").show();
							  $("#saReasonOther2").show();
						  }
						  
					  }); //second for each- master list
			});//first for each-selected list
		
			$("#saReasonId").show();
		}
	}
	
	//Show special attention other reason text area on click of other check box.
	$('input[name=selectedSAReasons]').click(function(){		
	 if($(this).is(':checked') && $(this).val()=="Other"){		
		 showOther = 1 ;
		 otherFlag=1;
	 }else{
		 if( $(this).val()=="Other"){
			 showOther = 0;
			 otherFlag=0;
		 }
	 }
	 if(showOther==0){		
		
		  $("#saReasonOther1").hide();
		  $("#saReasonOther2").hide();
		  $("#saR").val("");
	  }else{
		  $("#saReasonOther1").show();
		  $("#saReasonOther2").show();
	  }
	});
	
	//validation for required fields: on click of next
    $("#next").click(function () {
    	
    	var PIAStatus =$("#PIAStatus").val();		
		var admitReason = $("#admitReason").val();		
		var codeWithDetType = admitReason.split('|');		
		var facility = $("#assocTypeSel").val();
		var placementType = facility.split('|'); 	
		var authorizedBy=$("#authBy").val();		
		
		if(typeof facility == "undefined" || facility==""){
			alert("Facility is required.");			
    		return false;
		}
		else if(!$('input[name=secureStatus]').is(':checked')){
			alert("Secure Status is required.");
			return false;
		}
		else if(typeof  admitReason == "undefined" || admitReason==""){
			alert("Admit Reason is required.");
			$("#admitReason").focus();
			return false;
		}
			

       	var mou=$("#mou").val();    	
    	var room=$("#roomLoc").val();    	
    	
    	if( $("#locId12").hasClass("hidden")!="false" && mou != "" && room=="" ){
    		alert("Location 3 is required if MOU provided.");
    		$("#roomLoc").focus();
    		return false;
    	}
    		
    		
    		if(codeWithDetType[0]=="DIV" || codeWithDetType[0]=="DIP"){
    			var secStatus=$("#nsec").is(':checked');		
    		  	//If admit reason is ‘diversion’, then the facility’s Secure Indicator must be ‘Non-secure;’ otherwise, display notice: “Secure Status flag must be ‘Non-secure’ when Admit Reason is Diversion.”
    			if((typeof placementType[1] != "undefined" && placementType[1]!='D') || !secStatus){
	    			alert("Admit Reason of 'Diversion' can only be selected for Facility with TJJDPlacementType='D' and secure status 'N'.");
	    			return false;
    	    		}		    	
    			
    		}
    		//check the detention type for the admit reason
    		if(codeWithDetType[1]=="P")
    		{
    			//check PIA status of booking referral
    			if(typeof PIAStatus != "undefined"){
    				if(PIAStatus!='P'){
    					alert("Admit Reason invalid. Admit Reason has Detention Type P, Booking Referral PIA Status is not P.Please select new admit Reason.");
    					return false;
    				}
    			}
    		}    	
    		
    	
    	var selected = $("input[type='radio'][name='specialAttentionCd']:checked");
    	var saReasonOtherComments = $("#saR").val();
    	if($("#sa1,#sa2").is(':checked')){     	
    		if(!$('input[name=selectedSAReasons]').is(':checked')){ //reason required validation #1.    			
    			alert("If Special Attention is selected, Reason is required.");
    			return false;
    		}else{
    			
    			if($('input[name*=selectedSAReasons]:checked').val()=="Other"){ //special attention other required validation #2.       				
    				if(saReasonOtherComments == "" || typeof saReasonOtherComments == "undefined"){    					
    					alert("Special Attention Reason Other Comments is required.");
    					$("#saR").focus();
    					return false;
    				}
    			}
    		}
    	}    	
    
    	
    	/*if(typeof authorizedBy != "undefined" && authorizedBy!="" && authorizedBy.length<5){    		
			alert("Authorized By cannot be less than 5 characters.");
			$("#authBy").focus();
			return false;			
    	}*/
    	
    	var vals = $("#valsId").val();
    	if(vals==""){
    		alert("Valuables is required.");
    		$("#valsId").focus();
    		return false;
    	}
  
    	
       	var mou=$("#mou").val();    	
    	var room=$("#roomLoc").val();    	
    	
    	if( $("#locId12").hasClass("hidden")!="false" && mou != "" && room=="" ){
    		alert("Location 3 is required if MOU provided.");
    		$("#roomLoc").focus();
    		return false;
    	}
    	
    
    	//textLimit($('#admissionComments'),350);   	
    	if($("#admitDate").val()== "")
    	{
    		alert("Admit Date is required.");
			$("#admitDate").focus();
			return false;
    	}
    	else
    	{
	    	var date1 = new Date();	    	
			var diff = Math.abs(date1.getTime()-getDateFromFormat($("#admitDate").val(),"MM/dd/yyyy"));				
			if(Math.ceil(diff/(1000*3600*24))>3)
			{
				alert("Admit Date cannot be back dated more than 2 days from current date. ");
				$("#admitDate").focus();
				return false;
			}
			var prevRelDate = new Date($("#prevReleaseDate").val());
			var currAdmitDate = new Date($("#admitDate").val());
			var prevReleaseTime = $("#prevReleaseTime").val()					
			var relTime = new Date(0,0,0,prevReleaseTime.split(":")[0],prevReleaseTime.split(":")[1],0,0).getTime();
			var admitTime = $("#admitTime").val();		
			var currAdmitTime = new Date(0,0,0,admitTime.substring(0,2),admitTime.substring(2,4),0,0).getTime();			
		
			if($("#facilityStatusId").val() != "N")
			{	
				if( prevRelDate.getTime() > currAdmitDate.getTime() )
				{
					alert("Admit Date cannot be before previous Release Date. ");
					$("#admitDate").focus();
					return false;
				}
				else if( (prevRelDate.getTime() == currAdmitDate.getTime())
						&& ( relTime > currAdmitTime))
				{					
					alert("Admit Time cannot be before previous Release Date/Time. ");
					$("#admitTime").focus();
					return false;
				}
			}
			
    	}
    	if($("#admitTime").val() == "")
    	{
    		 alert("Admit Time is required.")
			  $("#admitTime").focus();
			  return false;
    	}
    	else
    	{
    	
	    	 var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
	    	 if(timeFormatRegex.test($("#admitTime").val())== false){
	 			  alert("Please enter Admit Time in HHmm format.")
	 			  $("#admitTime").focus();
	 			  return false;
	 		  }
	    		var currAdmitDate = new Date($("#admitDate").val());
	    		var today = new Date();
	    		var admitTime = $("#admitTime").val();		    		
	    		var currTime = new Date(0,0,0,today.getHours().toString(),today.getMinutes().toString(),0,0).getTime();
				var currAdmitTime = new Date(0,0,0,admitTime.substring(0,2),admitTime.substring(2,4),0,0).getTime();				
				if(currAdmitDate.getDate() == today.getDate())
				{
					if(currAdmitTime > currTime)
					{
						alert("Admit Time cannot be a future time. ");
						return false;
					}
				}
    	}
    	var admittedBy =  $("#admitBy").val();
    	if(typeof admittedBy !="undefined"){
        	if(admittedBy == ""){
        		alert("Admitted By is required.");
        		$("#admitBy").focus();
        		return false;
        	}
        	else
        	{
        		if(alphaNumericRegExp.test(admittedBy.trim(),alphaNumericRegExp) == false)
        		{
	    			alert("Admitted By contains invalid character(s), only alphanumeric values allowed.");
	    			return false;
	    		}
        	}
    	}
    	
    	/*if ( true ) {
    		spinner();
    	}*/
    	var theForm = document.admitReleaseForm;
    	return validateAdmitReleaseForm(theForm);
    });
    
	
	
});