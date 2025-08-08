$(document).ready(function () {
	
	var fld = document.getElementById("selPetfId");
	if (fld != null){
		var val = fld.value;
		var rbs = document.getElementsByName("selectedPetition");
		
		if (rbs != null && rbs.length > 0){
			for (x=0; x<rbs.length; x++){
				if (rbs[x].value == val) {
					var refNum = rbs[x].value.substr(0, 4); 
					rbs[x].checked = true;
					break;
				}
			}
		}
	}
	
	$("#addVOPDetailsBtn").on('click', function(){
		
		var checkedRadioVal = $("input[name='selectedRef4VOP']:checked").val();
		
		if(	checkedRadioVal == null  ){
			alert("Please select a referral to add VOP details." );
			return false;
		}
		$("#selectedRef4VOP").val(checkedRadioVal);
		
		$('form').attr('action','/JuvenileCasework/handleJuvenileProfileVOPsAction.do?submitAction=Add VOP details&selectedReferral='+checkedRadioVal);
	});
	
	$("#nextBtn").on('click', function(){
		
		var checkedRadioVal = $("input[id='referralPetitionRadio']:checked").val();
		if(	checkedRadioVal == null  ){
			alert("Please Select An In-County Original Petition Referral #." );
			return false;
		}
		$("#selectedPetition").val(checkedRadioVal);
		var selectedVOPsevSubType = document.getElementById("selectedVOPsevSubType").value;
		if (selectedVOPsevSubType == 'E')
		{
			var adultIndRadioVal = $("input[id='adultInd']:checked").val();
			if(	adultIndRadioVal == null  ){
				alert("Adult Indicator is required." );
				return false;
			}//locationIndicator bug 163195 changes
			if ($("#locationInd").val() == "") {
				alert("Charge Location Indicator is required.");
				$("#locationIndicator").focus();
				return false;
			}
			if ($("#offCodeID").val() == "") {
				alert("Offense Charge is required.");
				$("#offCodeID").focus();
				return false;
			}
			if ($("#offenseChargeDate").val() == "") {
				alert("Offense Charge Date is required.");
				$("#offCodeID").focus();
				return false;
			}
			if(typeof $("#offenseChargeDate").val() != "undefined"){ 
				//cannot be before referral date and can't  have a future date.
				var offenseChargeDate = new Date($("#offenseChargeDate").val());
				var referralDate = new Date($("#referralDate").val());
				var referralDateFormatted = getFormattedDate(referralDate);
				//if( referralDate.getTime() < offenseChargeDate.getTime() )
				if( referralDate < offenseChargeDate )
				{
					alert("Offense Date should be prior to Referral Date " + referralDateFormatted +". Please modify.");
					//alert("Offense Date should be prior to Referral Date. Please modify.");
					$("#offenseChargeDate").focus();
					return false;
				}
			}
		}
		return true
		});
	
	// validate offense code 
    $("#validateCode").on('click', function (e) {
    	e.preventDefault();
    	var fld = $("#offCodeID").val();
    	var val = Trim(fld);
    	var selectedPet = $("input[id='referralPetitionRadio']:checked").val();
    	if (val == "")
    	{
    		alert("Offense Code Missing, Please Add Offense Code And Retry.")
    		$("#offCodeID").focus();
    		return false;
    	}
    	$("#offCodeID").val(val.toUpperCase());
    	if ( true ) {
    		
			spinner();
			$.ajax({
				url: '/JuvenileCasework/handleJuvenileProfileVOPsAction.do?submitAction=Validate Offense Code',
		        type: 'post',
		        data: $('form#vopOffenseForm').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         		if (isJson(data)) {
		         			alert((JSON.parse(data)).message);
		         		} 
		         	}
		         	
		        }
			});
    	}
    	$("#detailsVOP").show();
		$("#detailsVOP2").show();
		$("#detailsVOP3").show();
		$("#referralPetitionRadio").val(selectedPet);
    	return true;
    });
	
	
	//Offense Charge DATE CALENDAR.
	if(typeof $("#offenseChargeDate") != "undefined"){
		datePickerSingle($("#offenseChargeDate"),"Offense Charge Date",true);
	}
	//the below is commented as we are NOT considering the sevSubType of the selected petition
/*	$("input[id^='referralPetitionRadio']").on('click', function (e) {
		//alert("In on change function here");
		var selected = $("input[id='referralPetitionRadio']:checked").val();
		//localStorage['my.radioSelection'] = this.checked;
		
		var refNum = selected.substr(0, 4); 
		var subSevType = selected.substr(5, 1);
		//$("#petitionNumber").val(selected); 
		//$("#referralPetitionRadio").val(selected); 
	
			if (subSevType == "E"){
				//show hidden fields
				$("#detailsVOP").show();
				$("#detailsVOP2").show();
				$("#detailsVOP3").show();
				
			}else{
				//showFields();
				$("#detailsVOP").hide();
				$("#detailsVOP2").hide();
				$("#detailsVOP3").hide();
			}
		
		//$('form').attr('action','/JuvenileCasework/handleJuvenileProfileVOPsAction.do?submitAction=addChargeDetails');
		//$('form').attr('action','/JuvenileCasework/handleJuvenileProfileVOPsAction.do?submitAction=addChargeDetails&selectedPetition='+selected.val());
		//$('form').submit();
		
	});*/
}); //end of document.ready



function isJson(str) {
		try {
    	json = JSON.parse(str);
	} catch (e) {
    	return false;
	}
	return true;
}

function validateCode()
{
	var fld = document.getElementById("offCodeID");
	var val = Trim(fld.value);
	if (val == "")
	{
		alert("Offense Code required to Validate...")
		fld.focus();
		return false;
	}
	fld.value = val.toUpperCase();
	return true;
}



function getFormattedDate(date) {
	  var year = date.getFullYear();

	  var month = (1 + date.getMonth()).toString();
	  month = month.length > 1 ? month : '0' + month;

	  var day = date.getDate().toString();
	  day = day.length > 1 ? day : '0' + day;
	  
	  return month + '/' + day + '/' + year;
	}


