function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  		alert("Your input has been truncated to " + maxlen + " characters.");
	}

	if (field.value.length > maxlen)
	{
  		field.value = field.value.substring(0, maxlen);
	}
} 

function radioCallback( yesOrNo )
{
	if( yesOrNo == "yes" )
	{
	  	show('detailTitle', HIDE_ITEM, 'row') ;
	  	show('overrideTitle', SHOW_ITEM, 'row') ;
		show('overrideInstruction', SHOW_ITEM, 'row') ; 
    	show('overrideEditRow', SHOW_ITEM, 'row') ;
    	show( 'overRideTypeRow', SHOW_ITEM, 'row' ) ;
	}
	else
	{
	  	show('detailTitle', SHOW_ITEM, 'row') ;
	  	show('overrideTitle', HIDE_ITEM, 'row') ;
    	show('overrideEditRow', HIDE_ITEM, 'row'); 
		show('overrideInstruction', HIDE_ITEM, 'row') ;
		show( 'overRideTypeRow', HIDE_ITEM, 'row' ) ;
	}
}

function overRideRadioCallback( tRadio )
{
	if( tRadio == 'text' )
	{
		show('overrideTextAreaRow', SHOW_ITEM, 'row') ; 
	}
	else
	{
		show('overrideTextAreaRow', HIDE_ITEM, 'row') ; 
	}
}

function getSelectedRadio(buttonGroup) 
{
	// returns the array number of the selected radio button or -1 if no button is selected
	if (buttonGroup[0]) 
	{ // if the button group is an array (one button is not an array)
		for (var i = 0; i < buttonGroup.length; i++) 
		{
			if (buttonGroup[i].checked) 
		    {
				return i ;
			}
		}
	} 
	else if (buttonGroup.checked) 
	{ 
		return 0; 
	} // if the one button is checked, return zero
	
	// if we get to this point, no radio button is selected
	return -1;
} // Ends the "getSelectedRadio" function

function getSelectedRadioValue(buttonGroup) 
{
  // returns the value of the selected radio button or "" if no button is selected
	var i = getSelectedRadio(buttonGroup);
	if (i == -1) 
	{
		return "";
	} 
	else 
	{
    if (buttonGroup[i]) 
    { // Make sure the button group is an array (not just one button)
       return buttonGroup[i].value;
    } 
    else 
    { // The button group is just the one button, and it is checked
       return buttonGroup.value;
    }
  }
} // Ends the "getSelectedRadioValue" function

function checkOverrideRadioButtons() 
{
	var recomOverid = getSelectedRadioValue(document.forms[0].recommendationOverridden);
	var rtn = true ;
	
	if (recomOverid == 'true') 
	{
		var radioButtonArr = getSelectedRadioValue(document.forms[0].overRiddenReasonCd);
		var commentRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2f\-]*$/;
		if (radioButtonArr != null )
		{ 
		    if( radioButtonArr.length == 0)
		    {
				alert('Please select an Override Reason.');
				rtn = false; 
		    }
		    else if( radioButtonArr == "RO04" )
		    { // we're looking at the "Release Override - Other (Explain)" radio button.
		    	document.forms[0].overRiddenReasonDetentionOther.value = '';
			    if( document.forms[0].overRiddenReasonOther.value.length < 30 )
			    {
			    	alert('Please provide an Release Override Explanation of at least 30 characters.');
			    	rtn = false ;
			    } else if (commentRegExp.test(document.forms[0].overRiddenReasonOther.value,commentRegExp) == false)
			    	{
			    	alert('Release Override Explanation must not begin with blanks or\ncontains one or more of these invalid characters\n ~ ` \! \@ \# \$ \% \^ \* \_ \+ \- \= { } [ ] | \: \" \< \> \? ');
			    	rtn = false ;
			    	}
		    }
		    else if( radioButtonArr == "DO03" )
		    { // we're looking at the "Detention Override - Other (Explain)" radio button.
		    	document.forms[0].overRiddenReasonOther.value = '';
			    if( document.forms[0].overRiddenReasonDetentionOther.value.length < 30 )
			    {
			    	alert('Please provide an Detention Override Explanation of at least 30 characters.');
			    	rtn = false ;
			    } else if (commentRegExp.test(document.forms[0].overRiddenReasonDetentionOther.value,commentRegExp) == false)
			    	{
			    		alert('Detention Override Explanation must not begin with blanks or\ncontains one or more of these invalid characters\n ~ ` \! \@ \# \$ \% \^ \* \_ \+ \- \= { } [ ] | \: \" \< \> \? ');
			    		rtn = false ;
			    	}	
		    } else {
		    	document.forms[0].overRiddenReasonOther.value = '';
		    	document.forms[0].overRiddenReasonDetentionOther.value = '';
		    }

		}
	} 
	else 
	{
		return;
	}

	return( rtn ) ;
}