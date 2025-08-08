$(document).ready(function () {
	
	var determinateSentence = sessionStorage.getItem("determinateSentence");
	if("true" == determinateSentence ) {
		$("#determinateSentenceYes").prop('checked', true);
		$("#timeRow").show();
	} else {
		$("#timeRow").hide();
	}
	
	//priorTJJDCommitmentDate DATE CALENDAR.
	if(typeof  $("#priorTJJDCommitmentDate") != "undefined"){
		datePickerSingle($("#priorTJJDCommitmentDate"),"PriorTJJDCommitment Date",false);
	}

	// Limit text on keyup and mouseout
	if(typeof $("#failureReason").val!='undefined'){ 
		$("#failureReason").on('keyup mouseout',function(){
			textLimit($(this),255);
			return false;
		});
	}
	
	if(typeof $("#inputAllowedId") != 'undefined'){
		
		$("#probationFailureYes").click(function(){
		   	$("#failureReasonRow").show();
		});
		
	    $("#probationFailureNo").click(function(){
	    	$("#mostSeriousOffenseId").val("");
			$("#failureReason").val("");
	    	$("#failureReasonRow").hide();
	    });
		
		if($("#probationFailureYes").is(':checked')){
			$("#failureReasonRow").show();
		}else{
			$("#failureReasonRow").hide()
		}
		
		
		$("#determinateSentenceYes").click(function(){
			sessionStorage.setItem("determinateSentence", "true");
		   	$("#timeRow").show();
		});
		
	    $("#determinateSentenceNo").click(function(){
	    	sessionStorage.setItem("determinateSentence", "false");
	    	$("#timeNumericId").val("");
	    	$("#detentionPeriodId").val("");
	    	$("#timeRow").hide();
	    });
	    
	    if($("#determinateSentenceYes").is(':checked')){
			$("#timeRow").show();
		}else{
			$("#timeRow").hide()
		}
	}

	$("#submitBtn").click(function () {
		  //jquery function validation.
		  if(typeof $("#selectedControllingReferral").val()!='undefined'&& $("#selectedControllingReferral").val()==""){
		  	alert("Controlling Referral is required");
		  	$("#selectedControllingReferral").focus();
		  	return false;
		  }else{
		  	 if(typeof $("#currentOffenseId").val()!='undefined'&& $("#currentOffenseId").val()==""){
		  		alert("Description of Current Offenses is required");
		  	    $("#currentOffenseId").focus();
		  	    return false;
		  	 }
		  }
	  	 //not mandatory
		 /* if(typeof $("#weaponTypeId").val()!='undefined'&& $("#weaponTypeId").val()==""){
		  		alert("Weapon Used is required");
		     	$("#weaponTypeId").focus();
		     	return false;
		  }*/
		  // when probation failure is selected
		  if($("#probationFailureYes").is(':checked')){
			  if(typeof $("#mostSeriousOffenseId").val()!='undefined' && $("#mostSeriousOffenseId").val()==""){
				  alert("Most Serious Offense description is required");
				  $("#mostSeriousOffenseId").focus();
				  return false;
			  	}
			  if(typeof $("#failureReason").val()!='undefined' && $("#failureReason").val()==""){
				  	alert("Failure reason is required");
				  	$("#failureReason").focus();
				  return false;
			  	}
		  }
	  
		  //when determinate sentence is selected.
		  if($("#determinateSentenceYes").is(':checked')){
			  if(typeof $("#timeNumericId").val()!='undefined' && $("#timeNumericId").val()==""){
				  alert("Time of sentence is required.");
					$("#timeNumericId").focus();
				  return false;
			  	}
			  if(typeof $("#detentionPeriodId").val()!='undefined' && $("#detentionPeriodId").val()==""){
				   alert("Time of sentence is required.");
			     	$("#detentionPeriodId").focus();
				   return false;
			  	}
		  }	
	});
	
	$("#courtName").on('change',function(){
		$('form').attr('action','/JuvenileCasework/displayCommonAppCourtOrderUpdateSummary.do?submitAction=Link');
		$('form:first').submit();
	});
	
	$("#selectedControllingReferral").on('change',function(){
		$("#weaponTypeId").val(""); //reset to original state.
		$('form').attr('action','/JuvenileCasework/displayCommonAppCourtOrderUpdateSummary.do?submitAction=Find');
		$('form:first').submit();
	});
	
	$("#mostSeriousOffenseId").on('change',function(){
		$('form').attr('action','/JuvenileCasework/displayCommonAppCourtOrderUpdateSummary.do?submitAction=Process');
		$('form:first').submit();
	});
	
	$("#currentOffenseId").on('change',function(){
		$('form').attr('action','/JuvenileCasework/displayCommonAppCourtOrderUpdateSummary.do?submitAction=Filter');
		$('form:first').submit();
	});
	
	$("#reset").click(function () {
		$("#selectedControllingReferral").val("");
		$("#courtName").val("");
		$("#judgeName").html("");
		$("#gangRelated").html("");
		$("#descCurrentOffenseRowId").hide();
		$("#offenseLevelRowId").hide();
		$("#currentOffenseId").val("");
		$("#typeOfCommitment").val("");
		$("#weaponTypeId").val("");
		$("#determinateSentenceNo").prop("checked",true);
		$("#probationFailureNo").prop("checked",true);
		$("#timeRow").hide();
		$("#failureReasonRow").hide();
		$("#priorTJJDCommitmentDate").val("")
		$("#prosecutingAttorneyName").val($("#prosecutingAttorneyNameHdn").val());
		$("#causeNum").val("");
		$("[id*=stayed]").prop("checked",false)
		return false;
	});
});


