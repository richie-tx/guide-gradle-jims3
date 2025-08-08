$(document).ready(function () {	
	  var action = $("#action").val();
	  var flag = 0;
		if(action=="initialSetting"){
			$("#juvNum").focus();
		}else if(action=="courtMainMenu"){
			$("#courtId").focus();
		}
		var countyHolidayList = $("#holidaysList").val();
		if(typeof $("#courtDate").val() != "undefined"){
			//datePickerSingleHolidays($("#courtDate"),"Court Date",false,countyHolidayList); //restricted dates list (excludes weekends and holidays)
			  datePickerSingle($("#courtDate"),"Court Date",false);
		}
		//if courtId=300, weekends are holidays can be selected
		$("#courtDate").on("change",function(){
		 var courtID = $("#courtId").val();
		 if (courtID != "300") {
			validateDateByCourtId($("#courtDate").val(),countyHolidayList);
		 }
		});
		
		$("#courtId").on("change",function(){
			 var courtID = $("#courtId").val();
			 if ($("#courtDate").val() != " ") { //
				validateDateByCourtId($("#courtDate").val(),countyHolidayList);
			 }
			});

	// on load hide the following
	$("#reqCourtId").hide();
	$("#reqDate").hide();
	$("#reqRef").hide();
	$("#reqJuvNum").hide();
	
	//validation for court action and court docket.
	$("#submitCourtAction").on('click', function (e) {
		var courtId = $("#courtId").val();
    	var courtDate = $("#courtDate").val();    	
    	if(courtId=="" && courtDate==""){ //courtId and Date
    		alert("Court Id and Date are required.");
    		$("#reqDate").show();
    		$("#reqCourtId").show();
    		$("#reqJuvNum").hide();
    		$("#reqRef").hide();
    		$("#courtId").focus();
    		return false;
    	}else if(courtId==""){ //courtID
    		alert("Court Id is required.");
    		$("#reqDate").show();
    		$("#reqCourtId").show();
    		$("#reqJuvNum").hide();
    		$("#reqRef").hide();
    		$("#courtId").focus();
    		return false;
    	}else if(courtDate==""){ //Date
    		alert("Date is required.");
    		$("#reqDate").show();
    		$("#reqCourtId").show();
    		$("#reqJuvNum").hide();
    		$("#reqRef").hide();
    		$("#courtDate").focus();
    		return false;
    	}
    	
    	if( true ){
    		spinner();
    	}
    });
	$("#submitCourtDocket").on('click', function (e) {		
		if(flag==0)
		{
			var courtId = $("#courtId").val();
	    	var courtDate = $("#courtDate").val();    	
	    	if(courtId=="" && courtDate==""){ //courtId and Date
	    		alert("Court Id and Date are required.");
	    		$("#reqDate").show();
	    		$("#reqCourtId").show();
	    		$("#reqJuvNum").hide();
	    		$("#reqRef").hide();
	    		$("#courtId").focus();
	    		return false;
	    	}else if(courtId==""){ //courtID
	    		alert("Court Id is required.");
	    		$("#reqDate").show();
	    		$("#reqCourtId").show();
	    		$("#reqJuvNum").hide();
	    		$("#reqRef").hide();
	    		$("#courtId").focus();
	    		return false;
	    	}else if(courtDate==""){ //Date
	    		alert("Date is required.");
	    		$("#reqDate").show();
	    		$("#reqCourtId").show();
	    		$("#reqJuvNum").hide();
	    		$("#reqRef").hide();
	    		$("#courtDate").focus();
	    		return false;
	    	}
	    	
	    	if( true ){
	    		spinner();
	    	}
		}
    });
	$("#submitRefDisp").on('click', function (e) {
		var juvNum = $("#juvNum").val();
	 	if(juvNum=="" ){ //juvnum
    		alert("Juvenile Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").hide();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
	 	}
	 	if( true ){
    		spinner();
    	}
	 	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=masterReferralDisplay');
		$('form').submit();
	});
    
    $("#submitInitialSetting,#submitPetitionUpdate").on('click', function (e) {
    	var juvNum = $("#juvNum").val();
    	var refNum = $("#refNum").val();
    	
    	if(juvNum=="" && refNum==""){ //ref and juvnum
    		alert("Juvenile Number and Referral Number are required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
    	}else if(juvNum==""){ //juvNum
    		alert("Juvenile Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
    	}else if(refNum==""){ //refNum
    		alert("Referral Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#refNum").focus();
    		return false;
    	}
    	if(juvNum.trim().length<6){
    		alert("Invalid Juvenile Number.");
    		$("#juvNum").focus();
    		return false;
    	}
    	if(refNum.trim().length<4){
    		alert("Invalid Referral Number.");
    		$("#refNum").focus();
    		return false;
    	}
    	
    	//juvenile Number
    	if(Math.floor(juvNum) != juvNum || $.isNumeric(juvNum)==false) {
    		alert("Invalid Juvenile Number.");
    		$("#juvNum").focus();
    		return false;
    	}
    	
    	// referral no has to be an integer
    	if(Math.floor(refNum) != refNum || $.isNumeric(refNum)==false) {
    		  alert("Invalid Referral Number.");
    		  $("#refNum").focus();
    		  return false;
    	}
    	
    	 if ( true ) {
    		 spinner();
    	 }
    });
    $("#submitAncillaryCrtAct").on('click', function (e) {
    	var juvNum = $("#juvNum").val();
    	if(juvNum==""){ //juvNum
    		alert("Petition Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").hide();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
    	}
    	
    	if ( true ) {
   		 spinner();
   	 	}
    });
    
    $("#submitReferralSearch").on('click', function (e) {
    	var juvNum = $("#juvNum").val();
    	if(juvNum==""){ //juvNum
    		alert("Juvenile Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").hide();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
    	}
    	else
    		{
    		// add numeric validation
	    		if(Math.floor(juvNum) != juvNum || $.isNumeric(juvNum)==false) {
	        		alert("Invalid Juvenile Number.");
	        		$("#juvNum").focus();
	        		return false;
	        	}    		
    		}
    	if ( true ) {
   		 spinner();
   	 	}
    });
    $("#submitNameSearch").on('click', function (e) {
    	spinner();
    });
       
    $("#submitNumberInquiry").on('click', function (e) {
    	var juvNum = $("#juvNum").val();
    	var refNum = $("#refNum").val();
    	
    	if(juvNum=="" && refNum==""){ //ref and juvnum
    		alert("Petition Number and Search Type are required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
    	}else if(juvNum==""){ //juvNum
    		alert("Petition Or Investigation Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
    	}else if(refNum==""){ //refNum
    		alert("Type Code is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#refNum").focus();
    		return false;
    	}
    	
    	var type = refNum.toLowerCase();
    	var lastChar = juvNum[juvNum.length -1];
    	if( "pet" == type && "j" != lastChar.toLowerCase() ){
    		
    		juvNum = juvNum.trim() + "j";
    		$("#juvNum").val(juvNum);
    		
    	}
    	
    	if(jQuery.inArray(type, ["pet","inv"]) < 0) {
    		alert("Invalid Type Code. " + type);
    		$("#refNum").focus();
    		return false;
    	}
    	
    	if ( true ) {
		    spinner();
    	}
    	    	
    	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=numberInquiry');
		$('form').submit();
		return true;
    });
    
    $("#submitPetCJIS").on('click', function (e) {    	
    	var juvNum = $("#juvNum").val();
    	var refNum = $("#refNum").val();
    	
    	if(refNum!=""&&refNum!="PET"&&refNum!="pet"&&refNum!="CJIS"&&refNum!="cjis")
    	{
    		alert("Invalid Type Code. " + refNum);
    		$("#typeCd").val('');
			$("#typeCd").focus(); 
    		return false;    		
    	}
    	
    	if(juvNum=="" && refNum=="")
    	{ //ref and juvnum
    		alert("Juvenile Number/Petition Number/CJIS Number or type code is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();    		
    		$("#juvNum").focus();
    		return false;
    	}
    	else if(juvNum=="")
    	{ //juvNum
    		alert("Juvenile Number/Petition Number/CJIS Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").show();
    		$("#juvNum").focus();
    		return false;
    	}
    	else if(juvNum!="" && refNum=="")
    	{
    		if(juvNum.trim().length>6)
    		{
    			alert("Type code is required.");
    			$("#refNum").focus();        		
        		return false;
    		}
    		else
    			spinner();
    	}
    	else if(juvNum!="" && refNum!="")
    	{
    		if(juvNum.trim().length==6)
    		{
    			alert("Type code not required for Juv num search.");
    			$("#refNum").val('');
    			$("#refNum").focus();
    			return false;
    		}
    		else if(juvNum.trim().length<10 && (refNum=="cjis"||refNum=="CJIS"))
    		{
    			alert(" At least first 10 digits of CJIS is required for search");
    			$("#juvNum").focus();
    			return false;
    		}
    		else if(juvNum.trim().length<10 && (refNum=="pet"||refNum=="PET"))
    		{
    			alert(" Petition number entered is not valid.");
    			$("#juvNum").focus();
    			return false;
    		}
    		else
    			spinner();
    	}
    		
    	else 
    	{
		    spinner();
    	}
    	//$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=PETITION/CJIS SEARCH');
    	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=PETITION/CJIS SEARCH&juvnum='+juvNum+'&typeCode='+refNum);
		$('form').submit();		
		return true;
    });
    //submitYouthCrtAct
    $("#submitYouthCrtAct").on('click', function (e) {    	
    	var juvNum = $("#juvNum").val();	 	
    	if(juvNum=="" ){ //juvnum
    		alert("Juvenile Number is required.");
    		$("#reqJuvNum").show();
    		$("#reqRef").hide();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();
    		$("#juvNum").focus();
    		return false;
	 	}
    	else {
		    spinner();
    	}
	 	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?fromPage=court&submitAction=courtActivityByYouth');
		$('form').submit();		
		return true;
    });
    
    $("#submitDALogNum").on('click', function (e) {
    	var dalogNum = $("#juvNum").val();    	
    	
    	if(dalogNum==""){ //dalognum 
    		alert("DA Log Number is required.");
    		$("#reqJuvNum").show();
    		/*$("#reqRef").show();
    		$("#reqDate").hide();
    		$("#reqCourtId").hide();*/
    		$("#juvNum").focus();
    		return false;
    	}
    	else if(Math.floor(dalogNum) != dalogNum || $.isNumeric(dalogNum)==false) 
    	{
    		alert("Invalid DA Log Number.");
    		$("#juvNum").focus();
    		return false;
    	}  
    	    	
    	if ( true ) {
		    spinner();
    	}
    	    	
    	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=DA LOG SEARCH');
		$('form').submit();
		return true;
    });
    
   $("#submitAncillarySettingAdd, #submitAncillarySettingDisp").on('click', function (e) {
	   
	   if ($("#courtId").val()=="" && $("#courtDate").val()==""){
		   	alert("Court ID and Date are required.");
		   	$("#reqJuvNum").hide();
   			$("#reqRef").hide();
   			$("#reqDate").show();
   			$("#reqCourtId").show();
   			$("#courtId").focus();
   			return false;
	   }else if($("#courtId").val()==""){ //courtID
    		alert("Court ID is required. Valid Court IDs are 313, 314, 315.");
    		$("#reqJuvNum").hide();
    		$("#reqRef").hide();
    		$("#reqDate").show();
    		$("#reqCourtId").show();
    		$("#courtId").focus();
    		return false;
   	} else if($("#courtId").val()!="313" &&  $("#courtId").val()!="314"  && $("#courtId").val()!="315"){
		   alert("Invalid Court ID. Valid Court IDs are 313, 314, 315. Please correct and retry. ");
			$("#reqJuvNum").hide();
			$("#reqRef").hide();
			$("#reqDate").show();
			$("#reqCourtId").show();
			$("#courtId").val('');
			$("#courtId").focus();
			return false;
	   } else if($("#courtDate").val()==""){ //court Date
    		alert("Court Date is required.");
    		$("#reqJuvNum").hide();
    		$("#reqRef").hide();
    		$("#reqDate").show();
    		$("#reqCourtId").show();
    		$("#courtDate").focus();
    		return false;
    	} else if($("#courtDate").val()!= ""){
    		var courtDate = new Date($("#courtDate").val());
    		var oldestCourtDate = new Date('01/01/1960');
    		if(courtDate < oldestCourtDate){
    		alert("Court Date Is Too Old, Please Correct.");
    		$("#reqJuvNum").hide();
    		$("#reqRef").hide();
    		$("#reqDate").show();
    		$("#reqCourtId").show();
    		$("#courtDate").focus();
    		return false;
    	}
    	}
	   
	   if ( true ) {
		    spinner();
	   }
     	
    }); //ancillary setting add requires validation of court id and date
 //default to submit button on enter
	//enter key		 
	  $(document).bind("keydown", function(event) 
	  {		  
		  if(event.which == 13) 
		  {	
			  event.preventDefault();
			  if ($("#courtId").val()!="" && $("#courtDate").val()!="")
			  {	
				  /*$("#submitCourtAction").click();*/
				  spinner();			    	
				  $('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=COURT ACTION');
				  $('form').submit();
			  }
			  else if ($("#juvNum").val()!="")
			  {	
				  flag=1;
				  //$("#submitYouthCrtAct").click();
				  spinner();			    	
				  $('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?fromPage=court&submitAction=courtActivityByYouth');				  
				  $('form').submit();			 
			  }
		  }
	  });
});