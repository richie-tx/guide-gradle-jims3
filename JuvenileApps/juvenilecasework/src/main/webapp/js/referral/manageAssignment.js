$(document).ready(function () {
	var myForm = document.forms[0];
	var action = $("#action").val();
	if (action == "manageAssignSuccess"){
		$("#assignmentDateStr").attr("disabled",true);
		$("#assignmentType").attr("disabled",true);
		$("#supervisionCat").attr("disabled",true);
		$("#supervisionType").attr("disabled",true);
		$("#jpo").attr("disabled",true);
		$("#validateBtn1").attr("disabled",true);//BUG 85008
		$("#validateBtn2").attr("disabled",true);
		$("#searchBtn").attr("disabled",true);
	}

	if (action == "manageAssignmentfromRefBrief"){
		localStorage.clear();
	} /*to clear the checked checkBoxes */
	var retrievedData = JSON.parse(localStorage.getItem("selectedRefs"));
	if(retrievedData != null){
	 for(var i = 0; i < retrievedData.length; i++)
     {		
		 var overrideRef=$('input[name=selectedToOverride]');
			$.each(overrideRef,function(index){
				 if ($(this).attr('id') == ('manageRefNum-'+ retrievedData[i])) 
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
	
	$("#assignmentType").on("change",function(){
		//store the selected referrals to add assignment
		spinner();
		var selectedRef=$('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(selectedRef,function(index){
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
	
	$("#supervisionType").on("change", function(){
		//store the selected referrals
		spinner();
		var selectedRef=$('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(selectedRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				i++;
			}
		});
		localStorage.setItem("selectedRefs", JSON.stringify(refs));
		$('form').attr('action','/JuvenileCasework/displayManageAssignment.do?submitAction=Change Supervision Type');
		$('form').submit();
	})
	
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
		if ( true ) {
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
	
	$("#submitBtn").click(function (event) {
		let isValid = true;
		event.preventDefault();
		/*
		   	var validate = validateCreateReferralForm(myForm);
		if(!validate){
			return false; 
		}*/	
		if($("#assignmentType").val() == null || $("#assignmentType").val() == ""){
			alert("Please select an assignment type.");
			$("assignmentType").focus();
			isValid = false;
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
			isValid = false;
			return false;
		}
		if($("#jpo").val() == null || $("#jpo").val() == ""){
			alert("Please enter a jpo code.");
			$("#jpo").focus();
			isValid = false;
			return false;
		}
		if(($("#jpo").val()).length <5)
		{
	      	alert("Juvenile Probation Officer is invalid. Please modify.");
	      	$("#jpo").focus();
	      	isValid = false;
	      	return false;
		}
		var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 \.\\()\x27\x40\x23\x24\x25\x5e\x26\x2a\x21]*$/;	
		if(alphaNumWithSymbolsRegExp.test($("#jpo").val().trim(),alphaNumWithSymbolsRegExp) == false){
			alert("JPO ID # must be alphanumeric with no leading spaces.  The following symbols !@#$%^&*() are allowed.");
			$("#jpo").focus();
			isValid = false;
			return false;
		}
		if($("#jpo").val() == "REC" || $("#jpo").val() == "UVREC"){
			if( $("#assignmentType").val() != "REC"){
			alert("JPO code UVREC does not qualify for this assignment type.  Please modify.");
			$("#jpo").focus();
			isValid = false;
			return false;
		}
		}
		if($("#assignmentDateStr").val() == null || $("#assignmentDateStr").val() == ""){
			alert("Please enter an assignment date." );
			$("#assignmentDateStr").focus();
			$("#assignmentDateStr").val("");
			isValid = false;
			return false;
		}
				var date1 = new Date();	    	
		var diff = Math.abs(date1.getTime()-getDateFromFormat($("#assignmentDateStr").val(),"MM/dd/yyyy"));		
		
		if(Math.ceil(diff/(1000*3600*24))>8)
		{		
			alert("Assignment date must not exceed the past seven days.  Please modify or contact Data Corrections for date modification. ");
			$("#assignmentDateStr").focus();
			isValid = false;
			return false;
		}
	 	if($("[id^=manageRefNum]").is(':checked')!=true){
			alert("Please select one or more referrals to complete assignment.");
			$("[id^=manageRefNum]").focus();
			isValid = false;
			return false;
		}
	   	var _refNums = new Array();
		$("input:checkbox:checked").each(function(){
			_refNums.push($(this).val());
		});
		$("#selectedReferrals").val( _refNums );
		var overrideRef=$('input[name=selectedToOverride]');
		var selectedReferralDate = $('input[name=selectedToOverride]');
		var refs = new Array();
		i=0;
		$.each(overrideRef,function(index){
			if($(this).prop('checked')== true)
			{	
				var selectedReferral = $(this).val();
				
				/*const selectedReferralDate = new Date( $("#selectedReferralDate-"  + selectedReferral).val() );
				const selectedAssignmentDate = new Date( $("#assignmentDateStr").val() );

				if (selectedAssignmentDate < selectedReferralDate ) {
					alert ("Assignment date can not be prior to referral date. Please modify.");
					isValid = false;
					return false;
				}*/
				
				refs[i] = $(this).val();
				i++;
			}
		});
		
		if (  isValid  ) {
			spinner();
		}
		
		localStorage.setItem("selectedRefs", JSON.stringify(refs));
		$('form').attr('action','/JuvenileCasework/displayManageAssignment.do?submitAction=Submit');
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
	    
	    $("#nxtAssignBtn").on('click', function (e) {    	
	    	localStorage.clear();
	    });
	    
		$("#searchBtn").click(function(){
			$('form').attr("action", "/JuvenileCasework/searchOfficerProfile.do?clr=Y&requestOrigin=MA");
			$('form').submit();
		})
		
		//=========== US 86587 ================
		markSignleRecordAsChecked()
});

function markSignleRecordAsChecked(){
	
	var overrideRef=$('input[name=selectedToOverride]');
	
	if(overrideRef && overrideRef.length === 1){
		
		if(overrideRef[0].checked !== true){
			overrideRef[0].checked = true
		}		
	}
	
}