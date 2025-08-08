/**
 * Javascript for IE11 changes.
 */

//JQuery on Dom ONLOAD
$(document).ready(function() {
	
		
	if(typeof $("#effectDate") != "undefined")
		datePickerSingle($("#effectDate"));
	
	
	if(typeof $("#startDate") != "undefined" && typeof $("#endDate") != "undefined")
	{		
		datePickerRange( $("#startDate"),$("#endDate"),"From Date","To Date");		
	}
	
	/**
	 * Single DatePicker function
	 * @param datefld
	 */
	 function datePickerSingle( datefld ){
		 datefld.datepicker({
		      defaultDate: new Date(),
		      changeMonth: true,
		      changeYear: true,	
		 })
	 }
	 
	 /**
	  * Date Picker Range
	  * @param from – from date
	  * @param toDate – to date
	  * @param fromName – name of the field like “Begin Date” ,”From Date”.
	  * @param toName - name of the field like “End Date” ,”to Date”.
	  */
	 function datePickerRange(from,to,fromName,toName){
	 		from.datepicker({
	 	      defaultDate: new Date(),
	 	      changeMonth: true,
	 	      changeYear: true,
	 	      onClose: function( selectedDate ) {
	 	        to.datepicker( "option", "minDate", selectedDate );
	 	      }
	 	    }).blur(function(){
	 	    	 if ($(this).val()!==''){
	 			 	 var msg = validateDate($(this).val(),fromName,false); //can be a future date: set to false by default.
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
	 	    	if ($(this).val()!==''){
	 			 	 var msg = validateDate($(this).val(),toName,false); //can be a future date: set to false by default.
	 			 	 if(msg != ""){
	 			 		 alert(msg);
	 			 		to.val("");
	 			 		to.focus();
	 			 	 }
	 			 } 
	 		});
	 	 };
	
/**
 * Date validations function taken from familyMemberLeftnavSearch
 * @param fldValue
 * @param fldName - used for message.
 * @param isDOB - (has an additional check as it cannot be a future date).If the field is not a date of birth, and can accept future date, set isDOB to false.
 * @returns {String}
 */
function validateDate(fldValue,fldName,isFutureDteCheckNeeded){
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
			msg = fldName + " can not be future value.\n";
			return msg;				
		}
    }
    return msg;
} 
	
}); // jquery ends here