<!-- Javascripts for JIMS2 App View ** Alphabetize All Scripts-->
//timesout in 29 minutes 60 * 29 *1000 == 1740000 ms
setTimeout("alert('Your session is about to expire. Please click Submit / Save & Continue / Next button to maintain session.');",1740000);
//setTimeout(function(){alert("Your session is about to expire in 5 minutes. Please click Submit / Save & Continue / Next button before session expires.")}, 3300000);
//setTimeout(function(){alert("Your session is about to expire in 2 minutes. Please click Submit / Save & Continue / Next button before session expires.")}, 3480000);

//***Begin change ACTION PATH MAPPING Script
function changeFormActionURL( formName, tURL, doSubmit )
{
	var myForm = document.getElementById( formName );

	if( myForm == null )
	{
		myForm = document.getElementsByName( formName )[0];
	}

	if(myForm == null)
        myForm = formName;


	myForm.action = tURL;
	if( doSubmit )
	{
		myForm.submit( );
	}

	return true;
}




function changeElementValuebyId( elementId, myValue )
{
	myElement = document.getElementById( elementId );
	myElement.value = myValue;

	return true;
}
//***End change ACTION PATH MAPPING Script


//***Begin AUTOTAB SCRIPT
function autoTab( input,len ) 
{
	if( input.value.length >= len )
	{
	  input.value = input.value.slice( 0, len );
	  input.form[( getIndex( input )+1 ) % input.form.length ].focus( );
	}

	return true;
}


// Check that a string contains only numbers
function isNumeric( string, ignoreWhiteSpace ) 
{
	if( string.search ) 
	{
	  if(( ignoreWhiteSpace && string.search( /[ ^\d\s ]/ ) != -1 ) || 
	  		( !ignoreWhiteSpace && string.search( /\D/ ) != -1 ) )
	  {
	  	return false;
	  }
	}
	
	return true;
}
//***End AUTOTAB SCRIPT


//***Begin CHANGE CASE - changes form field's value to Upper or Lower Case
	/*params: luFlag string "lower' or "upper" 
	/*params: el - the form element object ie: input type=text onblur=fx( this ) 
	*/
function changeCase( luFlag, el )
{	
	var val = el.value;
	
	if( luFlag == "lower" )
	{
		val = val.toLowerCase( );
	}
	else if( luFlag == "upper" )
	{				
		val = val.toUpperCase( );
	}
	else
	{
		alert( "EXCEPTION in changeCase( ) - Pass Paramter 'lower' or 'upper'" );
	}
	
	el.value = val;
}
//***End CHANGE CASE - changes form field's value to Upper or Lower Case


//***Begin parent CHECKBOX script
//* When a parent checkbox is checked, all of its children should be checked too
function checkChildren( el )
{
	var theForm = el.form;
	var thisCheckName = el.name;
	var childOfString = "childOf" + el.id;
	var objCheckBoxes = theForm.elements[ thisCheckName ];
	var countCheckBoxes = objCheckBoxes.length;
 
	for( var i = 0; i < countCheckBoxes; i++ )
	{
		var checkboxId = objCheckBoxes[ i ].id;

		if( checkboxId.indexOf( childOfString ) == 0 )
		{
		  if( el.checked )
		  {
		   	document.getElementById( objCheckBoxes[ i ].id ).checked = true;
		   	document.getElementById( objCheckBoxes[ i ].id ).editable = false;
		  }
		  else
		  {
		  	document.getElementById( objCheckBoxes[ i ].id ).checked = false;
		  }
		}
	}
}
//***End parent CHECKBOX script

// ***Begin Get Current DATE 
function getCurrentMMDDYYYYDate(elementName){
 var mydate=new Date()
 var year=mydate.getYear()
 if (year < 1000){
  year+=1900
 } 
 var month=mydate.getMonth()
 var daym=mydate.getDate()
 if (daym<10){
  daym="0"+daym
 } 
 var montharray=new Array("01","02","03","04","05","06","07","08","09","10","11","12")
 
 var currentDateStr = montharray[month]+"/"+daym+"/"+year;
    var elem= document.getElementsByName(elementName)[0];
    elem.value=currentDateStr;
 return elem.value;
}
// ***End Get Current DATE 


