$(document).ready(function () {	
	$("#selectRefBtn").on("click",function(){

		var referralOID        = $('input:radio[name=referralOID]:checked').val();
    	if( referralOID !="" ){     		
			$("#tempRefOID").val( referralOID ); //set it in the form
			console.log("tempRefOID : " + $("#tempRefOID").val() );
    		$('form').attr('action','/JuvenileCasework/DeleteMsReferralQuery.do?submitAction=Submit');
    		$('form').submit();
    		spinner();
    		 return true;
		 }

	});
	
	$("#BtnRefresh").on("click",function(){
		//Clear fields on the form
		$("#juvNum").val("");
		$("#refNum").val("");

		});	
	
	$("#backToQryBtn").on("click",function(){
		//Clear fields on the form
		$('form').attr('action','/JuvenileCasework/DeleteMsReferralQuery.do?clr=Y');
		$('form').submit();
		 return true;

	});
	
	
	
});