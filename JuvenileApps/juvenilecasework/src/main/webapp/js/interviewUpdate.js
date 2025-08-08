//functionality used by the interview tab

$(document).ready(function () {
	
	if(typeof $("#interviewDateId")!= "undefined"){	
	
		datePickerSingle( $("#interviewDateId"),"Interview Date", true);
		 
	}
	if(typeof $("#personsInterviewedStrId")!= "undefined"){	
		
		var tmpFld=$("#personsInterviewedStrId").val();
		
		if(tmpFld!=null && tmpFld != "undefined")
		{			
			var selectedPersons=tmpFld.split(':');
			var persons=$("#personsInterviewed");
			if(typeof  selectedPersons!= "undefined"){
				$.each(selectedPersons,function(idx,element){					
					$("#personsInterviewed option").each(function(){ 					
						if($(this).val()==element){ // check if the selected element is matched with the master list							
							  $(this).prop('selected', true); 
						}
						
					});
				});
			}
		}
		//alert($("#personsInterviewedStrId").val());
	}
	$("#userCommentsId").on('keyup mouseout',function(){
		
		textLimit($(this),255);
		return false;		
	});
	$("#summaryNoteId1, #summaryNoteId2").on('keyup mouseout',function(){		
		textLimit($(this),32000);
		return false;
	});	
	
	$("#progressReportId1, #progressReportId2").on('keyup mouseout',function(){		
		textLimit($(this),3000);
		return false;
	});
	
	
	$("#interviewChecklistId").click(function(){	
		
		changeFormActionURL('/JuvenileCasework/displayJuvInterviewChecklist.do?submitAction=Interview Checklist', true);	
		
		//disableSubmitButtonCasework($(this));
		return true;				
				
	});
	
	
	if(typeof $("[name='currentInterview.recordsInventoryIds']") != "undefined" || $("[name='currentInterview.recordsInventoryIds']").val() != "")
	{	
		//var juvLocUnit = $("[name='currentInterview.newAddress']").val();			
		getRecordsInventoryIds();
	}
	

	
	if($("#juvLocUnitId:selected").index != 0)
		checkLocation();
		
	$("[name='currentInterview.recordsInventoryIds']").on("change", function(){		
	
		getRecordsInventoryIds();
	});
	
	$("[name='currentInterview.juvLocUnitId']").on("change",function(){
		checkLocation();
	});
	
	$("#updateSummaryNotesId, #finishId").click(function(){		
		disableSubmitButtonCasework($(this));
		return false;	
	});
	
	function getRecordsInventoryIds()
	{
		var valId=$("[name='currentInterview.recordsInventoryIds']").val();		
		var found=0;
		$("select option:selected").each(function(){
			
			if($(this).val()== "OCO" || $(this).val()== "ODS")			{	
				
				$( '#otherRow').show() ;
				found=1;				
			}			
		});
		if(found==0)
		{			
			$("[name ='currentInterview.otherInventoryRecords']").val("");
		}
		
	}
	
	function checkLocation()
	{
		
		var juvLocUnit = $("#juvLocUnitId :selected").val();		
		if (juvLocUnit == "newaddress") //true if juvenile age less than 21
			$("#newAddressSection").show();
		else
		{
			$("#newAddressSection").hide();
			$("[name='currentInterview.newAddress.streetNum']").val("");
			$("[name='currentInterview.newAddress.streetName']").val("");
			$("[name='currentInterview.newAddress.aptNum']").val("");
			$("[name='currentInterview.newAddress.city']").val("");
			$("[name='currentInterview.newAddress.zipCode']").val("");
			$("[name='currentInterview.newAddress.additionalZipCode']").val("");
			$("[name='currentInterview.newAddress.streetTypeCode']").val("");
			$("[name='currentInterview.newAddress.stateCode']").val("");
			$("[name='currentInterview.newAddress.addressTypeCode']").val("");
			$("[name='currentInterview.newAddress.countyCode']").val("");
			$("[name='currentInterview.newAddress.streetNumSuffixCode']").val("");
				
		}		
		
	}
	
	
	$("#viewInterviewsBtn").click(function(){
        //alert("Button Pressed");
        $('form').attr("action","/JuvenileCasework/displayJuvInterviewList.do?clr=Y&submitAction=Link");
        $('form').submit();
	});

});