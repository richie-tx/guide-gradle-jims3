$(document).ready(function () {
	
	//adjudication date
	if(typeof $("#adjDateStrID").val() != "undefined"){
		datePickerSingle($("#adjDateStrID"),"Adjudication Date",true);
	}	
	
	//offense date
	if(typeof $("#offDateStrID").val() != "undefined"){
		datePickerSingle($("#offDateStrID"),"Offense Date",true);
	}
	
	//offense date
	if(typeof $("#pbsDateStrID").val() != "undefined"){
		datePickerSingle($("#pbsDateStrID"),"Probation Start Date",true);
	}
	
	//offense date
	if(typeof $("#preDateStrID").val() != "undefined"){
		datePickerSingle($("#preDateStrID"),"Probation End Date",false);
	}
	
	//person id not required
	$("#personIdNReq").hide();
	
	//#32503 loop through each offenses and set the mandatory and non mandatory field.
	var trnOffenses = $('input[name=selectedTransfer]');
	if(typeof trnOffenses!="undefined"){
		  $.each(trnOffenses,function(index){ 
			  if($("#selectedOffense").val() == $(this).val()){
					if(typeof $("#selectedTransfer-ISCOIN").val()!='undefined'){
						$("#personIdNReq").show();
						$("#personIdReq").hide();
						$("#countySelID").val('755');
						$("#selectedCounty").val('755');						
						$("#countySelID").attr("disabled",true);
					}else{
						$("#personIdNReq").hide();
						$("#personIdReq").show();
						$("#countySelID").val('');
						$("#countySelID").attr("disabled",false);
					}
				}
		  });
	}	
	$("input[name=selectedTransfer]").on('change',function(){
		
		//ends with trait id call update button
		
		$("[id$=ISCOIN]").click(function(){
		//$("[id$=ISCOIN]").on('change',function(){
			if(typeof $("#selectedTransfer-ISCOIN").val()!='undefined')
			{
				 //alert($("#selTrfId").val());
				$("#countySelID").val('755');
				$("#selectedCounty").val('755');
				$("#countySelID").attr("disabled",true);
				$("[id$=ISCOIN]").attr("checked",true);
				$("#personIdNReq").show();
				$("#personIdReq").hide();
			}
			else
			{
				$("#countySelID").val('');
				$("#selectedCounty").val('');
				$("#countySelID").attr("disabled",false);
				$("#personIdReq").show();
				$("#personIdNReq").hide();
			}			
			
		});
		
		//contains TRN
		$("[id*=TRN]").click(function(){
		//$("[id*=TRN]").on('change',function(){
			$("[id*=TRN]").attr("checked",true);
			$("#personIdReq").show();
			$("#personIdNReq").hide();
			$("#countySelID").val('');
			$("#countySelID").attr("disabled",false);
		});
		
	});
	//$("input[name=selectedTransfer]").on('change',function(){
	$("input[name=selectedTransfer]").click(function(){
		
		//ends with trait id call update button
		
		//$("[id$=ISCOIN]").click(function(){
		$("[id$=ISCOIN]").on('change',function(){
			if(typeof $("#selectedTransfer-ISCOIN").val()!='undefined')
			{
				 //alert($("#selTrfId").val());
				$("#countySelID").val('755');
				$("#selectedCounty").val('755');
				$("#countySelID").attr("disabled",true);
				$("[id$=ISCOIN]").attr("checked",true);
				$("#personIdNReq").show();
				$("#personIdReq").hide();
			}
			else
			{
				$("#countySelID").val('');
				$("#selectedCounty").val('');
				$("#countySelID").attr("disabled",false);
				$("#personIdReq").show();
				$("#personIdNReq").hide();
			}			
			
		});
		
		//contains TRN
		//$("[id*=TRN]").click(function(){
		$("[id*=TRN]").on('change',function(){
			$("[id*=TRN]").attr("checked",true);
			$("#personIdReq").show();
			$("#personIdNReq").hide();
			$("#countySelID").val('');
			$("#countySelID").attr("disabled",false);
		});
		
	});
	
	
	$("#add").click(function(){
		var msg="";
		var selMade = false;
		
		var personId = $("#personId").val();
		var isNumeric = $.isNumeric(personId);
		
		if(personId!="" && !isNumeric){ //personId must be numeric.
			alert("Sending County PID is numeric. Please enter a valid PID");
			$("#personId").focus();
			return false;
		}else if(personId!="" && personId.length<7){ //personId must be 7 digits.
			alert("Sending County PID must be 7 digits. Please enter a valid PID.");
			$("#personId").focus();
			return false;
		}
		
		//Required field conditions.
		//personId
		var isRequired = $("#personIdReq").is(":visible");
		if(isRequired){
			if(personId==""){
				msg += "Sending County PID is required.\n";
			}
		}
		
		//countySelID
		var countySelID = $("#countySelID").val();		
		if(countySelID==""){
			msg += "From County selection is required.\n";
		}
		
		//selectedTransfer
		var fld = document.getElementsByName("selectedTransfer");
		if (fld.length == 0)
		{
			alert("There is no Transferred Offense available to add to list.  If list contains one in error, 'Remove' and create a new list entry.");
			return false;
		}	
		
		//Transferred Offense(s)
		for (x=0; x<fld.length; x++)
		{
			if (fld[x].checked == true)
			{
				selMade = true;
				break;
			}	
		}	
		if (selMade == false)
		{
			msg = "Transferred Offense(s) selection is required.\n";
		}
		
		//offCodeID
		var offCodeID = $("#offCodeID").val();
		if(offCodeID==""){
			msg += "Offense Code is required.\n";
		}
		
		if($("#offDateStrID").val()==""){
			msg += "Offense Date is required.\n";
		}
		
		if($("#adjDateStrID").val()==""){
			msg += "Adjudication Date is required.\n";
		}
		
		var dt = $("#offDateStrID").val()+ " 00:00";
		var offDate = new Date(dt);
		var dt = $("#adjDateStrID").val()+ " 00:00";
		var adjDate = new Date(dt);
		
		if (offDate!="" && adjDate!="" && offDate >= adjDate)
		{
			if (msg == "")
			{	
				$("#adjDateStrID").focus();
			}	
			msg += "Offense Date must be previous to Adjudication Date.";		
		}
		
		var sDate = $("#pbsDateStrID").val()+ " 00:00";
		var probSDate = new Date(sDate);
		var eDate = $("#preDateStrID").val()+ " 00:00";
		var probEDate = new Date(eDate);
		
		if (probSDate!="" && probEDate!="" && probSDate >= probEDate)
		{
			if (msg == "")
			{	
				$("#pbsDateStrID").focus();
			}	
			msg += "Probation End Date cannot be prior to Probation Start Date.  Please modify entry.";		
		}
		
		if ( $("#pbsDateStrID").val() != "" && $("#preDateStrID").val() == "" )
		{
			$("#pbsDateStrID").val(getFormattedDate(probSDate));
			msg+="Probation End Date is required if Probation Start Date is provided.";	
		}	
		
		if ($("#pbsDateStrID").val() == "" && $("#preDateStrID").val() != "")
		{
			$("#preDateStrID").val(getFormattedDate(probEDate));
			msg+="Probation Start Date is required if Probation End Date is provided.";			
		}	
		
		if( $("#pbsDateStrID").val() == "" && $("#preDateStrID").val() == ""){
			alert("Probation Start Date and Probation End Date entry is necessary for accurate transferred offense entry.  \n" +
					"Please apply Probation Start and End Dates when available, in the Process Referrals - Update Referral screen.");
		}
		
		if (msg == "")
		{	
			return true;
		}
		alert(msg);
		return false;
	});
	
});

