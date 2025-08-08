//common functionality across casework, these functions are called in the module specific js.
 var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
 
/* Disable button exact replication of javascript function used in app.js*/

function disableSubmitButtonCasework(button){
	var hasURLExtensions = (-1);
	var action = $('form').attr('action');
	var btnVal = button.val();
	var btnName = button.attr('name');
	var hasAmpersendInValue;
	
	hasAmpersendInValue = btnVal.indexOf( "&" );
	if( hasAmpersendInValue != (-1) )
	{
	     btnVal = btnVal.replace( /&/,"%26" );
	} 
	
	hasURLExtensions = action.indexOf( "?" );
	if( hasURLExtensions != (-1) )
	{
		action+="&"+btnName+"="+btnVal;
	}else{
		action+="?"+btnName+"="+btnVal;
	}
	$('form').attr('action',action);
	$('form:first').submit();
	
	button.prop('disabled',true);
	return false;
}

//***Begin change ACTION PATH MAPPING Script
function changeFormActionURL( tURL, doSubmit ){
	$('form').attr('action',tURL);
	if( doSubmit )
	{
		$('form:first').submit( );
	}
	return true;
}

//***Begin NAVIGATION TO ANOTHER PAGE
/*params: location - new page to be redirected to
*/
function goNav( location )
{	
	if( location == "back" )
	{
		history.go( -1 );
	}
	else
	{
		window.location.href = location;
	}
}

//DATE RANGE
/**
 * Date Picker Range
 * @param from – from date
 * @param toDate – to date
 * @param fromName – name of the field like “Begin Date” ,”From Date”.
 * @param toName - name of the field like “End Date” ,”to Date”.
 */
function datePickerRange(from,to,fromName,toName){
		from.datepicker({
	     // defaultDate: "+1w",
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        to.datepicker( "option", "minDate", selectedDate );
	      }
	    }).blur(function(){
	    	 if ($(this).val()!=""){
			 	 var msg = dateValidation($(this).val(),fromName,false); //can be a future date: set to false by default.
			 	 if(msg != ""){
			 		 alert(msg);
			 		from.val("");
			 		from.focus();
			 	 }
			 } 
		});
	    to.datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        from.datepicker( "option", "maxDate", selectedDate );
	      }
	    }).blur(function(){
	    	if ($(this).val()!=""){
			 	 var msg = dateValidation($(this).val(),toName,false); //can be a future date: set to false by default.
			 	 if(msg != ""){
			 		 alert(msg);
			 		to.val("");
			 		to.focus();
			 	 }
			 } 
		});
	 };
	 
/**
 * Single DatePicker function
 * @param datefld
 * @param fldName
 * @param isFutureDteCheckNeeded -(has an additional check as it cannot be a future date).If the field is not a date of birth, and can accept future date, set isDOB to false.
 */
 function datePickerSingle(datefld,fldName,isFutureDteCheckNeeded){
	 datefld.datepicker({
	      //defaultDate: "+1w",
	      changeMonth: true,
	      changeYear: true,	
	 }).on("change", function(){	
		 if ($(this).val()!=""){
		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded,false);
		 	 if(msg != ""){
		 		 alert(msg);
		 		 datefld.val("");
		 		 datefld.focus();
		 	 }
		 } 
	});
 }

 /**
  * Back date only certain no of days
  * @param datefld
  * @param fldName
  * @param isFutureDteCheckNeeded
  */
 function datePickerSingleBackDate(datefld,fldName,isFutureDteCheckNeeded,backDate){
	 datefld.datepicker({
	      //defaultDate: "+1w",
		  minDate: backDate,
	      changeMonth: true,
	      changeYear: true,	
	 }).on("change", function(){	
		 if ($(this).val()!=""){
		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded,false);
		 	 if(msg != ""){
		 		 alert(msg);
		 		 datefld.val("");
		 		 datefld.focus();
		 	 }
		 } 
	});
 }
 
 /**
  * Added for court.
  * Back date only certain no of days and max date is current date.
  * @param datefld
  * @param fldName
  * @param isFutureDteCheckNeeded
  */
 function datePickerSingleMinMaxDte(datefld,fldName,isFutureDteCheckNeeded,backDate,backDateStr){
	 datefld.datepicker({
	      //defaultDate: "+1w",
		  maxDate:  new Date(),
		  minDate: backDate,
	      changeMonth: true,
	      changeYear: true,	
	 }).on("change", function(){	
		 if ($(this).val()!=""){
		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded,false);
		 	 //back Date Check.
		 	 var courtDate = new Date($(this).val());
		 	 var filingDate = new Date(backDate);
		 	 if(courtDate<filingDate){
			 	msg=fldName + " cannot be before "+backDateStr+".";
			 }
		 	 if(msg != ""){
		 		 alert(msg);
		 		 datefld.val(""); //clear the value to avoid focus issue.
		 		 datefld.focus();
		 	 }
		 } 
	});
 }
 
