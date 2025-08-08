$(document).ready(function () {
	//Court Date
	
	var cursorPosition = $("#cursorPosition").val();

	if(typeof $("#date") != "undefined"){
		datePickerSingle($("#date"),"Date",false);
	}
	
	
	if(typeof $("#resetToDate") != "undefined"){
		datePickerSingle($("#resetToDate"),"ResetTo",false);
	}
	
	
	//required fields validation
	$("#btnCrtAction,#btnCrtDocket").on("click",function(){
		//Can be a hearing Date\court date
		if(typeof $("#date") != "undefined"){
			if($("#date").val()==""){
				alert("Date is required. Please select a date.");
				$("#date").focus();
				return false;
			}
		}
		//facility
		if(typeof $("#facility") != "undefined"){
			if($("#facility").val()=="")
			{
				alert("Facility is required.Please select a facility.");
				$("#facility").focus();
				return false;
			}
		}
		if ( true ) {
			spinner();
		}
	});
	
	//Focus on Bar number
	if(cursorPosition=="barNum"){		
		 var docketEvtId=  $("#tempDocketEventId").val();
		 $("#barNum-"+docketEvtId).focus();
	}
	
	  // on click of validate bar number
	  $("input[id^='validateBarNumber']").on('click', function (e) {
		//  e.preventDefault();		  
		  $("#tempDocketEventId").val('');
		  $("#tempBarNumber").val('');
		
		  var buttonId = this.id;
		  var docketEventId = buttonId.split('.')[1];		
		  if($("#barNum-"+docketEventId).val()==""){
	  		  alert("Please Enter A Value To Validate Bar Number. Please Correct And Retry.");
	  		  $("#barNum-"+docketEventId).focus();
	  		  return false;
	  	  }
		  
		  if($("#barNum-"+docketEventId).val()!="" && $.isNumeric($("#barNum-"+docketEventId).val().trim())==false){
	  			alert("Bar Number Is Numeric. Please Correct And Retry.");
	  			$("#barNum-"+docketEventId).focus();
	  			return false;
	  		}
		  if ( true ) {
			  spinner();
		  }
		  $("#tempBarNumber").val($("#barNum-"+docketEventId).val());
		  $("#tempDocketEventId").val(docketEventId); //set it in the form		 
		  $('form').attr('action',"/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Validate");
		  $('form').submit();
		  
		  
	  });	
	  
	
	$("#printDocketBtn").on("click",function(){
	//docketTime
	if(typeof $("#docketTime") != "undefined"){
		if($("#docketTime").val()=="")
		{
			alert("Docket Time is required.Please enter a valid Time.");
			$("#docketTime").focus();
			return false;
		}else{
			var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
			if(timeFormatRegex.test($("#docketTime").val())== false){
 			  alert("Please enter Docket Time in HHmm format.")
 			  $("#docketTime").focus();
 			  return false;
			}
		}
		
		if ( true ) {
			spinner();
		}
	}	
	});
	
	  //on click of search button
	  $("input[id^='searchAttorney']").on('click', function (e) {
	     $("#tempDocketEventId").val('');
		 var buttonId = this.id;
	     var docketEventId = buttonId.split('.')[1];
	     
	     $("#tempAttorneyName").val($("#attorneyName-"+docketEventId).val());
	     $("#tempDocketEventId").val(docketEventId); //set it in the form
	     spinner();
		 $('form').attr('action','/JuvenileCasework/handleJuvenileSearchDetentionHearings.do?submitAction=Find Attorney');
	  	 $('form').submit();
	  	 
	  });
	
});