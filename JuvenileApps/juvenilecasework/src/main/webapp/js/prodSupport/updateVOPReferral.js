$(document).ready(function () {	
	
	if(typeof $("#referralDate") != "undefined"){
		datePickerSingle($("#referralDate"),"Date",false);
	}
	
	if(typeof $("#offenseChargeDate") != "undefined"){
		datePickerSingle($("#offenseChargeDate"),"Date",false);
	}
	
	$("#BtnRefresh").on("click",function(){
		//Clear input fields on the form
		$("#juvNum").val("");
		$("#refNum").val("");

		});	
	
	$("#backToVOPQryBtn").on("click",function(){
		$('form').attr('action','/JuvenileCasework/ViewVOPRecordsQuery.do?clr=Y');
		$('form').submit();
		 return true;

	});
	
	$("#validateCode").on("click",function(){
		alert("In Validate Code: /UpdateVOPRecord");
		//$('form').attr('action','/JuvenileCasework/handleJuvenileProfileVOPsAction.do?submitAction=Validate Offense Code');
		$('form').attr('action','/JuvenileCasework/UpdateVOPRecord.do?submitAction=Validate Offense Code');
		$('form').submit();
		 return true;

	});
	

	$("#searchCode").on("click",function(){
		$('form').attr('action','/JuvenileCasework/handleJuvenileProfileVOPsAction.do?submitAction=Search for Offense Code');
		$('form').submit();
		 return true;

	});

	$("#cancel").click(function(){
		$('form').attr("action","DisplayProductionSupportMainPopup.do");
		$('form').submit();
	})
	
	$("#refresh").click(function(){
		$("#juvenileId").val("");
		$("#referralId").val("");
		$("#notice").html("");
	});

	$("#updateVOPBtn").on("click",function(form){

		var msg = '';
		var allowUpdate =  true;
		var refNum 		= $("#referralId").val();
		var refDate		= $("#referralDate").val();
		var vopOffenseCode	= $("#VOPOffenseCode").val();
		var offenseChargeVOP    = $("#offenseChargeVOP").val();
		var offenseChargeDate	= $("#offenseChargeDate").val();
		var inCCountyRefNumVOP	= $("#inCCountyOrigPetitionedRefNumVOP").val();
		var adultIndicator    = $("#adultIndicator").val();
		var locationIndicatorVOP    = $("#locationIndicatorVOP").val();
		var ArrValidLocationIndicator = ["In County","Out Of County","Out Of State"];
		var today = formatDate(new Date());
		var refDateFormatted = formatDate(refDate);
		var offenseChargeDateFormatted = formatDate(offenseChargeDate);
		
		var juvNum = $("#juvenileNumber").val();
		if(juvNum){
			
			if ($("#juvenileNumber").val() != "") {
				if ($("#juvenileNumber").val().trim().length < 6) {
					alert("Invalid Juvenile Number.");
					$("#juvenileNumber").focus();
					return false;
				}

				// juvenile Number
				if ($.isNumeric(juvNum) == false) {
					alert("Invalid Juvenile Number.");
					$("#juvenileNumber").focus();
					return false;
				}							
			}
		}
		
		
		if( refNum == '' ){
			allowUpdate = false;
			//msg = 'Referral Number is Required';
			alert(" Referral Number is Required ");
			$("#referralId").focus();
			return false;
		}else{
			if( refNum.length < 4 ){
				
				allowUpdate = false;
				//msg = 'Referral Number is less than 4';
				alert(" Referral Number is not valid. Please correct. ");
				$("#referralId").focus();
				return false;
			}
			if( !$.isNumeric( refNum )){
				
				allowUpdate = false;
				//msg = 'Referral Number is Not Numeric';
				alert(" Referral Number is Not Numeric ");
				$("#referralId").focus();
				return false;
			}
		}
		
		if( inCCountyRefNumVOP == '' ){
			allowUpdate = false;
			//msg = 'Referral Number is Required';
			alert(" In County Original Petition Referral Number is Required ");
			$("#inCCountyOrigPetitionedRefNumVOP").focus();
			return false;
		}else{
			if( inCCountyRefNumVOP.length < 4 ){
				
				allowUpdate = false;
				//msg = 'Referral Number is less than 4';
				alert("In County Original Petition  Referral Number is not valid. Please correct. ");
				$("#inCCountyOrigPetitionedRefNumVOP").focus();
				return false;
			}
			if( !$.isNumeric( inCCountyRefNumVOP )){
				
				allowUpdate = false;
				//msg = 'Referral Number is Not Numeric';
				alert("In County Original Petition  Referral Number is Not Numeric ");
				$("#inCCountyOrigPetitionedRefNumVOP").focus();
				return false;
			}
		}
		if( refDate == '' ){
			allowUpdate = false;
			alert(" Referral Date is Required");
			//msg = 'Referral Date is Required';
			$("#referralDate").focus();
			return false;
		}
		if( refDateFormatted > today ){
			allowUpdate = false;
			alert(" The referral date cannot be a future date.  \nPlease modify entry.");
			//msg = 'The referral date cannot be a future date.  \nPlease modify entry.';
			$("#refDate").focus();
			return false;
		}
		
		if(typeof $("#offenseChargeDate").val() != "undefined"){ 
			//cannot be before referral date and can't  have a future date.
			var offenseChargeDate = new Date($("#offenseChargeDate").val());
			var referralDate = new Date($("#referralDate").val());
			var referralDateFormatted = getFormattedDate(referralDate);
			if( referralDate < offenseChargeDate )
			{
				//alert("Offense Date should be prior to Referral Date " + referralDateFormatted +". Please modify.");
				alert("Referral Date cannot be prior to Offense Date. \nPlease modify entry.");
				$("#offenseChargeDate").focus();
				return false;
			}
			if( offenseChargeDateFormatted  > today ){
				allowUpdate = false;
				msg = 'offense Charge Date cannot equal a future date.  \nPlease modify entry.';
				$("#offenseChargeDate").focus();
			}
		}
		if( adultIndicator != '' ){
			if( adultIndicator.length < 1 || adultIndicator.length > 1){
				allowUpdate = false;
				alert(" Adult Indicator is not valid. Value must be 0 or 1");
				//msg = 'Adult Indicator is not valid. Value must be 0 or 1';
				$("#adultIndicator").focus();
				return false;
			}
			if( !$.isNumeric( adultIndicator )){
				allowUpdate = false;
				alert(" Adult Indicator value should be 0 or 1.");
				//msg = 'Adult Indicator number should be 0 or 1.';
				$("#adultIndicator").focus();
				return false;
			}
			if (!(adultIndicator == 1 || adultIndicator == 0)){
				alert(" Adult Indicator value must be 0 or 1.");
				//msg = 'Adult Indicator number should be 0 or 1.';
				$("#adultIndicator").focus();
				return false;
			}
			
		}
		if( offenseChargeVOP != '' ){
			if( offenseChargeVOP.length < 6 ){
				allowUpdate = false;
				msg = 'offense Charge is not valid.';
				$("#offenseChargeVOP").focus();
				return false;
			}
		}
		if( vopOffenseCode != '' ){
			if( vopOffenseCode.length < 6 ){
				allowUpdate = false;
				msg = 'VOp Offense Code is not valid.';
				$("#vopOffenseCode").focus();
				return false;
			}
		}
    	if( !allowUpdate ){
    		alert( msg );
    		return false;
			
		 }else{
			 $("#newRefDate").val( $("#referralDate").val() ); //this is not assigning refDate
			 $("#newOffenseDate").val( $("#offenseChargeDate").val() );
			 $("#updateVOPRecord").submit();	
			 if(true)
				 spinner();
	    	 return true;
		 }

		}); 		
});
 	function formatDate(date) {
	 	
 		var newStr = '';
 		if( date > ''){
 			
 			var tempDate = new Date(date).toISOString().substr(0, 10);
	 		newStr = tempDate.replace(/-/g, "");
 		}	 		
	 	return newStr;
 	}
 	function getFormattedDate(date) {
 		  var year = date.getFullYear();

 		  var month = (1 + date.getMonth()).toString();
 		  month = month.length > 1 ? month : '0' + month;

 		  var day = date.getDate().toString();
 		  day = day.length > 1 ? day : '0' + day;
 		  
 		  return month + '/' + day + '/' + year;
 		}
 	

