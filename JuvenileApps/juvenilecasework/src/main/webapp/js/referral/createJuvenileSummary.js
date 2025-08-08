$(document).ready(function () {	
	var newJuvNum=$("#newJuvNum").val();
	var action = $("#action").val();
	  
    $("#searchJuvenileBtn").on('click', function (e) {
    	$('form').attr('action',"/JuvenileCasework/displayJuvenileProfileSearch.do?isReferral=true");
		$('form').submit();
    });
    
    $("#referralBriefingBtn").on('click', function (e) {    	
    	$('form').attr('action',"/JuvenileCasework/displayJuvenileBriefingDetails.do?submitAction=referralLink&juvenileNum="+$("#newJuvNum").val());
		$('form').submit();
    });
	
    $("#createJuvenileSubmitBtn").on('click', function (e) {  
    	spinner();
    	$('form').attr('action',"/JuvenileCasework/displayCreateJuvenileSummary.do?submitAction=Finish");
		$('form').submit();
    });
    $("#backBtn").on('click', function (e) {
    	$('form').attr('action',"/JuvenileCasework/displayCreateJuvenileSummary.do?submitAction=Back");
		$('form').submit();
    });
    
    $("#createReferralBtn").on('click', function (e) {  
    	spinner();
    	//$('form').attr('action',"/JuvenileCasework/processReferralBriefing.do?submitAction=Create Referral");
    	$('form').attr('action',"/JuvenileCasework/displayCreateJuvenileSummary.do?submitAction=Create Referral");
		$('form').submit();
    });
    
    //enable after submit
    $("#referralBriefingBtn").prop("disabled",true);
 
    //disable after submit
	if(action=="createJuvenileSummary"){
		$("#createJuvenileSubmitBtn").prop("disabled",true);
		$("#referralBriefingBtn").prop("disabled",false);
	}
	
	if(action=="updateJuvenileSummary"){
		$("#referralBriefingBtn").prop("disabled",false);
	}
	
	  //default to submit button on enter
    $(document).bind('keypress', function(e) {
    	// track enter key
	  var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
	  if (keycode == 13) { // keycode for enter key
	         // force the 'Enter Key' to implicitly click the submit button
	         $("#createJuvenileSubmitBtn").click();
	         return false;
	      } else  {
	         return true;
	      }
	}); // end of function
    
});