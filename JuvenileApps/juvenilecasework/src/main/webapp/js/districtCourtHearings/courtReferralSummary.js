$(document).ready(function () {
    $("#initialSettingBtn").on('click', function (e) {
    	var checkedRadioVal = $("input[name='referralRec']:checked").val();
		if(	checkedRadioVal == null  ){
		alert("Please Select The Referral By Clicking The Radio Button In The Left." );
		return false;
		}
		
		if ( true ){
			spinner();
		}
    });
    $("#submitYouthCrtAct").on('click', function (e) {
    	spinner();
    	var juvNum = $("#juvNum").val();
	 	
	 	$('form').attr('action','/JuvenileCasework/processJuvenileDistrictCourtHearings.do?submitAction=courtActivityByYouth&fromPage=cancel');
		$('form').submit();
		return true;
    });
    
    $("#petitionUpdateBtn").on('click', function (e) {
    	var checkedRadioVal = $("input[name='referralRec']:checked").val();
		if(	checkedRadioVal == null  ){
		alert("Please Select The Referral By Clicking The Radio Button In The Left." );
		return false;
		}
		
		if ( true ){
			spinner();
		}
    });
    
	$("#reSubmitbtn").on('click', function (e) {
    	var juvNum = $("#juvNum").val();
    	//reset pager.offset to "" for new search not to 0.
		var ps = document.getElementsByName("pager.offset");
		var elementIndex = $(ps).val();
		if(typeof elementIndex != "undefined"){
			document.getElementsByName("pager.offset")[0].value ="";	
		}
    	if(juvNum==""){ //juvnum
    		alert("Juvenile Number is required.");
    		$("#juvNum").show();
    		$("#juvNum").focus();
    		return false;
	 	}
    	
    	if ( true ) {
    		spinner();
    	}
	 	$('form').attr('action','/JuvenileCasework/handleJuvenileDistrictCourtReferralSummary.do?submitAction=GO&juvnum='+juvNum);
		$('form').submit();
		return true;
    });
    
});    