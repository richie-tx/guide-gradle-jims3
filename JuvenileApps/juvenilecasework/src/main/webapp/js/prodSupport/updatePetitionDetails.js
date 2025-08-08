$(document).ready(function () {		
	
	//onload disable button rry
	$("#selectPetBtn").attr("disabled",true);
	
	if(typeof $("#petDate") != "undefined"){
		datePickerSingle($("#petDate"),"newPetitionDate",false);
	}
	
	if(typeof $("#terminationDate") != "undefined"){
		datePickerSingle($("#terminationDate"),"newPetitionDate",false);
	}
	
	if(typeof $("#terminationCreateDate") != "undefined"){
		datePickerSingle($("#terminationCreateDate"),"newTerminationCreateDate",false);
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
			
    		$('form').attr('action',"/JuvenileCasework/updatePetitionDetailsQuery.do?submitAction=PETITION UPDATE&altOID="+$("#petitionIndex").val());
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
		 
		  var result = $("#crtResult").val(); //set it on the form
		  var petitionOID = $("#petOID").val();
		  var petAmend = $("#petAmend").val();
		  var petDate = $("#petDate").val();
		  var petType = $("#petType").val();
		  var petNum = $("#petNumber").val();
		  var refNum = $("#refNum").val();
		  var petStatus = $("#petStatus").val();
		  var petSeqNum = $("#petSeqNum").val();
		  var petCJIS = $("#cjisNum").val();
		  var petDPS = $("#dpsCode").val();
		  var petSeverity = $("#petSeverity").val();
		  var terminationDate = $("#terminationDate").val();
		  var terminationCreateDate = $("#terminationCreateDate").val();
		  var juvNum = $("#juvNum").val();
		  
		  if(typeof(juvNum)!="undefined")
			  $("#juvenileId").val(juvNum);
		  //set all the fields back to the form
		  $("#petitionAmend").val( petAmend ); 
		  $("#petitionNum").val( petNum );
		  $("#terminationDt").val( terminationDate );
		  $("#terminationCreateDt").val( terminationCreateDate);
		  $("#petitionType").val( petType );
		  $("#referralId").val( refNum );
		  $("#petitionStat").val( petStatus );
		  $("#petitionSeq").val( petSeqNum );
		  $("#petitionOid").val( petitionOID );
		  $("#petitionCJIS").val( petCJIS );
		  $("#petitionDPS").val( petDPS );
		  $("#newCharge").val(result);
		  $("#updateSeq").val("0");
		  $("#petitionDt").val(petDate);
		  $("#petSeverityId").val(petSeverity);
		  
		  
		  $('form').attr('action',"/JuvenileCasework/updatePetitionDetailsQuery.do?submitAction=Update Record");
		  spinner();
		  $('form').submit();
	});
 		
});