/*function validateInputs()
{
	var msg = "";
	var selMade = false;
	var fld = document.getElementsByName("selectedTransfer");
	if (fld.length == 0)
	{
		alert("There is no Transferred Offense available to add to list.  If list contains one in error, 'Remove' and create a new list entry.");
		return false;
	}	
	for (x=0; x<fld.length; x++)
	{
		if (fld[x].checked == true)
		{
			selMade = true;
			break;
		}	
	}	
	if (selMade == false)
	{
		msg = "Transferred Offense(s) selection is required.\n";
	}
	fld = document.getElementById("countySelID");
	if (fld.selectedIndex == 0)
	{
		fld.focus();
		msg += "From County selection is required.\n";
	}
	fld = document.getElementById("personId");
	val = Trim(fld.value);
	if (val == "")
	{
		if (msg == "")
		{	
			fld.focus();
		}	
		msg += "Person ID is required.\n";
	}
	fld = document.getElementById("offCodeID");
	val = Trim(fld.value);
	if (val == "")
	{
		if (msg == "")
		{	
			fld.focus();
		}	
		msg += "Offense Code is required.\n";
	}
	fld.value = val.toUpperCase();
	// begin date edits
	var offDteValid = 1;
	var adjDteValid = 1;
	fld = document.getElementById("offDateStrID");
	var val = Trim(fld.value);
	fld.value = val;
	var result = validateDate(val, "Offense Date", true);
	if (result != "")
	{
		offDteValid = 0;
		if (msg == "")
		{	
			fld.focus();
		}	
		msg += result;
	}
	fld = document.getElementById("adjDateStrID");
	val = Trim(fld.value);
	fld.value = val;
	var result = validateDate(val, "Adjudication Date", true);
	if (result != "")
	{
		adjDteValid = 0;
		if (msg == "")
		{	
			fld.focus();
		}	
		msg += result;
	}
	if (offDteValid == 1 &&  adjDteValid == 1)
	{
		var dt = document.getElementById("offDateStrID").value + " 00:00";
		var offDate = new Date(dt);
		var dt = document.getElementById("adjDateStrID").value + " 00:00";
		var adjDate = new Date(dt)
		if (offDate > adjDate)
		{
			if (msg == "")
			{	
				document.getElementById("offDateStrID").focus();
			}	
			msg += "Offense Date must be previous to Adjudication Date.";		
		}	
	}	
	if (msg == "")
	{
		return true;
	}
	alert(msg);
	return false;
}*/