// ***Begin DATE ENTRY IN ANY FORMAT
function checkdate( objName )
{
	var datefield = objName;
	
	if( chkdate( objName ) == false )
	{
		datefield.select( );
		alert( "That date is invalid.  Please enter a valid date in any format." );
		datefield.focus( );

		return false;
	}
	
	return true ;
}


function chkdate( objName )
{
	var strDatestyle = "US"; //United States date style
	var strDate;
	var strDateArray;
	var strDay;
	var strMonth;
	var strYear;
	var intday;
	var intMonth;
	var intYear;
	var booFound = false;
	var datefield = objName;
	var strSeparatorArray = new Array( "-"," ","/","." );
	var intElementNr;
	var err = 0;
	var strMonthArray = new Array( 12 );

	strMonthArray[ 0 ] = "Jan";
	strMonthArray[ 1 ] = "Feb";
	strMonthArray[ 2 ] = "Mar";
	strMonthArray[ 3 ] = "Apr";
	strMonthArray[ 4 ] = "May";
	strMonthArray[ 5 ] = "Jun";
	strMonthArray[ 6 ] = "Jul";
	strMonthArray[ 7 ] = "Aug";
	strMonthArray[ 8 ] = "Sep";
	strMonthArray[ 9 ] = "Oct";
	strMonthArray[ 10 ] = "Nov";
	strMonthArray[ 11 ] = "Dec";

	strDate = datefield.value;
	if( strDate.length < 1 ) 
	{
		return true;
	}

	for( intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++ ) 
	{
		if( strDate.indexOf( strSeparatorArray[ intElementNr ] ) != (-1) ) 
		{
			strDateArray = strDate.split( strSeparatorArray[ intElementNr ] );
			if( strDateArray.length != 3 ) 
			{
				err = 1;

				return false;
			}
			else 
			{
				strDay = strDateArray[ 0 ];
				strMonth = strDateArray[ 1 ];
				strYear = strDateArray[ 2 ];
			}

			booFound = true;
	   }
	}

	if( ! booFound ) 
	{
		if( strDate.length > 5 ) 
		{
			strDay = strDate.substr( 0, 2 );
			strMonth = strDate.substr( 2, 2 );
			strYear = strDate.substr( 4 );
		}
		else 
		{
			strDay = ""	
			strMonth = ""
			strYear = ""
		}   
	}

	if( strYear.length < 2 ) 
	{
		err = 2;

		return false;
	}

	if( (strYear.length != 2) &&  (strYear.length != 4) ) 
	{
		err = 2;

		return false;
	}

	if( strYear.length == 2 ) 
	{
		if( strYear < 11 ) 
		{
			strYear = '20' + strYear;
		}
		else 
		{
			strYear = '19' + strYear;
		}
	}

	if( strDatestyle == "US" ) 
	{
		strTemp = strDay;
		strDay = strMonth;
		strMonth = strTemp;
	}

	intday = parseInt( strDay, 10 );
	if( isNaN( intday ) ) 
	{
		err = 2;

		return false;
	}

	intMonth = parseInt( strMonth, 10 );
	if( isNaN( intMonth ) ) 
	{
		for( i = 0; i < 12; i++ ) 
		{
			if( strMonth.toUpperCase( ) == strMonthArray[ i ].toUpperCase( ) ) 
			{
				intMonth = i +1;
				strMonth = strMonthArray[ i ];
				i = 12;
		  }
		}

		if( isNaN( intMonth ) ) 
		{
			err = 3;

			return false;
	  }
	}// if( isNaN( intMonth ) ) 


	intYear = parseInt( strYear, 10 );
	if( isNaN( intYear ) ) 
	{
		err = 4;

		return false;
	}

	if( intMonth > 12 || intMonth < 1 ) 
	{
		err = 5;
		return false;
	}

	if( (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || 
			intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1) ) 
	{
		err = 6;

		return false;
	}

	if( (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) &&
			(intday > 30 || intday < 1) ) 
	{
		err = 7;

		return false;
	}

	if( intMonth == 2 ) 
	{
		if( intday < 1 ) 
		{
			err = 8;

			return false;
		}

		if( LeapYear( intYear ) == true ) 
		{
			if( intday > 29 ) 
			{
				err = 9;
	
				return false;
			}
		}
		else if( intday > 28 ) 
		{
			err = 10;

			return false;
		}
	}

	strMonth = intMonth.toString( );
	if( strMonth.length < 2 )
	{
		strMonth = "0" + strMonth;
	}	

	strDay = intday.toString( );
	if( strDay.length < 2 )
	{
		strDay = "0" + strDay;
	}
	datefield.value = strMonth + "/" + strDay +"/" + strYear;
	
	return true;
}

  
function LeapYear( intYear ) 
{
	if( (intYear % 400 == 0)  ||  ((intYear % 4 == 0) && (intYear % 100 != 0)) )
	{
		return true; 
	}

	return false;
}
//***End DATE ENTRY IN ANY FORMAT


