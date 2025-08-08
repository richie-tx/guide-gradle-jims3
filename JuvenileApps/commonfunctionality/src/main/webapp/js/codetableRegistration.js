/**
 * FOR Codetable Registration also Includes javascript for IE11 changes.
 */

//validations for modify admit flow starts
//JQuery on Dom ONLOAD
$(document).ready(function () {	

	$("#attrVerificationId").change(function(){
		if($("#attrCompoundId").is(":checked") && $("#attrVerificationId").is(":checked"))
		{
			alert("Either Compound or verification can be checked");
			return false;		
		}
		
		if($("#attrVerificationId").is(":checked"))
		{
			$("#compoundMain2").show();
			$('#contextMain').show();
			$('#context1').show();
			$('#context2').show();
		}
		else if(! $("#attrCompoundId").is(":checked"))
		{
			$("#compoundMain2").hide();
			$('#contextMain').hide();
			$('#context1').hide();
			$('#context2').hide();
		}
	
	});
	$("#attrCompoundId").change(function(){	
		if($("#attrCompoundId").is(":checked") && $("#attrVerificationId").is(":checked"))
		{
			alert("Either Compound or verification can be checked");
			return false;		
		}
		if($("#attrCompoundId").is(":checked"))
		{
			$("#compoundMain2").show();	
			$('#contextMain').show();
			$('#typeMain1').show();
			$('#typeMain2').show();	
			$('#context3').show();
			$('#context4').show();
			$('#context1').show();
			$('#context2').show();
			
		} else {		
			$('#compoundMain2').hide();	
			$('#contextMain').hide();
			$('#typeMain1').hide();
			$('#typeMain2').hide();			
			$('#context1').hide();	
			$('#context3').hide();
			$('#context4').hide();		
			$('#context2').hide();
		}
		resetCodetableDisplay();
	});
	
	$("#attrCodetableTypeId").change(function(){
		resetCodetableDisplay();
	});
	
	$("#addSelectedBtn").on('click',function(){
		var retVal = true;
		
		if ($('input[name="selectedAgencies"]:checked').length < 1)
		{
			alert("Please select atleast 1 agency!");
			retVal = false;
		}else{
			
			 $('form').attr('action',"/CommonFunctionality/displayCodeTableRegistrationSummary.do?action=create&submitAction=Add Selected");
			 $('form').submit();
		}

		return retVal;
	});
	
	$("#codetableTypeId").change(function(){
		
		evalType();
	});
	$("input[name=editAttribute]:radio").change(function(){
		
		$('#updateAttrButtonRow').show();
	
	});
	
	
	if(typeof $("#codetableTypeId").val() != "undefined")
		evalType();
		
	function resetCodetableDisplay()
	{
		var codetableType = $("#attrCodetableTypeId").val();
		if(codetableType == _tableSimple){			
			$("#compoundMain2").show();	
			$('#contextMain1').show();	
			$('#contextMain2').show();	
			document.getElementsByName("compoundAttrEntityName")[0].value = _simpleTableEntity;
			document.getElementsByName("compoundAttrEntityName")[0].readOnly=true;	
		}else {			
			//$("#compoundMain2").hide();
			$('#contextMain1').hide();	
			$('#contextMain2').hide();	
			document.getElementsByName("compoundAttrEntityName")[0].value = "";
			document.getElementsByName("compoundAttrEntityName")[0].readOnly=false;					
		}
	}
	
	function evalType(){	
		
		if ($("#codetableTypeId").val() == _tableSimple){			
			$('#contextInstruct').show();
			$('#entityInstruct').hide();
			$('#contextMain').show();		
			document.getElementsByName("codetableEntityName")[0].value = _simpleTableEntity;
			document.getElementsByName("codetableEntityName")[0].readOnly=true;
			document.getElementsByName("codetableContextKey")[0].focus();
		}
		else {			
			$('#contextInstruct').hide();
			$('#entityInstruct').show();
			$('#contextMain').hide();					
			document.getElementsByName("codetableEntityName")[0].value = document.getElementsByName("origEntityName")[0].value;
			document.getElementsByName("codetableEntityName")[0].readOnly=false;
			document.getElementsByName("codetableEntityName")[0].focus();			
		}
	}
	
});