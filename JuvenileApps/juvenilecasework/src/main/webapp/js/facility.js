/**
 * FOR FACILITY also Includes javascript for IE11 changes.
 */

//validations for modify admit flow starts
//JQuery on Dom ONLOAD
$(document).ready(function () {

	$("#generateFinalBtn").on("click",function(){
		window.location.href = '/JuvenileCasework/submitJuvenileFacilityAdmit.do?submitAction=Generate Final&view=briefingView&nocache='+(new Date()).getTime();
	});
	$("#evCourtAdminCheck").on('click',function(){
	return confirm("Are you sure you want to initiate a Court Administration email?");				
	});
	var currDate= new Date();
	var formatCurrDate = (currDate.getMonth() + 1) + '/' + currDate.getDate() + '/' +  currDate.getFullYear();
	//var currTime = currDate.getHours() + ":" + currDate.getMinutes();
	var timeFormatRegex = /^([0-9]|0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
	var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;
	var otherFlag=0; // retain other if selected.
	
	
	/** on load get the value of sa reasons **/
	var selectedSplAttn = $('#specialAttention').val();	

	// get the count of selected sa reasons from previous record.
	var saReasonCount = 0;
	var selectedSAReasons= $('#defaultSAReason').val();
	
	var selectedSA= $('#defaultSA').val();
	$("#"+selectedSA).prop('checked',true);
	
	
		var prevSaReasonOtherComm = $("#oldSaReasonOtherComments").val();
	//	$("#saReasonOtherComments").val(prevSaReasonOtherComm);
		
	/** includes facility traits functionality **/
	$("#finish,#facAddMoreTraits,#facUpdateTraitStatus,#facCreateTraitsBtn").click(function(){
		 $("#back").addClass('hidden');
		 $("#cancel").addClass('hidden');
		 disableSubmitButtonCasework($(this));
		 spinner();
		return false;
	});
		
	function enableFacilityTraitsUpdateButton(button){
		$("#facUpdateTraitStatus").prop('disabled',false);
		return button;
	}	
	
	//trait on click of back. retain the update button if selected.
	var selectedFacilityTrait=$("input[name=selectedValue]");
	if(selectedFacilityTrait.is(':checked')){
		enableFacilityTraitsUpdateButton($(this));
	}
	
	
	//ends with trait id call update button
	$("[styleId$=traitId]").click(function(){
		enableFacilityTraitsUpdateButton($(this));
	});
	
	$("#btnRefTransfer").on("click",function(){
		if($("#refTransferMesg").val()!=""){
			alert($("#refTransferMesg").val());
			return false;
		}else if($("#facilityStatus").val()!="" &&$("#facilityStatus").val()=="E" ){
				$("#refTransferMesg").val("Juvenile has escaped from custody.Detention record cannot be transferred to a different referral.");
				return false;
		}else{
			spinner();
			goNav('/JuvenileCasework/displayJuvenileFacilityRelease.do?submitAction=Booking Transfer');
		}
	});
	//if only one value, check it
	$("#bookingRefNum").prop("checked",'checked');

	
	//RELEASE DATE CALENDAR.
	if(typeof  $("#releaseDate") != "undefined"){
			datePickerSingle($("#releaseDate"),"Release Date",true);
	}

	//Return DATE CALENDAR.
	if(typeof  $("#returnDate") != "undefined"){
		datePickerSingle($("#returnDate"),"Return Date",true);
	}
	
	//added for US 31029
	if(typeof $("#restrictedAccessTrait").val()!= "undefined"){
		var restrictedAccess = $("#restrictedAccessTrait").val();		
		if(restrictedAccess == "true"){
			alert("Information about this youth is RESTRICTED and should not be shared outside of HCJPD personnel.  Contact Data Corrections for additional information.");
		}
	}
	//ESCAPE DATE CALENDAR.
	if(typeof  $("#escapeDate") != "undefined"){
		var currDate = new Date();
		currDate.setDate(currDate.getDate()-2); //back date 2 days only
		datePickerSingleBackDate($("#escapeDate"),"Escape Date",true,currDate);
	}

	// Limit text on keyup and mouseout
	$("#admissionComments,#escapeAttemptComments").on('keyup mouseout',function(){
		textLimit($(this),350);
		return false;
	});
	
	// Limit text on keyup and mouseout
	$("#tempReleaseReasonOtherComments").on('keyup mouseout',function(){
		textLimit($(this),50);
		return false;
	});
	
	//On Load Default Transfer to facility with old facility location and validate on change that old facility is not retained.
	var transferToFacility = $("#transferToFacility").val($("#facility").val());
	
	$("#transferToFacility").on("change",function(){
		if($("#transferToFacility").val()==$("#facility").val()){
			alert("Please select a new facility");
		}
	});
	
	
	 //on change.
	$("#admitReasonId").on("change",function(){
		var PIAStatus =$("#PIAStatus").val();
		var admitReason = $("#admitReasonId").val();
		var placementType =$("#placementType").val();
		var  xSecureStatus = $("#detSecureStatus").val();
		var codeWithDetType = $(this).val().split('|');
		//secure status bug.
		if(codeWithDetType[0]=="DIV" || codeWithDetType[0]=="DIP"){
		  	//If admit reason is ‘diversion’, then the facility’s Secure Indicator must be ‘Non-secure;’ otherwise, display notice: “Secure Status flag must be ‘Non-secure’ when Admit Reason is Diversion.”
			if(typeof admitReason != "undefined"){
		    	if(codeWithDetType[0] =="DIV"){
		    		//bug #26805
			    	var secStatus=$("#ss2").is(':checked');		
		    		if((typeof placementType != "undefined" && placementType!='D') && !secStatus){
		    			alert("The facility’s TJJD Placement Type must be ‘D’ when Admit Reason is Diversion.");
		    			return false;
		    		}
		    		//bug #26805
		    		
		    		if((typeof placementType != "undefined" && placementType=='V' && $("input[name=secureStatus]").val()=='S') || $("input[name=secureStatus]").val()=='S'){
		    				alert("Secure Status flag must be ‘Non-secure’ when Admit Reason is Diversion.")
		    				$("#ss2").prop('checked',true);
		    				$("input[name=secureStatus]").val('N');
		    			return false;
		    		}
		    	}
			}
		}else{
    		if(xSecureStatus=='S'){
    			$("#ss1").prop('checked',true);
				$("input[name=secureStatus]").val('S');
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
	
	$('input[name=specialAttentionCd]').click(function(){
		 if($(this).val()=="CW"||$(this).val()=="CO")
		 {
			 $("#saReasonId").show();
			//bug fix for 145271
			 $("[name='selectedSAReasons']").prop("checked",false);
			 $("#saReasonOtherComments").val("");
			 showOther = 0;
			 otherFlag=0;
				//Bug #51515 - if the user changes to NO and then changes back to previous selection
				/*$("#saReasonOtherComments").val(prevSaReasonOtherComm);	//check if the previous comments has to be retained			
				if(otherFlag==1){
					  $("#saReasonOther1").show();
					  $("#saReasonOther2").show();
					  $("#saReasonOtherComments").focus();
				}
				else
				{*/
			 //
					$("#saReasonOther1").hide();
					$("#saReasonOther2").hide();			
				//}
		 }
	});
	
	$("#NO").click(function () {
		currentSplAttn="NO";
		//bug fix for 145271
		$("[name='selectedSAReasons']").prop("checked",false);
		showOther = 0;
		otherFlag=0;
		//
		if(selectedSplAttn != "NO" && currentSplAttn == "NO")
		{
			//Bug #51515 - if the user changes from CO/CW to NO they have to enter comments
			$("#saReasonId").hide();
			$("#saReasonOther1").show();
			$("#saReasonOther2").show();
			$("#saReasonOtherComments").val("");
			currentSA="NO";
		}
		else
		{			
			$("#saReasonId").hide();			
			if(prevSaReasonOtherComm == "" ||  typeof prevSaReasonOtherComm == "undefined")
			{
				$("#saReasonOther1").hide();
				$("#saReasonOther2").hide();
			}
			else
				$("#saReasonOtherComments").val(prevSaReasonOtherComm);
		}
	});
	
	//if defaulted
	if($("#CW,#CO").is(':checked')){
		$("#saReasonId").show();
		//show("saReasonId",1);
		if(otherFlag==1){
			  $("#saReasonOther1").hide();
			  $("#saReasonOther2").hide();
		}
	}
	if($("#NO").is(':checked')){	
		$("#saReasonId").hide();
		//Bug #51515
		if($("#saReasonOtherComments").val() == "")
		{
			$("#saReasonOther1").hide();
			$("#saReasonOther2").hide();
		}
	}	
	var showOther=0;
	
	//default special attention reason.
	var selectedReasons;
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
					  $.each(saReasons,function(index){ //loops through all the elements of sa reasons.
						  if($(this).val()==element){ // check if the selected element is matched with the master list
							  $(this).prop('checked', true); // select it
							  if($(this).val()=='OT') // if other selected, show the other reason comments.
							  {
								  $("#saReasonOther1").show();
								  $("#saReasonOther2").show();
								  $("#saReasonOtherComments").focus();
								otherFlag=1;
							  }
						  }
						  if($(this).is(':checked') && $(this).val()=="OT"){
								 showOther = 1 ;
							 }else{
								 if( $(this).val()=="OT"){
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
							  $("#saReasonOtherComments").focus();
						  }
						  
					  }); //second for each- master list
			});//first for each-selected list
			$("#saReasonId").show();
		}
	}
	
	//Show special attention other reason text area on click of other check box.
	$('input[name=selectedSAReasons]').click(function(){
	 if($(this).is(':checked') && $(this).val()=="OT"){
		 showOther = 1 ;
	 }else{
		 if( $(this).val()=="OT"){
			 showOther = 0;
			 otherFlag=0;
		 }
	 }
	 if(showOther==0){
		  $("#saReasonOther1").hide();
		  $("#saReasonOther2").hide();
		  $("#saReasonOtherComments").val("");
	  }else{
		  $("#saReasonOther1").show();
		  $("#saReasonOther2").show();
		  $("#saReasonOtherComments").focus();
	  }
	});
	
    //when location change is selected i.e. Floor/Room/Unit Change
    $("#oc").click(function () { //other is selected.
    	otherStatusChange();
    });
    $("#lc").click(function () {
      locationStatusChange();
    });
    $("#ac").click(function () { //admit Reason Change is selected 
    	 admitReasonChange();
    }); 
    $("#sc").click(function () { // when secure status is selected
    	secureStatusChange();
    });
    
    //Secure Status value change
    $("#ss1").click(function(){
    	$("input[name=secureStatus]").val('S'); 
    	 $("#ss1").prop('checked',true);
    });
    
    $("#ss2").click(function(){
    	 $("#ss2").prop('checked',true);
    	$("input[name=secureStatus]").val('N'); 
    });
    
    //when secure status is defaulted
    if($("#ss2").is(':checked')){
    	 $("#ss2").prop('checked',true);
     	 $("input[name=secureStatus]").val('N'); 
    }else{
    	$("input[name=secureStatus]").val('S'); 
    	$("#ss1").prop('checked',true);
    }
        
    //when defaulted.
    if($("#lc").is(':checked')){
    	locationStatusChange();
	}else if($("#ac").is(':checked')){
		admitReasonChange();
	}else if($("#sc").is(':checked')){
		secureStatusChange();
	}else{
	    if($("#oc").is(':checked')){
	    	otherStatusChange();
		}
	}
    //#U.S 27500
    if($("#facilityStatus").val()!="" && ($("#facilityStatus").val()=="E" || $("#facilityStatus").val()=="T")){
    	 $("#lc").prop('disabled',true);
    	 $("#sc").prop('disabled',true);
    	 $("#ac").prop('disabled',true);
    	 $("#oc").prop('checked',true);
    }
    
    //location function
    function locationStatusChange(){
    	 $("#FL").prop('disabled',false);
         $("#UL").prop('disabled',false);
         $("#RL").prop('disabled',false);
         $("#MOU").prop('disabled',false);
         $("#VL").prop('disabled',false);
         $("#LL").prop('disabled',false);
         $("#admitBy").prop('disabled',true);
         $("#admitReasonId").prop('disabled',true);
         $("#ss1").prop('disabled',true);
         $("#ss2").prop('disabled',true);
         $("#reasonForChangeId").prop('disabled',true);
         $("#reasonForChangeId").prop('value','Location within the facility changed');
         $("#oc").prop('checked',false);
    	 $("#ac").prop('checked',false);
    	 $("#sc").prop('checked',false);
    	 
    	 //reset secure status change
    	 if($("#detSecureStatus").val()=='S'){
    		 $("#ss1").prop('checked',true);
    	 }else{
    		 $("#ss2").prop('checked',true);
    	 }
    	 
    	 //reset other value admitted by
    	 $("#admitBy").val($("#admittedByJPO").val());
    	 
      	 //reset admit reason bug fix : 51920
    	 var reasonCd = $("#reasonCode").val();
    	 $("#admitReasonId").val(reasonCd);
    	 
    	 $("input[name=changeLabelCd]").val('FRUC'); 
    }
    
    function admitReasonChange(){
    	 $("#admitReasonId").prop('disabled',false);
    	 $("#FL").prop('disabled',true);
         $("#UL").prop('disabled',true);
         $("#RL").prop('disabled',true);
         $("#MOU").prop('disabled',true);
         $("#VL").prop('disabled',true);
         $("#LL").prop('disabled',true);
         $("#ss1").prop('disabled',true);
    	 $("#ss2").prop('disabled',true);
    	 $("#admitBy").prop('disabled',true);
    	 $("#reasonForChangeId").prop('disabled',true);
    	 $("#reasonForChangeId").prop('value','Admit Reason within the facility changed');
    	 
    	 $("#lc").prop('checked',false);
    	 $("#oc").prop('checked',false);
    	 $("#sc").prop('checked',false);
    	 
    	 //reset location change
    	 $("#FL").val($("#floor").val());
    	 $("#UL").val($("#unit").val());
    	 $("#RL").val($("#room").val());
    	 $("#MOU").val($("#mulocpunit").val());
    	 $("#VL").val($("#valuables").val());
    	 $("#LL").val($("#locker").val());
    	 
    	 //reset secure status change
    	 if($("#detSecureStatus").val()=='S'){
    		 $("#ss1").prop('checked',true);
    	 }else{
    		 $("#ss2").prop('checked',true);
    	 }
    	 
    	 //reset other value admitted by
    	 $("#admitBy").val($("#admittedByJPO").val());
    	 $("input[name=changeLabelCd]").val('ADRC'); 
    }
    
    function secureStatusChange(){
    	 $("#ss1").prop('disabled',false);
    	 $("#ss2").prop('disabled',false);
    	 $("#FL").prop('disabled',true);
         $("#UL").prop('disabled',true);
         $("#MOU").prop('disabled',true);
         $("#RL").prop('disabled',true);
         $("#VL").prop('disabled',true);
         $("#LL").prop('disabled',true);
         $("#admitReasonId").prop('disabled',true);
         $("#admitBy").prop('disabled',true);
         $("#reasonForChangeId").prop('disabled',true);
    	 $("#reasonForChangeId").prop('value','Secure Status within the facility changed');
    	 
    	 $("#lc").prop('checked',false);
    	 $("#ac").prop('checked',false);
    	 $("#oc").prop('checked',false);
    	 
    	 $("#FL").val($("#floor").val());
    	 $("#UL").val($("#unit").val());
    	 $("#RL").val($("#room").val());
    	 $("#MOU").val($("#mulocpunit").val());
    	 $("#VL").val($("#valuables").val());
    	 $("#LL").val($("#locker").val());
    	 
    	 //reset other value admitted by
    	 $("#admitBy").val($("#admittedByJPO").val());
    	 
    	 //reset admit reason bug fix : 51920
    	 var reasonCd = $("#reasonCode").val();
    	 $("#admitReasonId").val(reasonCd);
    	 
    	 $("input[name=changeLabelCd]").val('SESC'); 
    }
    
    function otherStatusChange(){
   	 $("#FL").prop('disabled',true);
     $("#UL").prop('disabled',true);
     $("#RL").prop('disabled',true);
     $("#MOU").prop('disabled',true);
     $("#VL").prop('disabled',true);
     $("#LL").prop('disabled',true);
     $("#admitReasonId").prop('disabled',true);
     $("#ss1").prop('disabled',true);
	 $("#ss2").prop('disabled',true);
	 $("#admitBy").prop('disabled',false);
	 $("#reasonForChangeId").prop('disabled',true);
	 $("#reasonForChangeId").prop('value','Admit By, a Comments field, or Observation Status information has been changed.');
	 
	 $("#lc").prop('checked',false);
	 $("#ac").prop('checked',false);
	 $("#sc").prop('checked',false);
	 
	 //reset location change
	 $("#FL").val($("#floor").val());
	 $("#UL").val($("#unit").val());
	 $("#RL").val($("#room").val());
	 $("#MOU").val($("#mulocpunit").val());
	 $("#VL").val($("#valuables").val());
	 $("#LL").val($("#locker").val());
	 
	 //reset secure status change
	 if($("#detSecureStatus").val()=='S'){
		 $("#ss1").prop('checked',true);
	 }else{
		 $("#ss2").prop('checked',true);
	 }
	 //reset admit reason bug fix : 51920
	 var reasonCd = $("#reasonCode").val();
	 $("#admitReasonId").val(reasonCd);
	 
	 $("input[name=changeLabelCd]").val('OTHR'); 
    }
    
    //return Reason
    var returnReason = $("#returnReason").val();
	if(typeof returnReason != "undefined"){
		if(returnReason=="RE"){
			$("#returnReason").attr("disabled",true);
		}
	}

    //ReleaseTo and outcome in release section.
    var releasedTo = $("#releasedTo").val();
    var outcome = $("#outcome").val();
    var releaseReason = $("#releaseReason").val();
    
    //activity 2.9.5
    if(typeof releasedTo !="undefined" && $("#releaseReason").val()=="N"){
    	$("#releasedTo").attr('disabled',true);
    	$("#outcome").attr('disabled',true);
    }
    
	if(typeof releasedTo !="undefined" && $("#releaseReason").val()=="R"){
		$("#releasedTo").on('change',function(){
			 releasedTo = $("#releasedTo").val();
			 outcome = $("#outcome").val();
						
			/**
			 * Outcome defaults to “Absent without Permission” (B), but can be modified, when ReleasedTo = Away w/o Leave-AWOL (AWL),
			 */  
			if(releasedTo =='AWL'){
				$("#outcome").attr('disabled',false);
				$("#outcome").val('B');
			}
			
		    /**
		     * Outcome defaults to “Changed Facility” (C) and cannot be modified, when:
			   If ReleasedTo =Placed/Transportation (TRN) - documenting legal custody transfers between facilities 
			 **/
			else if(releasedTo =='TRN'){
				$("#outcome").val('C');
				$("#outcome").attr('disabled',true);
			}
			
			/**
			 * Outcome defaults to “Changed Facility” (C) but can be modified when: 
			   ReleasedTo = Chimney Rock Center (CRC), or
			   ReleasedTo = DET from DIV Status (DET).
			 */
			else if(releasedTo =='CRC' || releasedTo =='DET' ||releasedTo=='NTS'){
				$("#outcome").attr('disabled',false);
				$("#outcome").val('C');
			}
			/**
			 * Outcome defaults to “Failure to Comply” (X) but can be modified when:
			   ReleasedTo = Police Officer Local (POL)], or	ReleasedTo = Parole Officer (PRL), or
			   ReleasedTo = Police Officer State (POS), or	ReleasedTo =Adult Authorities (RAA).
			 */
			
			else if(releasedTo =='POL' || releasedTo =='PRL' || releasedTo =='POS' || releasedTo =='RAA'){
				$("#outcome").attr('disabled',false);
				$("#outcome").val('X');
			}
			/**
			 * Outcome defaults to “Transferred out of Jurisdiction” (J), but can be modified when:
			   ReleasedTo = U.S. Probation Off. (FED), or	ReleasedTo = U.S. Marshal (MAR), or
               ReleasedTo = Police, Out-of-Sate (FUZ), or	ReleasedTo = Out of County DET CN (OCD), or
               ReleasedTo = TJJD Representative (TYC).
			 */
			
			else if(releasedTo =='FED' || releasedTo =='MAR' || releasedTo =='FUZ' || releasedTo =='OCD' || releasedTo =='TYC'){
				$("#outcome").attr('disabled',false);
				$("#outcome").val('J');
			}
			/**
			 * Outcome defaults to “Completed” (S), but can be modified when:
			   ReleasedTo = Foster Father (F/F), or 			ReleasedTo = Foster Mother (F/M), or
			   ReleasedTo = Temporary Guardian (GRD), or		ReleasedTo = Legal Father (L/F), or
			   ReleasedTo = Legal Mother (L/M), or			ReleasedTo = Parents (PTS), or
			   ReleasedTo = Relative (REL), or				ReleasedTo = Responsible Adult (RSA), or
			   ReleasedTo = Step Father (S/F), or			ReleasedTo = Step Mother (S/M), or
			   ReleasedTo = Self (SLF).

			 */
			else if("[value*=/]" || releasedTo =='GRD' || releasedTo =='PTS' || releasedTo =='RSA' || releasedTo =='REL' || releasedTo =='SLF'){
				$("#outcome").attr('disabled',false);
				$("#outcome").val('S');
			} 
			else /**  If the user selects a ‘ReleasedTo’ option that is not one of the values listed below in the “Outcome Defaults” section, the Outcome defaults to “Completed” (S), but can be modified. **/
			{
				$("#outcome").attr('disabled',false);
				$("#outcome").val('S');
			}
			
		});
	}
	//on load.
	if(typeof $("#tempReleaseReason").val!='undefined'){
		 if($("#tempReleaseReason").val()=="OT"){
 		 	$("#tempReleaseReasonOther1").show();
 		 	$("#tempReleaseReasonOther2").show();
 	  }else{
 		  $("#tempReleaseReasonOther1").hide();
	      $("#tempReleaseReasonOther2").hide();
	      $("#tempReleaseReasonOtherComments").val("");
 	  }
	}
	
	 //Temp Release Other validation.
    $("#tempReleaseReason").on('change',function(){
    	 if($("#tempReleaseReason").val()=="OT"){
    		 	$("#tempReleaseReasonOther1").show();
    		 	$("#tempReleaseReasonOther2").show();
    	  }else{
    		  $("#tempReleaseReasonOther1").hide();
  	    	  $("#tempReleaseReasonOther2").hide();
  	    	  $("#tempReleaseReasonOtherComments").val("");
    	  }
    });
    
    
    //Referral Transfer Validations:
    var transferToRefs= $('input[name=transferToReferral]');
    if(typeof  transferToRefs!= 'undefined'){
    	$.each(transferToRefs,function(idx){ 
    		 var val = $(transferToRefs[idx]).val();
    		 if(!val.match("R$")){
    			var transferToRefId = '#'+$(transferToRefs[idx]).attr('id');
    		    $(transferToRefId).prop("checked",true);
    		    return false;
    		 }
    	});
    }
    
    //severity subtype conditions
    $("[id$=R]").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
    // if no casefiles found disable the referrals.
    $("[id*=false]").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
	var currentReferrals= $('input[name=currentReferral]');
	var bookingRef = $("#bookingRefId").val();
	var currentRefId; 
    if(typeof  currentReferrals!= 'undefined'){
    	$.each(currentReferrals,function(idx){ 
    		 var val = $(currentReferrals[idx]).val();
    		 var id =  $(currentReferrals[idx]).attr('id');
    		 var currentReferral = val.split("-")[0];
    		 if(!val.match("R$")){
    			if(bookingRef==currentReferral){
    				currentRefId = '#'+id;
	    		    $(currentRefId).prop("checked",true);
	    		    return false;
    			}
    		 }
    	});
    	
    	if(typeof  currentRefId== 'undefined' || currentRefId==""){
	    	$.each(currentReferrals,function(idx){ 
	   		 var val = $(currentReferrals[idx]).val();
	   		 var id =  $(currentReferrals[idx]).attr('id');
	   		 var currentReferral = val.split("-")[0];
	   		 if(!val.match("R$")){
	   				var currentRefId = '#'+id;
		    		    $(currentRefId).prop("checked",true);
		    		    return false;
	   			
	   		 }
	    	});
    	}
    }
    
    $("#transferFromReferral").prop("checked",true);
    
    
	var currentSplAttn = selectedSplAttn;
	$('input[name=specialAttentionCd]').on("change",function(){
		currentSplAttn = $('input[name=specialAttentionCd]:checked').val();
	});
	
	
	
	  $("#nextTmpDecision").click(function () {
	
		  var isDecisionChecked= $('input[name="temporaryReleaseAllowed"]:checked').val();
	  	  console.log("Inside nextTmpDecision " + isDecisionChecked );
			if(!isDecisionChecked){
					 alert("Temporary Release Decision is required.");
					  return false;
				}
			
			
	  });
	  
		// Limit text on keyup and mouseout
		$("#tempReleaseDecisionComments").on('keyup mouseout',function(){
			textLimit($(this),100);
			return false;
		});
	///////////////////////////////////////////////////////////////////////////////**********NEXT******************//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//validation for required fields: on click of next
    $("#next").click(function () {
    	
    	 if(typeof admitBy !="undefined"){
	    	if($("#admitBy").val()!=""){
	    		if(alphaNumericRegExp.test($("#admitBy").val().trim(),alphaNumericRegExp) == false){
	    			alert("Admitted By contains invalid character(s), only alphanumeric values allowed.");
	    			return false;
	    		}
	    	}
    	 }
    	
    	var PIAStatus =$("#PIAStatus").val();
		var admitReason = $("#admitReasonId").val();
		var placementType =$("#placementType").val();
		//Check if the release information is modified.
    	var xReleaseDate = $("#xReleaseDate").val();
    	var xReleaseTime =  $("#xReleaseTime").val();
    	var xReleaseTo =  $("#xReleaseTo").val();
    	var xReleaseBy =  $("#xReleaseBy").val();
    	var xReleaseAuthorizedBy =  $("#xReleaseAuthorizedBy").val();
    	
    	var releaseChangeFlg = 0;
    	
    	var releaseDate = $("#releaseDate").val();
    	var releaseTime =  $("#releaseTime").val();
    	var releaseTo =  $("#releasedTo").val();
    	var releaseBy =  $("#releasedBy").val();
    	var releaseAuthorizedBy =  $("#releaseAuthority").val();
    	
    	var escapeDate = $("#escapeDate").val();
    	var escapeTime = $("#escapeTime").val();
    	var xEscapeDate = $("#xEscapeDate").val();
    	var xEscapeTime = $("#xEscapeTime").val();
    	
    	var escapeAttempts = $("#escapeAttempts").val();
    	var xEscapeAttempts = $("#xEscapeAttempts").val();
    	var escapeAttemptComments = $("#escapeAttemptComments").val();
    	var xEscapeComments = $("#xEscapeComments").val();
    	
    	        	
    	if(typeof releaseTo!="undefined" && typeof xReleaseTo!="undefined" && typeof releaseBy!="undefined" && typeof xReleaseBy!="undefined" && typeof releaseAuthorizedBy!="undefined" && typeof xReleaseAuthorizedBy!="undefined" ){
    		if(releaseTo!=xReleaseTo || releaseBy!=xReleaseBy || releaseAuthorizedBy!=xReleaseAuthorizedBy){
    			releaseChangeFlg=1;
    		}
    	 	//check for releaseby validation
        	if(releaseChangeFlg==1){
        		if(releaseBy!=xReleaseBy){
        			if(releaseBy==""){
        				alert("ReleaseBy can be modified but cannot be deleted.Please enter a valid value.");
        				$("#releasedBy").focus();
        				return false;
        			}
        		}
        	}
    	}
    	
    	if(typeof escapeDate!="undefined" && typeof xEscapeDate!="undefined" && typeof escapeTime!="undefined" && typeof xEscapeTime!="undefined"){
    		var xEscapeTimeStr = xEscapeTime.replace(":","");
    		if(escapeDate!=xEscapeDate || escapeTime!=xEscapeTimeStr.trim()){
    			releaseChangeFlg=1;
    		}
    	}
    	
    	if(typeof escapeAttempts!="undefined" && typeof xEscapeAttempts!="undefined" && typeof escapeAttemptComments!="undefined" && typeof xEscapeComments!="undefined"){
    		if(escapeAttempts!=xEscapeAttempts || escapeAttemptComments!=xEscapeComments.trim()){
    			releaseChangeFlg=1;
    		}
    	}
		
    	if(typeof  admitReason!= "undefined" && admitReason!=null){
    		var codeWithDetType = admitReason.split('|');
    		if(codeWithDetType[0]=="DIV" || codeWithDetType[0]=="DIP"){
    		  	//If admit reason is ‘diversion’, then the facility’s Secure Indicator must be ‘Non-secure;’ otherwise, display notice: “Secure Status flag must be ‘Non-secure’ when Admit Reason is Diversion.”
    		    	if(codeWithDetType[0] =="DIV"){
    		    		//bug #26805
    			    	var secStatus=$("#ss2").is(':checked');			
    		    		if((typeof placementType != "undefined" && placementType!='D') && !secStatus){
    		    			alert("The facility’s TJJD Placement Type must be ‘D’ when Admit Reason is Diversion.");
    		    			return false;
    		    		}
    		    		//bug #26805
    		    		if((typeof placementType != "undefined" && placementType=='V' && $("input[name=secureStatus]").val()=='S') || $("input[name=secureStatus]").val()=='S'){
    		    				alert("Secure Status flag must be ‘Non-secure’ when Admit Reason is Diversion.");
    		    			return false;
    		    		}
    		    	}
    			}

    		//check the detention type for the admit reason
    		if(codeWithDetType[1]=="P")
    		{
    			//check PIA status of booking referral
    			if(typeof PIAStatus != "undefined"){
    				if(PIAStatus!='P'){
    					alert("Admit Reason invalid. Admit Reason has Detention Type P, Booking Referral PIA Status is not P.Please select a new admit Reason.");
    					return false;
    				}
    			}
    		}
    	}
    	
    	
    	if($("#CW,#CO").is(':checked')){
    		var invalid=false;
    		if(!$('input[name=selectedSAReasons]').is(':checked')){ //reason required validation #1.
    			alert("If Special Attention is selected, Reason is required.");
    			return false;
    		}else{ 
    			if(typeof  $('input[name=selectedSAReasons]:checked')!= "undefined"){
    	    		$.each($('input[name=selectedSAReasons]:checked'),function(idx,element){ //loops through the selected sareasons from the current record
    	    			if($(this).val()=='OT') 
						 {
						  if($("#saReasonOtherComments").val()==""){
							  alert("Special Attention Reason Other Comments is required.");
							  $("#saReasonOtherComments").focus();
							  invalid=true;
						  }
						 }
    	    		});
    	    		if(invalid){
    	    			return false;
    	    		}
    	    	}
    		}
    	}
    	//Bug #51515 - if CW/CO was previously selected but changed to NO - Comments required
    	if($("#NO").is(':checked')){
    		
    		if(selectedSplAttn != "NO" && currentSplAttn == "NO" && $("#saReasonOtherComments").val()=="")
    		{
    			alert("If ‘Special Attention’ changed to None, Other Reason Comments are required.");
    			$("#saReasonOtherComments").focus();
    			return false;
    		}
    		
    	}
    	if(typeof $("#escapeAttempts").val()!='undefined' && $("#escapeAttempts").val()==""){
    		if($("#escapeAttemptComments").val()!=""){
    			alert("Escape Attempts Comments is modified. Please enter a valid value in Escape Attempts.");
    			$("#escapeAttempts").focus();
    			return false;
    		}
    	}
    	
    	//if special attention is selected, do not validate change flags. [added after carlas review comments.]
    	var currentSaReasons= [];
    	var newSAReasons= $('input[name=selectedSAReasons]:checked'); // checked again in modify flow
    	//selectedSAReasons
    	if(typeof  newSAReasons!= "undefined"){
    		$.each(newSAReasons,function(idx,element){ //loops through the selected sareasons from the current record
    			currentSaReasons.push($(element).val());
    		});
    	}
    	
    	var currSaReasonOtherComm = $("#saReasonOtherComments").val();
    	
    	//Location change validation.
    	if(($("#FL").val()=="") && ($("#UL").val()=="") && ($("#RL").val()=="")&& ($("#VL").val()=="")){
			if(typeof $("#MOU").val() !="undefined" && $("#MOU").val()!=""){
				alert("FLOOR/UNIT/ROOM/VALUABLES is required if MOU provided.");
				$("#FL").focus();
				return false;
			}
		}
    	//floor numeric validation
    	if(typeof $("#FL").val()!='undefined' && $("#FL").val()!="" && !$.isNumeric($("#FL").val())){
    		alert("Floor is numeric. Please enter a value between [1-9].");
    		$("#FL").focus();
    		return false;
    	}
    	//room alpha numeric validation
    	 if(typeof $("#RL").val() !="undefined"){
 	    	if($("#RL").val()!=""){
 	    		if(alphaNumericRegExp.test($("#RL").val().trim(),alphaNumericRegExp) == false){
 	    			alert("Room contains invalid character(s), only alphanumeric values allowed.");
 	    			return false;
 	    		}
 	    	}
     	 }
    	//unit alpha numeric validation
    	 if(typeof $("#UL").val() !="undefined"){
	 	    	if($("#UL").val()!=""){
	 	    		if(alphaNumericRegExp.test($("#UL").val().trim(),alphaNumericRegExp) == false){
	 	    			alert("Unit contains invalid character(s), only alphanumeric values allowed.");
	 	    			return false;
	 	    		}
	 	    	}
	     	 }
    	 if(typeof $("#VL").val() !="undefined"){
  	    	if($("#VL").val()!=""){
  	    		if(alphaNumericRegExp.test($("#VL").val().trim(),alphaNumericRegExp) == false){
  	    			alert("Valuables contains invalid character(s), only alphanumeric values allowed.");
  	    			return false;
  	    		}
  	    	}
  	    	else
  	    		{
  	    		alert("Please enter Valuables");
  	    		return false;
  	    		}
      	 }
    	 if(typeof $("#LL").val() !="undefined"){
  	    	if($("#LL").val()!=""){
  	    		if(alphaNumericRegExp.test($("#LL").val().trim(),alphaNumericRegExp) == false){
  	    			alert("Locker contains invalid character(s), only alphanumeric values allowed.");
  	    			return false;
  	    		}
  	    	}
      	 }
    	var locationChangeFlg;
    	if(typeof selectedSplAttn!="undefined" && typeof currentSplAttn!="undefined" && typeof selectedSAReasons!="undefined"  && typeof currentSaReasons!="undefined"  &&  typeof prevSaReasonOtherComm!="undefined" &&  typeof currSaReasonOtherComm!="undefined")
    	{
	    	if(selectedSplAttn==currentSplAttn && selectedSAReasons.toString()==currentSaReasons.toString()&& prevSaReasonOtherComm==currSaReasonOtherComm && $("#lc").is(':checked')){
	    		if(locationChangeFlg!=0){
	    			if($("#floor").val() == $("#FL").val()  && $("#unit").val() == $("#UL").val() && $("#room").val() == $("#RL").val()&& $("#valuables").val() == $("#VL").val()&& $("#locker").val() == $("#LL").val()){ // all  not modified.
	    				locationChangeFlg =1;	    				
	    			}
	    		}
	    		//Error alert
	    		if(locationChangeFlg==1){
			    	alert("FLOOR/UNIT/ROOM/MOU is not modified. If FLOOR/UNIT/ROOM change is selected, please do an appropiate change in the location.");
					$("#FL").focus();
					return false;
    			}
    		}
	    	
	    	// admit reason change validation.
	    	if(selectedSplAttn==currentSplAttn && selectedSAReasons.toString()==currentSaReasons.toString()&& prevSaReasonOtherComm==currSaReasonOtherComm && releaseChangeFlg==0 && $("#ac").is(':checked')){
	    		var admitReasonId = $("#admitReasonId").val();
	    		if($("#admitReasonCd").val() == admitReasonId.split("|")[0]){
	    			alert("Admit Reason is not modified. If Admit Reason change is selected, please do a appropiate change in the Admit Reason.");
	    			$("#admitReasonId").focus();
	    			return false;
	    		}
	    	}
	    	
	    	//secure status change
	    	if(selectedSplAttn==currentSplAttn && selectedSAReasons.toString()==currentSaReasons.toString()&& prevSaReasonOtherComm==currSaReasonOtherComm  &&  releaseChangeFlg==0 && $("#sc").is(':checked')){
	    		if(($("#detSecureStatus").val() == $("#ss1").val()) && ($("#detSecureStatus").val() == $("#ss2").val())){
	    			alert("Secure Status is not modified. If Secure Status change is selected, please do a appropiate change in the Secure Status.");
	    			return false;
	    		}
	    	}
	    	
	    	//other change. 
	    	if(selectedSplAttn==currentSplAttn && selectedSAReasons.toString()==currentSaReasons.toString()&& prevSaReasonOtherComm==currSaReasonOtherComm  &&  releaseChangeFlg==0 && $("#oc").is(':checked')){
	    		if(($("#admittedByJPO").val() == $("#admitBy").val()) && ($("#comments").val() == $("#admissionComments").val())){
	    			alert("Other changes are not modified. If other change is selected, please do a appropiate change in the admitted by or the admission comments.");
	    			return false;
	    		}
	    	}
    	}

    	//date validations
    	//Release Date cannot occur before Admit Date and cannot be a future date.The time associated to the Release Date can be modified or deleted.
    	//If the Release Date is the same as the Admit Date, the time associated to the Release Date cannot occur before the Admit Time. Release Date/Time defaults to current system date/time
    	var admitDateStr; //converted date version
    	var admitTimeStr //converted date version
    	
    	var releaseReason=$("#releaseReason").val();
    	if(typeof releaseTo !="undefined" && typeof releaseReason !="undefined"){
    		if(releaseTo=="NTS" && releaseReason=="R"){
    			alert("‘NTS’ cannot be identified as a ReleaseTo value during final release or legal custody transfer processes. Please select a new value.");
    			$("#releasedTo").focus();
    			return false;
    		}
    	}
    	if(typeof releaseTo !="undefined" && typeof releaseReason !="undefined"){
    		if(releaseReason=="N"){
    	    	 $("#outcome").val('C');
    	    }
    		if(releaseTo=="TRN" && releaseReason=="N"){
    			alert("‘TRN’ cannot be identified as a ReleaseTo value during in transfer process. Please select a new value.");
    			$("#releasedTo").focus();
    			return false;
    		}
    	}
   
    	var admitDate = $("#admitDate").val(); // unformatted version: mm/dd/yyyy
    	
    	if(typeof admitDate !="undefined"){
    		admitDateStr = new Date(admitDate);
    	}
    	var admitTime = $("#admitTime").val();
    	if(typeof admitTime !="undefined"){
    		admitTimeStr = new Date(0,0,0,admitTime.split(":")[0],admitTime.split(":")[1],0,0).getTime();
    	}
    	
    	//Release Validations
        if(typeof releaseTime !="undefined"){
        	if(releaseTime == ""){
	    		alert("Release Time is required.");
	    		$("#releaseTime").focus();
	    		return false;
	        }
        	  //HHMM Format.
       	   var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
           if(timeFormatRegex.test($("#releaseTime").val())== false){
           	  alert("Please enter Release Time in HHMM format.")
           	  $("#releaseTime").focus();
           	  return false;
             }
            releaseTime = new Date(0,0,0,releaseTime.substring(0,2),releaseTime.substring(2,4),0,0).getTime();
        }
    	
        if(typeof releaseBy !="undefined"){
        	if(releaseBy == ""){
        		alert("Released By is required.");
        		$("#releasedBy").focus();
        		return false;
        	}else{
        		if(alphaNumericRegExp.test(releaseBy.trim(),alphaNumericRegExp) == false){
	    			alert("Released By contains invalid character(s), only alphanumeric values allowed.");
	    			return false;
	    		}
        	}
    	}
               
        if(typeof releaseTo !="undefined" && releaseTo == ""){
    		alert("Release To is required.");
    		$("#releasedTo").focus();
    		return false;
    	}
        
		if(typeof releaseDate !="undefined" && releaseDate == ""){
	    		alert("Release Date is required.");
	    		$("#releaseDate").focus();
	    		return false;
	    }
		
	    if(typeof releaseAuthorizedBy !="undefined"){
	    	if(releaseAuthorizedBy == ""){
	    		alert("Release Authority is required.");
	    		$("#releaseAuthority").focus();
	    		return false;
	    	}
	    	else{
	    		if(releaseAuthorizedBy.trim().length<3){
	    			alert("Release Authority min length is 3. Please enter a valid value.");
	    			$("#releaseAuthority").focus();
	    			return false;
	    		}
	    		if(alphaNumWithSymbolsRegExp.test(releaseAuthorizedBy.trim(),alphaNumWithSymbolsRegExp) == false){
	    			alert("Release Authority contains invalid character(s), The following symbols !@#$%^&*() are allowed.");
	    			$("#releaseAuthority").focus();
	    			return false;
	    		}
	    	}
	    }	
		
    	if(typeof releaseDate != "undefined"){
    		var currDate= new Date();
    		var currTime = currDate.getHours() + ":" + currDate.getMinutes();
    		var currTimeStr ="";
        	if(typeof currTime != "undefined" && currTime!=""){
        	  currTimeStr =  new Date(0,0,0,currTime.split(":")[0],currTime.split(":")[1],0,0).getTime();
        	}
    		releaseDate = new Date(releaseDate);
    		if(typeof releaseDate != "undefined"  && releaseDate.getTime() == currDate.getTime() && releaseTime > currTimeStr){
        		alert("Release cannot happen in the future time. Please enter a valid Release Time.");
        		$("#releaseTime").focus();
        		return false;
        	}
    		
	      if(releaseDate < admitDateStr){
	    		alert("Release Date cannot occur before admit date: "+admitDate);
	    		return false;
	    	}else if(releaseDate.getTime() == admitDateStr.getTime()){
	    		if(releaseTime <= admitTimeStr){
					alert("Release Time cannot be same or before the admit Time: "+admitTime);
					return false;
				}
	    	}
    	}
    	//release validations completed

    	//EscapeDate(Time) defaults to current system date/time; user can modify.  Escape Date cannot occur before Admit Date.Escape Date cannot be a future date.The time associated to the Escape Date can be modified or deleted. 
    	//If the Escape Date is the same as the Admit Date, the time associated to the Escape Date cannot occur before the Admit Time.
  
    	var escapeTime = $("#escapeTime").val();
    	
    	 if(typeof escapeTime != "undefined" && escapeTime==""){
 			alert("Escape Time is required...");
 			$("#escapeTime").focus();
 			return false;
 		}
    	 
    	if(typeof escapeTime != "undefined"){
    		var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/; //HHMM Format.
    		 escapeTime = new Date(0,0,0,escapeTime.substring(0,2),escapeTime.substring(2,4),0,0).getTime();
	    	 //HHMM Format.
			 if(timeFormatRegex.test($("#escapeTime").val())== false){
			  alert("Please enter valid escape Time in HHMM format.");
			  $("#escapeTime").focus();
			  return false;
			}
    	}
    	
    	
    	var detainedDate = $("#detainedDate").val();
    	if(typeof detainedDate != "undefined"){
    		detainedDate = new Date(detainedDate);
    	}
   	  
    	var escapeDate = $("#escapeDate").val();
         if(typeof escapeDate != "undefined" && escapeDate==""){
  			alert("Escape Date is required.");
  			$("#escapeDate").focus();
  			return false;
  		}
         
         if(typeof escapeDate != "undefined"){
        	 
        	var currDate= new Date();
     		var currTime = currDate.getHours() + ":" + currDate.getMinutes();
     		var currTimeStr ="";
         	if(typeof currTime != "undefined" && currTime!=""){
         	  currTimeStr =  new Date(0,0,0,currTime.split(":")[0],currTime.split(":")[1],0,0).getTime();
         	}
	 		escapeDate = new Date(escapeDate);
	 		
		   	if(typeof escapeDate != "undefined"  && escapeDate.getTime() == currDate.getTime() && escapeTime > currTimeStr){
	    		alert("Escape cannot happen in the future time. Please enter a valid Escape Time.");
	    		$("#escapeTime").focus();
	    		return false;
	    	}
		   	
		   	if(escapeDate < admitDateStr){
		   		alert("Escape Date cannot occur before admit date: "+ admitDate );
		    	return false;
		    }else if(escapeDate < detainedDate){
		    	alert("Escape Date cannot occur before detained date: "+detainedDate);
		    	return false;
		    }else if(escapeDate.getTime() == admitDateStr.getTime()){
		    	 	if(escapeTime <= admitTimeStr){
		    	 		alert("Escape Time cannot be same or before the admit Time: "+admitTime);
		    		return false;
		    	}
		    }
         }
    	
    	//Escape Attempts: Users cannot change the value to 0 or delete an existing value in; except through Production Support.Users may enter information documenting the 
    	//number of escape attempts by a juvenile and any supporting text. Users must provide a value for the EscapeAttempts field in order to document EscapeAttemptComments, which are optional.
    	var escapeAttempts = $("#escapeAttempts").val();
    	
    	if(typeof escapeAttempts!='undefined' && escapeAttempts!="" && !$.isNumeric(escapeAttempts)){
    		alert("Escape Attempts is numeric. Please enter a value between [1-99].");
    		$("#escapeAttempts").focus();
    		return false;
    	}
    	
    	if(typeof escapeAttempts!='undefined' && escapeAttempts == '0' || escapeAttempts == '00')
    	{
	    	 alert("EscapeAttempts cannot to be set to the value 0, except through the Production Support. Please enter a value between [1-99].");
	    	 $("#escapeAttempts").val("");
	    	 $("#escapeAttempts").focus();
	    	 return false;
    	}
    	
    	//escape from is mandatory on escape flow.  
    	var escapeFrom = $("#escapeFromId").val();
    	
    	if(escapeFrom ==""){
    		alert("Escape From is required");
    		 $("#escapeFromId").focus();
    		return false;
    	}
    	//escape validation completed.
    	 
    	//ReturnDate Date/Time the juvenile was returned to a facility following an escape or a temporary release. Required if ReturnReason is populated Default to current system date/time. 
    	//Can be modified: Cannot occur before the EscapeDate, FacilityAdmitDate or Detained Date. Cannot be a future date.
    	
    	var returnReason = $("#returnReason").val();
    	if(typeof returnReason != "undefined"){
    		if(returnReason==""){
    			alert("Return Reason is required.");
    			$("#returnReason").focus();
    			return false;
    		}
    	}
    	
    	var returnTime = $("#returnTime").val();
    	if(typeof returnTime != "undefined"){
    		if(returnTime == ""){
    			alert("Please enter a valid Return Time");
    			$("#returnTime").focus();
    			return false;
    		}
    		else
        	{
	   	    	 var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
	   	    	 if(timeFormatRegex.test(returnTime.trim())== false){
	       		  alert("Please enter Return Time in HHmm format.");
	       		  $("#returnTime").focus();
	       		  return false;
	   	    	 }
        	}
    		 returnTime = new Date(0,0,0,returnTime.substring(0,2),returnTime.substring(2,4),0,0).getTime();
    	}
    	
    	var returnDate = $("#returnDate").val();
    	
    	if(typeof returnDate != "undefined"){
    		if(returnDate==""){
    			alert("Return Date is required.");
    			$("#returnDate").focus();
    			return false;
    		}
    		returnDate = new Date(returnDate);
    	}
    	
    	// if there is a previous release or escape.
    	var xEscapeDate =  $("#xEscapeDate").val();
    	var xEscapeTime =  $("#xEscapeTime").val();
    	
    	//RETURN AND ADMIT.
        if(typeof  $("#returnDate").val() != "undefined"){
        	var currDate= new Date();
    		var currTime = currDate.getHours() + ":" + currDate.getMinutes();
    		var currTimeStr ="";
        	if(typeof currTime != "undefined" && currTime!=""){
        	  currTimeStr =  new Date(0,0,0,currTime.split(":")[0],currTime.split(":")[1],0,0).getTime();
        	}
        	if(returnDate.getTime() == currDate.getTime() && returnTime > currTimeStr){
	    		alert("Return cannot happen in the future time. Please enter a valid Return Time.");
	    		$("#returnTime").focus();
	    		return false;
	    	}
        	
	    	if(returnDate < admitDateStr){
	    		alert("Return Date cannot occur before Admit Date: "+admitDate);
	    		return false;
	    	}else{
	    		if(returnDate.getTime()== admitDateStr.getTime()){
		    		if(returnTime <= admitTimeStr){
		    			alert("Return Time cannot be same or before the Admit Time: "+admitTime);
		    			return false;
		    		}
	    		}
	    	}
	    	
	    	//RETURN AND ESCAPE.
	    	if(typeof $('#xEscapeDate').val()!="" && returnDate < new Date(xEscapeDate)){
	    		alert("Return Date cannot occur before Escape Date: "+$("#xEscapeDate").val());
	    		$("#returnDate").focus();
	    		return false;
	    	} else {
	    		if($("#returnDate").val() == $("#xEscapeDate").val()){
			    	var xEscapeTimeStr = new Date(0,0,0,xEscapeTime.split(":")[0],xEscapeTime.split(":")[1],0,0).getTime();
			    	if(xEscapeTime!="" && (returnTime <= xEscapeTimeStr)){
			    		alert("Return time cannot be same or before the Escape Time: "+$("#xEscapeTime").val());
			    		$("#returnTime").focus();
			    		return false;
			    	}
		    	}
	    	}
	    	//RETURN AND RELEASE.
	    	if(returnDate < new Date(xReleaseDate)){
	    		alert("Return Date cannot occur before Release Date: "+xReleaseDate);
	    		return false;
	    	}else{
	    		if($("#returnDate").val() == $("#xReleaseDate").val()){
	    			var xReleaseTimeStr = new Date(0,0,0,xReleaseTime.split(":")[0],xReleaseTime.split(":")[1],0,0).getTime();
		    		if(xReleaseTime!="" && (returnTime <= xReleaseTimeStr)){
		    			alert("Return time cannot be same or before the Release Time: "+$("#xReleaseTime").val());
		    			$("#returnTime").focus();
		    			return false;
		    		}
	    		}
	    	}
        }
        //return validations completed
    	
    	//truncate if not truncated on mouseout or key up.
    	if(typeof $('#admissionComments').val()!="undefined" || typeof $('#saReasonOtherComments').val()!="undefined" ){
    		textLimit($('#admissionComments'),350); 
    		textLimit($('#saReasonOtherComments'),50);
    	}
        
        //TransferToFacility
        var transferToFacility = $("#transferToFacility").val();
        if(typeof transferToFacility !="undefined"){
        	if(transferToFacility == ""){
	        	alert("Transfer To Facility is Required.");
	        	$("#transferToFacility").focus();
	    		return false;
        	}else{
        		if($("#transferToFacility").val()==$("#facility").val()){
        			alert("Please select a new facility to which the Juvenile will be transferred.");
        			$("#transferToFacility").focus();
        			return false;
        		}
        	}
        }
   
        var outcome  = $("#outcome").val();
        if(typeof outcome !="undefined" && outcome ==""){
    		alert("Outcome is required.");
    		$("#outcome").focus();
    		return false;
    	}

       if(typeof $("#tempReleaseReason").val() !="undefined" && $("#tempReleaseReason").val() == ""){
    		alert("Temporary Release Reason is required.");
    		$("#tempReleaseReason").focus();
    		return false;
    	}
   
        var otherTempReleaseReasonComments = $("#tempReleaseReasonOtherComments").val();
        if($("#tempReleaseReason").val()=="OT" && typeof otherTempReleaseReasonComments !="undefined" && otherTempReleaseReasonComments ==""){
        	alert("OtherTemporaryReleaseReason Comments are required if \"Other\" is selected.");
    		$("#tempReleaseReasonOtherComments").focus();
    		return false;
        }
        
        if ( true ) {
        	spinner();
        }
        
    	return true; //no errors
    });
    // on click of next ends
    $("#bannedLink").click(function(){		
		 var juvNum = $(this).data("href");				
		 var url = "/JuvenileCasework/displayJuvenileProfileTraitsSearch.do?submitAction=Detention&juvenileNum=" +juvNum;	
		 var newWin = window.open(url, "pictureWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
			newWin.focus();
		return false;
	});    
	  
    $("input[id^='addFacilityTraitBtn']").on('click', function (e) {		
    	
    	var traitTypeDescription =  $("#traitTypeDescriptionId").val();
    	var traitStatus =  $("#statusId").val();
    
    	if(typeof traitTypeDescription !="undefined" && traitTypeDescription == ""){
     		alert("Trait Type Description is required.");
     		$("#traitTypeDescriptionId").focus();
     		return false;
     	}
         if(typeof traitStatus !="undefined" && traitStatus == ""){
     		alert("Trait Status is required.");
     		$("#statusId").focus();
     		return false;
     	}
		
         if ( true ) {
        	 spinner();
         }
         return true;
    })
});