//***Begin DISABLE KEYS Script
function disableEvent( evt )
{
	evt.returnValue = false; 
	evt.cancel = true;
}

// Passing in a boolean true for disableEnter will disable the Enter Key
// Use in conjection with an event such as : onKeyDown="checkEnterKey( event,true )"
function checkEnterKey( evt,disableEnter )
{
	var currentElement = evt.srcElement;
	var key = evt.keyCode; 

	if( event.ctrlKey == true )
	{
		if( key == 78  ||  key == 110 )
		{  // the n or N key
			alert( "Sorry opening a new browser window is not allowed." );
			disableEvent( evt );
	
			return false;
		}	
	}
    
	if( disableEnter == true &&  key == 13 )
	{
		if( currentElement != null )
		{
			if( currentElement.type == 'textarea' )
			{
				// alert( "Have a text area don't do anything with the enter key" );

				return true;
			}
		}

		alert( "This page has multiple submit buttons. \n\nPlease select appropriate submit button." );
		disableEvent( evt );

		return false;
	}

	return true;
} 

// Passing in a boolean true for disableEnter will disable the Enter Key
// Use in conjection with an event such as : onKeyDown="checkEnterKey( event,true )"
function checkEnterKeyAndSubmit( evt, disableEnter )
{
	var currentElement = evt.srcElement;
	var key = evt.keyCode;

  if( event.ctrlKey == true )
  {
  	if( key == 78  ||  key == 110 )
  	{  // the n or N key
   		alert( "Sorry opening a new browser window is not allowed." );
   		disableEvent( evt );

   		return false;
  	}	
  }

  if( disableEnter == true &&  key == 13 )
  {
   	if( currentElement != null )
   	{
   		if( currentElement.type == 'textarea' )
   		{
   			// alert( "Have a text area don't do anything with the enter key" );
   			return true;
   		}
   	}

   	var foundBtn = getSubmitBtn( );
  	if( foundBtn == null )
  	{
			alert( "This page has multiple submit buttons. \n\n Please select appropriate submit button." );
		 	disableEvent( evt );

			return false;
		}
		else
		{
			foundBtn.click( );

			return false;
		}
	}

	return true;
}
//***End DISABLE KEYS Section


//***Begin Get INDEX SCRIPT
function getIndex( input ) 
{
	var index = (-1);
	var i = 0;
	var found = false;
	
	while( i < input.form.length && index == (-1) )
  {
		if( input.form[ i ] == input )
		{
			index = i;
		}
		else
		{
			i++;
		} 
	}
	
	return index;
} 
//***End Get INDEX SCRIPT


