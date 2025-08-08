$(document).ready(function () {
	 	
	  var action = $("#action").val();
	  $(':input:radio:eq(0)').attr('checked', 'checked');
		if(action=="initialSetting"){
			$("#juvNum").focus();
		}else if(action=="courtMainMenu"){
			$("#courtId").focus();
		}

	var juvenileDatesList = $("#holidaysList").val();
		if(typeof $("#courtDate").val() != "undefined"){
			datePickerSingleHolidays($("#courtDate"),"Court Date",false,juvenileDatesList);
		}
	
	// on load hide the following
	$("#reqCourtId").hide();
	$("#reqDate").hide();
	$("#reqRef").hide();
	$("#reqJuvNum").hide();
	
	
	
    
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
    	
    	var type = refNum.toLowerCase();
    	if(jQuery.inArray(type, ["pet","inv"]) < 0) {
    		alert("Invalid Type Code. " + type);
    		$("#refNum").focus();
    		return false;
    	} 
    	$('form').attr('action','/JuvenileCasework/handleNumberInquiry.do?submitAction=GO');
		$('form').submit();
		return true;
    });

    
    function submitForm(val,id){
		//create an iframe dynamically
		var ifrm = document.createElement('iframe');
		ifrm.setAttribute('id', id);
		//ifrm.setAttribute('name', id); //when not set opens new tabs and closes.
		$("#pdf-download").append(ifrm); // append it to an div
		$('form').attr('action',"/JuvenileCasework/handleNumberInquiry.do?submitAction=Submit&data="+val);
		$('form').submit();
		
		//remove once submitted iframe dynamically
		if($("iframe[id^=content]")){
			$("iframe[id^=content]").remove();
			$('form').removeAttr("target");
		}
		///jQuery.ajaxSetup({ cache: false });
    }
    
    
    $("#updateMasterBtn").on('click', function (e) {
    	
    	var checkedRadioVal = $("input[name='selectedId']:checked").val();
		if(	checkedRadioVal == null  ){
		alert("Please Select The Radio Button In The Left!" );
		return false;
		} 
		spinner();
    	$('form').attr('action',"/JuvenileCasework/handleNumberInquiry.do?submitAction=Master Display&selectedVal="+checkedRadioVal);
		$('form').submit();
    });
    
    $("#submitInitialSettingBtn").on('click', function (e) {
    	
    	var checkedRadioVal = $("input[name='selectedId']:checked").val();
		if(	checkedRadioVal == null  ){
		alert("Please Select The Radio Button In The Left!" );
		return false;
		} 
		spinner();
    	$('form').attr('action',"/JuvenileCasework/handleNumberInquiry.do?submitAction=Initial Setting&selectedVal="+checkedRadioVal);
		$('form').submit();
    });
    
    $("#courtMainMenuBtn").on('click', function (e) {
    	spinner();
    	$('form').attr('action',"/JuvenileCasework/handleNumberInquiry.do?submitAction=Court Main Menu");
		$('form').submit();
    });
    
    $("#updateAncillarySettingBtn").on('click', function(){
    	
		var checkedRadioVal = $("input[name='docketEventsRec']:checked").val();
		if(	checkedRadioVal == null  ){
		alert("Please Select The Ancillary Setting To Be Updated By Clicking The Radio Button In The Left." );
		return false;
		} 			
	});
    
 $(".toggler").click(function(e){
        
        e.preventDefault();
        
        //the data stored in the data-prod-cat
        var prodCat = ($(this).attr("data-prod-cat"));
        
        //toggle the link clicked on
        $(".history" + prodCat).toggle();
        
        
        //select the parent and find the span so you can
        //toggle the "cat-plus" class
       $(this).parent().find("span").toggleClass("hist-plus");
        
        //toggle the cat-minus class
        $(this).toggleClass("hist-minus"); 
        
    });
 

 //submitYouthCrtAct
 $("#submitYouthCrtActBtn").on('click', function (e) {    	 	
 	
 	var checkedRadioVal = $("input[name='selectedId']:checked").val();
		if(	checkedRadioVal == null  ){
			alert("Please select a juvenile - choose a radio button!" );
			return false;
		} 
		
		var selectedJuvNum = getJuvNumber(checkedRadioVal);
		
		spinner();
	 	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=courtActivityByYouth&fromPage=cancel&juvnum=' + selectedJuvNum);
		$('form').submit();		
		return true;
 });
 
 $("#submitReferralSearchBtn").on('click', function (e) {
	 
	 var checkedRadioVal = $("input[name='selectedId']:checked").val();
		if(	checkedRadioVal == null  ){
			alert("Please select a juvenile - choose a radio button!" );
			return false;
		} 
		
		var selectedJuvNum = getJuvNumber(checkedRadioVal);
		
		//assign the selected juv number from the grid
		$("#juvNum").val(selectedJuvNum);
		
		 spinner();
		$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=REFERRAL INQUIRY');
		$('form').submit();		
		return true;
 });
 
 function getJuvNumber(selectedVal){
	 
	 if(!selectedVal){
		 alert('juv number required');
		 return false;
	 }
	 
	 var pipePosition = selectedVal.indexOf('|');
	 var juvNum;
	 if(pipePosition != -1){
		 juvNum = selectedVal.substring(0, pipePosition);
	 }
	 
	 return juvNum;
 }
    
});