/**
 * Added for court
 * With Back Date
 * @param datefld
 * @param fldName
 * @param isFutureDteCheckNeeded
 * @param holidayList
 * @param backDate
 * @param backDateStr
 */
 function datePickerSingleHolidaysWithBackDate(datefld,fldName,isFutureDteCheckNeeded,holidayList,backDate,backDateStr){
	 datefld.datepicker({
 		  minDate: backDate,
		  beforeShowDay: $.datepicker.noWeekends,
	      changeMonth: true,
	      changeYear: true,	
	 }).on("change", function(){	
		 if ($(this).val()!=""){
			 var selectedDate = new Date($(this).val());
		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded,true);
		 	//if(typeof $('#xEscapeDate').val()!="" && returnDate < new Date(xEscapeDate)){
		 	 var courtDate = new Date($(this).val());
		 	 var filingDate = new Date(backDate);
		 	 if(courtDate<filingDate){
			 		msg=fldName + " cannot be before "+backDateStr+".";
			 	 }
		 	 //holiday check
		 	$.each(jQuery.parseJSON(holidayList), function(idx, obj) {
		 		var holidayYear = (obj.code).substring(0,4);
		 		var holidayMonth = (obj.code).substring(4,6);
		 		var holidayDay = (obj.code).substring(6,8);
		 		var holidayDate = new Date(holidayMonth+"/"+holidayDay+"/"+holidayYear);
		 		if(holidayDate.getTime()==selectedDate.getTime()){
		 			var description = $( "#holidayList selectedDate" ).text();
		 			msg=fldName+ " cannot be a Holiday (" +obj.description + "), Please correct.";
		 			datefld.val("");
		 			datefld.focus();
		 			return false;
		 		}
			});
		 	 if(msg != ""){
		 		 alert(msg);
		 		 datefld.val(""); //clear the value to avoid focus issue.
		 		 datefld.focus();
		 	 }
		 } 
	});
 }
 
 
 /**
  * Added for court
  * Max Date 1960
  * @param datefld
  * @param fldName
  * @param isFutureDteCheckNeeded
  * @param holidayList
  * @param backDate
  * 
  */
  function datePickerSingleHolidaysMaxDate(datefld,fldName,isFutureDteCheckNeeded,holidayList,backDate){
 	 datefld.datepicker({
  		  minDate: backDate,
 		  beforeShowDay: $.datepicker.noWeekends,
 	      changeMonth: true,
 	      changeYear: true,	
 	 }).on("change", function(){	
 		 if ($(this).val()!=""){
 			 var selectedDate = new Date($(this).val());
 		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded,true);
 		 	 //holiday check
 		 	$.each(jQuery.parseJSON(holidayList), function(idx, obj) {
 		 		var holidayYear = (obj.code).substring(0,4);
 		 		var holidayMonth = (obj.code).substring(4,6);
 		 		var holidayDay = (obj.code).substring(6,8);
 		 		var holidayDate = new Date(holidayMonth+"/"+holidayDay+"/"+holidayYear);
 		 		if(holidayDate.getTime()==selectedDate.getTime()){
 		 			var description = $( "#holidayList selectedDate" ).text();
 		 			msg=fldName+ " cannot be a Holiday (" +obj.description + "), Please correct.";
 		 			datefld.val("");
 		 			datefld.focus();
 		 			return false;
 		 		}
 			});
 		 	 if(msg != ""){
 		 		 alert(msg);
 		 		 datefld.val(""); //clear the value to avoid focus issue.
 		 		 datefld.focus();
 		 	 }
 		 } 
 	});
  }
 