//***Begin Disable MULTIPLE SUBMITS Script
// This function disables a submit button after one submit to prevent multible submits and duplicate information.
function disableSubmit( button, form )
{
  var hasURLExtensions = (-1);
  var myAction = form.action;
  var btnVal = button.value;
  var hasAmpersendInValue;

  hasAmpersendInValue = btnVal.indexOf( "&" );
  if( hasAmpersendInValue != (-1) )
  {
     btnVal = btnVal.replace( /&/,"%26" );
  }   

  hasURLExtensions = myAction.indexOf( "?" );
  if( hasURLExtensions != (-1) )
  {
		form.action += "&" + button.name +"=" +btnVal;
  }
  else
  {
		form.action += "?" +button.name +"=" +btnVal;;
  }

  form.submit( );
  button.disabled = true;

  return false;

}
//***End Disable MULTIPLE SUBMITS Script


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
//***End NAVIGATION TO ANOTHER PAGE


//***Begin NEW WINDOW
function newCustomWindow( tURL, windowName, width, height ) 
{
	var widthString = "width=" + width;
	var heightString = "height=" + height;
	var params = "resizable=yes, scrollbars=yes, " + widthString + "," + heightString;

	msgWindow = open( tURL, windowName, params );
	if( msgWindow.opener == null ) 
	{
		msgWindow.opener = self;
	}
	//msgWindow.location.reload(); //refresh pop-up window. bug fix 27558
}
//***End NEW WINDOW
//refresh pop-up window
function newCustomWindowForPhoto( tURL, windowName, width, height ) 
{	
	newCustomWindow(tURL, windowName, width, height)  ;
	msgWindow.location.reload(); //refresh pop-up window. bug fix 27558
}
//***End NEW WINDOW

//***Begin NEW WINDOW
function trickyWindow( tURL, windowName, width, height ) 
{
	var widthString = "width=" + width;
	alert(" Where am I?");
	var heightString = "height=" + height;
	var params = "resizable=yes, scrollbars=yes, " + widthString + "," + heightString;

	msgWindow = open( tURL, windowName, params );
	if( msgWindow.opener == null ) 
	{
		msgWindow.opener = self;
	}
	//msgWindow.location.reload(); //refresh pop-up window. bug fix 27558
}
//***End NEW WINDOW


//***Begin One RADIO BUTTON Required script 
function validateRadios( el, name, msg )
{
	var myOption = (-1);

	if( document.getElementsByName( name ).length > 1 )
	{
		for( i = 0; i < document.getElementsByName( name ).length; i++ )
		{
			if( document.getElementsByName( name )[ i ].checked )
			{
				myOption = i;
			}
		}

		if( myOption == (-1) )
		{
			alert( msg );

			return false;
		}
	}

	return true;
}
//***End One RADIO BUTTON Required script 


//***Begin RESET BUTTON Set Focus script 
function setFocus( theForm )
{
  for( var i = 0; i < theForm.length; i++ )
  {
    if( theForm.elements[ i ].type == 'text' )
    {
      theForm.elements[ i ].focus( );
      break;
    }
  }
}
//***End RESET BUTTON Set Focus script 


//***Begin SELECT SWAP MOVE script  
function addIt( fieldTo ) 
{ 
	var fieldFrom = document.form.listFrom;

	if( fieldFrom.selectedIndex >= 0 ) 
	{ 
		for( var i = fieldFrom.selectedIndex; i < fieldFrom.length; i++ ) 
		{ 
			var skip = false; 

			if( fieldFrom[ i ].selected ) 
			{ 
				if( i == (-1) || fieldFrom[ i ].value == '--' ) 
				{ 
					return; 
				} 

				for( var j = 0; j < fieldTo.options.length; j++ ) 
				{ 
					if( fieldFrom[ i ].text == fieldTo[ j ].text ) 
					{ 
						alert( "'" + fieldFrom[ i ].text + "' is already in your list." ); 
						skip = true; 
						break; 
					} 
				} 

				if( skip ) 
				{ 
					continue; 
				} 

				fieldTo.options[ fieldTo.options.length ] = new Option( fieldFrom[ i ].text, fieldFrom[ i ].value ); 
			} 
		} 
	} 
	else 
	{ 
		alert( "Please select item to be added." ); 
	} 
} 


