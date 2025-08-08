<!-- Javascripts for JIMS2 Addreses ** Alphabetize All Scripts-->
var harrisCountyDropDownValue = "101";

//***Begin COUNTY SELECTION IF STATE IS TEXAS script
function enableCounty( theSelect, countyId )
{
	var countyName = countyId.replace( ".stateId",".countyId" );
	var selectedState = theSelect.options[ theSelect.selectedIndex ].value;
	var theForm = theSelect.form;
	var theCountySelect = document.getElementsByName( countyName );

	if( selectedState == "TX" )
	{
		theCountySelect[ 0 ].disabled = false;

	}
	else
	{
		theCountySelect[ 0 ].selectedIndex = 0;
		theCountySelect[ 0 ].disabled = true;
	}
}
//***End COUNTY SELECTION IF STATE IS TEXAS script


//***Begin GIS ADDRESS VALIDATION RESEARCH LINK
//Opens window that is approx 90% of standard viewing area of 800x600
//Displays GIS address search page within window
function displayResearchWindow( ) 
{
	var leftPos = 40;
	var topPos = 40;
	var winWidth = 720;
	var winHeight = 540;

	if( screen ) 
	{
		winWidth = screen.width * 0.9;
		winHeight = screen.height * 0.8;
		leftPos = ( screen.width - winWidth ) / 2;
		topPos = ( screen.height - winHeight - 50 ) / 2;
	}
	
	ElementWindow = window.open( 'http://www.hctx.net/itc/gis/ais/ ','FirstWin','width='+winWidth+',height='+winHeight+',left='+leftPos+',top='+topPos ); 
	/* ElementWindow = window.open( 'http://test.hcintranet.net/gis/geo1/index.asp','FirstWin','width='+winWidth+',height='+winHeight+',left='+leftPos+',top='+topPos ); */

	return false;
}
//***End GIS ADDRESS VALIDATION RESEARCH LINK


//***Begin VALIDATE ADDRESS ACTION script
/* need to add zip code range validation to this function */
function validateAddrAction( formName, tURL, addrIndex, aFieldPrefix, inputPage, doSubmit )
{
	var myForm = ( document.getElementsByName( formName ) )[ 0 ];
	var returnVal = validateAddress( formName, addrIndex, aFieldPrefix );

	if( ! returnVal )
	{
		return false;
	}

	myForm.inputPage.value = inputPage;
	myForm.action = tURL;

	if( doSubmit )
	{
		myForm.submit( );
	}
	
	return true;
}
//***End VALIDATE ADDRESS ACTION script


