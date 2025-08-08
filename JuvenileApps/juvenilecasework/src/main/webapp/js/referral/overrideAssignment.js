$(document).ready(function () {
	var myForm = document.forms[0];
	var action = $("#action").val();
	if (action == "overrideSuccess"){
		$("#overrideReasonStr").attr("disabled",true);
		$("#assignmentDateStr").attr("disabled",true);
		$("#overrideOTHComments").attr("disabled",true);
		$("#assignmentType").attr("disabled",true);
		$("#supervisionCat").attr("disabled",true);
		$("#supervisionType").attr("disabled",true);
		$("#jpo").attr("disabled",true);
		$("#validateBtn1").attr("disabled",true);//BUG 85008
		$("#validateBtn2").attr("disabled",true);
		$("#searchBtn").attr("disabled",true);
	}
	if (action == "overrideAssignmentfromRefBrief"){
		localStorage.clear();
	} /*to clear the checkBoxes unchecked, BUG#85008*/
	
	var retrievedData = JSON.parse(localStorage.getItem("selectedRefs"));
	if(retrievedData != null){
	 for(var i = 0; i < retrievedData.length; i++)
     {		
		 var overrideRef=$('input[name=selectedToOverride]');
			$.each(overrideRef,function(index){
				 if ($(this).attr('id') == ('overrideRefNum-'+ retrievedData[i]))
					 $(this).attr('checked',true);
			});
     }
	 localStorage.clear();
	}

	if(typeof $("#assignmentDateStr") != "undefined"){
		var overrideRef=$('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(overrideRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				i++;
			}
		});
		
		localStorage.setItem("selectedRefs", JSON.stringify(refs));
		datePickerSingle($("#assignmentDateStr"),"Assignment Date",true);
	}
	
	// to keep the override other reason visible
	if($("#overrideReasonStr").val() == "OTH")
	{
		$("#otherComments").show();
		$("#overrideOTHComments").show();
	}
	
	$("#overrideReasonStr").on("change",function(){
		if($("#overrideReasonStr").val() == "OTH")
			{
			$("#otherComments").show();
			$("#overrideOTHComments").show();
			}else
				{
				$("#otherComments").hide();
				}
	});
	
	$("#assignmentType").on("change",function(){
		//store the selected referrals to override
		spinner();
		var overrideRef=$('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(overrideRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				i++;
			}
		});
		
		localStorage.setItem("selectedRefs", JSON.stringify(refs));
		$('form').attr('action','/JuvenileCasework/submitOverrideAssignment.do?submitAction=Change Assessment Type');
		$('form').submit();
	});
	$("#supervisionCat").on("change",function(){
		//store the selected referrals to override
		spinner();
		var overrideRef=$('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(overrideRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				i++;
			}
		});
		localStorage.setItem("selectedRefs", JSON.stringify(refs));
		$('form').attr('action','/JuvenileCasework/submitOverrideAssignment.do?submitAction=Change Supervision Cat');
		$('form').submit();
	});
	
	$("#supervisionType").on("change",function(){
		//store the selected referrals to override
		spinner();
		var overrideRef=$('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(overrideRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				i++;
			}
		});
		localStorage.setItem("selectedRefs", JSON.stringify(refs));
		$('form').attr('action','/JuvenileCasework/submitOverrideAssignment.do?submitAction=Change Supervision Type');
		$('form').submit();
	});
	
	$("input[id^='validateBtn']").on('click', function (e) {	
		
		if($("#jpo").val() == "")
		{
		      alert("Please enter a jpo code.");
		      $("#jpo").focus();
		      return false;
		}
		if(($("#jpo").val()).length <5)
		{
	      	alert("Juvenile Probation Officer is invalid. Please modify.");
	      	$("#jpo").focus();
	      	return false;
		}
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;	
		if(alphaNumWithSymbolsRegExp.test($("#jpo").val().trim(),alphaNumWithSymbolsRegExp) == false){
			alert("JPO ID # must be alphanumeric with no leading spaces.  The following symbols !@#$%^&*() are allowed.");
			$("#jpo").focus();
			return false;
		}
		//store the selected referrals to override
		var overrideRef=$('input[name=selectedToOverride]');

		var refs = new Array();
		i=0;
		$.each(overrideRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				i++;
			}
		});
		localStorage.setItem("selectedRefs", JSON.stringify(refs));
		if(true)
		{
			spinner();
		}
		$('form').attr('action','/JuvenileCasework/submitOverrideAssignment.do?submitAction=Validate');
		$('form').submit();
	});
	
	$("#nxtOverrideBtn").click(function () {	
		 localStorage.clear();
	});
	$("#refreshBtn").click(function () {	
		 spinner();
	});
	$("#cancelBtn").click(function () {	
		 spinner();
	});
	
	$("#submitBtn").click(function () {	
		/*
		   	var validate = validateCreateReferralForm(myForm);
		if(!validate){
			return false;
		}*/	
		
		if($("#assignmentType").val() == null || $("#assignmentType").val() == ""){
			alert("Please select an assignment type.");
			$("assignmentType").focus();
			return false;
		}
		if($("#supervisionCat").val() == null || $("#supervisionCat").val() == ""){
			alert("Please select a supervision category.");
			$("supervisionCat").focus();
			return false;
		}
		if($("#supervisionType").val() == null || $("#supervisionType").val() == ""){
			alert("Please select a supervision type.");
			$("supervisionType").focus();
			return false;
		}
		if($("#jpo").val() == null || $("#jpo").val() == ""){
			alert("Please enter a jpo code.");
			$("#jpo").focus();
			return false;
		}
		if(($("#jpo").val()).length <5)
		{
	      	alert("Juvenile Probation Officer is invalid. Please modify.");
	      	$("#jpo").focus();
	      	return false;
		}
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;	
		if(alphaNumWithSymbolsRegExp.test($("#jpo").val().trim(),alphaNumWithSymbolsRegExp) == false){
			alert("JPO ID # must be alphanumeric with no leading spaces.  The following symbols !@#$%^&*() are allowed.");
			$("#jpo").focus();
			return false;
		}
		if($("#jpo").val() == "REC" || $("#jpo").val() == "UVREC"){
			if( $("#assignmentType").val() != "REC"){
			alert("JPO code UVREC does not qualify for this assignment type.  Please modify.");
			$("#jpo").focus();
			return false;
		}
		}
		if($("#assignmentDateStr").val() == null || $("#assignmentDateStr").val() == ""){
			alert("Please enter an assignment date." );
			$("#assignmentDateStr").focus();
			$("#assignmentDateStr").val("");
			return false;
		}
				var date1 = new Date();	    	
		var diff = Math.abs(date1.getTime()-getDateFromFormat($("#assignmentDateStr").val(),"MM/dd/yyyy"));		
		
		if(Math.ceil(diff/(1000*3600*24))>8)
		{		
			alert("Assignment date must not exceed the past seven days.  Please modify or contact Data Corrections for date modification. ");
			$("#assignmentDateStr").focus();
			return false;
		}
		
		if($("#overrideReasonStr").val() == null || $("#overrideReasonStr").val() == ""){
			alert("Please select an override reason.");
			$("#overrideReasonStr").focus();
			return false;
		}
		if ($("#overrideReasonStr").val() == "OTH" && $("#overrideOTHComments").val() == ""){
				alert("Please enter comment for the Override reason - Other.");
				$("#overrideOTHComments").focus();
				return false;
		}
	 	if($("[id^=overrideRefNum]").is(':checked')!=true){
			alert("Please select one or more referrals to override.");
			$("[id^=overrideRefNum]").focus();
			return false;
		}
	   	var _refNums = new Array();
		$("input:checkbox:checked").each(function(){
			_refNums.push($(this).val());
		});
		$("#selectedReferrals").val( _refNums );
		
		/*var overrideRef=$('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(overrideRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				i++;
			}
		});*/
		
		//localStorage.setItem("selectedRefs", JSON.stringify(refs));
		
		if ( true ) {
			spinner();
		}
		$('form').attr('action','/JuvenileCasework/submitOverrideAssignment.do?submitAction=Submit');
		$('form').submit();
		
	});
	var juvNum=$("#juvNum").val();
	  $("#searchJuvenileBtn").on('click', function (e) {
	    	$('form').attr('action',"/JuvenileCasework/displayJuvenileProfileSearch.do?isReferral=true");
			$('form').submit();
	    });
	    
	    $("#referralBriefingBtn").on('click', function (e) {    	
	    	$('form').attr('action',"/JuvenileCasework/displayJuvenileBriefingDetails.do?submitAction=referralLink&juvenileNum="+$("#juvNum").val());
			$('form').submit();
	    });
	    
	    $("#updateRefBtn").on('click', function (e) {    	
	      $('form').attr('action',"/JuvenileCasework/processReferralBriefing.do?submitAction=Update Referral&juvenileNum="+$("#juvNum").val());
	    	/*$('form').attr('action','/JuvenileCasework/processReferralBriefing.do?submitAction=Next');*/
			$('form').submit();
	    });
	    
		$("#searchBtn").click(function(){
			$('form').attr("action","/JuvenileCasework/searchOfficerProfile.do?clr=Y&requestOrigin=OA");
			$('form').submit();
		})
		
		//========== US 166294 marked checkbox as true if there's only a single assignment record ===========
		markSignleAssignmentRecordAsChecked();
});

function markSignleAssignmentRecordAsChecked(){
	
	var overrideRef=$('input[name=selectedToOverride]');
	
	if(overrideRef && overrideRef.length === 1){
		
		if(overrideRef[0].checked !== true){
			overrideRef[0].checked = true
		}		
	}
	
}