function move( field1, field2, moveAll ) 
{   	    	
	for( var i = 0; i < field1.length; i++ ) 
	{
		if( field1.options[ i ].selected  || moveAll ) 
		{
			var option = new Option( field1.options[ i ].text, field1.options[ i ].value );
			
			// For conforming to W3C standards
			// field2.add( option, field2.options[ field2.options.length ] );
			
			// For implementing IE
			field2.add( option, 0 );
			
			field1.remove( i );
			i--;
		}
	}
}
	
function removeIt( fieldFrom ) 
{ 
	if( fieldFrom.selectedIndex >= 0 ) 
	{ 
		for( var i = fieldFrom.length -1, s = fieldFrom.selectedIndex; i >= s ; i-- ) 
		{ 
			if( fieldFrom.options[ i ].selected ) 
			{ 
				fieldFrom.options[ i ] = null; 
			} 
		} 
	} 
	else 
	{ 
		alert( "Please select item to be removed." ); 
	} 
}


function removeAll( fieldFrom ) 
{	 
	for( var i = fieldFrom.length -1, s = fieldFrom.selectedIndex; i >= s ; i-- ) 
	{
		fieldFrom.options[ i ] = null;
	}	
}

    
function selectAllData( field1 ) 
{    	
	for( var i = 0; i < field1.length; i++ ) 
	{
		field1.options[ i ].selected = true;
	}
}
//***End SELECT SWAP MOVE script  


//***Begin SHOW HIDE FOR SINGLE ROW Script 
function showHide( what, hide )
{
	if( ! hide )
	{
		document.getElementById( what ).className = 'hidden';
	}
  else 
  {
	 	document.getElementById( what ).className = 'visible';
	}
}
//***End SHOW HIDE FOR SINGLE ROW Script 


//***Begin SHOW HIDE FOR MULTIPLE ROWS
//show hide where there is an expand( plus sign ) - and u want to show multiple rows simultaneously
 /*params: imgName - name of the + or - sign, rowPrepend - ID prepend of the row, rowNums - number of rows to hide/show
 */
function showHideMulti( imgName, rowPrepend, rowNums, path )
{
  var appendName = rowPrepend + rowNums;
  var currentImage = window.document.images[ imgName ].src;
 
  if( currentImage.indexOf( "contract" ) < 0 )
  {
		window.document.images[ imgName ].src = path + "/images/contract.gif";
  } 
  else
  {
		window.document.images[ imgName ].src = path + "/images/expand.gif";
  }
 	
  for( var i = 0; i < rowNums; i++ ) 
  {
		appendName = rowPrepend + i;

		if( document.getElementById( appendName ).className == 'visibleTR' )
		{
			document.getElementById( appendName ).className = 'hidden';
		} 
		else
		{
			document.getElementById( appendName ).className = 'visibleTR';
		}
  }
}
//***End SHOW HIDE FOR MULTIPLE ROWS


//***Begin SHOW HIDE when Radio Button not checked
//Hides the new associates fields in relese to person 
//when radio button not checked
function show( what, hide, format )
{
 	if( ! hide )
	{
		document.getElementById( what ).className = 'hidden';
	}
	else if( format == "row" )
	{
		document.getElementById( what ).className = 'visibleTR';
	}
	else if( format == "table" )
	{
		document.getElementById( what ).className = 'visibleTable';
	}
	else if( format == "formDe" )
	{
		document.getElementById( what ).className = 'formDe';
	}
	else 
	{			
		document.getElementById( what ).className = 'visible';
	}
}
//***End SHOW HIDE when Radio Button not checked