/*//not used
  function Trim(s)
  {
    // Remove leading spaces and carriage returns
    while( (s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r') )
    { 
      s = s.substring( 1,s.length ); 
	}
  
    // Remove trailing spaces and carriage returns
    while( (s.substring(s.length -1 ,s.length) == ' ') || (s.substring(s.length -1,s.length) == '\n') || (s.substring(s.length -1,s.length) == '\r'))
    { 
      s = s.substring(0,s.length -1); 
    }
    
    return s;
  }
  
// Replaced the validation in jquery 		  
  function validateRadioFields(thisForm) 
  {
 
    validFields = 1;
    var componentName;
    var elem ;
    var elemVal = "";
    
  
    
    
    if(typeof thisForm["selectedControllingReferral"]!='undefined' && thisForm["selectedControllingReferral"].value =="")
    {
    	alert("Controlling Referral is required");
    	thisForm["selectedControllingReferral"].focus();
     	return false;
     } 
     else
     {
    	 if(typeof thisForm["courtOrder.currentOffenseId"]!='undefined' && thisForm["courtOrder.currentOffenseId"].value =="") {
    		 alert("Description of Current Offenses is required");
    	     thisForm["courtOrder.currentOffenseId"].focus();
    	    return false;
    	 }
     }
    
    if(typeof thisForm["courtOrder.weaponTypeId"]!='undefined' && thisForm["courtOrder.weaponTypeId"].value =="")
    {
    	alert("Weapon Used is required");
    	thisForm["courtOrder.weaponTypeId"].focus();
     	return false;
     }
    
    for(var i = 0; validFields  && (i < thisForm.length); i++ )
    {
      if(thisForm.elements[i].type == 'radio')
      {
        componentName = thisForm.elements[i].name;

        var hasOne = false;
        var radioList = document.getElementsByName(componentName);
      
        for( var x = 0; x < radioList.length; x++ )
        {
          if( radioList[x].checked )
          {
           if(componentName=="courtOrder.probationFailure" )
           {
	            if(radioList[0].checked)
	            {
	            	//if(thisForm["courtOrder.mostSeriousOffenseId"] == null) {
	            	//	alert("There is no Violation of Probation offense available.\n Please select \"No\" to Probation Failure Question." );
	            	//	return false;
	            	//}
	            	
	            	if(typeof thisForm["courtOrder.mostSeriousOffenseId"]!='undefined' && thisForm["courtOrder.mostSeriousOffenseId"].selectedIndex <=0)
		           	{
		           		alert("Most Serious Offense description is required");
		           		thisForm["courtOrder.mostSeriousOffenseId"].focus();
		           		return false;
		           	}
	            	
		           	if(typeof thisForm["courtOrder.failureReason"]!='undefined' && thisForm["courtOrder.failureReason"]==null || thisForm["courtOrder.failureReason"].value=="")
		           	{
		           		alert("Failure reason is required");
		           		thisForm["courtOrder.failureReason"].focus();
		           		return false;
		           	}  
	           	}   
	           	else
	           	{
	           		thisForm["courtOrder.failureReason"].value="";
	           	}      		
           }
           if(componentName=="courtOrder.determinateSentence" && radioList[0].checked)
           {
           		if(typeof thisForm["courtOrder.timeNumericId"]!='undefined' &&  thisForm["courtOrder.timeNumericId"].value == "")
           		{
           			alert("Time of sentence is required.");
           			thisForm["courtOrder.timeNumericId"].focus();
           			return false;
           		}
           		if(typeof thisForm["courtOrder.detentionPeriodId"]!='undefined' && thisForm["courtOrder.detentionPeriodId"].value == "")
           		{
           			alert("Time of sentence is required.");
           			thisForm["courtOrder.detentionPeriodId"].focus();
           			return false;
           		}
           }
          }
      	}
     
      }
    
  
    }//for
  
   return validateCommonAppCourtForm(thisForm); 
  }
*/
