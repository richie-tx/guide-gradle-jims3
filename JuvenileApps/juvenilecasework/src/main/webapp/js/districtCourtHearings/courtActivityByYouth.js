$(document).ready(function () {	
	
	var currDate= new Date();	
	
	$("#reSubmitbtn").on('click', function (e) {
    	var juvNum = $("#juvNum").val();
    	//reset pager.offset to "" for new search not to 0.
		var ps = document.getElementsByName("pager.offset");
		var elementIndex = $(ps).val();
		if(typeof elementIndex != "undefined"){
			document.getElementsByName("pager.offset")[0].value ="";	
		}
    	if(juvNum=="" ){ //juvnum
    		alert("Juvenile Number is required.");
    		$("#juvNum").show();
    		$("#juvNum").focus();
    		return false;
	 	}
    	
    	if ( true ) {
    		spinner();
    	}
	 	$('form').attr('action','/JuvenileCasework/handleJuvenileDistrictCourtActivityByYouth.do?submitAction=GO');
		$('form').submit();
		return true;
    });
	
	
	$("#courtActionBtn").on('click', function (e) {
    	
    	//reset pager.offset to "" for new search not to 0.
		var ps = document.getElementsByName("pager.offset");
		var elementIndex = $(ps).val();
		if(typeof elementIndex != "undefined"){
			document.getElementsByName("pager.offset")[0].value ="";	
		}
    
    });
	
	$("#referralSummary").click(function(){
		spinner();
	})
	
	$("#updateMasterBtn").click(function(){
		spinner();
	})
	
});