//***Begin SUBMIT GET BUTTON script   
function getSubmitBtn( )
{
	var subBtnElems = document.getElementsByName( "submitAction" );

	if( subBtnElems == null )
	{
		return null;
	}
	else
	{
		var btnToFire = null;
		var counter = 0;

		for( i = 0; i < subBtnElems.length; i++ ) 
		{
		  if(( subBtnElems[ i ].value.toUpperCase( ).match( "BACK" ) ) == null && 
				( subBtnElems[ i ].value.toUpperCase( ).match( "CANCEL" ) ) == null && 
		   	( subBtnElems[ i ].value.toUpperCase( ).match( "REFRESH" ) ) == null && 		    			   
		 		( subBtnElems[ i ].value.toUpperCase( ).match( "RESET" ) ) == null )  		    	
		 	{
		  	btnToFire = subBtnElems[ i ];
		  	counter++ ;
		  }
		}

		if( counter != 1 )
		{
		  return null;
		} 
		else
		{
			return btnToFire;
	  }
	}	
}
//***End SUBMIT GET BUTTON script 


//***Begin SUBMIT SORT FORM Script
function submitSortForm( theForm, tURL, theElement )
{
	var selectedChars = document.getElementsByName( "selectedChar" );
	theForm.action = tURL;

	for( var i = 0; i < selectedChars.length; i++ )
	{
	    var elem = selectedChars[ i ];

	    elem.value = theElement.options[ theElement.selectedIndex ].value;
	}

	if( theElement.options[ theElement.selectedIndex ].value != "0" ) 
	{
		theForm.submit( );
	}
}
//***End SUBMIT SORT FORM Script


//***Begin TEXTAREA maxLength & Text Area Too Large JAVASCRIPT
function textCounter( field, maxlimit )
{
  if( field.value.length > maxlimit )
  {
    alert( 'Maximum text length reached for this field' );
    field.value = field.value.substring( 0, maxlimit );
  } 
  else
  {
    maxlimit = maxlimit - field.value.length;
  }
}

function alertTextAreaToLarge( field, maxlimit, fieldName )
{  
	if( field.value.length >= maxlimit ) 
	{		 
    alert( fieldName+" has exceeded the max limit of " + maxlimit + " characters." );
    return false;
 	}

	return true;
}

//***End TEXTAREA maxLength & Text Area Too Large JAVASCRIPT


//***Begin TRIM Script
function Trim( s )
{
	// Remove leading spaces and carriage returns
	while( (s.substring( 0,1 ) == ' ') || (s.substring( 0,1 ) == '\n') || (s.substring( 0,1 ) == '\r') )
	{ 
		s = s.substring( 1, s.length ); 
	}
	
	// Remove trailing spaces and carriage returns
	while(( s.substring( s.length -1, s.length ) == ' ' ) || 
			(s.substring( s.length -1, s.length ) == '\n') ||
			(s.substring( s.length -1, s.length ) == '\r') )
	{ 
		s = s.substring( 0, s.length -1 ); 
	}
	
	return s;
}
//***End TRIM Script


//***Begin VALIDATE ADD Script
function validateAdd( theForm, checkboxName, errorMsg )
{
	var checkbox = theForm[ checkboxName ];
	
	if( checkbox.value )
	{
		if( checkbox.checked )
	  {
			return true;
		}
	}
	else
	{
		for( i = 0; i < checkbox.length; i++ )
		{
			if( checkbox[ i ].checked )
			{
				return true;
			}
		}
	}
	
	alert( errorMsg );

	return false;
}
//***End VALIDATE ADD Script


