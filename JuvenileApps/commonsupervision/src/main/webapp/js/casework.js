//common functionality across casework, these functions are called in the module specific js.

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
		 	 var msg = dateValidation($(this).val(),fldName,isFutureDteCheckNeeded);
		 	 if(msg != ""){
		 		 alert(msg);
		 		 datefld.val("");
		 		 datefld.focus();
		 	 }
		 } 
	});
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
function dateValidation(fldValue,fldName,isFutureDteCheckNeeded){
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

function spinner(){
	$('body').append( '<div class="overlay">'
			+ '<div class="overlay-background"></div>'
			+ '<div class="se-pre-con"></div>' 
			+ '</div' );
	$(".overlay").css("display", "block");
}