//***Begin VALIDATE ADDRESS FORM ACTION FOR STATE ID script
/**
* This functions uses non-standard value of 'stateId'.  'state' is considered the standard value.
*/
function changeAddrFormActionURL( formName, tURL, addrIndex, inputPage, doSubmit )
{
	var state = "";
	var streetName = "";
	var strNum = "";
	var city = "";
	var zipCode = "";
	var zipCode2 = "";

	var msg = "";
	var numbers = /^[ 0-9 ]*$/;
	var streetNumRegex = /^[ a-zA-Z0-9-\/  ]+$/;

	var zipre = /^[ 0-9 ]{5}$/;
	var addzipre = /^[ 0-9 ]{4}$/;
	if( addrIndex == null || addrIndex.toString( ) == "" )
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
		else if( (streetNumRegex.test( strNum ) == false) || ( (isNaN( strNum ) == false) && (strNum <= 0) ) )
		{
			streetNumField.focus( );
			msg += "Street Number must be a non-zero alphanumeric to validate.\n";
		}
		else if( isNaN( strNum.charAt( 0 ) ) )
		{
			streetNumField.focus( );
			msg += "The first character in Street Number must be numeric to validate.\n";
		}

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

		if( state == null  ||  state != "TX" )
		{
			if( msg == "" )
			{
				document.forms[ 0 ].stateId.focus( );
			}
			msg += "State must be 'TEXAS' to validate address.\n";
		}

		if( msg != "" )
		{
			alert( msg );
			return false;
		}
		
		document.forms[ 0 ].validAddrNum.value = addrIndex;
		document.forms[ 0 ].validStreetNum.value = strNum;
		document.forms[ 0 ].validStreetName.value = document.forms[ 0 ].streetName.value;
		document.forms[ 0 ].validZipCode.value = zipCode;
	}
	else
	{
		streetNum = 'associateAddresses[' + addrIndex + '].streetNum';
		streetName = 'associateAddresses[' + addrIndex + '].streetName';
		city = 'associateAddresses[' + addrIndex + '].city';
		stateID = 'associateAddresses[' + addrIndex + '].stateId';
		zipCode = 'associateAddresses[' + addrIndex + '].zipCode';
		zipCode2 = 'associateAddresses[' + addrIndex + '].additionalZipCode';
		aptNum = 'associateAddresses[' + addrIndex + '].aptNum';

		streetNums = document.getElementsByName( streetNum );
		streetNames = document.getElementsByName( streetName );
		cities = document.getElementsByName( city );
		stateIds = document.getElementsByName( stateID );
		zipCodes = document.getElementsByName( zipCode );
		additionalZipCodes = document.getElementsByName( zipCode2 );
		aptNums = document.getElementsByName( aptNum );
		strNum = streetNums[ 0 ].value
		state = stateIds[ 0 ].value.toUpperCase( );

		strNum = Trim( strNum );
		streetNames[ 0 ].value = Trim( streetNames[ 0 ].value );
		cities[ 0 ].value = Trim( cities[ 0 ].value );

		if( strNum == null  ||  strNum == "" )
		{
			if( msg == "" )
			{
				streetNums[0].focus();
			}
			msg += "Street Number is required to validate address.\n";
		}
		else if( (streetNumRegex.test( strNum ) == false) || ( (isNaN( strNum ) == false) && (strNum <= 0) ) )
		{
			streetNums[0].focus();
			msg += "Street Number must be non-zero alphanumeric.\n";
		}
		else if( isNaN( strNum.charAt( 0 ) ) )
		{
			streetNums[0].focus();
			msg += "The first character in Street Number must be numeric to validate.\n";
		}

		if( streetNames[ 0 ].value == null  ||  streetNames[ 0 ].value == "" )
		{
			if( msg == "" )
			{
				streetNames[0].focus();
			}
			msg += "Street Name is required to validate address.\n";
		}

		if( cities[ 0 ].value == null  ||  cities[ 0 ].value == "" )
		{
			if( msg == "" )
			{
				cities[0].focus();
			}
			msg += "City is required to validate address.\n";
		}

		if( state == null  ||  state != "TX" )
		{
			if( msg == "" )
			{
				stateIDs[0].focus();
			}
			msg += "State must be 'TEXAS' to validate address.\n";
		}

		var zipCode = Trim( zipCodes[ 0 ].value );
		if( zipCode == "" )
		{
			if( msg == "" )
			{
				zipCodes[ 0 ].focus( );
			}
			msg += "Zip Code is required.\n";
		}
		else if( !zipre.exec( zipCode ) || zipCode <= 0 )
		{
			if( msg == "" )
			{
				zipCodes[ 0 ].focus( );
			}
			msg += "Zip Code must be a non-zero 5 digit number.\n";
		}


		if( additionalZipCodes[ 0 ] != null )
		{
			var additionalZipCode = Trim( additionalZipCodes[ 0 ].value );
			
			if( additionalZipCode != "" )
			{
				if( !addzipre.exec( additionalZipCode ) )
				{
					if( msg == "" )
					{
						additionalZipCodes[ 0 ].focus( );
					}
					msg += "Zip Code extension must be a 4 digit number.\n";
				}
			}
		}

		if( msg != "" )
		{
			alert( msg );
			return false;
		}

		document.forms[ 0 ].validAddrNum.value = addrIndex;
		document.forms[ 0 ].validStreetNum.value = strNum;
		document.forms[ 0 ].validStreetName.value = streetNames[ 0 ].value;
		document.forms[ 0 ].validZipCode.value = zipCodes[ 0 ].value;
	}

	document.forms[ 0 ].inputPage.value = inputPage;
	document.forms[ 0 ].action = tURL;

	if( doSubmit )
	{
		document.forms[ 0 ].submit( );
	}

	return true;
}


function changeElementValuebyId( elementId, myValue )
{
	myElement = document.getElementById( elementId );
	myElement.value = myValue;

	return true;
}
//***End VALIDATE ADDRESS FORM ACTION FOR STATE ID script


