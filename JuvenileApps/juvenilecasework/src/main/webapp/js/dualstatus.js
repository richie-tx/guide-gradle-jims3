$(document).ready(function () {	
		
	$("#btnBack").on('click',function() {
		//alert('inside back js-dual')
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuseCreate.do?submitAction=Back Dual');
		$('form').submit();
	});
	
	$("#btnJuvTraitBack").on('click',function() {
		//alert('inside back js-dual')
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuse.do?submitAction=Add Dual Status Info');
		$('form').submit();
	});
	
	$("#btnDulaStatusBack").on('click',function() {
		//alert('inside back js-dual')
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuse.do?submitAction=Dual&action=View');
		$('form').submit();
	});
	
	$("#btnDulaStatusLstBack").on('click',function() {		
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuse.do?submitAction=Tab&action=View');
		$('form').submit();
	});
	
	$("#btnCancel").on('click',function() {
		//alert('inside back js-dual')
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuseCreate.do?submitAction=Back Dual');
		$('form').submit();
	});	
	$("#btndetailsCancel").on('click',function() {
		//alert('inside back js-dual')
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuseCreate.do?submitAction=Back To Dual Status List');
		$('form').submit();
	});
	 var $selectedValues = new Array();
	 $("select[id^='CPSServices']").on('change', function (e) {
		 $selectedValues=$("#CPSServices").val();
		 $("#hdnselectedServices").val($selectedValues);
		 //alert($("#hdnselectedServices").val())
		});	
	 if (typeof $("#hdnselectedServices").val() != "undefined")
	{	 
	 if($("#hdnselectedServices").val()!="")
	 {
			 if($("#hdnselectedServices").val().indexOf(",")!=-1)
				 {				 	
				 	var servsArray = $("#hdnselectedServices").val().split(",");
					 for ( i = 0; i < servsArray.length; i++ ) 
					 {
						 $("#CPSServices option").each(function(){
								if($(this).text()== servsArray[i])
									$(this).attr("selected","selected");							
						 });
					  }					 
				 }
	 }
	}
	 //if(typeof $("#codetableTypeId").val() != "undefined")
	 if (typeof $("#hdninformationSource").val() != "undefined")
	 {
		 if($("#hdninformationSource").val()!="")
		 {
			 $("#informationSrcCd option").each(function(){
				 		if($(this).val()== $("#hdninformationSource").val())
						{
							$(this).attr("selected","selected");
							//$("#informationSrcCd").disabled=true;
							document.getElementById('informationSrcCd').disabled=true;
						}
			 });
			 
		 }
		 else
		 {		 
			 document.getElementById('informationSrcCd').disabled=false;
		 }
	 }
		 //$("#informationSrcCd").disabled=false;
	 $("#btnNext").on('click',function() {
		 //alert('inside next js-dual')
		 if ($("#informationSrcCd").val() == "") {
				alert("Information Source is required.");
				$("#petitionStatus").focus();
				return false;
			}
		 if(document.getElementById('referralRegionR6A').checked== false&&document.getElementById('referralRegionR6B').checked== false&&document.getElementById('referralRegionOOC').checked== false)
		  {
			 	alert("Region of Referral is required.");
				$("#referralRegionR6A").focus();
				return false;
			 
		  }
		 if ($("#custodyStatus").val() == "") {
				alert("Custody Status is required.");
				$("#custodyStatus").focus();
				return false;
			}
		 if ($("#cpslevelofCare").val() == "") {
				alert("CPS Level of Care is required.");
				$("#cpslevelofCare").focus();
				return false;
			}
		 if ($("#parentalrightsTermination").val() == "") {
				alert("Parental Rights Terminated is required.");
				$("#parentalrightsTermination").focus();
				return false;
			} 
		 
		 if ($("#CPSServices").val() == null||$("#CPSServices").val() == "") {
				alert("CPS Services is required.");
				$("#CPSServices").focus();
				return false;
			}
		 
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuseCreate.do?submitAction=Next Dual');
		$('form').submit();
	});	
	$("#btnFinish").on('click',function() {
		$('form').attr('action','/JuvenileCasework/displayJuvenileAbuseCreate.do?submitAction=Finish Dual');
		$('form').submit();
	});
	

});