/*function validateDate(fldValue, fldName, futureDateEdit)
{
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	if (fldValue == "")
	{
		msg = fldName + " is required.\n";
		return msg;
	}
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
	{
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];

	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldName + " is not a valid date.\n";
		return msg;	
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;	
	} 

    if (month < 1 || month > 12)
    {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if (day < 1 || day > 31) {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
		msg = fldName + " is not valid.\n";
		return msg;	
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
			msg = fldName + " is not valid.\n";
			return msg;	
        }
    }    
    if (futureDateEdit && msg == ""){
		var dt = fldValue + " 00:00";
		var fldDateTime = new Date(dt);
		var curDateTime = new Date();
		if (fldDateTime > curDateTime){
			msg = fldName + " can not be future value.\n";
			return msg;				
		}    	
    }
 	return msg;
}*/

function getFormattedDate(date) {
	  var year = date.getFullYear();

	  var month = (1 + date.getMonth()).toString();
	  month = month.length > 1 ? month : '0' + month;

	  var day = date.getDate().toString();
	  day = day.length > 1 ? day : '0' + day;
	  
	  return month + '/' + day + '/' + year;
	}

function validateCode()
{
	var fld = document.getElementById("offCodeID");
	var val = Trim(fld.value);
	if (val == "")
	{
		alert("Offense Code required to Validate.")
		fld.focus();
		return false;
	}
	fld.value = val.toUpperCase();
	return true;
}
//needed to reset Transfered Offense selection on re-entry from Validate Offense
//Code button and Search for Offense Code link
function setTransOffense()
{
	var fld = document.getElementById("selTrfId");
	if (fld != null){
		var val = fld.value;
		rbs = document.getElementsByName("selectedTransfer");
		if (rbs != null && rbs.length > 0){
			for (x=0; x<rbs.length; x++){
				if (rbs[x].value == val) {
					rbs[x].checked = true;
					break;
				}
			}
		}
	}
	// determine if validation message should display and show alert if message is present
	fld = document.getElementById("valOffMsgId");
	if (fld != null) {
		msg = fld.value;
		if (msg != "") {
			alert(msg);
			if (msg.indexOf("not") > 0) {
				document.getElementById("offCodeID").focus();
			}	
		}	
		fld.value = "";
	}
}