//***Begin VALIDATE COMPLEX ADDRESS script
function validateAddress( formName, addrIndex, aFieldPrefix )
{
	var myForm = ( document.getElementsByName( formName ) )[ 0 ];

	if( myForm == null )
	{
		return false;
	}

	var state = "";
	var streetName = "";
	var strNum = "";
	var city = "";
	var zipCode = "";
	var zipCode2 = "";
	var fieldPrefix = null;
	var msg = "";
	var numbers = /^[ 0-9 ]*$/;
	var streetNumRegex = /^[ a-zA-Z0-9-\/  ]+$/;

	var zipre = /^[ 0-9 ]{5}$/;
	var addzipre = /^[ 0-9 ]{4}$/;

	var streetNameElem;
	var strNumElem;
	var cityElem;
	var stateElem;
	var zipCodeElem;

	var streetNumberElemName = "streetNumber";
	var altStreetNumberElemName = "streetNum";

	if( aFieldPrefix != null  &&  aFieldPrefix != "" )
	{
		fieldPrefix = aFieldPrefix;
	}

	if( fieldPrefix == null  ||  fieldPrefix == "" )
	{
		streetNameElem= ( document.getElementsByName( "streetName" ) )[ 0 ];

		if( ( document.getElementsByName( streetNumberElemName ) )[ 0 ] == null )
			streetNumberElemName=altStreetNumberElemName;

		strNumElem = ( document.getElementsByName( streetNumberElemName ) )[ 0 ];
		cityElem = ( document.getElementsByName( "city" ) )[ 0 ];

		stateElem = ( document.getElementsByName( "stateId" ) )[ 0 ];
		if( stateElem == null )
			stateElem = ( document.getElementsByName( "stateCode" ) )[ 0 ];

		zipCodeElem = ( document.getElementsByName( "zipCode" ) )[ 0 ];
	}
	else
	{
		var elementName = fieldPrefix + ".streetName";
		//  streetNameElem = document.getElementById( elementName );
		streetNameElem = ( document.getElementsByName( elementName ) )[ 0 ];

		elementName = fieldPrefix + "." + streetNumberElemName;

		if( ( document.getElementsByName( elementName ) )[ 0 ] == null )
		{
			streetNumberElemName = altStreetNumberElemName;
			elementName = fieldPrefix + "." + streetNumberElemName;
		}

		strNumElem = ( document.getElementsByName( elementName ) )[ 0 ];
		elementName = fieldPrefix + ".city";
		cityElem = ( document.getElementsByName( elementName ) )[ 0 ];

		elementName = fieldPrefix+".stateId";
		stateElem = ( document.getElementsByName( elementName ) )[ 0 ];
		if( stateElem == null )
			stateElem = ( document.getElementsByName( fieldPrefix+".stateCode" ) )[ 0 ];
			
		//This method will look for both id and name, therefore it's safer to use.
		if( stateElem == null )
			stateElem = document.forms[0][ "stateId" ];
		if( stateElem == null ) 
			stateElem = document.forms[0][ "stateCode" ];
			
		elementName = fieldPrefix + ".zipCode";
		zipCodeElem = ( document.getElementsByName( elementName ) )[ 0 ];
	}

	fieldPrefix = null;

	if( addrIndex == null || addrIndex.toString( ) == "" )
	{
		strNum = Trim( strNumElem.value );
		streetName = Trim( streetNameElem.value );
		city = Trim( cityElem.value );
		state = Trim( stateElem.value.toUpperCase( ) );
		zipCode = Trim( zipCodeElem.value );

		if( strNum == null  ||  strNum == "" )
		{
			if( msg == "" )
			{
				strNumElem.focus( );
			}
			msg += "Street Number is required to validate address.\n";
		}
		else if( (streetNumRegex.test( strNum ) == false) || ( (isNaN( strNum ) == false) && (strNum <= 0) ) )
		{
			strNumElem.focus( );
			msg += "Street Number must be a non-zero alphanumeric to validate.\n";
		}
		else if( isNaN( strNum.charAt( 0 ) ) )
		{
			strNumElem.focus( );
			msg += "The first character in Street Number must be numeric to validate.\n";
		}

		if( zipCode == null  ||  zipCode == "" )
		{
			if( msg == "" )
			{
				zipCodeElem.focus( );
			}
			msg += "Zip Code is required.\n";
		}
		else if( !zipre.exec( zipCode ) )
		{
			if( msg == "" )
			{
				zipCodeElem.focus( );
			}
			msg += "Zip Code must be a 5 digit number.\n";
		}

		if( streetName == null || streetName == "" )
		{
			if( msg == "" )
			{
				streetNameElem.focus( );
			}
			msg += "Street Name is required to validate address.\n";
		}

		if( city == null || city == "" )
		{
			if( msg == "" )
			{
				cityElem.focus( );
			}
			msg += "City is required to validate address.\n";
		}

		if( state == null  ||  state != "TX" )
		{
			if( msg == "" )
			{
				stateElem.focus( );
			}
			msg += "State must be 'TEXAS' to validate address.\n";
		}

		if( msg != "" )
		{
			alert( msg );
			return false;
		}

		myForm.validAddrNum.value = addrIndex;
		myForm.validStreetNum.value = strNum;
		myForm.validStreetName.value = streetNameElem.value;
		myForm.validZipCode.value = zipCode;
		//following code added to check if current address is mailing or billing
	  myForm.currentAddressInd.value = aFieldPrefix;
					
	}
	else
	{
		streetNum = fieldPrefix + '[' + addrIndex + '].' + streetNumberElemName;
		streetName = fieldPrefix + '[' + addrIndex + '].streetName';
		city = fieldPrefix + '[' + addrIndex + '].city';
		stateID = fieldPrefix + '[' + addrIndex + '].stateId';
		zipCode = fieldPrefix + '[' + addrIndex + '].zipCode';
		zipCode2 = fieldPrefix + '[' + addrIndex + '].additionalZipCode';
		aptNum = fieldPrefix + '[' + addrIndex + '].aptNum';
		
		if( (streetNums = document.getElementsByName( streetNum )) == null )
		{
			if( streetNumberElemName == altStreetNumberElemName )
				streetNumberElemName = "streetNumber";

			streetNum = fieldPrefix + '[ ' + addrIndex + ' ].' + streetNumberElemName;
			streetNums = document.getElementsByName( streetNum );
		}

		streetNames = document.getElementsByName( streetName );
		cities = document.getElementsByName( city );
		stateIds = document.getElementsByName( stateID );
		zipCodes = document.getElementsByName( zipCode );
		additionalZipCodes = document.getElementsByName( zipCode2 );
		aptNums = document.getElementsByName( aptNum );
		strNum = streetNums[ 0 ].value
		state = stateIds[ 0 ].value.toUpperCase( );

		strNum = Trim( strNum );
		streetNames[ 0 ].value = Trim( streetNames[ 0 ].value );
		cities[ 0 ].value = Trim( cities[ 0 ].value );
	
		if( strNum == null  ||  strNum == "" )
		{
			if( msg == "" )
			{
				streetNums[0].focus();
			}
			msg += "Street Number is required to validate address.\n";
		}
		else if( (streetNumRegex.test( strNum ) == false) || ( (isNaN( strNum ) == false) && (strNum <= 0) ) )
		{
			streetNums[0].focus();
			msg += "Street Number must be non-zero alphanumeric.\n";
		}
		else if( isNaN( strNum.charAt( 0 ) ) )
		{
			streetNums[0].focus();
			msg += "The first character in Street Number must be numeric to validate.\n";
		}

		if( streetNames[ 0 ].value == null  ||  streetNames[ 0 ].value == "" )
		{
			if( msg == "" )
			{
				streetNames[0].focus();
			}
			msg += "Street Name is required to validate address.\n";
		}

		if( cities[ 0 ].value == null  ||  cities[ 0 ].value == "" )
		{
			if( msg == "" )
			{
				cities[0].focus();
			}
			msg += "City is required to validate address.\n";
		}

		if( state == null  ||  state != "TX" )
		{
			if( msg == "" )
			{
				stateIds[0].focus();
			}
			msg += "State must be 'TEXAS' to validate address.\n";
		}

		var zipCode = Trim( zipCodes[ 0 ].value );
		if( zipCode == "" )
		{
			if( msg == "" )
			{
				zipCodes[ 0 ].focus( );
			}
			msg += "Zip Code is required.\n";
		}
		else if( !zipre.exec( zipCode ) || zipCode <= 0 )
		{
			if( msg == "" )
			{
				zipCodes[ 0 ].focus( );
			}
			msg += "Zip Code must be a non-zero 5 digit number.\n";
		}


		if( additionalZipCodes[ 0 ] != null )
		{
			var additionalZipCode = Trim( additionalZipCodes[ 0 ].value );

			if( additionalZipCode != "" )
			{
				if( !addzipre.exec( additionalZipCode ) )
				{
					if( msg == "" )
					{
						additionalZipCodes[ 0 ].focus( );
					}
					msg += "Zip Code extension must be a 4 digit number.\n";
				}
			}
		}

		if( msg != "" )
		{
			alert( msg );
			return false;
		}

		myForm.validAddrNum.value = addrIndex;
		myForm.validStreetNum.value = strNum;
		myForm.validStreetName.value = streetNames[ 0 ].value;
		myForm.validZipCode.value = zipCodes[ 0 ].value;
		//following code added to check if current address is mailing or billing
		myForm.currentAddressInd.value = aFieldPrefix;
 												       
	}

	return true;
}
//***End VALIDATE COMPLEX ADDRESS script


