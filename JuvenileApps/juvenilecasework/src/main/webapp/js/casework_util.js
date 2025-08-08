  // Passing in a boolean true for disableEnter will disable the Enter Key
  // Use in conjunction with an event such as : onKeyDown="checkEnterKey(event,true)"
  function checkEnterKeyAndSubmit(evt,disableEnter)
  {
    var RETURN_KEY = 13 ;
    var THE_N_KEY = 78 ;
	var currentElement = evt.srcElement;
    var key = evt.keyCode;
  
    if( (evt.ctrlKey == true) && (key == THE_N_KEY) )
   	{  // the n key
		alert("Sorry opening a new browser window is not allowed.");
		disableEvent(evt);
		  
		return false;
    }
  
    if( disableEnter == true && key == RETURN_KEY )
  	{
		if( (currentElement != null)  && (currentElement.type == 'textarea') )
		{
          return true;
		}

		var foundBtn = getSubmitBtn();
    
		if(foundBtn == null)
		{
		    alert("This page has multiple submit buttons.\n\nPlease select appropriate submit button.");
		    disableEvent(evt);
		    return false;
		}
		else
		{
		    foundBtn.click();
		    return false;
		}
    }
  
    return true;
  } 
  
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
   
  function disableEvent(evt)
  {
    evt.returnValue = false; 
    evt.cancel = true;
  }
   
	function getSubmitBtn()
	{
		var subBtnElems = document.getElementsByName("submitAction");
		
		if(subBtnElems == null)
		{
			return null;
		}
		else
		{
			var NOT_FOUND = (-1) ;
			var btnToFire = null;
			var counter = 0;
			var buttonStrings = "back cancel reset refresh" ;
			var str = "" ;
			var offset = 0 ;
			
			for(i = 0; i < subBtnElems.length; i++) 
			{
				str = subBtnElems[i].value.toLowerCase() ;
				offset = buttonStrings.indexOf( str ) ;

				if( offset == NOT_FOUND )
				{
					btnToFire = subBtnElems[i];	          
					counter++;
				}			
			}// for

			if(counter != 1)
			{
				return null;
			}
			else
			{
				return btnToFire;
			}
		}// else
	}

	//this function alerts a message and then redirects to another page
	/*params: msg - message to be alerted
					  location - new page to be redirected to
	*/
	function alertAndGo(msg, location)
	{
		alert(msg);
		window.location.href = location;
	}

	//this function redirects to another page
	/*params: location - new page to be redirected to
	*/
	function goNav(location)
	{
		if (location == "back")
		{
			history.go(-1);
		}
		else
		{
			window.location.href = location;
		}
	}

	//changes the background color of the row where the checkbox is
	//row has incrementing number Id, and checkbox has same value
	//param: chk - the checkbox form object
	function chooseRow(chk) 
	{
		//alternating row scriptlet - first row (RecordCounter) is 1
		var	num = chk.value;

		//create row id
		id = "row" + num;

		var thisRow = document.getElementById(id);

		//checkbox is checked - change background color	- check if alternating row
		//alternating row turned off here...
		if (chk.checked)
		{
			thisRow.className = "selectedRow";
		}
		else
		{
			thisRow.className = "";
		}
	}

	//called onload to check if checkboxes are checked and set color
	//param: formNum pass in which form ie:0 for the first
	function maintainChks(formNum)
	{
		//get form which checkboxes are in
		elem = document.forms[formNum];

		for(i = 0; i < elem.length; i++) 
		{
		    if(elem[i].type == "checkbox")
		    {
		    	if(elem[i].checked)
		    	{
		    		var	num = elem[i].value;
		    		var id = "row" + num;
		    		document.getElementById(id).className = "selectedRow";
		    	}
		    }
		}
	}

	//called to confirm the Cancel button
	//param: msg - passed to verify f(x)
	//param: location - where to send the user if they click OK on the confirm box - passed to goNav f(x)

	function cancelConfirm(msg, location)
	{
		if (verify(msg))
		{
			goNav(location);
		}
		else 
		{
			return false;
		}
	}

	//called by cancel confirm
	//returns true/false
	//param: msg - message in the confirm box
	function verify(msg)
	{
		return confirm(msg);
	}

	function openPictureWindow(url)
	{
		var newWin = window.open(url, "pictureWindow", "height=220,width=200,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		newWin.focus();
	}

	function openWindow(url)
	{
		var newWin = window.open(url, "pictureWindow", "height=300,width=400,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		newWin.focus();
	}


	function openCustomRestrictiveWindow(url,heightSize, widthSize)
	{
		var newWin = window.open(url, "pictureWindow", "height=" + heightSize +",width=" + widthSize + ",toolbar=no,scrollbars=yes,resizable=no,status=no,location=no,menubar=no,dependent=no");
		newWin.focus();
	}

// makes code more readable:
var HIDE_ITEM = 0 ;
var SHOW_ITEM = 1 ;

	function showHide(what, hide)
	{
		if(document.getElementById(what) != null)
		{
			if (hide == HIDE_ITEM)
			{
				document.getElementById(what).className = 'hidden';
			}
			else
			{
				document.getElementById(what).className = 'visible';
			}
		}
	}
		
	
	function showHide2(elementName, elementType, hide)
	{
		if(document.getElementById(elementName) != null)
		{
			if (hide == HIDE_ITEM)
			{
				document.getElementById(elementName).className = 'hidden';
			}
			else
			{
				if(elementType == "row")
				{
					document.getElementById(elementName).className = 'visibleTR';
				}
				else if(elementType == "table")
				{
					document.getElementById(elementName).className = 'visibleTable';
				}
				else 
				{
					document.getElementById(elementName).className = 'visible';
				}
			}
		}
	}
	
	//show hide where there is an expand (plus sign) - and u want to show multiple rows simultaneously
 /*params:
 imgName - name of the + or - sign - has to be unique
 rowPrepend - ID prepend of the row - has to be unique
 rowNums - number of rows to hide/show - size of the collection
 path - img path (<msp tag>)
 */

 function showHideMulti(imgName, rowPrepend, rowNums, path)
 {	
	  var appendName = rowPrepend + rowNums;
	
	  if (imgName != "")
	  {
	  	var currentImage = window.document.images[imgName].src;
	
		if (currentImage.indexOf("contract") < 0)
		{
		   window.document.images[imgName].src = path + "/images/contract.gif";
		} 
		else
		{
		   window.document.images[imgName].src = path + "/images/expand.gif";
		}
	  }
	
	  for (var i = 0; i < rowNums; i++ ) 
	  {
		   appendName = rowPrepend + i ;
		  // alert("appendName "+document.getElementById( appendName ).className);
		   if(document.getElementById(appendName) != null)
		   {
			   if (document.getElementById(appendName).className == 'visibleTR' || 
					   document.getElementById(appendName).className == 'visible')
			   {
				   document.getElementById(appendName).style.display ="";
				   document.getElementById(appendName).style.visibility="hidden";				   
				    show(appendName,HIDE_ITEM);    
			   } 
			   else
			   {
				   document.getElementById(appendName).style.visibility="visible";
				   	show(appendName,SHOW_ITEM,"row");    
			   }
		   }
	  }
 }
 
 //show hide 
 /*params:
what - the id of the thing to show
hide - 0 = hide 1 = show
format - table, row, or "" (for spans)
 */
 function show(what, hide, format)
 {
	if(document.getElementById(what) != null)
	{
		if (hide == HIDE_ITEM) 
		{
			document.getElementById(what).className = 'hidden';
		}
		else if(format == "row")
		{
			document.getElementById(what).className = 'visibleTR';
		}
		else if(format == "table")
		{
			document.getElementById(what).className = 'visibleTable';
		}
		else if(format == "inline")
		{
			document.getElementById(what).className = 'visibleInline';
		}
		else 
		{
			document.getElementById(what).className = 'visible';
		}
	}
}
 
function showHideMultiNoExpansion(rowPrepend, rowNums, hide)
{
  var appendName = rowPrepend + rowNums;

  for (var i = 0; i < rowNums; i++) 
  {
   appendName = rowPrepend + i;
   if (hide == HIDE_ITEM)
   {
    show(appendName,HIDE_ITEM);
   } 
   else
   {
   	show(appendName, SHOW_ITEM,"row");
   }
  }// for
 }

//show hide where there is an expand (plus sign)
/*params: spanName - name of the span to hide or show - image has same id as span -"Span"
	format: table, row, etc
*/
function showHideExpand(spanName, format, path)
{
	var appendName = spanName + "Span";
	if(document.getElementById(appendName) != null)
	{
		var theClassName = document.getElementById(appendName).className;
		if (theClassName == 'visible' || theClassName == 'visibleTR' || theClassName == 'visibleTable')
		{
			window.document.images[spanName].src = path + "images/expand.gif";
			document.getElementById(appendName).className = 'hidden';
		}
		else 
		{
			window.document.images[spanName].src = path + "images/contract.gif";
			if(format == "row")
			{
				document.getElementById(appendName).className = 'visibleTR';
			}
			else if(format == "table")
		    {
				document.getElementById(appendName).className = 'visibleTable';
			}
			else 
			{			
				document.getElementById(appendName).className = 'visible';
			}						
		}
	}
}

function showSomethingFromDropDown(el, prePend, rowNums)
{
	var selectedValue = el.options[el.selectedIndex].value;
	var prePendToSend = prePend + selectedValue;

	for (var i = 0; i < el.length; i++)
	{
		if (selectedValue != el.options[i].value)
		{
			showHideMultiNoExpansion(prePend + el.options[i].value, rowNums, HIDE_ITEM)
		}
		else
		{
			showHideMultiNoExpansion(prePendToSend, rowNums, SHOW_ITEM)
		}
	}

}

function openCustomRestrictiveWindow(url,heightSize, widthSize)
{
	var newWin = window.open(url, "pictureWindow", "height=" + 
			heightSize +",width=" + widthSize + 
			",toolbar=no,scrollbars=yes,resizable=no,status=no,location=no,menubar=no,dependent=no");
	newWin.focus();
}

//***Begin back to top Script - this renders the back to top link at the bottom of most pages
//this script also lives in app.js
function renderBackToTop(){
	document.write("[<a href=\"javascript:void(0);\" onclick=\"goNav('#top')\">Back to Top</a>]");
}
//**** End Begin back to top Script


