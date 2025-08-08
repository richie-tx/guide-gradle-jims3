
//JQuery on Dom ONLOAD
$(document).ready(function () {
	
	if(typeof  $("#probationStartDate") != "undefined"){	
		datePickerSingle($("#probationStartDate"),"Date",false);
	}
	if(typeof  $("#probationEndDate") != "undefined"){	
		datePickerSingle($("#probationEndDate"),"Date",false);
	}
	if(typeof  $("#updtateRefCloseDt") != "undefined"){	
		datePickerSingle($("#updtateRefCloseDt"),"Date",false);
	}
	if ($("#action").val() == "addproDates"){
		localStorage.clear();
	} 
	document.getElementById('submitNext').disabled=false;
	
	//alert($("#hdnDates").val())	
	var retrievedData = $("#hdnDates").val();
	if(retrievedData != null&&retrievedData !='')
	{
	 var array = retrievedData.split(',');
	 for(var i = 0; i < array.length; i++)
     {	
		 var updateRef=$('input[name=selectedToupdate]');
			$.each(updateRef,function(index){
				 if ($(this).attr('id') == ('manageRefNum-'+ array[i])) 
					 $(this).attr('disabled',true);
			});
     }
	 
	}
	$("input[id^='manageRefNum-']").on('change', function (e) 
	{
		var elementName = $(this).attr('name')
		document.getElementById('submitNext').disabled=false;
		var updateRef=$('input[name=selectedToupdate]');
		$.each(updateRef,function(index)
		{
			if ($(this).prop('checked')== true) 
			{
				  document.getElementById('submitNext').disabled=true; //disabled
				  /*var probStart=document.getElementById('probationstartDate-'+$(this).val()).value;//,'MM/dd/yyyy'
				  //formatDate(new Date(),'MM/dd/yyyy')
				  var probEnd=document.getElementById('probationEndDate-'+$(this).val()).value;//,'MM/dd/yyyy'
				  alert(probStart)
				  alert(probEnd)
				  $("#probationStartDate").val(probStart);
				  $("#probationEndDate").val(probEnd);*/
			}
		});
	});
	$("#updateBtn").on('click',function() {
		$("#action").val("updateprobdates");
		var today = formatDate(new Date());		
		updateprobStart=document.getElementById('probationStartDate').value;
		updateprobEnd=document.getElementById('probationEndDate').value;
		updatecloseDate=document.getElementById('updtateRefCloseDt').value;
		var formattedupdateprobStart = formatDate(updateprobStart);
		var formattedupdateprobEnd = formatDate(updateprobEnd);
		var formattedupdatecloseDate = formatDate(updatecloseDate);
		if(updateprobStart==''&&updateprobEnd==''&&updatecloseDate=='')
		{
			alert('Enter details to update')
			return false;
		}
		if(updateprobStart!='')
		{
			if(updateprobEnd=='')
			{
				alert('Probation Start Date and Probation End Date is required.')
				return false;				
			}
			if(formattedupdateprobStart>formattedupdateprobEnd)
			{
				alert('Probation Start Date must not equal a date after Probation End Date.  Please modify entry')
				return false;				
			}
			if(formattedupdateprobStart>today)
			{
				alert('Future date is not allowed in Probation Start date.')
				return false;				
			}			
		}
		if(updateprobEnd!='')
		{
			if(updateprobStart=='')
			{
				alert('Probation Start Date and Probation End Date is required.')
				return false;				
			}
			if(formattedupdateprobStart>formattedupdateprobEnd)
			{
				alert('Probation Start Date must not equal a date after Probation End Date.  Please modify entry')
				return false;				
			}
		}
		if(updatecloseDate!='')
		{
			if(formattedupdatecloseDate>today)
			{
				alert('Referral Close Date  should not be a future date.')
				return false;				
			}
		}
		var selectRef=$('input[name=selectedToupdate]');
		var probStart;
		var probEnd;
		var flag="false";
		var closedateflag="false";
		var intakeflag="false";
		var courtDateflag="false";
		var intakeDecisionTJPCFlag = "false";
		var courtDispositionTJPCFlag = "false";
		var intakeDecisionTJPC;
		var courtDispositionTJPC;
		var refs = new Array();
		i=0;
		$.each(selectRef,function(index){
			if($(this).prop('checked')== true)
			{			
				refs[i] = $(this).val();
				probStart=document.getElementById('probationstartDate-'+$(this).val()).value;
				probEnd=document.getElementById('probationEndDate-'+$(this).val()).value;
				referralDate=document.getElementById('referralDate-'+$(this).val()).value;
				intakeDate=document.getElementById('intakeDate-'+$(this).val()).value;
				courtDate=document.getElementById('courtDate-'+$(this).val()).value;
				intakeDecisionTJPC = document.getElementById('intakeDecisionTJPCCode-'+$(this).val()).value;
				courtDispositionTJPC = document.getElementById('finalDispositionTJPCCode-'+$(this).val()).value;
				var formattedreferralDate = formatDate(referralDate);
				var formattedintakeDate = formatDate(intakeDate);
				var formattedcourtDate = formatDate(courtDate);
				console.log("Referral number: " + refs[i] +"formatted court date: " + formattedcourtDate );
				//var formattedupdatecloseDate = formatDate(updatecloseDate);
				if(probStart!=''||probEnd!=''){
					flag="true";
				}
				if(formattedupdatecloseDate<formattedreferralDate){
					closedateflag="true";
				}
				if(formattedupdatecloseDate<formattedintakeDate){
					intakeflag="true";
				}
				if(formattedupdatecloseDate<formattedcourtDate){
					courtDateflag="true";
				}
				if ( courtDate == ""
						&& intakeDecisionTJPC === "000" ){
					intakeDecisionTJPCFlag = "true"
					
				}
				if ( courtDate != "" ){
					if ( Number ( courtDispositionTJPC ) > 99 ) {
						courtDispositionTJPCFlag = "true";
					}
				}
				i++;
			}			
		});
		
		if(i==0)
		{
			alert('Please select referral(s) to update.')
			return false;
		}
		else
		{	
			if(updatecloseDate!=''&&closedateflag=="true")
			{			
				alert('Referral closed date cannot be prior to referral date.  Please try again. ')
				return false;
			}
			if(updatecloseDate!=''&&intakeflag=="true")
			{			
				alert('Referral closed date cannot be prior to intake date.  Please try again. ')
				return false;
			}
			if(updatecloseDate!=''&&intakeDecisionTJPCFlag=="true")
			{			
				alert('The selected referral does not qualify for closure at this time. Please contact Data Corrections for assistance.');
				return false;
			}
			
			if(updatecloseDate!=''&&courtDispositionTJPCFlag=="true")
			{			
				alert('The selected referral does not qualify for closure at this time. Please contact Data Corrections for assistance.');
				return false;
			}
			
			if(updatecloseDate!=''&&courtDateflag=="true")
			{			
				alert('Referral closed date cannot be prior to court decision date.  Please try again. ')
				return false;
			}
			if(flag=="true"&& i>1)
			{			
				alert('Probation Start Date and Probation End Date entries detected.  Please update referral entry individually. ')
				return false;
			}
			else
			{			
				/*localStorage.setItem("selectedRefs", JSON.stringify(refs));				
				var strRefs=JSON.stringify(refs);*/
				
				$('form').attr('action','/JuvenileCasework/processCasefileClosing.do?submitAction=Update&selectedReferrals='+refs);
				$('form').submit();
			}
		}
	});
	function formatDate(date) {
	 	
 		var newStr = '';
 		if( date > ''){
 			
 			var tempDate = new Date(date).toISOString().substr(0, 10);
	 		newStr = tempDate.replace(/-/g, "");
 		}	 		
	 	return newStr;
 	}
	$("#submitNext").on('click',function() 
	{
		/*if( confirm( "Submission of Referral Dates cannot be undone in the Closing Screen.  Do you wish to continue?" ) )
		{*/
			$('form').attr('action','/JuvenileCasework/processCasefileClosing.do?submitAction=Next');
			$('form').submit();
			return true;
        //} 
		/*else
		{
			return false;
		}*/
	});
	$("#btnCancel").on('click',function() {
		//alert('inside back js-dual')
		$('form').attr('action','/JuvenileCasework/processCasefileClosing.do?submitAction=Cancel');
		$('form').submit();
	});	
	$("#refreshBtn").on('click',function() {
		$("#probationStartDate").val('');
		$("#probationEndDate").val('');
		$("#updtateRefCloseDt").val('');
		$('form').attr('action','/JuvenileCasework/processCasefileClosing.do?submitAction=Refresh');
		$('form').submit();
	});
});

