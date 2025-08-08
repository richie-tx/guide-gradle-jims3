//court Action Display.js - District court conversion.
$(document).ready(function () {	
	var action = $("#action").val();
	var actionError = $("#actionError").val();
	var cursorPosition = $("#cursorPosition").val();
	var searchResultSize = $("#searchResultSize").val();
	var currDate= new Date(); //current Date
	if(typeof $("#courtDate").val() != "undefined"){
		  datePickerSingle($("#courtDate"),"Court Date",false);
	}

	//current page offset.
	var startIndex;
	var endIndex;
	var noOfElements;
	var ps = document.getElementsByName("pager.offset");
	var elementIndex = $(ps).val();

	$("[id^='S.']").find("input,button,text,select").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5"); //Bug #76479
	$("[id^='I.']").find("input,button,text,select").prop("disabled",true).css("background-color","#f5f5f5").css("opacity",".5");
		
	startIndex= elementIndex
	noOfElements =  3
	
	var pageNumber = (parseInt(startIndex) / 3) + 1;
    var lastPageItems = searchResultSize%3;
    var totalPages = parseInt((searchResultSize/3) + 1);
    
     if(pageNumber == totalPages)
     {
		noOfElements = lastPageItems
	 }
	endIndex = parseInt(startIndex) + parseInt(noOfElements); 
		
	/**
	 * 3.4.6.5	  Activity: Validate Court or Ancillary setting Action Date
	 */
	var backDate = new Date("01/01/1960");
	var fmtBackDate=  (backDate.getMonth() + 1) + '/' + backDate.getDate() + '/' +  backDate.getFullYear();

	//holiday check calendar.
	var juvenileDatesList = $("#holidaysList").val();
	//loop through each index page. 4 records per page.
	for (var i=startIndex; i <endIndex; i++) 
	{
		var docketType=$('[name=\'allDktSearchResults['+i+'].docketType\']');
		var docketEventId=$('[name=\'allDktSearchResults['+i+'].docketEventId\']');			
		var docketEventIdKey=$('[name=\'allDktSearchResults['+i+'].docketEventIdKey\']');
		var courtResult= $('[name=\'allDktSearchResults['+i+'].courtResult\']');
		var disposition= $('[name=\'allDktSearchResults['+i+'].disposition\']');
		var transferTo= $('[name=\'allDktSearchResults['+i+'].transferTo\']');
		var courtDate= $('[name=\'allDktSearchResults['+i+'].courtDate\']'); 
		var courtTime= $('[name=\'allDktSearchResults['+i+'].courtTime\']');
		var actionDate= $('[name=\'allDktSearchResults['+i+'].actionDate\']');
		var actionTime= $('[name=\'allDktSearchResults['+i+'].actionTime\']');
		var resetHearingType= $('[name=\'allDktSearchResults['+i+'].resetHearingType\']');
		var barNum= $('[name=\'allDktSearchResults['+i+'].barNum\']');
		var attorneyConnection= $('[name=\'allDktSearchResults['+i+'].attorneyConnection\']');
		var attorneyName= $('[name=\'allDktSearchResults['+i+'].attorneyName\']');
		var galBarNum= $('[name=\'allDktSearchResults['+i+'].galBarNum\']');
		//var aty2BarNum= $('[name=\'allDktSearchResults['+i+'].attorney2BarNum\']');
		var galName= $('[name=\'allDktSearchResults['+i+'].galName\']');
		var prevNotes= $('[name=\'allDktSearchResults['+i+'].prevNotes\']');
		var setNote= $('[name=\'allDktSearchResults['+i+'].setNote\']');
		var comments= $('[name=\'allDktSearchResults['+i+'].comments\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+i+'].atyConfirmation\']');	
		//var trnValue=$('[name=\'allDktSearchResults['+i+'].transferFlag\']');
		if(docketEventIdKey.val()!=undefined)
		{
			var hidden=$("#atyBypass-"+docketEventIdKey.val());
			if(typeof (hidden.val())!="undefined")
				var atyBypass= document.getElementById('atyBypass-'+docketEventIdKey.val()).value;	
			else
				var atyBypass='';
			
			// used for sealed and purged records
			var recType= $('[name=\'allDktSearchResults['+i+'].recType\']');
			var originalActionDate = $('[name=\'allDktSearchResults['+elementIndex+'].originalActionDate\']');
		    var originalActionTime = $('[name=\'allDktSearchResults['+elementIndex+'].originalActionTime\']');	
		    var oldAttorney=document.getElementById('oldAttorney-'+docketEventId.val()).value;
		    var button=$("#attnyBypass."+docketEventIdKey.val());
			if(typeof (button.val())!="undefined")
				document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=true;
		    document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=true;
		    if(docketType.val()=="COURT")
		    {
			    var appellateAttorney= document.getElementById('appAttorney-'+docketEventIdKey.val()).value;  
			    if(appellateAttorney=="1")
			    	document.getElementById('appAttorney.'+docketEventIdKey.val()).checked=true;
			    else
			    	document.getElementById('appAttorney.'+docketEventIdKey.val()).checked=false;
		    }
		    var interpreter= document.getElementById('interpreter-'+docketEventIdKey.val()).value;  
		    //alert(interpreter)
		    if(interpreter=="1")
		    	document.getElementById('interpreter.'+docketEventIdKey.val()).checked=true;
		    else
		    	document.getElementById('interpreter.'+docketEventIdKey.val()).checked=false;    
		    if(oldAttorney!=attorneyName.val())
		    	{
		    		document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false; 
		    		if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
				  		document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
		    	}
			   
			    //validations to keep the confirm button enabled if anything changed on load
			    var oldattorneyConn=document.getElementById('oldAttorneyConn-'+docketEventId.val()).value;
				var newAttorneyConn;
				  if(document.getElementById('HATattorneyConnection.'+docketEventIdKey.val()).checked== true)
				  {
					  newAttorneyConn='HAT';
				  }
				  else if(document.getElementById('AATattorneyConnection.'+docketEventIdKey.val()).checked== true)
				  {
					  newAttorneyConn='AAT';
				  }
				  else if(document.getElementById('PDOattorneyConnection.'+docketEventIdKey.val()).checked== true)
				  {
					  newAttorneyConn='PDO';
				  }		
				  else
				  {
					  newAttorneyConn='';
				  }
				  
				if(oldattorneyConn!=newAttorneyConn)			  
			  	{
					document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
					if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
				  		document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
			  	}
				
				if(atyBypass!="YES")
				{
					if(atyConfirm.val()!="YES" && docketType.val()=="DETENTION")// 
					{
						var oldCourtResult=document.getElementById('oldCourtResult-'+docketEventId.val()).value;
						var newCourtResult=document.getElementById('courtResult-'+docketEventIdKey.val()).value;
						var oldDisposition=document.getElementById('oldDisposition-'+docketEventId.val()).value;
						var newDisposition=document.getElementById('disposition-'+docketEventIdKey.val()).value;
						var oldtransferTo=document.getElementById('oldtransferTo-'+docketEventId.val()).value;
						var newnewTransferTo=document.getElementById('transferTo-'+docketEventIdKey.val()).value;
						var oldActionDate=document.getElementById('oldActionDate-'+docketEventId.val()).value;
						var newActionDate=document.getElementById('actionDate-'+docketEventIdKey.val()).value;
						var oldActionTime=document.getElementById('oldActionTime-'+docketEventId.val()).value;
						var newActionTime=document.getElementById('actionTime-'+docketEventIdKey.val()).value;
						/*var oldHearingType=document.getElementById('oldResetHearingType-'+docketEventId.val()).value;
						var newHearingType=document.getElementById('hearingType-'+docketEventIdKey.val()).value;
						var oldPrevNotes=document.getElementById('oldPrevNotes-'+docketEventId.val()).value;
						var newPrevNotes=document.getElementById('prevNotes-'+docketEventIdKey.val()).value;*/
						var oldComments=document.getElementById('oldComments-'+docketEventId.val()).value;
						var newComments=document.getElementById('comments-'+docketEventIdKey.val()).value;
						
						if(oldCourtResult!=newCourtResult||oldtransferTo!=newnewTransferTo||oldComments!=newComments||oldDisposition!=newDisposition)//||oldPrevNotes!=newPrevNotes||oldHearingType!=newHearingType			  
				  		{
				  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
				  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
						  		document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
				  		}	
				    }
					if(atyConfirm.val()!="YES" && docketType.val()=="COURT")// 
					{
						var oldCourtResult=document.getElementById('oldCourtResult-'+docketEventId.val()).value;
						var newCourtResult=document.getElementById('courtResult-'+docketEventIdKey.val()).value;
						var oldDisposition=document.getElementById('oldDisposition-'+docketEventId.val()).value;
						var newDisposition=document.getElementById('disposition-'+docketEventIdKey.val()).value;
						var oldtransferTo=document.getElementById('oldtransferTo-'+docketEventId.val()).value;
						var newnewTransferTo=document.getElementById('transferTo-'+docketEventIdKey.val()).value;
						var oldActionDate=document.getElementById('oldActionDate-'+docketEventId.val()).value;
						var newActionDate=document.getElementById('actionDate-'+docketEventIdKey.val()).value;
						var oldActionTime=document.getElementById('oldActionTime-'+docketEventId.val()).value;
						var newActionTime=document.getElementById('actionTime-'+docketEventIdKey.val()).value;
						var oldHearingType=document.getElementById('oldResetHearingType-'+docketEventId.val()).value;
						var newHearingType=document.getElementById('hearingType-'+docketEventIdKey.val()).value;
						var oldPrevNotes=document.getElementById('oldPrevNotes-'+docketEventId.val()).value;
						var newPrevNotes=document.getElementById('prevNotes-'+docketEventIdKey.val()).value;
						var oldComments=document.getElementById('oldComments-'+docketEventId.val()).value;
						var newComments=document.getElementById('comments-'+docketEventIdKey.val()).value;			
						/*alert('oldComments='+oldComments)
				    	alert('newComments='+newComments)*/
						if(oldCourtResult!=newCourtResult||oldtransferTo!=newnewTransferTo||oldComments!=newComments||oldDisposition!=newDisposition||oldPrevNotes!=newPrevNotes||oldHearingType!=newHearingType)
					  	{
				  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
				  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
						  		document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
				  		}	
				    }
				/* if(docketType.val()=="DETENTION")
				 {*/
					    /*if(trnValue.val()=="1")
					    {
							$("#TRN-"+docketEventIdKey.val()).prop("checked", true);
							$("#transferTo-"+docketEventIdKey.val()).prop("disabled", false);//
					    }*/
					 //alert(actionDate)
						/*if( actionDate.val()== '')
						 {  
							 $("#TRN-"+docketEventIdKey.val()).prop("disabled", false);
						 }
						 else if ( actionDate.val()!= '')
						 {
							 $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
						 }*/
				 //}
			}
		}//check if docketeventId is defined in case of readonly
	    if($("#courtId").val()=="300")
	    {
				/*if(transferTo.val()!="300")
				{
					datePickerSingleHolidaysMaxDate(actionDate,"Reset Date",false,juvenileDatesList,fmtBackDate);					
				}
				else
					datePickerSingleBackDate($('[name=\'allDktSearchResults['+i+'].actionDate\']'),"Court Date",false,fmtBackDate);*/
	    	datePickerSingleBackDate($('[name=\'allDktSearchResults['+i+'].actionDate\']'),"Court Date",false,fmtBackDate);	    	
	    }
	    else
	    {
	    	datePickerSingleHolidaysMaxDate($('[name=\'allDktSearchResults['+i+'].actionDate\']'),"Court Date",false,juvenileDatesList,fmtBackDate);
	    	/**
			 * e. If TransferCourt is NOT �300� AND if Hearing Type is not �PC�
			 * or �AP�, then run Saturday/Sunday/Holiday check: 1) If date
			 * entered is the SEVENTH day of the week, display �THIS RESET DATE
			 * IS A SATURDAY.� 2) If the date entered is the FIRST day of the
			 * week, displays �THIS RESET DATE IS A SUNDAY. 3) If screen Reset
			 * Date value is on the Holiday Hearing Reset code table, then
			 * display �THIS RESET DATE IS A HOLIDAY�.
			 * 
			 */	    	
			if(docketType.val()=="DETENTION"){
				if(transferTo.val()!="300" && resetHearingType.val()!="PC" && resetHearingType.val()!="AP"){
					datePickerSingleHolidaysMaxDate(actionDate,"Reset Date",false,juvenileDatesList,fmtBackDate);					
				}
			}
	    }
	    
		/*if(courtResult.val()=="TRN"){
			transferTo.prop("disabled",false);
		}else{
			  transferTo.val("");
			  transferTo.prop("disabled",true);
		 }*/
	    
		  if(docketType.val()!="DETENTION")
		  {
			  var districtcourtDecisionsList = $("#districtcourtDecisionsList").val();			 
			  var currVal = courtResult.val();			  
			  $.each(jQuery.parseJSON(districtcourtDecisionsList), function(idx, obj) {
				  if(currVal==obj.code){
					  
					  if(obj.subgroupInd!="R")
					  {
						  $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
						  return false;
					  }
					  else
					  {
						  if( actionDate.val()== '')
							 {  
								 $("#TRN-"+docketEventIdKey.val()).prop("disabled", false);
							 }
							 else
							 {
								 $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
							 }
						  return false;
					  }
				  }
			  });
		  }
		 if(docketType.val()=="DETENTION"){
			 var courtDecisionsList = $("#courtDecisionsList").val();
			  //for detention records, disable the reset date and time,if the reset allowed is N 
			  var currVal = courtResult.val();
			  $("#disposition-"+docketEventIdKey.val()).prop("disabled", true);	
			  $.each(jQuery.parseJSON(courtDecisionsList), function(idx, obj) {
				  if(currVal==obj.code){
					  if(obj.resetAllowed=="N")
					  {
						  actionDate.prop("disabled",true);
						  actionTime.prop("disabled",true);
						  $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
						  return false;
					  }
					  else{
						  actionDate.prop("disabled",false);
						  actionTime.prop("disabled",false);
						  
						  if( actionDate.val()== '')
							 {  
								 $("#TRN-"+docketEventIdKey.val()).prop("disabled", false);
							 }
							 else
							 {
								 $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
							 }
						  return false;
					  }
				  }
			  });
		  }
	} // end of loop
	
	//holiday check calendar.
	var countyHolidayList = $("#holidaysList").val();
	var courtId = $("#courtId").val();
	
	
	//if courtId=300, weekends are holidays can be selected
	$("#courtDate").on("change",function(){
	 var courtID = $("#courtId").val();
	 if (courtID != "300") {
		validateDateByCourtId($("#courtDate").val(),countyHolidayList);
	 }
	});
	$("input[id^='TRN-']").on('change', function (e) {
		  var resultantId = this.id;
	      dktEventId = resultantId.split('-')[1];
	      var result = $("#courtResult-"+dktEventId).val();
	      var docket = $("#docketType-"+dktEventId).val();
	      //alert(dktEventId)
	      if( typeof( paginationPageInput )!="undefined" ){
				localStorage.setItem("currentPageNum", paginationPageInput.value);
			  	 $("#pagerOffset").val($("input[name='pagerSearch']").val());
		 } 
	      if(document.getElementById('TRN-'+dktEventId).checked== true)
		  {
	    	  //alert("checked")
	    	  if( result== '')
	    	  {
	    		  alert("Please select a court result.");
	 				$("#courtResult-"+dktEventId).focus();
	 				$("#TRN-"+dktEventId).prop("checked", false);
	 				return false;
	    	  }
	    	 if(docket=="DETENTION")
	    	 {
				  $("#transferTo-"+dktEventId).val('001');
				  $("#transferTo-"+dktEventId).prop("disabled", false);
				  $("#TRNValue-"+dktEventId).val('1');
	    	 }
	    	 else
	    	 { 
				  $("#transferTo-"+dktEventId).prop("disabled", false);
				  $("#transferTo-"+dktEventId).focus();
				  //$("#resetToDate-"+dktEventId).prop("disabled", false);
				  $("#TRNValue-"+dktEventId).val('1');
	    	 }
		  }
		 if(document.getElementById('TRN-'+dktEventId).checked== false)
		  {
			 //alert("unchecked")
			 $("#transferTo-"+dktEventId).prop("disabled", true);
			 $("#transferTo-"+dktEventId).val('');
			 //$("#resetToDate-"+dktEventId).prop("disabled", true);
			 $("#TRNValue-"+dktEventId).val('0');
		  }			 
    });
	$("select[id^='courtResult']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']')
		if(atyConfirm.val()!="YES")
	    {		
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;	
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	    }	
	});	
	//on change of court result. check for TRN and enable transferTo.
	$("select[id^='courtResult']").on('change', function (e) {
		//court decisions
		var courtDecisionsList = $("#courtDecisionsList").val();
		//var districtcourtDecisionsList = $("#districtcourtDecisionsList").val();
		
		  var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
		  
			var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
			var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
			var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
			
			if(atyConfirm.val()!="YES")
		    {
				var oldCourtResult=document.getElementById('oldCourtResult-'+docketEventId.val()).value;
				var newCourtResult=document.getElementById('courtResult-'+docketEventIdKey.val()).value;				
				
				if(oldCourtResult!=newCourtResult)			  
		  		{
		  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
		  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
						document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
		  		}	
		    }			
		  var transferTo = $('[name=\'allDktSearchResults['+elementIndex+'].transferTo\']');
		  var actionDate = $('[name=\'allDktSearchResults['+elementIndex+'].actionDate\']');
		  var actionTime = $('[name=\'allDktSearchResults['+elementIndex+'].actionTime\']');
		  var originalActionDate = $('[name=\'allDktSearchResults['+elementIndex+'].originalActionDate\']');
		  var originalActionTime = $('[name=\'allDktSearchResults['+elementIndex+'].originalActionTime\']');

		  var docketType = $('[name=\'allDktSearchResults['+elementIndex+'].docketType\']');
		  if($(this).val()=="TRN")
		  {
			alert("Change the Result entry to a reset value and use the checkbox for Transfer instead of the TRN code");//Use the checkbox for transfer instead of the TRN code.
			//return false;
		  }
		  /*if($(this).val()=="TRN"){
			if(typeof transferTo!="undefined"){
			  transferTo.prop("disabled",false);
			}
		  }else{
			  transferTo.val("");
			  transferTo.prop("disabled",true);
		  }*/
		  if(docketType.val()!="DETENTION")
		  {
			  var currVal = $(this).val();			  
			  //add code to clear 
			  /*$("#TRN-"+docketEventIdKey.val()).prop("checked", false);
			  transferTo.val("");
			  actionDate.val("");
			  actionTime.val("");*/
			  $.each(jQuery.parseJSON(districtcourtDecisionsList), function(idx, obj) {
				  if(currVal==obj.code){
					  //alert(obj.subgroupInd)
					  if(obj.subgroupInd!="R")
					  {
						  $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
						  //$("#transferTo-"+docketEventIdKey.val()).prop("disabled", true);
						  return false;
					  }
					  else
					  {
						  if( actionDate.val()== '')
						  { 	
							  $("#TRN-"+docketEventIdKey.val()).prop("disabled", false);
							  //$("#transferTo-"+docketEventIdKey.val()).prop("disabled", false);
						  }
						  else
						  {
							  	$("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
							  	//$("#transferTo-"+docketEventIdKey.val()).prop("disabled", true);
						  }
						  return false;
					  }
				  }
			  });
		  }
		  
		  if(docketType.val()=="DETENTION"){
			  //for detention records, disable the reset date and time,if the reset allowed is N 
			  var currVal = $(this).val();
			  /*$("#TRN-"+docketEventIdKey.val()).prop("checked", false);
			  transferTo.val("");			  
			  actionDate.val("");
			  actionTime.val("");*/
			  $.each(jQuery.parseJSON(courtDecisionsList), function(idx, obj) {
				  if(currVal==obj.code)
				  {
					  if(obj.resetAllowed=="N")
					  {
						  actionDate.val("");
						  actionTime.val("");
						  actionDate.prop("disabled",true);
						  actionTime.prop("disabled",true);
						  $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
						  //$("#transferTo-"+docketEventIdKey.val()).prop("disabled", true);
						  return false;
					  }
					  else
					  {
						  actionDate.prop("disabled",false);
						  actionTime.prop("disabled",false);
						  if( actionDate.val()== '')
						  { 	
							  $("#TRN-"+docketEventIdKey.val()).prop("disabled", false);
							  //$("#transferTo-"+docketEventIdKey.val()).prop("disabled", false);
						  }
						  else
						  {
							   $("#TRN-"+docketEventIdKey.val()).prop("disabled", true);
							   //$("#transferTo-"+docketEventIdKey.val()).prop("disabled", true);
						  }
						  return false;
					  }
				  }
			  });
		  }
	  });
	$("select[id^='disposition']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']')
		if(atyConfirm.val()!="YES")
	    {		
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	    }	
	});	
	$("select[id^='disposition']").on('change', function (e) {
			var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
			var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
			var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
			var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
			if(atyConfirm.val()!="YES")
		    {
				var oldDisposition=document.getElementById('oldDisposition-'+docketEventId.val()).value;
				var newDisposition=document.getElementById('disposition-'+docketEventIdKey.val()).value;	
				if(oldDisposition!=newDisposition)			  
		  		{
		  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
		  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
						document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
		  		}	
		    }
		
	});
	$("input[id^='transferTo']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
		if(atyConfirm.val()!="YES")
	    {
			var oldtransferTo=document.getElementById('oldtransferTo-'+docketEventId.val()).value;
			var newnewTransferTo=document.getElementById('transferTo-'+docketEventIdKey.val()).value;	
			if(oldtransferTo!=newnewTransferTo)			  
	  		{
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  		}	
	    }
	
	});
	$("input[id^='transferTo']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		/*var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');*/
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
		if(atyConfirm.val()!="YES")
	    {
			/*var oldtransferTo=document.getElementById('oldtransferTo-'+docketEventId.val()).value;
			var newnewTransferTo=document.getElementById('transferTo-'+docketEventIdKey.val()).value;	
			if(oldtransferTo!=newnewTransferTo)			  
	  		{*/
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  		//}	
	    }
	
	});
	$("input[id^='actionDate']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']')
		if(atyConfirm.val()!="YES")
	    {		
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	    }	
	});	
	$("input[id^='actionDate']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
		if(atyConfirm.val()!="YES")
	    {
			var oldActionDate=document.getElementById('oldActionDate-'+docketEventId.val()).value;
			var newActionDate=document.getElementById('actionDate-'+docketEventIdKey.val()).value;	
			if(oldActionDate!=newActionDate)			  
	  		{
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  		}	
	    }
	
	});
	$("input[id^='actionTime']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']')
		if(atyConfirm.val()!="YES")
	    {		
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	    }	
	});	
	$("input[id^='actionTime']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
		if(atyConfirm.val()!="YES")
	    {
			var oldActionTime=document.getElementById('oldActionTime-'+docketEventId.val()).value;
			var newActionTime=document.getElementById('actionTime-'+docketEventIdKey.val()).value;
			if(oldActionTime!=newActionTime)			  
	  		{
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  		}	
	    }
		var actionTime = $('[name=\'allDktSearchResults['+elementIndex+'].actionTime\']');
		if(typeof actionTime!="undefined"){
			//Action Time � If populated, hours must be between 6 and 24.  Action Time-Minutes must be between 00 and 59.  
			//If entry outside of listed ranges, display �INVALID TIME ENTERED, PLEASE CORRECT AND RETRY.� Action Time is not valid for Detention settings.
			if(actionTime.val()!=""){
				var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
				var time = actionTime.val();
				var currTime = new Date(0,0,0,time.substring(0,2),time.substring(2,4),0,0).getTime();
				var startTime = new Date(0,0,0,06,00,0,0).getTime(); //6 am
				var endTime = new Date(0,0,0,24,00,0,0).getTime(); //24 pm
			 
				var value = timeFormatRegex.test(actionTime.val().trim())
				if(value==false ||(currTime<startTime || currTime>=endTime) ){
					alert("Invalid Time Entered, Please Correct And Retry.");
					actionTime.focus();
					return false;
				}
			}
		  }
	});
	$("select[id^='hearingType']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']')
		if(atyConfirm.val()!="YES")
	    {		
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	    }	
	});	
	$("select[id^='hearingType']").on('change', function (e) {		
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
		if(atyConfirm.val()!="YES")
	    {
			var oldHearingType=document.getElementById('oldResetHearingType-'+docketEventId.val()).value;
			var newHearingType=document.getElementById('hearingType-'+docketEventIdKey.val()).value;				
			if(oldHearingType!=newHearingType)			  
	  		{
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  		}	
	    }
	
	});
	$("input[id^='prevNotes']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
		if(atyConfirm.val()!="YES")
	    {
			var oldPrevNotes=document.getElementById('oldPrevNotes-'+docketEventId.val()).value;
			var newPrevNotes=document.getElementById('prevNotes-'+docketEventIdKey.val()).value;
			if(oldPrevNotes!=newPrevNotes)			  
	  		{
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  		}	
	    }
	
	});
	$("input[id^='prevNotes']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']')
		if(atyConfirm.val()!="YES")
	    {		
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	    }
	
	});
	$("input[id^='comments']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']');
		if(atyConfirm.val()!="YES")
	    {
			var oldComments=document.getElementById('oldComments-'+docketEventId.val()).value;
			var newComments=document.getElementById('comments-'+docketEventIdKey.val()).value;
			if(oldComments!=newComments)			  
	  		{
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  		}	
	    }
	
	});
	$("input[id^='comments']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var atyConfirm= $('[name=\'allDktSearchResults['+elementIndex+'].atyConfirmation\']')
		if(atyConfirm.val()!="YES")
	    {		
	  			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
	  			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	    }
	
	});
	$("input[id^='attorneyName']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var oldAttorney=document.getElementById('oldAttorney-'+docketEventId.val()).value;
		var newAttorney=document.getElementById('attorneyName-'+docketEventIdKey.val()).value;		
		if(oldAttorney!=newAttorney)			  
	  	{
			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
				document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  	}
	});
	$("input[id^='barNum']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');		
		document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
		if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
			document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	});
	$("input[id^='HATattorneyConnection']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');		
		document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;	
		if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
			document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	});
	$("input[id^='AATattorneyConnection']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');		
		document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
		if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
			document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	});
	$("input[id^='PDOattorneyConnection']").on('click', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');		
		document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
		if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
			document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	});
	
	$("input[id^='HATattorneyConnection']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var oldattorneyConn=document.getElementById('oldAttorneyConn-'+docketEventId.val()).value;
		var newAttorneyConn;
		  if(document.getElementById('HATattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='HAT';
		  }
		  else if(document.getElementById('AATattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='AAT';
		  }
		  else if(document.getElementById('PDOattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='PDO';
		  }	
		  else
		  {
			  newAttorneyConn='';
		  }
		if(oldattorneyConn!=newAttorneyConn)			  
	  	{
			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
				document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  	}   
	
	});
	$("input[id^='AATattorneyConnection']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var oldattorneyConn=document.getElementById('oldAttorneyConn-'+docketEventId.val()).value;
		var newAttorneyConn;
		  if(document.getElementById('HATattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='HAT';
		  }
		  else if(document.getElementById('AATattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='AAT';
		  }
		  else if(document.getElementById('PDOattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='PDO';
		  }
		  else
		  {
			  newAttorneyConn='';
		  }
		if(oldattorneyConn!=newAttorneyConn)			  
	  	{
			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
				document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  	}   
	
	});
	$("input[id^='PDOattorneyConnection']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var oldattorneyConn=document.getElementById('oldAttorneyConn-'+docketEventId.val()).value;
		var newAttorneyConn;
		  if(document.getElementById('HATattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='HAT';
		  }
		  else if(document.getElementById('AATattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='AAT';
		  }
		  else if(document.getElementById('PDOattorneyConnection.'+docketEventIdKey.val()).checked== true)
		  {
			  newAttorneyConn='PDO';
		  }
		  else
		  {
			  newAttorneyConn='';
		  }
		if(oldattorneyConn!=newAttorneyConn)			  
	  	{
			document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=false;
			if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
				document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=false;
	  	}   
	
	});
	$("input[id^='appAttorney']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var newappAttorney;
		if(document.getElementById('appAttorney.'+docketEventIdKey.val()).checked== true)
		{
			newappAttorney="1";
		} 
		else 
		{
			newappAttorney="0";
		}
		document.getElementById('appAttorney-'+docketEventIdKey.val()).value=newappAttorney;		
	});
	$("input[id^='interpreter']").on('change', function (e) {
		var elementName = $(this).attr('name')
		var elementIndex = elementName.split('[')[1].split("]")[0];
		var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		var docketEventId=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventId\']');
		var newinterpreter;
		if(document.getElementById('interpreter.'+docketEventIdKey.val()).checked== true)
		{
			newinterpreter="1";
		} 
		else 
		{
			newinterpreter="0";
		}
		document.getElementById('interpreter-'+docketEventIdKey.val()).value=newinterpreter;		
	});
	/*//do the same for disposition.
	$("select[id^='disposition']").on('change', function (e) {
		//court decisions
		var courtDecisionsList = $("#courtDecisionsList").val();
		  var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
		  var transferTo = $('[name=\'allDktSearchResults['+elementIndex+'].transferTo\']');
		  var actionDate = $('[name=\'allDktSearchResults['+elementIndex+'].actionDate\']');
		  var actionTime = $('[name=\'allDktSearchResults['+elementIndex+'].actionTime\']');
		  var docketType = $('[name=\'allDktSearchResults['+elementIndex+'].docketType\']');
		  
		  if($(this).val()=="TRN"){
			if(typeof transferTo!="undefined"){
			  transferTo.prop("disabled",false);
			}
		  }else{
			  transferTo.prop("disabled",true);
		  }

		  if(docketType.val()=="DETENTION"){
			  //for detention records, disable the reset date and time,if the reset allowed is N 
			  var currVal = $(this).val();
			  $.each(jQuery.parseJSON(courtDecisionsList), function(idx, obj) {
				  if(currVal==obj.code){
					  if(obj.resetAllowed=="N")
					  {
						  actionDate.prop("disabled",true);
						  actionTime.prop("disabled",true);
						  return false;
					  }else{
						  actionDate.prop("disabled",false);
						  actionTime.prop("disabled",false);
						  return false;
					  }
				  }
			  });
		  }
	  });*/
	
	// on click of validate bar number
	  /*$("input[id^='actionTime']").on('change', function (e) {
		  var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
		  var actionTime = $('[name=\'allDktSearchResults['+elementIndex+'].actionTime\']');
		  
		  if(typeof actionTime!="undefined"){
			//Action Time � If populated, hours must be between 6 and 24.  Action Time-Minutes must be between 00 and 59.  
			//If entry outside of listed ranges, display �INVALID TIME ENTERED, PLEASE CORRECT AND RETRY.� Action Time is not valid for Detention settings.
			if(actionTime.val()!=""){
				var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
				var time = actionTime.val();
				var currTime = new Date(0,0,0,time.substring(0,2),time.substring(2,4),0,0).getTime();
				var startTime = new Date(0,0,0,06,00,0,0).getTime(); //6 am
				var endTime = new Date(0,0,0,24,00,0,0).getTime(); //24 pm
			 
				var value = timeFormatRegex.test(actionTime.val().trim())
				if(value==false ||(currTime<startTime || currTime>=endTime) ){
					alert("Invalid Time Entered, Please Correct And Retry.");
					actionTime.focus();
					return false;
				}
			}
		  }
	  });*/
	
	  // on click of validate bar number
	  $("input[id^='validateBarNumber']").on('click', function (e) {
		  var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
		  var barNum = $('[name=\'allDktSearchResults['+elementIndex+'].barNum\']');
		  var attName = $('[name=\'allDktSearchResults['+elementIndex+'].attorneyName\']');		  
		  var docketEventIdKey=$('[name=\'allDktSearchResults['+elementIndex+'].docketEventIdKey\']');
		  //var attConnctn = $('[name=\'allDktSearchResults['+elementIndex+'].attorneyConnection\']');
		  var attConnctn =$("#attorneyConnection."+docketEventIdKey.val());
		  if(typeof barNum!="undefined")
		  {
			  if(barNum.val()=="")
			  {		  		 
				  attName.val("");				  
				  if(document.getElementById('HATattorneyConnection.'+docketEventIdKey.val()).checked== true)
				  {
					  document.getElementById('HATattorneyConnection.'+docketEventIdKey.val()).checked=false;
				  }
				  if(document.getElementById('AATattorneyConnection.'+docketEventIdKey.val()).checked== true)
				  {
					  document.getElementById('AATattorneyConnection.'+docketEventIdKey.val()).checked=false;
				  }
				  if(document.getElementById('PDOattorneyConnection.'+docketEventIdKey.val()).checked== true)
				  {
					  document.getElementById('PDOattorneyConnection.'+docketEventIdKey.val()).checked=false;
				  }
				  //alert("Cleared attorney name and attorney connection");
				  //return false;
		  	  }
			  
			  if(barNum.val()!="" && $.isNumeric(barNum.val().trim())==false)
			  {
		  			alert("Bar Number Is Numeric. Please Correct And Retry.");
		  			barNum.focus();
		  			return false;
		  	}
			 
		  }
		  
		  $("#tempDocketEventIdKey").val('');
		  $("#tempBarNumber").val('');
		  
		  var buttonId = this.id;
		  var docketEventIdKey = buttonId.split('.')[1];
		
		 
		  $("#tempBarNumber").val(barNum.val());
		  $("#tempDocketEventIdKey").val(docketEventIdKey); //set it in the form
		  
		  if ( true ) {
			  spinner();
		  }
		  
		  $('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Validate&barNum="+barNum.val());
		  $('form').submit();
	  });	
	  
	  // on click of validate bar number
	  $("input[id^='validateGalBarNum']").on('click', function (e) {
		  var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
		  var barNum = $('[name=\'allDktSearchResults['+elementIndex+'].galBarNum\']');
		  if(typeof barNum!="undefined"){
			  if(barNum.val()==""){
				  galName.val("");
		  		  /*alert("Please Enter A Value To Validate Bar Number. Please Correct And Retry.");
		  		  barNum.focus();
		  		  return false;*/
		  	  }
			  
			  if(barNum.val()!="" && $.isNumeric(barNum.val().trim())==false){
		  			alert("Bar Number Is Numeric. Please Correct And Retry.");
		  			barNum.focus();
		  			return false;
		  		}
		  }
		  
		  $("#tempDocketEventIdKey").val('');
		  $("#tempBarNumber").val('');
		  
		  var buttonId = this.id;
		  var docketEventIdKey = buttonId.split('.')[1];
		
		 
		  $("#tempBarNumber").val(barNum.val());
		  $("#tempDocketEventIdKey").val(docketEventIdKey); //set it in the form
		  
		  if ( true ) {
			  spinner();
		  }
		  
		  $('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=ValidateAdlitem&barNum="+barNum.val());
		  $('form').submit();
	  });	
	// on click of validate bar number for aty2
	  $("input[id^='validate2ndAttorneyBarNum']").on('click', function (e) {
		  var elementName = $(this).attr('name')
		  var elementIndex = elementName.split('[')[1].split("]")[0];
		  var barNum = $('[name=\'allDktSearchResults['+elementIndex+'].attorney2BarNum\']');
		  if(typeof barNum!="undefined"){			  
			  if(barNum.val()!="" && $.isNumeric(barNum.val().trim())==false){
		  			alert("Bar Number Is Numeric. Please Correct And Retry.");
		  			barNum.focus();
		  			return false;
		  		}
		  }
		  
		  $("#tempDocketEventIdKey").val('');
		  $("#tempBarNumber").val('');
		  
		  var buttonId = this.id;
		  var docketEventIdKey = buttonId.split('.')[1];
		
		 
		  $("#tempBarNumber").val(barNum.val());
		  $("#tempDocketEventIdKey").val(docketEventIdKey); //set it in the form
		  
		  if ( true ) {
			  spinner();
		  }
		  
		  $('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Validate Aty2 Barnum&barNum="+barNum.val());
		  $('form').submit();
	  });
	  
	  //on click of search button
	  $("input[name^='searchAttorney']").on('click', function (e) {
		 
		 var elementName = $(this).attr('name')
		 var elementIndex = elementName.split('[')[1].split("]")[0];
		 var searchAttorney = $('[name=\'allDktSearchResults['+elementIndex+'].searchAttorney\']');
		 $("#action").val("searchAttorney");  
  	     $("#tempDocketEventIdKey").val('');
		 var buttonId = this.id;
  	     var docketEventIdKey = buttonId.split('.')[1];
  	     $("#tempDocketEventIdKey").val(docketEventIdKey); //set it in the form
  	 
		 $('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Search');
	  	 $('form').submit();
	  	
	  });
	
	//on click of attorney Confirm button
	  $("input[name^='attorneyConfirm']").on('click', function (e) {
		 
			var elementName = $(this).attr('name');
		  	var allValid = true;
			var elementIndex = elementName
					.split('[')[1].split("]")[0];
			var docketEventId = $('[name=\'allDktSearchResults['
					+ elementIndex
					+ '].docketEventId\']');
			var docketEventIdK = $('[name=\'allDktSearchResults['
					+ elementIndex
					+ '].docketEventIdKey\']');
		 //alert(docketEventId.val())
		 var attorneyConn;
		 var buttonId = this.id;
  	     var docketEventIdKey = buttonId.split('.')[1];
  	     if(document.getElementById('HATattorneyConnection.'+docketEventIdKey).checked== true)
		  {
			 attorneyConn='HAT';
		  }
		 else if(document.getElementById('AATattorneyConnection.'+docketEventIdKey).checked== true)
		  {
			  attorneyConn='AAT';
		  }
		 else if(document.getElementById('PDOattorneyConnection.'+docketEventIdKey).checked== true)
		  {
			  attorneyConn='PDO';
		  }
		 else
		 {
			 attorneyConn='';
		 }
  	 //task 184038
 	 	var districtcourtDecisionsList = $("#districtcourtDecisionsList").val();
 	 	var result = $("#courtResult-"+docketEventIdK.val()).val();	 	
 	 	var actionDate = $("#actionDate-"+docketEventIdK.val()).val();
 		var actionTime = $("#actionTime-"+docketEventIdK.val()).val();
 		var juvNum=$("#juvenileNumber-"+docketEventId.val()).val();
 	 	//alert(result+""+actionDate+""+actionTime)
 		  $.each(jQuery.parseJSON(districtcourtDecisionsList), function(idx, obj) {
 			  if(result==obj.code)
 			  {
 				  if(obj.optionCode=="R")
 				  {
 					  if( actionDate== ''||actionTime== '')
 					  { 	
 						  alert("Please enter reset date and time for JUV - "+juvNum+".");
 						  //return false;
 						  allValid=false;
 						  //break;						  
 					  }					  
 				  }
 			  }
 		  }); 		  
 		//task 184038
 		 if (allValid)
		  {
 			 spinner(); 
 			$('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Attorney Confirmation&docketEventId='+docketEventId.val()+'&coc='+attorneyConn);
 		  	$('form').submit();
		  } 		 
		 	  	
	  });
	//on click of attorney bypass button
	  $("input[name^='attorneyBypass']").on('click', function (e) {
		 
		  	var elementName = $(this).attr('name');
		  	var allValid = true;
			var elementIndex = elementName
					.split('[')[1].split("]")[0];
			var docketEventId = $('[name=\'allDktSearchResults['
					+ elementIndex
					+ '].docketEventId\']');
			var docketEventIdKey = $('[name=\'allDktSearchResults['
					+ elementIndex
					+ '].docketEventIdKey\']');

			var newAttorneyConn;			
			if (document
					.getElementById('HATattorneyConnection.'
							+ docketEventIdKey
									.val()).checked == true) {
				newAttorneyConn = 'HAT';
			} else if (document
					.getElementById('AATattorneyConnection.'
							+ docketEventIdKey
									.val()).checked == true) {
				newAttorneyConn = 'AAT';
			} else if (document
					.getElementById('PDOattorneyConnection.'
							+ docketEventIdKey
									.val()).checked == true) {
				newAttorneyConn = 'PDO';
			} else {
				newAttorneyConn = '';
			}
			
			 var oldattorneyName =$("#oldAttorney-"+docketEventId.val()).val();
			 var attorneyName =$("#attorneyName-"+docketEventIdKey.val()).val();
			 var oldattorneyConn = document
				.getElementById('oldAttorneyConn-'
						+ docketEventId.val()).value;										 
			 if(oldattorneyName!=attorneyName||newAttorneyConn!=oldattorneyConn) 
			 { 
				 alert("ATTORNEY DETAILS ARE MODIFIED,PLEASE CONFIRM ATTORNEY."); 
				 allValid=false; 
			 }		 
		
	 	 //task 184038
	 	var districtcourtDecisionsList = $("#districtcourtDecisionsList").val();
	 	var result = $("#courtResult-"+docketEventIdKey.val()).val();	 	
	 	var actionDate = $("#actionDate-"+docketEventIdKey.val()).val();
		var actionTime = $("#actionTime-"+docketEventIdKey.val()).val();
		var juvNum=$("#juvenileNumber-"+docketEventId.val()).val(); //document.getElementById('juvenileNumber-'+docketEventId.val()).value;
		
	 	//alert(result+""+actionDate+""+actionTime)
		  $.each(jQuery.parseJSON(districtcourtDecisionsList), function(idx, obj) {
			  if(result==obj.code)
			  {
				  if(obj.optionCode=="R")
				  {
					  if( actionDate== ''||actionTime== '')
					  { 	
						  alert("Please enter reset date and time for JUV - "+juvNum+".");
						  //return false;
						  allValid=false;
						  //break;						  
					  }					  
				  }
			  }
		  });
		  if (allValid)
		  {
			  if(document.getElementById('attnyBypass.'+docketEventIdKey.val()))
			  {
					document.getElementById('atyBypass-'+docketEventIdKey.val()).value='YES';
					document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled=true;
			  }
			  document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled=true;
			  spinner();	 	  
	 		  $('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Attorney Bypass&docketEventId='+docketEventId.val());
	 		  $('form').submit();
		  }
		//task 184038
	 	  
	 	  	 	  
	  });
	//on click of search button
	  $("input[name^='search2ndAttorney']").on('click', function (e) {
		 
		 var elementName = $(this).attr('name')
		 var elementIndex = elementName.split('[')[1].split("]")[0];
		 //var searchAttorney = $('[name=\'allDktSearchResults['+elementIndex+'].search2ndAttorney\']');
		 $("#action").val("search2ndAttorney");  
  	     $("#tempDocketEventIdKey").val('');
		 var buttonId = this.id;
  	     var docketEventIdKey = buttonId.split('.')[1];
  	     $("#tempDocketEventIdKey").val(docketEventIdKey); //set it in the form
  	 
		 $('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Search Aty2');
	  	 $('form').submit();
	  	
	  });
	  //on click of search gal button
	  $("input[name^='searchGAL']").on('click', function (e) {
		 
		 var elementName = $(this).attr('name')
		 var elementIndex = elementName.split('[')[1].split("]")[0];
		 var searchAttorney = $('[name=\'allDktSearchResults['+elementIndex+'].galName\']');
		 $("#action").val("searchAttorney");  
  	     $("#tempDocketEventIdKey").val('');
		 var buttonId = this.id;
  	     var docketEventIdKey = buttonId.split('.')[1];
  	     $("#tempDocketEventIdKey").val(docketEventIdKey); //set it in the form
  	 
		 $('form').attr('action','/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Search GAL');
	  	 $('form').submit();
	  	
	  });
	  
	
	//Go button.
	$("#goBtn").on('click', function (e) {
		//reset pager.offset to "" for new search not to 0.
		var ps = document.getElementsByName("pager.offset");
		var elementIndex = $(ps).val();
		if(typeof elementIndex != "undefined"){
			document.getElementsByName("pager.offset")[0].value ="";	
		}
		
		$("[id^=results]").hide();
		var validCourts=["313","314","300","315","001"];
		if($.inArray($("#courtId").val(),validCourts)==-1){
			   alert("Invalid Court ID. Please Correct and Retry.");
				$("#courtId").val('');
				$("#courtId").focus();
				return false;
			}
	    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=GO");
			$('form').submit();
			$(this).prop('disabled', true);
			spinner();
			return false;
	});
	
	$("#updateBtn").on('click', function (e) {
		//add code to check if attorney confirmed
		var startIndex;
		var endIndex;
		var noOfElements;
		var ps = document.getElementsByName("pager.offset");
		var elementIndex = $(ps).val();
		var searchResultSize = $("#searchResultSize").val();
		var allValid = true;		
		startIndex= elementIndex
		noOfElements =  3
		
		var pageNumber = (parseInt(startIndex) / 3) + 1;
	    var lastPageItems = searchResultSize%3;
	    var totalPages = parseInt((searchResultSize/3) + 1);
		if(pageNumber == totalPages){
			noOfElements = lastPageItems
			}
		endIndex = parseInt(startIndex) + parseInt(noOfElements); 
		//add code to check if attorney bypassed
		//loop through each index page. 3 records per page.
		for (var i=startIndex; i <endIndex; i++) 
		{
			var docketType=$('[name=\'allDktSearchResults['+i+'].docketType\']');
			if(docketType.val()!="ANCILLARY")
			{				
				var docketEventId=$('[name=\'allDktSearchResults['+i+'].docketEventId\']');			
				var docketEventIdKey=$('[name=\'allDktSearchResults['+i+'].docketEventIdKey\']');
				var juvNum=document.getElementById('juvenileNumber-'+docketEventId.val()).value;
				var hidden=$("#atyBypass-"+docketEventIdKey.val());
				if(typeof (hidden.val())!="undefined")
				{
						if(document.getElementById('attnyBypass.'+docketEventIdKey.val()).disabled==false)
						{
							alert('Please CONFIRM or BYPASS the attorney for JUV - '+juvNum+'.');				
							//return false;	
							allValid=false;
						}
				}
				else
				{
					if(document.getElementById('attnyConfm.'+docketEventIdKey.val()).disabled==false)
					{
						alert('Please confirm the attorney for JUV - '+juvNum+'.');				
						//return false;	
						allValid=false;
					}
				}
			
				var districtcourtDecisionsList = $("#districtcourtDecisionsList").val();
			 	var result = $("#courtResult-"+docketEventIdKey.val()).val();	 	
			 	var actionDate = $("#actionDate-"+docketEventIdKey.val()).val();
				var actionTime = $("#actionTime-"+docketEventIdKey.val()).val();	
				/*var result=document.getElementById('courtResult-'+docketEventIdKey.val()).value;
				var actionDate=document.getElementById('actionDate-'+docketEventIdKey.val()).value;
				var actionTime=document.getElementById('actionTime-'+docketEventIdKey.val()).value;*/
			 	//alert(docketEventIdKey.val()+""+actionDate+""+actionTime)
				  $.each(jQuery.parseJSON(districtcourtDecisionsList), function(idx, obj) {
					  if(result==obj.code)
					  {
						  if(obj.optionCode=="R")
						  {
							  if( actionDate== ''||actionTime== '')
							  { 	
								  alert("Please enter reset date and time for JUV - "+juvNum+".");
								  //return false;
								  allValid=false;
								  //break;						  
							  }					  
						  }
					  }
				  });
			}
			
		}
		/*if ( true ) {
			spinner();
		}*/
		if (allValid)
		{	
			spinner();
	    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Submit");
			$('form').submit();
			$(this).prop('disabled', true);
			return false;
		}
    });

	if($("#deleteFlag").val()=="true"){
		var originalActionDate = $("#deleteSettingDate").val();
		var dktEventId = $("#tempDocketId").val();
		var petitionIdKey =$("#petitionNum-"+dktEventId).val();
		 
		confirmDialog("Delete Setting For "+originalActionDate+" AND Petition "+petitionIdKey+" ? CONFIRM OR CANCEL Deletion");
	}
	
	$("#deleteBtn").on('click', function (e) {
	/*	var result;
		if($("[id^=deleteSetting]").is(':checked')!=true){
			alert("Please Select A Setting, To Delete A Setting.");
			$("[id^=deleteSetting]").focus();
			return false;
		}
		$("[id^=deleteSetting]:checked").each(function(){
			var currdocketEventIdKey = $(this).attr("id").split("-")[1];  
			var currActionDate = $("#courtDate-"+currdocketEventIdKey).val();
			if ( true ){
				spinner();
			}
			$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Delete Setting");
    		$('form').submit();
			
		});*/
		$("[id^=deleteSetting]").prop('disabled', false);
		
	});
	
	//select one record at a time . Check box.
	//delete functionality
	$("[id^=deleteSetting]").bind('change', function() {
		$("[id^=deleteSetting]:checked").each(function(){
			var currdocketEventIdKey = $(this).attr("id").split("-")[1]; 
			
			$("#tempDocketId").val(currdocketEventIdKey);
			var currActionDate = $("#courtDate-"+currdocketEventIdKey).val();
			if ( true ){
				spinner();
			}
			$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Delete Setting");
    		$('form').submit();
			
		})
		$("[id^=deleteSetting]").not(this).prop('checked', false);  
	});
	 
	 $("#courtMainMenuBtn").on('click', function (e) {
		 	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Court Main Menu");
				$('form').submit();
	  });
    
	//default to submit button on enter
	//enter key		 
	  $(document).bind("keydown", function(event) {
		  if(event.which == 13) {
			 // event.preventDefault();			  
			  if (($('input:focus').length == 0 && $('select:focus').length == 0 && $('textarea:focus').length == 0)){			  
		    	$("#updateBtn").click();
		      }
		  }
	  });
	  
	  //**********************************************//FUNCTIONS//**********************************************************************************************
	  
	 /* function confirmDialog(message) {
		    confirmation = window.confirm(message);
		    if(confirmation){
		    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Delete Setting");
    			$('form').submit();
    			$(this).prop('disabled', true);
		    } else {
		    	$("#deleteFlag").val("")
		    }
		};*/
	  function confirmDialog(message) {
		    confirmation = window.confirm(message);
		    if(confirmation){
		    	if ( true ){
					spinner();
				}
		    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Delete Setting");
  			$('form').submit();
  			$(this).prop('disabled', true);
		    } else {
		    	
		    	$("#deleteFlag").val("");
		    	//submit the flag to action
		    	$('form').attr('action',"/JuvenileCasework/submitJuvenileDistrictCourtHearings.do?submitAction=Set Deletflag");
	  			$('form').submit();
	  			$(this).prop('disabled', true);
		    	
		    }
		};	
	//on error position the cursor.
	$(cursorPosition).focus();
});