<!-- JavaScript for this page only -->
<!-- 06/06/2005  sprakash - this validates the fields with condition in addition to Struts validation -->

<script language=javascript>
document.onkeypress = onKeyPress;
function onKeyPress () {
  var keycode = window.event.keyCode;
  if (keycode == 13) {
     buttonCheck('N');
     return false;
  }
  return true;
}
function buttonCheck(button, theForm)
{  
	if (button == 'N')
  	{
		var theSelect = theForm.warrantActivationStatusId;
		var selectedValue = theSelect.options[theSelect.selectedIndex].value;
		
		var reasonSizeLimit = 255;
		
     	if(selectedValue == "" )
     	{
	    	alert("Activation Status is required.");
			theSelect.focus();
			return false;
	 	} else if ( selectedValue == "RJ" )
		{
			reason = Trim(theForm.unsendNotSignedReason.value);
	    	if (reason == "")
			{
	        	alert("Reason required when activation status is Reject.");
		    	theForm.unsendNotSignedReason.focus();
		    	return false;
			}
			else if(reason.length > reasonSizeLimit)
			{
				alert("Reason exceeds maximum size of "+reasonSizeLimit+" characters.");
				theForm.unsendNotSignedReason.focus();
				return false;
			}
			theForm.unsendNotSignedReason.value = reason;
		} else if ( selectedValue == "US" )
		{
			reason = Trim(theForm.unsendNotSignedReason.value);
	    	if (reason == "")
			{
	        	alert("Reason required when activation status is Unsend.");
		    	theForm.unsendNotSignedReason.focus();
		    	return false;
			}
			else if(reason.length > reasonSizeLimit)
			{
				alert("Reason exceeds maximum size of "+reasonSizeLimit+" characters.");
				theForm.unsendNotSignedReason.focus();
				return false;
			}
			theForm.unsendNotSignedReason.value = reason;
		}
	 	return true;
	 }
}

function Trim(s)
{
	// Remove leading spaces and carriage returns
	while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
	{ 
		s = s.substring(1,s.length); 
	}
	
	// Remove trailing spaces and carriage returns
	while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
	{ 
		s = s.substring(0,s.length-1); 
	}
	
	return s;
}

</script>