//***Begin VALIDATE SSN Script 
function validateSSN( elem1Name,elem2Name,elem3Name, msgSSN, isRequired )
{
	var elem1Val = Trim( document.getElementById( elem1Name ).value );
	var elem2Val = Trim( document.getElementById( elem2Name ).value );
	var elem3Val = Trim( document.getElementById( elem3Name ).value );

	if( isRequired && elem1Val == "" && elem2Val == "" && elem3Val == "" )
	{
    alert( msgSSN + " is required" );
    document.getElementById( elem1Name ).focus( );

    return false;
   }

	if( document.getElementById( elem1Name ).value > "" && document.getElementById( elem2Name ).value == "" ) 
	{
    alert( msgSSN + " must be entered if there is partial entry." );
    document.getElementById( elem2Name ).focus( );

    return false;
  }

  if( document.getElementById( elem1Name ).value > "" && document.getElementById( elem3Name ).value == "" ) 
  {
    alert( msgSSN + " must be entered if there is partial entry." );
    document.getElementById( elem3Name ).focus( );

    return false;
  }

  if( document.getElementById( elem2Name ).value > "" && document.getElementById( elem1Name ).value == "" ) 
  {
    alert( msgSSN + " must be entered if there is partial entry." );
    document.getElementById( elem1Name ).focus( );

    return false;
  }

  if( document.getElementById( elem2Name ).value > "" && document.getElementById( elem3Name ).value == "" ) 
  {
    alert( msgSSN + " must be entered if there is partial entry." );
    document.getElementById( elem3Name ).focus( );

    return false;
  }

  if( document.getElementById( elem3Name ).value > "" && document.getElementById( elem1Name ).value == "" ) 
  {
    alert( msgSSN + " must be entered if there is partial entry." );
    document.getElementById( elem1Name ).focus( );

    return false;
  }

  if( document.getElementById( elem3Name ).value > "" && document.getElementById( elem2Name ).value == "" ) 
  {
    alert( msgSSN + " must be entered if there is partial entry." );
    document.getElementById( elem2Name ).focus( );

    return false;
  }  

	return true;
}
//***End VALIDATE SSN Script 


//***Begin New WINDOW Script
function openWindow(url)
{
	var newWin = window.open(url, "pictureWindow", "height=400,width=600,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	newWin.focus();
}
//***End New WINDOW Script

//*** Begin phone number validation: returns true if phone number has required parts does not check length
// use "" for extension if there isn't an extension field
function validatePhone(areaCodeElemName, prefixElemName, lastFourDigitElemName, aExtension, phoneErrorMsgName, theForm){
	if (theForm[areaCodeElemName].value > "" && theForm[prefixElemName].value == "") {
      alert(phoneErrorMsgName + " Prefix must be entered if Area Code is entered.");
      theForm[prefixElemName].focus();
      return false;
   }
   if (theForm[prefixElemName].value > "" && theForm[lastFourDigitElemName].value == "") {
      alert(phoneErrorMsgName + " Last Four Digits must be entered if Prefix is entered.");
      theForm[lastFourDigitElemName].focus();
      return false;
   }
   if (theForm[prefixElemName].value == "" && theForm[lastFourDigitElemName].value > "") {
      alert(phoneErrorMsgName + " Prefix must be entered if last four digits are entered.");
      theForm[prefixElemName].focus();
      return false;
     }
    if (theForm[areaCodeElemName].value == "" && theForm[lastFourDigitElemName].value > "") {
      alert(phoneErrorMsgName + " Area Code must be entered if last four digits are entered.");
      theForm[areaCodeElemName].focus();
      return false;
     } 
     if(aExtension!=null && aExtension != "" && aExtension !="null" && aExtension > ""){
	     if (theForm[aExtension].value > "" && theForm[lastFourDigitElemName].value == "") {
	      alert(phoneErrorMsgName + " Last Four Digits must be entered if extension is entered.");
	      theForm[lastFourDigitElemName].focus();
	      return false;
	     }
     }
    return true;
}
//**** End phone validation

//***Begin back to top Script - this renders the back to top link at the bottom of most pages
function renderBackToTop(){
	document.write("[<a href=\"javascript:void(0);\" onclick=\"goNav('#top')\">Back to Top</a>]");
}
//**** End Begin back to top Script

<!--  End Javascripts for JIMS2 App View  -->
