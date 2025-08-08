$(document).ready(function () {
	
	$("select[id^='placementdateMonth']").on('change', function (e) {
		$("#placementDt").val($("#placementdateMonth").val()+"/"+$("#placementdateYear").val());			
	});
	$("select[id^='placementdateYear']").on('change', function (e) {
		$("#placementDt").val($("#placementdateMonth").val()+"/"+$("#placementdateYear").val());			
	});
	$("select[id^='placementType']").on('change', function (e) {		
		if($("#placementType").val()=='OTHER')
			{			
				document.getElementById('placementtypeotherReason').disabled=false; 
			}
		else
			{
			$("#placementtypeotherReason").val('');
			document.getElementById('placementtypeotherReason').disabled=true;
			}
	});
	$("select[id^='placementRemovalReason']").on('change', function (e) {		
		if($("#placementRemovalReason").val()=='OTHER REASON')
			{			
				document.getElementById('placementremovalreasonOther').disabled=false; 
			}
		else
			{
			$("#placementremovalreasonOther").val('');
			document.getElementById('placementremovalreasonOther').disabled=true;
			}
	});
	
	$("#btnplacementAdd").on('click',function() {
		 //alert('inside next js-dual')
		if ($("#placementdateMonth").val() == "" && $("#placementType").val() == "") {
			alert("Please enter placement details.");
			$("#placementdateMonth").focus();
			return false;
		}
		 if ($("#placementdateMonth").val() != "") 
		 {
			 if ($("#placementdateYear").val() == "") {
				alert("Year is required.");
				$("#placementdateYear").focus();
				return false;
			 }
			 else
			{
				 var today = new Date();
				 var dd = today.getDate();
				 var mm = today.getMonth() + 1;
				 var yyyy = today.getFullYear();
				 if($("#placementdateYear").val()== yyyy && $("#placementdateMonth").val()>mm)
				 {
					 	alert("Future date is not allowed.");
						$("#placementdateMonth").focus();
						return false;
					 
				 }
				 
				 
			}
		 }
		 if ($("#placementdateYear").val() != "") 
		 {
			 if ($("#placementdateMonth").val() == "") {
				alert("Month is required.");
				$("#placementdateMonth").focus();
				return false;
			 }
		 }
		 if ($("#placementdateMonth").val() != "") 
		 {
			 if ($("#placementType").val() == "") {
				alert("Placement Type is required with Placement Date.");
				$("#placementType").focus();
				return false;
			 }
		 }
		 if ($("#placementType").val() == "OTHER") 
		 {
			 if ($("#placementtypeotherReason").val() == "") {
				alert("Placement Type(Other) is required.");
				$("#placementtypeotherReason").focus();
				return false;
			 }
		 }
		 if ($("#placementRemovalReason").val() == "OTHER REASON") 
		 {
			 if ($("#placementremovalreasonOther").val() == "") {
				alert("Placement Removal(Other) is required.");
				$("#placementremovalreasonOther").focus();
				return false;
			 }
		 }
		 
		
	});	


});