//***Begin VALIDATE SIMPLE ADDRESS script
function validateSimpleAddress( theForm )
{
	var streetNumElem;
	var streetNameElem;
	var streetTypeElem;
	var aptNumElem;
	var cityElem;
	var stateElem;
	var zipCode;
	
	if( (streetNumElem = ( document.getElementsByName( "streetNumber" ) )[ 0 ]) == null )
		streetNumElem = ( document.getElementsByName( "streetNum" ) )[ 0 ];

	streetNameElem = ( document.getElementsByName( "streetName" ) )[ 0 ];
	
	if( (streetTypeElem = ( document.getElementsByName( "streetTypeId" ) )[ 0 ]) == null )
		streetTypeElem = ( document.getElementsByName( "streetTypeCode" ) )[ 0 ];

	if( (aptNumElem = ( document.getElementsByName( "apartmentNum" ) )[ 0 ]) == null )
		aptNumElem = ( document.getElementsByName( "aptNum" ) )[ 0 ];

	cityElem = ( document.getElementsByName( "city" ) )[ 0 ];
	
	if( (stateElem = ( document.getElementsByName( "stateId" ) )[ 0 ]) == null )
		stateElem = ( document.getElementsByName( "stateCode" ) )[ 0 ];

	zipCode = ( document.getElementsByName( "zipCode" ) )[ 0 ];

	if( (streetNumElem.value > "") ||  (streetNameElem.value > "") || (streetTypeElem.selectedIndex > 0) ||
			(aptNumElem.value > "") || (cityElem.value > "") ||
			(stateElem.selectedIndex > 0) || (zipCode.value > "") ) 
	{
		if( streetNumElem.value == "" ) 
		{
			alert( "Street Number is required if one or more other address fields have entries." );
			streetNumElem.focus( );

			return false;
		}

		if( streetNameElem.value == "" ) 
		{
			alert( "Street Name is required if one or more other address fields have entries." );
			streetNameElem.focus( );

			return false;
		}

		if( cityElem.value == "" ) 
		{
			alert( "City is required if one or more other address fields have entries." );
			cityElem.focus( );

			return false;
		}
		if( stateElem.selectedIndex < 1 )
		{
			alert( "State is required if one or more other address fields have entries." );
			stateElem.focus( );

			return false;
		}

		if( zipCode.value == "" ) 
		{
			alert( "Zip Code is required if one or more other address fields have entries." );
			zipCode.focus( );

			return false;
		}
	}

	return true;
}
//***End VALIDATE SIMPLE ADDRESS script

//***Begin MAPQUEST ACCESS script
function openMapquest(streetNum, street, city, state, zip, country, county)
{
	var inputFlds = streetNum + street + city + state + zip;
	if (inputFlds > '')
	{
		if (country == null  || country == '' )
		{
			country = 'US';
		}

		if (county == null  || county == '' )
		{
			county = 'HARRIS';
		}
 
		var url = 'http://www.mapquest.com/maps/map.adp?address='+streetNum+'+'+street+' +&city='+city +'&state='+state +'&zipcode='+zip +'&county='+county +'&country='+country+'&cid=lfmaplink' ;
		var newWin = window.open( url, "MapquestWindow", "height=600,width=700,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no" ) ;
	}
	else
	{
		alert("No address information to do search.");
	}
}
//***End MAPQUEST ACCESS script
<!--  End Javascripts for JIMS2 ADDRESS View  -->