/**
 * Added for court
 * Without Back Date.
 * @param datefld
 * @param fldName
 * @param isFutureDteCheckNeeded
 * @param holidayList
 */
 function datePickerSingleHolidays(datefld,fldName,isFutureDteCheckNeeded,holidayList){
	 datefld.datepicker({
		  beforeShowDay: $.datepicker.noWeekends,
	      changeMonth: true,
	      changeYear: true,	
	 }).on("change", function(){	
		 if ($(this).val()!=""){
			 var selectedDate = new Date($(this).val());
		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded,true);
		 	 
		 	 //holiday check
		 	$.each(jQuery.parseJSON(holidayList), function(idx, obj) {
		 		var holidayYear = (obj.code).substring(0,4);
		 		var holidayMonth = (obj.code).substring(4,6);
		 		var holidayDay = (obj.code).substring(6,8);
		 		var holidayDate = new Date(holidayMonth+"/"+holidayDay+"/"+holidayYear);
		 		
		 	/*	var resetYear = (obj.resetDate).substring(0,4);
		 		var resetMonth = (obj.resetDate).substring(4,6);
		 		var resetDay = (obj.resetDate).substring(6,8);
		 		var resetDate = resetMonth+"/"+resetDay+"/"+resetYear;*/
		 	
		 		if(holidayDate.getTime()==selectedDate.getTime()){
		 		//datefld.val(resetDate);
		 			var description = $( "#holidayList selectedDate" ).text();
		 			//alert("Holiday Name: " + obj.description);
		 			alert("Court Date cannot be a Holiday (" +obj.description + "),  Please correct.");
		 			datefld.val("");
		 			datefld.focus();
		 			return false;
		 		}
			});
		 	 if(msg != ""){
		 		 alert(msg);
		 		 datefld.val(""); //clear the value to avoid focus issue.
		 		 datefld.focus();
		 	 }
		 } 
	});
 }
 
 
 function datePickerSingleMaxDte(datefld,fldName,isFutureDteCheckNeeded,backDate,backDateStr,futureDate,futureDateStr){
	 datefld.datepicker({
	      //defaultDate: "+1w",
		  maxDate:  futureDate,
	      changeMonth: true,
	      changeYear: true,	
	 }).on("change", function(){	
		 if ($(this).val()!=""){
		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded);
		 	 //back Date Check.
		 	 var courtDate = new Date($(this).val());
		 	 var filingDate = new Date(backDate);
		 	 if(courtDate<filingDate){
			 		msg=fldName + " cannot be before "+backDateStr+".";
			 }
		 	 //future Date check.
		 	 if(courtDate>futureDate){
			 		msg=fldName + " cannot be after "+futureDateStr+".";
			 }
		 	 if(msg != ""){
		 		 alert(msg);
		 		 datefld.val(""); //clear the value to avoid focus issue.
		 		 datefld.focus();
		 	 }
		 } 
	});
 }
 
 /**
  * validateDateByCourtId
  * @param courtDate
  * added for JUV District Court Conversion
  */
 function validateDateByCourtId(courtDate,holidayList){
	 if (courtDate!=""){
		 var selectedDate = new Date(courtDate);
	 	 var msg = dateValidation(courtDate,"Court Date",false,true);
	 	 
	 	 //holiday check
	 	$.each(jQuery.parseJSON(holidayList), function(idx, obj) {
	 		var holidayYear = (obj.code).substring(0,4);
	 		var holidayMonth = (obj.code).substring(4,6);
	 		var holidayDay = (obj.code).substring(6,8);
	 		var holidayDate = new Date(holidayMonth+"/"+holidayDay+"/"+holidayYear);
	 	
	 		if(holidayDate.getTime()==selectedDate.getTime()){
	 			var description = $( "#holidayList selectedDate" ).text();
	 			alert("Court Date cannot be a Holiday (" +obj.description + "),  Please correct.");
	 			return false;
	 		}
		});
	 	 if(msg != ""){
	 		 alert(msg);
	 	 }
	 } 
 }
 
  /**
  * timeValidationHHMMFormat
  * @param time
  * @returns {Boolean}
  */
 function timeValidationHHMMFormat(time, timeStr){
	 if(typeof time != "undefined"){	 
		 if(timeFormatRegex.test(time.val().trim())== false){
		 	  alert("Please Enter "+timeStr +" In HHMM Format.")
		 	  time.focus();
		 	  return false;
		 }
	 }
 }
  
 function openInNewTab(url) {
	  var win = window.open(url, '_blank');
	  win.focus();
	}
/**
 * Text Limit
 */
function textLimit(field,maxlen) 
{
	if(field.val().length > maxlen) 
	{
		field.val(field.val().substring(0,maxlen));
	    alert("Your input has been truncated to "+maxlen+" characters");
	}
}
/**
 * Date validations function taken from familyMemberLeftnavSearch
 * @param fldValue
 * @param fldName - used for message.
 * @param isDOB - (has an additional check as it cannot be a future date).If the field is not a date of birth, and can accept future date, set isDOB to false.
 * @returns {String}
 */
function dateValidation(fldValue,fldName,isFutureDteCheckNeeded,isWeekendCheck){
	var msg = "";
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;
	}
	
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];
	var numericRegExp = /^[0-9]*$/;
	
	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldName + " is not a valid date.\n";
		return msg;	
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;	
	} 

    if (month < 1 || month > 12) {
		msg = fldName + " has invalid month value.\n";
		return msg;		
    }
    if (day < 1 || day > 31) {
    	msg = fldName + " has invalid day value.\n";
		return msg;		
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
    	msg = fldName + " has invalid day value.\n";
		return msg;	
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
        	msg = fldName + " has invalid day value.\n";
			return msg;	
        }
    }    
    
    if (isFutureDteCheckNeeded && msg == ""){
		var dt = fldValue + " 00:00";
		var fldDateTime = new Date(dt);
		var curDateTime = new Date();
		if (fldDateTime > curDateTime){
			msg = fldName + " cannot be a future date.\n";
			return msg;				
		}
    }
    //sat and sun check 
    if(isWeekendCheck){ //court validation.
	    var selectedDate = new Date(fldValue);
	    var dayOfWeek = selectedDate.getDay();
	    var isWeekend = (dayOfWeek ==0) || (dayOfWeek == 6);
	    if (isWeekend){
	    	msg = fldName + " cannot be a weekend. Please correct.\n";
	    	return msg;	
	    }
    }
        
    return msg;
}

function spinner(){
	$('body').append( '<div class="overlay">'
			+ '<div class="overlay-background"></div>'
			+ '<div class="se-pre-con"></div>' 
			+ '</div' );
	$(".overlay").css("display", "block");
}