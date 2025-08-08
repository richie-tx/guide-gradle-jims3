<!-- JavaScript - Trait Check -->

function validateAddressOnly(theForm) {
	
	var state = "";
	var streetName = "";
	var strNum = "";
	var city = "";
	var zipCode = "";
	var zipCode2 = "";

	var msg = "";
	var numbers = /^[ 0-9 ]*$/;
	var streetNumRegex = /^[ a-zA-Z0-9 \.'\-  ]+$/;

	var zipre = /^[ 0-9 ]{5}$/;
	var addzipre = /^[ 0-9 ]{4}$/;

	var valid = true;
	
	if(	(theForm.streetNum.value > "") || 
			(theForm.streetName.value > "") || 
			(theForm.city.value > "") ||
			(theForm.stateId.value > "") ||
			(theForm.zipCode.value > "")
	  ) 
	{
		
		
		streetNumField = document.forms[ 0 ].streetNum;
		strNum = Trim( streetNumField.value );
		streetName = Trim( document.forms[ 0 ].streetName.value );
		city = Trim( document.forms[ 0 ].city.value );
		var statefld = document.getElementsByName('stateId');
		if (statefld.length > 0)
		{
			state = Trim( document.forms[ 0 ].stateId.value.toUpperCase( ) );
		} else {
			state = Trim( document.forms[ 0 ].state.value.toUpperCase( ) );
		}
		zipCode = Trim( document.forms[ 0 ].zipCode.value );

		if( strNum == null  ||  strNum == "" )
		{
			if( msg == "" )
			{
				streetNumField.focus( );
			}
			msg += "Street Number is required to validate address.\n";
		}
		else if( (streetNumRegex.test( strNum ) == false) )
		{
			streetNumField.focus( );
			msg += "Street Number must be a non-zero alphanumeric to validate.\n";
		}
//		else if( isNaN( strNum.charAt( 0 ) ) )
//		{
//			streetNumField.focus( );
//			msg += "The first character in Street Number must be numeric to validate.\n";
//		}

		if( zipCode == null  ||  zipCode == "" )
		{
			if( msg == "" )
			{
				document.forms[ 0 ].zipCode.focus( );
			}
			msg += "Zip Code is required.\n";
		}
		else if( !zipre.exec( zipCode ) )
		{
			if( msg == "" )
			{
				document.forms[ 0 ].zipCode.focus( );
			}
			msg += "Zip Code must be a 5 digit number.\n";
		}

		if( streetName == null || streetName == "" )
		{
			if( msg == "" )
			{
				document.forms[ 0 ].streetName.focus( );
			}
			msg += "Street Name is required to validate address.\n";
		}

		if( city == null || city == "" )
		{
			if( msg == "" )
			{
				document.forms[ 0 ].city.focus( );
			}
			msg += "City is required to validate address.\n";
		}

		if( msg != "" )
		{
			alert( msg );
			valid = false;
		}
		
	}
	return valid;
}

function validateAddressFieldsUpdate(theForm)
{
	addressValid = validateAddressOnly(theForm);
	
	if (!addressValid) 
	{
		return false;
	}
	
	return validateJuvenileContactUpdate(theForm);
}

function validateAddressFields(theForm)
{
	addressValid = validateAddressOnly(theForm);
	
	if (!addressValid) 
	{
		return false;
	}

	if( validateJuvenileContactCreate(theForm))
	{
		var theConElem = document.getElementById("relationshipId");
	    theVal = theConElem.value;
	    if(theVal!='PL')
	    {
	    	var theFN = document.getElementById("contactName.firstName");
	     	if(theFN.value == '')
	     	{
	     		alert("First name is required");
	     		return false;
	     	}
	     }
	     return validateTheNameFields(theForm);
	}
	else
	{
		return false;
	}
 }
 
 function hideFNRequired()
 {
	var theConElem = document.getElementById("relationshipId");
	theVal = theConElem.value;

   if(theVal != 'PL')
   {
 	showHide( 'fnreq', true );
 	showHide( 'fnreqPlacement', false );
   }
   else
   {
   	showHide( 'fnreqPlacement', true )
	showHide( 'fnreq', false )
   }
 }
 
 
