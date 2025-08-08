$(document).ready(function () {		
	
	//onload disable button rry
	$("#selectPetBtn").attr("disabled",true);
	
	if(typeof $("#petDate") != "undefined"){
		datePickerSingle($("#petDate"),"newPetitionDate",false);
	}
	
	$("input:radio").change(function () {
	    $("#selectPetBtn").attr("disabled", false);
	});
	
	/*$("select[id^='crtResult']").on('change', function (e) {
		
		  var result = $("#crtResult").val(); //set it on the form
		  $("#newCharge").val(result);
		  //$('form').attr('action',"/JuvenileCasework/updatePetitionDetailsQuery.do?submitAction=Update Offense");
		  //$('form').submit();
	});*/
	
	$("#selectPetBtn").on("click",function(){

    	var petitionOID  = $('input:radio[name=selectedValue]:checked').val();
    	var refDate = $("#referralDate").val(); //set it in the form
    	if( petitionOID !="" ){     		
			$("#petitionIndex").val( petitionOID ); //set it in the form
			$("#referralDt").val( refDate ); //set it in the form
			console.log("tempRefOID : " + $("#petitionIndex").val() );			
    		$('form').attr('action',"/JuvenileCasework/deletePetitionDetailsRecordQuery.do?submitAction=Submit&altOID="+$("#petitionIndex").val());
    		spinner();
    		$('form').submit();
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
		$('form').attr('action','/JuvenileCasework/UpdateMsReferralQuery.do?clr=Y');
		$('form').submit();
		 return true;

	});		

	$("#submitBtn").on("click",function(form){	
		
		var isOk = window.confirm('Are you sure you want to delete the Petition Record?');
		
		if (isOk) {
			$('form').attr('action',"/JuvenileCasework/deletePetitionDetailsRecordQuery.do?submitAction=Delete Record&petOID="+$("#petOID").val());
			  spinner();
			  $('form').submit();
				
				return true;
		} 
		  
	});
 		
});